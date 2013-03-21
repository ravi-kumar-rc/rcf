package com.riskcare.forums.server.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* Session holder for Hibernate
*/
public class HibernateSession {
   
   public static final ThreadLocal<Session> threadLocalSession = new ThreadLocal<Session>();
   static final Logger LOG = LoggerFactory.getLogger(HibernateSession.class);
   
   /**
    * Gets the current session
    * @param sessionFactory
    * @return Session
    * @throws HibernateException
    */
   public static Session currentSession(SessionFactory sessionFactory) throws HibernateException {
       Session s = (Session) threadLocalSession.get();
       LOG.debug(" Session object from ThreadLocal "+s);
       if (s == null) {
           LOG.debug("New HibernateSession is created.");
           s = sessionFactory.openSession();
           threadLocalSession.set(s);
       } else if(!s.isOpen()) {
           LOG.debug("New HibernateSession is created.");
           s = sessionFactory.openSession();
          threadLocalSession.set(s);
       } else if(!s.isConnected()) {
          LOG.debug("New HibernateSession is created.");
          s = sessionFactory.openSession();
          threadLocalSession.set(s);
       }
       LOG.debug(" Returning Session object from ThreadLocal "+s);
       return s;
   }

   /**
    * Closes the current session
    * @throws HibernateException
    */
   public static void closeSession() throws HibernateException {
       Session s = (Session) threadLocalSession.get();
       threadLocalSession.set(null);
       if (s != null && s.isOpen()) {
           s.flush();
           s.close();
       }
       LOG.debug("HibernateSession is closed.");
   }
    
   /**
    * Closes the current session
    * @throws HibernateException
    */
   public static void clearSesssion() throws HibernateException {
       Session s = (Session) threadLocalSession.get();
       threadLocalSession.set(null);
       if (s != null && s.isOpen()) {
           s.clear();
           s.close();
       }
       LOG.debug("HibernateSession is closed.");
   }
   
}
