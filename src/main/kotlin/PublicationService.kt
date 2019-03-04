import io.reactivex.Observable

sealed class Publication(val title: String, val description: String, val siteArea: SiteArea)
class ReadOnlyPublication(title: String, description: String, siteArea: SiteArea) :
    Publication(title, description, siteArea)

class EditablePublication(title: String, description: String, siteArea: SiteArea) :
    Publication(title, description, siteArea)

class PublicationService(private val publications: List<ReadOnlyPublication> = defaultPublications()) {

    fun publications(user: User): Observable<Publication> = when (user) {
        is Root -> TODO()
        is Editor -> TODO()
        is AllAccess -> TODO()
        is Standard -> TODO()
        NotLoggedIn -> TODO()
    }
}
