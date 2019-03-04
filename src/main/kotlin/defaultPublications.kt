fun defaultPublications(): List<ReadOnlyPublication> {
    return listOf(
        ReadOnlyPublication(
            "The Economist",
            "Authoritative insight and opinion on international news",
            SiteArea.ENTERPRISE
        ),
        ReadOnlyPublication("Fortune", "Fortune 500 Daily & Breaking Business News", SiteArea.ENTERPRISE),
        ReadOnlyPublication("Fast Company", "World's leading progressive business media brand", SiteArea.ENTERPRISE),
        ReadOnlyPublication(
            "PLOS One",
            "Peer-reviewed open access scientific journal published by the Public Library of Science",
            SiteArea.ACADEMIC
        ),
        ReadOnlyPublication(
            "Nature",
            "Nature is the world's leading multidisciplinary science journal",
            SiteArea.ACADEMIC
        ),
        ReadOnlyPublication("New Scientist", "Science news and science articles from New Scientist", SiteArea.ACADEMIC)
    )
}

fun ReadOnlyPublication.toEditablePublication() = EditablePublication(title, description, siteArea)
