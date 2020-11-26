package com.biz.hello

fun main() {
    var escString : String = "우리는 민족중흥의 \n" +
            "역사적 사명을 띄고\n" +
            "이땅에 태어났다"

    println(escString)
    var rawString : String = """
        하늘을 우러러 
        한점 부끄럼 없기를
        잎새에 이는 바람에도
        괴로워 했다
    """
    println(rawString) // 여백까지 보이는 그대로
    println(rawString.trimIndent()) // 여백을 제거하고

    var rawIntentString : String = """
    |하늘을 
            |우러러 한점
    |부끄럼 없기글
    """
    println(rawIntentString.trimMargin("|"))

    // Kt는 문자열을 Char 배열과 동일하게 취급한다.
    // 문자열변수에 첨자를 부착하여 사용하면 문자를 추출해 낼수 있다.
    var strArray = "Republic Of Korea"
    println(strArray[3]) // 배열처럼
    println(strArray.get(4)) // get method를 통해서
    println(strArray.length) // 문자열 길이

    for(i in 0..strArray.length - 1) {
        print("${strArray[i]} \t" )
    }












}