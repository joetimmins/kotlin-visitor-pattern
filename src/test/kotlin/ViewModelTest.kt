class ViewModelTest {
//    @Test
//    fun `root user has full editable list`() {
//        val root: UserInput = UserInput("Joe", "aeFRa4tZYy")
//        val expectedPublications = listOf(
//            theEconomist,
//            fortune,
//            fastCompany,
//            plosOne,
//            nature,
//            newScientist
//        ).asEditablePublications()
//        val expectedUiState = UiState("Joe", expectedPublications)
//
//        val viewModel = createViewModel()
//        viewModel.userInputEvents.onNext(root)
//        val actualUiState = viewModel.uiState.test()
//
//        actualUiState.assertValuesOnly(expectedUiState)
//    }
//
//    @Test
//    fun `academic editor can edit publications in the academic area`() {
//        val expectedPublications = listOf(
//            plosOne,
//            nature,
//            newScientist
//        ).asEditablePublications()
//        val viewModel = createViewModel()
//
//        val actualPublications = service.publications(academicEditor).test()
//        actualPublications.assertValueSequence(expectedPublications)
//    }
//
//    @Test
//    fun `enterprise editor can edit publications in the enterprise area`() {
//        val expectedPublications = listOf(
//            theEconomist,
//            fortune,
//            fastCompany
//        ).asEditablePublications()
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
//
//    private fun List<ReadOnly>.asEditablePublications() = map { it.toEditablePublication() }

    private fun createViewModel() = ViewModel()
}
