package com.biz.imglist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PetViewAdapter(private val petList: ArrayList<PetVO>, private val context : Context):
        RecyclerView.Adapter<PetViewAdapter.PetViewHoder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetViewHoder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pet_item,parent,false)
        return PetViewHoder(view)
    }

    override fun onBindViewHolder(holder: PetViewHoder, position: Int) {

        holder.txtName.text = petList[position].name
        holder.txtAge.text = petList[position].age.toString()
        holder.txtGender.text = petList[position].gender

        if(petList[position].photo != "") {
            // 이미지 이름에서 Id값 추출하기
            val resId = context.resources.getIdentifier(petList[position].photo,
                "drawable",context.packageName)
            holder.imgPhoto.setImageResource(resId)
        } else {
            // 이미지가 없으면 andriod 기본 icon으로 보이기
            holder.imgPhoto.setImageResource(R.mipmap.ic_launcher)
        }
    }

    override fun getItemCount(): Int {
        return petList.size
    }

    class PetViewHoder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtName: TextView = itemView.findViewById(R.id.pet_item_name)
        var txtAge = itemView.findViewById<TextView>(R.id.pet_item_age)
        var txtGender = itemView.findViewById<TextView>(R.id.pet_item_gender)
        var imgPhoto = itemView.findViewById<ImageView>(R.id.pet_item_img)
    }

}