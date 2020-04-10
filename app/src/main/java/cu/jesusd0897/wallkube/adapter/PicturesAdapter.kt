package cu.jesusd0897.wallkube.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatImageView
import com.squareup.picasso.Picasso
import cu.jesusd0897.wallkube.R
import cu.jesusd0897.wallkube.adapter.holder.GenericViewHolder
import cu.jesusd0897.wallkube.database.model.Picture

class PicturesAdapter(val interactionListener: OnInteractionListener<Picture>, layoutId: Int) :
    ModelAdapter<Picture>(interactionListener, layoutId) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder<Picture> {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return PictureViewHolder(view)
    }

    inner class PictureViewHolder internal constructor(view: View) :
        GenericViewHolder<Picture>(view, interactionListener) {

        private val imageView: AppCompatImageView = itemView.findViewById(R.id.picture_view)
        private val favIndicator: AppCompatImageView = itemView.findViewById(R.id.fav_indicator)
        private val actionBtn: AppCompatImageButton = itemView.findViewById(R.id.more_action_btn)

        init {
            itemView.setOnLongClickListener {
                interactionListener.onLongClick(adapterPosition, getModel(adapterPosition))
                return@setOnLongClickListener true
            }
            actionBtn.setOnClickListener {
                interactionListener.onLongClick(adapterPosition, getModel(adapterPosition))
            }
        }

        override fun bind(model: Picture) {
            Picasso.get()
                .load(model.fileThumbURL)
                .into(imageView)
            favIndicator.visibility = if (model.isFavorite) View.VISIBLE else View.INVISIBLE
        }

        override fun getModel(position: Int): Picture = getItem(position)

    }

}