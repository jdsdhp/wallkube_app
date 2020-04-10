package cu.jesusd0897.wallkube.ui

import android.content.Context
import android.graphics.PointF
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class KSnappingLayoutManager(spanCount: Int, orientation: Int) :
    StaggeredGridLayoutManager(spanCount, orientation) {

    override fun smoothScrollToPosition(
        recyclerView: RecyclerView, state: RecyclerView.State, position: Int
    ) {
        val smoothScroller = TopSnappedSmoothScroller(recyclerView.context)
        smoothScroller.targetPosition = position
        startSmoothScroll(smoothScroller)
    }

    private inner class TopSnappedSmoothScroller(context: Context?) :
        LinearSmoothScroller(context) {

        override fun computeScrollVectorForPosition(targetPosition: Int): PointF? =
            this@KSnappingLayoutManager.computeScrollVectorForPosition(targetPosition)

        override fun getVerticalSnapPreference(): Int = SNAP_TO_START

    }
}