package com.example.znaniya

import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class resulttest1 : AppCompatActivity() {

    private lateinit var correctAnswersCountTextView: TextView
    private lateinit var gradeTextView: TextView
    private lateinit var resrusTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resulttest)
        resrusTextView = findViewById(R.id.textView5)

        // Установка текста в TextView
        val sharedPreferences: SharedPreferences = getSharedPreferences("TestResults", MODE_PRIVATE)
        val resrusString = sharedPreferences.getString("res", getString(R.string.resrus))
        resrusTextView.text = resrusString

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.navigationBarColor = resources.getColor(R.color.navigation_bar_color)
        }

        correctAnswersCountTextView = findViewById(R.id.correctAnswersTextView)
        gradeTextView = findViewById(R.id.gradeTextView)

        // Получение результатов из SharedPreferences
        val correctAnswersCount = sharedPreferences.getInt("correctAnswersCount", 0)
        val grade = sharedPreferences.getInt("grade", 0)

        // Если результаты в SharedPreferences не найдены, получаем их из Intent
        if (correctAnswersCount == 0 && grade == 0) {
            val intentCorrectAnswersCount = intent.getIntExtra("correctAnswersCount", 0)
            val intentGrade = intent.getIntExtra("grade", 0)

            // Сохранение результатов в SharedPreferences
            with(sharedPreferences.edit()) {
                putString("res", resrusString)
                putInt("correctAnswersCount", intentCorrectAnswersCount)
                putInt("grade", intentGrade)
                apply()
            }

            // Используем результаты из Intent
            correctAnswersCountTextView.text = "Количество верных ответов: $intentCorrectAnswersCount"
            gradeTextView.text = "Оценка: $intentGrade"
        } else {
            // Используем результаты из SharedPreferences
            correctAnswersCountTextView.text = "Количество верных ответов: $correctAnswersCount"
            gradeTextView.text = "Оценка: $grade"
        }

        val otherActivityButton: Button = findViewById(R.id.loginButton2)

        otherActivityButton.setOnClickListener {
            val intent = Intent(this, testlist1::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        // Обработка нажатия кнопки "Назад"
    }
}
