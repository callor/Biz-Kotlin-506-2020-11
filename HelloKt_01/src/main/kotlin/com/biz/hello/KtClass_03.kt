package com.biz.hello

// 내부적으로 생성자, getter, setter, Tosting 기본적으로 만들어지고
//  equeals(), hashCode(), copy(), componentN() 메서드가 생성
data class BookVO(var title : String, var author : String, var comp : String, var price : Int)
data class UserVO(var name : String = "", var tel : String = "", var age : Int = 0)

fun main() {
    // 생성자가 변수 타입만 지정된 data class는 빈 값으로 객체를 생성할수 없다
    var bookVO = BookVO("자바야놀자","홍기동","이지즈",15000)
    bookVO.title = "오라클"
    println(bookVO.toString())
    println(bookVO.hashCode())

    // data class를 빈 값으로 객체로 생성하려면 클래스 선언문에 default 값이 지정되어 있어야 한다
    var userVO = UserVO()
    println(userVO.toString())
    println(userVO.hashCode())
    println("userVO야 너는 UserVO 클래스로 부터 만들어진 객체냐 ? " +
            "${if(userVO.equals(UserVO())) "맞아" else "아니"} " )
    println("userVO야 너는 BookVO 클래스로 부터 만들어진 객체냐 ? " +
            "${if(userVO.equals(BookVO("","","",0))) "맞아" else "아니"} " )

    
    var userVO1 = UserVO(name="이몽룡",age=33)
    var bookVO1 = BookVO(author="성춘향",title="제이쿼리",comp="이지즈",price=12000)
    println(UserVO(name="장보고",tel="010-222-1111").toString())

    var userVOCopy = userVO1.copy(name="임꺽정")
    println(userVOCopy.toString())

    // var title = bookVO1.title
    // var author = bookVO1.author
    var (title,author) = bookVO1
    println("$title, $author")

    var (first, second) = Pair("홍기롱","이몽룡")
    var (f,s,t) = Triple("010",111,2222)

    println(StaticClass.ID)
    println(StaticClass.SECURITY)

}

class StaticClass {
    companion object NAVER_KEY {
        var ID = "1234567"
        var SECURITY = "000111000"
    }

}

class Daum_Config {
    companion object {
        var SEC_ID = "0001"
        var SEC_VALUE = 1111
    }
}

fun daum() {
    println(Daum_Config.SEC_ID)
    println(Daum_Config.SEC_VALUE)
}
