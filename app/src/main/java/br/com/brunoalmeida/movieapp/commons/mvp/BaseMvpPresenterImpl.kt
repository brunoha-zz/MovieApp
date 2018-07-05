package br.com.brunoalmeida.movieapp.commons.mvp

/**
 * Created by brunoalmeida on 14/03/18.
 */
open class BaseMvpPresenterImpl<V : BaseMvpView> : BaseMvpPresenter<V> {

    protected var mView : V? = null

    override fun attachView(view: V) {
        mView = view
    }

    override fun detachView() {
        mView = null
    }

}