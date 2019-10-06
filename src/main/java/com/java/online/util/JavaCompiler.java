package com.java.online.util;

import com.java.online.lib.MemorySource;
import com.java.online.lib.SpecialClassLoader;
import com.java.online.lib.SpecialJavaFileManager;

import javax.tools.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class JavaCompiler {

    private JavaCompiler() {
        throw new UnsupportedOperationException();
    }

    public static List<String[]> execute(String codeId, String code, SpecialClassLoader classLoader) {
        List<String[]> list = new ArrayList<>();
        javax.tools.JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> collector = new DiagnosticCollector<>();
        StandardJavaFileManager standardFileManager = compiler
                .getStandardFileManager(null, null, null);
        SpecialJavaFileManager specialJavaFileManager = new SpecialJavaFileManager(standardFileManager, classLoader);
        List<MemorySource> compilationUnits = Arrays.asList(new MemorySource(codeId, code));
        Boolean result = compiler.getTask(null, specialJavaFileManager, collector,
                null, null, compilationUnits).call();
        if (!result) {
            for (Diagnostic diagnostic : collector.getDiagnostics()) {
                String message = diagnostic.getMessage(null);
                long line = diagnostic.getLineNumber();
                list.add(new String[]{String.valueOf(line), message});
            }
        }
        return list;
    }
}
