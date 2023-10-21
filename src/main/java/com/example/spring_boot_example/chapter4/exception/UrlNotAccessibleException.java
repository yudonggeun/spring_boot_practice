package com.example.spring_boot_example.chapter4.exception;

public class UrlNotAccessibleException extends RuntimeException {
    private final String url;

    public UrlNotAccessibleException(String url){
        this(url, null);
    }

    public UrlNotAccessibleException(String url, Throwable cause) {
        super("URL " + url + " is not accessible", cause);
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
