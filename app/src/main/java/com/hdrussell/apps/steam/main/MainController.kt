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

    lateinit var currentView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val drawerLayout: UIDrawer = findViewById(R.id.drawer_layout)
        currentView = findViewById(R.id.main)
        changeView("Profile")

        val navigationView: UINav = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            drawerLayout.closeDrawers()
            changeView(menuItem?.title as String)
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

    private fun changeView(itemTitle: String?){
        val parent: ViewGroup = this.currentView.parent as ViewGroup
        val index: Int = parent.indexOfChild(this.currentView)
        parent.removeView(this.currentView)
        when(itemTitle) {
            "Profile" -> this.currentView = layoutInflater.inflate(R.layout.profile, parent, false)
            "Friends" -> this.currentView = layoutInflater.inflate(R.layout.friends, parent, false)
            "Games" -> this.currentView = layoutInflater.inflate(R.layout.games, parent, false)
            else -> this.currentView = layoutInflater.inflate(R.layout.profile, parent, false)
        }
        parent.addView(this.currentView, index)
    }
}