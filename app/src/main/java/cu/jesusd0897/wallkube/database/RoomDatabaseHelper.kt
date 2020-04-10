package cu.jesusd0897.wallkube.database

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import cu.jesusd0897.wallkube.database.dao.PictureDao
import cu.jesusd0897.wallkube.database.dao.SectionDao
import cu.jesusd0897.wallkube.database.model.Picture
import cu.jesusd0897.wallkube.database.model.Section
import cu.jesusd0897.wallkube.util.ASSETS_DIRECTORY

@Database(entities = [Section::class, Picture::class], version = DB_VERSION, exportSchema = false)
abstract class RoomDatabaseHelper : RoomDatabase() {

    abstract fun sectionDAO(): SectionDao
    abstract fun pictureDAO(): PictureDao

    companion object {

        @Volatile
        private var INSTANCE: RoomDatabaseHelper? = null

        fun getInstance(context: Context): RoomDatabaseHelper? {
            if (INSTANCE != null) return INSTANCE
            synchronized(this) {
                val instance =
                    Room.databaseBuilder(context, RoomDatabaseHelper::class.java, DB_NAME)
                        .fallbackToDestructiveMigration()
                        .addCallback(callback)
                        .build()
                INSTANCE = instance
                return instance
            }
        }

        private val callback: Callback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateDBTask(INSTANCE!!).execute()
            }
        }
    }

    private class PopulateDBTask(helper: RoomDatabaseHelper) : AsyncTask<Void, Void?, Unit?>() {

        private val sectionDao = helper.sectionDAO()
        private val pictureDao = helper.pictureDAO()

        override fun doInBackground(vararg voids: Void) {
            sectionDao.insert(
                Section(
                    "urban", null, ASSETS_DIRECTORY + "section_pics/section1.jpg"
                ),
                Section(
                    "natural", null, ASSETS_DIRECTORY + "section_pics/section2.jpg"
                ),
                Section(
                    "artistic", null, ASSETS_DIRECTORY + "section_pics/section3.jpg"
                ),
                Section(
                    "flat", null, ASSETS_DIRECTORY + "section_pics/section4.jpg"
                )
            )
            val sectionsCount = 4
            val sectionPicturesCount = 45
            for (s in 1..sectionsCount) {
                for (p in 1..sectionPicturesCount)
                    pictureDao.insert(
                        Picture(
                            s,
                            ASSETS_DIRECTORY + "s" + (if (s < 10) "0" else "") + s + "_p" + (if (p < 10) "0" else "") + p + ".jpg",
                            ASSETS_DIRECTORY + "thumb_s" + (if (s < 10) "0" else "") + s + "_p" + (if (p < 10) "0" else "") + p + ".jpg",
                            "s" + (if (s < 10) "0" else "") + s + "_p" + (if (p < 10) "0" else "") + p + ".jpg",
                            "faker.lorem.paragraph()",
                            "faker.name.name()",
                            false
                        )
                    )
            }
        }

    }

}