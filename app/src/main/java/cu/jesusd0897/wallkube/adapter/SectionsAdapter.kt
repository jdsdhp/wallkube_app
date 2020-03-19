package cu.jesusd0897.wallkube.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.NonNull
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import cu.jesusd0897.wallkube.R
import cu.jesusd0897.wallkube.database.model.Section

class SectionsAdapter(
    @field:LayoutRes @param:LayoutRes private val layout: Int,
    private val interactionListener: OnInteractionListener<Section>
) : RecyclerView.Adapter<SectionsAdapter.MyViewHolder>() {

    private var items: MutableList<Section> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
        MyViewHolder(LayoutInflater.from(parent.context).inflate(layout, parent, false))

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) =
        holder.bind(items[position])

    override fun getItemCount(): Int = items.size

    fun getItem(position: Int): Section = items[position]

    fun getItems(): MutableList<Section> = items

    fun setItems(items: MutableList<Section>) {
        this.items = items
        notifyDataSetChanged()
    }

    inner class MyViewHolder internal constructor(@NonNull view: View) :
        RecyclerView.ViewHolder(view), View.OnClickListener, OnLongClickListener {

        private val titleView: AppCompatTextView = itemView.findViewById(R.id.title_view)
        private val subtitleView: AppCompatTextView = itemView.findViewById(R.id.subtitle_view)
        private val imageView: AppCompatImageView = itemView.findViewById(R.id.picture_view)

        init {
            view.setOnClickListener(this)
            view.setOnLongClickListener(this)
        }

        fun bind(section: Section) {
            titleView.text = section.title
            subtitleView.text = section.subtitle
            Picasso.get()
                .load(section.imagePath)
                .into(imageView)
        }

        override fun onClick(v: View?) {
            interactionListener.onClick(adapterPosition, getItem(adapterPosition))
        }

        override fun onLongClick(v: View?): Boolean {
            interactionListener.onLongClick(adapterPosition, getItem(adapterPosition))
            return true
        }

    }

}