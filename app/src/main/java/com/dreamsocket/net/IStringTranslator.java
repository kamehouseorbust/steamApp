package com.dreamsocket.net;



public interface IStringTranslator<T>{
    T decode(String p_value) throws Throwable;
}
