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


class kottest : AppCompatActivity() {
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
            "Что такое Kotlin?",
            listOf("Язык программирования", "Фреймворк", "База данных", "Веб-браузер"),
            0,
            "Язык программирования"
        ),

        Question(
            "Как создать переменную в Kotlin?",
            listOf("variable x = 10", "val x: Int = 10", "let x = 10", "const x = 10"),
            1,
            "val x: Int = 10"
        ),

        Question(
            "Что такое 'nullable' тип в Kotlin?",
            listOf("Тип данных, который не может быть изменен", "Тип данных, который может принимать значение null", "Тип данных, доступный только для чтения", "Тип данных, который требует явного приведения типов"),
            1,
            "Тип данных, который может принимать значение null"
        ),

        Question(
            "Как объявить функцию в Kotlin?",
            listOf("function add(a: Int, b: Int): Int { return a + b }", "def add(a: Int, b: Int): Int = { a + b }", "fun add(a: Int, b: Int): Int { a + b }", "method add(a: Int, b: Int): Int = { return a + b }"),
            0,
            "function add(a: Int, b: Int): Int { return a + b }"
        ),

        Question(
            "Что такое 'Lambda-выражение' в Kotlin?",
            listOf("Тип данных для хранения символов", "Короткая анонимная функция", "Оператор для создания циклов", "Специальный тип переменной"),
            1,
            "Короткая анонимная функция"
        ),

        Question(
            "Какой модификатор доступа по умолчанию используется для членов класса в Kotlin?",
            listOf("private", "protected", "public", "internal"),
            2,
            "public"
        ),

        Question(
            "Что такое 'smart cast' в Kotlin?",
            listOf("Автоматическое приведение типов в определенных условиях", "Ручное приведение типов", "Запрет приведения типов", "Автоматическое приведение типов во всех случаях"),
            0,
            "Автоматическое приведение типов в определенных условиях"
        ),

        Question(
            "Как создать экземпляр класса в Kotlin?",
            listOf("new MyClass()", "create MyClass", "val obj = MyClass()", "init MyClass"),
            2,
            "val obj = MyClass()"
        ),

        Question(
            "Какой оператор используется для безопасного вызова в Kotlin?",
            listOf("?", "!!", ":", "::", "&&"),
            0,
            "?"
        ),

        Question(
            "Что такое 'data class' в Kotlin?",
            listOf("Класс для работы с базой данных", "Класс для хранения только данных", "Класс с закрытыми членами", "Класс с поддержкой наследования"),
            1,
            "Класс для хранения только данных"
        ),

        Question(
            "Какой цикл используется для перебора элементов в коллекции в Kotlin?",
            listOf("for", "while", "repeat", "foreach"),
            3,
            "foreach"
        ),

        Question(
            "Что такое 'coroutine' в Kotlin?",
            listOf("Многозадачный процесс", "Объект для работы с файлами", "Легковесный поток выполнения", "Механизм для обработки ошибок"),
            2,
            "Легковесный поток выполнения"
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
                val intent = Intent(this, testlist3::class.java)
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
        val intent = Intent(this, resulttest3::class.java)
        intent.putExtra("correctAnswersCount", correctAnswersCount)
        intent.putExtra("grade", grade)

        // Получение строки из ресурсов и сохранение в SharedPreferences
        val resrusString = getString(R.string.reskot)
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
        timer = object : CountDownTimer(5 * 60 * 1000, 1000) {
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