package demo.application.repository

import demo.application.dto.Reaction
import org.springframework.stereotype.Component

@Component
class ReactionRepository (
    val reactionRepository: MutableList<Reaction>
) {

    fun react(reaction: Boolean, userToId: Int, userFromId: Int) {
        val existingReaction = reactionRepository.find { it.userFromId == userFromId && it.userToId == userToId }

        if (existingReaction != null) {
            existingReaction.reaction = reaction
        } else {
            reactionRepository.add(Reaction(reactionRepository.size, userFromId, userToId, reaction))
        }
    }
}