package com.example.inote.Model

import android.icu.text.CaseMap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "iNotes")
class Notes(
    @PrimaryKey(autoGenerate = true)
    val id:Int? = null,
    val title:String,
    val subtitle:String,
    val note:String,
    val date:String,
    val priority:String
)