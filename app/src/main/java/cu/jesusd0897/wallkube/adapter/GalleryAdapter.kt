package cu.jesusd0897.wallkube.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.NonNull
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import cu.jesusd0897.wallkube.R
import cu.jesusd0897.wallkube.adapter.GalleryAdapter.MyViewHolder
import cu.jesusd0897.wallkube.database.model.Picture

class GalleryAdapter(
    @field:LayoutRes @param:LayoutRes private val layout: Int,
    private val interactionListener: OnInteractionListener<Picture>
) : RecyclerView.Adapter<MyViewHolder>() {

    private var items: MutableList<Picture> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
        MyViewHolder(LayoutInflater.from(parent.context).inflate(layout, parent, false))

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) =
        holder.bind(items[position])

    override fun getItemCount(): Int = items.size

    fun getItem(position: Int): Picture = items[position]

    fun getItems(): MutableList<Picture> = items

    fun setItems(items: MutableList<Picture>) {
        this.items = items
        notifyDataSetChanged()
    }

    inner class MyViewHolder internal constructor(@NonNull view: View) :
        RecyclerView.ViewHolder(view), View.OnClickListener, OnLongClickListener {

        private val imageView: AppCompatImageView = itemView.findViewById(R.id.picture_view)

        init {
            view.setOnClickListener(this)
            view.setOnLongClickListener(this)
        }

        fun bind(picture: Picture) =
            Picasso.get()
                .load(picture.fileThumbURL)
                .placeholder(R.color.color_my_dark_overlay_40)
                .into(imageView)

        override fun onClick(v: View?) {
            interactionListener.onClick(adapterPosition, getItem(adapterPosition))
        }

        override fun onLongClick(v: View?): Boolean {
            interactionListener.onLongClick(adapterPosition, getItem(adapterPosition))
            return true
        }

    }

}