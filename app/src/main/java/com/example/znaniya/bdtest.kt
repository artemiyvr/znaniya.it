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


class bdtest : AppCompatActivity() {
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
            "Что представляет собой база данных?",
            listOf("Список данных", "Набор таблиц", "Система хранения и управления данными", "Структурированный текст"),
            2,
            "Система хранения и управления данными"
        ),

        Question(
            "Что такое таблица в контексте баз данных?",
            listOf("Документ Microsoft Excel", "Набор строк и столбцов для хранения данных", "Математическая формула", "Список ссылок"),
            1,
            "Набор строк и столбцов для хранения данных"
        ),

        Question(
            "Какой ключ уникально идентифицирует каждую запись в таблице?",
            listOf("Primary key", "Foreign key", "Unique key", "Composite key"),
            0,
            "Primary key"
        ),

        Question(
            "Что такое связь 'один к многим' в базе данных?",
            listOf("Каждая запись в одной таблице соответствует каждой записи в другой таблице", "Каждая запись в одной таблице соответствует нескольким записям в другой таблице", "Отсутствие связи между таблицами", "Все записи в одной таблице соответствуют всем записям в другой таблице"),
            1,
            "Каждая запись в одной таблице соответствует нескольким записям в другой таблице"
        ),

        Question(
            "Каково назначение индексов в базе данных?",
            listOf("Сортировка таблицы", "Обеспечение уникальности данных", "Ускорение поиска данных", "Ограничение доступа к таблице"),
            2,
            "Ускорение поиска данных"
        ),

        Question(
            "Что такое нормализация базы данных?",
            listOf("Процесс увеличения дублирования данных", "Процесс улучшения производительности базы данных", "Процесс уменьшения дублирования данных", "Процесс изменения структуры таблиц"),
            2,
            "Процесс уменьшения дублирования данных"
        ),

        Question(
            "Что означает термин 'ACID' в контексте баз данных?",
            listOf("Атомарность, Согласованность, Изолированность, Долговечность", "Абстракция, Компиляция, Интерпретация, Декомпиляция", "Активность, Стабильность, Интеграция, Динамичность", "Анимация, Сжатие, Изоляция, Декомпозиция"),
            0,
            "Атомарность, Согласованность, Изолированность, Долговечность"
        ),

        Question(
            "Тип связи, при котором каждая запись в одной таблице соответствует каждой записи в другой?",
            listOf("Один к одному", "Один к многим", "Многие ко многим", "Нет связи"),
            0,
            "Один к одному"
        ),

        Question(
            "Что такое 'транзакция' в базе данных?",
            listOf("Программа на языке программирования", "Логическая операция, состоящая из одного или нескольких SQL-запросов", "Таблица, содержащая данные", "Процесс администрирования базы данных"),
            1,
            "Логическая операция, состоящая из одного или нескольких SQL-запросов"
        ),

        Question(
            "Каким образом индекс помогает при выполнении запросов к базе данных?",
            listOf("Уменьшает размер таблицы", "Создает дополнительные копии данных", "Обеспечивает уникальность данных", "Ускоряет поиск данных"),
            3,
            "Ускоряет поиск данных"
        ),

        Question(
            "Какие основные принципы реляционной модели данных?",
            listOf("Абстракция, Инкапсуляция, Полиморфизм", "Агрегация, Композиция, Наследование", "Сущность, Атрибут, Связь", "Класс, Объект, Метод"),
            2,
            "Сущность, Атрибут, Связь"
        ),

        Question(
            "Что такое 'подзапрос' в SQL?",
            listOf("Обновление структуры таблицы", "Второстепенный запрос внутри основного запроса", "Процедура удаления данных", "Увеличение производительности запросов"),
            1,
            "Второстепенный запрос внутри основного запроса"
        ),

        Question(
            "Какие основные типы данных используются в базах данных?",
            listOf("Integer, Float, Boolean", "String, Character, Array", "int, double, bool", "Только текстовые данные"),
            1,
            "String, Character, Array"
        ),

        Question(
            "Что представляет собой 'группировка данных' в SQL?",
            listOf("Сортировка данных по алфавиту", "Объединение данных из разных таблиц", "Удаление дубликатов данных", "Разделение данных на группы по определенному признаку"),
            3,
            "Разделение данных на группы по определенному признаку"
        ),

        Question(
            "Какой оператор SQL используется для соединения данных из двух таблиц?",
            listOf("JOIN", "MERGE", "COMBINE", "CONNECT"),
            0,
            "JOIN"
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
        val resrusString = getString(R.string.resbd)
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
        timer = object : CountDownTimer(9 * 60 * 1000, 1000) {
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