package com.example.znaniya

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView

class loading : AppCompatActivity() {
    private val SPLASH_DELAY: Long = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)

        val imageView = findViewById<ImageView>(R.id.imageView22)

        val alphaAnimation = ObjectAnimator.ofFloat(imageView, "alpha", 0f, 1f)
        alphaAnimation.duration = 1500

        alphaAnimation.start()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.navigationBarColor = resources.getColor(R.color.navigation_bar_color)
        }
        Handler().postDelayed({

            val intent = Intent(this, main::class.java)
            startActivity(intent)
            finish()
        }, SPLASH_DELAY)

    }
}

