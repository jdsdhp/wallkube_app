package cu.jesusd0897.wallkube.transformes

import android.view.View
import androidx.viewpager.widget.ViewPager
import kotlin.math.abs

private const val MIN_SCALE = 0.75f

class DepthPageTransformer : ViewPager.PageTransformer {
    override fun transformPage(view: View, position: Float) {
        val pageWidth = view.width
        when {
            position < -1 -> view.alpha = 0f
            position <= 0 -> {
                view.alpha = 1f
                view.translationX = 0f
                view.scaleX = 1f
                view.scaleY = 1f
            }
            position <= 1 -> {
                view.alpha = 1 - position
                view.translationX = pageWidth * -position
                val scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - abs(position))
                view.scaleX = scaleFactor
                view.scaleY = scaleFactor
            }
            else -> view.alpha = 0f
        }
    }

}