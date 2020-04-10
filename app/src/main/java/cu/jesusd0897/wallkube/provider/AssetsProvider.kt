package cu.jesusd0897.wallkube.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.res.AssetFileDescriptor
import android.database.Cursor
import android.net.Uri
import java.io.FileNotFoundException
import java.io.IOException

class AssetsProvider : ContentProvider() {

    @Throws(FileNotFoundException::class)
    override fun openAssetFile(uri: Uri, mode: String): AssetFileDescriptor? {
        val assetManager = context!!.assets
        val fileName = uri.lastPathSegment ?: throw FileNotFoundException()
        try {
            return assetManager.openFd(fileName)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }

    override fun onCreate(): Boolean = false

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? = null

    override fun getType(uri: Uri): String? = null

    override fun insert(uri: Uri, values: ContentValues?): Uri? = null

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int = 0

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String>?
    ): Int = 0
}