package com.example.tamrineighthsubmitinformation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tamrineighthsubmitinformation.databinding.ActivityShowInfoBinding

class ShowInfoActivity : AppCompatActivity() {
    lateinit var binding: ActivityShowInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityShowInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setInfoInText()
        binding.butEditInfo.setOnClickListener { editInfo() }
        binding.butNewUser.setOnClickListener { addUser() }
    }
   fun setInfoInText(){
        sharedPreferences=getSharedPreferences(fileName,0)
       binding.tvNationalCode.text="کد ملی:"+sharedPreferences.getString("NationalCode","")
       binding.tvBornLocation.text="محل تولد:"+sharedPreferences.getString("BornLocation","")
       binding.tvLocation.text="آدرس:"+sharedPreferences.getString("Address","")
       binding.tvPostalAddress.text="کد پستی:"+sharedPreferences.getString("PostalCode","")
       binding.tvGender.text="جنسیت:"+sharedPreferences.getString("Gender","")
   }
    fun editInfo(){
        val result=Intent(this, MainActivity::class.java)
        setResult(RESULT_OK,result)
        finish()
    }

    override fun onBackPressed() {
       // super.onBackPressed()
    }
    fun addUser(){
        val editor = sharedPreferences.edit()
        editor.putString("FullName", null)
        editor.putString("NationalCode", null)
        editor.putString("BornLocation", null)
        editor.putString("Address", null)
        editor.putString("PostalCode", null)
        editor.putString("Gender",null)
        editor.apply()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}