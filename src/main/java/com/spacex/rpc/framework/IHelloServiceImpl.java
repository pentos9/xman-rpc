package com.spacex.rpc.framework;

public class IHelloServiceImpl implements IHelloService {
    @Override
    public String sayHello(String name) {
        return "hello " + name;
    }
}
