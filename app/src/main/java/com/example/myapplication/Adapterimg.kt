package com.example.myapplication

import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ActivityActivityimgBinding
import com.example.myapplication.databinding.PatronimgBinding


class Adapterimg : RecyclerView.Adapter<Adapterimg.MyViewHolder>() {
    var Img :MutableList<ListadoImages> = java.util.ArrayList()
    lateinit var context : Context

    class MyViewHolder(val binding: PatronimgBinding) : RecyclerView.ViewHolder(binding.root)

    fun Adapterimg(Raza: MutableList<>)
}