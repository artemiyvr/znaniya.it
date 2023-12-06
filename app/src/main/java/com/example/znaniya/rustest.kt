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


class rustest : AppCompatActivity() {
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
            "В каком из слов правильно стоит ударение?",
            listOf("Дóговор", "Жáлюзи", "Звóнит", "Кварта́л"),
            3,
            "Кварта́л"
        ),
        Question(
            "Какой из перечисленных слов является союзом?",
            listOf("И", "Быстро", "Стол", "Молоко"),
            0,
            "И"
        ),
        Question(
            "В каком из слов правильно стоит ударение?",
            listOf("Сливóвый", "Жáлюзи", "Щавéль", "Квáртал"),
            3,
            "Кварта́л"
        ),
        Question(
            "Как называется явление, при котором слова имеют одинаковое начало?",
            listOf("Аллитерация", "Ассонанс", "Антитеза", "Парадокс"),
            0,
            "Аллитерация"
        ),
        Question(
            "Что такое глагол?",
            listOf("Слова с одинаковым значением", "Слова с противоположным значением", "Слова с разными значениями", "Слова, обозначающие действие"),
            3,
            "Слова, обозначающие действие"
        ),

        Question(
            "Как называется группа слов, связанных по смыслу, но не являющихся предложением?",
            listOf("Синтаксическая конструкция", "Словосочетание", "Параграф", "Абзац"),
            1,
            "Словосочетание"
        ),
        Question(
            "Что такое причастие?",
            listOf("Форма глагола", "Вид наречия", "Способ прилагательного", "Временная форма существительного"),
            2,
            "Способ прилагательного"
        ),
        Question(
            "Как называется явление, при котором слова имеют одинаковый корень?",
            listOf("Антитеза", "Ассонанс", "Омонимия", "Паронимия"),
            2,
            "Омонимия"
        ),
        Question(
            "Где правильно стоит запятая?",
            listOf("Между подлежащим и сказуемым", "В конце предложения", "Перед союзом и после него", "Перед каждым словом"),
            2,
            "Перед союзом и после него"
        ),
        Question(
            "Как называется часть речи, обозначающая качество предмета?",
            listOf("Глагол", "Прилагательное", "Существительное", "Местоимение"),
            1,
            "Прилагательное"
        ),
        Question(
            "Что такое деепричастие?",
            listOf("Форма глагола", "Вид наречия", "Способ прилагательного", "Временная форма существительного"),
            0,
            "Форма глагола"
        ),
        Question(
            "Какое из приложений правильное?",
            listOf("Я, сегодня вкусно поел и пошел гулять", "Я сегодня вкусно поел, и пошел гулять", "Я сегодня, вкусно поел и пошел гулять", "Я сегодня вкусно поел и пошел гулять"),
            3,
            "Я сегодня вкусно поел и пошел гулять"
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
        val intent = Intent(this, resulttest1::class.java)
        intent.putExtra("correctAnswersCount", correctAnswersCount)
        intent.putExtra("grade", grade)

        // Получение строки из ресурсов и сохранение в SharedPreferences
        val resrusString = getString(R.string.resrus)
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




