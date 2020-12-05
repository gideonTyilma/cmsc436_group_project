package com.example.habitandhobby

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.util.*

@Entity
class Goal(var habitName:String,
           @PrimaryKey @NotNull var name:String,
           var date:String,var category:String) {
    constructor() : this("", "", java.util.Calendar.getInstance().time.toString(),"")

}
