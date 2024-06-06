package com.arcearista.rafael.poketinder

import SharedPreferencesRepository
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.util.Patterns
import com.arcearista.rafael.poketinder.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var sharedPreferencesRepository: SharedPreferencesRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferencesRepository = SharedPreferencesRepository().also {
            it.setSharedPreference(this)
        }

        binding.btnRegister.setOnClickListener { registerUser() }
        binding.btnBackClose.setOnClickListener { finish() }
        binding.loginUser.setOnClickListener { startActivity(Intent(this, LoginActivity::class.java)) }
    }

    private fun registerUser() {
        val email = binding.edtEmail.text.toString()
        val password = binding.edtPassword.text.toString()
        val confirmPassword = binding.edtPassword2.text.toString()

        if (!isValidEmail(email)) {
            Toast.makeText(this, "Correo electrónico inválido", Toast.LENGTH_SHORT).show()
            return
        }

        if (password != confirmPassword) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            return
        }

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Ingrese todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        sharedPreferencesRepository.saveUserEmail(email)
        sharedPreferencesRepository.saveUserPassword(password)
        Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
        finish()
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
