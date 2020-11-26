package com.biz.hello

// 처음만드는 코틀린 프로젝트
// kt 1.3 미만에서는
// fun main(arg :Array<String>) {
// kt 1.3 이상에서는 생략가능
fun main() {

    /**
     * 변수의 선언
     * 변수명 : 변수타입 = 값 형식으로 선언한다
     * 값의 타입이 명확할 경우 변수타입을 지정하지 않아도 된다.
     * 값의 타입이 명확할 경우 변수추론을 통해 타입을 자동으로 지정한다.
     *
     * Console 출력문
     * $변수명 형식으로 사용하면 Print() 함수에서 따옴표("")와 변수명으로
     * 문자열 출력 format을 자유롭게 설정할수 있다.
     *
     */

    println("반갑습니다")
    println("3 + 4 = ${3+4}")
    println("Republic of Korea")

    var num1 = 100
    var num2 = 200
    var sum = num1 + num2
    println("$num1 + $num2 = $sum")

    var num3 : Long?
    num3 = 100L
    var num4 : Long?
    num4 = 200L
    println("$num3 x $num4 = ${num3 * num4}")

    // val 로 선언된 변수는 상수로서 값을 변경할수 없다.
    val num5 = 100
    val num6 = 200
    println("$num5 + $num6 = ${num5 + num6}")

    num4 = 400L
    // num5 = 400

}
