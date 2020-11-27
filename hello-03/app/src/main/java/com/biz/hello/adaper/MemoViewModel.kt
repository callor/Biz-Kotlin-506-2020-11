package com.biz.hello.adaper

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.biz.hello.db.MemoRepository
import com.biz.hello.model.MemoVO

class MemoViewModel(app : Application) : AndroidViewModel(app) {
    private val memoRep : MemoRepository = MemoRepository(app)
    fun selectAll() : LiveData<MutableList<MemoVO>> {
        return memoRep.selectAll()
    }

    fun findById(id: Long): MemoVO {
        return memoRep.findById(id);
    }

    fun insert(memoVO : MemoVO) {
        memoRep.insert(memoVO)
    }

    fun update(memoVO : MemoVO) {
        memoRep.update(memoVO)
    }

    fun delete(id: Long) {
        memoRep.delete(id)
    }
}