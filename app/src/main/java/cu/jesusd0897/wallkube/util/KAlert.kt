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

package cu.jesusd0897.wallkube.util

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.view.View
import androidx.annotation.ArrayRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import cu.jesusd0897.wallkube.R
import cu.jesusd0897.wallkube.database.model.Picture
import cu.jesusd0897.wallkube.database.repo.PictureRepo

object KAlert {

    fun buildDialog(
        context: Context,
        @StringRes title: Int?, @StringRes message: Int?,
        @StringRes positiveText: Int?, @StringRes negativeText: Int?,
        @DrawableRes icon: Int?, isCancelable: Boolean?,
        positiveListener: DialogInterface.OnClickListener?,
        negativeListener: DialogInterface.OnClickListener?
    ): MaterialAlertDialogBuilder {
        val builder = MaterialAlertDialogBuilder(context)
        isCancelable?.let { builder.setCancelable(it) }
        message?.let { builder.setMessage(it) }
        icon?.let { builder.setIcon(it) }
        title?.let { builder.setTitle(it) }
        positiveText?.let { builder.setPositiveButton(it, positiveListener) }
        negativeText?.let { builder.setNegativeButton(it, negativeListener) }
        return builder
    }

    fun buildChoiceDialog(
        context: Context,
        @StringRes title: Int?, @StringRes message: Int?,
        @StringRes positiveText: Int?, @StringRes negativeText: Int?,
        @DrawableRes icon: Int?, isCancelable: Boolean?,
        positiveListener: DialogInterface.OnClickListener?,
        negativeListener: DialogInterface.OnClickListener?,
        @ArrayRes items: Int,
        checkedItem: Int,
        clickListener: DialogInterface.OnClickListener?
    ): MaterialAlertDialogBuilder {
        val builder = MaterialAlertDialogBuilder(context)
        isCancelable?.let { builder.setCancelable(it) }
        message?.let { builder.setMessage(it) }
        icon?.let { builder.setIcon(it) }
        title?.let { builder.setTitle(it) }
        positiveText?.let { builder.setPositiveButton(it, positiveListener) }
        negativeText?.let { builder.setNegativeButton(it, negativeListener) }
        builder.setSingleChoiceItems(items, checkedItem, clickListener)
        return builder
    }

    fun buildMultiChoiceDialog(
        context: Context,
        @StringRes title: Int?, @StringRes message: Int?,
        @StringRes positiveText: Int?, @StringRes negativeText: Int?,
        @DrawableRes icon: Int?, isCancelable: Boolean?,
        positiveListener: DialogInterface.OnClickListener?,
        negativeListener: DialogInterface.OnClickListener?,
        @ArrayRes items: Int,
        checkedItems: BooleanArray,
        clickListener: DialogInterface.OnMultiChoiceClickListener?
    ): MaterialAlertDialogBuilder {
        val builder = MaterialAlertDialogBuilder(context)
        isCancelable?.let { builder.setCancelable(it) }
        message?.let { builder.setMessage(it) }
        icon?.let { builder.setIcon(it) }
        title?.let { builder.setTitle(it) }
        positiveText?.let { builder.setPositiveButton(it, positiveListener) }
        negativeText?.let { builder.setNegativeButton(it, negativeListener) }
        builder.setMultiChoiceItems(items, checkedItems, clickListener)
        return builder
    }

    fun buildSimpleOKDialog(
        context: Context,
        @StringRes title: Int?, @StringRes message: Int?,
        @StringRes positiveText: Int?,
        @DrawableRes icon: Int?, isCancelable: Boolean?
    ): MaterialAlertDialogBuilder {
        val builder = MaterialAlertDialogBuilder(context)
        isCancelable?.let { builder.setCancelable(it) }
        message?.let { builder.setMessage(it) }
        icon?.let { builder.setIcon(it) }
        title?.let { builder.setTitle(it) }
        positiveText?.let { builder.setPositiveButton(it, null) }
        return builder
    }

    fun buildSimpleOKDialog(
        context: Context, title: String?, message: String?, positiveText: String?,
        @DrawableRes icon: Int?, isCancelable: Boolean?
    ): MaterialAlertDialogBuilder {
        val builder = MaterialAlertDialogBuilder(context)
        isCancelable?.let { builder.setCancelable(it) }
        message?.let { builder.setMessage(it) }
        icon?.let { builder.setIcon(it) }
        title?.let { builder.setTitle(it) }
        positiveText?.let { builder.setPositiveButton(it, null) }
        return builder
    }

    /**
     * Show picture handler modal dialog.
     * @param activity Parent activity.
     */
    fun showPictureSheetDialog(activity: Activity, item: Picture) {
        val sheetDialog = BottomSheetDialog(activity)
        val v: View = activity.layoutInflater.inflate(R.layout.content_picture_sheet_layout, null)
        val favButton = v.findViewById<AppCompatButton>(R.id.action_mark_has_favorite)
        favButton.text =
            activity.getString(if (item.isFavorite) R.string.unmark_from_favorites else R.string.mark_has_favorite)
        favButton.setCompoundDrawablesWithIntrinsicBounds(
            if (item.isFavorite) R.drawable.ic_star_black2_24dp
            else R.drawable.ic_star_border_black2_24dp, 0, 0, 0
        )
        favButton.setOnClickListener {
            item.isFavorite = !item.isFavorite
            PictureRepo(activity).update(item)
            favButton.setCompoundDrawablesWithIntrinsicBounds(
                if (item.isFavorite) R.drawable.ic_star_black2_24dp
                else R.drawable.ic_star_border_black2_24dp, 0, 0, 0
            )
            sheetDialog.dismiss()
        }
        v.findViewById<View>(R.id.action_share)
            .setOnClickListener {
                KUtil.sharePicture(activity, item.fileName)
                sheetDialog.dismiss()
            }
        v.findViewById<View>(R.id.action_wallpaper).setOnClickListener {
            buildDialog(
                activity, R.string.set_wallpaper, R.string.set_wallpaper_question,
                R.string.accept, R.string.cancel, null, true,
                DialogInterface.OnClickListener { _, _ ->
                    KUtil.setAsWallpaper(activity, item.fileName)
                }, null
            ).show()
            sheetDialog.dismiss()
        }
        v.findViewById<View>(R.id.action_cancel).setOnClickListener { sheetDialog.dismiss() }
        sheetDialog.setContentView(v)
        sheetDialog.show()
    }


}

