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

package cu.jesusd0897.wallkube.database.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import cu.jesusd0897.wallkube.database.SectionColumns
import cu.jesusd0897.wallkube.database.Tables
import cu.jesusd0897.wallkube.model.Model

@Entity(tableName = Tables.SECTIONS)
data class Section(
    @field:ColumnInfo(name = SectionColumns.TITLE) var title: String,
    @field:ColumnInfo(name = SectionColumns.SUBTITLE) var subtitle: String?,
    @field:ColumnInfo(name = SectionColumns.IMAGE_PATH) var imagePath: String
) : Model {

    @field:PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = SectionColumns.ID)
    var id: Int = 0

    private constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString(),
        parcel.readString()!!
    ) {
        id = parcel.readInt()
    }

    override fun match(query: String?): Boolean = true

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(subtitle)
        parcel.writeString(imagePath)
        parcel.writeInt(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Section> {
        override fun createFromParcel(parcel: Parcel): Section {
            return Section(parcel)
        }

        override fun newArray(size: Int): Array<Section?> {
            return arrayOfNulls(size)
        }
    }

}