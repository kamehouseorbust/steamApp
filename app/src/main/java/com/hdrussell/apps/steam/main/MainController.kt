package com.hdrussell.apps.steam.main

import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.hdrussell.apps.R
import com.hdrussell.apps.steam.news.services.GetGameNewsParams
import com.hdrussell.apps.steam.news.services.GetGameNewsService
import com.hdrussell.apps.steam.PrivateKey
import com.hdrussell.apps.steam.profile.services.GetPlayerSummaryParams
import com.hdrussell.apps.steam.profile.services.GetPlayerSummaryService
import com.hdrussell.widgets.UIDrawer
import com.hdrussell.widgets.UINav
import com.hdrussell.widgets.UIToolbar
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient

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
            "Profile" -> {
                this.currentView = layoutInflater.inflate(R.layout.profile, parent, false)
                val m_client = OkHttpClient()
                val playerItemsService = GetPlayerSummaryService(m_client)
                playerItemsService.execute(GetPlayerSummaryParams(PrivateKey().key, 76561197960435530.toLong(), "json")).subscribe(
                        {
                            var nameView = this.currentView.findViewById<TextView>(R.id.profile_name)
                            var imageView = this.currentView.findViewById<TextView>(R.id.profile_picture)
                            nameView.text = it.playerItemsList[0].realname
                            imageView.text = "Profile Image Url = " + it.playerItemsList[0].avatarfull
                        },
                        {
                            val f = 0
                        }
                )
            }
            "Friends" -> this.currentView = layoutInflater.inflate(R.layout.friends, parent, false)
            "Games" -> {
                this.currentView = layoutInflater.inflate(R.layout.games, parent, false)
                val m_client = OkHttpClient()
                val gameNewsService = GetGameNewsService(m_client)
                gameNewsService.execute(GetGameNewsParams((440).toDouble(), 3, (3000).toDouble(), "json")).subscribe(
                        {
                            var newsView1 = this.currentView.findViewById<TextView>(R.id.newsItem1)
                            newsView1.setText("1: " + it.gamesNewsItemsList[0].title)
                            var newsView2 = this.currentView.findViewById<TextView>(R.id.newsItem2)
                            newsView2.setText("2: " + it.gamesNewsItemsList[1].title)
                            var newsView3 = this.currentView.findViewById<TextView>(R.id.newsItem3)
                            newsView3.setText("3: " + it.gamesNewsItemsList[2].title)
                        },
                        {
                            val f = 0
                        }
                )
            }
            else -> this.currentView = layoutInflater.inflate(R.layout.profile, parent, false)
        }
        parent.addView(this.currentView, index)
    }
}