package demo.application.dto

data class Form(
    val gender: Gender,
    val age: Int,
    val lastName: String,
    val firstName: String
) {

    enum class Gender {
        MALE, FEMALE;
    }

}
