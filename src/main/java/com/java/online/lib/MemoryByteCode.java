package com.java.online.lib;

import javax.tools.SimpleJavaFileObject;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

public class MemoryByteCode extends SimpleJavaFileObject {

    private ByteArrayOutputStream byteArrayOutputStream;

    public MemoryByteCode(String name) {
        super(URI.create("byte:///" + name + ".class"), Kind.CLASS);
    }

    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) {
        throw new IllegalStateException();
    }

    @Override
    public OutputStream openOutputStream() {
        this.byteArrayOutputStream = new ByteArrayOutputStream();
        return byteArrayOutputStream;
    }

    @Override
    public InputStream openInputStream() {
        throw new IllegalStateException();
    }

    public byte[] getBytes() {
        return byteArrayOutputStream.toByteArray();
    }
}
