package com.dreamsocket.net.json;


public class JSONParseException extends Exception{

    public JSONParseException() {
    }


    public JSONParseException(String p_detailMessage) {
        super(p_detailMessage);
    }


    public JSONParseException(String p_detailMessage, Throwable p_throwable) {
        super(p_detailMessage, p_throwable);
    }
}
