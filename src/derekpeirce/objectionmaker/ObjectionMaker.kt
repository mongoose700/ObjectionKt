package derekpeirce.objectionmaker

import com.google.gson.Gson
import java.awt.Color
import java.io.File
import java.util.*
import kotlin.time.Duration

class Person(val panels: MutableList<Panel>) {

}

class Action(val id: Int, private val panels: MutableList<Panel>, private val defaultTextSpeed: String?) {
    fun say(text: String, bubble: String = "0") = apply {
        panels.add(
            Panel(
                Id = panels.size + 1,
                Text = (defaultTextSpeed ?: "") + text,
                PoseId = id,
                BubbleType = bubble
            )
        )
    }

    fun objection(text: String) = say(text, "1")
    fun holdIt(text: String) = say(text, "2")
    fun takeThat(text: String) = say(text, "3")

}

fun color(text: String, color: Color): String {
    return "[#/c${"%02x".format(color.red)}${"%02x".format(color.green)}${"%02x".format(color.blue)}]$text[/#]"
}
fun textSpeed(speed: Int) = "[#ts$speed]"
fun pause(pause: Duration) = "[#p${pause.inMilliseconds.toInt()}]"
val flashSmall = "[#fs]"
val flashMedium = "[#fm]"
val flashBig = "[#fl]"
val shakeSmall = "[#ss]"
val shakeMedium = "[#sm]"
val shakeBig = "[#sl]"

class ObjectionMaker(val panels: List<Panel>) {
    class Builder(defaultTextSpeed: Int?) {

        private val defaultTextSpeedStr = defaultTextSpeed?.let(::textSpeed)

        private val panels = mutableListOf<Panel>()

        private fun a(id: Int) = Action(id, panels, defaultTextSpeedStr)

        inner class Judge {
            val stand = a(30)
            val negative = a(31)
            val surprised = a(32)
            val positive = a(44)
            val eyesClosed = a(186)
        }

        inner class Phoenix {
            val stand = a(1)
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

        inner class Edgeworth {
            val stand = a(5)
            val deskSlam = a(6)
            val point = a(7)
        }

        inner class VonKarma {
            val stand = a(27)
            val confident = a(28)
            val armsCrossed = a(29)
            val cornered = a(42)
            val snapFinger = a(43)
            val damage = a(191)
            val breakdown1 = a(309)
            val breakdown2 = a(310)
        }

        inner class Maya {
            val stand = a(102)
            val angry = a(103)
            val dull = a(104)
            val thinking = a(105)
            val determined = a(106)
        }


        fun build(): ObjectionMaker =
            ObjectionMaker(panels.toList())
    }

    fun writeTo(filename: String) {
        val json = Gson().toJson(panels)
        val base64 = Base64.getEncoder().encodeToString(json.toByteArray())
        File(filename).writeText(base64)
    }
}

fun makeTrial(defaultTextSpeed: Int? = null, builder: ObjectionMaker.Builder.() -> Unit): ObjectionMaker {
    return ObjectionMaker.Builder(defaultTextSpeed = defaultTextSpeed).apply(builder).build()
}

data class Panel(
    val Id: Int,
    val Text: String,
    val PoseId: Int = 1,
    val PoseAnimation: Boolean = true,
    val Flipped: Boolean = false,
    val BubbleType: String = "0",
    val GoNext: Boolean = false,
    val MergeNext: Boolean = false,
    val DoNotTalk: Boolean = false,
    val Username: String = ""
)