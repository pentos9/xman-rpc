package com.spacex.rpc.framework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GiantStartUpBootstrap {
    private static Logger logger = LoggerFactory.getLogger(GiantStartUpBootstrap.class);

    private static final int port = 8080;

    public static void main(String[] args) {
        exportRpc(9001);
    }

    public static void exportRpc(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            ExecutorService executorService = Executors.newFixedThreadPool(100);
            while (true) {
                logger.info("GiantStartUpBootstrap starts.");
                Socket socket = serverSocket.accept();
                executorService.submit(new RpcRequestHandler(socket));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
