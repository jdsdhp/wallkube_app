package cu.jesusd0897.wallkube.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.squareup.picasso.Picasso
import cu.jesusd0897.wallkube.R
import cu.jesusd0897.wallkube.adapter.holder.GenericViewHolder
import cu.jesusd0897.wallkube.database.model.Section
import cu.jesusd0897.wallkube.util.KUtil

class SectionsAdapter(val interactionListener: OnInteractionListener<Section>, layoutId: Int) :
    ModelAdapter<Section>(interactionListener, layoutId) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder<Section> {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return SectionViewHolder(view)
    }

    inner class SectionViewHolder internal constructor(view: View) :
        GenericViewHolder<Section>(view, interactionListener) {

        private val titleView: AppCompatTextView = itemView.findViewById(R.id.title_view)
        private val imageView: AppCompatImageView = itemView.findViewById(R.id.picture_view)

        override fun bind(model: Section) {
            titleView.text = itemView.context.getString(KUtil.getSectionTitle(model.id))
            Picasso.get()
                .load(model.imagePath)
                .into(imageView)
        }

        override fun getModel(position: Int): Section = getItem(position)

    }

}