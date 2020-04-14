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
import cu.jesusd0897.wallkube.database.PictureColumns.FAVORITE
import cu.jesusd0897.wallkube.database.PictureColumns.ID
import cu.jesusd0897.wallkube.database.PictureColumns.SECTION_ID
import cu.jesusd0897.wallkube.database.Tables.PICTURES
import cu.jesusd0897.wallkube.database.model.Picture

@Dao
interface PictureDao : BasicDao<Picture> {

    @Query("DELETE FROM $PICTURES")
    fun deleteAll()

    @Query("SELECT * FROM $PICTURES WHERE $ID LIKE :id")
    fun get(id: String): LiveData<Picture>

    @get:Query("SELECT * FROM $PICTURES")
    val all: LiveData<MutableList<Picture>>

    @get:Query("SELECT * FROM $PICTURES WHERE $FAVORITE LIKE 1")
    val favorites: LiveData<MutableList<Picture>>

    @Query("SELECT * FROM $PICTURES WHERE $SECTION_ID LIKE :sectionId")
    fun findBySection(sectionId: String): LiveData<MutableList<Picture>>

}