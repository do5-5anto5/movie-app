package com.do55anto5.movieapp.presenter.auth.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.do55anto5.movieapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
    }
}