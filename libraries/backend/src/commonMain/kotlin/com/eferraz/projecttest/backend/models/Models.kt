package com.eferraz.projecttest.backend.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class Pokemon(
    val id: Long,
    val name: String,
    @SerialName("base_experience")
    val baseExperience: Long,
    val height: Long,
    @SerialName("is_default")
    val isDefault: Boolean,
    val order: Long,
    val weight: Long,
    val abilities: List<Ability>,
    val forms: List<Form>,
    @SerialName("game_indices")
    val gameIndices: List<Index>,
    @SerialName("held_items")
    val heldItems: List<HeldItem>,
    @SerialName("location_area_encounters")
    val locationAreaEncounters: String,
    val moves: List<Mfe>,
    val species: Species,
    val sprites: Sprites,
    val cries: Cries,
    val stats: List<Stat>,
    val types: List<Type>,
    @SerialName("past_types")
    val pastTypes: List<PastType>,
)

@Serializable
internal data class Ability(
    @SerialName("is_hidden")
    val isHidden: Boolean,
    val slot: Long,
    val ability: Ability2,
)

@Serializable
internal data class Ability2(
    val name: String,
    val url: String,
)

@Serializable
internal data class Form(
    val name: String,
    val url: String,
)

@Serializable
internal data class Index(
    @SerialName("game_index")
    val gameIndex: Long,
    val version: Version,
)

@Serializable
internal data class Version(
    val name: String,
    val url: String,
)

@Serializable
internal data class HeldItem(
    val item: Item,
    @SerialName("version_details")
    val versionDetails: List<VersionDetail>,
)

@Serializable
internal data class Item(
    val name: String,
    val url: String,
)

@Serializable
internal data class VersionDetail(
    val rarity: Long,
    val version: Version2,
)

@Serializable
internal data class Version2(
    val name: String,
    val url: String,
)

@Serializable
internal data class Mfe(
    val move: Move,
    @SerialName("version_group_details")
    val versionGroupDetails: List<VersionGroupDetail>,
)

@Serializable
internal data class Move(
    val name: String,
    val url: String,
)

@Serializable
internal data class VersionGroupDetail(
    @SerialName("level_learned_at")
    val levelLearnedAt: Long,
    @SerialName("version_group")
    val versionGroup: VersionGroup,
    @SerialName("move_learn_method")
    val moveLearnMethod: MoveLearnMethod,
)

@Serializable
internal data class VersionGroup(
    val name: String,
    val url: String,
)

@Serializable
internal data class MoveLearnMethod(
    val name: String,
    val url: String,
)

@Serializable
internal data class Species(
    val name: String,
    val url: String,
)

@Serializable
internal data class Sprites(
    @SerialName("back_default")
    val backDefault: String,
    @SerialName("back_female")
    val backFemale: String?,
    @SerialName("back_shiny")
    val backShiny: String,
    @SerialName("back_shiny_female")
    val backShinyFemale: String?,
    @SerialName("front_default")
    val frontDefault: String?,
    @SerialName("front_female")
    val frontFemale: String?,
    @SerialName("front_shiny")
    val frontShiny: String,
    @SerialName("front_shiny_female")
    val frontShinyFemale: String?,
    val other: Other,
    val versions: Versions,
)

@Serializable
internal data class Other(
    @SerialName("dream_world")
    val dreamWorld: DreamWorld,
    val home: Home,
    @SerialName("official-artwork")
    val officialArtwork: OfficialArtwork,
    val showdown: Showdown,
)

@Serializable
internal data class DreamWorld(
    @SerialName("front_default")
    val frontDefault: String,
    @SerialName("front_female")
    val frontFemale: String?,
)

@Serializable
internal data class Home(
    @SerialName("front_default")
    val frontDefault: String,
    @SerialName("front_female")
    val frontFemale: String?,
    @SerialName("front_shiny")
    val frontShiny: String,
    @SerialName("front_shiny_female")
    val frontShinyFemale: String?,
)

@Serializable
internal data class OfficialArtwork(
    @SerialName("front_default")
    val frontDefault: String,
    @SerialName("front_shiny")
    val frontShiny: String,
)

@Serializable
internal data class Showdown(
    @SerialName("back_default")
    val backDefault: String,
    @SerialName("back_female")
    val backFemale: String?,
    @SerialName("back_shiny")
    val backShiny: String,
    @SerialName("back_shiny_female")
    val backShinyFemale: String?,
    @SerialName("front_default")
    val frontDefault: String,
    @SerialName("front_female")
    val frontFemale: String?,
    @SerialName("front_shiny")
    val frontShiny: String,
    @SerialName("front_shiny_female")
    val frontShinyFemale: String?,
)

