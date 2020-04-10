package cu.jesusd0897.wallkube.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cu.jesusd0897.wallkube.R
import cu.jesusd0897.wallkube.adapter.ChangeLogAdapter
import cu.jesusd0897.wallkube.adapter.OnInteractionListener
import cu.jesusd0897.wallkube.model.CHANGE_LOGS
import cu.jesusd0897.wallkube.model.ChangeLog

class ChangelogFragment :
    RecyclerFragment<ChangeLog>(R.string.no_items_found, R.drawable.ic_find_in_page_black_24dp),
    OnInteractionListener<ChangeLog> {

    companion object {
        fun newInstance(): ChangelogFragment = ChangelogFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        adapter = ChangeLogAdapter(this, R.layout.item_changelog)
        recyclerView.adapter = adapter
        adapter.setItems(CHANGE_LOGS.toMutableList())
        return view
    }

    override fun onClick(position: Int, item: ChangeLog) {}

    override fun onLongClick(position: Int, item: ChangeLog) {}

}