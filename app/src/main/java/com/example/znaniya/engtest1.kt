package com.example.znaniya
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class engtest1 : AppCompatActivity() {
    data class Question(
        val questionText: String,
        val options: List<String>,
        val correctAnswerIndex: Int,
        val correctAnswerText: String
    )

    private val questions = listOf(
        Question(
            "Как перевести на английский язык слово 'коричневый'?",
            listOf("Brown", "Black", "Blue", "Green"),
            0,
            "Brown"
        ),

                Question(
                "Сколько у тебя пальцев на руке?",
        listOf("Seven", "Ten", "Five", "Three"),
        2,
        "Five"
    ),

    Question(
    "Какой день недели идет после среды?",
    listOf("Saturday", "Friday", "Sunday", "Thursday"),
    3,
    "Thursday"
    ),

    Question(
    "Как перевести на английский язык слово 'красный'?",
    listOf("Yellow", "Red", "Blue", "Green"),
    1,
    "Red"
    ),

    Question(
    "Как перевести на английский слово 'город'?",
    listOf("Village", "Town", "Country", "City"),
    3,
    "City"
    ),
    Question(
    "Как сказать на английском 'я не знаю'?",
    listOf("I know", "I speak", "I don't know", "I understand"),
    2,
    "I don't know"
    ),
    Question(
    "Что такое 'книга' по-английски?",
    listOf("Computer", "Chair", "Table", "Book"),
    3,
    "Book"
    ),

    Question(
    "Как перевести на английский язык слово 'путешествие'?",
    listOf("Adventure", "Travel", "Journey", "Trip"),
    1,
    "Travel"
    ),

    Question(
    "Что означает 'завтрак' на английском языке?",
    listOf("Dinner", "Lunch", "Breakfast", "Supper"),
    2,
    "Breakfast"
    ),

    Question(
    "Как перевести на английский слово 'семья'?",
    listOf("Brother", "Relative", "Friend", "Family"),
    3,
    "Family"
    ),

    Question(
    "Что такое 'дом' на английском языке?",
    listOf("Building", "Room", "Apartment", "House"),
    3,
    "House"
    ),

    Question(
    "Как сказать 'я люблю читать' на английском языке?",
    listOf("I hate reading", "I prefer movies", "I don't like books", "I love to read"),
    3,
    "I love to read"
    ),

    Question(
    "Что такое 'работа' на английском языке?",
    listOf("Occupation", "Work", "Job", "Career"),
    1,
    "Work"
    ),

    Question(
    "Как перевести на английский слово 'зима'?",
    listOf("Autumn", "Summer", "Winter", "Spring"),
    2,
    "Winter"
    ),

    Question(
    "Какой сезон идет после лета?",
    listOf("Winter", "Spring", "Autumn", "Summer"),
    2,
    "Autumn"
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
        val resrusString = getString(R.string.reseng)
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
        timer = object : CountDownTimer(8 * 60 * 1000, 1000) {
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




