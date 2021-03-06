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

package cu.jesusd0897.wallkube.model

import androidx.annotation.StringRes
import cu.jesusd0897.wallkube.R
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Library(
    @StringRes val name: Int,
    @StringRes val link: Int,
    @StringRes val license: Int
) : Model {
    override fun match(query: String?): Boolean = true
}

val LIBRARIES: List<Library> = arrayListOf(
    Library(
        R.string.library_kotlin,
        R.string.library_kotlin_link,
        R.string.library_kotlin_license
    ),
    Library(
        R.string.library_material,
        R.string.library_material_link,
        R.string.library_material_license
    ),
    Library(
        R.string.library_materialabout,
        R.string.library_materialabout_link,
        R.string.library_materialabout_license
    ), Library(
        R.string.library_okio,
        R.string.library_okio_link,
        R.string.library_okio_license
    ),
    Library(
        R.string.library_photoview,
        R.string.library_photoview_link,
        R.string.library_photoview_license
    ),
    Library(
        R.string.library_picasso,
        R.string.library_picasso_link,
        R.string.library_picasso_license
    )
)