package com.java.online.lib;

import java.util.HashMap;
import java.util.Map;

public class SpecialClassLoader extends ClassLoader {

    private Map<String, MemoryByteCode> memoryByteCodeMap = new HashMap<>();

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        MemoryByteCode current = memoryByteCodeMap.get(name);
        if (current == null){
            current = memoryByteCodeMap.get(name.replace(".","/"));
            if (current==null){
                return super.findClass(name);
            }
        }
        return defineClass(name, current.getBytes(), 0, current.getBytes().length);
    }

    public void addClass(String name, MemoryByteCode memoryByteCode) {
        memoryByteCodeMap.put(name, memoryByteCode);
    }
}
