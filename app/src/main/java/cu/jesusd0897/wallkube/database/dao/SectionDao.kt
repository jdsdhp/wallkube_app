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

package cu.jesusd0897.wallkube.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import cu.jesusd0897.wallkube.database.SectionColumns.ID
import cu.jesusd0897.wallkube.database.Tables.SECTIONS
import cu.jesusd0897.wallkube.database.model.Section

@Dao
interface SectionDao : BasicDao<Section> {

    @Query("DELETE FROM $SECTIONS")
    fun deleteAll()

    @Query("SELECT * FROM $SECTIONS WHERE $ID LIKE :id")
    fun get(id: String): LiveData<Section>

    @get:Query("SELECT * FROM $SECTIONS")
    val all: LiveData<MutableList<Section>>

}