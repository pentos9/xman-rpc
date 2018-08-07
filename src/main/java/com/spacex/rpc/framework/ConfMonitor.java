package com.spacex.rpc.framework;

import java.util.HashMap;
import java.util.Map;

public class ConfMonitor {

    public static Map<String, Class> conf = new HashMap<>();

    static {
        conf.put("com.spacex.rpc.framework.IHelloService", IHelloServiceImpl.class);
    }
}
