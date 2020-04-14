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

package cu.jesusd0897.wallkube.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import cu.jesusd0897.wallkube.database.dao.PictureDao
import cu.jesusd0897.wallkube.database.model.Picture
import cu.jesusd0897.wallkube.database.repo.PictureRepo

class PictureViewModel(app: Application) : AndroidViewModel(app), PictureDao {
    private val repo: PictureRepo = PictureRepo(app)
    override val all: LiveData<MutableList<Picture>> = repo.all
    override val favorites: LiveData<MutableList<Picture>> = repo.favorites
    override fun get(id: String): LiveData<Picture> = repo.get(id)
    override fun insert(model: Picture) = repo.insert(model)
    override fun insert(vararg models: Picture) = repo.insert(*models)
    override fun update(model: Picture) = repo.update(model)
    override fun update(vararg models: Picture) = repo.update(*models)
    override fun delete(model: Picture) = repo.delete(model)
    override fun delete(vararg models: Picture) = repo.delete(*models)
    override fun deleteAll() = repo.deleteAll()
    override fun findBySection(sectionId: String): LiveData<MutableList<Picture>> =
        repo.findBySection(sectionId)
}