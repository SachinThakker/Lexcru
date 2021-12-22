package com.lexcru.lexcruapp

import android.content.Context
import android.content.Intent
import com.lexcru.lexcruapp.base.BaseActivity
import com.lexcru.lexcruapp.viewmodels.AuthViewModel
import com.lexcru.lexcruapp.views.auth.SplashActivity
import kotlin.reflect.KClass

class MainActivity : BaseActivity<AuthViewModel>() {

    override val modelClass: KClass<AuthViewModel>
        get() = AuthViewModel::class

    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun init() {
        viewModel.generateFcmToken()
//        val crashButton = Button(this)
//        crashButton.text = "Test Crash"
//        crashButton.setOnClickListener {
//            throw RuntimeException("Test Crash") // Force a crash
//        }
//
//        addContentView(crashButton, ViewGroup.LayoutParams(
//            ViewGroup.LayoutParams.MATCH_PARENT,
//            ViewGroup.LayoutParams.WRAP_CONTENT))

    }

    override fun setListeners() {

    }

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, SplashActivity::class.java)
            context.startActivity(intent)
        }
    }
}