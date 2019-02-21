package com.dreamsocket.net.okhttp;


import com.dreamsocket.net.json.IJSONObjectDecoder;
import com.dreamsocket.net.json.JSONToObjectDecoder;

import okhttp3.Response;

public class OkHTTPJSONObjectTranslator<T> implements IOkHTTPResponseTranslator<T> {

    protected JSONToObjectDecoder<T> m_translator;

    public OkHTTPJSONObjectTranslator(){
    }


    public OkHTTPJSONObjectTranslator(IJSONObjectDecoder<T> p_translator){
        this.m_translator = new JSONToObjectDecoder(p_translator);
    }


    public T decode(Response p_input) throws Throwable{
        return this.m_translator.decode(p_input.body().string());
    }
}
