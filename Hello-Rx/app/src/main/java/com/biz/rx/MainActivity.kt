package com.biz.rx;

import android.util.Log
import com.biz.rx.R
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.observers.DisposableObserver

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * MainActivity에 View.OnClickListener 인터페이스를 implements 하고
 * onClick 이벤트 핸들러를 override하여 사용하는 방법
 */
class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var btnStart: Button
    private lateinit var btnStop:Button

    // Observable을 등록할때 메모리 누수를 최소화 할 수 있는 도구 중의 하나
    private val disposables : CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStart = findViewById(R.id.btn_start)
        btnStop = findViewById(R.id.btn_stop)

        btnStart.setOnClickListener(this)
        btnStop.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        val viewId = v?.id // v.getIs()
        var msg : String = when(viewId) {
            R.id.btn_start -> "Start"
            R.id.btn_stop -> "Stop"
            else -> "모름"
        }
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()

        when(viewId) {
            R.id.btn_start -> {this.start()}
        }

    }

    // Observable을 등록을 한후에 사용이 완료되면
    // 메모리에서 삭제해주어야 한다.
    // onDestroy() 는 모든 객체, 화면, Thread 등이 사용이 만료되면
    // 마지막에 실행되는 method
    // 여기에서 Observable을 해제 해야한다
    override fun onDestroy() {
        super.onDestroy()
        Log.d("Des","Clear")
        disposables.clear()
    }

    private fun start() {
        // CompositeDisposable를 이용하여 구독을 시작하겠다
        disposables.add(
                // objservale() : 메시지를 발행하는 method
                observable()
                        .subscribeOn(Schedulers.io()) // 메시지를 구독하겠다.
            .observeOn(AndroidSchedulers.mainThread()) // 메시지 구독을 MainActivity가 작동되는
                                                       // thread에서 받겠다
                // 메시지 전송이 오면
                .subscribeWith(object: DisposableObserver<String?>(){
                    // 연속된 메시지가 오면, 일단 계속해서 수신하라
                    // 수신된 데이터를 처리하는 부분
                    override fun onNext(t: String?) {
                        Log.d("OB",t.toString())
                    }
                    // 메시지가 전송되는 과정에서 오류가 발생하면
                    override fun onError(e: Throwable?) {
                        Log.d("OB","Erorr")
                    }
                    // 메시지 발행처에서 다보냈다라고 통보가 오면
                    override fun onComplete() {
                        Log.d("OB","완료")
                    }
                }))
    }

    // static method
    // 메시지를 발행 just(문자열들...총 10개까지 가능)
    companion object {
        fun observable(): Observable<String> {
            return Observable.defer {
                Observable.just("1","2","3","4","5","6")
                Observable.just("1","2","3","4","5","6")
            }
        }
    }
}