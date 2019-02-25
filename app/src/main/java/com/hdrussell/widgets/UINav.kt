package com.hdrussell.widgets

import android.content.Context
import android.support.design.widget.NavigationView
import android.util.AttributeSet

class UINav : NavigationView {

    constructor(context: Context?) : super(context) {
        this.init()
    }


    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        this.init()
    }


    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        this.init()
    }


    private fun init() {
        this.menu.getItem(0)?.isChecked = true
    }
}