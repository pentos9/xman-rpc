package com.spacex.rpc.framework;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;

public class RpcProxy implements InvocationHandler, Serializable {

    private String ip;
    private int port;
    private Class targetClass;

    public RpcProxy(String ip, int port, Class targetClass) {
        this.ip = ip;
        this.port = port;
        this.targetClass = targetClass;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object object = null;

        Socket socket = new Socket(ip, port);
        RpcObject rpcObject = new RpcObject(targetClass, method.getName(), args);
        ObjectOutputStream outputStream = null;
        ObjectInputStream inputStream = null;


        outputStream = new ObjectOutputStream(socket.getOutputStream());
        outputStream.writeObject(rpcObject);
        outputStream.flush();//request and handle return result

        inputStream = new ObjectInputStream(socket.getInputStream());
        object = inputStream.readObject();

        outputStream.close();
        inputStream.close();

        return object;
    }
}
