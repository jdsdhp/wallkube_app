package cu.jesusd0897.wallkube.util

import android.content.Context

const val GENERAL_KEY_THEME = "theme"
const val KEY_THEME_DARK = "dark"

object MyPreferences {

    /**
     * Save a map of preferences identified by a general key into specific XML file.
     * @param context    App context.
     * @param generalKey General key (XML file name) for this preferences map.
     * @param map        Map of preferences.
     */
    fun savePreferences(context: Context, generalKey: String, map: Map<String, Any>) {
        val sharedPreferences = context.getSharedPreferences(generalKey, Context.MODE_PRIVATE)
        val edit = sharedPreferences.edit()
        if (map.isNotEmpty())
            for ((key, value) in map) edit.putString(key, value.toString()).apply()
    }

    /**
     * Get saved preference value from custom XML file.
     * @param context    App context.
     * @param generalKey General key (XML file name).
     * @param keyValue   Specific key.
     * @return Value of a preference. Null if was't found.
     */
    fun getPreferences(context: Context, generalKey: String, keyValue: String): String? {
        val sharedPreferences = context.getSharedPreferences(generalKey, Context.MODE_PRIVATE)
        return sharedPreferences.getString(keyValue, null)
    }

    /**
     * Remove all preference values from a specific XML file.
     * @param context    App context.
     * @param generalKey General key (XML file name).
     */
    fun removeAllPreferences(context: Context, generalKey: String) {
        val sharedPreferences = context.getSharedPreferences(generalKey, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear().apply()
    }

}