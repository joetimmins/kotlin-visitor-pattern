val theEconomist = Publication(
    "The Economist",
    "Authoritative insight and opinion on international news",
    SiteArea.ENTERPRISE
)

val fortune = Publication("Fortune", "Fortune 500 Daily & Breaking Business News", SiteArea.ENTERPRISE)

val fastCompany =
    Publication("Fast Company", "World's leading progressive business media brand", SiteArea.ENTERPRISE)

val plosOne = Publication(
    "PLOS One",
    "Peer-reviewed open access scientific journal published by the Public Library of Science",
    SiteArea.ACADEMIC
)

val nature = Publication(
    "Nature",
    "Nature is the world's leading multidisciplinary science journal",
    SiteArea.ACADEMIC
)

val newScientist =
    Publication("New Scientist", "Science news and science articles from New Scientist", SiteArea.ACADEMIC)

val defaultPublications = listOf(
    theEconomist,
    fortune,
    fastCompany,
    plosOne,
    nature,
    newScientist
)
