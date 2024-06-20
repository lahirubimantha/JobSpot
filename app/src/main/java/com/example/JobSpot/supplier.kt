package com.example.JobSpot

class Supplier {
    private var SupplierID:String = ""
    private var JobSupplierName:String = ""
    private var jobTitle: String = ""
    private var jobID: String = ""
    private var jobDescription: String = ""
    private var jobSalary: String = ""
    private var SupplierContact: Int = 0

    constructor()

    fun setSupplierID(SupplierID:String){
        this.SupplierID = SupplierID
    }

    fun getSupplierID():String{
        return SupplierID
    }

    fun setJobSupplierName(JobSupplierName:String){
        this.JobSupplierName = JobSupplierName
    }

    fun getJobSupplierName():String{
        return JobSupplierName
    }

    fun setJobTitle(jobTitle:String){
        this.jobTitle = jobTitle
    }

    fun getJobTitle():String{
        return jobTitle
    }

    fun setJobID(jobID:String){
        this.jobID = jobID
    }

    fun getJobID():String{
        return jobID
    }

    fun setJobDescription(jobDescription:String){
        this.jobDescription = jobDescription
    }

    fun getJobDescription():String{
        return jobDescription
    }

    fun setJobSalary(jobSalary:String){
        this.jobSalary = jobSalary
    }

    fun getJobSalary():String{
        return jobSalary
    }

    fun setSupplierContact(SupplierContact:Int){
        this.SupplierContact = SupplierContact
    }

    fun getSupplierContact():Int{
        return SupplierContact
    }
}