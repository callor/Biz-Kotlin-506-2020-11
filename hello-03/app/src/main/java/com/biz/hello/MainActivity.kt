package com.biz.hello

import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.biz.hello.adaper.MemoViewAdapter
import com.biz.hello.adaper.MemoViewModel
import com.biz.hello.model.MemoVO
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.*

/**
 * MainActivity class
 * Android 의 진입점 클래스
 * AppCompatActivity 클래스를 상속받아서 사용
 * Android의 App이 실행한는데 필요한 기본 속성들이 정의 되어 있다.
 *
 */
class MainActivity : AppCompatActivity() {

    // lateinit : 지금은 변수를 선언만 하지만 이 클래스 어딘가에서
    // 반드시 초기화를 하겠다
    private lateinit var txtMemoInput: TextInputEditText
    private lateinit var btnSave: Button
    private lateinit var memoViewModel: MemoViewModel
    private lateinit var memoAdapter: MemoViewAdapter

    /**
     * onCreate() method를 override하여 사용
     * App이 화면에 떠올라서 작동되는 순간 호출되는 최초의 method
     * activity_main.xml파일에 설정한 코드를 읽어서
     * 화면에 컴포넌트를 구성하고 layout을 설정하는 일을 한다.
     *
     * 만약 버튼의 event를 설정하거나 하는 코드가 필요하면
     * 여기에 그 코드를 작성한다.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // activity_main.xml 파일을 읽어서 화면을 그려라
        setContentView(R.layout.activity_main)

        txtMemoInput = findViewById(R.id.txt_memo)
        btnSave = findViewById(R.id.btn_save)
        memoViewModel = MemoViewModel(this.application)

        // 입력 도중 키보드의 Send 버튼을 클릭했을때 반응할 event
        txtMemoInput.setOnEditorActionListener { view, actionId, event ->

            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEND -> {

                    // Snackbar.make(view, text, Snackbar.LENGTH_LONG).show()
                    val text: String = txtMemoInput.text.toString()
                    val sd = SimpleDateFormat("yyyy-MM-dd")
                    val st = SimpleDateFormat("HH:mm:ss")
                    val date = Date(System.currentTimeMillis())

                    val memoVO: MemoVO = MemoVO()
                    memoViewModel.insert(memoVO)
                    true

                }
                else -> false
            }
        }

        btnSave.setOnClickListener { view ->
            Log.d("MAIN", "btnSave") // debug

            val text: String = txtMemoInput.text.toString()
            // Snackbar.make(view,text,Snackbar.LENGTH_LONG).show()
            Toast.makeText(this, text, Toast.LENGTH_LONG).show()

        }

        var memoList: MutableList<MemoVO> = mutableListOf()
        memoAdapter = MemoViewAdapter(memoList)
        //=============================================
        // recyclerView와 데이터를 바인딩하는 코드
        //=============================================
        // 내용물이 없는 mutableList 선언 및 초기화, null 값이 되지 않도록 하기 위한 조치
        memoViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory(application)
        )
            .get(MemoViewModel::class.java)

        // Rx( Relative Extention ) java 패턴을 응용해서 만든 observe메서드
        // observe 데이터를 생산하는 주체
        // 데이터를 생산하고 view한테, 지금 데이터가 만들어졌으니 가져다가
        // 알아서 요리해라 라는 통보만
        memoViewModel.selectAll().observe(this, {
            it?.let {
                memoAdapter.setList(it)
            }
            memoAdapter.notifyDataSetChanged()
        })

        /*
        for(i in 0..30) {
            var memoVO : MemoVO = MemoVO()
            memoVO.memo = i.toString()

            val sd = SimpleDateFormat("yyyy-MM-dd")
            val st = SimpleDateFormat("HH:mm:ss")
            val date = Date(System.currentTimeMillis())

            memoVO.date = sd.format(date).toString()
            memoVO.time = st.format(date).toString()
            memoList.add(memoVO)
        }
        */


        val rView: RecyclerView = findViewById(R.id.data_list_view)
//        rView.adapter = memoAdaper

        val layoutManger = LinearLayoutManager(this)
        rView.layoutManager = layoutManger


    }


}



