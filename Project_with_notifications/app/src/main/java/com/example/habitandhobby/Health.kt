package com.example.habitandhobby

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Health : Activity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.health)
        val set = findViewById<Button>(R.id.btnSave)

        set.setOnClickListener {

            val intent = Intent(this, SmallGoals::class.java)
            // Launch the Activity using the intent
//            create a current activity record in the database.put health as string extra so next activity knows
//            what category to be in. need addsmallgoal activity
//            save notification as a string and have a way to parse
            var cat = "Health"
            var name = findViewById<EditText>(R.id.name).text.toString()
            var goal = findViewById<EditText>(R.id.goal).text.toString()
            var ins = InsertCurrent(cat,name,goal,this.application)
            ins.execute()
            intent.putExtra("name",name)
            intent.putExtra("cat",cat)
            startActivity(intent)
        }

        val clear = findViewById<Button>(R.id.btnClear)
        clear.setOnClickListener {
            findViewById<EditText>(R.id.name).setText("")
            findViewById<EditText>(R.id.goal).setText("")
        }

    }
    class InsertCurrent(val cat:String,
                        val name:String,
                        val goal:String,
                        val application:Application): AsyncTask<Void, Void, Void>(){
        override fun doInBackground(vararg params: Void?): Void? {
            var db = goalsDB.get(application)
            db.dataDao().insertCurrent(CurrentActivity(cat,name,goal))
            var curr:List<CurrentActivity> = db.dataDao().getAllCurr()
            for(x in curr){
                Log.i("HabitName",x.name)
                Log.i("Category",x.category)
                Log.i("Goal",x.goal)
            }

            return null
        }
    }
}