package derekpeirce.objectionmaker

import kotlin.time.milliseconds
import kotlin.time.seconds

val large = keyword("Large")
val tollTheDead = keyword("toll the dead")
val wisdom = keyword("Wisdom")
val wildShape = keyword("wild shape")
fun evidenceStart(id: Int) = "[#evdi${id}]"
const val evidenceEnd = "[#evdh]"

// T-rex: https://www.publicdomainpictures.net/en/view-image.php?image=291901&picture=brown-t-rex
// Giant toad: https://www.flickr.com/photos/briangratwicke/5414889938

const val tRexEvidence = 371875;
const val giantToadEvidence = 371887;

fun main() {

    makeTrial() {

        val judge = Judge()
        val phoenix = Phoenix()
        val miles = Edgeworth(name = "Miles")
        val maya = Maya()

        playMusic(Music.SUSPENSE) {
            judge("Now the only beast left is a single giant toad.\t Phoenix,\t it's your turn.")
            phoenix("How far is it from me?")
            judge("About ${value(40)} feet.")
            phoenix.confident("As a T-rex, my movement speed is ${value(50)} feet. I run up and bite it. That's a $dieRoll ${value(24)} to hit.")
            judge.positive("That hits.")
            phoenix.point("Then it takes ${diceRolls(4)} $shakeMedium ${value(35)} piercing damage.\t And it's $grappled and $restrained!")
            miles.read.objection("Phoenix, take a closer look at your bite attack.\t ${evidenceStart(tRexEvidence)}You can only grapple a creature if it's $medium or smaller, but the giant toad is $large.")
            phoenix.silly("${evidenceEnd}Oh,\t I didn't realize how big it was.\t")
            judge("Up next is the giant toad, with Maya on deck.\t It's looking really hurt,\t but will try to bite you,\t Phoenix. $dieRoll \t Does a ${value(17)} hit?")
            phoenix("Yes.")
            judge("You take ${diceRolls(2)} ${value(4)} piercing damage and ${value(5)} poison damage.\t And you're $grappled and $restrained.")
        }
        playMusic(Music.CROSS_EXAMINING) {
            phoenix.point.objection("But as a T-rex,\t I'm $huge!\t Surely it can't grapple me!")
            judge("That is a good point,\t let me take another look.${evidenceStart(giantToadEvidence)}$pause")
            judge.surprised("No,\t there's no size limit specified for the bite attack.\t It can only swallow creatures that are $medium or smaller,\t though,\t so you're safe from that.")
            phoenix(evidenceEnd)
            phoenix.deskSlam("But that doesn't make any sense!\t The toad is so much smaller than I am!\t And its head is so much smaller than my head!")
            miles.smirk("But it's what the rules say!\t You can't complain about them just because you don't like them.")
            phoenix.headshake("But think about it!\t With that logic,\t the giant toad could grapple a kraken!")
            judge.eyesClosed("That certainly would be odd...")
            maya.thinking("Maybe if you had a hundred toads they could grapple the kraken.")
            miles.confidentSmirk("But none of that matters,\t because the bite attack doesn't have a size limit.")
            phoenix.deskSlam("Miles,\t why are you fighting me on this?")
            miles.deskSlam("Because the rules are the rules!$pause")
            miles("... And I can $wildShape into a giant toad myself,\t so I don't want to restrict it.")
            judge("Miles,\t do you have arguments in favor of the toad grappling the T-rex aside from the rules saying so?")
            miles.damage("Well,$pause no.\t But doesn't that trump everything?")
            judge.negative("It's important that we don't forget the most important rule of all.")
        }
        playMusic(Music.TRUTH) {
            judge.eyesClosed("\"As a referee,\t the DM interprets the rules and decides when to abide by them and when to change them.\"")
            judge("And I think in this case I have heard enough.\t While the toad is able to deal its damage,\t it is not able to hold you.")
            judge("Phoenix, you are")
        }
        judge.stand("${Sound.BADUM}NOT${pause(700.milliseconds)} ${Sound.BADUM}GRAPPLED $bigPause ${Sound.APPLAUSE} ${pause(3.seconds)}", doNotTalk = true, centerText = true)
        playMusic(Music.SUSPENSE) {
            judge("Maya,\t it's your turn,\t with Miles on deck.")
            maya.determined("I cast $tollTheDead!\t It needs to make a $wisdom $savingThrow.")
            judge(dieRoll)
            judge.negative("It fails with a ${value(7)}.")
            maya.determined("It takes ${diceRolls(3)} ${value(8)} necrotic damage and ${value(5)} radiant damage.")
        }
        judge.positive("And the toad is dead.")
        maya("I did it!")
        phoenix.damage("Wait,\t we did all that arguing just for it to die on the next turn?")
    }.run {
        print()
        writeTo("giantToad.objection")
    }
}
