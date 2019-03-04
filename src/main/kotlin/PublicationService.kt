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

class PublicationService(publications: List<ReadOnlyPublication> = defaultPublications) {

    private val handler = UserHandler(publications)

    fun publications(user: User): Observable<Publication> = user.dispatch(handler)
}

class UserHandler(private val publications: List<ReadOnlyPublication>) {
    fun handle(user: Root): Observable<Publication> = publications
        .map { it.toEditablePublication() }
        .toObservable()

    fun handle(user: Editor): Observable<Publication> = publications
        .filter { it.siteArea == user.siteArea }
        .map { it.toEditablePublication() }
        .toObservable()

    fun handle(user: AllAccess): Observable<Publication> = publications.toObservable()

    fun handle(user: Standard): Observable<Publication> = publications
        .filter { it.siteArea == user.siteArea }
        .toObservable()

    fun handle(user: NotLoggedIn): Observable<Publication> = Observable.empty<Publication>()
}

private fun <E> List<E>.toObservable(): Observable<E> = Observable.fromIterable(this)