@Serializable
internal data class Versions(
    @SerialName("generation-i")
    val generationI: GenerationI,
    @SerialName("generation-ii")
    val generationIi: GenerationIi,
    @SerialName("generation-iii")
    val generationIii: GenerationIii,
    @SerialName("generation-iv")
    val generationIv: GenerationIv,
    @SerialName("generation-v")
    val generationV: GenerationV,
    @SerialName("generation-vi")
    val generationVi: GenerationVi,
    @SerialName("generation-vii")
    val generationVii: GenerationVii,
    @SerialName("generation-viii")
    val generationViii: GenerationViii,
)

@Serializable
internal data class GenerationI(
    @SerialName("red-blue")
    val redBlue: RedBlue,
    val yellow: Yellow,
)

@Serializable
internal data class RedBlue(
    @SerialName("back_default")
    val backDefault: String,
    @SerialName("back_gray")
    val backGray: String,
    @SerialName("front_default")
    val frontDefault: String,
    @SerialName("front_gray")
    val frontGray: String,
)

@Serializable
internal data class Yellow(
    @SerialName("back_default")
    val backDefault: String,
    @SerialName("back_gray")
    val backGray: String,
    @SerialName("front_default")
    val frontDefault: String,
    @SerialName("front_gray")
    val frontGray: String,
)

@Serializable
internal data class GenerationIi(
    val crystal: Crystal,
    val gold: Gold,
    val silver: Silver,
)

@Serializable
internal data class Crystal(
    @SerialName("back_default")
    val backDefault: String,
    @SerialName("back_shiny")
    val backShiny: String,
    @SerialName("front_default")
    val frontDefault: String,
    @SerialName("front_shiny")
    val frontShiny: String,
)

@Serializable
internal data class Gold(
    @SerialName("back_default")
    val backDefault: String,
    @SerialName("back_shiny")
    val backShiny: String,
    @SerialName("front_default")
    val frontDefault: String,
    @SerialName("front_shiny")
    val frontShiny: String,
)

@Serializable
internal data class Silver(
    @SerialName("back_default")
    val backDefault: String,
    @SerialName("back_shiny")
    val backShiny: String,
    @SerialName("front_default")
    val frontDefault: String,
    @SerialName("front_shiny")
    val frontShiny: String,
)

@Serializable
internal data class GenerationIii(
    val emerald: Emerald,
    @SerialName("firered-leafgreen")
    val fireredLeafgreen: FireredLeafgreen,
    @SerialName("ruby-sapphire")
    val rubySapphire: RubySapphire,
)

@Serializable
internal data class Emerald(
    @SerialName("front_default")
    val frontDefault: String,
    @SerialName("front_shiny")
    val frontShiny: String,
)

@Serializable
internal data class FireredLeafgreen(
    @SerialName("back_default")
    val backDefault: String,
    @SerialName("back_shiny")
    val backShiny: String,
    @SerialName("front_default")
    val frontDefault: String,
    @SerialName("front_shiny")
    val frontShiny: String,
)

@Serializable
internal data class RubySapphire(
    @SerialName("back_default")
    val backDefault: String,
    @SerialName("back_shiny")
    val backShiny: String,
    @SerialName("front_default")
    val frontDefault: String,
    @SerialName("front_shiny")
    val frontShiny: String,
)

@Serializable
internal data class GenerationIv(
    @SerialName("diamond-pearl")
    val diamondPearl: DiamondPearl,
    @SerialName("heartgold-soulsilver")
    val heartgoldSoulsilver: HeartgoldSoulsilver,
    val platinum: Platinum,
)

@Serializable
internal data class DiamondPearl(
    @SerialName("back_default")
    val backDefault: String,
    @SerialName("back_female")
    val backFemale: String?,
    @SerialName("back_shiny")
    val backShiny: String,
    @SerialName("back_shiny_female")
    val backShinyFemale: String?,
    @SerialName("front_default")
    val frontDefault: String,
    @SerialName("front_female")
    val frontFemale: String?,
    @SerialName("front_shiny")
    val frontShiny: String,
    @SerialName("front_shiny_female")
    val frontShinyFemale: String?,
)

@Serializable
internal data class HeartgoldSoulsilver(
    @SerialName("back_default")
    val backDefault: String,
    @SerialName("back_female")
    val backFemale: String?,
    @SerialName("back_shiny")
    val backShiny: String,
    @SerialName("back_shiny_female")
    val backShinyFemale: String?,
    @SerialName("front_default")
    val frontDefault: String,
    @SerialName("front_female")
    val frontFemale: String?,
    @SerialName("front_shiny")
    val frontShiny: String,
    @SerialName("front_shiny_female")
    val frontShinyFemale: String?,
)

