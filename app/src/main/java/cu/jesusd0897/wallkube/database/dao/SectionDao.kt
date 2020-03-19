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