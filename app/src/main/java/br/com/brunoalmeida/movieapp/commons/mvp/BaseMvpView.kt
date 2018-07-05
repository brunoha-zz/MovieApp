package br.com.brunoalmeida.movieapp.commons.mvp

import android.app.FragmentManager
import android.content.Context
import android.support.annotation.StringRes

/**
 * Created by brunoalmeida on 14/03/18.
 */
interface BaseMvpView {

    fun getContext() : Context

    fun showError(error : String?)

    fun showLoading()

    fun dismissLoading()

    fun showError(@StringRes stringResIdRes: Int)

    fun showMessage(@StringRes stringRes: Int)

    fun showMessage(message: String)
}