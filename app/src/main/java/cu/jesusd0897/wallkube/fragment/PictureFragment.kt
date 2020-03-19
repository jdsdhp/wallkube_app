package cu.jesusd0897.wallkube.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.chrisbanes.photoview.PhotoView
import com.squareup.picasso.Picasso
import cu.jesusd0897.wallkube.R
import cu.jesusd0897.wallkube.activity.PictureDetailActivity
import cu.jesusd0897.wallkube.database.model.Picture
import cu.jesusd0897.wallkube.util.ARG_IMG_PARCELABLE
import cu.jesusd0897.wallkube.util.ARG_SECTION_POSITION

class PictureFragment : Fragment() {

    private lateinit var picture: Picture
    private var position = 0

    companion object {
        fun newInstance(sectionNumber: Int, picture: Picture): PictureFragment {
            val fragment = PictureFragment()
            val args = Bundle()
            args.putInt(ARG_SECTION_POSITION, sectionNumber)
            args.putParcelable(ARG_IMG_PARCELABLE, picture)
            fragment.arguments = args
            return fragment
        }
    }

    override fun setArguments(args: Bundle?) {
        super.setArguments(args)
        position = args!!.getInt(ARG_SECTION_POSITION)
        picture = args.getParcelable(ARG_IMG_PARCELABLE)!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_detail, container, false)
        val photoView: PhotoView = view.findViewById(R.id.photo_view)
        photoView.setOnClickListener { (activity as PictureDetailActivity).handleActivityDecorVisibility() }
        Picasso.get().load(picture.fileURL).into(photoView)
        return view
    }

}