package derekpeirce.objectionmaker

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import derekpeirce.objectionmaker.ObjectionMaker.Builder
import derekpeirce.objectionmaker.ObjectionMaker.Builder.Actor
import java.awt.Color
import java.io.File
import java.util.*
import kotlin.time.Duration
import kotlin.time.milliseconds

interface PanelCreator {
    fun createPanel(
        id: Int,
        text: String,
        poseId: Int,
        args: ActionArgs,
        username: String
    ): Panel

    object Default : PanelCreator {
        override fun createPanel(
            id: Int,
            text: String,
            poseId: Int,
            args: ActionArgs,
            username: String
        ): Panel {
            return Panel(
                id = id,
                text = text,
                poseId = poseId,
                poseAnimation = args.animated,
                bubbleType = args.bubble,
                goNext = args.interrupted,
                doNotTalk = args.doNotTalk,
                mergeNext = args.mergeNext,
                username = username
            )
        }
    }
}

data class ActionArgs(
    val bubble: String = "0",
    val animated: Boolean = true,
    val interrupted: Boolean = false,
    val doNotTalk: Boolean = false,
    val mergeNext: Boolean = false
) {
    companion object {
        val DEFAULT = ActionArgs()
    }
}

class Action(
    val id: Int,
    private val builder: Builder,
    private val defaultTextSpeed: String? = null,
    private val tabPauseTime: Duration = 150.milliseconds,
    private val name: String? = null
) {
    operator fun invoke(
        text: String,
        args: ActionArgs = ActionArgs.DEFAULT,
        panelCreator: PanelCreator = PanelCreator.Default
    ) = apply {
        val music = when (val state = builder.musicState) {
            MusicState.None, MusicState.Continue -> null
            is MusicState.StartPlaying -> {
                builder.musicState = MusicState.Continue
                state.music.toString()
            }
            MusicState.Stopping -> {
                builder.musicState = MusicState.None
                endBackgroundMusic
            }
        }
        val image = when (val state = builder.imageState) {
            ImageState.None, ImageState.Continue -> null
            is ImageState.StartShowing -> {
                builder.imageState = ImageState.Continue
                val start = "[#evd${state.image}]"
                val prevPanel = builder.panels.lastOrNull()
                if (prevPanel != null) {
                    prevPanel.text += start
                    null
                } else {
                    start
                }
            }
            ImageState.Stopping -> {
                builder.imageState = ImageState.None
                "[#evdh]"
            }
        }
        builder.panels.add(
            panelCreator.createPanel(
                id = builder.panels.size + 1,
                text = music.orEmpty() + image.orEmpty() + defaultTextSpeed.orEmpty() + text.replace("\t", pause(tabPauseTime)),
                poseId = id,
                args = args,
                username = name.orEmpty()
            )
        )
    }

    fun objection(text: String) = invoke(text, args = ActionArgs("1"))
    fun holdIt(text: String) = invoke(text, args = ActionArgs("2"))
    fun takeThat(text: String) = invoke(text, args = ActionArgs("3"))

}

fun color(text: String, color: Color): String {
    val colorString = when (color) {
        Color.RED -> "r"
        Color.GREEN -> "g"
        Color.BLUE -> "b"
        else -> "c${"%02x".format(color.red)}${"%02x".format(color.green)}${"%02x".format(color.blue)}"
    }
    return "[#/$colorString]$text[/#]"
}
fun textSpeed(speed: Int) = "[#ts$speed]"
fun pause(pause: Duration) = "[#p${pause.inMilliseconds.toInt()}]"
val flashSmall = "[#fs]"
val flashMedium = "[#fm]"
val flashBig = "[#fl]"
val shakeSmall = "[#ss]"
val shakeMedium = "[#sm]"
val shakeBig = "[#sl]"
val endBackgroundMusic = "[#bgms]"

sealed class MusicState {
    object None : MusicState()
    data class StartPlaying(val music: Music): MusicState()
    object Continue: MusicState()
    object Stopping: MusicState()
}

sealed class ImageState {
    object None : ImageState()
    data class StartShowing(val image: String): ImageState()
    object Continue: ImageState()
    object Stopping: ImageState()
}

class Music(val n: Int) {

    companion object {
        val ALLEGRO_2001 = Music(1)
        val ALLEGRO_2002 = Music(77)
        val ALLEGRO_2004 = Music(7)
        val ALLEGRO_2007 = Music(712)
        val ALLEGRO_2009 = Music(72)
        val ALLEGRO_2011 = Music(71)
        val BLUE_BADGER = Music(695)
        val CIRCUS = Music(470)
        val CONGRATULATIONS = Music(16)
        val CORNERED = Music(4)
        val CROSS_EXAMINING = Music(13)
        val MODERATO_2001 = Music(18)
        val OBJECTION = Music(14)
        val SUSPENSE = Music(65)
        val TRIAL = Music(2)
        val TRUTH = Music(17)
        val VON_KARMA = Music(287)
        val WILD_WEST = Music(40)
    }

