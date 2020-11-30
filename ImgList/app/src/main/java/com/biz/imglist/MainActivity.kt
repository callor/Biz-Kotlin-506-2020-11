package com.biz.imglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val petList = arrayListOf<PetVO>(
        PetVO("몽탱",10,"Male","pet1"),
        PetVO("럭키",5,"Female","pet2"),
        PetVO("니키",3,"Female","pet3"),
        PetVO("장금이",2,"Male","pet4"),
        PetVO("희망이",2,"Male","pet5"),
        PetVO("몽탱",10,"Male","pet1"),
        PetVO("럭키",5,"Female","pet2"),
        PetVO("니키",3,"Female","pet3"),
        PetVO("장금이",2,"Male","pet4"),
        PetVO("희망이",2,"Male","pet5"),
        PetVO("몽탱",10,"Male","pet1"),
        PetVO("럭키",5,"Female","pet2"),
        PetVO("니키",3,"Female","pet3"),
        PetVO("장금이",2,"Male","pet4"),
        PetVO("희망이",2,"Male","pet5"),
        PetVO("몽탱",10,"Male","pet1"),
        PetVO("럭키",5,"Female","pet2"),
        PetVO("니키",3,"Female","pet3"),
        PetVO("장금이",2,"Male","pet4"),
        PetVO("희망이",2,"Male","pet5"),



    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val petListView = findViewById<RecyclerView>(R.id.pet_list)
        petListView.layoutManager = LinearLayoutManager(this)
        petListView.setHasFixedSize(true)
        petListView.adapter = PetViewAdapter(petList,this)
    }
}