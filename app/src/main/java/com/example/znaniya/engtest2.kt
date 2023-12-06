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


class engtest2 : AppCompatActivity() {
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
            "What is the verb 'to be' in the present simple for 'I' in English?",
            listOf("am", "is", "are", "be"),
            0,
            "am"
        ),


        Question(
            "Which of the following is a preposition?",
            listOf("Run", "On", "Jump", "Sing"),
            1,
            "On"
        ),

        Question(
            "What is the plural form of 'child'?",
            listOf("Childs", "Childrens", "Childes", "Children"),
            3,
            "Children"
        ),

        Question(
            "How do you ask someone's name in English?",
            listOf("What is your name?", "How are you?", "Where are you from?", "What do you do?"),
            0,
            "What is your name?"
        ),

        Question(
            "Which sentence is in the past simple tense?",
            listOf("I am going to the store.", "She is studying for the exam.", "They played football yesterday.", "We will visit the museum."),
            2,
            "They played football yesterday."
        ),

        Question(
            "What is the opposite of 'big'?",
            listOf("Small", "Tall", "Fat", "Short"),
            0,
            "Small"
        ),

        Question(
            "How many continents are there in the world?",
            listOf("Four", "Six", "Seven", "Eight"),
            2,
            "Seven"
        ),

        Question(
            "What is the past participle of 'eat'?",
            listOf("Ate", "Eaten", "Eating", "Eats"),
            1,
            "Eaten"
        ),

        Question(
            "Which of the following is a modal verb?",
            listOf("Go", "Can", "Play", "Read"),
            1,
            "Can"
        ),

        Question(
            "What is the correct order of the days of the week?",
            listOf("Monday, Wednesday, Friday", "Tuesday, Thursday, Saturday", "Sunday, Monday, Wednesday", "Friday, Sunday, Tuesday"),
            2,
            "Sunday, Monday, Wednesday"
        ),

        Question(
            "Which tense is used to talk about plans for the future?",
            listOf("Present simple", "Past simple", "Future simple", "Present continuous"),
            2,
            "Future simple"
        ),

        Question(
            "What is the plural of 'man'?",
            listOf("Mans", "Mens", "Men", "Manes"),
            2,
            "Men"
        ),

        Question(
            "How do you spell the word 'Красивый'?",
            listOf("Beutiful", "Beautyful", "Buetiful", "Beautiful"),
            3,
            "Beautiful"
        ),

        Question(
            "What is the comparative form of 'happy'?",
            listOf("Happiest", "Happier", "Happyer", "More happy"),
            1,
            "Happier"
        ),

        Question(
            "Which question is correct?",
            listOf("What you do?", "Where you from?", "How are you?", "Who you are?"),
            2,
            "How are you?"
        ),

        Question(
            "What does the word 'book' mean?",
            listOf("Food", "Clothing", "Building", "Written or printed work"),
            3,
            "Written or printed work"
        ),

        Question(
            "Which of the following is a conjunction?",
            listOf("Run", "But", "Sing", "Jump"),
            1,
            "But"
        ),

        Question(
            "What is the past simple of 'go'?",
            listOf("Goes", "Gone", "Went", "Goed"),
            2,
            "Went"
        ),

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
        val resrusString = getString(R.string.reseng2)
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