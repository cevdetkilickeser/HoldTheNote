package com.cevdetkilickeser.holdthenote.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "notes")
data class Note (@PrimaryKey(autoGenerate = true)
                 @ColumnInfo(name= "id") val id : Int,
                 @ColumnInfo(name = "title") val title : String,
                 @ColumnInfo(name = "detail") val detail : String,
                 @ColumnInfo(name = "date") val date : String) : Serializable
