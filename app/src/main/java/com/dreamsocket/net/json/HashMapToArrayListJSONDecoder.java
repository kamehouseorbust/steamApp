package com.dreamsocket.net.json;

import com.dreamsocket.net.DecoderSettings;
import com.dreamsocket.net.IStringDecoder;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.Iterator;


public class HashMapToArrayListJSONDecoder<T> implements IStringDecoder{

    protected IJSONObjectDecoder m_decoder;

    public HashMapToArrayListJSONDecoder(IJSONObjectDecoder p_decoder){
        this.m_decoder = p_decoder;
    }


    public Object decode(String p_input) throws Throwable{
        Object value = new JSONTokener(p_input).nextValue();

        if(value instanceof JSONObject)
            return this.decode((JSONObject)value);
        else
            return this.decode((JSONArray)value);
    }


    public ArrayList<T> decode(JSONObject p_input) throws Throwable{
        ArrayList<T> data = new ArrayList<>();
        Iterator<String> keys = p_input.keys();

        while(keys.hasNext()){
            try{
                String key = keys.next();
                data.add( (T)this.m_decoder.decode(p_input.optJSONObject(key)));
            }
            catch (Exception e){
                if(DecoderSettings.throwAllExceptions){
                    throw e;
                }
            }
        }

        return data;
    }


    public ArrayList<?> decode(JSONArray p_value) throws Throwable{
        ArrayList<T> data = new ArrayList<>();

        for(int i = 0; i < p_value.length(); i++){
            try{
                data.add((T)this.decode(p_value.getJSONObject(i)));
            }
            catch (Throwable e){
                if(DecoderSettings.throwAllExceptions){
                    throw e;
                }
            }
        }


        return data;
    }
}
