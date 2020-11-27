package com.biz.hello

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.biz.hello.adaper.MemoViewAdapter
import com.biz.hello.adaper.MemoViewModel
import com.biz.hello.model.MemoVO
import com.google.android.material.snackbar.Snackbar
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

//        val toolbar = findViewById<Toolbar>(R.id.toolbar)
//        setSupportActionBar(toolbar)

        txtMemoInput = findViewById(R.id.txt_memo)
        btnSave = findViewById(R.id.btn_save)
        memoViewModel = MemoViewModel(this.application)

        // 입력 도중 키보드의 Send 버튼을 클릭했을때 반응할 event
        txtMemoInput.setOnEditorActionListener { view, actionId, event ->

            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEND -> {
                    // Snackbar.make(view, text, Snackbar.LENGTH_LONG).show()
                    // val text: String = txtMemoInput.text.toString()
                    // val sd = SimpleDateFormat("yyyy-MM-dd")
                    // val st = SimpleDateFormat("HH:mm:ss")
                    // val date = Date(System.currentTimeMillis())

                    // val memoVO: MemoVO = MemoVO()
                    // memoViewModel.insert(memoVO)

                    this.memoInsert(view)
                    true
                }
                else -> false
            }
        }

        btnSave.setOnClickListener { view ->
            Log.d("MAIN", "btnSave") // debug

            val text: String = txtMemoInput.text.toString()
            // Snackbar.make(view,text,Snackbar.LENGTH_LONG).show()
            // Toast.makeText(this, text, Toast.LENGTH_LONG).show()
            this.memoInsert(view)
        }

        var memoList: MutableList<MemoVO> = mutableListOf()
        memoAdapter = MemoViewAdapter(memoList,
                            {id-> memoViewModel.delete( id as Long )},
                            {id->this.memoUpdate(id as Long) } )
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

        val rView: RecyclerView = findViewById(R.id.data_list_view)
        rView.adapter = memoAdapter

        val layoutManger = LinearLayoutManager(this)
        rView.layoutManager = layoutManger
    }

    fun memoInsert( view : View) {

        // Button Parent인 view 를 Button Down 형변환을 하여 Button속성을 쉽게 추출하여
        // 사용할 수 있도록 한다.
        val btn = view as Button

        // 입력박스에 입력된 문자열 추출
        val txtMemo = txtMemoInput.text.toString()
        if(txtMemo.isEmpty()) {
            Snackbar
                .make(view,"메모를 입력한 후 저장을 수행하세요",Snackbar.LENGTH_LONG)
                .show()
            return
        }

        var memoVO = MemoVO()
        if(btn.text == "변경") {
            memoVO = btn.tag as MemoVO
            memoVO.memo = txtMemo 
        } else {
            val sd = SimpleDateFormat("yyyy-MM-dd")
            val st = SimpleDateFormat("HH:mm:ss")
            val date = Date(System.currentTimeMillis())
            memoVO.memo = txtMemo
            memoVO.date = sd.format(date).toString()
            memoVO.time = st.format(date).toString()
        }
        memoViewModel.insert(memoVO)

        // 메모를 저장한 후 입력박스의 내용을 초기화 시키기
        txtMemoInput.text = null
        btnSave.text = "추가"
    }

    fun memoUpdate(id:Long) {
        val memoVO : MemoVO = memoViewModel.findById(id);
        // TextInput Box에서 값을 읽을때는
        //  text = textbox.text
        // 값을 써넣을때는 textbox.text = "값" 형식 안되고
        // txtbox?.setText("값") 형식으로 사용해야 한다.
        txtMemoInput?.setText(memoVO.memo as String)
        btnSave.text = "변경"
        btnSave.tag = memoVO
    }
}



