package com.example.to_do_app

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "To_Do_App")
data class Entity (
    @PrimaryKey(autoGenerate = true)
    var id:Int

    var title:String,
    var priority:String

)