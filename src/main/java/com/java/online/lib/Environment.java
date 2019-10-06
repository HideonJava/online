package com.java.online.lib;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public final class Environment {

    private static PrintStream defaultOut = System.out;
    private static InputStream defaultIn = System.in;
    private static ByteArrayOutputStream outputStream;
    private static ByteArrayInputStream inputStream;
    private static SecurityManager defaultSecurityManager = System.getSecurityManager();

    private Environment(){
        throw new UnsupportedOperationException();
    }

    public static void on(String input){
        if(!input.isEmpty()){
            inputStream = new ByteArrayInputStream(input.getBytes());
        }
        outputStream = new ByteArrayOutputStream();
        System.setIn(inputStream);
        System.setOut(new PrintStream(outputStream));
        System.setSecurityManager(new SandboxSecurityManager());
    }

    public static void off(){
        System.out.flush();
        System.setOut(defaultOut);
        System.setIn(defaultIn);
        System.setSecurityManager(defaultSecurityManager);
    }

    public static String getOut(){
        return outputStream.toString();
    }

}
