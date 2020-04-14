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

package cu.jesusd0897.wallkube.database.repo

import android.content.Context
import androidx.lifecycle.LiveData
import cu.jesusd0897.wallkube.database.RoomDatabaseHelper
import cu.jesusd0897.wallkube.database.dao.PictureDao
import cu.jesusd0897.wallkube.database.model.Picture

class PictureRepo(context: Context) : PictureDao {

    private val dao: PictureDao = RoomDatabaseHelper.getInstance(context)!!.pictureDAO()

    override fun get(id: String): LiveData<Picture> = dao.get(id)
    override val all: LiveData<MutableList<Picture>> get() = dao.all
    override val favorites: LiveData<MutableList<Picture>> get() = dao.favorites

    override fun findBySection(sectionId: String): LiveData<MutableList<Picture>> =
        dao.findBySection(sectionId)

    override fun insert(model: Picture) {
        InsertTask(dao).execute(model)
    }

    override fun insert(vararg models: Picture) {
        InsertManyTask(dao).execute(*models)
    }

    override fun update(model: Picture) {
        UpdateTask(dao).execute(model)
    }

    override fun update(vararg models: Picture) {
        UpdateManyTask(dao).execute(*models)
    }

    override fun delete(model: Picture) {
        DeleteTask(dao).execute(model)
    }

    override fun delete(vararg models: Picture) {
        DeleteManyTask(dao).execute(*models)
    }

    override fun deleteAll() {
        DeleteAllTask(dao).execute()
    }

    private class InsertTask internal constructor(DAO: PictureDao) :
        HandlerTask<PictureDao, Picture>(DAO) {
        override fun doInBackground(vararg params: Picture): Void? {
            super.DAO.insert(params[0])
            return null
        }
    }

    private class InsertManyTask internal constructor(DAO: PictureDao) :
        HandlerTask<PictureDao, Picture>(DAO) {
        override fun doInBackground(vararg models: Picture): Void? {
            super.DAO.insert(*models)
            return null
        }
    }

    private class UpdateTask(DAO: PictureDao) : HandlerTask<PictureDao, Picture>(DAO) {
        override fun doInBackground(vararg models: Picture): Void? {
            super.DAO.update(models[0])
            return null
        }
    }

    private class UpdateManyTask(DAO: PictureDao) : HandlerTask<PictureDao, Picture>(DAO) {
        override fun doInBackground(vararg models: Picture): Void? {
            super.DAO.update(*models)
            return null
        }
    }

    private class DeleteTask(DAO: PictureDao) : HandlerTask<PictureDao, Picture>(DAO) {
        override fun doInBackground(vararg models: Picture): Void? {
            super.DAO.delete(models[0])
            return null
        }
    }

    private class DeleteManyTask(DAO: PictureDao) : HandlerTask<PictureDao, Picture>(DAO) {
        override fun doInBackground(vararg models: Picture): Void? {
            super.DAO.delete(*models)
            return null
        }
    }

    private class DeleteAllTask(DAO: PictureDao) : HandlerTask<PictureDao, Picture>(DAO) {
        override fun doInBackground(vararg models: Picture): Void? {
            super.DAO.deleteAll()
            return null
        }
    }

}