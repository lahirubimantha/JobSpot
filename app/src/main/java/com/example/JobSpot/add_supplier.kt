package com.example.JobSpot

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.EditTextPreference
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.JobSpot.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class add_supplier : AppCompatActivity() {
    private lateinit var addSupplierID: EditText;
    private lateinit var addJobSupplierName: EditText;
    private lateinit var addJobTitle: EditText;
    private lateinit var addJobID: EditText;
    private lateinit var addJobDescription: EditText;
    private lateinit var addJobSalary : EditText;
    private lateinit var addSupplierContact: EditText;

    private lateinit var btn_addSupplier: Button;
    private lateinit var btn_cancelAddSupplier: Button;

    private lateinit var supplierObj: Supplier;

    private lateinit var dbRef: DatabaseReference;

    var nextID = 1

    private fun clearControls() {
        addSupplierID.setText("")
        addJobSupplierName.setText("")
        addJobTitle.setText("")
        addJobID.setText("")
        addJobDescription.setText("")
        addJobSalary.setText("")
        addSupplierContact.setText("")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_supplier)

        addSupplierID = findViewById(R.id.addSupplierID)
        addJobSupplierName = findViewById(R.id.addJobSupplierName)
        addJobTitle = findViewById(R.id.addJobTitle)
        addJobID = findViewById(R.id.addJobID)
        addJobDescription = findViewById(R.id.addJobDescription)
        addJobSalary = findViewById(R.id.addJobSalary)
        addSupplierContact = findViewById(R.id.addSupplierContact)

        btn_addSupplier = findViewById(R.id.btn_addSupplier)
        btn_cancelAddSupplier = findViewById(R.id.btn_cancelAddSupplier)

        supplierObj = Supplier();

        btn_cancelAddSupplier.setOnClickListener {
            var intent = Intent(this@add_supplier,activity_supplieshome::class.java)
            startActivity(intent)
        }

        dbRef = FirebaseDatabase.getInstance().getReference().child("Supplier")

        //save data
        btn_addSupplier.setOnClickListener {
            try{
                val id = nextID.toString()
                nextID++

                if(TextUtils.isEmpty(addSupplierID.text.toString()))
                    Toast.makeText(applicationContext,"Please enter job supplierID",Toast.LENGTH_SHORT).show()
                else if(TextUtils.isEmpty(addJobSupplierName.text.toString()))
                    Toast.makeText(applicationContext,"Please enter job supplier name",Toast.LENGTH_SHORT).show()
                else if(TextUtils.isEmpty(addJobTitle.text.toString()))
                    Toast.makeText(applicationContext,"Please enter job title",Toast.LENGTH_SHORT).show()
                else if(TextUtils.isEmpty(addJobID.text.toString()))
                    Toast.makeText(applicationContext,"Please enter job title",Toast.LENGTH_SHORT).show()
                else if(TextUtils.isEmpty(addJobDescription.text.toString()))
                    Toast.makeText(applicationContext,"Please enter job description",Toast.LENGTH_SHORT).show()
                else if(TextUtils.isEmpty(addJobSalary.text.toString()))
                    Toast.makeText(applicationContext,"Please enter job salary",Toast.LENGTH_SHORT).show()
                else{
                    supplierObj.setSupplierID(addSupplierID.text.toString().trim())
                    supplierObj.setJobSupplierName(addJobSupplierName.text.toString().trim())
                    supplierObj.setJobTitle(addJobTitle.text.toString().trim())
                    supplierObj.setJobID(addJobID.text.toString().trim())
                    supplierObj.setJobDescription(addJobDescription.text.toString().trim())
                    supplierObj.setJobSalary(addJobSalary.text.toString().trim())
                    supplierObj.setSupplierContact(addSupplierContact.text.toString().trim().toInt())

                    var jobID = supplierObj.getJobID()

                    dbRef.child(jobID).setValue(supplierObj)

                    Toast.makeText(
                        applicationContext,
                        "Data saved Successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                    clearControls()
                }
            }
            catch(e: NumberFormatException){
                Toast.makeText(applicationContext,"Invalid contact number",Toast.LENGTH_SHORT).show()
            }
        }

    }
}