/**
 * Dreamsocket
 *
 * Copyright 2011 Dreamsocket.
 * All Rights Reserved.
 *
 * This software (the "Software") is the property of Dreamsocket and is protected by U.S. and
 * international intellectual property laws. No license is granted with respect to the
 * software and users may not, among other things, reproduce, prepare derivative works
 * of, modify, distribute, sublicense, reverse engineer, disassemble, remove, decompile,
 * or make any modifications of the Software without written permission from Dreamsocket.
 * Further, Dreamsocket does not authorize any user to remove or alter any trademark, logo,
 * copyright or other proprietary notice, legend, symbol, or label in the Software.
 * This notice is not intended to, and shall not, limit any rights Dreamsocket has under
 * applicable law.
 *
 */

package com.dreamsocket.net.json;

import com.dreamsocket.net.IStringTranslator;


import org.json.JSONObject;
import org.json.JSONTokener;


public class JSONToObjectDecoder<M> implements IStringTranslator<M>{

    protected IJSONObjectDecoder<M> m_decoder;


    public JSONToObjectDecoder(IJSONObjectDecoder<M> p_decoder){
        this.m_decoder = p_decoder;
    }


    @Override
    public M decode(String p_input) throws Throwable{
        return this.m_decoder.decode((JSONObject) new JSONTokener(p_input).nextValue());
    }
}
