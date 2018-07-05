package br.com.brunoalmeida.movieapp.moviedetail.presenter

import br.com.brunoalmeida.movieapp.commons.mvp.BaseMvpPresenter
import br.com.brunoalmeida.movieapp.commons.mvp.BaseMvpView

interface MovieDetailContract  {

    interface View : BaseMvpView {

    }

    interface Presenter : BaseMvpPresenter<View>{

    }
}