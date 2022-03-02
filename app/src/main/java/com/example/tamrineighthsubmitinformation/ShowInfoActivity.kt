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
    }

}