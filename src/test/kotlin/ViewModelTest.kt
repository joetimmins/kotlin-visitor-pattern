import org.junit.Test

class ViewModelTest {
    @Test
    fun `root user has full editable list`() {
        val root = UserInput("Joe", "aeFRa4tZYy")
        val expectedEditables = listOf(
            theEconomist,
            fortune,
            fastCompany,
            plosOne,
            nature,
            newScientist
        ).asEditables()
        val expectedUiState = UiState("Joe", expectedEditables, emptyList())

        val viewModel = createViewModel()
        viewModel.userInputEvents.onNext(root)
        val actualUiState = viewModel.uiState.test()

        actualUiState.assertValuesOnly(expectedUiState)
    }

    @Test
    fun `academic editor can edit publications in the academic area`() {
        val academicEditor = UserInput("Bob", "Hv6kwbnKJb")
        val expectedEditables = listOf(
            plosOne,
            nature,
            newScientist
        ).asEditables()
        val expectedReadOnlies = listOf(
            theEconomist,
            fortune,
            fastCompany
        ).asReadOnlies()
        val expectedUiState = UiState("Bob", expectedEditables, expectedReadOnlies)

        val viewModel = createViewModel()
        viewModel.userInputEvents.onNext(academicEditor)
        val actualUiState = viewModel.uiState.test()

        actualUiState.assertValuesOnly(expectedUiState)
    }

//    @Test
//    fun `enterprise editor can edit publications in the enterprise area`() {
//        val expectedPublications = listOf(
//            theEconomist,
//            fortune,
//            fastCompany
//        ).asEditables()
//        val viewModel = createViewModel()
//
//        val actualPublications = service.publications(enterpriseEditor).test()
//        actualPublications.assertValueSequence(expectedPublications)
//    }
//
//    @Test
//    fun `all-access viewer can view all areas, but not edit`() {
//        val expectedPublications = listOf(
//            theEconomist,
//            fortune,
//            fastCompany,
//            plosOne,
//            nature,
//            newScientist
//        )
//        val viewModel = createViewModel()
//
//        val actualPublications = service.publications(allAccessViewer).test()
//        actualPublications.assertValueSequence(expectedPublications)
//    }
//
//    @Test
//    fun `academic viewer can view academic publications, but not edit`() {
//        val expectedPublications = listOf(
//            plosOne,
//            nature,
//            newScientist
//        )
//        val viewModel = createViewModel()
//
//        val actualPublications = service.publications(academicViewer).test()
//        actualPublications.assertValueSequence(expectedPublications)
//    }
//
//    @Test
//    fun `enterprise viewer can view enterprise publications, but not edit`() {
//        val expectedPublications = listOf(
//            theEconomist,
//            fortune,
//            fastCompany
//        )
//        val viewModel = createViewModel()
//
//        val actualPublications = service.publications(enterpriseViewer).test()
//        actualPublications.assertValueSequence(expectedPublications)
//    }
//
//    @Test
//    fun `no publications are returned when user is not logged in`() {
//        val viewModel = createViewModel()
//
//        val actualPublications = service.publications(NotLoggedIn).test()
//        actualPublications.assertNoValues()
//    }

    private fun createViewModel() = ViewModel()
}

private fun List<Publication>.asEditables() = map { it.toEditable() }
private fun Publication.toEditable(): Editable = Editable(this)

private fun List<Publication>.asReadOnlies() = map { it.toReadOnly() }
private fun Publication.toReadOnly(): ReadOnly = ReadOnly(this)

