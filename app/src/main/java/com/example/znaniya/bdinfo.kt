package com.example.znaniya

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class bdinfo : AppCompatActivity() {
    private lateinit var textView: TextView
    private lateinit var textView1: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infotest)

        textView = findViewById(R.id.textView8)
        val bdinform = resources.getString(R.string.bdinform)
        textView.text = bdinform

        textView1 = findViewById(R.id.textView7)
        val bdname = resources.getString(R.string.bdname)
        textView1.text = bdname

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.navigationBarColor = resources.getColor(R.color.navigation_bar_color)
        }
        val nextButtonnn: Button = findViewById(R.id.testButton)
        nextButtonnn.setOnClickListener {
            val intent = Intent(this@bdinfo, bdtest::class.java)
            startActivity(intent)
            finish()
        }
        val nextButton1: Button = findViewById(R.id.testlistbutton1)
        nextButton1.setOnClickListener {
            val intent = Intent(this@bdinfo, testlist3::class.java)
            startActivity(intent)
            finish()
        }
    }
    override fun onBackPressed() {

    }
}
