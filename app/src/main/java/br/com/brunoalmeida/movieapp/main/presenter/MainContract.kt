package br.com.brunoalmeida.movieapp.main.presenter

import br.com.brunoalmeida.movieapp.commons.mvp.BaseMvpPresenter
import br.com.brunoalmeida.movieapp.commons.mvp.BaseMvpView

interface MainContract {

    interface View : BaseMvpView {

    }

    interface Presenter : BaseMvpPresenter<View> {

    }
}