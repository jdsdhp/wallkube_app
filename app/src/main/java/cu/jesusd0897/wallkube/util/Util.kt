package cu.jesusd0897.wallkube.util

import android.app.WallpaperManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import cu.jesusd0897.wallkube.BuildConfig
import cu.jesusd0897.wallkube.R

fun setAsWallpaper(context: Context, fileName: String) {
    val wpm = WallpaperManager.getInstance(context)
    val ins = context.assets.open(fileName)
    wpm.setStream(ins)
    Toast.makeText(context, R.string.set_wallpaper_done, Toast.LENGTH_SHORT).show()
}

fun sharePicture(context: Context, fileName: String) {
    val theUri =
        Uri.parse("content://" + BuildConfig.APPLICATION_ID + "/" + fileName)
    val theIntent = Intent(Intent.ACTION_SEND)
    theIntent.type = "image/*"
    theIntent.putExtra(Intent.EXTRA_STREAM, theUri)
    context.startActivity(theIntent)
}