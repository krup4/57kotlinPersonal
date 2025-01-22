package demo.application.service

import demo.application.dto.Profile
import demo.application.repository.ReactionRepository
import demo.application.request.Form
import demo.application.request.UserRegister
import demo.application.repository.UserRepository
import demo.application.request.ReactionRequest
import demo.application.response.FormResponse
import demo.application.response.ReactionResponse
import demo.application.response.UserResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class UserService(
    val userRepository: UserRepository,
    val reactionRepository: ReactionRepository
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
        return ResponseEntity(FormResponse(id, form), HttpStatus.OK)
    }

    fun getUsers(page: Int, size: Int, sortBy: String, token: String): ResponseEntity<List<Profile>> {
        if (userRepository.getUserByToken(token) == null) {
            return ResponseEntity(listOf(), HttpStatus.NOT_FOUND)
        }
        val profiles = userRepository.getUsersProfile(page, size, sortBy)
        return ResponseEntity(profiles, HttpStatus.OK)
    }

    fun react(reaction: ReactionRequest, id: Int, token: String) : ResponseEntity<ReactionResponse> {
        val user = userRepository.getUserByToken(token)
        if (user == null) {
            return ResponseEntity(ReactionResponse(false), HttpStatus.NOT_FOUND)
        }
        reactionRepository.react(reaction.reaction, id, user.id)
        return ResponseEntity(ReactionResponse(reaction.reaction), HttpStatus.OK)
    }
}