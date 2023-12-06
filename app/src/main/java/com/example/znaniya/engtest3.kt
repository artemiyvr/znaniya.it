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


class engtest3 : AppCompatActivity() {
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
            "Какая временная форма: 'She has been studying English for five years'?",
            listOf("Present Simple", "Past Simple", "Present Perfect Continuous", "Past Perfect"),
            2,
            "Present Perfect Continuous"
        ),

        Question(
            "Какие из перечисленных слов являются модальными глаголами?",
            listOf("Run, Swim, Jump", "Can, Must, Should", "Book, Table, Chair", "Happy, Sad, Excited"),
            1,
            "Can, Must, Should"
        ),

        Question(
            "Как правильно завершить предложение: 'If I ___ the answer, I would tell you'?",
            listOf("know", "knows", "known", "knew"),
            3,
            "knew"
        ),

        Question(
            "Что означает выражение 'hit the books'?",
            listOf("Быть в центре внимания", "Изучать учебники", "Быть очень занятым", "Иметь хорошие отношения"),
            1,
            "Изучать учебники"
        ),

        Question(
            "Какой вариант перевода на русский язык корректен для фразы 'break the ice'?",
            listOf("Разбить лед", "Пройти испытание", "Пересечь границу", "Провести эксперимент"),
            0,
            "Разбить лед"
        ),

        Question(
            "Как называется изменение формы слова в зависимости от его функции в предложении?",
            listOf("Сложение", "Согласование", "Интонация", "Склонение"),
            3,
            "Склонение"
        ),

        Question(
            "Какие из перечисленных слов являются неопределенными местоимениями?",
            listOf("This, That, These", "Some, Any, Many", "Red, Blue, Green", "Big, Small, Tall"),
            1,
            "Some, Any, Many"
        ),

        Question(
            "Как правильно поставить вопрос в Past Simple?",
            listOf("Did you goes to the store yesterday?", "Were you went to the store yesterday?", "Do you went to the store yesterday?", "Did you go to the store yesterday?"),
            3,
            "Did you go to the store yesterday?"
        ),

        Question(
            "Какой из вариантов является правильным вопросительным предложением?",
            listOf("He didn't like the movie?", "Did he liked the movie?", "Did he like the movie?", "He liked the movie?"),
            2,
            "Did he like the movie?"
        ),

        Question(
            "Как перевести выражение 'to hit the hay' на русский язык?",
            listOf("Попасть в сложное положение", "Попасть в аварию", "Ложиться спать", "Бросить вызов"),
            2,
            "Ложиться спать"
        ),

        Question(
            "Какие из перечисленных слов являются союзами?",
            listOf("Fast, Slow, Quick", "And, But, Or", "Happy, Sad, Excited", "Book, Table, Chair"),
            1,
            "And, But, Or"
        ),

        Question(
            "Как перевести на русский язык выражение 'to be over the moon'?",
            listOf("Быть в центре внимания", "Быть в полном восторге", "Закончить работу", "Попасть в беду"),
            1,
            "Быть в полном восторге"
        ),

        Question(
            "Какой вариант перевода на русский язык корректен для фразы 'a piece of cake'?",
            listOf("Кусок торта", "Легкое задание", "Кусок мяса", "Сложное испытание"),
            0,
            "Кусок торта"
        ),

        Question(
            "Как правильно использовать 'used to' в предложении?",
            listOf("I'm used to eat sushi every day.", "I'm used eating sushi every day.", "I used eat sushi every day.", "I used to eat sushi every day."),
            3,
            "I used to eat sushi every day."
        ),

        Question(
            "Как перевести на русский язык фразу 'to turn a blind eye'?",
            listOf("Открывать третий глаз", "Закрыть глаза", "Повернуть слепое око", "Отвернуть взгляд"),
            1,
            "Закрыть глаза"
        ),

        Question(
            "Что используется в приложении: 'I will be traveling to Paris next week'?",
            listOf("Future Simple", "Future Continuous", "Future Perfect", "Future Perfect Continuous"),
            1,
            "Future Continuous"
        ),

        Question(
            "Какой вариант перевода на русский язык корректен для фразы 'to go the extra mile'?",
            listOf("Пройти дополнительную милю", "Пойти в магазин", "Пройти километр", "Сделать дополнительное усилие"),
            3,
            "Сделать дополнительное усилие"
        ),

        Question(
            "Какой вид 'conditionals' используется в предложении: 'If I had known, I would have come'?",
            listOf("Zero Conditional", "First Conditional", "Second Conditional", "Third Conditional"),
            3,
            "Third Conditional"
        ),

        Question(
            "Как перевести на русский язык фразу 'to cost an arm and a leg'?",
            listOf("Стоить копейки", "Стоить целое состояние", "Стоить дешево", "Стоить руку и ногу"),
            1,
            "Стоить целое состояние"
        ),

        Question(
            "Какие из перечисленных слов являются синонимами слова 'amazing'?",
            listOf("Boring, Dull, Tedious", "Wonderful, Incredible, Marvelous", "Angry, Furious, Irritated", "Calm, Serene, Peaceful"),
            1,
            "Wonderful, Incredible, Marvelous"
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
        val resrusString = getString(R.string.reseng3)
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