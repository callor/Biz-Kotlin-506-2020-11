package com.biz.hello

import java.util.*

fun main() {

    val scan = Scanner(System.`in`)

    print("구의 반지름 >> ")
    val strRedius = scan.nextLine()
    val dRedius = strRedius.toDouble()

    // 구의 면적
    val area: Double = dRedius * Math.PI * 4.0
    // 구의 부피
    val volume: Double = (dRedius * dRedius) * (4 / 3).toDouble() * Math.PI

    println("반지름이 $dRedius 인 구의 면적 : $area, 부피 : $volume")


}

