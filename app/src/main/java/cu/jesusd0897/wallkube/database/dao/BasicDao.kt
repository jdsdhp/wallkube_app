package cu.jesusd0897.wallkube.database.dao

import androidx.room.*

@Dao
interface BasicDao<M> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(model: M)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg models: M)

    @Update
    fun update(model: M)

    @Update
    fun update(vararg models: M)

    @Delete
    fun delete(model: M)

    @Delete
    fun delete(vararg models: M)
}