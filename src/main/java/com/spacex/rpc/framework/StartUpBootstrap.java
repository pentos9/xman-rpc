package com.spacex.rpc.framework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class StartUpBootstrap {

    private static Logger logger = LoggerFactory.getLogger(StartUpBootstrap.class);

    private static final int port = 9001;

    public static void main(String[] args) {
        exportRpc(port);
    }

    public static void exportRpc(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);

            while (true) {
                logger.info("StartUpBootstrap starts successfully and it is listening now.");
                Socket socket = serverSocket.accept();
                new Thread(new RpcRequestHandler(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
