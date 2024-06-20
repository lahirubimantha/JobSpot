package com.example.JobSpot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.JobSpot.R
import com.example.JobSpot.databinding.ActivityUpdateSupplierBinding
import com.google.firebase.database.*


class update_supplier : AppCompatActivity() {
    private lateinit var binding : ActivityUpdateSupplierBinding
    private lateinit var updateRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateSupplierBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnUpdate.setOnClickListener {
            var jobID = binding.edtJobID.text.toString()
            var supplierID = binding.editsupplierID.text.toString()
            var supplierName = binding.editSupplierName.text.toString()
            var jobTitle = binding.editJobTitle.text.toString()
            var jobDescription = binding.editJobDescription.text.toString()
            var jobSalary = binding.editJobSalary.text.toString()
            var supplierContact = binding.editSupplierContact.text.toString()

            updateData(jobID,supplierID,supplierName,jobTitle,jobDescription,jobSalary,supplierContact)
        }
    }

    private fun updateData(jobID: String, supplierID: String, supplierName: String, jobTitle: String, jobDescription: String, jobSalary: String, supplierContact: String) {
        updateRef = FirebaseDatabase.getInstance().getReference("Supplier")
        val supplier = mapOf<String,String>(
            "supplierID" to supplierID,
            "supplierName" to supplierName,
            "jobTitle" to jobTitle,
            "jobDescription" to jobDescription,
            "jobSalary" to jobSalary,
            "supplierContact" to supplierContact
        )

        updateRef.child(jobID).updateChildren(supplier).addOnSuccessListener {
            binding.edtJobID.text.clear()
            binding.editsupplierID.text.clear()
            binding.editSupplierName.text.clear()
            binding.editJobTitle.text.clear()
            binding.editJobDescription.text.clear()
            binding.editJobSalary.text.clear()
            binding.editSupplierContact.text.clear()
            Toast.makeText(this,"Data updated Successfully",Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Toast.makeText(this,"Failed operation",Toast.LENGTH_SHORT).show()
        }
    }


}