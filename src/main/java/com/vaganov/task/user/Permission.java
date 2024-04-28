package com.vaganov.task.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    USER_READ("user:read"),
    USER_UPDATE("user:update"),
    USER_CREATE("user:create"),
    USER_DELETE("user:delete"),
    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
    TASKS_READ("tasks:read"),
    TASKS_UPDATE("tasks:update"),
    TASKS_DELETE("tasks:delete"),
    TASKS_CREATE("tasks:create"),
    ;

    @Getter
    private final String permission;
}
