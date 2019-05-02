package com.kober.cetakoo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_sign.*

class SignActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)

        sign_button.setOnClickListener {
            performRegister()
        }

        button_login.setOnClickListener {

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

    }
    private fun performRegister() {
        val email = editText.text.toString()
        val password = editText2.text.toString()
        val name = editText3.text.toString()
        val mDatabase = FirebaseDatabase.getInstance()
        val mAuth = FirebaseAuth.getInstance()
        val mDatabaseReference = mDatabase.reference.child("Users")


        if (email.isEmpty() || password.isEmpty() || name.isEmpty()) {
            Toast.makeText(this, "Please enter text in email/pw/phone", Toast.LENGTH_SHORT).show()
            return
        }

                // Firebase Authentication to create a user with email and password
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        val userId = mAuth.currentUser!!.uid
                        val currentUserDb = mDatabaseReference.child(userId)
                        currentUserDb.child("Normal").child("Name").setValue(name)
                        Toast.makeText(this, "Successfully created user, please login to continue", Toast.LENGTH_SHORT).show()
                    }
                }
                .addOnFailureListener{
                    Toast.makeText(this, "Failed to create user: ${it.message}", Toast.LENGTH_SHORT).show()
                }

        }
    }