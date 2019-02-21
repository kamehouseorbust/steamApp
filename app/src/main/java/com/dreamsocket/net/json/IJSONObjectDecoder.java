package com.dreamsocket.net.json;

import org.json.JSONObject;


public interface IJSONObjectDecoder<M> {
    M decode(JSONObject p_input) throws Throwable;
}
