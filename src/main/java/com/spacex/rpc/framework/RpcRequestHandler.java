package com.spacex.rpc.framework;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;


public class RpcRequestHandler implements Runnable {
    private Socket socket;

    public RpcRequestHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        ObjectInputStream objectInputStream = null;
        ObjectOutputStream objectOutputStream = null;

        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            RpcObject rpcRequestObject = (RpcObject) objectInputStream.readObject();

            Object targetObject = getTargetObject(rpcRequestObject.getClazz());
            Object resultObject = executeMethod(targetObject, rpcRequestObject.getMethodName(), rpcRequestObject.getArgs());

            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(resultObject);
            objectOutputStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }


    private Object executeMethod(Object object, String methodName, Object[] args) {
        Object objResult = null;
        Class[] requestParameterTypes = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];
            requestParameterTypes[i] = arg.getClass();
        }

        try {

            Method method = object.getClass().getMethod(methodName, requestParameterTypes);
            objResult = method.invoke(object, args);

        } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return objResult;
    }

    private Object getTargetObject(Class targetClass) {
        Object object = null;

        try {
            object = ConfMonitor.conf.get(targetClass.getName()).newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return object;
    }
}
