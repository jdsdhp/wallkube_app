/*
 * Copyright (c) 2020 jesusd0897.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cu.jesusd0897.wallkube.fragment

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import cu.jesusd0897.wallkube.R
import cu.jesusd0897.wallkube.adapter.OnInteractionListener
import cu.jesusd0897.wallkube.adapter.SectionsAdapter
import cu.jesusd0897.wallkube.database.model.Section
import cu.jesusd0897.wallkube.ui.KItemDecoration
import cu.jesusd0897.wallkube.ui.SMALL_MARGIN
import cu.jesusd0897.wallkube.util.KNav
import cu.jesusd0897.wallkube.viewmodel.SectionViewModel

class SectionsFragment :
    RecyclerFragment<Section>(R.string.no_items_found, R.drawable.ic_find_in_page_black_24dp),
    OnInteractionListener<Section> {

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
    ): View {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        adapter = SectionsAdapter(this, R.layout.item_section)
        val columns =
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) 1 else 2
        recyclerView.layoutManager = GridLayoutManager(context!!, columns)
        recyclerView.addItemDecoration(KItemDecoration(SMALL_MARGIN, columns))
        recyclerView.adapter = adapter
        return view
    }

    override fun onClick(position: Int, item: Section) = KNav.navToSection(context!!, item)

    override fun onLongClick(position: Int, item: Section) {}

}