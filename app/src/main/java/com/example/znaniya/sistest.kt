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


class sistest : AppCompatActivity() {
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
            "Что представляет собой процесс 'внедрения компьютерной системы'?",
            listOf("Проектирование аппаратного обеспечения", "Внедрение программного обеспечения в существующую среду", "Разработка компьютерных игр", "Создание нового языка программирования"),
            1,
            "Внедрение программного обеспечения в существующую среду"
        ),

        Question(
            "Каковы основные этапы внедрения компьютерной системы?",
            listOf("Только разработка", "Разработка, тестирование, внедрение, поддержка", "Только внедрение", "Разработка и внедрение"),
            1,
            "Разработка, тестирование, внедрение, поддержка"
        ),

        Question(
            "Что такое 'баг-трекинг' в контексте поддержки компьютерных систем?",
            listOf("Процесс создания программных ошибок", "Методика программирования", "Система отслеживания и устранения программных ошибок", "Модель разработки программного обеспечения"),
            2,
            "Система отслеживания и устранения программных ошибок"
        ),

        Question(
            "Каким образом происходит тестирование компьютерной системы?",
            listOf("После внедрения", "Только до внедрения", "Только во время разработки", "Всегда выполняется в конце жизненного цикла"),
            0,
            "После внедрения"
        ),

        Question(
            "Что такое 'rollback' в контексте внедрения компьютерной системы?",
            listOf("Вернуться к предыдущей версии системы", "Повторно выполнить процесс внедрения", "Изменение конфигурации системы", "Удаление всех багов"),
            0,
            "Вернуться к предыдущей версии системы"
        ),

        Question(
            "Каким образом обеспечивается безопасность при внедрении компьютерной системы?",
            listOf("Скрытие всех багов", "Применение антивирусного программного обеспечения", "Шифрование данных", "Все вышеуказанные варианты"),
            3,
            "Все вышеуказанные варианты"
        ),

        Question(
            "Что включает в себя поддержка компьютерной системы?",
            listOf("Только обучение пользователей", "Обеспечение бесперебойной работы, устранение ошибок, обучение пользователей", "Только обеспечение бесперебойной работы", "Обеспечение безопасности системы"),
            1,
            "Обеспечение бесперебойной работы, устранение ошибок, обучение пользователей"
        ),

        Question(
            "Какие меры предпринимаются для устранения ошибок после внедрения комп. системы?",
            listOf("Просто оставить ошибки", "Отменить внедрение", "Разработать новую систему", "Использовать баг-трекинг и выпустить исправления"),
            3,
            "Использовать баг-трекинг и выпустить исправления"
        ),

        Question(
            "Что означает термин 'масштабирование' в контексте внедрения компьютерной системы?",
            listOf("Изменение цветовой схемы интерфейса", "Увеличение размера шрифта", "Разработка системы для обработки больших объемов данных", "Смена языка интерфейса"),
            2,
            "Разработка системы для обработки больших объемов данных"
        ),

        Question(
            "Что представляет собой 'автоматизированное тестирование'?",
            listOf("Тестирование проводится только вручную", "Тестирование, проводимое с использованием автоматических инструментов", "Тестирование, проводимое с помощью роботов", "Тестирование, проводимое в изоляции от других процессов"),
            1,
            "Тестирование, проводимое с использованием автоматических инструментов"
        ),

        Question(
            "Каким образом выполняется резервное копирование компьютерной системы?",
            listOf("Только вручную", "Только автоматически", "Комбинированным методом: вручную и автоматически", "Резервное копирование не выполняется"),
            2,
            "Комбинированным методом: вручную и автоматически"
        ),

        Question(
            "Что такое 'мониторинг производительности' в контексте поддержки компьютерной системы?",
            listOf("Только отслеживание использования ресурсов", "Отслеживание и анализ производительности системы", "Только анализ кода программы", "Изменение настроек системы"),
            1,
            "Отслеживание и анализ производительности системы"
        ),

        Question(
            "Каким образом обеспечивается безопасность при внедрении компьютерной системы?",
            listOf("Скрытие всех багов", "Применение антивирусного программного обеспечения", "Шифрование данных", "Все вышеуказанные варианты"),
            3,
            "Все вышеуказанные варианты"
        ),
        Question(
            "Какие методы используются для обучения пользователей?",
            listOf("Только печатные инструкции", "Только вебинары и видеоуроки", "Комбинированный подход: печатные инструкции, вебинары, видеоуроки", "Обучение не предусмотрено"),
            2,
            "Комбинированный подход"
        ),

        Question(
            "Что такое 'SLA'?",
            listOf("Секретный ключ доступа", "Служба личной аутентификации", "Соглашение об уровне обслуживания", "Система логирования активности"),
            2,
            "Соглашение об уровне обслуживания"
        ),

        Question(
            "Каким образом выполняется аудит безопасности компьютерной системы?",
            listOf("Только вручную", "Только с использованием автоматических инструментов", "Комбинированным методом: вручную и с использованием автоматических инструментов", "Аудит безопасности не проводится"),
            2,
            "Комбинированным методом"
        ),

        Question(
            "Каким образом обеспечивается конфиденциальность данных?",
            listOf("Только через использование сложных паролей", "Только с использованием шифрования данных", "Сочетание различных методов: сложные пароли, шифрование данных, ограничение доступа", "Конфиденциальность данных не обеспечивается"),
            2,
            "Сочетание различных методов"
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
        val resrusString = getString(R.string.ressis)
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