package com.example.thebreakingbadapp.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.thebreakingbadapp.R
import com.example.thebreakingbadapp.ui.MainActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed(
            { startActivity(Intent(this@SplashActivity, MainActivity::class.java)) },
            3000L
        )
    }
}