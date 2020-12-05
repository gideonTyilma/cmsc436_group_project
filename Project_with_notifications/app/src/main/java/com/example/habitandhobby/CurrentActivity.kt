package com.example.habitandhobby

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity
class CurrentActivity(var category: String,
                      @PrimaryKey @ColumnInfo(name = "name") @NotNull var name: String,
                      var goal: String){


        constructor() : this("", "", "")

}
