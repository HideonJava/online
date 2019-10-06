package com.java.online.code;

import com.java.online.lib.SpecialClassLoader;
import com.java.online.util.Constants;
import com.java.online.util.RegexpUtil;

public class PrepareCode implements CodeChain {

    private CodeChain next;

    @Override
    public void nextChain(CodeChain next) {
        this.next = next;
    }

    @Override
    public void doChain(Request request, Response response, SpecialClassLoader classLoader) {
        String code = RegexpUtil.deleteAll(request.getCode(),Constants.REGEXP_PKG);
        String codeId = RegexpUtil.matcher(request.getCode(), Constants.REGEXP_CLASS);
        if(code.isEmpty() || codeId.isEmpty()){
            return;
        }
        request.setCodeId(codeId);
        request.setCode(code);
        next.doChain(request, response, classLoader);
    }

}
