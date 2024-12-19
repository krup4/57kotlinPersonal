package demo.application

import demo.application.client.UserClient
import demo.application.dto.Form
import demo.application.dto.User
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class DemoApplicationTests {
    @Autowired
    lateinit var userClient: UserClient

    @Test
    fun registration() {
        val request = User(login = "pupupu", password = "123456789")
        val response = userClient.registerUser(request)
        Assertions.assertEquals(200, response.statusCode)
    }

    @Test
    fun editUser() {
        val registrationRequest = User(login = "pupupu", password = "123456789")
        val registrationResponse = userClient.registerUser(registrationRequest)
        Assertions.assertEquals(200, registrationResponse.statusCode)

        val editRequest = Form(gender = Form.Gender.MALE, age = 52, lastName = "Ivanov", firstName = "Ivan")
        val editResponse = userClient.updateUser(editRequest, registrationResponse.body!!.token)
        Assertions.assertEquals(200, editResponse.statusCode)
    }
}