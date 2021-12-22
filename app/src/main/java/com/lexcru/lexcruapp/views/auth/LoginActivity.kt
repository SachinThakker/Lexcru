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
import com.lexcru.lexcruapp.utils.getValue
import com.lexcru.lexcruapp.utils.showToast
import com.lexcru.lexcruapp.viewmodels.AuthViewModel
import kotlinx.android.synthetic.main.activity_login.*
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import kotlin.reflect.KClass


class LoginActivity : BaseActivity<AuthViewModel>(), View.OnClickListener {

    override val modelClass: KClass<AuthViewModel>
        get() = AuthViewModel::class

    override fun getLayout(): Int {
        hideStatusBar()
        return R.layout.activity_login
    }


    private val loginObserver = Observer<Any> { response ->
        when (response) {

            is Resource.Error<*> -> {
                showToast(this, response.message.toString())
            }
            is Resource.UnAuthorisedRequest<*> -> {
                /*CommonUtils.showTokenExpiredDialog(
                    resources.getString(R.string.lbl_session_expired_msg),
                    this,
                    prefs
                )*/
            }
            is Resource.Success<*> -> {
//                val res = response.data as LoginResponse
//                PreferenceHelper.saveUserObject(res.data?.result)
//                DashboardActivity.startActivity(this@LoginActivity)
//                finishAffinity()
            }
            is Resource.Loading<*> -> {
                response.isLoadingShow.let {
                    if (it as Boolean) {
                        showProgressDialog(this)
                    } else {
                        hideProgressDialog()
                    }
                }
            }
            is Resource.NoInternetError1<*> -> {

            }
            is Resource.ValidationError<*> -> {

            }
            is Resource.ValidationCheck<*> -> {

            }
        }
    }


    override fun onResume() {
        super.onResume()
        printHashKey(this)
    }

    fun printHashKey(pContext: Context?) {
        try {
            val info = packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES)
            for (signature in info.signatures) {
                val md: MessageDigest = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                val hashKey: String = String(Base64.encode(md.digest(), 0))
                Log.i("LoginActivity", "printHashKey() Hash Key: $hashKey")
            }
        } catch (e: NoSuchAlgorithmException) {
            Log.e("LoginActivity", "printHashKey()", e)
        } catch (e: Exception) {
            Log.e("LoginActivity", "printHashKey()", e)
        }
    }

    override fun init() {
        viewModel.generateFcmToken()
    }



    override fun addObserver() {
        viewModel.loginStatus.observe(this, loginObserver)
    }

    override fun removeObserver() {
        viewModel.loginStatus.removeObserver(loginObserver)
    }
    override fun setListeners() {
        btnSignIn.setOnClickListener(this)
        tvSignUpMessage.setOnClickListener(this)
    }
    override fun onClick(v: View?) {

        when (v) {
            btnSignIn -> {
                if (isDataValid()) {
//                    viewModel.loginUser(
//                        etEmail.getValue()
//                    )
                }
            }
            tvSignUpMessage -> {
                SignupActivity.startActivity(this) //MainActivity.startActivity(this)
            }
        }
    }

    private fun isDataValid(): Boolean {
        when {
            etMobileNo.getValue().isEmpty() -> {
                return false
            }
            else -> {
                return true
            }
        }
    }

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
        }
    }
}