package cu.jesusd0897.wallkube.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vansuita.materialabout.builder.AboutBuilder
import cu.jesusd0897.wallkube.R
import cu.jesusd0897.wallkube.activity.AboutActivity
import cu.jesusd0897.wallkube.util.KNav
import cu.jesusd0897.wallkube.util.KUtil

class AboutFragment : Fragment() {

    companion object {
        fun newInstance(): AboutFragment = AboutFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = AboutBuilder.with(context)
        .setWrapScrollView(true)
        .setLinksAnimated(true)
        .setShowAsCard(false)
        .setPhoto(R.drawable.jesusd0897)
        .setBackgroundColor(KUtil.getThemeColor(context!!, android.R.attr.colorBackground))
        .setName(R.string.app_dev)
        .setSubTitle(R.string.app_copyright)
        .setBrief(R.string.app_brief)
        .setAppIcon(R.drawable.ic_launcher_round)
        .setAppName(R.string.app_name)
        .setVersionNameAsAppSubTitle()
        /*.addAction(R.mipmap.github, R.string.github) {
            KUtil.openWebPage(
                context!!,
                "https://github.com/jdsdhp/wallkube_app"
            )
        }*/
        .addChangeLogAction { KNav.navToChangelog(context!!) }
        .addFeedbackAction(
            getString(R.string.app_dev_email),
            getString(R.string.app_name) + "/feedback"
        )
        .addAction(
            KUtil.getBitmapFromDrawable(context!!, R.drawable.ic_coffee), R.string.invite_to_coffee
        ) { (activity!! as AboutActivity).showInputDialog() }
        .addAction(
            KUtil.getBitmapFromDrawable(context!!, R.drawable.ic_star_half_black_24dp),
            R.string.rate_us_on_cubapk
        ) { KUtil.openWebPage(context!!, "https://cubapk.com/store/cu.jesusd0897.wallkube") }
        .addAction(
            KUtil.getBitmapFromDrawable(context!!, R.drawable.ic_star_half_black_24dp),
            R.string.rate_us_on_apkpure
        ) { KUtil.openWebPage(context!!, "https://apkpure.com/p/cu.jesusd0897.wallkube") }
        .build()

}