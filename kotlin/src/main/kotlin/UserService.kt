import io.reactivex.Observable

class UserService {

    private val rootUser: User = Root("Joe", "aeFRa4tZYy")
    private val academicEditor: User = Editor("Bob", SiteArea.ACADEMIC, "Hv6kwbnKJb")
    private val enterpriseEditor: User = Editor("Tim", SiteArea.ENTERPRISE, "ehW3tQGxdc")
    private val allAccess: User = AllAccess("Kathleen", "ADroRMIoLL")
    private val academicViewer: User = Standard("Cora", SiteArea.ACADEMIC, "DxCdvGYM2G")
    private val enterpriseViewer: User = Standard("Mike", SiteArea.ENTERPRISE, "fU57LODMNU")

    fun retrieveUser(username: String, password: String): Observable<User> =
        when (username) {
            "Joe" -> Observable.just(rootUser)
            "Bob" -> Observable.just(academicEditor)
            "Tim" -> Observable.just(enterpriseEditor)
            "Kathleen" -> Observable.just(allAccess)
            "Cora" -> Observable.just(academicViewer)
            "Mike" -> Observable.just(enterpriseViewer)
            else -> Observable.just(NotLoggedIn)
        }

}
