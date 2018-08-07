package com.spacex.rpc.framework;

import java.io.Serializable;

public class RpcObject implements Serializable {
    private Class clazz;
    private String methodName;
    private Object[] args;

    public RpcObject() {
    }

    public RpcObject(Class clazz, String methodName, Object[] args) {
        this.clazz = clazz;
        this.methodName = methodName;
        this.args = args;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }
}
