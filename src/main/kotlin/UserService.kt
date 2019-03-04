import io.reactivex.Observable
import io.reactivex.Single

interface User {
    fun accept(visitor: UserVisitor): Observable<Publication>
}

data class Root(val name: String, val accessToken: String) : User {
    override fun accept(visitor: UserVisitor) = visitor.visit(this)
}

data class Editor(val name: String, val siteArea: SiteArea, val accessToken: String) : User {
    override fun accept(visitor: UserVisitor) = visitor.visit(this)
}

data class AllAccess(val name: String, val accessToken: String) : User {
    override fun accept(visitor: UserVisitor) = visitor.visit(this)
}

data class Standard(val name: String, val siteArea: SiteArea, val accessToken: String) : User {
    override fun accept(visitor: UserVisitor) = visitor.visit(this)
}

object NotLoggedIn : User {
    override fun accept(visitor: UserVisitor) = visitor.visit(this)
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
