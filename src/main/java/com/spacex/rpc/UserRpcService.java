package com.spacex.rpc;


import com.spacex.rpc.beans.User;

public interface UserRpcService {
    User getUser(long userId);
}
