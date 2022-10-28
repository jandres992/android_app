package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.toolbox.JsonObjectRequest
import com.example.myapplication.databinding.ActivityActivityimgBinding
import com.android.volley.Request
import com.android.volley.toolbox.Volley
import org.json.JSONArray

class Activityimg : AppCompatActivity() {
    lateinit var  binding: ActivityActivityimgBinding
    val Adapterimg : Adapterimg = Adapterimg()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityActivityimgBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val datos = this.intent.extras
        val raza = datos!!.getString("raza").toString()

        binding.labeltitulo.text = "Imagenes de "+ raza.replaceFirstChar { it.uppercase() }
        descargar_img(raza)
    }

    fun descargar_img( raza : String){
        val url = "https://dog.ceo/api/breed/"+raza+"/images"
        val solicitud = JsonObjectRequest(Request.Method.GET, url, null,
            {response->
                try{
                    if(response.getString("status") == "success"){
                        Toast.makeText(this, "Descarga exitosa", Toast.LENGTH_SHORT).show()
                        val array_datos = response.getJSONArray("message")
                        val items: MutableList<ListadoImages> = ArrayList()

                        for (i in 0 until array_datos.length()) {
                            val item = array_datos.get(i)
                            val nombre = raza + " IMG " + i.toString()
                            items.add(ListadoImages(nombre, item.toString()))
                        }
                        binding.rvimg.setHasFixedSize(true)
                        binding.rvimg.layoutManager = LinearLayoutManager(this)
                        Adapterimg.Adapterimg(items, this)
                        binding.rvimg.adapter = Adapterimg
                    } else {
                        binding.labeltitulo.text = "Error al descargar imagenes"
                    }
                } catch (e: Exception) {
                    binding.labeltitulo.text = "$e"
                }
            }, {
                binding.labeltitulo.text = "$it"
            })
        val cola = Volley.newRequestQueue(this)
        cola.add(solicitud)
    }
}