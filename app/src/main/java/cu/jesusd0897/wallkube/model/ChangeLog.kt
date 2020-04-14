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
data class ChangeLog(
    @StringRes val version: Int,
    @StringRes val date: Int,
    @StringRes val changes: Int
) : Model {
    override fun match(query: String?): Boolean = true
}

val CHANGE_LOGS: List<ChangeLog> = arrayListOf(
    ChangeLog(
        R.string.change_log_050_version,
        R.string.change_log_050_date,
        R.string.change_log_050_changes
    )
)