package cu.jesusd0897.wallkube.model

import androidx.annotation.StringRes
import cu.jesusd0897.wallkube.R
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Library(
    @StringRes val name: Int,
    @StringRes val link: Int,
    @StringRes val license: Int
) : Model {
    override fun match(query: String?): Boolean = true
}

val LIBRARIES: List<Library> = arrayListOf(
    Library(
        R.string.library_kotlin,
        R.string.library_kotlin_link,
        R.string.library_kotlin_license
    ),
    Library(
        R.string.library_material,
        R.string.library_material_link,
        R.string.library_material_license
    ),
    Library(
        R.string.library_materialabout,
        R.string.library_materialabout_link,
        R.string.library_materialabout_license
    ), Library(
        R.string.library_okio,
        R.string.library_okio_link,
        R.string.library_okio_license
    ),
    Library(
        R.string.library_photoview,
        R.string.library_photoview_link,
        R.string.library_photoview_license
    ),
    Library(
        R.string.library_picasso,
        R.string.library_picasso_link,
        R.string.library_picasso_license
    )
)