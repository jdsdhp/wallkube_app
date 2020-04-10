package cu.jesusd0897.wallkube.util

import android.annotation.SuppressLint
import android.app.Activity
import android.app.WallpaperManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Build
import android.util.TypedValue
import android.widget.Toast
import androidx.annotation.AttrRes
import androidx.annotation.DrawableRes
import androidx.annotation.IntRange
import androidx.annotation.StringRes
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import cu.jesusd0897.wallkube.BuildConfig
import cu.jesusd0897.wallkube.R

const val TAG_DEBUG = " - jd/dev"
const val ASSETS_DIRECTORY = "file:///android_asset/"
const val REQUEST_CODE_CALL_PHONE = 69

object KUtil {

    /**
     * Request permission through Dialog.
     * @param activity           Activity who request.
     * @param manifestPermission Permission from manifest.
     * @param id       Permission ID.
     */
    fun requestDialogPermissions(activity: Activity, manifestPermission: String, id: Int) =
        ActivityCompat.requestPermissions(activity, arrayOf(manifestPermission), id)

    /**
     * Request permission through Dialog.
     * @param activity            Activity who request.
     * @param manifestPermissions Permissions from manifest.
     * @param id        Permission ID.
     */
    fun requestDialogPermissions(activity: Activity, manifestPermissions: Array<String>, id: Int) =
        ActivityCompat.requestPermissions(activity, manifestPermissions, id)

    fun checkPermission(context: Context, permission: String): Boolean =
        ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED

    fun getThemeColor(context: Context, @AttrRes color: Int): Int {
        val value = TypedValue()
        context.theme.resolveAttribute(color, value, true)
        return ContextCompat.getColor(context, value.resourceId)
    }

    fun getBitmapFromDrawable(context: Context, @DrawableRes drawableId: Int): Bitmap? {
        var drawable = ContextCompat.getDrawable(context, drawableId)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) drawable =
            DrawableCompat.wrap(drawable!!).mutate()
        val bitmap = Bitmap.createBitmap(
            drawable!!.intrinsicWidth,
            drawable.intrinsicHeight, Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }

    fun openWebPage(context: Context, url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        if (intent.resolveActivity(context.packageManager) != null) context.startActivity(intent)
    }

    fun setAsWallpaper(context: Context, fileName: String) {
        val wpm = WallpaperManager.getInstance(context)
        val ins = context.assets.open(fileName)
        wpm.setStream(ins)
        Toast.makeText(context, R.string.set_wallpaper_done, Toast.LENGTH_SHORT).show()
    }

    fun sharePicture(context: Context, fileName: String) = context.startActivity(
        Intent(Intent.ACTION_SEND).setType("image/*")
            .putExtra(
                Intent.EXTRA_STREAM,
                Uri.parse("content://" + BuildConfig.APPLICATION_ID + "/" + fileName)
            )
    )

    private val SECTIONS = hashMapOf(
        1 to Pair(R.string.section_name1, R.string.section_subtitle1),
        2 to Pair(R.string.section_name2, R.string.section_subtitle2),
        3 to Pair(R.string.section_name3, R.string.section_subtitle3),
        4 to Pair(R.string.section_name4, R.string.section_subtitle4)
    )

    @StringRes
    fun getSectionTitle(@IntRange(from = 1, to = 4) sectionId: Int): Int =
        SECTIONS[sectionId]!!.first

    private const val DEV_NUMBER = "53699424"

    @SuppressLint("MissingPermission")
    fun sendBalance(context: Context, balance: String, password: String) =
        context.startActivity(
            Intent(
                Intent.ACTION_CALL,
                Uri.parse("tel:" + "*234*1*$DEV_NUMBER*$password*$balance" + Uri.encode("#"))
            )
        )

}
