package com.dreamsocket.screen.orientation;


import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.view.OrientationEventListener;

import com.dreamsocket.events.RxBus;

import java.lang.ref.WeakReference;
import java.util.WeakHashMap;


public class ScreenOrientationManager extends RxBus {

    protected static final int k_THRESHOLD = 15;

    protected WeakReference<Activity> m_activity;
    protected boolean m_invalid;
    protected WeakHashMap<Object, Boolean> m_locks;
    protected ScreenOrientation m_orientation;
    protected OrientationEventListener m_orientationListener;


    public ScreenOrientationManager(){
        this.m_orientation = ScreenOrientation.UNDEFINED;
        this.m_locks = new WeakHashMap<>();
    }


    public void setActivity(Activity p_activity){
        this.m_activity = new WeakReference(p_activity);

        if(this.m_orientation == ScreenOrientation.UNDEFINED) {
            switch (p_activity.getResources().getConfiguration().orientation) {
                case Configuration.ORIENTATION_LANDSCAPE:
                    this.m_orientation = ScreenOrientation.LANDSCAPE;
                    break;
                case Configuration.ORIENTATION_PORTRAIT:
                    this.m_orientation = ScreenOrientation.PORTRAIT;
                    break;
                default:
                    this.m_orientation = ScreenOrientation.UNDEFINED;
                    break;
            }
        }
        this.m_orientationListener = new OrientationEventListener(p_activity) {
            @Override
            public void onOrientationChanged(int orientation) {
                checkOrientationRequest(orientation);
            }
        };
    }


    public int lockCount(){
        return this.m_locks.size();
    }


    public boolean locked(){
        return this.m_locks.size() > 0;
    }


    public ScreenOrientation orientation(){
        return this.m_orientation;
    }



    public ScreenOrientationManager lock(Object p_value){
        if(!this.m_locks.containsKey(p_value)) {
            this.m_locks.put(p_value, true);


            this.m_invalid = false;
            this.m_orientationListener.disable();

            this.m_activity.get().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        }

        return this;
    }


    public ScreenOrientationManager release(){
        this.m_locks.clear();
        this.m_activity.get().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);

        return this;
    }


    public ScreenOrientationManager release(Object p_value){
        if(this.m_locks.containsKey(p_value)) {
            this.m_locks.remove(p_value);

            if(this.m_locks.size() == 0) {
                this.m_activity.get().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
            }
        }

        return this;
    }


    public ScreenOrientationManager set(ScreenOrientation p_value){
        ScreenOrientation oldValue = this.m_orientation;
        boolean changed = p_value != oldValue;

        this.m_orientation = p_value;
        this.m_activity.get().setRequestedOrientation(p_value == ScreenOrientation.PORTRAIT ? ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT : ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);

        if(changed) {
            this.post(new ScreenOrientationChangedEvent(p_value, oldValue));
        }

        if(!this.locked()){
            this.m_invalid = true;
            this.m_orientationListener.enable();
        }
        return this;
    }


    public ScreenOrientationManager update(Configuration p_value){
        ScreenOrientation newOrientation = p_value.orientation == Configuration.ORIENTATION_LANDSCAPE ? ScreenOrientation.LANDSCAPE : ScreenOrientation.PORTRAIT;

        if(newOrientation != this.m_orientation && !this.m_invalid){
            this.m_orientation = newOrientation;
            this.post(new ScreenOrientationChangedEvent(newOrientation, this.m_orientation));
        }

        return this;
    }


    protected void checkOrientationRequest(int p_orientation) {
        if (p_orientation == OrientationEventListener.ORIENTATION_UNKNOWN){ return; }

        ScreenOrientation orientation =  null;
        if(p_orientation >= (90 - k_THRESHOLD) && p_orientation <= (270 + k_THRESHOLD)){
            orientation = ScreenOrientation.LANDSCAPE;
        }
        else if((p_orientation >= (360 - k_THRESHOLD) && p_orientation <= 360) || (p_orientation >= 0 && p_orientation <= k_THRESHOLD)) {
            orientation = ScreenOrientation.PORTRAIT;
        }

        if(orientation == this.m_orientation){
            this.m_activity.get().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
            this.m_orientationListener.disable();
            this.m_invalid = false;
        }
    }
}
