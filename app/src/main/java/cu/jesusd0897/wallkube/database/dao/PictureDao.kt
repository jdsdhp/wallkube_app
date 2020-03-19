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