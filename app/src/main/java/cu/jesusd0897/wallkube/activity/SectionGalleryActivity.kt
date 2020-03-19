package cu.jesusd0897.wallkube.activity

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import cu.jesusd0897.wallkube.R
import cu.jesusd0897.wallkube.database.model.Section
import cu.jesusd0897.wallkube.fragment.GalleryFragment
import cu.jesusd0897.wallkube.util.ARG_SECTION_EXTRA
import cu.jesusd0897.wallkube.util.replaceFragmentView

class SectionGalleryActivity : ThemedActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_section_gallery)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
        val section = intent.getParcelableExtra(ARG_SECTION_EXTRA) as Section
        replaceFragmentView(
            supportFragmentManager, GalleryFragment.newInstance(section.id), R.id.container
        )
        title = section.title
        toolbar.title = section.title
    }

}
