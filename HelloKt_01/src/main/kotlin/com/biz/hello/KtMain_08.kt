package com.biz.hello

fun main() {

    var str1 : String = "Korea"
    var str2 : String = "Korea"

    // 문자열 값으로 비교하기
    if(str1 == str2) {
        println("같은 문자열 입니다")
    }
    // 레퍼런스로 비교
    if(str1 === str2) {
        println("주소가 다를 수 있습니다")
    }

    var num1 = 10
    var num2 = 20

    // num1과 num2중 큰값찾기
    var max1 = num1
    if(num1 < num2) max1 = num2

    // KT의 3항연산
    // num1 > num2 ? num1 : num2
    max1 = if(num1 > num2) num1 else num2

    // num1과 num2의 크기를 비교한 후
    // 어떤 연산을 수행하고 그 결과를 max1에 담고 싶을때
    max1 = if(num1 > num2) {
        println("num1이 더 큼")
        num1
    } else {
        println("num2가 더 큼")
        num2
    }

    var num3 = 3
    when(num3) {
        1 -> println("num3 = 1")
        2 -> println("num3 = 2")
        3 -> println("num3 = 3")
        4 -> println("num3 = 4")
        else -> println("없음")
    }

    var rNum : Int = (Math.random() * 100 + 1).toInt()
    when(rNum) {
        1 -> println("rNum는 1이다 ")
        in 10..20 -> println("rNum는 10 부터 20중의 하나이다 $rNum ")
        in 21..31 -> println("rNum는 21 부터 31중의 하나이다 $rNum ")
        else -> println("어디에도 속하지 않는다")
    }

    var arrInt = intArrayOf(1,2,3,4,5,6,7,8,9,10)
    rNum = (Math.random() * 20 + 1).toInt()
    when(rNum) {
        in arrInt -> println("arrInt 배열에 포함된 값")
    }

    // Any 모든 자료형
    var xVar : Any = "Republic of Korea"
    var x = when(xVar) {
        is String -> xVar.indexOf("Korea") > 0 // 문자열이 포함되어 있나?
        is String -> xVar.startsWith("of") // 시작하는 문자열이 of 이냐?
        else -> false
    }
    println("$xVar 에는 Korea 문자열이 ${if(x) "포함 되었음" else "포함되지 않음" }")

}