import io.reactivex.Observable

sealed class User

data class Root(val name: String, val accessToken: String) : User()
data class Editor(val name: String, val siteArea: SiteArea, val accessToken: String) : User()
data class AllAccess(val name: String, val accessToken: String) : User()
data class Standard(val name: String, val siteArea: SiteArea, val accessToken: String) : User()
object NotLoggedIn : User()

class UserService {

    private val rootUser: User = Root("Joe", "aeFRa4tZYy")
    private val academicEditor: User = Editor("Bob", SiteArea.ACADEMIC, "Hv6kwbnKJb")
    private val enterpriseEditor: User = Editor("Tim", SiteArea.ENTERPRISE, "ehW3tQGxdc")
    private val allAccess: User = AllAccess("Kathleen", "ADroRMIoLL")
    private val academicViewer: User = Standard("Cora", SiteArea.ACADEMIC, "DxCdvGYM2G")
    private val enterpriseViewer: User = Standard("Mike", SiteArea.ENTERPRISE, "fU57LODMNU")

    fun retrieveUser(username: String, password: String) =
        Observable.fromCallable {
            when (username) {
                "Joe" -> rootUser
                "Bob" -> academicEditor
                "Tim" -> enterpriseEditor
                "Kathleen" -> allAccess
                "Cora" -> academicViewer
                "Mike" -> enterpriseViewer
                else -> NotLoggedIn
            }
        }

}
