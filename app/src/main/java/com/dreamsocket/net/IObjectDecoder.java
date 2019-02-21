package com.dreamsocket.net;


public interface IObjectDecoder<T>{
    T decode(Object p_input) throws Throwable;
}
