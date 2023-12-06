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


class webtest : AppCompatActivity() {
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
            "Какие технологии используются в веб-программировании?",
            listOf("HTML, CSS, JavaScript", "Java, C++, Python", "SQL, PHP, Ruby", "Swift, Kotlin, Objective-C"),
            0,
            "HTML, CSS, JavaScript"
        ),

        Question(
            "Что такое AJAX и как он применяется?",
            listOf("Новый язык программирования", "Метод обработки изображений", "Технология для асинхронных запросов", "Библиотека стилей"),
            2,
            "Технология для асинхронных запросов"
        ),

        Question(
            "Каким образом CSS обеспечивает стилизацию веб-страниц?",
            listOf("С помощью языка программирования", "Созданием сервера", "Определением структуры HTML", "Управлением внешним видом элементов"),
            3,
            "Управлением внешним видом элементов"
        ),

        Question(
            "Какую роль выполняет JavaScript на веб-страницах?",
            listOf("Определение структуры документа", "Добавление стилей к элементам", "Создание интерактивности", "Хранение данных в базе данных"),
            2,
            "Создание интерактивности"
        ),

        Question(
            "Что такое RESTful API, и как он применяется в веб-программировании?",
            listOf("Язык программирования", "Способ взаимодействия с веб-сервисами", "Библиотека для работы с базой данных", "Метод стилизации веб-страниц"),
            1,
            "Способ взаимодействия с веб-сервисами"
        ),

        Question(
            "Какие преимущества несет использование фреймворков в веб-программировании?",
            listOf("Уменьшение безопасности", "Упрощение разработки", "Отсутствие необходимости в CSS", "Увеличение сложности кода"),
            1,
            "Упрощение разработки"
        ),

        Question(
            "Что такое SQL, и как он используется в веб-программировании?",
            listOf("Язык запросов к базе данных", "Метод оформления HTML", "Инструмент для создания стилей", "Среда разработки веб-приложений"),
            0,
            "Язык запросов к базе данных"
        ),

        Question(
            "Каким образом веб-сервер взаимодействует с веб-приложением?",
            listOf("Путем передачи данных в браузер", "Через AJAX-запросы", "С помощью RESTful API", "Обработкой HTTP-запросов и отправкой HTTP-ответов"),
            3,
            "Обработкой HTTP-запросов и отправкой HTTP-ответов"
        ),

        Question(
            "Каким образом управляются состояния сессии в веб-программировании?",
            listOf("Через параметры URL", "С использованием JavaScript", "С помощью cookies", "Методом передачи данных через AJAX"),
            2,
            "С помощью cookies"
        ),

        Question(
            "Что такое MVC, и как он применяется в веб-программировании?",
            listOf("Модель веб-камеры", "Архитектурный шаблон", "Метод обработки событий", "Библиотека для работы с изображениями"),
            1,
            "Архитектурный шаблон"
        ),

        Question(
            "Каким образом в веб-программировании реализуется безопасность?",
            listOf("С помощью графических библиотек", "Через использование стилей", "С применением протокола HTTPS", "С использованием анимации"),
            2,
            "С применением протокола HTTPS"
        ),

        Question(
            "Какие инструменты используются для отладки веб-приложений?",
            listOf("Карандаш и бумага", "Инструменты разработчика браузера", "Музыкальные инструменты", "Программы для создания стилей"),
            1,
            "Инструменты разработчика браузера"
        ),

        Question(
            "Каким образом в веб-программировании реализуется кэширование?",
            listOf("С использованием графических файлов", "Через применение AJAX-запросов", "С помощью сохранения данных на сервере", "Хранением копий данных для быстрого доступа"),
            3,
            "Хранением копий данных для быстрого доступа"
        ),

        Question(
            "Как происходит взаимодействие клиента и сервера в Single Page Application?",
            listOf("С помощью периодического обновления страницы", "Через использование фреймворков", "С применением RESTful API", "С обработкой каждого запроса на сервер"),
            2,
            "С применением RESTful API"
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
                val intent = Intent(this, testlist4::class.java)
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
        val intent = Intent(this, resulttest4::class.java)
        intent.putExtra("correctAnswersCount", correctAnswersCount)
        intent.putExtra("grade", grade)

        // Получение строки из ресурсов и сохранение в SharedPreferences
        val resrusString = getString(R.string.resweb)
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