package cu.jesusd0897.wallkube.adapter.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import cu.jesusd0897.wallkube.adapter.OnInteractionListener

abstract class GenericViewHolder<M> internal constructor(
    itemView: View, private val interactionListener: OnInteractionListener<M>
) : RecyclerView.ViewHolder(itemView) {

    init {
        itemView.setOnClickListener {
            interactionListener.onClick(adapterPosition, getModel(adapterPosition))
        }
    }

    abstract fun bind(model: M)
    abstract fun getModel(position: Int): M
}