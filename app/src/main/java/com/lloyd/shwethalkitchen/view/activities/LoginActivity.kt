package com.lloyd.shwethalkitchen.view.activities

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.lloyd.shwethalkitchen.R
import com.lloyd.shwethalkitchen.view.models.LoginRequestBody
import com.lloyd.shwethalkitchen.view.models.LoginUIModel
import com.lloyd.shwethalkitchen.view.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var vm: LoginViewModel
    private var email: String? = null
    private var password: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initViews()
        vm = ViewModelProvider(this).get(LoginViewModel::class.java)
        vm.liveData.observe(this,
            Observer<LoginUIModel> {
                when (it) {
                    is LoginUIModel.OnSuccess -> {
                        Toast.makeText(
                            this@LoginActivity,
                            "OnSuccess status is " + it.s,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    is LoginUIModel.OnFailure -> {
                        Toast.makeText(
                            this@LoginActivity,
                            "OnError is " + it.s,
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                }
            })
    }

    private fun initViews() {
        et_username.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                email = s?.toString();
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })

        et_password.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                password = s?.toString();
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })

        btn_sign_in.setOnClickListener {
            if (::vm.isInitialized) {
                vm.callLoginApi(LoginRequestBody(email, password))
            }
        }
    }
}
