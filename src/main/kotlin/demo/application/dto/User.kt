package demo.application.dto

data class User(
    val id: Int,
    val token: String,
    val login : String,
    var password: String,
    var gender: Gender? = null,
    var age: Int? = null,
    var lastName: String? = null,
    var firstName: String? = null,
    var photo: String? = null
) {

    enum class Gender {
        MALE, FEMALE;
    }

}