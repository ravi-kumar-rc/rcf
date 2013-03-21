package com.riskcare.forums.request.proxy;

import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.google.web.bindery.requestfactory.shared.ValueProxy;
import com.riskcare.forums.server.vo.NodeVO;

@ProxyFor(NodeVO.class)
public interface NodeProxy extends ValueProxy {

    public int getId();
    public Object getCategoryNode();
}
