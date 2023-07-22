package us.timinc.mc.cobblemon.unimplementeditems

object ErrorMessages {
    private fun buildErrorMessage(name: String): String {
        return "${UnimplementedItems.MOD_ID}.errors.${name}"
    }

    val notPokemon = buildErrorMessage("notPokemon")
    val notYourPokemon = buildErrorMessage("notYourPokemon")
    val onlyOneCommonAbility = buildErrorMessage("onlyOneCommonAbility")
    val alreadyPerfectIv = buildErrorMessage("alreadyPerfectIv")
    val alreadyPerfectIvs = buildErrorMessage("alreadyPerfectIvs")
    val alreadyPerfectHp = buildErrorMessage("alreadyPerfectHp")
}