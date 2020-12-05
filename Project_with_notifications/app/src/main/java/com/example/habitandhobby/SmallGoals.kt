package com.example.habitandhobby

import android.app.Activity
import android.app.AlertDialog
import android.app.Application
import android.content.DialogInterface
import android.content.Intent
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.provider.CalendarContract
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.widget.*
import androidx.annotation.RequiresApi
import java.util.*
import kotlin.collections.ArrayList

class SmallGoals : Activity(){
    private lateinit var listView: ListView
    private lateinit var mAdapter: smallGoalAdapter
    private lateinit var habitName: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.small_goal)
        var category = intent.getStringExtra("cat")
         habitName = intent.getStringExtra("name").toString()
        listView = findViewById(R.id.list)
        mAdapter = smallGoalAdapter(applicationContext)
        listView.adapter = mAdapter


//        make listeners for the buttons
//        add button to show a dialog with edittext and date timepicker
        findViewById<Button>(R.id.add).setOnClickListener{
            Toast.makeText(this,"testingadd",Toast.LENGTH_LONG).show()
            val vw = layoutInflater.inflate(R.layout.goal_dialog,null)
            val alertDialog: AlertDialog? = this.let {

                val builder = AlertDialog.Builder(it)

                builder.setView(vw)

                builder.create()
                builder.show()
            }

            var smallGoalName = ""
            var date = ""



            val datePicker = vw.findViewById<DatePicker>(R.id.date_Picker)

            val today = Calendar.getInstance()
            datePicker.init(today.get(Calendar.YEAR), today.get(Calendar.MONTH),
                today.get(Calendar.DAY_OF_MONTH)

            ) { view, year, month, day ->
                val month = month + 1
                date = "$month/$day/$year"

            }

            vw.findViewById<Button>(R.id.reminder).setOnClickListener{
                val intent = Intent(Intent.ACTION_INSERT).setData(CalendarContract.Events.CONTENT_URI)
                startActivity(intent)

            }

            vw.findViewById<Button>(R.id.btnSave).setOnClickListener{
                smallGoalName = vw.findViewById<EditText>(R.id.goal_name).text.toString()
                var ins = habitName?.let { it1 -> category?.let { it2 ->
                    InsertSmallGoal(it1, smallGoalName, date,
                        it2, this.application)
                } }
                habitName?.let { it1 -> category?.let { it2 -> Goal(it1,smallGoalName,date, it2) } }?.let { it2 ->
                    mAdapter.add(
                        it2
                    )
                }
                ins!!.execute()
                alertDialog!!.dismiss()

            }


        }
        findViewById<Button>(R.id.clear).setOnClickListener{
            mAdapter.clear()
            var clr = Clear(this.application)
            clr.execute()
        }

        findViewById<Button>(R.id.delete).setOnClickListener{
            mAdapter.deleteLast()
            var del = Delete(this.application)
            del.execute()
        }

        var list:ArrayList<Goal> = ArrayList<Goal>()
        var pop = Populate(this.application,list,habitName)
        pop.execute()
        Thread.sleep(1000)
        mAdapter.clear()
        for(x in list){
            mAdapter.add(x)
            Log.i("Populted with: ",x.habitName)
        }
    }

    class Populate(val application: Application,var list:ArrayList<Goal>,var category: String?): AsyncTask<Void, Void, Void>(){
        override fun doInBackground(vararg params: Void?): Void? {
            var db = goalsDB.get(application)
            var li:List<Goal> = db.dataDao().getAllGoalsInCategory(category)

            for(x in li){
                list.add(x)
            }

            return null
        }


    }

    class Delete(val application: Application): AsyncTask<Void, Void, Void>(){
        override fun doInBackground(vararg params: Void?): Void? {
            var db = goalsDB.get(application)
            db.dataDao().deleteLastGoal()
            var goals:List<Goal> = db.dataDao().getAllSmallGoals()
            for(x in goals){
                Log.i("HabitName",x.habitName)
                Log.i("Name",x.name)
                Log.i("Date",x.date)
                Log.i("Category",x.category)
            }
            return null
        }

    }



    class Clear(val application: Application): AsyncTask<Void, Void, Void>(){
        override fun doInBackground(vararg params: Void?): Void? {
            var db = goalsDB.get(application)
            db.dataDao().deleteSmallGoals()
            var goals:List<Goal> = db.dataDao().getAllSmallGoals()
            for(x in goals){
                Log.i("HabitName",x.habitName)
                Log.i("Name",x.name)
                Log.i("Date",x.date)
                Log.i("Category",x.category)
            }
            return null
        }

    }

    class InsertSmallGoal(val habitName:String,
                        val smallGoalName:String,
                        val date:String,
                          val category:String,
                        val application: Application,
    ): AsyncTask<Void, Void, Void>(){
        override fun doInBackground(vararg params: Void?): Void? {
            var db = goalsDB.get(application)
            db.dataDao().insertSmallGoal(Goal(habitName,smallGoalName,date,category))
            var goals:List<Goal> = db.dataDao().getAllSmallGoals()
            for(x in goals){
                Log.i("HabitName",x.habitName)
                Log.i("Name",x.name)
                Log.i("Date",x.date)
                Log.i("Category",x.category)
            }

            return null
        }
    }

}
