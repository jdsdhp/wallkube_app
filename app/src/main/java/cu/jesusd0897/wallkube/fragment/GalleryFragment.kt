package cu.jesusd0897.wallkube.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import cu.jesusd0897.wallkube.R
import cu.jesusd0897.wallkube.activity.PictureDetailActivity
import cu.jesusd0897.wallkube.adapter.GalleryAdapter
import cu.jesusd0897.wallkube.adapter.OnInteractionListener
import cu.jesusd0897.wallkube.database.model.Picture
import cu.jesusd0897.wallkube.ui.SnappingLayoutManager
import cu.jesusd0897.wallkube.util.ARG_SECTION_EXTRA
import cu.jesusd0897.wallkube.util.IMAGE_MODELS_FLAG
import cu.jesusd0897.wallkube.util.POSITION_FLAG
import cu.jesusd0897.wallkube.viewmodel.PictureViewModel
import java.util.*

class GalleryFragment : Fragment(),
    OnInteractionListener<Picture>, Observer<MutableList<Picture>> {

    private lateinit var pictures: MutableCollection<Picture>
    private lateinit var adapter: GalleryAdapter
    private var sectionId: Int = 0

    companion object {
        fun newInstance(sectionId: Int): GalleryFragment {
            val fragment = GalleryFragment()
            val args = Bundle()
            args.putInt(ARG_SECTION_EXTRA, sectionId)
            fragment.arguments = args
            return fragment
        }
    }

    override fun setArguments(args: Bundle?) {
        super.setArguments(args)
        sectionId = args!!.getInt(ARG_SECTION_EXTRA)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val pictureViewModel = PictureViewModel(activity!!.application)
        pictureViewModel.findBySection(sectionId.toString()).observe(this, this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_gallery, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler)
        recyclerView.layoutManager = SnappingLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        adapter = GalleryAdapter(R.layout.gallery_picture_item, this)
        recyclerView.adapter = adapter
        val upFab = view.findViewById<FloatingActionButton>(R.id.up_fab)
        upFab.setOnClickListener { recyclerView.smoothScrollToPosition(0) }
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) upFab.hide() // Scrolling up
                else upFab.show() // Scrolling down
            }
        })
        return view
    }

    override fun onClick(position: Int, item: Picture) {
        startActivity(
            Intent(context, PictureDetailActivity::class.java)
                .putParcelableArrayListExtra(IMAGE_MODELS_FLAG, ArrayList(pictures))
                .putExtra(POSITION_FLAG, position)
        )
    }

    override fun onLongClick(position: Int, item: Picture) {
        //Do nothing.
    }

    override fun onChanged(pictures: MutableList<Picture>) {
        this.pictures = pictures
        adapter.setItems(pictures)
    }
}