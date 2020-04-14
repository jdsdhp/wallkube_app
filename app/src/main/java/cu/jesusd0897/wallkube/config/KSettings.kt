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

package cu.jesusd0897.wallkube.config

import android.content.Context

//General keys
private const val SETTINGS = "settings"

//Root settings
private const val LANG_KEY = "lang"
private const val DARK_THEME_KEY = "dark_theme"
private const val SHOW_WIZARD_KEY = "show_wizard"

//Languages
const val ES_LANG = "es"
const val EN_LANG = "en"

object KSettings {

    fun restoreDefaultValues(context: Context) = KPreferences.remove(context, SETTINGS)

    fun setLang(context: Context, lang: String) =
        KPreferences.store(context, SETTINGS, hashMapOf(LANG_KEY to lang))

    fun getLang(context: Context): String = KPreferences.get(context, SETTINGS, LANG_KEY, ES_LANG)

    fun setDarkTheme(context: Context, isEnable: Boolean) =
        KPreferences.store(context, SETTINGS, hashMapOf(DARK_THEME_KEY to isEnable))

    fun isDarkTheme(context: Context): Boolean =
        KPreferences.get(context, SETTINGS, DARK_THEME_KEY, true.toString()).toBoolean()

    fun setWizardEnabled(context: Context, isEnable: Boolean) =
        KPreferences.store(context, SETTINGS, hashMapOf(SHOW_WIZARD_KEY to isEnable))

    fun isWizardEnabled(context: Context): Boolean =
        KPreferences.get(context, SETTINGS, SHOW_WIZARD_KEY, true.toString()).toBoolean()

}