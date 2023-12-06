package com.example.znaniya

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog


class matemtest : AppCompatActivity() {

    private val questions = listOf(
        Question("Чему равна производная функции f(x) = 3x^2 - 2x + 1?", listOf("a) 6x - 2", "b) 6x + 2", "c) 6x - 1", "d) 6x + 1"), 0),
        Question("Чему равен интеграл функции f(x) = 2x^3 + 3x^2 - 4x + 1?", listOf("a) x^4 + x^3 - 2x^2 + x + C", "b) x^4 + x^3 - 2x^2 + C", "c) x^4 + x^3 - 2x + C", "d) x^4 + x^3 + C"), 0),
        Question("Чему равно значение функции f(x) = log(1000) при x = 10?", listOf("a) 1", "b) 2", "c) 3", "d) 4"), 2),
        Question("Чему равно значение выражения sin(π/2)?", listOf("a) 0", "b) 1", "c) -1", "d) 2"), 1),
        Question("Чему равно значение выражения cos(0)?", listOf("a) 0", "b) 1", "c) -1", "d) 2"), 1),
        Question("Чему равно значение выражения e^0?", listOf("a) 0", "b) 1", "c) -1", "d) 2"), 1),
        Question("Чему равно значение выражения ln(1)?", listOf("a) 0", "b) 1", "c) -1", "d) 2"), 0),
        Question("Какой метод используется для решения системы линейных уравнений?", listOf("a) Метод Эйлера", "b) Метод Ньютона", "c) Метод Гаусса", "d) Метод Рунге-Кутты"), 2),
        Question("Что такое производная функции?", listOf("a) Площадь под графиком функции", "b) Скорость изменения функции", "c) Интеграл функции", "d) Максимальное значение функции"), 1),
        Question("Что такое интеграл функции?", listOf("a) Площадь под графиком функции", "b) Скорость изменения функции", "c) Производная функции", "d) Максимальное значение функции"), 0),
        // Добавьте остальные вопросы с вариантами ответов и правильными ответами
    )

    private var currentQuestionIndex = 0
    private var correctAnswersCount = 0

    private lateinit var questionTextView: TextView
    private lateinit var option1Button: Button
    private lateinit var option2Button: Button
    private lateinit var option3Button: Button
    private lateinit var option4Button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.navigationBarColor = resources.getColor(R.color.navigation_bar_color)
        }
        val button7: Button = findViewById(R.id.button4)
        button7.setOnClickListener {
            // Создание диалогового окна
            val alertDialogBuilder = AlertDialog.Builder(this, R.style.AlertDialogTheme)
            alertDialogBuilder.setTitle("Подтверждение")
            alertDialogBuilder.setMessage("Вы уверены, что хотите отказаться от прохождения теста?\nРезультаты теста не будут сохранены, если вы откажетесь.")

            // Обработка нажатия кнопки "Да"
            alertDialogBuilder.setPositiveButton("Отказаться") { dialogInterface: DialogInterface, i: Int ->
                // Создание намерения для перехода на другое активити
                val intent = Intent(this, testlist1::class.java)
                startActivity(intent)
            }

            // Обработка нажатия кнопки "Нет"
            alertDialogBuilder.setNegativeButton("Продолжить тест") { dialogInterface: DialogInterface, i: Int ->
                // Здесь можно добавить код для отмены перехода или выполнения других действий
            }

            // Отображение диалогового окна
            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
            alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE)?.setTextColor(Color.parseColor("#6ec29f"))
            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)?.setTextColor(Color.parseColor("#c4314c"))
        }
        questionTextView = findViewById(R.id.questionTextView1)
        option1Button = findViewById(R.id.option1Button1)
        option2Button = findViewById(R.id.option2Button1)
        option3Button = findViewById(R.id.option3Button1)
        option4Button = findViewById(R.id.option4Button1)

        setQuestion()

        option1Button.setOnClickListener {
            checkAnswer(0)
        }

        option2Button.setOnClickListener {
            checkAnswer(1)
        }

        option3Button.setOnClickListener {
            checkAnswer(2)
        }

        option4Button.setOnClickListener {
            checkAnswer(3)
        }
    }

    private fun setQuestion() {
        val currentQuestion = questions[currentQuestionIndex]
        questionTextView.text = currentQuestion.questionText
        option1Button.text = currentQuestion.options[0]
        option2Button.text = currentQuestion.options[1]
        option3Button.text = currentQuestion.options[2]
        option4Button.text = currentQuestion.options[3]
    }

    private fun checkAnswer(selectedAnswerIndex: Int) {
        val currentQuestion = questions[currentQuestionIndex]
        if (selectedAnswerIndex == currentQuestion.correctAnswerIndex) {
            correctAnswersCount++
        }

        currentQuestionIndex++
        if (currentQuestionIndex < questions.size) {
            setQuestion()
        } else {
            val grade = calculateGrade()
            showResult(grade)
        }
    }

    private fun calculateGrade(): Int {
        val totalQuestions = questions.size
        val percentage = (correctAnswersCount.toDouble() / totalQuestions) * 100
        return when {
            percentage >= 90 -> 5
            percentage >= 75 -> 4
            percentage >= 50 -> 3
            else -> 2
        }
    }

    override fun onBackPressed() {

    }

    private fun showResult(grade: Int) {
        val intent = Intent(this, resulttest1::class.java)
        intent.putExtra("correctAnswersCount", correctAnswersCount)
        intent.putExtra("grade", grade)

        // Получение строки из ресурсов и сохранение в SharedPreferences
        val resrusString = getString(R.string.resmatem)
        val sharedPreferences = getSharedPreferences("TestResults", MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putString("res", resrusString)
            putInt("correctAnswersCount", correctAnswersCount)
            putInt("grade", grade)
            apply()
        }

        // Добавление строки из ресурсов в Intent
        intent.putExtra("res", resrusString)

        startActivity(intent)
        finish()
    }

}

data class Question(val questionText: String, val options: List<String>, val correctAnswerIndex: Int)

