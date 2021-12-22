package com.lexcru.lexcruapp.views.Registration

import android.app.DatePickerDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.gson.JsonObject
import com.lexcru.lexcruapp.R
import com.lexcru.lexcruapp.base.BaseActivity
import com.lexcru.lexcruapp.utils.Constant
import com.lexcru.lexcruapp.viewmodels.AuthViewModel
import com.lexcru.lexcruapp.views.auth.SignupActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_registration.*
import java.util.*
import kotlin.reflect.KClass

class RegisterActivity : BaseActivity<AuthViewModel>() , View.OnClickListener{
    override val modelClass: KClass<AuthViewModel>
        get() = AuthViewModel::class
    var dealer: String? = ""
    var retailer: String? = ""
    var technician: String? = ""
    private var mYear = 0
    private var mMonth = 0
    private var mDay = 0
    override fun getLayout(): Int {
        hideStatusBar()
        return R.layout.activity_registration
    }

    override fun init() {
        viewModel.generateFcmToken()
    }

    override fun setListeners() {
        img_uploadfile.setOnClickListener(this)
        btn_register.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            btn_register -> {
                if (Constant.isDeviceOnline(this@RegisterActivity)) {

                } else {
                    Constant.ShowAlertDialog(
                        this@RegisterActivity,
                        "Alert!",
                        "No Internet Connection."
                    )
                }
            }
            tvSignUpMessage -> {
                SignupActivity.startActivity(this) //MainActivity.startActivity(this)
            }
        }
    }
    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, RegisterActivity::class.java)
            context.startActivity(intent)
        }
    }
