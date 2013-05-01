package com.riskcare.forums.server.service;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.riskcare.forums.server.event.UIEvent;

public class StatusServiceImpl implements StatusService {

	private static final Logger LOG = LoggerFactory.getLogger(StatusServiceImpl.class);
	
	private ConcurrentHashMap<String, UIEvent> updates;
	
	public StatusServiceImpl() {
		updates = new ConcurrentHashMap<String, UIEvent>();
	}

	@Override
	public List<UIEvent> getUIUpdates() {
        List<UIEvent> arr = new ArrayList<UIEvent>(updates.size());
        arr.addAll(updates.values());
        return arr;		
	}

	@Override
	public void setUpdates(String id, UIEvent value) {
		updates.put(id, value);
	}
	
    /**
     * Clears the updates queue
     * 
     */
    public void clearUpdates() {
    	LOG.debug("clearUpdates timer");
    	LOG.debug("Size of updates: " + updates.size());
        if (updates != null && updates.size() > 0) {
            for (Entry<String, UIEvent> entry : updates.entrySet()) {
                UIEvent value = entry.getValue();
                if (value != null) {
                    if (value.getCategoryVO() != null && value.getCategoryVO().getTimeToDie() != null &&
                            (DateTime.now()).isAfter(value.getCategoryVO().getTimeToDie())) {
                    	String s = entry.getKey();
                    	LOG.debug("clearUpdates - removing "+s);
                        updates.remove(s);
                        LOG.debug("Updates array size after cleanup : " + updates.size());
                    }
                    if (value.getPostVO() != null && value.getPostVO().getTimeToDie() != null &&
                            (DateTime.now()).isAfter(value.getPostVO().getTimeToDie())) {
                    	String s = entry.getKey();
                    	LOG.debug("clearUpdates - removing "+s);
                        updates.remove(s);
                        LOG.debug("Updates array size after cleanup : " + updates.size());
                    }                    
                }
            }
        }
    }

    /**
     * Clears the updates queue every 5secs
     */
    //ScheduledExecutorService starts with a delay of 5sec and polls every 3 seconds to clear concurrentHashMap.
    public void startClearUpdates() {
    	LOG.debug("Start clearing UI updates");
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        final Runnable updatesRun = new Runnable() {
            public void run() {
                clearUpdates();
            }
        };

        //UI Polls every 2 seconds, so we need to have a clearing after this but before 4 seconds.
        scheduler.scheduleWithFixedDelay(updatesRun, 0, 2, SECONDS);
    }	
}
