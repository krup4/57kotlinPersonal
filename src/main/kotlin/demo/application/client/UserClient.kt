package demo.application.client

import demo.application.dto.Form
import demo.application.dto.UserRegister
import demo.application.response.FormResponse
import demo.application.response.UserResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@FeignClient(name = "userClient", url = "http://localhost:8080/")
interface UserClient {

    @PostMapping(value = ["/users"])
    fun registerUser(@RequestBody user: UserRegister): ResponseEntity<UserResponse>

    @PutMapping(value = ["/users"])
    fun updateUser(@RequestBody form: Form, @RequestHeader("Authorization") token: String): ResponseEntity<FormResponse>
}
