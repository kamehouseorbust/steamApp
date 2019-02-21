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

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;


abstract public class AbstractJSONDecoder<M> implements IJSONDecoder<M> {

	abstract public M decode(JSONObject p_input) throws Throwable;


	public ArrayList<M> decode(JSONArray p_value) throws Throwable{
		ArrayList<M> data = new ArrayList<>();

		if(p_value != null) {
			for (int i = 0; i < p_value.length(); i++) {
				try {
					data.add(this.decode(p_value.getJSONObject(i)));
				}
				catch (Throwable e) {
				}
			}
		}

		return data;
	}
}
