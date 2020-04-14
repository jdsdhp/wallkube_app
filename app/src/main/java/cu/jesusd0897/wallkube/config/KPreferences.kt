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