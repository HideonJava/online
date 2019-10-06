package com.java.online.controller;

import com.java.online.code.Request;
import com.java.online.code.Response;
import com.java.online.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CodeController {

    @Autowired
    private CodeService codeService;

    @GetMapping("/api")
    public Response run(Request request){
        return codeService.run(request);
    }

}
