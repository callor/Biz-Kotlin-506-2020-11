package com.biz.hello

fun main() {

    // 배열 선언하기
    var arrInt = Array<Int>(5,{0})
    var arrStr  = arrayOf(5,"K","O","R","A")

    var intList = 0..100
    var odd = 0
    var even = 0
    for(i in intList) {
        if(i % 2  == 0) {
            even += i
        } else {
            odd += i
        }
    }
    println("짝수의 합 : $even, 홀수의 합 $odd")

    odd = 0
    even = 0
    intList.filter {it % 2 == 0}.map { even += it}
    intList.filter {it % 2 != 0}.map { odd += it}

    for(i in 0..100 step 2) {
        print("$i \t")
    }

    var arrList = ArrayList<Int>()
    arrList.add(10)
    arrList.add(10)
    arrList.add(10)
    arrList.add(10)
    arrList.add(10)

    for(i in 0..10) {
        var r = (Math.random() * 100).toInt()
        arrList.add(r)
    }

    // forEach 문에서 index와 value값을 동시에 추출하기
    for((index,value) in arrList.withIndex()) {
        println(" $index : $value" )
    }




}