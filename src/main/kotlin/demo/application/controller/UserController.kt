package demo.application.controller

import demo.application.request.Form
import demo.application.request.ReactionRequest
import demo.application.request.UserRegister
import demo.application.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
class UserController (
    val userService: UserService
) {

    @PostMapping("/users")
    fun register(@RequestBody userRegister: UserRegister) = userService.register(userRegister)

    @PutMapping("/users")
    fun addInfo(@RequestBody form: Form, @RequestHeader("Authorization") token: String) = userService.addInfo(form, token)

    @GetMapping("/users")
    fun getUsers(@PathVariable page: Int, @PathVariable size: Int, @PathVariable sortBy: String, @RequestHeader("Authorization") token: String) = userService.getUsers(page, size, sortBy, token)

    @PostMapping("/users/{id}/reaction")
    fun reactUser(@RequestBody reaction: ReactionRequest, @PathVariable id: Int, @RequestHeader("Authorization") token: String) = userService.react(reaction, id, token)
}