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

    private val handler = UserVisitor(publications)

    fun publications(user: User): Observable<Publication> = user.accept(handler)
}

private class UserVisitor(private val publications: List<ReadOnlyPublication>) {
    fun visit(user: Root): Observable<Publication> = publications
        .map { it.toEditablePublication() }
        .toObservable()

    fun visit(user: Editor): Observable<Publication> = publications
        .filter { it.siteArea == user.siteArea }
        .map { it.toEditablePublication() }
        .toObservable()

    fun visit(user: AllAccess): Observable<Publication> = publications.toObservable()

    fun visit(user: Standard): Observable<Publication> = publications
        .filter { it.siteArea == user.siteArea }
        .toObservable()

    fun visit(user: NotLoggedIn): Observable<Publication> = Observable.empty<Publication>()
}

private fun <E> List<E>.toObservable(): Observable<E> = Observable.fromIterable(this)
