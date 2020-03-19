package cu.jesusd0897.wallkube.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import cu.jesusd0897.wallkube.database.dao.SectionDao
import cu.jesusd0897.wallkube.database.model.Section
import cu.jesusd0897.wallkube.database.repo.SectionRepo

class SectionViewModel(app: Application) : AndroidViewModel(app), SectionDao {
    private val repo: SectionRepo = SectionRepo(app)
    override val all: LiveData<MutableList<Section>> = repo.all
    override fun get(id: String): LiveData<Section> = repo.get(id)
    override fun insert(model: Section) = repo.insert(model)
    override fun insert(vararg models: Section) = repo.insert(*models)
    override fun update(model: Section) = repo.update(model)
    override fun update(vararg models: Section) = repo.update(*models)
    override fun delete(model: Section) = repo.delete(model)
    override fun delete(vararg models: Section) = repo.delete(*models)
    override fun deleteAll() = repo.deleteAll()
}