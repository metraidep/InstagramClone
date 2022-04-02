package com.tran.instagramclone

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.FileProvider
import com.parse.*
import java.io.File
import android.os.Environment
import android.provider.MediaStore
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tran.instagramclone.fragments.ComposeFragment
import com.tran.instagramclone.fragments.FeedFragment


// Let user create a post by taking a photo with their camera
class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        findViewById<Button>(R.id.btnLogOut).setOnClickListener {
//            ParseUser.logOut()
//            val currentUser = ParseUser.getCurrentUser()
//            if (currentUser == null){
//                goToLoginActivity()
//            }
//        }

        val fragmentManager: FragmentManager = supportFragmentManager
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        findViewById<BottomNavigationView>(R.id.bottom_navigation).setOnItemSelectedListener {

            item ->

            var fragmenttoShow: Fragment? = null
            when (item.itemId){
                R.id.action_home -> {
                    fragmenttoShow = FeedFragment()
                    Toast.makeText(this,"Home",Toast.LENGTH_SHORT).show()
                }
                R.id.action_compose -> {
                    fragmenttoShow = ComposeFragment()
//                    Toast.makeText(this,"Compose",Toast.LENGTH_SHORT).show()
                }
                R.id.action_profile -> {
                    Toast.makeText(this,"Profile",Toast.LENGTH_SHORT).show()
                }

            }

            if (fragmenttoShow != null){
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragmenttoShow).commit()
            }
            //return true to say that we've handled the interaction on the item
             true
        }
            // Set default selection
            bottomNavigationView.selectedItemId = R.id.action_home
//        queryPosts()

    }

    //Send a Post object to our Parse server


    // Returns the File for a photo stored on disk given the fileName

    //Query for all posts in our server


    private fun goToLoginActivity(){
        val intent = Intent(this@MainActivity,LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
    companion object{
        const val TAG = "MainActivity"
    }
}