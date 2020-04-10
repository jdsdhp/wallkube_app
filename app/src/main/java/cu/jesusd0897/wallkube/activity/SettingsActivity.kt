package cu.jesusd0897.wallkube.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import cu.jesusd0897.wallkube.R
import cu.jesusd0897.wallkube.fragment.SettingsFragment
import cu.jesusd0897.wallkube.util.KNav

class SettingsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
        KNav.replaceFragment(supportFragmentManager, SettingsFragment.newInstance(), R.id.container)
    }

    override fun onBackPressed() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

}