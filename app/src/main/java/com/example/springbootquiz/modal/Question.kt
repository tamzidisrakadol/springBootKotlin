package com.example.springbootquiz.modal

data class Question(
    var id:Int=0,
    var category:String="",
    var title:String="",
    var option1:String="",
    var option2:String="",
    var option3:String="",
    var option4:String="",
    var rightAnswer:String=""
)