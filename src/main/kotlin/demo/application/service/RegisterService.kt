package demo.application.service

import demo.application.dto.Form
import demo.application.dto.User
import demo.application.dto.UserRegister
import demo.application.repository.UserRepository
import demo.application.response.FormResponse
import demo.application.response.UserResponse
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class RegisterService(
    val userRepository: UserRepository
) {

    private fun generateRandomString(length: Int): String {
        val chars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        return (1..length)
            .map { chars.random() }
            .joinToString("")
    }

    fun register(userRegister: UserRegister) : ResponseEntity<UserResponse> {
        val token = generateRandomString(Random.nextInt(15, 25))
        val id = userRepository.register(userRegister, token)
        return ResponseEntity(UserResponse(id, userRegister.login, token), HttpStatus.OK)
    }

    fun addInfo(form: Form, token: String): ResponseEntity<FormResponse> {
        val id = userRepository.addInfo(form, token)
        return ResponseEntity(FormResponse(id), HttpStatus.OK)
    }
}