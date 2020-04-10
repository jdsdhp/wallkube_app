package cu.jesusd0897.wallkube.activity

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import cu.jesusd0897.wallkube.R
import cu.jesusd0897.wallkube.database.model.Section
import cu.jesusd0897.wallkube.fragment.PicturesFragment
import cu.jesusd0897.wallkube.util.KNav
import cu.jesusd0897.wallkube.util.KUtil

class SectionActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_section_gallery)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
        val section = intent.getParcelableExtra(KNav.EXTRA_ITEM_TAG) as Section
        KNav.replaceFragment(
            supportFragmentManager, PicturesFragment.newInstance(section.id), R.id.container
        )
        toolbar.title = getString(KUtil.getSectionTitle(section.id))
    }

}
