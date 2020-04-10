package cu.jesusd0897.wallkube.database.repo

import android.content.Context
import androidx.lifecycle.LiveData
import cu.jesusd0897.wallkube.database.RoomDatabaseHelper
import cu.jesusd0897.wallkube.database.dao.SectionDao
import cu.jesusd0897.wallkube.database.model.Section

class SectionRepo(context: Context) : SectionDao {

    private val dao: SectionDao = RoomDatabaseHelper.getInstance(context)!!.sectionDAO()

    override fun get(id: String): LiveData<Section> = dao.get(id)
    override val all: LiveData<MutableList<Section>> get() = dao.all

    override fun insert(model: Section) {
        InsertTask(dao).execute(model)
    }

    override fun insert(vararg models: Section) {
        InsertManyTask(dao).execute(*models)
    }

    override fun update(model: Section) {
        UpdateTask(dao).execute(model)
    }

    override fun update(vararg models: Section) {
        UpdateManyTask(dao).execute(*models)
    }

    override fun delete(model: Section) {
        DeleteTask(dao).execute(model)
    }

    override fun delete(vararg models: Section) {
        DeleteManyTask(dao).execute(*models)
    }

    override fun deleteAll() {
        DeleteAllTask(dao).execute()
    }

    private class InsertTask internal constructor(DAO: SectionDao) :
        HandlerTask<SectionDao, Section>(DAO) {
        override fun doInBackground(vararg params: Section): Void? {
            super.DAO.insert(params[0])
            return null
        }
    }

    private class InsertManyTask internal constructor(DAO: SectionDao) :
        HandlerTask<SectionDao, Section>(DAO) {
        override fun doInBackground(vararg models: Section): Void? {
            super.DAO.insert(*models)
            return null
        }
    }

    private class UpdateTask(DAO: SectionDao) : HandlerTask<SectionDao, Section>(DAO) {
        override fun doInBackground(vararg models: Section): Void? {
            super.DAO.update(models[0])
            return null
        }
    }

    private class UpdateManyTask(DAO: SectionDao) : HandlerTask<SectionDao, Section>(DAO) {
        override fun doInBackground(vararg models: Section): Void? {
            super.DAO.update(*models)
            return null
        }
    }

    private class DeleteTask(DAO: SectionDao) : HandlerTask<SectionDao, Section>(DAO) {
        override fun doInBackground(vararg models: Section): Void? {
            super.DAO.delete(models[0])
            return null
        }
    }

    private class DeleteManyTask(DAO: SectionDao) : HandlerTask<SectionDao, Section>(DAO) {
        override fun doInBackground(vararg models: Section): Void? {
            super.DAO.delete(*models)
            return null
        }
    }

    private class DeleteAllTask(DAO: SectionDao) : HandlerTask<SectionDao, Section>(DAO) {
        override fun doInBackground(vararg models: Section): Void? {
            super.DAO.deleteAll()
            return null
        }
    }

}