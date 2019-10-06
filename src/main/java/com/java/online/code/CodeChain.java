package com.java.online.code;

import com.java.online.lib.SpecialClassLoader;

public interface CodeChain {

    void nextChain(CodeChain next);

    void doChain(Request request, Response response, SpecialClassLoader classLoader);
}
