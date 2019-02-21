package com.dreamsocket.net.okhttp;


import com.dreamsocket.net.json.IJSONArrayListDecoder;
import com.dreamsocket.net.json.JSONToArrayListDecoder;

import java.util.ArrayList;

import okhttp3.Response;

public class OkHTTPJSONArrayListTranslator<T> implements IOkHTTPResponseTranslator<ArrayList<T>> {

    protected JSONToArrayListDecoder<T> m_translator;

    public OkHTTPJSONArrayListTranslator(){
    }


    public OkHTTPJSONArrayListTranslator(IJSONArrayListDecoder<T> p_translator){
        this.m_translator = new JSONToArrayListDecoder(p_translator);
    }


    public ArrayList<T> decode(Response p_input) throws Throwable{
        return this.m_translator.decode(p_input.body().string());
    }
}
