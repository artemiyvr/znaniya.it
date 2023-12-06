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


class arhtest : AppCompatActivity() {
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
            "Что такое архитектура компьютера?",
            listOf("Организация компьютерных программ", "Структура железа компьютера", "Язык программирования", "Алгоритмы"),
            0,
            "Организация компьютерных программ"
        ),

        Question(
            "Какие компоненты входят в структуру центрального процессора?",
            listOf("RAM", "ALU", "GPU", "Hard Drive"),
            1,
            "ALU"
        ),

        Question(
            "Что представляет собой оперативная память (RAM) в компьютерной архитектуре?",
            listOf("Долговременное хранение данных", "Кэш-память", "Временное хранение данных", "Резервное копирование"),
            1,
            "Кэш-память"
        ),

        Question(
            "Каким образом происходит обмен данными между ЦП и оперативной памятью?",
            listOf("Через системную шину", "Через USB-порты", "По беспроводной связи", "Через HDMI-порты"),
            0,
            "Через системную шину"
        ),

        Question(
            "Что такое машинный код в контексте компьютерной архитектуры?",
            listOf("Читаемый человеком код", "Набор инструкций, понятных процессору", "Код для веб-приложений", "Язык высокого уровня"),
            1,
            "Набор инструкций, понятных процессору"
        ),

        Question(
            "Какие типы памяти используются в компьютерной архитектуре?",
            listOf("Только оперативная память", "Только внешний накопитель", "Оперативная и постоянная память", "Только кэш-память"),
            2,
            "Оперативная и постоянная память"
        ),

        Question(
            "Что такое системная шина в компьютерной архитектуре?",
            listOf("Шина в метро", "Путь для передачи данных между компонентами", "Программа для защиты от вирусов", "Экран монитора"),
            1,
            "Путь для передачи данных между компонентами"
        ),

        Question(
            "Каким образом происходит чтение данных с жесткого диска?",
            listOf("Через процессор", "Через оперативную память", "Через системную шину", "Через внешний порт"),
            2,
            "Через системную шину"
        ),

        Question(
            "Что такое кэш-память и как она используется в компьютерной архитектуре?",
            listOf("Память для хранения фотографий", "Быстрая временная память, близкая к процессору", "Хранилище долговременных данных", "Оперативная память для графики"),
            1,
            "Быстрая временная память, близкая к процессору"
        ),

        Question(
            "Каким образом процессор выполняет инструкции программы?",
            listOf("Случайным образом", "Последовательно, по одной инструкции за раз", "С пропусками и перескоками", "Только в обратном порядке"),
            1,
            "Последовательно, по одной инструкции за раз"
        ),

        Question(
            "Что такое системное программное обеспечение в компьютерной архитектуре?",
            listOf("Программы для офисной работы", "Программы для графического дизайна", "Программы для управления ресурсами компьютера", "Программы для создания музыки"),
            2,
            "Программы для управления ресурсами компьютера"
        ),

        Question(
            "Какие функции выполняет операционная система в компьютерной архитектуре?",
            listOf("Управление ресурсами компьютера, обеспечение безопасности, обеспечение интерфейса пользователя", "Создание веб-сайтов, обработка изображений, воспроизведение музыки", "Редактирование текстовых документов, создание презентаций, обработка таблиц", "Анализ данных, создание отчётов, разработка алгоритмов"),
            0,
            "Управление ресурсами компьютера, обеспечение безопасности, обеспечение интерфейса пользователя"
        ),

        Question(
            "Какие компоненты входят в состав моста в компьютерной архитектуре?",
            listOf("Процессор и оперативная память", "Микропроцессор и графическая карта", "Процессор и жесткий диск", "Контроллер ввода-вывода и системная шина"),
            3,
            "Контроллер ввода-вывода и системная шина"
        ),

        Question(
            "Каким образом работает многозадачность в компьютерной архитектуре?",
            listOf("Выполнение одной задачи за раз", "Выполнение нескольких задач одновременно", "Выполнение задачи с пропусками", "Выполнение задачи задом наперёд"),
            1,
            "Выполнение нескольких задач одновременно"
        ),

        Question(
            "Что такое периферийные устройства в компьютерной архитектуре?",
            listOf("Устройства, встроенные в ядро процессора", "Устройства, подключенные к системной шине", "Устройства для хранения данных", "Устройства для охлаждения компьютера"),
            1,
            "Устройства, подключенные к системной шине"
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
        val resrusString = getString(R.string.resarh)
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