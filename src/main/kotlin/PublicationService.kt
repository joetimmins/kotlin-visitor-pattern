import io.reactivex.Observable

sealed class Publication(open val title: String, open val description: String, open val siteArea: SiteArea)
data class ReadOnlyPublication(
    override val title: String,
    override val description: String,
    override val siteArea: SiteArea
) : Publication(title, description, siteArea)

data class EditablePublication(
    override val title: String,
    override val description: String,
    override val siteArea: SiteArea
) : Publication(title, description, siteArea)

class PublicationService(private val publications: List<ReadOnlyPublication> = defaultPublications) {

    fun publications(user: User): Observable<Publication> = when (user) {
        is Root -> publications
            .map { it.toEditablePublication() }
            .toObservable()
        is Editor -> publications
            .filter { it.siteArea == user.siteArea }
            .map { it.toEditablePublication() }
            .toObservable()
        is AllAccess -> publications.toObservable()
        is Standard -> publications
            .filter { it.siteArea == user.siteArea }
            .toObservable()
        NotLoggedIn -> Observable.empty()
    }
}

private fun <E> List<E>.toObservable(): Observable<E> = Observable.fromIterable(this)
