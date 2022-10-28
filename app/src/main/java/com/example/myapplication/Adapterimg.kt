package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.ActivityActivityimgBinding
import com.example.myapplication.databinding.PatronimgBinding


class Adapterimg : RecyclerView.Adapter<Adapterimg.MyViewHolder>() {
    var Img :MutableList<ListadoImages> = java.util.ArrayList()
    lateinit var context : Context

    class MyViewHolder(val binding: PatronimgBinding) : RecyclerView.ViewHolder(binding.root)

    fun Adapterimg(Raza: MutableList<ListadoImages>, context: Context){
        this.Img = Raza
        this.context = context
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = Img.get(position)
        holder.binding.labelnombre.text = item.id_img
        Glide.with(context).load(item.ruta_img).into(holder.binding.imgdog)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return Adapterimg.MyViewHolder(
            PatronimgBinding.inflate(
                LayoutInflater.from(parent.context), parent,false
            )
        )
    }

    override fun getItemCount(): Int {
        return Img.size
    }
}