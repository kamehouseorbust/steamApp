package com.hdrussell.widgets

import android.content.Context
import android.support.v7.widget.Toolbar
import android.util.AttributeSet

class UIToolbar : Toolbar {

    constructor(context: Context?) : super(context) {
        this.init()
    }


    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        this.init()
    }


    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        this.init()
    }


    protected fun init() {}
}