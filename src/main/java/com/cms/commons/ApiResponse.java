package com.cms.commons;


public record ApiResponse<T>(
        boolean success,
        String message,
        T data
) {
}