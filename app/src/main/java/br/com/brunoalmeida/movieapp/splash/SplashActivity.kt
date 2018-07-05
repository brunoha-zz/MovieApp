package br.com.brunoalmeida.movieapp.splash

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.brunoalmeida.movieapp.R
import br.com.brunoalmeida.movieapp.search.view.SearchFragment

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        startActivity(Intent(this, SearchFragment::class.java))
        finish()
    }
}
