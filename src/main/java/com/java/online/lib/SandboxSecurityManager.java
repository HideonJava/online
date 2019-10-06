package com.java.online.lib;

import java.io.FilePermission;
import java.lang.reflect.ReflectPermission;
import java.security.Permission;
import java.security.SecurityPermission;
import java.util.PropertyPermission;
import java.util.logging.LoggingPermission;

public class SandboxSecurityManager extends SecurityManager {

    @Override
    public void checkExit(int status) {
        throw new SecurityException("非法退出");
    }

    @Override
    public void checkPermission(Permission permission) {
        conformPermissionToSandbox(permission);
    }

    @Override
    public void checkPermission(Permission permission, Object context) {
        conformPermissionToSandbox(permission);
    }

    private void conformPermissionToSandbox(Permission permission) {
        if (permission instanceof SecurityPermission) {
            if (permission.getName().startsWith("getProperty")) {
                return;
            }
        } else if (permission instanceof PropertyPermission) {
            if (permission.getActions().equals("read")) {
                return;
            }
        } else if (permission instanceof FilePermission) {
            if (permission.getActions().equals("read")) {
                return;
            }
        } else if (permission instanceof RuntimePermission
                || permission instanceof ReflectPermission
                || permission instanceof LoggingPermission) {
            return;
        };
        throw new SecurityException("没有权限:" + permission.getName());
    }

}
