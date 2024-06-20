package com.example.JobSpot

import android.app.Dialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class Register : AppCompatActivity() {
    private lateinit var usernameInput : EditText
    private lateinit var emailInput : EditText
    private lateinit var passwordInput : EditText
    private lateinit var confirmpwdInput : EditText

    private lateinit var btn_register : Button

    private lateinit var auth : FirebaseAuth

    //Create reference for wait page
    private lateinit var dialog : Dialog

    private lateinit var tv_login: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        usernameInput = findViewById(R.id.usernameInput)
        emailInput = findViewById(R.id.emailInput)
        passwordInput = findViewById(R.id.passwordInput)
        confirmpwdInput = findViewById(R.id.confirmpwdInput)

        btn_register = findViewById(R.id.btn_register)

        auth = FirebaseAuth.getInstance()

        tv_login = findViewById(R.id.tv_login)

        tv_login.setOnClickListener(View.OnClickListener {
            intent = Intent(this@Register, Login::class.java)
            startActivity(intent)
            finish()
        })

        btn_register.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View) {
                val username = usernameInput.getText().toString()
                val email = emailInput.getText().toString()
                val password = passwordInput.getText().toString()
                val confirmpassword = confirmpwdInput.getText().toString()

                tv_login.setOnClickListener(View.OnClickListener {
                    intent = Intent(this@Register, Login::class.java)
                    startActivity(intent)
                    finish()
                })

                if(Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    if (password == confirmpassword && password.length >= 8) {
                        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
                        dialog = Dialog(this@Register)
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                        dialog.setContentView(R.layout.dialog_wait)
                        dialog.setCanceledOnTouchOutside(false)
                        dialog.show()

                        auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener { task ->
                                if(task.isSuccessful()){
                                    Toast.makeText(this@Register,"Successfully created",Toast.LENGTH_SHORT).show()
                                    val user: FirebaseUser? = auth.currentUser
                                    if(user != null){
                                        dialog.dismiss();
                                        intent = Intent(this@Register,homepage::class.java)
                                        startActivity(intent)
                                        finish()
                                    }

                                }else{
                                    dialog.dismiss();
                                    Toast.makeText(this@Register,"Authentication Failed",Toast.LENGTH_SHORT).show()
                                }
                            }

                    }else if(password.length < 8){
                        passwordInput.setError("Password must be 8 characters long")
                        passwordInput.requestFocus()
                    }else{
                        confirmpwdInput.setError("Password doesn't match")
                        confirmpwdInput.requestFocus()
                    }
                }else{
                    emailInput.setError("Please enter a valid Email Address");
                    emailInput.requestFocus();
                }

            }
        })


    }
}