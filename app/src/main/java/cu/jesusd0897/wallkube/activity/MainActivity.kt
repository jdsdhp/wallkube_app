package cu.jesusd0897.wallkube.activity

import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import androidx.annotation.NonNull
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import cu.jesusd0897.wallkube.R
import cu.jesusd0897.wallkube.fragment.FavoritesFragment
import cu.jesusd0897.wallkube.fragment.SectionsFragment
import cu.jesusd0897.wallkube.util.replaceFragmentView

class MainActivity : ThemedActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var toolbar: Toolbar
    private lateinit var drawer: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
        toolbar.title = getString(R.string.sections)

        drawer = findViewById(R.id.drawer_layout)
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        toggle.isDrawerIndicatorEnabled = false
        val drawable =
            ResourcesCompat.getDrawable(resources, R.drawable.ic_short_text_black_24dp, theme)
        toggle.setHomeAsUpIndicator(drawable)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        toggle.setToolbarNavigationClickListener {
            if (drawer.isDrawerVisible(GravityCompat.START)) drawer.closeDrawer(GravityCompat.START)
            else drawer.openDrawer(GravityCompat.START)
        }
        navigationView.setNavigationItemSelectedListener(this)
        replaceFragmentView(supportFragmentManager, SectionsFragment.newInstance(), R.id.container)
    }

    override fun onNavigationItemSelected(@NonNull menuItem: MenuItem): Boolean { // Handle navigation view item clicks here.
        val id: Int = menuItem.itemId
        Handler().postDelayed({ handleNavSelected(id) }, 250)
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) drawer.closeDrawer(GravityCompat.START)
        else super.onBackPressed()
    }

    private fun handleNavSelected(itemId: Int) {
        when (itemId) {
            R.id.action_sections -> {
                toolbar.title = getString(R.string.sections)
                replaceFragmentView(
                    supportFragmentManager, SectionsFragment.newInstance(), R.id.container
                )
            }
            R.id.action_favorites -> {
                toolbar.title = getString(R.string.favorites)
                replaceFragmentView(
                    supportFragmentManager, FavoritesFragment.newInstance(), R.id.container
                )
            }
            R.id.action_dark_mode -> {
                switchTheme()
            }
        }
    }


}
