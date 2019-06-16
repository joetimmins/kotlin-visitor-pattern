import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class ViewModel(
    private val userService: UserService = UserService(),
    private val publicationService: PublicationService = PublicationService(),
    val userInputEvents: PublishSubject<UserInput> = PublishSubject.create()
) {
    private val _uiState: PublishSubject<UiState> = PublishSubject.create()
    val uiState: Observable<UiState> = _uiState


}

data class ReadOnly(
    private val publication: Publication,
    val title: String = publication.title,
    val description: String = publication.description,
    val siteArea: SiteArea = publication.siteArea
)

data class Editable(
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
