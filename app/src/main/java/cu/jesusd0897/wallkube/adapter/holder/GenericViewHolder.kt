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