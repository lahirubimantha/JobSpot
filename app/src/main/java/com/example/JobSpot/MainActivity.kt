package com.example.JobSpot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.JobSpot.R


class MainActivity : AppCompatActivity() {
    private lateinit var main_login : Button
    private lateinit var main_signin : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_login = findViewById(R.id.main_loginBtn)
        main_signin = findViewById(R.id.main_signupBtn)

        main_login.setOnClickListener {
            intent = Intent(this@MainActivity,Login::class.java)
            startActivity(intent)
        }

        main_signin.setOnClickListener {
            intent = Intent(this@MainActivity,Register::class.java)
            startActivity(intent)
        }
    }
}