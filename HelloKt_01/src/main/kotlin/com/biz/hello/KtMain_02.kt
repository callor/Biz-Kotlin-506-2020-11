package com.biz.hello

import com.biz.hello.java.Class_01

fun main() {
    // Kotlin에서 java로 선언한 클래스 사용하기
    // var, val 로 시작하고 생성자를 new 키워드 없이 사용
    // java클래스로 객체 생성하기
    val c01 = Class_01()
    var ret = c01.sum(100,200)
    println(ret)

}