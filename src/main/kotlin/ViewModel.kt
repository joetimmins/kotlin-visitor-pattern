import io.reactivex.subjects.PublishSubject

class ViewModel(
    private val userService: UserService = UserService(),
    private val publicationService: PublicationService = PublicationService(),
    val userInputEvents: PublishSubject<UserInput>
) {



}
