package com.java.online.util;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public final class PortUtil {

    private PortUtil(){
        throw new UnsupportedOperationException();
    }

    public static boolean available(int port) {
        boolean use = true;
        Socket socket = null;
        try {
            String host = InetAddress.getLocalHost().getHostAddress();
            socket = new Socket(host, port);
        } catch (IOException e) {
            use = false;
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return use;
    }
}
