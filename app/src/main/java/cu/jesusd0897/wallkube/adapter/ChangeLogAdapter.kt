package cu.jesusd0897.wallkube.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import cu.jesusd0897.wallkube.R
import cu.jesusd0897.wallkube.adapter.holder.GenericViewHolder
import cu.jesusd0897.wallkube.model.ChangeLog

class ChangeLogAdapter(val interactionListener: OnInteractionListener<ChangeLog>, layoutId: Int) :
    ModelAdapter<ChangeLog>(interactionListener, layoutId) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GenericViewHolder<ChangeLog> {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return ChangeLogViewHolder(view)
    }

    inner class ChangeLogViewHolder internal constructor(view: View) :
        GenericViewHolder<ChangeLog>(view, interactionListener) {

        private val title: AppCompatTextView = view.findViewById(R.id.title_item)
        private val date: AppCompatTextView = view.findViewById(R.id.date_item)
        private val subtitle: AppCompatTextView = view.findViewById(R.id.subtitle_item)

        @SuppressLint("SetTextI18n")
        override fun bind(model: ChangeLog) {
            val version = itemView.context.getText(R.string.change_log_version)
            val versionNumber = itemView.context.getText(model.version)
            title.text = "$version $versionNumber"
            date.text = itemView.context.getText(model.date)
            subtitle.text = itemView.context.getText(model.changes)
        }

        init {
            itemView.setOnClickListener(null)
            itemView.setOnLongClickListener(null)
        }

        override fun getModel(position: Int): ChangeLog = getItem(position)

    }

}