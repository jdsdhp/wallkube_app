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
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import cu.jesusd0897.wallkube.database.PictureColumns
import cu.jesusd0897.wallkube.database.SectionColumns
import cu.jesusd0897.wallkube.database.Tables
import cu.jesusd0897.wallkube.model.Model

@Entity(
    tableName = Tables.PICTURES,
    foreignKeys = [ForeignKey(
        entity = Section::class,
        parentColumns = [SectionColumns.ID], childColumns = [PictureColumns.SECTION_ID]
    )]
)
data class Picture(
    @field:ColumnInfo(name = PictureColumns.SECTION_ID) var sectionId: Int,
    @field:ColumnInfo(name = PictureColumns.FILE_URL) var fileURL: String,
    @field:ColumnInfo(name = PictureColumns.FILE_THUMB_URL) var fileThumbURL: String,
    @field:ColumnInfo(name = PictureColumns.FILE_NAME) var fileName: String,
    @field:ColumnInfo(name = PictureColumns.INFO) var info: String?,
    @field:ColumnInfo(name = PictureColumns.PUBLISHED_BY) var publishedBy: String?,
    @field:ColumnInfo(name = PictureColumns.FAVORITE, defaultValue = "0") var isFavorite: Boolean
) : Model {

    @PrimaryKey(autoGenerate = true)
    @field:ColumnInfo(name = PictureColumns.ID)
    var id: Int = 0

    private constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte()
    ) {
        id = parcel.readInt()
    }

    override fun match(query: String?): Boolean = true

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(sectionId)
        parcel.writeString(fileURL)
        parcel.writeString(fileThumbURL)
        parcel.writeString(fileName)
        parcel.writeString(info)
        parcel.writeString(publishedBy)
        parcel.writeByte(if (isFavorite) 1 else 0)
        parcel.writeInt(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Picture> {
        override fun createFromParcel(parcel: Parcel): Picture {
            return Picture(parcel)
        }

        override fun newArray(size: Int): Array<Picture?> {
            return arrayOfNulls(size)
        }
    }
}