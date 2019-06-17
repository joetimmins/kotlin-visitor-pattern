import io.reactivex.Observable
import io.reactivex.Single

interface User {
    val name: String
    fun resolvePublications(visitor: PublicationResolver): Observable<Publication>
}

data class Root(override val name: String, val accessToken: String) : User {
    override fun resolvePublications(visitor: PublicationResolver) = visitor.visit(this)
}

data class Editor(override val name: String, val siteArea: SiteArea, val accessToken: String) : User {
    override fun resolvePublications(visitor: PublicationResolver) = visitor.visit(this)
}

data class AllAccess(override val name: String, val accessToken: String) : User {
    override fun resolvePublications(visitor: PublicationResolver) = visitor.visit(this)
}

data class Standard(override val name: String, val siteArea: SiteArea, val accessToken: String) : User {
    override fun resolvePublications(visitor: PublicationResolver) = visitor.visit(this)
}

object NotLoggedIn : User {
    override val name: String
        get() = "Not logged in"

    override fun resolvePublications(visitor: PublicationResolver) = visitor.visit(this)
}

val rootUser: User = Root("Joe", "aeFRa4tZYy")
val academicEditor: User = Editor("Bob", SiteArea.ACADEMIC, "Hv6kwbnKJb")
val enterpriseEditor: User = Editor("Tim", SiteArea.ENTERPRISE, "ehW3tQGxdc")
val allAccessViewer: User = AllAccess("Kathleen", "ADroRMIoLL")
val academicViewer: User = Standard("Cora", SiteArea.ACADEMIC, "DxCdvGYM2G")
val enterpriseViewer: User = Standard("Mike", SiteArea.ENTERPRISE, "fU57LODMNU")

class UserService {

    fun retrieveUser(userInput: UserInput) =
        Single.fromCallable {
            when (userInput.username) {
                "Joe" -> rootUser
                "Bob" -> academicEditor
                "Tim" -> enterpriseEditor
                "Kathleen" -> allAccessViewer
                "Cora" -> academicViewer
                "Mike" -> enterpriseViewer
                else -> NotLoggedIn
            }
        }

}
