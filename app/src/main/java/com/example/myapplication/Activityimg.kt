package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityActivityimgBinding


class Activityimg : AppCompatActivity() {
    lateinit var  binding: ActivityActivityimgBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityActivityimgBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val datos = this.intent.extras
        var raza = datos!!.getString("raza").toString()

        binding.labeltitulo.text = "Imagenes de "+ raza.replaceFirstChar { it.uppercase() }
    }
}