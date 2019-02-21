package com.dreamsocket.net.json;

import com.dreamsocket.screen.ScreenType;
import com.dreamsocket.net.DecoderSettings;
import com.dreamsocket.net.IObjectDecoder;
import com.dreamsocket.net.StringDecoder;

import org.json.JSONObject;

public class JSONPlatformValueSelector<T> implements IJSONValueSelector, IObjectDecoder<T> {


    public JSONPlatformValueSelector(){
        this(ScreenType.PHONE);
    }


    public JSONPlatformValueSelector(ScreenType p_screenType){
        this(p_screenType, new StringDecoder());
    }


    public JSONPlatformValueSelector(IObjectDecoder p_decoder){
        this(ScreenType.PHONE, p_decoder);
    }


    public JSONPlatformValueSelector(ScreenType p_screenType, boolean p_treatSmallTabletAsPhone){
        this(p_screenType, new StringDecoder(), p_treatSmallTabletAsPhone);
    }

    
    public JSONPlatformValueSelector(ScreenType p_screenType, IObjectDecoder<T> p_decoder){
        this(p_screenType, p_decoder, false);
    }


    public JSONPlatformValueSelector(ScreenType p_screenType, IObjectDecoder<T> p_decoder, boolean p_treatSmallTabletAsPhone){
        this.screenType = p_screenType;
        this.decoder = p_decoder;
        this.treatSmallTabletAsPhone = p_treatSmallTabletAsPhone;
    }


    public IObjectDecoder<T> decoder;
    public ScreenType screenType;
    public boolean treatSmallTabletAsPhone = false;


    public T decode(Object p_value) throws Throwable{
        return this.get(p_value);
    }


    public T get(Object p_value) throws Throwable{
        Object value = p_value;

        if (p_value instanceof JSONObject) {
            JSONObject val = (JSONObject) p_value;
            boolean tablet = this.screenType == ScreenType.TABLET_LARGE || (!this.treatSmallTabletAsPhone && this.screenType == ScreenType.TABLET_SMALL);

            if (tablet && val.has("android_tablet")) {
                value = val.opt("android_tablet");
            } else if (this.screenType == ScreenType.TABLET_LARGE && val.has("android_tablet_large")) {
                value = val.opt("android_tablet_large");
            } else if (this.screenType == ScreenType.TABLET_SMALL && val.has("android_tablet_small")) {
                value = val.opt("android_tablet_small");
            } else if (val.has("android_phone")) {
                value = val.opt("android_phone");
            } else if (val.has("android")) {
                value = val.opt("android");
            } else if (tablet && val.has("tablet")) {
                value = val.opt("tablet");
            } else if (this.screenType == ScreenType.TABLET_LARGE && val.has("tablet_large")) {
                value = val.opt("tablet_large");
            } else if (this.screenType == ScreenType.TABLET_SMALL && val.has("tablet_small")) {
                value = val.opt("tablet_small");
            } else if (val.has("phone")) {
                value = val.opt("phone");
            } else if (val.has("default")) {
                value = val.opt("default");
            }

        }

        try {
            return this.decoder.decode(value);
        } catch (Throwable e) {
            if(DecoderSettings.throwAllExceptions) {
                throw e;
            }
            else{
                return null;
            }
        }
    }
}
