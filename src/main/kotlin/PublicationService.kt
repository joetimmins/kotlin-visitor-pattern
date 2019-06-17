import io.reactivex.Observable

data class Publication(
    val title: String,
    val description: String,
    val siteArea: SiteArea,
    val isEditable: Boolean = false
)

enum class SiteArea {
    ACADEMIC,
    ENTERPRISE
}

class PublicationService(
    publications: List<Publication> = defaultPublications,
    private val publicationResolver: PublicationResolver = PublicationResolver(publications)
) {

    fun publications(user: User): Observable<Publication> = user.resolvePublications(publicationResolver)
}

class PublicationResolver(private val publications: List<Publication>) {
    fun visit(user: Root): Observable<Publication> = publications
        .map { it.makeEditable() }
        .toObservable()

    fun visit(user: Editor): Observable<Publication> = publications
        .map { if (it.siteArea == user.siteArea) it.makeEditable() else it }
        .toObservable()

    fun visit(user: AllAccess): Observable<Publication> = publications.toObservable()

    fun visit(user: Standard): Observable<Publication> = publications
        .filter { it.siteArea == user.siteArea }
        .toObservable()

    fun visit(user: NotLoggedIn): Observable<Publication> = Observable.empty<Publication>()
}

private fun Publication.makeEditable() = copy(
    title = title,
    description = description,
    siteArea = siteArea,
    isEditable = true
)


private fun <E> List<E>.toObservable(): Observable<E> = Observable.fromIterable(this)
