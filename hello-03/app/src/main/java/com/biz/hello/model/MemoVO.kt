package com.biz.hello.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tbl_memo")
class MemoVO {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    public var id : Long = 0

    @ColumnInfo(name="date")
    public var date : String = ""

    @ColumnInfo(name="time")
    public var time : String = ""

    @ColumnInfo
    public var memo : String = ""

}