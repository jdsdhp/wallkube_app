package cu.jesusd0897.wallkube.activity

import android.Manifest
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import com.google.android.material.textfield.TextInputLayout
import cu.jesusd0897.wallkube.R
import cu.jesusd0897.wallkube.fragment.AboutFragment
import cu.jesusd0897.wallkube.util.KAlert
import cu.jesusd0897.wallkube.util.KNav
import cu.jesusd0897.wallkube.util.KUtil
import cu.jesusd0897.wallkube.util.REQUEST_CODE_CALL_PHONE

class AboutActivity : BaseActivity() {

    private lateinit var balanceLayout: TextInputLayout
    private lateinit var passwordLayout: TextInputLayout
    private lateinit var inputDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
        KNav.replaceFragment(supportFragmentManager, AboutFragment.newInstance(), R.id.container)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        var allGrant = true
        for (result in grantResults) if (result != PackageManager.PERMISSION_GRANTED) allGrant =
            false
        if (allGrant) when (requestCode) {
            REQUEST_CODE_CALL_PHONE ->
                if (isInputValid()) KUtil.sendBalance(
                    this,
                    balanceLayout.editText!!.text.toString(),
                    passwordLayout.editText!!.text.toString()
                )
                else inputDialog.show()
        } else Toast.makeText(this, R.string.permissions_required, Toast.LENGTH_LONG).show()
    }

    private fun isInputValid(): Boolean {
        var isValid = true
        balanceLayout.error = null
        passwordLayout.error = null

        if (TextUtils.isEmpty(balanceLayout.editText!!.text.toString())) {
            balanceLayout.error = getString(R.string.required_field)
            isValid = false
        } else if (balanceLayout.editText!!.text.toString().toDouble() == 0.toDouble()) {
            balanceLayout.error = getString(R.string.value_cant_be_0)
            isValid = false
        }
        if (TextUtils.isEmpty(passwordLayout.editText!!.text.toString())) {
            passwordLayout.error = getString(R.string.required_field)
            isValid = false
        }
        return isValid
    }

    fun showInputDialog() {
        val view: View = layoutInflater.inflate(R.layout.content_send_balance_layout, null)
        balanceLayout = view.findViewById(R.id.balance_input_layout)
        passwordLayout = view.findViewById(R.id.password_input_layout)
        inputDialog = KAlert.buildDialog(
            this, R.string.invite_to_coffee, null,
            R.string.send_balance, R.string.cancel, R.drawable.ic_coffee, false,
            DialogInterface.OnClickListener { _, _ -> beginCall() }, null
        ).setView(view).create()
        inputDialog.show()
    }

    private fun beginCall() {
        KUtil.requestDialogPermissions(
            this, Manifest.permission.CALL_PHONE, REQUEST_CODE_CALL_PHONE
        )
    }

}