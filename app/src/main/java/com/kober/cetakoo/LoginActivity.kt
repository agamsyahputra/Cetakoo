package com.kober.cetakoo

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)
        button_register.setOnClickListener {
            val intent = Intent(this, SignActivity::class.java)
            startActivity(intent)
        }
        sign_button.setOnClickListener { _ ->
            val email = editText.text.toString()
            val password = editText2.text.toString()
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter text in email/pw", Toast.LENGTH_SHORT).show()
            }

FirebaseAuth.getInstance()
        .signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                        val intent = Intent(this, HomeActivity::class.java)
                        startActivity(intent) }
                        else
                            Toast.makeText(this, "Failed to log in, please check your email & password", Toast.LENGTH_SHORT).show()
                    }



        }

    }
}