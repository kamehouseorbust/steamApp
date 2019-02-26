package com.hdrussell.apps.steam.main

import android.content.res.Resources
import android.os.Bundle
import android.provider.ContactsContract
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.hdrussell.apps.R
import com.hdrussell.widgets.UIDrawer
import com.hdrussell.widgets.UINav
import com.hdrussell.widgets.UIToolbar
import kotlinx.android.synthetic.main.activity_main.*

class MainController : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val drawerLayout: UIDrawer = findViewById(R.id.drawer_layout)
        var currentView: View = findViewById(R.id.main)

        val navigationView: UINav = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            drawerLayout.closeDrawers()

            val parent: ViewGroup = currentView.parent as ViewGroup
            val index: Int = parent.indexOfChild(currentView)
            parent.removeView(currentView)

            when(menuItem.title) {
                "Profile" -> currentView = layoutInflater.inflate(R.layout.profile, parent, false)
                "Friends" -> currentView = layoutInflater.inflate(R.layout.friends, parent, false)
                "Games" -> currentView = layoutInflater.inflate(R.layout.games, parent, false)
            }

            parent.addView(currentView, index)



            true
        }

        val toolbar: UIToolbar = findViewById(R.id.toolbar)
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