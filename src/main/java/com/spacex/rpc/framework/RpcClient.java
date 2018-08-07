package com.spacex.rpc.framework;

public class RpcClient {
    public static void main(String[] args) {
        String ip = "localhost";
        int port = 9001;
        IHelloService helloService = ProxyFactory.create(IHelloService.class, ip, port);
        System.out.println(helloService.sayHello("Marie"));
    }
}