@Serializable
internal data class Platinum(
    @SerialName("back_default")
    val backDefault: String,
    @SerialName("back_female")
    val backFemale: String?,
    @SerialName("back_shiny")
    val backShiny: String,
    @SerialName("back_shiny_female")
    val backShinyFemale: String?,
    @SerialName("front_default")
    val frontDefault: String,
    @SerialName("front_female")
    val frontFemale: String?,
    @SerialName("front_shiny")
    val frontShiny: String,
    @SerialName("front_shiny_female")
    val frontShinyFemale: String?,
)

@Serializable
internal data class GenerationV(
    @SerialName("black-white")
    val blackWhite: BlackWhite,
)

@Serializable
internal data class BlackWhite(
    val animated: Animated,
    @SerialName("back_default")
    val backDefault: String,
    @SerialName("back_female")
    val backFemale: String?,
    @SerialName("back_shiny")
    val backShiny: String,
    @SerialName("back_shiny_female")
    val backShinyFemale: String?,
    @SerialName("front_default")
    val frontDefault: String,
    @SerialName("front_female")
    val frontFemale: String?,
    @SerialName("front_shiny")
    val frontShiny: String,
    @SerialName("front_shiny_female")
    val frontShinyFemale: String?,
)

@Serializable
internal data class Animated(
    @SerialName("back_default")
    val backDefault: String,
    @SerialName("back_female")
    val backFemale: String?,
    @SerialName("back_shiny")
    val backShiny: String,
    @SerialName("back_shiny_female")
    val backShinyFemale: String?,
    @SerialName("front_default")
    val frontDefault: String,
    @SerialName("front_female")
    val frontFemale: String?,
    @SerialName("front_shiny")
    val frontShiny: String,
    @SerialName("front_shiny_female")
    val frontShinyFemale: String?,
)

@Serializable
internal data class GenerationVi(
    @SerialName("omegaruby-alphasapphire")
    val omegarubyAlphasapphire: OmegarubyAlphasapphire,
    @SerialName("x-y")
    val xY: XY,
)

@Serializable
internal data class OmegarubyAlphasapphire(
    @SerialName("front_default")
    val frontDefault: String,
    @SerialName("front_female")
    val frontFemale: String?,
    @SerialName("front_shiny")
    val frontShiny: String,
    @SerialName("front_shiny_female")
    val frontShinyFemale: String?,
)

@Serializable
internal data class XY(
    @SerialName("front_default")
    val frontDefault: String,
    @SerialName("front_female")
    val frontFemale: String?,
    @SerialName("front_shiny")
    val frontShiny: String,
    @SerialName("front_shiny_female")
    val frontShinyFemale: String?,
)

@Serializable
internal data class GenerationVii(
    val icons: Icons,
    @SerialName("ultra-sun-ultra-moon")
    val ultraSunUltraMoon: UltraSunUltraMoon,
)

@Serializable
internal data class Icons(
    @SerialName("front_default")
    val frontDefault: String,
    @SerialName("front_female")
    val frontFemale: String?,
)

@Serializable
internal data class UltraSunUltraMoon(
    @SerialName("front_default")
    val frontDefault: String,
    @SerialName("front_female")
    val frontFemale: String?,
    @SerialName("front_shiny")
    val frontShiny: String,
    @SerialName("front_shiny_female")
    val frontShinyFemale: String?,
)

@Serializable
internal data class GenerationViii(
    val icons: Icons2,
)

@Serializable
internal data class Icons2(
    @SerialName("front_default")
    val frontDefault: String,
    @SerialName("front_female")
    val frontFemale: String?,
)

@Serializable
internal data class Cries(
    val latest: String,
    val legacy: String,
)

@Serializable
internal data class Stat(
    @SerialName("base_stat")
    val baseStat: Long,
    val effort: Long,
    val stat: Stat2,
)

@Serializable
internal data class Stat2(
    val name: String,
    val url: String,
)

@Serializable
internal data class Type(
    val slot: Long,
    val type: Type2,
)

@Serializable
internal data class Type2(
    val name: String,
    val url: String,
)

@Serializable
internal data class PastType(
    val generation: Generation,
    val types: List<Type3>,
)

@Serializable
internal data class Generation(
    val name: String,
    val url: String,
)

@Serializable
internal data class Type3(
    val slot: Long,
    val type: Type4,
)

@Serializable
internal data class Type4(
    val name: String,
    val url: String,
)
