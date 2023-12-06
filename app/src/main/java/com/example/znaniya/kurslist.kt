package com.example.znaniya

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class kurslist : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kurslist)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.navigationBarColor = resources.getColor(R.color.navigation_bar_color)
        }
        val nextButton1: Button = findViewById(R.id.matembutton2)
        nextButton1.setOnClickListener {
            // Создаем Intent для перехода на следующую активность
            val intent = Intent(this@kurslist, testlist1::class.java)
            startActivity(intent)
            finish()
        }
        val nextButtonnn1: Button = findViewById(R.id.matembutton3)
        nextButtonnn1.setOnClickListener {
            // Создаем Intent для перехода на следующую активность
            val intent = Intent(this@kurslist, testlist2::class.java)
            startActivity(intent)
            finish()
        }
        val nextButtonnnn1: Button = findViewById(R.id.matembutton4)
        nextButtonnnn1.setOnClickListener {
            // Создаем Intent для перехода на следующую активность
            val intent = Intent(this@kurslist, testlist3::class.java)
            startActivity(intent)
            finish()
        }
        val nextButtonnnnn1: Button = findViewById(R.id.matembutton5)
        nextButtonnnnn1.setOnClickListener {
            // Создаем Intent для перехода на следующую активность
            val intent = Intent(this@kurslist, testlist4::class.java)
            startActivity(intent)
            finish()
        }
        val nextButtonn1: Button = findViewById(R.id.button7)
        nextButtonn1.setOnClickListener {
            val intent = Intent(this@kurslist, main::class.java)
            startActivity(intent)
            finish()
        }
        val exitButton: Button = findViewById(R.id.exitButton3)
        exitButton.setOnClickListener {
            finishAffinity()
        }
    }
    override fun onBackPressed() {
    }
}