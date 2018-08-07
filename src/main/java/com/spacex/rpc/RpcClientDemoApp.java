package com.spacex.rpc;

public class RpcClientDemoApp {
    public static void main(String[] args) {

        UserRpcService userRpcService = (UserRpcService) RpcProxyInvoker.getProxy(UserRpcService.class);
        userRpcService.getUser(1L);

        ClassLoader classLoader = ClassLoader.getSystemClassLoader();

    }

}
