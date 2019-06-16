import org.junit.Test

class PublicationServiceTest {
    @Test
    fun `root user has full editable list`() {
        val expectedPublications = listOf(
            theEconomist,
            fortune,
            fastCompany,
            plosOne,
            nature,
            newScientist
        ).asEditablePublications()
        val service = createService()

        val actualPublications = service.publications(rootUser).test()
        actualPublications.assertValueSequence(expectedPublications)
    }

    @Test
    fun `academic editor can edit publications in the academic area`() {
        val expectedPublications = listOf(
            plosOne,
            nature,
            newScientist
        ).asEditablePublications()
        val service = createService()

        val actualPublications = service.publications(academicEditor).test()
        actualPublications.assertValueSequence(expectedPublications)
    }

    @Test
    fun `enterprise editor can edit publications in the enterprise area`() {
        val expectedPublications = listOf(
            theEconomist,
            fortune,
            fastCompany
        ).asEditablePublications()
        val service = createService()

        val actualPublications = service.publications(enterpriseEditor).test()
        actualPublications.assertValueSequence(expectedPublications)
    }

    @Test
    fun `all-access viewer can view all areas, but not edit`() {
        val expectedPublications = listOf(
            theEconomist,
            fortune,
            fastCompany,
            plosOne,
            nature,
            newScientist
        )
        val service = createService()

        val actualPublications = service.publications(allAccessViewer).test()
        actualPublications.assertValueSequence(expectedPublications)
    }

    @Test
    fun `academic viewer can view academic publications, but not edit`() {
        val expectedPublications = listOf(
            plosOne,
            nature,
            newScientist
        )
        val service = createService()

        val actualPublications = service.publications(academicViewer).test()
        actualPublications.assertValueSequence(expectedPublications)
    }

    @Test
    fun `enterprise viewer can view enterprise publications, but not edit`() {
        val expectedPublications = listOf(
            theEconomist,
            fortune,
            fastCompany
        )
        val service = createService()

        val actualPublications = service.publications(enterpriseViewer).test()
        actualPublications.assertValueSequence(expectedPublications)
    }

    @Test
    fun `no publications are returned when user is not logged in`() {
        val service = createService()

        val actualPublications = service.publications(NotLoggedIn).test()
        actualPublications.assertNoValues()
    }

    private fun List<Publication>.asEditablePublications() = map { it.toEditablePublication() }

    private fun createService() = PublicationService(defaultPublications)
}

private fun Publication.toEditablePublication(): Publication = copy(title, description, siteArea, true)
