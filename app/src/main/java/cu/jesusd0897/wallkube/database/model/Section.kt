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