//    fun doRegister() {
//
//        if (Constant.isDeviceOnline(this@RegisterActivity)) {
//            //  startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
//            if (technician!!.length > 0) {
//                if (TechnicianValidation()) {
//                    // doRegister();
//                    Maintannace(this, "", "registration")
//                }
//            }
//            if (dealer!!.length > 0) {
//                if (DealerValidation()) {
//                    // doRegister();
//                    Maintannace(this, "", "registration")
//                }
//            }
//            if (retailer!!.length > 0) {
//                if (DistributorValidation()) {
//                    // doRegister();
//                    //  MaintananceTask();
//                    Maintannace(this, "", "registration")
//                }
//            }
//        } else {
//            Constant.ShowAlertDialog(
//                this@RegistrationActivity,
//                "Alert!",
//                "No Internet Connection."
//            )
//        }
//        progressDialog = ProgressDialog(this)
//        progressDialog!!.setMessage("Loading...")
//        progressDialog!!.show()
//        val jsonObject = JsonObject()
//
//        var edt_name= findViewById<EditText>(R.id.edt_name)
//        var edt_mobile_number= findViewById<EditText>(R.id.edt_mobile_number)
//        var edt_company_name= findViewById<EditText>(R.id.edt_company_name)
//        var edt_address= findViewById<EditText>(R.id.edt_address)
//        var edt_pincode= findViewById<EditText>(R.id.edt_pincode)
//        var edt_city= findViewById<EditText>(R.id.edt_city)
//        var edt_gst= findViewById<EditText>(R.id.edt_gst)
//
//        var edt_email= findViewById<EditText>(R.id.edt_email)
//        var edt_dob= findViewById<EditText>(R.id.edt_dob)
//        var edt_residential_address= findViewById<EditText>(R.id.edt_residential_address)
//
//
//        var edt_aniversary_date= findViewById<EditText>(R.id.edt_aniversary_date)
//        var edt_referral_code= findViewById<EditText>(R.id.edt_referral_code)
//        var edt_state= findViewById<EditText>(R.id.edt_state)
//        var edt_district= findViewById<EditText>(R.id.edt_district)
//
//        jsonObject.addProperty("name", edt_name!!.text.toString())
//        jsonObject.addProperty("mobile", edt_mobile_number!!.text.toString())
//        jsonObject.addProperty("email", edt_email!!.text.toString())
//        jsonObject.addProperty("pincode", edt_pincode!!.text.toString())
//        jsonObject.addProperty("address", edt_address!!.text.toString())
//        jsonObject.addProperty("residential_address", edt_residential_address!!.text.toString())
//        jsonObject.addProperty("city", edt_city!!.text.toString())
//        jsonObject.addProperty("state", state)
//        jsonObject.addProperty("district", district)
//        jsonObject.addProperty("ref_code", edt_referral_code!!.text.toString())
//        val stranivarsary_date = edt_aniversary_date!!.text.toString()
//        jsonObject.addProperty("aniversary_date", stranivarsary_date)
//        val strdate = edt_dob!!.text.toString()
//        jsonObject.addProperty("dob", strdate)
//        if (appVersionName != null && appVersionName!!.length > 0) {
//            jsonObject.addProperty("version_no", appVersionName)
//        }
//        if (imgfileEncodedstr != null && imgfileEncodedstr!!.length > 0) {
//            jsonObject.addProperty("image_data", imgfileEncodedstr)
//        } else {
//            jsonObject.addProperty("image_data", " ")
//        }
////        if (App.getPrefs().getValue(Constant.FCM_TOKEN) != null && App.getPrefs()
////                .getValue(Constant.FCM_TOKEN).length > 0
////        ) {
////            jsonObject.addProperty("token_id", App.getPrefs().getValue(Constant.FCM_TOKEN))
////        } else {
////            jsonObject.addProperty("token_id", "")
////        }
//        if (dealer != null && dealer!!.length > 0 || retailer != null && retailer!!.length > 0) {
//            jsonObject.addProperty("company_name", edt_company_name!!.text.toString())
//        } else {
//            jsonObject.addProperty("company_name", "")
//        }
//        if (dealer != null && dealer!!.length > 0) {
//            jsonObject.addProperty("gst_no", edt_gst!!.text.toString())
//            jsonObject.addProperty("role", dealer)
//        }
//        if (technician != null && technician!!.length > 0) {
//            jsonObject.addProperty("role", technician)
//            jsonObject.addProperty("gst_no", "")
//        } else if (retailer != null && retailer!!.length > 0) {
//            jsonObject.addProperty("role", retailer)
//            jsonObject.addProperty("gst_no", "")
//        }
//        Log.e("reg", "" + jsonObject.toString())
//        Ion.with(this)
//            .load(BASE_URL.REGISTRATION)
//            .setJsonObjectBody(jsonObject)
//            .asJsonObject()
//            .setCallback(FutureCallback<JsonObject?> { e, result ->
//                if (progressDialog != null && progressDialog!!.isShowing) progressDialog!!.dismiss()
//                if (e != null && result == null) return@FutureCallback
//                if (result != null) {
//                    var btn_register= findViewById<Button>(R.id.btn_register)
//                    btn_register!!.isEnabled = true
//                    ShowAlertDialog(
//                        this@RegistrationActivity,
//                        "Success",
//                        result["message"].toString()
//                    )
//                }
//            })
//    }
//    fun TechnicianValidation(): Boolean {
//        if (edt_name != null && edt_name!!.text.toString().length > 0) {
//            if (edt_mobile_number != null && edt_mobile_number!!.text.toString().length == 10) {
//                if (edt_pincode != null && edt_pincode!!.text.toString().length > 0) {
//                    if (edt_pincode!!.text.toString().length == 6) {
//                        return true
//                    } else {
//                        EnableRegisterButton(btn_register, true)
//                        Constant.ShowAlertDialog(
//                            this@RegistrationActivity,
//                            "Alert!",
//                            "Pincode is invalid.PinCode Required 6 digit"
//                        )
//                    }
//                } else {
//                    EnableRegisterButton(btn_register, true)
//                    Constant.ShowAlertDialog(
//                        this@RegistrationActivity,
//                        "Alert!",
//                        "Pincode is  Required Field."
//                    )
//                }
//            } else {
//                EnableRegisterButton(btn_register, true)
//                Constant.ShowAlertDialog(
//                    this@RegistrationActivity,
//                    "Alert!",
//                    "Please Enter Valid Mobile Number."
//                )
//            }
//        } else {
//            EnableRegisterButton(btn_register, true)
//            Constant.ShowAlertDialog(
//                this@RegistrationActivity,
//                "Alert!",
//                "Please Enter Name , Name is  Required Field. "
//            )
//        }
//        return false
//    }
//
//    fun DealerValidation(): Boolean {
//        if (edt_name != null && edt_name!!.text.toString().length > 0) {
//            if (edt_company_name != null && edt_company_name!!.text.toString().length > 0) {
//                if (edt_mobile_number != null && edt_mobile_number!!.text.toString().length > 0) {
//                    if (edt_pincode != null && edt_pincode!!.text.toString().length > 0) {
//                        if (edt_pincode!!.text.toString().length == 6) {
//                            if (edt_gst != null && edt_gst!!.text.toString().length > 0) {
//                                return true
//                            } else {
//                                EnableRegisterButton(btn_register, true)
//                                Constant.ShowAlertDialog(
//                                    this@RegistrationActivity,
//                                    "Alert!",
//                                    "GST Field is Required Field"
//                                )
//                            }
//                        } else {
//                            EnableRegisterButton(btn_register, true)
//                            Constant.ShowAlertDialog(
//                                this@RegistrationActivity,
//                                "Alert!",
//                                "Pincode is invalid.PinCode Required 6 digit"
//                            )
//                        }
//                    } else {
//                        EnableRegisterButton(btn_register, true)
//                        Constant.ShowAlertDialog(
//                            this@RegistrationActivity,
//                            "Alert!",
//                            "Pincode is Required Field"
//                        )
//                    }
//                } else {
//                    EnableRegisterButton(btn_register, true)
//                    Constant.ShowAlertDialog(
//                        this@RegistrationActivity,
//                        "Alert!",
//                        "Please Enter  Mobile Number, Mobile Number is Required Field."
//                    )
//                }
//            } else {
//                EnableRegisterButton(btn_register, true)
//                Constant.ShowAlertDialog(
//                    this@RegistrationActivity,
//                    "Alert!",
//                    "Please Enter Company Name, Company name is Required Field."
//                )
//            }
//        } else {
//            EnableRegisterButton(btn_register, true)
//            Constant.ShowAlertDialog(
//                this@RegistrationActivity,
//                "Alert!",
//                "Please Enter Name , Name is Required Field. "
//            )
//        }
//        return false
//    }
//
//    fun DistributorValidation(): Boolean {
//        if (edt_name != null && edt_name!!.text.toString().length > 0) {
//            if (edt_company_name != null && edt_company_name!!.text.toString().length > 0) {
//                if (edt_mobile_number != null && edt_mobile_number!!.text.toString().length > 0) {
//                    if (edt_pincode != null && edt_pincode!!.text.toString().length > 0) {
//                        if (edt_pincode!!.text.toString().length == 6) {
//                            if (imgfileEncodedstr != null && imgfileEncodedstr!!.length > 0) {
//                                return true
//                            } else {
//                                EnableRegisterButton(btn_register, true)
//                                Constant.ShowAlertDialog(
//                                    this@RegistrationActivity,
//                                    "Alert!",
//                                    "please Upload profile photo"
//                                )
//                            }
//                        } else {
//                            EnableRegisterButton(btn_register, true)
//                            Constant.ShowAlertDialog(
//                                this@RegistrationActivity,
//                                "Alert!",
//                                "Pincode is invalid.PinCode Required 6 digit"
//                            )
//                        }
//                    } else {
//                        EnableRegisterButton(btn_register, true)
//                        Constant.ShowAlertDialog(
//                            this@RegistrationActivity,
//                            "Alert!",
//                            "Pincode is Required Field"
//                        )
//                    }
//                } else {
//                    Constant.ShowAlertDialog(
//                        this@RegistrationActivity,
//                        "Alert!",
//                        "Please Enter  Mobile Number,Mobile Number is Required Field."
//                    )
//                    EnableRegisterButton(btn_register, true)
//                }
//            } else {
//                Constant.ShowAlertDialog(
//                    this@RegistrationActivity,
//                    "Alert!",
//                    "Please Enter Company Name, Company Name filed is Required."
//                )
//                EnableRegisterButton(btn_register, true)
//            }
//        } else {
//            Constant.ShowAlertDialog(
//                this@RegistrationActivity,
//                "Alert!",
//                "Please Enter Name , Name is Required Field. "
//            )
//            EnableRegisterButton(btn_register, true)
//        }
//        return false
//    }



