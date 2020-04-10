package cu.jesusd0897.wallkube.config

import android.content.Context

internal object KPreferences {

    /**
     * Save a list of preferences identified by a general key in a different xml file.
     * @param context    App context.
     * @param generalKey General key for a preferences list.
     * @param map    Map of preferences.
     */
    fun store(context: Context, generalKey: String, map: Map<String, Any?>) {
        val preferences = context.getSharedPreferences(generalKey, Context.MODE_PRIVATE)
        val edit = preferences.edit()
        if (map.isNotEmpty())
            for ((key, value) in map) edit.putString(key, value.toString()).apply()
    }

    /**
     * Get a stored preference.
     * @param context    App context.
     * @param generalKey General key of preferences list.
     * @param keyValue   Specific key of preference.
     * @param defaultValue   Default value for return if nothing was found.
     * @return Value of a preference.
     */
    fun get(context: Context, generalKey: String, keyValue: String, defaultValue: String): String =
        context.getSharedPreferences(generalKey, Context.MODE_PRIVATE)
            .getString(keyValue, defaultValue)!!

    /**
     * Remove all custom saved preferences.
     * @param context App context.
     * @param generalKey General key of preferences.
     */
    fun remove(context: Context, generalKey: String) =
        context.getSharedPreferences(generalKey, Context.MODE_PRIVATE).edit().clear().apply()

}