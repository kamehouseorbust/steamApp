package com.hdrussell.widgets

import android.content.Context
import android.support.v4.widget.DrawerLayout
import android.util.AttributeSet
import android.util.Log
import android.view.View

class UIDrawer : DrawerLayout {

    var dragEnabled: Boolean = false


    constructor(context: Context) : super(context) {
        this.init()
    }


    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        this.init()
    }


    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {
        this.init()
    }


    private fun init() {
        this.dragEnabled = true

        this.addDrawerListener(
                object : DrawerLayout.DrawerListener {
                    override fun onDrawerSlide(p0: View, p1: Float) {
                        Log.d("drawerElement", "drawerSlide")
                    }

                    override fun onDrawerOpened(p0: View) {
                        Log.d("drawerElement", "drawerOpened")
                    }

                    override fun onDrawerClosed(p0: View) {
                        Log.d("drawerElement", "drawerClosed")
                    }

                    override fun onDrawerStateChanged(p0: Int) {
                        Log.d("drawerElement", "drawerChanged")
                    }
                }
        )
    }

}