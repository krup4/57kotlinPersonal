package demo.application.repository

import demo.application.dto.Form
import demo.application.dto.User
import demo.application.dto.UserRegister
import org.springframework.stereotype.Repository

@Repository
class UserRepository(
    val userRepository: MutableList<User>

) {
    private fun convertFormGenderToUserGender(formGender: Form.Gender): User.Gender {
        return when (formGender) {
            Form.Gender.MALE -> User.Gender.MALE
            Form.Gender.FEMALE -> User.Gender.FEMALE
        }
    }

    fun register(userRegister: UserRegister, token: String): Int {
        userRepository.add(User(id = userRepository.size, token = token, login = userRegister.login, password = userRegister.password))
        return userRepository.size - 1
    }

    fun addInfo(form: Form, token: String): Int {
        var id = -1
        userRepository.forEach { user ->
            if (user.token == token) {
                user.age = form.age
                user.gender = convertFormGenderToUserGender(form.gender)
                user.lastName = form.lastName
                user.firstName = form.firstName
                id = user.id
            }
        }
        return id
    }
}