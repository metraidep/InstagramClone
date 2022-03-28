package com.tran.instagramclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.parse.ParseUser

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //Check if there's a user logged in
        //If there is, take them to MainActitivty
        if (ParseUser.getCurrentUser()!=null){
            goToMainActivity()
        }

        findViewById<Button>(R.id.login_button).setOnClickListener{
            val username = findViewById<EditText>(R.id.et_username).text.toString()
            val password = findViewById<EditText>(R.id.et_password).text.toString()
            loginUser(username,password)
        }

        findViewById<Button>(R.id.signupBtn ).setOnClickListener {
            val username = findViewById<EditText>(R.id.et_username).text.toString()
            val password = findViewById<EditText>(R.id.et_password).text.toString()
            signUpUser(username, password)
        }
    }


    private fun signUpUser(username: String, password: String) {
        val user = ParseUser()

        // Set fields for the user to be created
        user.setUsername(username)
        user.setPassword(password)

        user.signUpInBackground{ e ->
            if (username != null) {
                // Hooray! Let them use the app now.
                //TODO: Navigate the user to the MainActivity
                //TODO: Show a toast to indicate user succesfully signed up for an account
                Log.i(TAG,"Successfully signed up user")
                Toast.makeText(this,"Sign up successfully!", Toast.LENGTH_SHORT).show()
                goToMainActivity()

            } else {
                e.printStackTrace()
                //TODO: Show a toast to tell user sign up was not successful
                Toast.makeText(this,"Error signing up",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loginUser(username: String, password: String) {
        ParseUser.logInInBackground(username, password, ({ user, e ->
            if (user != null) {
                Log.i(TAG, "Successfully logged in user")
                goToMainActivity()
            } else {
                e.printStackTrace()
                Toast.makeText(this,"Error logging in", Toast.LENGTH_SHORT).show()
            }})
        )
    }

    private fun goToMainActivity(){
        val intent = Intent(this@LoginActivity,MainActivity::class.java)
            startActivity(intent)
            finish()
    }


    companion object{
        const val TAG = "LoginActivity"
    }
}