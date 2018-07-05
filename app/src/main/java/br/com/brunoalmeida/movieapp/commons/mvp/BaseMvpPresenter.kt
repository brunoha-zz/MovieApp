package br.com.brunoalmeida.movieapp.commons.mvp

/**
 * Created by brunoalmeida on 14/03/18.
 */
interface BaseMvpPresenter<in V : BaseMvpView> {

    fun attachView(view : V)

    fun detachView()

}