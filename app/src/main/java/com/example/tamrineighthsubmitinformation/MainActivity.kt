package com.example.tamrineighthsubmitinformation

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.example.tamrineighthsubmitinformation.databinding.ActivityMainBinding
var NationalCode=""
var PostalCode=""
var Address=""
var BornLocation=""
var Gender=""
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var listOfEditText = mutableListOf<EditText>()
    lateinit var sharedPreferences: SharedPreferences
    private var fileName = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        binding.setInfo.setOnClickListener {
            setInfoButton()
            goToShowActivity()
        }
    }

    fun initView() {
        listOfEditText.add(binding.editTextTextPersonName)
        listOfEditText.add(binding.editTextNationalCode)
        listOfEditText.add(binding.editTextTextBornLocation)
        listOfEditText.add(binding.editTextTextLocation)
        listOfEditText.add(binding.editTextTextPostalAddress)
    }

    fun checkFields(): Boolean {
        var isError = true
        listOfEditText.forEach {
            if (it.text.isBlank()) {
               // isError = false
            }
        }
        if (binding.editTextNationalCode.text.length < 10 || binding.editTextNationalCode.text.length > 10) {
           // isError = false
        }
        if (binding.editTextTextPostalAddress.text.javaClass !is Number) {
            //isError = false
        }
        if (!binding.male.isChecked && !binding.female.isChecked) {
           // isError = false
        }
        return isError
    }

    fun setInfoButton() {
        if (checkFields()) {
            sharedPreferences = getSharedPreferences(fileName, 0)
            var editor = sharedPreferences.edit()
            editor.putString("FullName", binding.editTextTextPersonName.text.toString())
            editor.putString(NationalCode, binding.editTextNationalCode.text.toString())
            editor.putString(BornLocation, binding.editTextTextBornLocation.text.toString())
            editor.putString(Address, binding.editTextTextLocation.text.toString())
            editor.putString(PostalCode, binding.editTextTextPostalAddress.text.toString())
            var gender = ""
            if (binding.female.isChecked) {
                gender = "female"
            } else {
                gender = "male"
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

}