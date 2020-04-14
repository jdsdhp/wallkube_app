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

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import cu.jesusd0897.wallkube.R
import cu.jesusd0897.wallkube.adapter.ModelAdapter
import cu.jesusd0897.wallkube.model.Model
import cu.jesusd0897.wallkube.ui.KItemDecoration
import cu.jesusd0897.wallkube.ui.SMALL_MARGIN
import java.util.*

abstract class RecyclerFragment<M : Model?> internal constructor(
    @field:StringRes @param:StringRes private val emptyTitle: Int,
    @field:DrawableRes @param:DrawableRes private val emptyIcon: Int
) : Fragment(), Observer<MutableList<M>> {

    protected lateinit var adapter: ModelAdapter<M>
    protected lateinit var recyclerView: RecyclerView
    protected lateinit var emptyPlaceholder: View
    protected lateinit var emptyPlaceholderTile: AppCompatTextView
    protected lateinit var emptyPlaceholderSubtitle: AppCompatTextView
    protected lateinit var emptyPlaceholderImage: AppCompatImageView
    protected lateinit var refreshLayout: SwipeRefreshLayout
    protected var items: MutableList<M> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_recycler, container, false)
        recyclerView = view.findViewById(R.id.recycler)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)
        emptyPlaceholder = view.findViewById(R.id.empty_placeholder)
        emptyPlaceholderTile = view.findViewById(R.id.empty_placeholder_tile)
        emptyPlaceholderSubtitle = view.findViewById(R.id.empty_placeholder_subtitle)
        emptyPlaceholderImage = view.findViewById(R.id.empty_placeholder_image)
        recyclerView.addItemDecoration(KItemDecoration(SMALL_MARGIN, 1))
        refreshLayout = view.findViewById(R.id.refresh_layout)
        refreshLayout.isEnabled = false

        return view
    }

    override fun onChanged(items: MutableList<M>) {
        this.items = items
        adapter.setItems(this.items)
        if (this.items.isEmpty()) setEmptyPlaceholder()
        else emptyPlaceholder.visibility = View.GONE
    }

    protected fun setEmptyPlaceholder() {
        emptyPlaceholderTile.setText(R.string.default_empty_title)
        emptyPlaceholderSubtitle.setText(emptyTitle)
        emptyPlaceholderImage.setImageResource(emptyIcon)
        emptyPlaceholder.visibility = View.VISIBLE
    }

}