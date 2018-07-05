package br.com.brunoalmeida.movieapp.commons

import android.content.Context
import android.os.Looper
import android.util.Log
import android.widget.Toast
import br.com.brunoalmeida.movieapp.R
import br.com.brunoalmeida.movieapp.commons.network.NoConnectivityException
import com.squareup.moshi.JsonDataException
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

class RxBus {

    companion object {
        val behaviorSubject: BehaviorSubject<Any> = BehaviorSubject.create()
        val errorSubject: BehaviorSubject<Throwable> = BehaviorSubject.create()
        val searchSubject : BehaviorSubject<String> = BehaviorSubject.create()
        fun getErrorSubject(context : Context) : Disposable {
            return RxBus.errorSubject
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(AndroidSchedulers.from(Looper.getMainLooper()))
                    .subscribe {
                        when (it) {
                            is NoConnectivityException ->
                                Toast.makeText(context, context.getString(R.string.no_connection), Toast.LENGTH_SHORT).show()
                            is JsonDataException ->
                                Toast.makeText(context, context.getString(R.string.movies_not_found), Toast.LENGTH_SHORT).show()
                            else -> {
                                Toast.makeText(context, context.getString(R.string.generical_error), Toast.LENGTH_SHORT).show()
                                Log.w("Error ---->", it.message)
                            }

                        }
                    }
        }
    }

}