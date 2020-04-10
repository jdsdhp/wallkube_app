package cu.jesusd0897.wallkube.util

import android.content.Context
import android.content.Intent
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import cu.jesusd0897.wallkube.BuildConfig
import cu.jesusd0897.wallkube.activity.*
import cu.jesusd0897.wallkube.database.model.Picture
import cu.jesusd0897.wallkube.database.model.Section

object KNav {

    const val EXTRA_ID_TAG = "${BuildConfig.APPLICATION_ID}.extra_id"
    const val EXTRA_ITEM_TAG = "${BuildConfig.APPLICATION_ID}.extra_item"
    const val EXTRA_POSITION_TAG = "${BuildConfig.APPLICATION_ID}.position_flag"
    const val EXTRA_SECTION_NUMBER_TAG = "${BuildConfig.APPLICATION_ID}.section_number"

    fun replaceFragment(
        fragmentManager: FragmentManager, fragment: Fragment, @IdRes containerViewId: Int
    ) = fragmentManager.beginTransaction().replace(containerViewId, fragment).commit()

    fun navToLibraries(context: Context) =
        context.startActivity(Intent(context, LibrariesActivity::class.java))

    fun navToChangelog(context: Context) =
        context.startActivity(Intent(context, ChangelogActivity::class.java))

    fun navToSettings(context: Context) =
        context.startActivity(Intent(context, SettingsActivity::class.java))

    fun navToAbout(context: Context) =
        context.startActivity(Intent(context, AboutActivity::class.java))

    fun navToPictureDetail(context: Context, items: List<Picture>, position: Int) =
        context.startActivity(
            Intent(context, PictureDetailActivity::class.java)
                .putParcelableArrayListExtra(EXTRA_ITEM_TAG, ArrayList(items))
                .putExtra(EXTRA_POSITION_TAG, position)
        )

    fun navToSection(context: Context, section: Section) = context.startActivity(
        Intent(context, SectionActivity::class.java).putExtra(EXTRA_ITEM_TAG, section)
    )

    fun navToMain(context: Context) =
        context.startActivity(Intent(context, MainActivity::class.java))
}
