val theEconomist = ReadOnlyPublication(
    "The Economist",
    "Authoritative insight and opinion on international news",
    SiteArea.ENTERPRISE
)

val fortune = ReadOnlyPublication("Fortune", "Fortune 500 Daily & Breaking Business News", SiteArea.ENTERPRISE)

val fastCompany =
    ReadOnlyPublication("Fast Company", "World's leading progressive business media brand", SiteArea.ENTERPRISE)

val plosOne = ReadOnlyPublication(
    "PLOS One",
    "Peer-reviewed open access scientific journal published by the Public Library of Science",
    SiteArea.ACADEMIC
)

val nature = ReadOnlyPublication(
    "Nature",
    "Nature is the world's leading multidisciplinary science journal",
    SiteArea.ACADEMIC
)

val newScientist =
    ReadOnlyPublication("New Scientist", "Science news and science articles from New Scientist", SiteArea.ACADEMIC)

val defaultPublications = listOf(
    theEconomist,
    fortune,
    fastCompany,
    plosOne,
    nature,
    newScientist
)

fun ReadOnlyPublication.toEditablePublication() = EditablePublication(title, description, siteArea)
