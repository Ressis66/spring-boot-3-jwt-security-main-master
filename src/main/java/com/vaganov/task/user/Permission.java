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
    ALBUMS_READ("albums:read"),
    ALBUMS_UPDATE("albums:update"),
    ALBUMS_DELETE("albums:delete"),
    ALBUMS_CREATE("albums:create"),
    POSTS_READ("posts:read"),
    POSTS_UPDATE("posts:update"),
    POSTS_DELETE("posts:delete"),
    POSTS_CREATE("posts:create")
    ;

    @Getter
    private final String permission;
}
