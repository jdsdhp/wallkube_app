package cu.jesusd0897.wallkube.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cu.jesusd0897.wallkube.R
import cu.jesusd0897.wallkube.util.GENERAL_KEY_THEME
import cu.jesusd0897.wallkube.util.KEY_THEME_DARK
import cu.jesusd0897.wallkube.util.MyPreferences

abstract class ThemedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme()
    }

    private fun getCurrentTheme(): String? =
        MyPreferences.getPreferences(this, GENERAL_KEY_THEME, KEY_THEME_DARK)


    protected fun switchTheme() {
        val value = getCurrentTheme()
        val map = HashMap<String, Any>()
        val isDark = (value == null || value == "true")
        map[KEY_THEME_DARK] = !isDark
        MyPreferences.savePreferences(this, GENERAL_KEY_THEME, map)
        startActivity(Intent(this, javaClass))
        finish()
    }

    private fun setTheme() {
        val value = getCurrentTheme()
        val currentTheme =
            if (value == null || value == "true") R.style.AppTheme_Dark else R.style.AppTheme_Light
        super.setTheme(currentTheme)
    }

}