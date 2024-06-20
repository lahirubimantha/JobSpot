package com.example.JobSpot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class supplier_home : AppCompatActivity() {
    private lateinit var btn_update1: Button;
    private lateinit var btn_addTask: Button;

    fun onClickButtonListenerUpdate() {
        val btnUpdate1: Button = findViewById(R.id.btn_update1)
        val btn_addTask: Button = findViewById(R.id.btn_addTask)

        //Navigate to update page
        btnUpdate1.setOnClickListener {
            val intent = Intent(this@supplier_home, update_supplier::class.java)
            startActivity(intent)
        }

        //Navigate to add job page for supplier
        btn_addTask.setOnClickListener {
            val intent = Intent(this@supplier_home, add_supplier::class.java)
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_supplier_home)
        onClickButtonListenerUpdate()
    }
}