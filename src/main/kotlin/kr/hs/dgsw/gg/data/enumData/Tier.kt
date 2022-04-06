package kr.hs.dgsw.gg.data.enumData

enum class Tier(val tierId: Int, val tierStr: String) {
    CHALLENGER(1, "Challenger"),
    GRANDMASTER(2, "GrandMaster"),
    MASTER(3, "Master"),
    DIAMOND(4, "Diamond"),
    PLATINUM(5, "Platinum"),
    GOLD(6, "Gold"),
    SILVER(7, "Silver"),
    BRONZE(8, "Bronze"),
    IRON(9, "Iron"),
    UNRANKED(10, "Unranked")
}