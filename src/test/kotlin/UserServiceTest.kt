import org.junit.Test

private const val password = "password"

class UserServiceTest {
    @Test
    fun `user called Joe is a root user`() {
        val observer = UserService().retrieveUser("Joe", password).test()

        observer.assertValue { it is Root }
    }
}
