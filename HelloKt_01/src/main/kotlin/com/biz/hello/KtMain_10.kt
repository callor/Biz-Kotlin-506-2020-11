package com.biz.hello

fun main() {

    var sum = add(100,200)
    println(sum)
    println(add(3.5F,4.5F))
    println(add("Korea","대한민국"))

}

fun add(num1 : Int, num2 : Int) : Int {
    return num1 + num2
}
fun add(fnum1 : Float, fnum2 : Float) : Float {
    return fnum1 + fnum2;
}
fun add(str1:String, str2:String) : String {
    return str1 + str2
}

