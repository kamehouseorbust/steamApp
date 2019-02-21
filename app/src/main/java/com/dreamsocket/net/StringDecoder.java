package com.dreamsocket.net;

public class StringDecoder implements IStringTranslator<String>, IObjectDecoder {
    public String decode(String p_input) throws Throwable{
        return p_input;
    }


    public Object decode(Object p_input) throws Throwable{
        return p_input;
    }
}
