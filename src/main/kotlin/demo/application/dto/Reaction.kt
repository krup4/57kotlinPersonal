package demo.application.dto

data class Reaction(
    val id: Int,
    val userFromId: Int,
    val userToId: Int,
    var reaction: Boolean
)
