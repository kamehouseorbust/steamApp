package com.dreamsocket.screen.orientation;


public class ScreenOrientationChangedEvent {

    public ScreenOrientationChangedEvent(ScreenOrientation p_current, ScreenOrientation p_previous){
        this.current = p_current;
        this.previous = p_previous;
    }


    public final ScreenOrientation current;
    public final ScreenOrientation previous;
}