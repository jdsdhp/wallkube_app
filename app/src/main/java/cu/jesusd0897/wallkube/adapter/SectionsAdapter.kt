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

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.squareup.picasso.Picasso
import cu.jesusd0897.wallkube.R
import cu.jesusd0897.wallkube.adapter.holder.GenericViewHolder
import cu.jesusd0897.wallkube.database.model.Section
import cu.jesusd0897.wallkube.util.KUtil

class SectionsAdapter(val interactionListener: OnInteractionListener<Section>, layoutId: Int) :
    ModelAdapter<Section>(interactionListener, layoutId) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder<Section> {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return SectionViewHolder(view)
    }

    inner class SectionViewHolder internal constructor(view: View) :
        GenericViewHolder<Section>(view, interactionListener) {

        private val titleView: AppCompatTextView = itemView.findViewById(R.id.title_view)
        private val imageView: AppCompatImageView = itemView.findViewById(R.id.picture_view)

        override fun bind(model: Section) {
            titleView.text = itemView.context.getString(KUtil.getSectionTitle(model.id))
            Picasso.get()
                .load(model.imagePath)
                .into(imageView)
        }

        override fun getModel(position: Int): Section = getItem(position)

    }

}