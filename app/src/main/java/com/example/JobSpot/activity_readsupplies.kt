package com.example.JobSpot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.JobSpot.R
import com.example.JobSpot.databinding.ActivityReadsuppliesBinding
import com.example.JobSpot.databinding.ActivityUpdateSupplierBinding
import com.google.firebase.database.*


class activity_readsupplies : AppCompatActivity() {
    private lateinit var binding : ActivityReadsuppliesBinding
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReadsuppliesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnReadSupply.setOnClickListener {
            val jobID:String = binding.edtJobid.text.toString()
            if(jobID.isNotEmpty()){
                readData(jobID)
            }
            else{
                Toast.makeText(this,"Please enter jobID",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun readData(jobID: String) {
        dbRef = FirebaseDatabase.getInstance().getReference("Supplier")
        dbRef.child(jobID).get().addOnSuccessListener {
            if(it.exists()){
                var supplierID = it.child("supplierID").value
                var jobSupplierName = it.child("jobSupplierName").value
                var jobTitle = it.child("jobTitle").value
                var jobID = it.child("jobID").value
                var jobDescription = it.child("jobDescription").value
                var jobSalary = it.child("jobSalary").value
                var supplierContact = it.child("supplierContact").value

                Toast.makeText(this,"Data read successfully",Toast.LENGTH_SHORT).show()
                binding.edtJobid.text.clear()

                binding.tvSupplierID.text = supplierID.toString()
                binding.tvSupplierName.text = jobSupplierName.toString()
                binding.tvJobTitle.text = jobTitle.toString()
                binding.tvJobID.text = jobID.toString()
                binding.tvJobDesc.text = jobDescription.toString()
                binding.tvJobSalary.text = jobSalary.toString()
                binding.tvContact.text = supplierContact.toString()

            }
            else{
                Toast.makeText(this,"JobId doesn't exists",Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{
            Toast.makeText(this,"Operation failed",Toast.LENGTH_SHORT).show()
        }
    }
}