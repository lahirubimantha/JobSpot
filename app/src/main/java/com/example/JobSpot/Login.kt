package com.example.JobSpot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.JobSpot.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class Login : AppCompatActivity() {
    private lateinit var login_email : EditText
    private lateinit var login_password : EditText

    private lateinit var loginBtn : Button

    private lateinit var loginAuth : FirebaseAuth

    private lateinit var signup : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_email = findViewById(R.id.login_email)
        login_password = findViewById(R.id.login_password)

        loginBtn = findViewById(R.id.loginBtn)

        loginAuth = FirebaseAuth.getInstance()

        signup = findViewById(R.id.signup)

        signup.setOnClickListener(View.OnClickListener {
            intent = Intent(this@Login, Register::class.java)
            startActivity(intent)
            finish()
        })

        loginBtn.setOnClickListener(View.OnClickListener {
            val email = login_email.text.toString()
            val password = login_password.text.toString()

            if (Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length >= 8) {
                loginAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val user: FirebaseUser? = loginAuth.currentUser
                            if (user != null) {
                                Toast.makeText(this@Login,"LoggedIn successfully", Toast.LENGTH_SHORT).show()
                                intent = Intent(this@Login, homepage::class.java)
                                startActivity(intent)
                                finish()
                            }
                        } else {
                            Toast.makeText(this@Login,"Authentication Failed", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                login_email.setError("Please enter a valid Email Address")
                login_email.requestFocus()
            } else {
                login_password.setError("Password must be at least 8 characters")
                login_password.requestFocus()
            }
        })

    }
}
