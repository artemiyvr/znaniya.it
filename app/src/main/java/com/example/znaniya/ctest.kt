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


class ctest : AppCompatActivity() {
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
            "Что такое 1C-Программирование?",
            listOf("Язык программирования", "Семейство бизнес-приложений", "Методика разработки веб-приложений", "Технология криптографии"),
            1,
            "Семейство бизнес-приложений"
        ),

        Question(
            "Какие средства разработки используются в 1C-Программировании?",
            listOf("IntelliJ IDEA", "Eclipse", "1C:Enterprise Development Tools", "Visual Studio Code"),
            2,
            "1C:Enterprise Development Tools"
        ),

        Question(
            "Каким образом можно создать новую конфигурацию?",
            listOf("Через командную строку", "Только вручную, правка конфигурационных файлов", "С помощью конструктора конфигураций", "Используя встроенный текстовый редактор"),
            2,
            "С помощью конструктора конфигураций"
        ),

        Question(
            "Что такое объект?",
            listOf("Целочисленная переменная", "Экземпляр класса", "Массив данных", "Таблица в базе данных"),
            1,
            "Экземпляр класса"
        ),

        Question(
            "Каким образом осуществляется взаимодействие между объектами в 1C?",
            listOf("Только через явное указание ссылок", "С использованием интерфейсов", "Через глобальные переменные", "Только через наследование"),
            1,
            "С использованием интерфейсов"
        ),

        Question(
            "Какие типы данных поддерживает 1C-Программирование?",
            listOf("Только целочисленные и строковые", "Целочисленные, строковые и даты", "Только вещественные", "Логические, текстовые и числовые"),
            2,
            "Целочисленные, строковые и даты"
        ),

        Question(
            "Каким образом можно организовать цикл в 1C?",
            listOf("Только с помощью оператора 'for'", "Только с помощью оператора 'while'", "Используя операторы 'for' и 'while'", "Циклы не поддерживаются"),
            2,
            "Используя операторы 'for' и 'while'"
        ),

        Question(
            "Что такое форма в 1C-Программировании?",
            listOf("Структура данных", "Объект, предназначенный для ввода данных", "Процедура обработки ошибок", "Способ организации кода"),
            1,
            "Объект, предназначенный для ввода данных"
        ),

        Question(
            "Каким образом можно организовать обработку событий в 1C?",
            listOf("Только с использованием обработчиков событий", "Только с помощью конструкции 'if-else'", "Используя триггеры базы данных", "Создавая отдельные потоки выполнения"),
            0,
            "Только с использованием обработчиков событий"
        ),

        Question(
            "Что представляет собой конфигурация в 1C-Программировании?",
            listOf("Модуль кода", "Структура базы данных", "Пакет документации", "Набор объектов и подсистем для решения бизнес-задач"),
            3,
            "Набор объектов и подсистем для решения бизнес-задач"
        ),

        Question(
            "Какие виды отчетов поддерживает 1C-Программирование?",
            listOf("Только табличные", "Только графические", "Табличные и графические", "Отчеты не поддерживаются"),
            2,
            "Табличные и графические"
        ),

        Question(
            "Каким образом можно организовать безопасность данных в 1C?",
            listOf("Только с использованием паролей", "С помощью резервного копирования", "Используя ролевую модель доступа", "Только с помощью шифрования"),
            2,
            "Используя ролевую модель доступа"
        ),

        Question(
            "Что такое информационные реквизиты в 1C?",
            listOf("Дополнительная информация о пользователе", "Поля, содержащие справочные данные", "Атрибуты объектов и справочников", "Только идентификационные номера"),
            2,
            "Атрибуты объектов и справочников"
        ),

        Question(
            "Каким образом можно осуществить многопоточное программирование в 1C?",
            listOf("Только с помощью явного создания потоков", "Только с использованием асинхронных запросов", "С использованием встроенных механизмов языка", "Многопоточность не поддерживается"),
            2,
            "С использованием встроенных механизмов языка"
        ),

        Question(
            "Что такое конфигуратор в 1C-Программировании?",
            listOf("Среда разработки", "Модуль тестирования", "Инструмент администрирования", "Графический редактор интерфейсов"),
            0,
            "Среда разработки"
        ),

        Question(
            "Каким образом можно осуществить взаимодействие с внешними системами в 1C?",
            listOf("Только через ручной ввод данных", "С использованием сторонних библиотек", "Только с использованием встроенных средств", "Созданием собственных сетевых протоколов"),
            2,
            "Только с использованием встроенных средств"
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
        val resrusString = getString(R.string.resc)
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
        timer = object : CountDownTimer(12 * 60 * 1000, 1000) {
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