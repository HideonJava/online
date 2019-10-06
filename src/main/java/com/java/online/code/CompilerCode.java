package com.java.online.code;

import com.java.online.lib.SpecialClassLoader;
import com.java.online.util.DomRender;
import com.java.online.util.JavaCompiler;

import java.util.List;

public class CompilerCode implements CodeChain {

    private CodeChain next;

    @Override
    public void nextChain(CodeChain next) {
        this.next = next;
    }

    @Override
    public void doChain(Request request, Response response, SpecialClassLoader classLoader) {
        List<String[]> result = JavaCompiler.execute(
                request.getCodeId(), request.getCode(), classLoader);
        if(result.isEmpty()){
            next.doChain(request, response, classLoader);
        }else{
            response.setCode(Response.FAILURE);
            response.setResult(DomRender.diagnostic(result));
        }
    }
}
