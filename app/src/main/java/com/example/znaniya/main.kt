package com.example.znaniya

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class main : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.navigationBarColor = resources.getColor(R.color.navigation_bar_color)
        }
        // Получение ссылок на элементы пользовательского интерфейса
        val otherActivityButton: Button = findViewById(R.id.button5)
        // Обработчик нажатия на кнопку для перехода на другую активность
        otherActivityButton.setOnClickListener {
            val intent = Intent(this, info::class.java)
            startActivity(intent)
            finish()
        }
        val starttestbutton: Button = findViewById(R.id.starttestbutton)
        // Обработчик нажатия на кнопку для перехода на другую активность
        starttestbutton.setOnClickListener {
            val intent = Intent(this, kurslist::class.java)
            startActivity(intent)
            finish()
        }
        val exitButton: Button = findViewById(R.id.exitButton2)
        exitButton.setOnClickListener {
            finishAffinity()
        }

    }
    override fun onBackPressed() {
    }
}
