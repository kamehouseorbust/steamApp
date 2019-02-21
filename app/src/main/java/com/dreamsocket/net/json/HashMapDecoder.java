package com.dreamsocket.net.json;


import com.dreamsocket.net.IObjectDecoder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;

public class HashMapDecoder<T> implements IJSONObjectDecoder<HashMap<String, T>>, IObjectDecoder<HashMap<String, T>>{

    protected IJSONDecoder<T> m_jsonDecoder;
    protected IObjectDecoder<T> m_objectDecoder;

    public HashMapDecoder(IJSONDecoder<T> p_decoder){
        this.m_jsonDecoder = p_decoder;
    }

    public HashMapDecoder(IObjectDecoder<T> p_decoder){
        this.m_objectDecoder = p_decoder;
    }


    public HashMap<String, T> decode(Object p_input) throws Throwable{
        if(p_input instanceof JSONObject) {
            return this.decode((JSONObject) p_input);
        }
        return null;
    }


    @SuppressWarnings("unchecked")
    public HashMap<String, T> decode(JSONObject p_input) throws Throwable{
        HashMap<String, T> data = new HashMap<>();
        Iterator<String> keys = p_input.keys();

        while(keys.hasNext()){
            try{
                String key = keys.next();
                Object o = p_input.opt(key);

                if(o instanceof JSONObject){
                    if(this.m_jsonDecoder != null) {
                        data.put(key, this.m_jsonDecoder.decode((JSONObject) p_input.opt(key)));
                    }
                    else{
                        data.put(key, this.m_objectDecoder.decode(p_input.opt(key)));
                    }
                }
                else if(o instanceof JSONArray){
                    if(this.m_jsonDecoder != null) {
                        data.put(key, (T) this.m_jsonDecoder.decode((JSONArray) p_input.opt(key)));
                    }
                    else{
                        data.put(key, this.m_objectDecoder.decode(p_input.opt(key)));
                    }
                }
                else{
                    data.put(key, this.m_objectDecoder.decode(p_input.opt(key)));
                }
            }
            catch (Exception error){}
        }

        return data;
    }

}
