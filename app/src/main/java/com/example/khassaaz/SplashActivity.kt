package com.example.khassaaz

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.HandlerCompat
import com.example.khassaaz.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Load fade-in animation
        val fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        val slideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up)
        val scaleUp = AnimationUtils.loadAnimation(this, R.anim.scale_up) // optional
        binding.logoImage.startAnimation(fadeIn)
        binding.appName.startAnimation(fadeIn)

        // When fade-in ends, trigger slide-up or scale
        fadeIn.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {
                binding.logoImage.startAnimation(slideUp)
                binding.appName.startAnimation(slideUp)
                // binding.logoImage.startAnimation(scaleUp) // If using scale instead
            }
            override fun onAnimationRepeat(animation: Animation) {}
        })
        // Wait 2 seconds then launch MainActivity
        HandlerCompat.createAsync(mainLooper).postDelayed({
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 5000)
    }
}
