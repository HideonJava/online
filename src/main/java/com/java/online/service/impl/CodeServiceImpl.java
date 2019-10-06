package com.java.online.service.impl;

import com.java.online.code.*;
import com.java.online.code.Response;
import com.java.online.lib.SpecialClassLoader;
import com.java.online.service.CodeService;
import org.springframework.stereotype.Service;

@Service
public class CodeServiceImpl implements CodeService {

    private CodeChain prepareCode = new PrepareCode();
    private CodeChain compilerCode = new CompilerCode();
    private CodeChain runCode = new RunCode();

    @Override
    public Response run(Request request) {
        Response response = new Response();
        prepareCode.nextChain(compilerCode);
        compilerCode.nextChain(runCode);
        prepareCode.doChain(request, response, new SpecialClassLoader());
        return response;
    }
}
