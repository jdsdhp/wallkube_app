package cu.jesusd0897.wallkube.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cu.jesusd0897.wallkube.R
import cu.jesusd0897.wallkube.adapter.OnInteractionListener
import cu.jesusd0897.wallkube.adapter.SectionsAdapter
import cu.jesusd0897.wallkube.database.model.Section
import cu.jesusd0897.wallkube.util.navToSectionGallery
import cu.jesusd0897.wallkube.viewmodel.SectionViewModel

class SectionsFragment : Fragment(),
    OnInteractionListener<Section>,
    Observer<MutableList<Section>> {

    private lateinit var sections: MutableCollection<Section>
    private lateinit var adapter: SectionsAdapter

    companion object {
        fun newInstance(): SectionsFragment = SectionsFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sectionViewModel = SectionViewModel(activity!!.application)
        sectionViewModel.all.observe(this, this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sections, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.recycler)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = SectionsAdapter(
            R.layout.section_item,
            this
        )
        recyclerView.adapter = adapter
        return view
    }

    override fun onClick(position: Int, item: Section) {
        navToSectionGallery(context!!, item)
    }

    override fun onLongClick(position: Int, item: Section) {
        //Do nothing.
    }

    override fun onChanged(pictures: MutableList<Section>) {
        this.sections = pictures
        adapter.setItems(pictures)
    }
}