import io.reactivex.Single

data class Publication(val title: String, val description: String, val siteArea: SiteArea)

class PublicationService {

    private val publications = listOf(
        Publication("The Economist", "Authoritative insight and opinion on international news", SiteArea.ENTERPRISE),
        Publication("Fortune", "Fortune 500 Daily & Breaking Business News", SiteArea.ENTERPRISE),
        Publication("Fast Company", "World's leading progressive business media brand", SiteArea.ENTERPRISE),
        Publication(
            "PLOS One",
            "Peer-reviewed open access scientific journal published by the Public Library of Science",
            SiteArea.ACADEMIC
        ),
        Publication("Nature", "Nature is the world's leading multidisciplinary science journal", SiteArea.ACADEMIC),
        Publication("New Scientist", "Science news and science articles from New Scientist", SiteArea.ACADEMIC)
    )

    fun publications(user: User): Single<List<Publication>> = when (user) {
        is Root -> TODO()
        is Editor -> TODO()
        is AllAccess -> TODO()
        is Standard -> TODO()
        NotLoggedIn -> TODO()
    }
}
