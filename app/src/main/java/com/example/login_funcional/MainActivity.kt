package com.example.login_funcional

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import android.view.View
import android.widget.EditText
import android.text.method.PasswordTransformationMethod



class MainActivity : AppCompatActivity() {
    private lateinit var nextScreenButton: Button

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nextScreenButton = findViewById(R.id.login_btn)
        nextScreenButton.setOnClickListener {
            val intent = Intent(this, registro::class.java)
            startActivity(intent)
        }


        emailEditText = findViewById(R.id.email_et)
        passwordEditText = findViewById(R.id.password_et)
        loginButton = findViewById(R.id.log_btn)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (isValidCredentials(email, password)) {
                val intent = Intent(this, home::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Credenciales inválidas", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isValidCredentials(email: String, password: String): Boolean {
        // Aquí puedes implementar la lógica de validación de credenciales
        val validEmail = "correo@example.com"
        val validPassword = "contraseña123"

        return email == validEmail && password == validPassword
    }
    fun applyPasswordTransformation(editText: EditText) {
        editText.transformationMethod = PasswordTransformationMethod.getInstance()
    }
}
