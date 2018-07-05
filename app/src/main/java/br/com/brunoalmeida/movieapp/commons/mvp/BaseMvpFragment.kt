package br.com.brunoalmeida.movieapp.commons.mvp

import android.app.FragmentManager
import android.content.Context
import android.os.Bundle
import android.os.Looper
import android.os.PersistableBundle
import android.support.v4.app.Fragment
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

abstract class BaseMvpFragment<in V : BaseMvpView, T : BaseMvpPresenter<V>> : Fragment(), BaseMvpView {

    protected abstract var mPresenter: T

    lateinit var disposable: Disposable


    var dialog = LoadingDialog()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter.attachView(this as V)
    }

    override fun onStart() {
        super.onStart()
        disposable = RxBus.getErrorSubject(context)
    }

    override fun showLoading() {
//        if (!dialog.isAdded)
//            dialog.show(this.fragmentManager, "Loading_dialog")
    }

    override fun dismissLoading() {
//        if (dialog.isAdded)
//            dialog.dismiss()
    }


    override fun getContext(): Context = activity as Context

    override fun showError(error: String?) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }

    override fun showError(strId: Int) {
        Toast.makeText(context, strId, Toast.LENGTH_SHORT).show()
    }

    override fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun showMessage(stringRes: Int) {
        Toast.makeText(context, stringRes, Toast.LENGTH_SHORT).show()
    }

    override fun onStop() {
        super.onStop()
        disposable.dispose()
    }

}