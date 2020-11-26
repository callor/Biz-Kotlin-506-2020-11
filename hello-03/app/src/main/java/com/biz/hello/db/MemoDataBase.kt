package com.biz.hello.db

import android.content.Context
import androidx.room.Database
import androidx.room.DatabaseConfiguration
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.biz.hello.model.MemoVO
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Database(entities = [MemoVO::class],version = 1,exportSchema = false)
abstract class MemoDataBase  : RoomDatabase(){

    abstract val memoDao : MemoDao

    companion object {
        private var INSTANCE : MemoDataBase? = null
        private val NUMBER_THREADS = 5;

        val databaseWriterExcutor:ExecutorService = Executors.newFixedThreadPool(NUMBER_THREADS)

        fun getInstance(context : Context) : MemoDataBase? {

            if(INSTANCE == null) {
                synchronized(MemoDataBase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        MemoDataBase::class.java,"memo_datebase").build()
                }
            }
            return INSTANCE
        }
    }
}