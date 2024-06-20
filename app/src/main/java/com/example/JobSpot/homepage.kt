package com.example.JobSpot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.JobSpot.R
import com.google.firebase.auth.FirebaseAuth


class homepage : AppCompatActivity() {
    private lateinit var tv_supplier : TextView

    private lateinit var btn_logout : Button

    private lateinit var logoutAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)

        tv_supplier = findViewById(R.id.tv_supplier)

        btn_logout = findViewById(R.id.btn_logout)

        logoutAuth = FirebaseAuth.getInstance()

        tv_supplier.setOnClickListener {
            intent = Intent(this@homepage,activity_supplieshome::class.java)
            startActivity(intent)
        }

        btn_logout.setOnClickListener {
            logoutAuth.signOut()
            intent = Intent(this@homepage,Login::class.java)
            startActivity(intent)
            finish()
        }
    }
}