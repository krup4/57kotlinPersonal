package demo.application.controller

import demo.application.dto.Form
import demo.application.dto.UserRegister
import demo.application.service.RegisterService
import org.springframework.web.bind.annotation.*

@RestController
class RegisterController (
    val registerService: RegisterService
) {

    @PostMapping("/users")
    fun register(@RequestBody userRegister: UserRegister) = registerService.register(userRegister)

    @PutMapping("/users")
    fun addInfo(@RequestBody form: Form, @RequestHeader("Authorization") token: String) = registerService.addInfo(form, token)
}