package com.spacex.rpc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class RpcProxyInvoker implements InvocationHandler {

    private Class<?> clazz;

    public RpcProxyInvoker(Class<?> clazz) {
        this.clazz = clazz;
    }

    public static Object getProxy(Class<?> clazz) {
        RpcProxyInvoker rpcProxyInvoker = new RpcProxyInvoker(clazz);
        return Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, rpcProxyInvoker);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Class<?>[] paramTypes = new Class<?>[args.length];
        for (int index = 0; index < args.length; index++) {
            paramTypes[index] = args[index].getClass();
        }

        //Request
        Request request = new Request();
        request.setServiceName(clazz.getName());
        request.setMethodName(method.getName());
        request.setParamTypes(paramTypes);
        request.setParams(args);

        // ... 后续操作:序列化,网络操作等等

        return null;
    }


}