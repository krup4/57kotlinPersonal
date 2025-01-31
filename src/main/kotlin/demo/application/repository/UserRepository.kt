package demo.application.repository

import demo.application.request.Form
import demo.application.dto.Profile
import demo.application.dto.User
import demo.application.request.UserRegister
import org.springframework.stereotype.Component

@Component
class UserRepository(
    val userRepository: MutableList<User>

) {
    private fun convertFormGenderToUserGender(formGender: Form.Gender): User.Gender {
        return when (formGender) {
            Form.Gender.MALE -> User.Gender.MALE
            Form.Gender.FEMALE -> User.Gender.FEMALE
        }
    }

    fun getUserByToken(token: String): User? {
        return userRepository.find { it.token == token }
    }

    fun getUsersProfile(page: Int, size: Int, sortBy: String) : List<Profile> {
        return userRepository.map { user ->
            Profile(
                age=user.age ?: 0,
                lastName=user.lastName ?: "",
                firstName = user.firstName ?: "",
                photoUrl = user.photo ?: ""
            )
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
                user.photo=form.photo
            }
        }
        return id
    }
}