    override fun toString() = "[#bgm$n]"
}

class Sound(val n: Int) {
    companion object {
        val APPLAUSE = Sound(5)
        val THWAP = Sound(27)
        val REALIZATION = Sound(3)
        val WHOOPS = Sound(1)
    }

    override fun toString() = "[#bgs$n]"
}


class ObjectionMaker(val frames: List<Panel>, val pairs: List<ActorPairData>, val aliases: List<Nothing> = emptyList(), val version: Int = 3) {
    class Builder(defaultTextSpeed: Int?, private val tabPauseTime: Duration) {

        private val defaultTextSpeedStr = defaultTextSpeed?.let(::textSpeed)
        private val pairs = mutableListOf<ActorPair<*, *>>()
        private var pairIdCounter = 1

        var musicState: MusicState = MusicState.None
        var imageState: ImageState = ImageState.None

        val panels = mutableListOf<Panel>()

        private fun action(id: Int, name: String? = null) = Action(id,this, defaultTextSpeedStr, tabPauseTime, name)

        fun playMusic(music: Music, withMusic: () -> Unit) {
            musicState = MusicState.StartPlaying(music)
            withMusic()
            musicState = MusicState.Stopping
        }

        fun showImage(image: String, withImage: () -> Unit) {
            imageState = ImageState.StartShowing(image)
            withImage()
            imageState = ImageState.Stopping
        }

        inner open class Actor(val characterId: Int, standId: Int, val name: String? = null) {
            val stand = a(standId)

            internal fun a(id: Int) = action(id, name)

            operator fun invoke(text: String) = stand(text)
        }

        inner class Polly : Actor(38, 302)

        inner class Judge : Actor(0, 30) {
            val negative = a(31)
            val surprised = a(32)
            val positive = a(44)
            val eyesClosed = a(186)
        }

        inner class Phoenix : Actor(1, 1) {
            val deskSlam = a(2)
            val point = a(3)
            val confident = a(4)
            val thinking = a(33)
            val cornered = a(34)
            val silly = a(35)
            val read = a(59)
            val sipMug = a(76)
            val nod = a(100)
            val headshake = a(101)
            val damage = a(129)
            val breakdown = a(140)
            val coffeeStained = a(188)
            val yell = a(271)
        }

        inner class Edgeworth(name: String? = null) : Actor(94, 5, name = name) {
            val deskSlam = a(6)
            val point = a(7)
            val armsCrossed = a(8)
            val confidentShrug = a(9)
            val cornered = a(36)
            val confidentSmirk = a(47)
            val read = a(89)
            val damage = a(90)
            val smirk = a(185)
            val bow = a(189)
            val yell = a(272)
        }

        inner class VonKarma : Actor(0, 27) {
            val confident = a(28)
            val armsCrossed = a(29)
            val cornered = a(42)
            val snapFinger = a(43)
            val damage = a(191)
            val breakdown1 = a(309)
            val breakdown2 = a(310)
        }

        inner class Maya(name: String? = null) : Actor(0, 102, name = name) {
            val angry = a(103)
            val dull = a(104)
            val thinking = a(105)
            val determined = a(106)
        }

        inner class Apollo(name: String? = null) : Actor(0, 60, name = name) {
            val confident = a(55)
            val silly = a(56)
            val deskSlam = a(57)
            val read = a(58)
            val point = a(61)
            val cornered = a(62)
            val thinking = a(63)
        }

        inner class FranziskaVonKarma(name: String? = null) : Actor(0, 21, name = name) {
            val confident = a(22)
            val deskSlam = a(23)
            val armsCrossed = a(24)
            val point = a(25)
            val whipDesk = a(26)
            val cornered = a(41)
            val damage = a(192)
            val bow = a(193)
            val yell = a(274)
        }

        inner class Lotta(name: String? = null) : Actor(0, 113, name = name) {
            val stand2 = a(114)
            val silly = a(115)
            val relieved = a(116)
            val smile = a(117)
            val angry = a(118)
            val stare = a(119)
            val uncertain = a(120)
        }

        inner class MattEngarde(name: String? = null) : Actor(0, 253, name = name) {
            val uncertain = a(254)
            val call = a(255)
            val onCall = a(256)
            val evil = a(257)
            val laugh = a(258)
            val cornered = a(259)
            val breakdown = a(260)
        }

        inner class Gumshoe(name: String? = null) : Actor(0, 130, name = name) {
            val sad = a(134)
            val headscratch = a(137)
            val stare = a(135)
        }

        inner class JakeMarhsall(name: String? = null) : Actor(0, 152, name = name) {
            val cornered = a(161)
            val damage = a(160)
            val drink = a(155)
            val drinkNewBottle = a(154)
            val hatOff = a(156)
            val nervous = a(157)
            val point = a(153)
            val serious = a(159)
            val shave = a(162)
        }

