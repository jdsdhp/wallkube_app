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

package cu.jesusd0897.wallkube.ui

import android.graphics.Rect
import android.view.View
import androidx.annotation.IntRange
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

const val DEFAULT_MARGIN = 18
const val SMALL_MARGIN = 14
const val BIG_MARGIN = 22

class KItemDecoration(
    @param:IntRange(from = 0) private val margin: Int,
    @param:IntRange(from = 0) private val columns: Int
) : ItemDecoration() {

    /**
     * Set different margins for the items inside the recyclerView: no top margin for the first row
     * and no left margin for the first column.
     */
    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        val pos = parent.getChildLayoutPosition(view)
        outRect.right = margin //set right margin to all
        outRect.bottom = margin //set bottom margin to all
        if (pos < columns) outRect.top = margin //we only add top margin to the first row
        if (pos % columns == 0) outRect.left = margin //add left margin only to the first column
    }

}