package com.arcearista.rafael.poketinder

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.arcearista.rafael.poketinder.databinding.ActivitySpashBinding
import com.rommansabbir.animationx.Attention
import com.rommansabbir.animationx.animationXAttention

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySpashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imvLogo.animationXAttention(Attention.ATTENTION_SHAKE)
        runPostDelayed()
    }

    private fun runPostDelayed() {
        Handler(Looper.getMainLooper()).postDelayed({
            goLoginActivity()
        }, 3000)
    }

    private fun goLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
