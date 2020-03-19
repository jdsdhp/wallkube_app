package cu.jesusd0897.wallkube.util

import android.content.Context
import android.content.Intent
import androidx.annotation.IdRes
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import cu.jesusd0897.wallkube.activity.SectionGalleryActivity
import cu.jesusd0897.wallkube.database.model.Section

fun replaceFragmentView(
    @NonNull fragmentManager: FragmentManager, @NonNull fragment: Fragment, @IdRes containerId: Int
) = fragmentManager.beginTransaction().replace(containerId, fragment).commit()

fun navToSectionGallery(@NonNull context: Context, section: Section) = context.startActivity(
    Intent(context, SectionGalleryActivity::class.java).putExtra(ARG_SECTION_EXTRA, section)
)