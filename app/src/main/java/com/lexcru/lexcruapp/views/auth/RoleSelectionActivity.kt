package com.lexcru.lexcruapp.views.auth

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import com.google.android.gms.tasks.Task
import com.lexcru.lexcruapp.R
import com.lexcru.lexcruapp.base.BaseActivity
import com.lexcru.lexcruapp.network.Resource
import com.lexcru.lexcruapp.utils.Constant
import com.lexcru.lexcruapp.utils.PreferenceHelper
import com.lexcru.lexcruapp.utils.getValue
import com.lexcru.lexcruapp.utils.showToast
import com.lexcru.lexcruapp.viewmodels.AuthViewModel
import com.lexcru.lexcruapp.views.Registration.RegisterActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_select_role.*
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import kotlin.reflect.KClass


class RoleSelectionActivity : BaseActivity<AuthViewModel>(), View.OnClickListener {

    override val modelClass: KClass<AuthViewModel>
        get() = AuthViewModel::class

    override fun getLayout(): Int {
        hideStatusBar()
        return R.layout.activity_select_role
    }



    override fun setListeners() {
        rlDealer.setOnClickListener(this)
        rlSubdealer.setOnClickListener(this)
        rltechnician.setOnClickListener(this)
        rlLogin.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        when (v) {
            rlDealer ->{
                val tech_intent = Intent(this@RoleSelectionActivity, RegisterActivity::class.java)
                tech_intent.putExtra(Constant.AS_TECHNICIAN, Constant.AS_TECHNICIAN)
                SignupActivity.startActivity(context = this)
            }
            rlSubdealer -> {
                val dealer_intent = Intent(this@RoleSelectionActivity, RegisterActivity::class.java)
                dealer_intent.putExtra(Constant.AS_TECHNICIAN, Constant.AS_TECHNICIAN)
                SignupActivity.startActivity(context = this)
            }
            rltechnician ->{
                val retailer_intent = Intent(this@RoleSelectionActivity, RegisterActivity::class.java)
                retailer_intent.putExtra(Constant.AS_TECHNICIAN, Constant.AS_TECHNICIAN)
                SignupActivity.startActivity(context = this)
            }
            rlLogin -> {
                LoginActivity.startActivity(context = this)
            }
        }
    }


    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, RoleSelectionActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun init() {
        viewModel.generateFcmToken()
    }
}