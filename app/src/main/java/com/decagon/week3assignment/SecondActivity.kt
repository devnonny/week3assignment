package com.decagon.week3assignment

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)


        val inactive = intent.getStringExtra("INACTIVE")

        val secondTv = findViewById<TextView>(R.id.secondTv)
        secondTv.text = intent.getStringExtra("ACTIVE") ?: inactive
    }


    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}