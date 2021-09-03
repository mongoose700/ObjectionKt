package derekpeirce.objectionmaker

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.awt.Color
import java.awt.Frame
import java.io.File
import java.util.*
import kotlin.time.Duration
import kotlin.time.milliseconds

class Person(val panels: MutableList<Panel>) {

}

class Action(
    val id: Int,
    private val builder: ObjectionMaker.Builder,
    private val defaultTextSpeed: String? = null,
    private val name: String? = null
) {
    operator fun invoke(
        text: String,
        bubble: String = "0",
        animated: Boolean = true,
        interrupted: Boolean = false,
        doNotTalk: Boolean = false,
        centerText: Boolean = false
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
        val frameActions = mutableListOf<FrameAction>()
        if (centerText) {
            frameActions.add(FrameAction.CENTER_TEXT)
        }
        if (doNotTalk) {
            frameActions.add(FrameAction.MUTE_SPEECH)
        }
        builder.panels.add(
            Panel(
                id = builder.panels.size + 1,
                text = music.orEmpty() + defaultTextSpeed.orEmpty() + text.replace("\t", pause(150.milliseconds)),
                poseId = id,
                bubbleType = bubble,
                poseAnimation = animated,
                goNext = interrupted,
                doNotTalk = doNotTalk,
                frameActions = frameActions,
                username = name.orEmpty()
            )
        )
    }

    fun objection(text: String) = invoke(text, "1")
    fun holdIt(text: String) = invoke(text, "2")
    fun takeThat(text: String) = invoke(text, "3")

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
    }

    override fun toString() = "[#bgm$n]"
}

class Sound(val n: Int) {
    companion object {
        val APPLAUSE = Sound(5)
        val APPLAUSE2 = Sound(18)
        val BADUM = Sound(6)
        val BANG = Sound(24)
        val BREAK = Sound(1631)
        val BREAK2 = Sound(1922)
        val CHICKEN = Sound(929)
        val DAMAGE = Sound(7)
        val DAMAGE2 = Sound(10)
        val DESK_SLAM = Sound(2)
        val DRAMA_POUND = Sound(20)
        val EARTHQUAKE = Sound(126)
        val EVIDENCE = Sound(8)
        val EXPLOSION = Sound(28)
        val FALL = Sound(255)
        val FLIP_BOOK = Sound(1637)
        val GALLERY = Sound(31)
        val GALLERY_CHEER = Sound(29)
        val GALLERY_NOISE = Sound(23)
        val GAVEL_QUICK = Sound(139)
        val GAVEL = Sound(16)
        val GUILTY = Sound(17)
        val GUITAR_ERROR = Sound(928)
        val GUNSHOT = Sound(933)
        val GUNSHOT2 = Sound(931)
        val HEARTBEAT = Sound(11)
        val LIGHT_BULB = Sound(9)
        val MEGAPHONE = Sound(934)
        val PHOTO_SNAP = Sound(30)
        val PING = Sound(25)
        val REALIZATION = Sound(3)
        val ROAR = Sound(136)
        val SALUTE = Sound(925)
        val SHINY = Sound(937)
        val SHOCK = Sound(4)
        val SHOOOP = Sound(14)
        val SKETCH = Sound(1645)
        val SMACK = Sound(15)
        val SNACKOOD = Sound(21)
        val SNAP_FINGER = Sound(1632)
        val SNAP = Sound(1633)
        val THWAP = Sound(27)
        val WHACK = Sound(19)
        val WHIP = Sound(26)
        val WHOOPS = Sound(1)
        val YELL = Sound(12)
        val YELL2 = Sound(13)
    }

    override fun toString() = "[#bgs$n]"
}


class ObjectionMaker(val panels: List<Panel>) {
    class Builder(defaultTextSpeed: Int?) {

        private val defaultTextSpeedStr = defaultTextSpeed?.let(::textSpeed)

        var musicState: MusicState = MusicState.None

        val panels = mutableListOf<Panel>()

        private fun action(id: Int, name: String? = null) = Action(id,this, defaultTextSpeedStr, name)

        fun playMusic(music: Music, withMusic: () -> Unit) {
            musicState = MusicState.StartPlaying(music)
            withMusic()
            musicState = MusicState.Stopping
        }

        inner open class Actor(standId: Int, val name: String? = null) {
            val stand = a(standId)

            internal fun a(id: Int) = action(id, name)

            operator fun invoke(text: String) = stand(text)
        }

        inner class Polly : Actor(302)

        inner class Judge : Actor(30) {
            val negative = a(31)
            val surprised = a(32)
            val positive = a(44)
            val eyesClosed = a(186)
        }

        inner class Phoenix : Actor(1) {
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

        inner class Edgeworth : Actor(5) {
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

        inner class VonKarma : Actor(27) {
            val confident = a(28)
            val armsCrossed = a(29)
            val cornered = a(42)
            val snapFinger = a(43)
            val damage = a(191)
            val breakdown1 = a(309)
            val breakdown2 = a(310)
        }

        inner class Maya(name: String? = null) : Actor(102, name = name) {
            val angry = a(103)
            val dull = a(104)
            val thinking = a(105)
            val determined = a(106)
        }

        inner class Apollo(name: String? = null) : Actor(60, name = name) {
            val confident = a(55)
            val silly = a(56)
            val deskSlam = a(57)
            val read = a(58)
            val point = a(61)
            val cornered = a(62)
            val thinking = a(63)
        }

        inner class FranziskaVonKarma(name: String? = null) : Actor(21, name = name) {
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

        inner class Lotta(name: String? = null) : Actor(113, name = name) {
            val stand2 = a(114)
            val silly = a(115)
            val relieved = a(116)
            val smile = a(117)
            val angry = a(118)
            val stare = a(119)
            val uncertain = a(120)
        }

        inner class MattEngarde(name: String? = null) : Actor(253, name = name) {
            val uncertain = a(254)
            val call = a(255)
            val onCall = a(256)
            val evil = a(257)
            val laugh = a(258)
            val cornered = a(259)
            val breakdown = a(260)
        }

        inner class Gumshoe(name: String? = null) : Actor(130, name = name) {
            val sad = a(134)
            val headscratch = a(137)
            val stare = a(135)
        }

        fun build(): ObjectionMaker =
            ObjectionMaker(panels.toList())
    }

    fun writeTo(filename: String) {
        val json = Gson().toJson(panels)
        println(json)
        val base64 = Base64.getEncoder().encodeToString(json.toByteArray())
        File(filename).writeText(base64)
    }

    fun print() {
        println(GsonBuilder().setPrettyPrinting().create().toJson(panels))
    }
}

fun makeTrial(defaultTextSpeed: Int? = null, builder: ObjectionMaker.Builder.() -> Unit): ObjectionMaker {
    return ObjectionMaker.Builder(defaultTextSpeed = defaultTextSpeed).apply(builder).build()
}

data class FrameAction(
    val actionId: Int,
    val actionParam: String = "0"
) {
    companion object {
        val MUTE_SPEECH = FrameAction(actionId = 5)
        val CENTER_TEXT = FrameAction(actionId = 9)
    }
}


data class Panel(
    val id: Int,
    val text: String,
    val poseId: Int = 1,
    val poseAnimation: Boolean = true,
    val flipped: Boolean = false,
    val bubbleType: String = "0",
    val goNext: Boolean = false,
    val mergeNext: Boolean = false,
    val doNotTalk: Boolean = false,
    val username: String = "",
    val frameActions: List<FrameAction> = emptyList()
)