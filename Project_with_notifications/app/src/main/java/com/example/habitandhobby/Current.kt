package com.example.habitandhobby

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ListView

//list of current activities which are stored somewhere somehow
class Current : Activity(){
    private lateinit var listView: ListView
    private lateinit var mAdapter: CurrentActivityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.current)

        listView = findViewById(R.id.list_of_activity)
        mAdapter = CurrentActivityAdapter(applicationContext)
        listView.adapter = mAdapter


        var list:ArrayList<CurrentActivity> = ArrayList<CurrentActivity>()
        var pop = Populate(this.application, list)
        pop.execute()
        Thread.sleep(1000)
        mAdapter.clear()
        for(x in list){
            mAdapter.add(x)
            Log.i("Populted with: ",x.name)
        }

        listView.setOnItemClickListener{
                parent, view, position, id ->
                var curr:CurrentActivity? = mAdapter.getItem(position)
                if(curr!=null){
                    var intent = Intent(this, SmallGoals::class.java)
                    intent.putExtra("name",curr.name)
                    intent.putExtra("cat",curr.category)
                    startActivity(intent)
                }
        }

        findViewById<Button>(R.id.clear).setOnClickListener{
            mAdapter.clear()
            var clr = Clear(this.application)
            clr.execute()
        }

        findViewById<Button>(R.id.delete).setOnClickListener{
            var curr = mAdapter.getLast()
            var habitName:String = ""
            if(curr!=null){
                habitName = curr.name
            }
            mAdapter.deleteLast()
            var del = Delete(this.application,habitName)
            del.execute()
        }


    }
}

class Clear(val application: Application): AsyncTask<Void, Void, Void>(){
    override fun doInBackground(vararg params: Void?): Void? {
        var db = goalsDB.get(application)
        db.dataDao().deleteCurrent()
        db.dataDao().deleteSmallGoals()


        return null
    }

}


class Delete(val application: Application,val habitName:String): AsyncTask<Void, Void, Void>(){
    override fun doInBackground(vararg params: Void?): Void? {
        var db = goalsDB.get(application)
        db.dataDao().deleteLastCurrent()
        db.dataDao().deleteGoalsByHabitName(habitName)

        return null
    }

}

class Populate(val application: Application, var list:ArrayList<CurrentActivity>): AsyncTask<Void, Void, Void>(){
    override fun doInBackground(vararg params: Void?): Void? {
        var db = goalsDB.get(application)
        var li:List<CurrentActivity> = db.dataDao().getAllCurr()

        for(x in li){
            list.add(x)
        }

        return null
    }


}

