package cu.jesusd0897.wallkube.activity

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import cu.jesusd0897.wallkube.R
import cu.jesusd0897.wallkube.fragment.ChangelogFragment
import cu.jesusd0897.wallkube.util.KNav

class ChangelogActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_changelog)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
        KNav.replaceFragment(
            supportFragmentManager, ChangelogFragment.newInstance(), R.id.container
        )
    }

}