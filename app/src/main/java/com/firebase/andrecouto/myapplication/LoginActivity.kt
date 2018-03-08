package com.firebase.andrecouto.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import android.widget.Toast
import com.firebase.andrecouto.myapplication.extensions.getText
import com.google.firebase.auth.FirebaseUser

class LoginActivity : AppCompatActivity() {

    lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()

        btCriar.setOnClickListener {
            mAuth.createUserWithEmailAndPassword(inputEmail.getText(), inputSenha.getText())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val user = mAuth.currentUser
                            //updateUI(user)
                        } else {
                            Toast.makeText(this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show()
                            //updateUI(null)
                        }

                    }
        }

        btLogin.setOnClickListener {
            mAuth.signInWithEmailAndPassword(inputEmail.getText(), inputSenha.getText())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val user = mAuth.currentUser
                            //updateUI(user)
                        } else {
                            Toast.makeText(this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show()
                           // updateUI(null)
                        }
                    }
        }

        btLogout.setOnClickListener {

        }

        btEnviarEmail.setOnClickListener {
            val user: FirebaseUser? = mAuth.currentUser
            user?.sendEmailVerification()
                    ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                   Log.i("e-mail","sent")
                } else {
                    Log.i("e-mail","not sent")
                }
            }
        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth.currentUser
        //updateUI(currentUser)
    }
}
