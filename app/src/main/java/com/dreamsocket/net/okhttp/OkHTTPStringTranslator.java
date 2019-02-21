package com.dreamsocket.net.okhttp;


import com.dreamsocket.net.IStringTranslator;

import java.util.ArrayList;

import okhttp3.Response;

public class OkHTTPStringTranslator<T> implements IOkHTTPResponseTranslator<T> {

    protected IStringTranslator<T> m_translator;

    public OkHTTPStringTranslator(){
    }


    public OkHTTPStringTranslator(IStringTranslator<T> p_translator){
        this.m_translator = p_translator;
    }


    public T decode(Response p_input) throws Throwable{
        return this.m_translator.decode(p_input.body().string());
    }
}
