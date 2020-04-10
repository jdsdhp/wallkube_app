package cu.jesusd0897.wallkube.fragment

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.SwitchCompat
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import cu.jesusd0897.wallkube.R
import cu.jesusd0897.wallkube.activity.SettingsActivity
import cu.jesusd0897.wallkube.config.EN_LANG
import cu.jesusd0897.wallkube.config.ES_LANG
import cu.jesusd0897.wallkube.config.KSettings
import cu.jesusd0897.wallkube.util.KAlert
import cu.jesusd0897.wallkube.util.KNav

class SettingsFragment : Fragment() {

    private lateinit var langCardView: CardView
    private lateinit var langTextView: AppCompatTextView
    private lateinit var darkThemeSwitch: SwitchCompat

    companion object {
        fun newInstance(): SettingsFragment = SettingsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_settings, container, false)
        initViews(view)
        initActionListeners(view)
        return view
    }

    private fun initViews(view: View) {
        langTextView = view.findViewById(R.id.setting_value_lang)
        val languages = resources.getStringArray(R.array.language_values)
        val currentLang = KSettings.getLang(context!!)
        langTextView.text =
            if (ES_LANG == currentLang) languages[0] else languages[1]
        langCardView = view.findViewById(R.id.setting_lang_layout)
        langCardView.setOnClickListener {
            KAlert.buildChoiceDialog(
                context!!, R.string.select,
                null, null, R.string.cancel, null, true,
                null, null, R.array.language_values,
                if (currentLang == ES_LANG) 0 else 1,
                DialogInterface.OnClickListener { _, which ->
                    KSettings.setLang(context!!, if (which == 0) ES_LANG else EN_LANG)
                    langTextView.text = languages[which]
                    restartActivity()
                }
            ).show()
        }

        darkThemeSwitch = view.findViewById(R.id.setting_value_theme)
        darkThemeSwitch.isChecked = KSettings.isDarkTheme(context!!)
        darkThemeSwitch.setOnCheckedChangeListener { _, _ ->
            KSettings.setDarkTheme(context!!, !KSettings.isDarkTheme(context!!))
            restartActivity()
        }
    }

    private fun initActionListeners(view: View) {
        view.findViewById<CardView>(R.id.setting_about)
            .setOnClickListener { KNav.navToAbout(context!!) }
        view.findViewById<CardView>(R.id.setting_libraries)
            .setOnClickListener { KNav.navToLibraries(context!!) }
    }

    private fun restartActivity() {
        startActivity(Intent(activity!!, SettingsActivity::class.java))
        activity!!.finish()
    }

}