package com.dreamsocket.net.json;


import org.json.JSONArray;

import java.util.HashSet;

public class StringHashSetDecoder {
    public HashSet<String> decode(JSONArray p_value){
        HashSet<String> data = new HashSet<>();

        if(p_value != null) {
            for (int i = 0; i < p_value.length(); i++) {
                try {
                    data.add(p_value.getString(i));
                }
                catch (Throwable e) {
                }
            }
        }

        return data;
    }
}
