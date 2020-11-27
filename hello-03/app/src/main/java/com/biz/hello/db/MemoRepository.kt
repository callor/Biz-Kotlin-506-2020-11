package com.biz.hello.db

import android.app.Application
import androidx.lifecycle.LiveData
import com.biz.hello.model.MemoVO

class MemoRepository(app : Application) {
    private lateinit var memoDao : MemoDao
    init {
        val db : MemoDataBase? = MemoDataBase.getInstance(app)
        if( db != null) {
            memoDao = db.getMemoDao()!!
        }
    }

    fun selectAll() : LiveData<MutableList<MemoVO>> {
        return memoDao.selectAll()
    }
    fun insert(memoVO : MemoVO) {
        // MemoDatabase에 선언된 databaseWriterExcutor 메서드를
        // Thread 방식으로 호출하여 Insert를 수행하라
        MemoDataBase.databaseWriterExcutor.execute(Runnable { memoDao.save(memoVO)})
    }
    fun update(memoVO : MemoVO) {
        // MemoDatabase에 선언된 databaseWriterExcutor 메서드를
        // Thread 방식으로 호출하여 Insert를 수행하라
        MemoDataBase.databaseWriterExcutor.execute(Runnable { memoDao.save(memoVO)})
    }

    fun delete(memoVO : MemoVO) {
        memoDao.delete(memoVO.id)
    }


}



