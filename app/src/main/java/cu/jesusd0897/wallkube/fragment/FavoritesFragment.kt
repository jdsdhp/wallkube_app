package cu.jesusd0897.wallkube.fragment

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import cu.jesusd0897.wallkube.R
import cu.jesusd0897.wallkube.activity.PictureDetailActivity
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

class FavoritesFragment :
    RecyclerFragment<Picture>(R.string.no_items_found, R.drawable.ic_find_in_page_black_24dp),
    OnInteractionListener<Picture> {

    companion object {
        fun newInstance(): FavoritesFragment = FavoritesFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val pictureViewModel = PictureViewModel(activity!!.application)
        pictureViewModel.favorites.observe(this, this)
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

    override fun onClick(position: Int, item: Picture) {
        startActivity(
            Intent(context, PictureDetailActivity::class.java)
                .putParcelableArrayListExtra(KNav.EXTRA_ITEM_TAG, ArrayList(items))
                .putExtra(KNav.EXTRA_POSITION_TAG, position)
        )
    }

    override fun onLongClick(position: Int, item: Picture) =
        KAlert.showPictureSheetDialog(activity!!, item)

}