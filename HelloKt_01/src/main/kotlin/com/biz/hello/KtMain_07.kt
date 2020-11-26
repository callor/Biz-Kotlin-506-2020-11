package com.biz.hello

fun main(){

    var intRange : IntRange = 0..100
    println("RANGE : ${intRange}")
    for(i in intRange) {
        print("$i \t")
    }


}