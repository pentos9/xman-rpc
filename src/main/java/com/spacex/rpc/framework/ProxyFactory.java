package com.spacex.rpc.framework;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ProxyFactory {

    public static <T> T create(Class<T> clazz, String ip, int port) {
        InvocationHandler handler = new RpcProxy(ip, port, clazz);
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, handler);
    }
}
