import io.reactivex.Single

sealed class Publication(val title: String, val description: String, val siteArea: SiteArea)
class ReadOnlyPublication(title: String, description: String, siteArea: SiteArea) :
    Publication(title, description, siteArea)

class EditablePublication(title: String, description: String, siteArea: SiteArea) :
    Publication(title, description, siteArea)

class PublicationService {

    private val publications = listOf(
        ReadOnlyPublication(
            "The Economist",
            "Authoritative insight and opinion on international news",
            SiteArea.ENTERPRISE
        ),
        ReadOnlyPublication("Fortune", "Fortune 500 Daily & Breaking Business News", SiteArea.ENTERPRISE),
        ReadOnlyPublication("Fast Company", "World's leading progressive business media brand", SiteArea.ENTERPRISE),
        ReadOnlyPublication(
            "PLOS One",
            "Peer-reviewed open access scientific journal published by the Public Library of Science",
            SiteArea.ACADEMIC
        ),
        ReadOnlyPublication(
            "Nature",
            "Nature is the world's leading multidisciplinary science journal",
            SiteArea.ACADEMIC
        ),
        ReadOnlyPublication("New Scientist", "Science news and science articles from New Scientist", SiteArea.ACADEMIC)
    )

    fun publications(user: User): Single<List<Publication>> = when (user) {
        is Root -> TODO()
        is Editor -> TODO()
        is AllAccess -> TODO()
        is Standard -> TODO()
        NotLoggedIn -> TODO()
    }
}
