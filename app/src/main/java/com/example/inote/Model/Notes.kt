package com.example.inote.Model

import android.icu.text.CaseMap
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "iNotes")
@Parcelize
class Notes(
    @PrimaryKey(autoGenerate = true)
    val id:Int? = null,
    val title:String,
    val subtitle:String,
    val note:String,
    val date:String,
    val priority:String
) :Parcelable