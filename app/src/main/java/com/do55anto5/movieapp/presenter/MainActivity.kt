package com.do55anto5.movieapp.presenter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.do55anto5.movieapp.R
import com.do55anto5.movieapp.databinding.ActivityMainBinding
import com.do55anto5.movieapp.presenter.auth.forgot.ForgotFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val forgotFragment = ForgotFragment()

        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.add(R.id.action_container, forgotFragment)
        transaction.commit()
    }
}