package com.example.znaniya
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.content.DialogInterface
import android.graphics.Color
import androidx.appcompat.app.AlertDialog


class cplustest : AppCompatActivity() {
    private fun saveTestResults() {
        val sharedPreferences = getSharedPreferences("TestResults", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("correctAnswersCount", correctAnswersCount)
        editor.putInt("grade", calculateGrade())
        editor.apply()
    }

    data class Question(
        val questionText: String,
        val options: List<String>,
        val correctAnswerIndex: Int,
        val correctAnswerText: String
    )
    private val questions = listOf(
        Question(
            "Что такое язык программирования C++?",
            listOf("Язык разметки документов", "Язык программирования для создания веб-сайтов", "Язык программирования высокого уровня", "Язык ассемблера"),
            2,
            "Язык программирования высокого уровня"
        ),

        Question(
            "Как объявить переменную в C++?",
            listOf("var x: int", "let x", "int x", "x = 5"),
            2,
            "int x"
        ),

        Question(
            "Что такое оператор в C++?",
            listOf("Условие", "Специальный символ", "Последовательность шагов", "Операция, выполняемая над переменными"),
            3,
            "Операция, выполняемая над переменными"
        ),

        Question(
            "Какой оператор используется для выполнения условий в C++?",
            listOf("if-else", "for", "switch", "while"),
            0,
            "if-else"
        ),

        Question(
            "Что такое функция в C++?",
            listOf("Переменная", "Группа операторов, выполняющих определенную задачу", "Тип данных", "Массив"),
            1,
            "Группа операторов, выполняющих определенную задачу"
        ),

        Question(
            "Как передать значение в функцию в C++?",
            listOf("Через системную шину", "Через внешний порт", "Через параметры функции", "Через оперативную память"),
            2,
            "Через параметры функции"
        ),

        Question(
            "Что такое указатель в C++?",
            listOf("Специальная конструкция для условий", "Переменная, хранящая адрес ячейки памяти", "Оператор для циклических действий", "Тип данных для хранения текста"),
            1,
            "Переменная, хранящая адрес ячейки памяти"
        ),

        Question(
            "Что такое массив в C++?",
            listOf("Группа операторов", "Оператор сравнения", "Условие", "Набор однотипных данных"),
            3,
            "Набор однотипных данных"
        ),

        Question(
            "Как осуществить ввод данных с клавиатуры в C++?",
            listOf("input()", "readLine()", "cin >>", "scanf()"),
            2,
            "cin >>"
        ),

        Question(
            "Что представляет собой оператор условия в C++?",
            listOf("Оператор вывода", "Оператор ввода", "Оператор сравнения", "Оператор выбора"),
            3,
            "Оператор выбора"
        ),

        Question(
            "Как объявить константу в C++?",
            listOf("const x = 5", "let x", "final int x", "constant x = 5"),
            0,
            "const x = 5"
        ),

        Question(
            "Что такое цикл в C++?",
            listOf("Событие", "Условие", "Последовательность шагов", "Переменная"),
            2,
            "Последовательность шагов"
        ),

        Question(
            "Какие основные типы данных существуют в C++?",
            listOf("Integer, Float, Boolean", "int, double, bool", "String, Character, Array", "Только текстовые данные"),
            1,
            "int, double, bool"
        ),

        Question(
            "Что такое оператор switch в C++?",
            listOf("Оператор для включения компьютера", "Оператор для выполнения цикла", "Оператор для множественного выбора", "Оператор для вывода данных"),
            2,
            "Оператор для множественного выбора"
        ),

        Question(
            "Какие библиотеки используются для ввода-вывода в C++?",
            listOf("stdio.h", "inputoutput.lib", "iostream", "io.h"),
            2,
            "iostream"
        )
    )


    private var currentQuestionIndex = 0
    private var correctAnswersCount = 0
    private var timeRemaining = 0L

    private lateinit var questionTextView: TextView
    private lateinit var option1Button: Button
    private lateinit var option2Button: Button
    private lateinit var option3Button: Button
    private lateinit var option4Button: Button

    private lateinit var timer: CountDownTimer
    private lateinit var timerTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.navigationBarColor = resources.getColor(R.color.navigation_bar_color)
        }
        val button4: Button = findViewById(R.id.button4)
        button4.setOnClickListener {
            // Создание диалогового окна
            val alertDialogBuilder = AlertDialog.Builder(this, R.style.AlertDialogTheme)
            alertDialogBuilder.setTitle("Подтверждение")
            alertDialogBuilder.setMessage("Вы уверены, что хотите отказаться от прохождения теста?\nРезультаты теста не будут сохранены, если вы откажетесь.")

            // Обработка нажатия кнопки "Да"
            alertDialogBuilder.setPositiveButton("Отказаться") { dialogInterface: DialogInterface, i: Int ->
                // Создание намерения для перехода на другое активити
                val intent = Intent(this, testlist2::class.java)
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
        timerTextView = findViewById(R.id.timerTextView1)

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

        startTimer()
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
            stopTimer()
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
        val intent = Intent(this, resulttest2::class.java)
        intent.putExtra("correctAnswersCount", correctAnswersCount)
        intent.putExtra("grade", grade)

        // Получение строки из ресурсов и сохранение в SharedPreferences
        val resrusString = getString(R.string.rescplus)
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




    private fun startTimer() {
        timer = object : CountDownTimer(10 * 60 * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeRemaining = millisUntilFinished / 1000
                val minutes = timeRemaining / 60
                val seconds = timeRemaining % 60
                timerTextView.text = String.format("%02d:%02d", minutes, seconds)
            }

            override fun onFinish() {
                stopTimer()
                val grade = calculateGrade()
                showResult(grade)
            }
        }.start()

    }

    private fun stopTimer() {
        timer.cancel()
    }
}