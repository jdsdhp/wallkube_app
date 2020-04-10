package cu.jesusd0897.wallkube.fragment

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import cu.jesusd0897.wallkube.R
import cu.jesusd0897.wallkube.adapter.OnInteractionListener
import cu.jesusd0897.wallkube.adapter.OnScrollListener
import cu.jesusd0897.wallkube.adapter.PicturesAdapter
import cu.jesusd0897.wallkube.database.model.Picture
import cu.jesusd0897.wallkube.ui.KItemDecoration
import cu.jesusd0897.wallkube.ui.KSnappingLayoutManager
import cu.jesusd0897.wallkube.ui.SMALL_MARGIN
import cu.jesusd0897.wallkube.util.KAlert
import cu.jesusd0897.wallkube.util.KNav
import cu.jesusd0897.wallkube.viewmodel.PictureViewModel

class PicturesFragment :
    RecyclerFragment<Picture>(R.string.no_items_found, R.drawable.ic_find_in_page_black_24dp),
    OnInteractionListener<Picture> {

    private var sectionId: Int = 0

    companion object {
        fun newInstance(sectionId: Int): PicturesFragment {
            val fragment = PicturesFragment()
            val args = Bundle()
            args.putInt(KNav.EXTRA_SECTION_NUMBER_TAG, sectionId)
            fragment.arguments = args
            return fragment
        }
    }

    override fun setArguments(args: Bundle?) {
        super.setArguments(args)
        sectionId = args!!.getInt(KNav.EXTRA_SECTION_NUMBER_TAG)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val pictureViewModel = PictureViewModel(activity!!.application)
        pictureViewModel.findBySection(sectionId.toString()).observe(this, this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        val columns =
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) 2 else 3
        recyclerView.layoutManager =
            KSnappingLayoutManager(columns, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.addItemDecoration(KItemDecoration(SMALL_MARGIN, columns))
        adapter = PicturesAdapter(this, R.layout.item_picture)
        val upFab = view.findViewById<FloatingActionButton>(R.id.up_fab)
        upFab.setOnClickListener { recyclerView.smoothScrollToPosition(0) }
        adapter.onScrollListener = object : OnScrollListener {
            override fun onTopReached(position: Int) = upFab.hide()
            override fun onCenterReached(position: Int) {
                if (adapter.getItems().size > 10) upFab.show()
            }

            override fun onBottomReached(position: Int) {
                if (adapter.getItems().size > 10) upFab.show()
            }
        }
        recyclerView.adapter = adapter
        return view
    }

    override fun onClick(position: Int, item: Picture) =
        KNav.navToPictureDetail(context!!, items, position)

    override fun onLongClick(position: Int, item: Picture) =
        KAlert.showPictureSheetDialog(activity!!, item)


}