package com.lexcru.lexcruapp.views.auth

import android.content.Context
import android.content.Intent
import androidx.lifecycle.observe
import com.lexcru.lexcruapp.MainActivity
import com.lexcru.lexcruapp.R
import com.lexcru.lexcruapp.base.BaseActivity
import com.lexcru.lexcruapp.utils.PreferenceHelper
import com.lexcru.lexcruapp.viewmodels.SplashViewModel
import kotlinx.android.synthetic.main.activity_splash.*
import kotlin.reflect.KClass

class SplashActivity : BaseActivity<SplashViewModel>() {

    override val modelClass: KClass<SplashViewModel>
        get() = SplashViewModel::class

    override fun getLayout(): Int {
        hideStatusBar()
        return R.layout.activity_splash
    }


    override fun init() {
        viewModel.startSplashScreenTimer()
        viewModel.splashScreenTimer.observe(this) {
            if (it) {
                goToNextScreen()
            }
        }
    }

    private fun goToNextScreen() {

        if (PreferenceHelper.getUserDetailsObject() == null) {
            RoleSelectionActivity.startActivity(this) //MainActivity.startActivity(this)
        } else {
            RoleSelectionActivity.startActivity(this)
        }
        finish()
    }

    override fun setListeners() {

    }

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context,SplashActivity::class.java)
            context.startActivity(intent)
        }
    }
}
