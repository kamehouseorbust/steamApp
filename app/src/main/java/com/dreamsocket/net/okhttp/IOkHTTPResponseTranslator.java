package com.dreamsocket.net.okhttp;


import okhttp3.Response;

public interface IOkHTTPResponseTranslator<T> {
    T decode(Response p_input) throws Throwable;
}
