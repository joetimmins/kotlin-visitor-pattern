import org.junit.Test

class PublicationServiceTest {
    @Test
    fun `root user has full editable list`() {
        val expectedPublications = defaultPublications().map { it.toEditablePublication() }
        val service = createService()

        val publications = service.publications(rootUser).test()

        publications.assertValueSequence(expectedPublications)
    }



    private fun createService() = PublicationService(defaultPublications())
}
