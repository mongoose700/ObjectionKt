package derekpeirce.objectionmaker

import kotlin.time.milliseconds

fun main() {

    makeTrial() {
        val judge = Judge()
        val phoenix = Phoenix()
        val miles = Edgeworth(name = "Miles")
//        val larry = Larry()
//
//        playMusic(Music.CIRCUS) {
//            judge("Moe,\t it's your turn.")
//            moe("So,\t what happens when a dark elf casts $sleep on you?")
//            phoenix.breakdown("Oh dear...")
//            moe.laugh("You get drow-sy!\t I cast $hideousLaughter! Count Rothenel needs to make a $wisdomSavingThrow.")
//            judge.negative("I'm almost inclined to give him advantage from that joke,\t but he gets $dieRoll a ${value(15)}.")
//            moe("He fails!\t At least someone has a sense of humor here!\t He's now $incapacitated and $prone!")
//            regina.happy("I thought it was funny,\t Uncle Moe!")
//            moe("For that,\t you get $bardicInspiration.")
//            judge("And now it's his turn... $dieRoll \t and he fails again.\t Regina,\t you're up.")
//            regina.happy("Yay!\t I hit him with $eldritchBlast!")
//        }
//        playMusic(Music.SUSPENSE) {
//            judge.negative("You will have disadvantage becuase he's $prone and you're making a ranged attack!")
//            moe.point("But he's also $incapacitated,\t so I think that means you also have advantage.")
//            judge.negative.holdIt("While other conditions that also include the $incapacitated condition,\t like $restrained, grant advantage on attack rolls against the creature,\t the $incapacitated condition itself does not.")
//            moe.sad("Oh no!\t I didn't mean to make this harder.\t Could you cast a different spell?")
//            regina.cry("I...\t I ran out of spell slots!")
//            judge.eyesClosed("Such is the life of a warlock.")
//            regina.sad("I guess I can run up and stab him with my dagger.\t At least that will have advantage.")
//            phoenix.deskSlam.holdIt("Being $prone only inflicts disadvantage if you're more than 5 feet away from the target.\t If you're within 5 feet,\t you get advantage instead!\t This applies for both ranged and melee attacks.")
//            moe.dull("But attacking a target within 5 feet of you with a ranged attack also has disadvantage.")
//            phoenix.point.objection("The rule is that you have disadvantage if you're within 5 feet of a hostile creature that can see you and isn't $incapacitated!")
//            phoenix.yell("But Count Rothenel is $incapacitated by $hideousLaughter!")
//            judge.positive("Yes,\t if you run up to the count,\t you will have advantage.")
//            regina.point("Then I run up to him and cast $eldritchBlast!\t The first beam has ${diceRolls(2)} a ${value(23)} to hit!")
//            judge.positive("That hits.")
//            regina.sparkle("That's ${diceRolls(2)} ${value(8)} force damage and ${value(5)} necrotic damage.\t Then for my next beam", interrupted = true)
//            judge.negative.holdIt("First I need to remake the save against $hideousLaughter,\t this time actually with advantage.\t ${diceRolls(2)} He gets a ${value(20)}.")
//            moe.damage("That passes.")
//            judge.positive("Then he's no longer $incapacitated,\t so you have disadvantage,\t canceling out your advantage.")
//            regina.sad("Oh.\t Then it's $dieRoll an ${value(18)}.\t I think that misses...")
//            regina.sparkle2("But with $bardicInspiration,\t it's $dieRoll a total of ${value(27)},\t so it hits!\t ${diceRolls(2)} ${value(12)} force damage and ${value(3)} necrotic damage.")
//            judge.positive("That knocks him unconscious.")
//        }
//        regina.sparkle("We did it!")
//        playMusic(Music.CROSS_EXAMINING) {
//            judge.negative("But that doesn't mean this fight is over!\t The enemy priest is up next.")
//            regina.cry("Oh no!\t I'm out of movement,\t if he gets healed he can hit me.")
//            regina.point(
//                "I'll hit him again with the third blast,\t again with advantage.\t ${diceRolls(2)} Does a ${value(
//                    22
//                )} hit?"
//            )
//            judge.positive("Yes.\t Because that's a ranged attack,\t he gets one failed death save...")
//            phoenix.point.objection("She's within 5 feet!\t That makes it an automatic critical hit,\t so he fails two death saves!")
//            judge.surprised("Ah,\t you're right.\t It turns out being a ranged attack doesn't matter.")
//            judge.eyesClosed("But that still isn't enough to kill him.\t Next,\t the priest", interrupted = true)
//            regina.point.holdIt("I still have my bonus action!")
//            regina.sparkle2("I use my $maddeningHex invocation to deal ${value(5)} psychic damage!")
//            judge.positive("That does him in.")
//        }
//        playMusic(Music.CIRCUS) {
//            moe.laugh("One,${pause(300.milliseconds)} two,${pause(300.milliseconds)} three death saves,$pause and he's down for the Count!")
//            phoenix.breakdown("...$pause")
//            judge.eyesClosed("...$pause")
//            regina.happy("...$bigPause")
//        }
    }.run {
        print()
        writeTo("fiveFeet.objection")
    }
}
