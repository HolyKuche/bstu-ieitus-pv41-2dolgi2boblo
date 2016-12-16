package ru.tdtb.application.security;

import java.util.HashMap;
import java.util.Map;

public enum PermissionType {
    READ("read");

    private String name;

    private static Map<String, PermissionType> map = new HashMap<>();

    PermissionType(String name) {
        this.name = name;
    }

    static {
        for (PermissionType permissionType : values()) {
            map.put(permissionType.name, permissionType);
        }
    }

    public static PermissionType getByName(String name) {
        PermissionType permissionType = map.get(name);
        if (permissionType == null) {
            throw new IllegalArgumentException("name");
        }
        return permissionType;
    }
}
