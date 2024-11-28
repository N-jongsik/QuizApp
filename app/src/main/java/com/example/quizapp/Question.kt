package com.example.quizapp

data class Question(
    val questionText: String, // 질문
    val options: List<String>, // 선택지
    val correctAnswer: String, // 정답
    val explanation : String // 설명
)