//    fun Maintannace(context: Context?, userid: String?, page: String?) {
//        val jsonObject = JsonObject()
//        jsonObject.addProperty("user_id", userid)
//        jsonObject.addProperty("pagename", page)
//        Ion.with(context)
//            .load(BASE_URL.MAINTAINANCE)
//            .setJsonObjectBody(jsonObject)
//            .`as`(Maintanance::class.java)
//            .setCallback(FutureCallback<Maintanance?> { e, result ->
//                if (e != null && result == null) return@FutureCallback
//                Log.e("CAT", "Registration : $result")
//                if (result != null) {
//                    if (result.getMessage().contains("Working") && result.getResponse()
//                            .getUser_lock().equals("Unlock")
//                    ) {
//                        doRegister()
//                    } else {
//                        if (!result.getResponse().getUser_lock().equals("Unlock")) {
//                            Logout()
//                        } else if (!result.getMessage().contains("Working")) {
//                            Constant.ShowAlertDialog(
//                                this@RegistrationActivity,
//                                "Under Maintenance",
//                                result.getMessage().toString()
//                            )
//                        }
//                    }
//                }
//            })
//    }

    fun ShowCalenderDialog(context: Context?, textView: TextView?) {
        val date = ""
        val c = Calendar.getInstance()
        mYear = c[Calendar.YEAR]
        mMonth = c[Calendar.MONTH]
        mDay = c[Calendar.DAY_OF_MONTH]
        val datePickerDialog = DatePickerDialog(
            context!!,
            { view, year, monthOfYear, dayOfMonth ->
                val month: String
                val datestr: String
                datestr = if (dayOfMonth < 10) {
                    "0$dayOfMonth"
                } else {
                    dayOfMonth.toString()
                }
                month = if (monthOfYear + 1 < 10) {
                    "0" + (monthOfYear + 1)
                } else {
                    (monthOfYear + 1).toString()
                }
                textView!!.text = "$datestr-$month-$year"
                // textView.setText(year + "-" + month + "-" + dayOfMonth);
            }, mYear, mMonth, mDay
        )
        datePickerDialog.show()
    }
}