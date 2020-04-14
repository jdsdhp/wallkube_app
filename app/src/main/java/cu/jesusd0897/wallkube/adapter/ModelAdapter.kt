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

package cu.jesusd0897.wallkube.adapter

import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import cu.jesusd0897.wallkube.adapter.holder.GenericViewHolder
import cu.jesusd0897.wallkube.model.Model
import java.util.*

abstract class ModelAdapter<M : Model?> internal constructor(
    val clickListener: OnInteractionListener<M>, @field:LayoutRes @param:LayoutRes var layoutId: Int
) : RecyclerView.Adapter<GenericViewHolder<M>>() {

    private var models: MutableList<M> = ArrayList()

    var onScrollListener: OnScrollListener? = null

    override fun onBindViewHolder(holder: GenericViewHolder<M>, position: Int) {
        when (position) {
            0 -> onScrollListener?.onTopReached(position)
            models.size - 1 -> onScrollListener?.onBottomReached(position)
            (models.size / 2) -> onScrollListener?.onCenterReached(position)
        }
        holder.bind(models[position])
    }

    override fun getItemCount(): Int = models.size

    fun getItem(position: Int): M = models[position]

    fun getItems(): MutableList<M> = models

    fun setItems(models: MutableList<M>) {
        this.models = models
        notifyDataSetChanged()
    }

}