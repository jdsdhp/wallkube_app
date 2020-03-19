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