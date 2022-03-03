package com.example.tamrineighthsubmitinformation

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.tamrineighthsubmitinformation.databinding.ActivityMainBinding
var fileName = ""
lateinit var sharedPreferences: SharedPreferences
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var listOfEditText = mutableListOf<EditText>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()

    }

    fun initView() {
        listOfEditText.add(binding.editTextTextPersonName)
        listOfEditText.add(binding.editTextNationalCode)
        listOfEditText.add(binding.editTextTextBornLocation)
        listOfEditText.add(binding.editTextTextLocation)
        listOfEditText.add(binding.editTextTextPostalAddress)
        binding.setInfo.setOnClickListener {
            setInfoButton()
            goToShowActivity()
        }
    }

    fun checkFields(): Boolean {
        var isError = true
        listOfEditText.forEach {
            if (it.text.isBlank()) {
                it.error = "این قسمت نمی تواند خالی باشد"
                isError = false
            }
        }
        if (binding.editTextNationalCode.text.length < 10 || binding.editTextNationalCode.text.length > 10) {
            binding.editTextNationalCode.error = "کد ملی باید 10 رقم باشد"
            isError = false
        }
        if (binding.editTextTextPostalAddress.inputType!=InputType.TYPE_CLASS_NUMBER) {
            binding.editTextTextPostalAddress.error = "کد پستی باید عدد باشد"
            isError = false
        }
        if (!binding.male.isChecked && !binding.female.isChecked) {
            binding.tvGender.error="لطفا گزینه مناسب را انتخاب کنید"
            isError = false
        }
        return isError
    }

    fun setInfoButton() {
        if (checkFields()) {
            sharedPreferences = getSharedPreferences(fileName, 0)
            var editor = sharedPreferences.edit()
            editor.putString("FullName", binding.editTextTextPersonName.text.toString())
            editor.putString("NationalCode", binding.editTextNationalCode.text.toString())
            editor.putString("BornLocation", binding.editTextTextBornLocation.text.toString())
            editor.putString("Address", binding.editTextTextLocation.text.toString())
            editor.putString("PostalCode", binding.editTextTextPostalAddress.text.toString())
            var gender = ""
            if (binding.female.isChecked) {
                gender = "زن"
            } else {
                gender = "مرد"
            }
            editor.putString("Gender", gender)
            editor.commit()
            Toast.makeText(this, "Info Saved", Toast.LENGTH_SHORT).show()
        }
    }

    fun goToShowActivity() {
        if (checkFields()) {
            val intent = Intent(this, ShowInfoActivity::class.java)
            startActivity(intent)
        }
    }
    val startForResult=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result: ActivityResult ->
        if (result.resultCode==Activity.RESULT_OK){
            val intent=result.data
            val isNewUser=intent?.getBooleanExtra("NewUser",false)
            if (isNewUser!!){
                listOfEditText.forEach { it.text.clear() }
                binding.gender.callOnClick()
            }
        }
    }
}