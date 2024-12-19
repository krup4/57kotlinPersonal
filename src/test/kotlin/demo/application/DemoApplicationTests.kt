package demo.application

import demo.application.client.UserClient
import demo.application.dto.Form
import demo.application.dto.UserRegister
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpStatusCode

@SpringBootTest
class DemoApplicationTests {
    @Autowired
//    @MockBean
    private lateinit var userClient: UserClient

    @Test
    fun registration() {
        val request = UserRegister(login = "pupupu", password = "123456789")
        val response = userClient.registerUser(request)
        Assertions.assertEquals(HttpStatusCode.valueOf(200), response.statusCode)
    }

    @Test
    fun editUser() {
        val registrationRequest = UserRegister(login = "pupupu1", password = "123456789")
        val registrationResponse = userClient.registerUser(registrationRequest)
        Assertions.assertEquals(HttpStatusCode.valueOf(200), registrationResponse.statusCode)

        val editRequest = Form(gender = Form.Gender.MALE, age = 52, lastName = "Ivanov", firstName = "Ivan")
        val editResponse = userClient.updateUser(editRequest, registrationResponse.body!!.token)
        Assertions.assertEquals(HttpStatusCode.valueOf(200), editResponse.statusCode)
    }
}