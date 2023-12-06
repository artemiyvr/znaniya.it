package com.example.znaniya

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView

// Ваша текущая активность
class testlist2 : AppCompatActivity() {
    private lateinit var textView: TextView
    private lateinit var button: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_testlist)

        textView = findViewById(R.id.textView)
        val geoinform1 = resources.getString(R.string.second)
        textView.text = geoinform1

        button = findViewById(R.id.matembutton)
        val name11 = resources.getString(R.string.alg)
        button.text = name11

        button2 = findViewById(R.id.russianButton)
        val name2 = resources.getString(R.string.arh)
        button2.text = name2

         button3 = findViewById(R.id.pascalButton)
         val name3 = resources.getString(R.string.cplus)
         button3.text = name3

        button4 = findViewById(R.id.engButton)
        val name4 = resources.getString(R.string.eng2)
        button4.text = name4

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.navigationBarColor = resources.getColor(R.color.navigation_bar_color)
        }
        val nextButton: Button = findViewById(R.id.matembutton)
        nextButton.setOnClickListener {
            // Создаем Intent для перехода на следующую активность
            val intent = Intent(this@testlist2, alginfo::class.java)
            startActivity(intent)
            finish()

        }
          val nextButton1: Button = findViewById(R.id.russianButton)
           nextButton1.setOnClickListener {
               // Создаем Intent для перехода на следующую активность
               val intent = Intent(this@testlist2, arhinfo::class.java)
               startActivity(intent)
               finish()
           }
          val nextButton2: Button = findViewById(R.id.pascalButton)
         nextButton2.setOnClickListener {
             // Создаем Intent для перехода на следующую активность
             val intent = Intent(this@testlist2, cplusinfo::class.java)
             startActivity(intent)
             finish()
         }
                 val engButton: Button = findViewById(R.id.engButton)
         engButton.setOnClickListener {
             // Создаем Intent для перехода на следующую активность
             val intent = Intent(this@testlist2, enginfo2::class.java)
             startActivity(intent)
             finish()

         }
        val Button3: Button = findViewById(R.id.button3)
        Button3.setOnClickListener {
            // Создаем Intent для перехода на следующую активность
            val intent = Intent(this@testlist2, kurslist::class.java)
            startActivity(intent)
            finish()
        }


        val exitButton: Button = findViewById(R.id.exitButton)
        exitButton.setOnClickListener {
            finishAffinity()
        }

        val buttonn: Button = findViewById(R.id.button6)
        buttonn.setOnClickListener {
            // Создаем Intent для перехода на следующую активность
            val intent = Intent(this@testlist2, resulttest2::class.java)
            startActivity(intent)
            finish()
        }



    }
    override fun onBackPressed() {
    }
}





