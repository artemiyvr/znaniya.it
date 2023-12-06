package com.example.znaniya

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class enginfo1 : AppCompatActivity() {
    private lateinit var textView: TextView
    private lateinit var textView1: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infotest)

        textView = findViewById(R.id.textView8)
        val geoinform = resources.getString(R.string.enginform)
        textView.text = geoinform

        textView1 = findViewById(R.id.textView7)
        val geoinform1 = resources.getString(R.string.engname)
        textView1.text = geoinform1

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.navigationBarColor = resources.getColor(R.color.navigation_bar_color)
        }
        val nextButtonnn: Button = findViewById(R.id.testButton)
        nextButtonnn.setOnClickListener {
            val intent = Intent(this@enginfo1, engtest1::class.java)
            startActivity(intent)
            finish()
        }
        val nextButton1: Button = findViewById(R.id.testlistbutton1)
        nextButton1.setOnClickListener {
            val intent = Intent(this@enginfo1, testlist1::class.java)
            startActivity(intent)
            finish()
        }
    }
    override fun onBackPressed() {

    }
    }
