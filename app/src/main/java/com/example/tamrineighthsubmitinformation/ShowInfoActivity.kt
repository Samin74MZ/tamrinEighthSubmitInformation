package com.example.tamrineighthsubmitinformation

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
    }
   fun setInfoInText(){
       var getInfo=getSharedPreferences(resources.getString(R.string.app_name),0)
       binding.tvNationalCode.text="کد ملی:"+getInfo.getString(NationalCode,"")
       binding.tvBornLocation.text="محل تولد:"+getInfo.getString(BornLocation,"")
       binding.tvLocation.text="آدرس:"+getInfo.getString(Address,"")
       binding.tvPostalAddress.text="کد پستی:"+getInfo.getString(PostalCode,"")
       binding.tvGender.text="جنسیت:"+getInfo.getString(Gender,"")
   }
    fun editInfo(){

    }
}