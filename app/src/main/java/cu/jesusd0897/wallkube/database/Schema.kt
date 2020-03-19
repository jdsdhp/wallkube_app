package cu.jesusd0897.wallkube.database

const val DB_NAME = "data.db"
const val DB_VERSION = 1

object Tables {
    const val SECTIONS = "sections"
    const val PICTURES = "pictures"
}

object SectionColumns {
    const val ID = "id"
    const val TITLE = "title"
    const val SUBTITLE = "subtitle"
    const val IMAGE_PATH = "image_path"
}

object PictureColumns {
    const val ID = "id"
    const val SECTION_ID = "section_id"
    const val FILE_URL = "file_url"
    const val FILE_THUMB_URL = "file_thumb_url"
    const val FILE_NAME = "name"
    const val INFO = "info"
    const val PUBLISHED_BY = "published_by"
    const val FAVORITE = "favorite_flag"
}