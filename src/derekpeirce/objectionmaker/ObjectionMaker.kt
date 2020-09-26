package derekpeirce.objectionmaker

import com.google.gson.Gson
import java.awt.Color
import java.io.File
import java.util.*
import kotlin.time.Duration

class Person(val panels: MutableList<Panel>) {

}

class Action(val id: Int, private val panels: MutableList<Panel>) {
    fun say(text: String, bubble: String = "0") {
        panels.add(
            Panel(
                Id = panels.size + 1,
                Text = text,
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
fun pause(pause: Duration) = "[#ts${pause.inMilliseconds.toInt()}]"
val flashSmall = "[#fs]"
val flashMedium = "[#fm]"
val flashBig = "[#fl]"
val shakeSmall = "[#ss]"
val shakeMedium = "[#sm]"
val shakeBig = "[#sl]"

class ObjectionMaker(val panels: List<Panel>) {
    class Builder {

        val panels = mutableListOf<Panel>()

        private fun a(id: Int) = Action(id, panels)

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

fun makeTrial(builder: ObjectionMaker.Builder.() -> Unit): ObjectionMaker {
    return ObjectionMaker.Builder().apply(builder).build()
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