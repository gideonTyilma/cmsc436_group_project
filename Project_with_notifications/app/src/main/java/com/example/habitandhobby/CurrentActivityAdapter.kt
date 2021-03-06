package com.example.habitandhobby

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.annotation.RequiresApi

class CurrentActivityAdapter(private val mContext: Context):BaseAdapter() {
    private val mItems = ArrayList<CurrentActivity>()
    private val inf = LayoutInflater.from(this.mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        // TODO - Get the current category
        val curr = mItems[position]

        val newView: View

        if(null == convertView) {
            newView = inf.inflate(R.layout.current_item,parent,false)

        } else {
//            viewHolder = convertView.tag as ViewHolder
//            viewHolder.mStatusView!!.setOnCheckedChangeListener(null)
            newView = convertView

        }


        // TODO - Display Title in TextView
        val cat = newView.findViewById(R.id.category) as TextView
        cat.text = "Habit Category: "+curr.category
        val goalName = newView.findViewById(R.id.goalName) as TextView
        goalName.text = "Goal Name: "+curr.goal
        val habitName = newView.findViewById(R.id.habitname) as TextView
        habitName.text = "Habit You are Developing: "+curr.name


        return newView

    }

    override fun getItem(position: Int): CurrentActivity {
        return mItems[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return mItems.size
    }

    fun add(item:CurrentActivity){
        mItems.add(item)
        notifyDataSetChanged()
    }

    fun clear(){
        mItems.clear()
        notifyDataSetChanged()
    }
    //    need a delete last
    fun deleteLast(){
        if(mItems.isNotEmpty()){
            mItems.removeAt(mItems.size-1)
            notifyDataSetChanged()
        }

    }
    fun getLast():CurrentActivity?{
        if(mItems.isNotEmpty()){
           return mItems[mItems.size-1]

        }
        return null
    }
}