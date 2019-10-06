package com.java.online.service;

import com.java.online.code.Request;
import com.java.online.code.Response;

public interface CodeService {
    Response run(Request request);
}
