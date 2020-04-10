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