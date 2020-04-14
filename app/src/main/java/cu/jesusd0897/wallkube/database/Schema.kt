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