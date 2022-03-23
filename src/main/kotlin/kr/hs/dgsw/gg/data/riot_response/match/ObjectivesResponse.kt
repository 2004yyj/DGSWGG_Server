package kr.hs.dgsw.gg.data.riot_response.match

class ObjectivesResponse(
        val baron: ObjectiveResponse,
        val champion: ObjectiveResponse,
        val dragon: ObjectiveResponse,
        val inhibitor: ObjectiveResponse,
        val riftHerald: ObjectiveResponse,
        val tower: ObjectiveResponse
    )