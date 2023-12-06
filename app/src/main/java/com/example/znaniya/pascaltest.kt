package com.example.znaniya
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class pascaltest : AppCompatActivity() {
    data class Question(
        val questionText: String,
        val options: List<String>,
        val correctAnswerIndex: Int,
        val correctAnswerText: String
    )
    private val questions = listOf(
        Question(
            "Что такое переменная в программировании?",
            listOf("Число", "Команда", "Контейнер для хранения данных", "Условие"),
            2,
            "Контейнер для хранения данных"
        ),

        Question(
            "Какие типы данных могут быть в языке программирования Pascal?",
            listOf("Только целочисленные", "Только вещественные", "Целочисленные и вещественные", "Только символьные"),
            2,
            "Целочисленные и вещественные"
        ),

        Question(
            "Что такое цикл в программировании?",
            listOf("Событие", "Условие", "Контейнер для данных", "Повторяющаяся конструкция"),
            3,
            "Повторяющаяся конструкция"
        ),

        Question(
            "Как объявить массив в Pascal?",
            listOf("Array[1..10] of Integer", "List<Integer>", "int[] array = new int[10]", "ArrayList<Integer>"),
            0,
            "Array[1..10] of Integer"
        ),

        Question(
            "Что делает оператор 'if' в Pascal?",
            listOf("Цикл", "Условие", "Оператор вывода", "Массив"),
            1,
            "Условие"
        ),

        Question(
            "Как осуществить ввод данных с клавиатуры в Pascal?",
            listOf("readln()", "cin >>", "Console.ReadLine()", "input()"),
            0,
            "readln()"
        ),

        Question(
            "Что такое процедура в Pascal?",
            listOf("Условие", "Цикл", "Фрагмент кода, выполняющий определенную задачу", "Переменная"),
            2,
            "Фрагмент кода, выполняющий определенную задачу"
        ),

        Question(
            "Как объявить функцию в Pascal?",
            listOf("method", "Function", "def", "procedure"),
            1,
            "Function"
        ),

        Question(
            "Какой оператор используется для цикла 'for' в Pascal?",
            listOf("for (int i = 0; i < 10; i++)", "foreach", "while", "for i := 1 to 10 do"),
            3,
            "for i := 1 to 10 do"
        ),

        Question(
            "Что такое рекурсия в программировании?",
            listOf("Способ ввода данных", "Функция, вызывающая саму себя", "Массив", "Условие"),
            1,
            "Функция, вызывающая саму себя"
        ),

        Question(
            "Как завершить выполнение программы в Pascal?",
            listOf("break", "return", "exit", "halt"),
            3,
            "halt"
        ),

        Question(
            "Как создать условие 'если-иначе' в Pascal?",
            listOf("if-else", "switch", "case", "elif"),
            0,
            "if-else"
        ),
        Question(
                "Как объявить целочисленную переменную в Pascal?",
        listOf("integer x", "int x", "x : Integer", "var x: int"),
        2,
        "x : Integer"
        ),

        Question(
        "Как осуществить вывод данных на экран в Pascal?",
         listOf("print()", "Console.WriteLine()", "cout <<", "writeln()"),
    3,
    "writeln()"
    ),

        Question(
    "Что такое оператор 'while' в Pascal?",
    listOf("Цикл", "Условие", "Оператор ввода", "Массив"),
    0,
    "Цикл"
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

    @RequiresApi(Build.VERSION_CODES.O)
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
        val resrusString = getString(R.string.respascal)
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




