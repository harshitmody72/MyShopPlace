package com.myshoppal.ui.activities

import android.content.Intent
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.myshoppal.R
import com.myshoppal.firestore.FirestoreClass
import kotlinx.android.synthetic.main.activity_splash.*

@Suppress("DEPRECATION")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // This is used to hide the status bar and make the splash screen as a full screen activity.
        // It is deprecated in the API level 30. I will update you with the alternate solution soon.
        @Suppress("DEPRECATION")
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        }else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        // Adding the handler to after the a task after some delay.
        // It is deprecated in the API level 30.
        Handler().postDelayed(
            {

                // If the user is logged in once and did not logged out manually from the app.
                // So, next time when the user is coming into the app user will be redirected to MainScreen.
                // If user is not logged in or logout manually then user will  be redirected to the Login screen as usual.

                // Get the current logged in user id
                val currentUserID = FirestoreClass().getCurrentUserID()

                if (currentUserID.isNotEmpty()) {
                    // Launch dashboard screen.
                    startActivity(Intent(this@SplashActivity, DashboardActivity::class.java))
                } else {
                    // Launch the Login Activity
                    startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                }
                finish() // Call this when your activity is done and should be closed.
            },
            2500
        ) // Here we pass the delay time in milliSeconds after which the splash activity will disappear.


        //Without Custom front Text View Classes
//        val typeface : Typeface = Typeface.createFromAsset(assets,"Montserrat_Bold.ttf")
//        tv_app_name.typeface = typeface
    }
}