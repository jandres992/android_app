package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.PatronlistBinding
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    val AdapterRazas : AdapterRazas = AdapterRazas()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cargar_listas_razas()
    }

    fun cargar_listas_razas(){
        val url = "https://dog.ceo/api/breeds/list/all"
        val solicitud = JsonObjectRequest(Request.Method.GET, url, null,
            {response->
                try{
                    if (response.getString("status") == "success"){
                        Toast.makeText(this, "Descarga Exitosa", Toast.LENGTH_SHORT).show()
                        val arrayDatos = response.getJSONObject("message")
                        val items : MutableList<listadoprincipal> = ArrayList()
                        val clave = arrayDatos.names()
                        for (i in 0 until clave.length()){
                            val valor = clave.getString(i)
                            items.add(listadoprincipal(valor))
                        }
                        binding.labeltitulo.text = "Mostrando " + clave.length().toString() + " razas"
                        binding.listrazas.setHasFixedSize(true)
                        binding.listrazas.layoutManager = LinearLayoutManager(this)
                        AdapterRazas.AdapterRazas(items, this)
                        binding.listrazas.adapter = AdapterRazas
                    } else {
                        Toast.makeText(this, "Error al desacrgar", Toast.LENGTH_SHORT).show()
                    }
                } catch (e:Exception){
                    Toast.makeText(this, "$e", Toast.LENGTH_SHORT).show()
                }
            }, {
                Toast.makeText(this, "$it", Toast.LENGTH_SHORT).show()
            })
        val cola = Volley.newRequestQueue(this)
        cola.add(solicitud)

    }

}