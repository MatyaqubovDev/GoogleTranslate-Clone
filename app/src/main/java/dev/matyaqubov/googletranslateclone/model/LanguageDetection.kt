package dev.matyaqubov.googletranslateclone.model

data class LanguageDetection(
    val data: Data
)

data class Data(
    val detections: List<List<Detection>>
)

data class Detection(
    val language: String,
    val confidence: Long,
    val isReliable: Boolean
)