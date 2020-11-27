package com.biz.hello.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.biz.hello.model.MemoVO
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Database(entities = [MemoVO::class], version = 1, exportSchema = false)
abstract class MemoDataBase : RoomDatabase() {

    abstract fun getMemoDao() : MemoDao?

    companion object {
        private var INSTANCE: MemoDataBase? = null
        private val NUMBER_THREADS = 5

        val databaseWriterExcutor: ExecutorService = Executors.newFixedThreadPool(NUMBER_THREADS)

        @Synchronized
        fun getInstance(context: Context): MemoDataBase? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    MemoDataBase::class.java, "memo_datebase"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE
        }
    }
}