package us.timinc.mc.cobblemon.unimplementeditems

object ErrorMessages {
    private fun buildErrorMessage(name: String): String {
        return "${UnimplementedItems.MOD_ID}.errors.$name"
    }

    val notPokemon by lazy { buildErrorMessage("notPokemon") }
    val notYourPokemon by lazy { buildErrorMessage("notYourPokemon") }
    val onlyOneCommonAbility by lazy { buildErrorMessage("onlyOneCommonAbility") }
    val alreadyPerfectIv by lazy { buildErrorMessage("alreadyPerfectIv") }
    val alreadyPerfectIvs by lazy { buildErrorMessage("alreadyPerfectIvs") }
    val noHiddenAbility by lazy { buildErrorMessage("noHiddenAbility") }
    val alreadyHasHiddenAbility by lazy { buildErrorMessage("alreadyHasHiddenAbility") }
    val alreadyHasZeroEvs by lazy { buildErrorMessage("alreadyHasZeroEvs") }
}
