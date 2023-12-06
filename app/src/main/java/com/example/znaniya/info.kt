package com.example.znaniya

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class info : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_info)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.navigationBarColor = resources.getColor(R.color.navigation_bar_color)
            }
            val nextButton: Button = findViewById(R.id.button2)
            nextButton.setOnClickListener {
                val intent = Intent(this@info, main::class.java)
                startActivity(intent)
                finish()
            }
        }
    override fun onBackPressed() {

    }
    }

