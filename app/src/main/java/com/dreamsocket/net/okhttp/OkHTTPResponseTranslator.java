package com.dreamsocket.net.okhttp;



import okhttp3.Response;

public class OkHTTPResponseTranslator implements IOkHTTPResponseTranslator<Response> {

    public Response decode(Response p_input) throws Throwable{
        return p_input;
    }
}
