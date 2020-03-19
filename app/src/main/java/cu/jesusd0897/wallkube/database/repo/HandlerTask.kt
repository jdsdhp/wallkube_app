package cu.jesusd0897.wallkube.database.repo

import android.os.AsyncTask

internal abstract class HandlerTask<D, M>(val DAO: D) : AsyncTask<M, Void?, Void?>()