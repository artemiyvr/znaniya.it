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


class ciftest : AppCompatActivity() {
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
            "Что представляет собой цифровая технология?",
            listOf("Современный вид искусства", "Технический прогресс в космонавтике", "Использование цифровых сигналов", "Метод разведки национальных границ"),
            2,
            "Использование цифровых сигналов"
        ),

        Question(
            "Как цифровые технологии применяются в современных мобильных устройствах?",
            listOf("Только для звонков", "Для передачи аналоговых сигналов", "В качестве основы для работы приложений", "Для управления дизайном корпуса"),
            2,
            "В качестве основы для работы приложений"
        ),

        Question(
            "Что такое искусственный интеллект, и как он применяется в цифровых технологиях?",
            listOf("Область изобразительного искусства", "Способность машины к обучению и выполнению задач", "Исследование истории искусства", "Метод создания виртуальной реальности"),
            1,
            "Способность машины к обучению и выполнению задач"
        ),

        Question(
            "Какие функции выполняют цифровые сенсоры в технике?",
            listOf("Создание музыки", "Обнаружение физических свойств окружающей среды", "Управление космическими полетами", "Измерение температуры человеческого тела"),
            1,
            "Обнаружение физических свойств окружающей среды"
        ),

        Question(
            "Каким образом цифровые технологии влияют на развитие медицины?",
            listOf("Только в создании цифровых рентгеновских снимков", "Созданием виртуальных больниц", "Автоматизацией лабораторных исследований", "Только в разработке цифровых медицинских карт"),
            2,
            "Автоматизацией лабораторных исследований"
        ),

        Question(
            "Что представляют собой блокчейн-технологии?",
            listOf("Способ изготовления строительных блоков", "Метод обработки цифровых фотографий", "Технология децентрализованных распределенных баз данных", "Создание архитектурных чертежей зданий"),
            2,
            "Технология децентрализованных распределенных баз данных"
        ),

        Question(
            "Каким образом цифровые технологии используются в сфере образования?",
            listOf("Только в качестве замены учебников", "Для создания виртуальных уроков", "Только в сфере дистанционного обучения", "Созданием цифровых красок и кистей"),
            1,
            "Для создания виртуальных уроков"
        ),

        Question(
            "Что такое Интернет вещей (IoT), и какие примеры его применения?",
            listOf("Глобальная компьютерная сеть для обмена фотографиями", "Способность предметов быть подключенными к интернету", "Создание виртуальных миров", "Метод организации онлайн-конференций"),
            1,
            "Способность предметов быть подключенными к интернету"
        ),

        Question(
            "Каким образом цифровые технологии влияют на развитие транспорта?",
            listOf("Только в создании цифровых карт городов", "Автоматизацией управления транспортными средствами", "Только в создании цифровых маршрутов", "Созданием цифровых транспортных билетов"),
            1,
            "Автоматизацией управления транспортными средствами"
        ),

        Question(
            "Что представляют собой виртуальная и дополненная реальность?",
            listOf("Только форматы аудиофайлов", "Способы управления компьютерами", "Технологии для создания цифровых образов", "Создание симуляции виртуальных миров и добавление информации к реальному миру"),
            3,
            "Создание симуляции виртуальных миров и добавление информации к реальному миру"
        ),

        Question(
            "Каким образом цифровые технологии применяются в сфере искусства?",
            listOf("Только для создания цифровых копий картин", "Автоматизацией процесса создания скульптур", "Созданием компьютерных программ для рисования", "Только для хранения цифровых фотографий"),
            2,
            "Созданием компьютерных программ для рисования"
        ),

        Question(
            "Что такое кибербезопасность, и почему она важна в цифровых технологиях?",
            listOf("Только защита компьютерных игр", "Метод создания шифрованных сообщений", "Обеспечение безопасности в сети Интернет", "Только защита от вирусов"),
            2,
            "Обеспечение безопасности в сети Интернет"
        ),

        Question(
            "Каким образом цифровые технологии применяются в сфере бизнеса?",
            listOf("Только для хранения бухгалтерской отчетности", "Автоматизацией бизнес-процессов", "Только для создания цифровых визиток", "Созданием виртуальных офисов"),
            1,
            "Автоматизацией бизнес-процессов"
        ),

        Question(
            "Что такое Big Data, и какие задачи решает в цифровых технологиях?",
            listOf("Технология создания больших баз данных", "Область программирования", "Обработка и анализ больших объемов данных", "Только создание больших интернет-сервисов"),
            2,
            "Обработка и анализ больших объемов данных"
        ),

        Question(
            "Каким образом цифровые технологии применяются в области развлечений и игр?",
            listOf("Только для создания цифровых книг", "Созданием виртуальных миров и компьютерных игр", "Только для анимации фильмов", "Автоматизацией работы кинотеатров"),
            1,
            "Созданием виртуальных миров и компьютерных игр"
        ),

        Question(
            "Что представляет собой технология 5G, и какое влияние она оказывает на связь и интернет?",
            listOf("Только технология создания 5D-фильмов", "Пятая версия языка программирования", "Технология беспроводной связи следующего поколения", "Пятая генерация компьютерных игр"),
            2,
            "Технология беспроводной связи следующего поколения"
        ),

        Question(
            "Каким образом цифровые технологии применяются в сфере научных исследований?",
            listOf("Только для создания цифровых архивов", "Созданием программ для просмотра звездного неба", "Автоматизацией процесса проведения экспериментов", "Только для анализа старых научных статей"),
            2,
            "Автоматизацией процесса проведения экспериментов"
        ),

        Question(
            "Что такое квантовые вычисления, и какие перспективы они открывают?",
            listOf("Создание квантовых фотоаппаратов", "Использование квантовых компьютеров для обработки данных", "Технология создания квантовых сетей", "Только теоретическое направление в физике"),
            1,
            "Использование квантовых компьютеров для обработки данных"
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
        val resrusString = getString(R.string.rescif)
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