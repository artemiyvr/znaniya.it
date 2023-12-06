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


class engtest4 : AppCompatActivity() {
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
            "What is the correct usage of the word 'ubiquitous'?",
            listOf("Easy to understand", "Present everywhere", "Highly skilled", "Quick and efficient"),
            1,
            "Present everywhere"
        ),

        Question(
            "Choose the correct form of the verb: 'She __ to the party last night.'",
            listOf("go", "goes", "gone", "went"),
            3,
            "went"
        ),

        Question(
            "Which sentence is grammatically correct?",
            listOf("He don't like coffee.", "She don't have time.", "They doesn't know the answer.", "I don't believe you."),
            3,
            "I don't believe you."
        ),

        Question(
            "What does the idiom 'hit the nail on the head' mean?",
            listOf("To hurt someone with a nail", "To criticize unfairly", "To understand exactly", "To avoid a difficult situation"),
            2,
            "To understand exactly"
        ),

        Question(
            "Choose the correct meaning of the word 'ephemeral'.",
            listOf("Lasting for a very short time", "Energetic and lively", "Easy to understand", "Having a strong impact"),
            0,
            "Lasting for a very short time"
        ),

        Question(
            "How would you complete this sentence: 'If I __ the answer, I would tell you.'?",
            listOf("know", "knows", "known", "knew"),
            3,
            "knew"
        ),

        Question(
            "In the fantastic concert; __, the food was delicious. What connector should fill the blank?",
            listOf("however", "but", "and", "therefore"),
            0,
            "however"
        ),

        Question(
            "Identify the correct passive form: 'They are building a new bridge.'",
            listOf("A new bridge has built.", "A new bridge is being built.", "A new bridge was built.", "Building a new bridge."),
            1,
            "A new bridge is being built."
        ),

        Question(
            "Choose the correct synonym for 'eloquent'.",
            listOf("Mute", "Articulate", "Awkward", "Incoherent"),
            1,
            "Articulate"
        ),

        Question(
            "Which sentence uses the conditional correctly?",
            listOf("If I will see him, I will say hello.", "If she would come earlier, we will catch the train.", "If it rains, I will stay at home.", "If he comes tomorrow, we can go to the beach."),
            3,
            "If he comes tomorrow, we can go to the beach."
        ),

        Question(
            "What is the correct preposition: 'She is afraid __ spiders.'",
            listOf("for", "of", "at", "with"),
            1,
            "of"
        ),

        Question(
            "Choose the correct meaning of the phrasal verb 'bring up'",
            listOf("To lift something heavy", "To mention a topic", "To cancel a plan", "To finish a project"),
            1,
            "To mention a topic"
        ),

        Question(
            "Identify the correct reported speech form: 'She said, 'I will come tomorrow'",
            listOf("She said that she will come tomorrow.", "She says she will come tomorrow.", "She said that she would come the next day.", "She says that she comes tomorrow."),
            2,
            "She said that she would come the next day."
        ),

        Question(
            "Which sentence is in the passive voice?",
            listOf("The cat chased the mouse.", "The mouse was chased by the cat.", "The mouse chased the cat.", "The cat and the mouse played together."),
            1,
            "The mouse was chased by the cat."
        ),

        Question(
            "Choose the correct modal verb: 'You __ finish your homework before watching TV.'",
            listOf("must", "should", "might", "can"),
            0,
            "must"
        ),

        Question(
            "What is the correct meaning of the expression 'jump on the bandwagon'?",
            listOf("To criticize unfairly", "To join others in doing something popular", "To avoid a difficult situation", "To be successful"),
            1,
            "To join others in doing something popular"
        ),

        Question(
            "Identify the correct meaning of the word 'copious'.",
            listOf("Scarce or insufficient", "Excessive or abundant", "Dull and boring", "Difficult to understand"),
            1,
            "Excessive or abundant"
        ),

        Question(
            "Choose the correct relative pronoun to complete the sentence: 'The woman __ son is a doctor.'",
            listOf("who", "which", "whom", "whose"),
            3,
            "whose"
        ),

        Question(
            "What is the correct form of the verb: 'He suggested that she __ the report.'",
            listOf("writing", "to write", "writes", "write"),
            3,
            "write"
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
        val resrusString = getString(R.string.reseng4)
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
        timer = object : CountDownTimer(15 * 60 * 1000, 1000) {
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