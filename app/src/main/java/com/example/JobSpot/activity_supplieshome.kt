package com.example.JobSpot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.cardview.widget.CardView

class activity_supplieshome : AppCompatActivity() {
    private lateinit var tv_supplyList: TextView
    private lateinit var tv_addSupply: TextView
    private lateinit var tv_readSupply: TextView
    private lateinit var tv_updateSupply: TextView
    private lateinit var tv_deleteSupply: TextView

    fun onClickListnerSupplierCards(){
        val tv_supplyList:TextView = findViewById(R.id.tv_supplyList)
        val tv_addSupply:TextView = findViewById(R.id.tv_addSupply)
        val tv_readSupply:TextView = findViewById(R.id.tv_readSupply)
        val tv_updateSupply:TextView = findViewById(R.id.tv_updateSupply)
        val tv_deleteSupply:TextView = findViewById(R.id.tv_deleteSupply)

        tv_supplyList.setOnClickListener{
            val intent = Intent(this@activity_supplieshome,supplier_dashboard::class.java)
            startActivity(intent)
        }

        tv_addSupply.setOnClickListener{
            val intent = Intent(this@activity_supplieshome,add_supplier::class.java)
            startActivity(intent)
        }

        tv_readSupply.setOnClickListener {
            val intent = Intent(this@activity_supplieshome,activity_readsupplies::class.java)
            startActivity(intent)
        }

        tv_updateSupply.setOnClickListener {
            val intent = Intent(this@activity_supplieshome,update_supplier::class.java)
            startActivity(intent)
        }

        tv_deleteSupply.setOnClickListener {
            val intent = Intent(this@activity_supplieshome,activity_removesupply::class.java)
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_supplieshome)
        onClickListnerSupplierCards()
    }
}