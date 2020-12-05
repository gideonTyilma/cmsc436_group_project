package com.example.habitandhobby

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val health = findViewById<Button>(R.id.Health)
        health.setOnClickListener {
            val intent = Intent(this, Health::class.java)
            startActivity(intent)

        }
        val hobbies = findViewById<Button>(R.id.Hobbies)
        hobbies.setOnClickListener {
            val intent = Intent(this, Hobbies::class.java)
            startActivity(intent)
        }
        val chores = findViewById<Button>(R.id.Chores)
        chores.setOnClickListener {
            val intent = Intent(this, Chores::class.java)
            startActivity(intent)
        }
        val school = findViewById<Button>(R.id.School)
        school.setOnClickListener {
            val intent = Intent(this, School::class.java)
            startActivity(intent)
        }
        val current = findViewById<Button>(R.id.Current)
        current.setOnClickListener {
            val intent = Intent(this, Current::class.java)
            startActivity(intent)
        }
        val achievement = findViewById<Button>(R.id.Achievement)
        achievement.setOnClickListener {
            val intent = Intent(this, Achievement::class.java)
            startActivity(intent)
        }
    }
    public override fun onStart() {
        super.onStart()



    }
    public override fun onResume() {
        super.onResume()


    }
    public override fun onPause() {
        super.onPause()


    }

    public override fun onStop() {
        super.onStop()



    }
    public override fun onRestart() {
        super.onRestart()




    }

    public override fun onDestroy() {
        super.onDestroy()


    }




}