        inner class Pearl(name: String? = null): Actor(62,469, name = name) {
            val angry = a(472)
            val cry = a(477)
            val dream = a(475)
            val happy = a(473)
            val neutral = a(470)
            val sad = a(476)
            val surprised = a(474)
            val worried = a(471)
        }

        inner class ActorPair<A1: Actor, A2: Actor>(
            private val actor1: A1,
            private val actor2: A2,
            name: String? = null,
            private val offsetX: Int = -20,
            private val offsetY: Int = 0,
            private val offsetX2: Int = 20,
            private val offsetY2: Int = 0,
            private val front: Boolean = true
        ) {
            private val pairId = pairIdCounter++
            private val name = name ?: "Pair $pairId"

            init {
                pairs += this
            }

            operator fun component1() = Delegate1()
            operator fun component2() = Delegate2()

            inner class Delegate1 {
                operator fun invoke(text: String, args: ActionArgs = ActionArgs.DEFAULT, otherPose: A2.() -> Action = Actor::stand) {
                    return invoke(Actor::stand, text, args, otherPose)
                }


                operator fun invoke(action: A1.() -> Action, text: String, args: ActionArgs = ActionArgs.DEFAULT, otherPose: A2.() -> Action = Actor::stand) {
                    val action1 = actor1.action()
                    val action2 = actor2.otherPose()
                    action1.invoke(
                        text = text,
                        args = args,
                        panelCreator = object : PanelCreator {
                            override fun createPanel(
                                id: Int,
                                text: String,
                                poseId: Int,
                                args: ActionArgs,
                                username: String
                            ): Panel {
                                return Panel(
                                    id = id,
                                    text = text,
                                    poseId = poseId,
                                    pairPoseId = action2.id,
                                    bubbleType = args.bubble,
                                    username = username,
                                    doNotTalk = args.doNotTalk,
                                    goNext = args.interrupted,
                                    poseAnimation = true,
                                    mergeNext = args.mergeNext,
                                    flipped = false,
                                    pairId = pairId
                                )
                            }

                        }
                    )
                }
            }

            inner class Delegate2 {
                operator fun invoke(text: String, args: ActionArgs = ActionArgs.DEFAULT, otherPose: A1.() -> Action = Actor::stand) {
                    return invoke(Actor::stand, text, args, otherPose)
                }


                operator fun invoke(action: A2.() -> Action, text: String, args: ActionArgs = ActionArgs.DEFAULT, otherPose: A1.() -> Action = Actor::stand) {
                    val action2 = actor2.action()
                    val action1 = actor1.otherPose()
                    action2.invoke(
                        text = text,
                        args = args,
                        panelCreator = object : PanelCreator {
                            override fun createPanel(
                                id: Int,
                                text: String,
                                poseId: Int,
                                args: ActionArgs,
                                username: String
                            ): Panel {
                                return Panel(
                                    id = id,
                                    text = text,
                                    poseId = poseId,
                                    pairPoseId = action1.id,
                                    bubbleType = args.bubble,
                                    username = username,
                                    doNotTalk = args.doNotTalk,
                                    goNext = args.interrupted,
                                    poseAnimation = true,
                                    flipped = false,
                                    pairId = pairId
                                )
                            }

                        }
                    )
                }
            }

            fun toData() = ActorPairData(
                id = 0,
                pairId = pairId,
                name = name,
                characterId = actor1.characterId,
                characterId2 = actor2.characterId,
                offsetX = offsetX,
                offsetY = offsetY,
                offsetX2 = offsetX2,
                offsetY2 = offsetY2,
                front = front
            )
        }

        fun build(): ObjectionMaker = ObjectionMaker(panels.toList(), pairs.map { it.toData() })
    }

    fun writeTo(filename: String) {
        val json = Gson().toJson(this)
        println(json)
        val base64 = Base64.getEncoder().encodeToString(json.toByteArray())
        File(filename).writeText(base64)
    }

    fun print() {
        println(GsonBuilder().setPrettyPrinting().create().toJson(this))
    }
}

fun makeTrial(defaultTextSpeed: Int? = null, tabPauseTime: Duration = 150.milliseconds, builder: Builder.() -> Unit): ObjectionMaker {
    return Builder(defaultTextSpeed = defaultTextSpeed, tabPauseTime = tabPauseTime).apply(builder).build()
}

data class Panel(
    val id: Int,
    var text: String,
    val poseId: Int = 1,
    val pairPoseId: Int? = null,
    val poseAnimation: Boolean = true,
    val flipped: Boolean = false,
    val bubbleType: String = "0",
    val goNext: Boolean = false,
    val mergeNext: Boolean = false,
    val doNotTalk: Boolean = false,
    val username: String = "",
    val pairId: Int? = null
)

data class ActorPairData(
    val id: Int = 0,
    val pairId: Int,
    val name: String,
    val characterId: Int,
    val characterId2: Int,
    val offsetX: Int = -20,
    val offsetY: Int = 0,
    val offsetX2: Int = 20,
    val offsetY2: Int = 0,
    val front: Boolean = true
)