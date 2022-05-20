package dev.matyaqubov.googletranslateclone.model

data class TranslationResponse(
    val data: DataTranslation
)

data class DataTranslation(
    val translations: List<Translation>
)

data class Translation(
    val translatedText: String
)