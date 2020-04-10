package cu.jesusd0897.wallkube.model

import android.os.Parcelable

interface Model : Parcelable {
    fun match(query: String?): Boolean
}