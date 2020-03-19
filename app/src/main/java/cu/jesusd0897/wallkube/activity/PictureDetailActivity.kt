package cu.jesusd0897.wallkube.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.google.android.material.floatingactionbutton.FloatingActionButton
import cu.jesusd0897.wallkube.R
import cu.jesusd0897.wallkube.adapter.HackyViewPager
import cu.jesusd0897.wallkube.database.model.Picture
import cu.jesusd0897.wallkube.database.repo.PictureRepo
import cu.jesusd0897.wallkube.fragment.PictureFragment
import cu.jesusd0897.wallkube.transformes.ZoomOutPageTransformer
import cu.jesusd0897.wallkube.util.IMAGE_MODELS_FLAG
import cu.jesusd0897.wallkube.util.POSITION_FLAG
import cu.jesusd0897.wallkube.util.setAsWallpaper
import cu.jesusd0897.wallkube.util.sharePicture

class PictureDetailActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var toolbarContainer: View
    private lateinit var fabFavorites: FloatingActionButton
    private var position = 0
    private var pictures = ArrayList<Picture>()
    private var isCurrentImmersive = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture_detail)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
        toolbarContainer = findViewById(R.id.toolbar_container)
        fabFavorites = findViewById(R.id.fab_favorite)
        pictures = intent.getParcelableArrayListExtra(IMAGE_MODELS_FLAG)
        position = intent.getIntExtra(POSITION_FLAG, 0)
        val sectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)
        val viewPager = findViewById<HackyViewPager>(R.id.picture_page_container)
        viewPager.setPageTransformer(true, ZoomOutPageTransformer())
        viewPager.adapter = sectionsPagerAdapter
        viewPager.currentItem = position
        title = (position + 1).toString() + "/" + pictures.size
        viewPager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int, positionOffset: Float, positionOffsetPixels: Int
            ) {
                /*Do Nothing.*/
            }

            override fun onPageSelected(position: Int) {
                this@PictureDetailActivity.position = position
                title = (position + 1).toString() + "/" + pictures.size
                fabFavorites.setImageResource(
                    if (pictures[position].isFavorite) R.drawable.ic_star_black_24dp
                    else R.drawable.ic_star_border_black_24dp
                )
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
        fabFavorites.setImageResource(
            if (pictures[position].isFavorite) R.drawable.ic_star_black_24dp
            else R.drawable.ic_star_border_black_24dp
        )
        fabFavorites.setOnClickListener {
            val picture = pictures[position]
            picture.isFavorite = !picture.isFavorite
            PictureRepo(this).update(picture)
            fabFavorites.setImageResource(
                if (picture.isFavorite) R.drawable.ic_star_black_24dp
                else R.drawable.ic_star_border_black_24dp
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_image_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_share -> sharePicture(applicationContext, pictures[position].fileName)
            R.id.action_wallpaper -> setAsWallpaper(applicationContext, pictures[position].fileName)
            R.id.action_info -> {
                //TODO: Code this.
            }
        }
        return super.onOptionsItemSelected(item)

    }


    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) handleActivityDecorVisibility()
    }

    fun handleActivityDecorVisibility() {
        if (isCurrentImmersive) showSystemUI()
        else hideSystemUI()
    }

    private fun hideSystemUI() {
        val decorView = window.decorView
        decorView.systemUiVisibility = (SYSTEM_UI_FLAG_LAYOUT_STABLE
                or SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or SYSTEM_UI_FLAG_FULLSCREEN
                or SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        toolbarContainer.visibility = GONE
        fabFavorites.hide()
        isCurrentImmersive = true
    }

    private fun showSystemUI() {
        val decorView = window.decorView
        decorView.systemUiVisibility = (SYSTEM_UI_FLAG_LAYOUT_STABLE
                or SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)
        toolbarContainer.visibility = VISIBLE
        fabFavorites.show()
        isCurrentImmersive = false
    }

    /**
     * A [FragmentPagerAdapter] that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    private inner class SectionsPagerAdapter internal constructor(fm: FragmentManager) :

        FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        override fun getItem(position: Int): Fragment = PictureFragment.newInstance(
            position, pictures[position]
        )

        override fun getCount(): Int = pictures.size

        override fun getPageTitle(position: Int): CharSequence? = pictures[position].fileName
    }
}