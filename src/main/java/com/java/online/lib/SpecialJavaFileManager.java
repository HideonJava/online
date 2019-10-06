package com.java.online.lib;

import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import java.io.IOException;

public class SpecialJavaFileManager extends ForwardingJavaFileManager<StandardJavaFileManager> {

    private SpecialClassLoader classLoader;

    public SpecialJavaFileManager(StandardJavaFileManager standardJavaFileManager, SpecialClassLoader classLoader) {
        super(standardJavaFileManager);
        this.classLoader = classLoader;
    }
    public JavaFileObject getJavaFileForOutput(Location location, String name, JavaFileObject.Kind kind, FileObject sibling) throws IOException {
        MemoryByteCode memoryByteCode = new MemoryByteCode(name);
        classLoader.addClass(name, memoryByteCode);
        return memoryByteCode;
    }

    public ClassLoader getClassLoader(Location location) {
        return classLoader;
    }
}