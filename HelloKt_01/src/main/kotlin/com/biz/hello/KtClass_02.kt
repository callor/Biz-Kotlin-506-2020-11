package com.biz.hello

/**
 * Kotlin에서의 클래스
 * Kotlin에서는 필드변수(속성, 인스턴스변수)를 생성자화 함께
 * 선언하고 초기화 할 수 있다
 */

// 생성자와 함께 클래스 내부에서 사용할 속성(필드변수)를 선언
// var, val 키워드가 없는 속성은 외부에서 접근 불가
//      obj : Class_01 = Class_01()
//      obj.name, obj.age 변수를 읽거나 쓸수 없다
class Class_01(name : String, age : Int) { }

// 속성 name, 과 age에 접근 가능하다
// 단
// name val 로 선언되어서 읽기 전용이다.
//      obj : Class_02 = Class_02()
//      var n = obj.name 가능
//      obj.name = "홍길동"
//      var a = obj.age 가능
//       obj.age = 30 가능
class Class_02(val name : String, var age : Int) {}

// 속성에 기본(default)값을 지정하여 객체를 만들때
// 변수값이 주입되지 않으면 기본값으로 변수를 초기화
//      var obj = Class_03("이몽룡",33) : name 속성과 age를 정의
//      var obj = Class_03() : name은 홍길동, age는 0으로 초기화 된다.
class Class_03(val name : String ="홍길동", var age : Int = 0) {}


// KT은 기본적으로 클래스의 상속이 금지된다.
// 클래스를 상속하려면 클래스 선언자에 open 키워드로 시작해야하고
// init 블럭이 있어야 한다.
open class Class_04() {
    // 생성자 블럭
    // 클래스로 객체를 생성할때 무조건 실행되는 블럭
    init {
        println("기본 생성자 블럭")
    }
    // 상속받은 클래스 메서드를 Override 하려면
    // 메서드에도 open 키워드로 시작해야 한다.
    // 상속받은 클래스에서는 override 키워드로 반드시 시작해야 한다.
    open fun sum() {

    }
}
class Class_05 : Class_04() {
    override fun sum() {
        println("나는 Override 된 함수")
    }
}

fun main() {
    var c4 = Class_04()
    var c5 = Class_05()
    c5.sum()

}


