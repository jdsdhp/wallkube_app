package cu.jesusd0897.wallkube.fragment

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cu.jesusd0897.wallkube.R
import cu.jesusd0897.wallkube.adapter.LibraryAdapter
import cu.jesusd0897.wallkube.adapter.OnInteractionListener
import cu.jesusd0897.wallkube.model.LIBRARIES
import cu.jesusd0897.wallkube.model.Library
import cu.jesusd0897.wallkube.util.KAlert
import cu.jesusd0897.wallkube.util.KUtil

class LibrariesFragment :
    RecyclerFragment<Library>(R.string.no_items_found, R.drawable.ic_find_in_page_black_24dp),
    OnInteractionListener<Library> {

    companion object {
        fun newInstance(): LibrariesFragment = LibrariesFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        adapter = LibraryAdapter(this, R.layout.item_library)
        recyclerView.adapter = adapter
        adapter.setItems(LIBRARIES.toMutableList())
        return view
    }

    override fun onClick(position: Int, item: Library) {
        KAlert.buildDialog(
            context!!, item.name, item.license, R.string.cancel, R.string.open,
            null, true, null,
            DialogInterface.OnClickListener { dialog, _ ->
                KUtil.openWebPage(context!!, getString(item.link))
                dialog.dismiss()
            }
        ).show()
    }

    override fun onLongClick(position: Int, item: Library) {}

}