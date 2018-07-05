package br.com.brunoalmeida.movieapp.commons

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast
import br.com.brunoalmeida.movieapp.MovieApp
import br.com.brunoalmeida.movieapp.R
import android.content.DialogInterface




open class Utils {
    companion object {
        fun hasNetWork(): Boolean {
            val context = MovieApp.mInstance
            val connectivityManager: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            var activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (connectivityManager.activeNetworkInfo != null) {
                activeNetworkInfo = connectivityManager.getActiveNetworkInfo()
            }
            return (activeNetworkInfo != null && activeNetworkInfo.isConnected)
        }

    }
}