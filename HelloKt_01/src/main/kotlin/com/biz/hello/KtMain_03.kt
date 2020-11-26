package com.biz.hello

import java.util.*

fun main() {

    // Java의 Scanner 클래스로 scan 객체 생성
    val scan = Scanner(System.`in`)

    print("국어점수를 입력하세요 >> ")
    var strKor = scan.nextLine();

    print("영어점수를 입력하세요 >> ")
    var strEng = scan.nextLine()

    var sum = strKor.toInt() + strEng.toInt()
    println("점수 합계 : $sum")

}