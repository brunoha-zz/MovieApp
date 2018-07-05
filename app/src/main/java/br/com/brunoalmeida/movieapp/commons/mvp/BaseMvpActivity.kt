package br.com.brunoalmeida.movieapp.commons.mvp

import android.app.FragmentManager
import android.content.Context
import android.os.Bundle
import android.os.Looper
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import br.com.brunoalmeida.movieapp.R
import br.com.brunoalmeida.movieapp.commons.RxBus
import br.com.brunoalmeida.movieapp.commons.Utils
import br.com.brunoalmeida.movieapp.commons.network.NoConnectivityException
import br.com.brunoalmeida.movieapp.commons.view.LoadingDialog
import com.squareup.moshi.JsonDataException
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

/**
 * Created by brunoalmeida on 14/03/18.
 */

abstract class BaseMvpActivity<in V : BaseMvpView, T : BaseMvpPresenter<V>> : AppCompatActivity(), BaseMvpView {

    protected abstract var mPresenter: T

    lateinit var disposable: Disposable


    var dialog = LoadingDialog()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mPresenter.attachView(this as V)
        Log.d("Taaaag--->", "--🙂--------AttachView")

        disposable = RxBus.errorSubject
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.from(Looper.getMainLooper()))
                .subscribe {
                    when (it) {
                        is NoConnectivityException ->
                            Toast.makeText(getContext(), getString(R.string.no_connection), Toast.LENGTH_SHORT).show()
                        is JsonDataException ->
                            Toast.makeText(getContext(), getString(R.string.movies_not_found), Toast.LENGTH_SHORT).show()
                        else -> {
                            Toast.makeText(getContext(), getString(R.string.generical_error), Toast.LENGTH_SHORT).show()
                            Log.w("Error ---->", it.message)
                        }

                    }
                }

    }

    override fun showLoading() {
        if (!dialog.isAdded)
            dialog.show(fragmentManager, "Loading_dialog")
    }

    override fun dismissLoading() {
        if (dialog.isAdded)
            dialog.dismiss()
    }


    override fun getContext(): Context = this

    override fun showError(error: String?) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun showError(strId: Int) {
        Toast.makeText(this, strId, Toast.LENGTH_SHORT).show()
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showMessage(stringRes: Int) {
        Toast.makeText(this, stringRes, Toast.LENGTH_SHORT).show()
    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        disposable.dispose()
//
//    }

}