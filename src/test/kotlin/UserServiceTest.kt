import org.junit.Test

private const val password = "password"

class UserServiceTest {
    private val joe = UserInput("Joe", password)

    @Test
    fun `user called Joe is a root user`() {
        val observer = UserService().retrieveUser(joe).test()

        observer.assertValue { it is Root }
    }
}
