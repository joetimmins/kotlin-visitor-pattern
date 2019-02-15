sealed class User

data class Root(val name: String, val accessToken: String) : User()
data class Editor(val name: String, val siteArea: SiteArea, val accessToken: String) : User()
data class AllAccess(val name: String, val accessToken: String) : User()
data class Standard(val name: String, val siteArea: SiteArea, val accessToken: String) : User()
object NotLoggedIn : User()

enum class SiteArea {
    ACADEMIC,
    ENTERPRISE
}
