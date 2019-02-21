package com.dreamsocket.net.json;

import org.json.JSONArray;

import java.util.ArrayList;


public interface IJSONArrayListDecoder <M> {
    ArrayList<M> decode(JSONArray p_input) throws Throwable;
}
