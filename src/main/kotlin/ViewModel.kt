import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.PublishSubject

class ViewModel(
    private val userService: UserService = UserService(),
    private val publicationService: PublicationService = PublicationService(),
    private val _uiState: PublishSubject<UiState> = PublishSubject.create(),
    val uiState: Observable<UiState> = _uiState,
    val userInputEvents: PublishSubject<UserInput> = PublishSubject.create()
) {

    private val disposables = CompositeDisposable()

    init {
        val retrievedUser = userInputEvents
            .flatMapSingle { userInput -> userService.retrieveUser(userInput) }
            .flatMapSingle { retrievePublicationsAsBio(it) }
            .map { it.convertToUiState() }

        disposables += retrievedUser.subscribeBy(
            onNext = { _uiState.onNext(it) },
            onError = { _uiState.onError(it) }
        )
    }

    private fun retrievePublicationsAsBio(user: User): Single<BullshitIntermediateObject> =
        publicationService.publications(user)
            .reduce(BullshitIntermediateObject(user.name, emptyList()),
                { bio, publication -> bio.addPublication(publication) }
            )


    data class BullshitIntermediateObject(val name: String, val publications: List<Publication>) {
        fun addPublication(publication: Publication) = copy(
            name = name,
            publications = publications.plusElement(publication)
        )
    }

    private fun BullshitIntermediateObject.convertToUiState(): UiState {
        val editables = publications.filter { it.isEditable }.map { Editable(it) }
        val readOnlies = publications.filter { !it.isEditable }.map { ReadOnly(it) }
        return UiState(name, editables, readOnlies)
    }
}

data class Editable(
    private val publication: Publication,
    val title: String = publication.title,
    val description: String = publication.description,
    val siteArea: SiteArea = publication.siteArea
)

data class ReadOnly(
    private val publication: Publication,
    val title: String = publication.title,
    val description: String = publication.description,
    val siteArea: SiteArea = publication.siteArea
)

data class UiState(
    val name: String,
    val editables: List<Editable>,
    val readOnlies: List<ReadOnly>
)

data class UserInput(val username: String, val password: String)
