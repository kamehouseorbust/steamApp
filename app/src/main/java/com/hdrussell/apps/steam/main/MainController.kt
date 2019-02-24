package com.hdrussell.apps.steam.main

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.MenuItem
import android.view.View
import com.hdrussell.apps.R
import kotlinx.android.synthetic.main.activity_main.*

class MainController : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawer_layout)

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            drawerLayout.closeDrawers()

            true
        }

        drawerLayout.addDrawerListener(
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

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val actionbar: ActionBar? = supportActionBar
        actionbar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp)
        }
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                drawer_layout.openDrawer(GravityCompat.START)
                true
            } else -> super.onOptionsItemSelected(item)
        }
    }

}