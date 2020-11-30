package com.biz.ktgugudan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import io.reactivex.rxjava3.subjects.BehaviorSubject

class MainActivity : AppCompatActivity() {

    // Kotlin에서 나중에 초기화를 꼭 하겠다 라는 선언
    private lateinit var txt : EditText
    private lateinit var view : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txt = findViewById(R.id.txt)
        view = findViewById(R.id.view)

        // EditText에 키보드로 입력을 하면 Change Event가 발생한다
        // Change Event를 가로채서 글자를 view에 보이기를 해 보자

        /**
         * rxJava의 Subject(주제)
         * rxJava 에서 Observable처럼 행동하는 구현체
         * 이미 생산된 메시지를 구독하는 소비주체
         */

        // Subject중에서 가장많이 사용하는 구현체
        // 최근 마지막까지 캐시를 만들어서 구현하는 클래스
        // 아무런 메시지가 없을때 문자열 "0"으로 초기화하여 객체를 생성하라
        val subject: BehaviorSubject<String> = BehaviorSubject.createDefault("0")

        // TextWatcher interface
        // 키보드로 EditText Box에 문자를 입력했을때
        // 문자열이 입력된 사항을 지켜보고 있다가
        // 문자열의 처리를 담당하는 역할을 수행한다.
        // 람바 코드로 익명 클래스를 선언한다.
        txt.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // TODO("Not yet implemented")
            }

            // 문자열이 입력되는 순간 실행되는 method
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val text = txt.text
                // 직접 view의 text 속성에 문자열을 부착하여 app 화면에 보여주기
                // app, device입장에서는 상당히 많은 비용이 소요되는 코드
                // view.text = text
                subject.map { txt.text.toString() == "" } // 최초에 app이 실행되거나,
                                                        // EditText에 문자열이 모두 제거되면

                     // java Stream(연속된 데이터)형태의 자료를 생성하는 method
                    // flatMap에 의해 생성된 데이터는 Observable형태의 데이터이다.
                    // Observable관련된 method등을 chain하여 데이터를 처리할 수 있다
                    .flatMap({ BehaviorSubject.range(1,9) }) // {1,2, ~ 9 } 형태의 배열자료를 만들어라
                    {_,row -> "0 x $row = 0\n"}
                    .scan {x,y -> x + y}
                    .subscribe {text->view.text = text}

                /**
                 * view.text = text 코드를 사용하면 view화면에 보이는데 매우 단순한 코드로 완료된다
                 * 그럼에도 불구하고 subject(rxjava) 코드를 사용하는 이유는
                 * 만약 text change가 발생했을때 화면에 그리기 위한 text 문자열을 만드는 코드가 많아진다면
                 * (for, while등을 이용하여 코드를 작성해야 할 것이다)
                 * 그 코드에 의해 문자열이 모두 생성되어서 화면에 나타탈때까지
                 * mainActivity의 다른 코드나 이벤트의 실행에 영향을 미치게 된다.
                 *
                 * subject 를 사용하게 되면 change event가 발생한 즉시 subject에게 데이터를 넘겨버리고
                 * mainActivity를 작동시키는 main thread는 역할을 끝내버린다.
                 * 나머지는 subject가 화면에 보여줄 문자열을 만들고, 문자열이 완성되면
                 * 화면에 보여주는 일을 대신 수행한다.
                 */
                subject.map {txt.text.toString().toLong()}
                    .flatMap({BehaviorSubject.range(1,9)  })
                    {dan,row -> dan.toString() + " X " + row + " = " + dan * row + "\n"}
                    .scan {x,y -> x + y}
                    .subscribe({text->view.text = text })
                    {obj : Throwable->obj.message}
            }
            override fun afterTextChanged(s: Editable?) {
                // TODO("Not yet implemented")
            }
        })
    }
    
    // 인터페이스를 상속받아서 구현된 일반적인 클래스
    class MyWatcher : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            TODO("Not yet implemented")
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            TODO("Not yet implemented")
        }

        override fun afterTextChanged(s: Editable?) {
            TODO("Not yet implemented")
        }
    }

}



