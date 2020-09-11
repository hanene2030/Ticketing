package com.android.kotlin.ticketing

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class SplashScreenActivity : AppCompatActivity() {
    lateinit var handler : Handler
    lateinit var textSplash :TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        textSplash = findViewById(R.id.textSplash) as TextView
        var myAnim : Animation = AnimationUtils.loadAnimation(this,R.anim.splashtransition)
        textSplash.startAnimation(myAnim)

        handler = Handler()
       handler.postDelayed({
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()
        } , 2000)

    }
}
