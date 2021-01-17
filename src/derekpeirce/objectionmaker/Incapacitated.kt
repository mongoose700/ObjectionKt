package derekpeirce.objectionmaker


val chapter10Spellcasting = keyword("Chapter 10: Spellcasting")
val stealth = keyword("Stealth")
val athletics = keyword("Athletics")
val bite = keyword("bite")
val tail = keyword("tail")
val grappled = keyword("grappled")
val stunned = keyword("stunned")
val incapacitated = keyword("incapacitated")
val restrained = keyword("restrained")
val kiPoint = keyword("ki point")
val kiPoints = keyword("ki points")
val kiSaveDC = keyword("ki save DC")
val focusedAim = keyword("Focused Aim")
val stunningStrike = keyword("Stunning Strike")
val constitution = keyword("Constitution")
val strength = keyword("Strength")
val savingThrows = keyword("saving throws")
val slowFall = keyword("slow fall")
val flurryOfBlows = keyword("Flurry of Blows")
val medium = keyword("Medium")
val huge = keyword("Huge")

val polymorph = spell("polymorph")
val passWithoutTrace = spell("pass without trace")

fun main() {

    makeTrial() {

        val judge = Judge()
        // Phoenix is a level 9 Wood elf Monk, with 8 Str, 20 Dex, 14 Con, low Int, 16 Wis, low Cha. 8 + 8 * 5 + 9 * 2 = 66
        val phoenix = Phoenix()
        val edgeworth = Edgeworth()
        val maya = Maya()

        playMusic(Music.TRIAL) {
            phoenix("I'll scout ahead.")
            maya("Alone?")
            phoenix.confident("If the rest of you come,\t we wouldn't be very stealthy,\t especially with your heavy armor.")
                .invoke("And I'm fast enough to escape almost anything if I get into trouble.")
            maya.thinking("But can't Edgeworth cast $passWithoutTrace?")
            edgeworth.armsCrossed("Not anymore. I've only got 1st level spell slots remaining.")
            maya.dull("Fine.")
            judge("You advance ahead of the group along the cliff trail,\t which is about ${value(25)} feet wide.\t If you're doing this stealthily,\t go ahead and roll your $stealth check.")
            phoenix(dieRoll)
            phoenix.silly("I rolled a ${value(4)},\t for a total of ${value(13)}.")
        }
        playMusic(Music.SUSPENSE) {
            judge.negative("When you come around the corner and peek your head out,\t you spot a Tyrannosaurus rex.\t And as you realize that you're upwind of it, it spots you.\t Roll initiative!")
            phoenix.thinking("(A T. rex?\t I didn't think there were any in the mountains.)")
            phoenix("I got $dieRoll\t a ${value(7)},\t for ${value(12)} with my $dexterity modifier.")
            judge("$dieRoll It gets a ${value(14)}. It was ${value(40)} feet away from you, so it's able to run up to you and attempt a $bite attack. It's a $dieRoll")
            judge.surprised("$pause $shakeBig natural ${value(20)}! It deals... ${diceRolls(8)} $shakeBig ${value(58)} piercing damage!\t And you are $grappled and $restrained!")
            phoenix.damage(flashBig)
            phoenix.breakdown("I'm in the single digits from one attack!")
            judge("And it follows up with a $tail attack.")
            edgeworth.read.objection("The Tyrannosaurus rex is not allowed to make both of its attacks against the same target.")
            judge("I see.\t But why are you looking that up?")
            edgeworth.smirk("I've had to reference it from earlier fights when I would cast $polymorph.\t In fact, I've turned Phoenix into a T. rex before.")
            phoenix.thinking("(If I try to escape the grapple and fail,\t I'm done for.\t I've only got one choice.)")
            phoenix.deskSlam("I'll try to stab it with my spear.\t That's $dieRoll a ${value(17)} to hit!")
            judge.negative.holdIt("Remember,\t you have $disadvantage on attack rolls when you're $restrained.")
            phoenix.cornered("Oh.\t Then it's $dieRoll an ${value(11)}.")
            judge.negative("That misses.")
            phoenix.point.holdIt(
                "Then I'll use $focusedAim to spend ${value(1)} $kiPoint to increase the attack roll by ${value(
                    2
                )}."
            )
            judge.positive("Now it hits.")
        }
        playMusic(Music.CROSS_EXAMINING) {
            phoenix.confident("I'm not done spending $kiPoints yet!")
            phoenix.yell("$stunningStrike!")
            judge("$pause Could you roll damage first?")
            phoenix.silly("Right.\t That's $dieRoll ${Sound.DAMAGE2} ${value(9)} piercing damage.")
            judge("And now to make the $constitution $savingThrows.... ${diceRolls(2)}")
            edgeworth.armsCrossed("(Throws?)")
            judge("What's your $kiSaveDC?")
            phoenix("${value(15)}.")
            judge.positive("And the dinosaur is $stunned!\t But I suppose you are still $grappled.")
            phoenix.headshake("Doesn't it drop me?")
            judge.negative("According to the conditions,\t $incapacitated only prevents it from taking actions and reactions.\t It says nothing about grappling.")
            phoenix.read.objection("That's because you have to look at the $grappled condition instead.\t \"The condition ends if the grappler is $incapacitated\"!")
            judge.surprised("Why indeed it does!\t Then you are dropped.")
        }
        maya.determined("Now you can run for it!")
        phoenix.thinking("Or I could try to push it over the cliff.")
        edgeworth.point.holdIt("But you are $medium while it is $huge. You can't shove or grapple a creature that's two or more sizes larger than you.")
        phoenix.cornered("Oh,\t right.")
        playMusic(Music.CROSS_EXAMINING) {
            edgeworth.armsCrossed("DM,\t you mentioned an extra $constitution $savingThrow.\t And we haven't seen any dinosaurs here before.\t Is the T. rex concentrating on its own $polymorph?")
            judge("Perhaps.\t But there's no reason for you to know that.")
            edgeworth.point("If it was concentrating on a spell,\t then the the spell has ended!")
            judge.negative("But the $incapacitated condition doesn't say anything about that either!")
            edgeworth.read.objection("It says so in $chapter10Spellcasting:\t \"You lose concentration on a spell if you are $incapacitated or if you die\".")
            judge.surprised("So it does.\t The T. rex shrinks as it drops you. Before you is a man wearing a black cloak with an emblem of a broken gavel.")
            edgeworth.cornered("We've finally caught up to Injudicious, the wizard that burned down my forest!")
            phoenix.confident("Then I'll take him down!\t I'll grapple him.")
            judge("Then make an $athletics check.")
        }
        playMusic(Music.OBJECTION) {
            phoenix.deskSlam.holdIt("But he's $stunned! Shouldn't that make it easier for me to grapple him?")
            judge.negative("A $stunned creature automatically fails $strength and $dexterity $savingThrows,\t but it says nothing about $abilityChecks.")
            phoenix.thinking("Maybe it's mentioned under the grappling rules instead.")
            judge("Let's take a look. $bigPause")
            judge.negative("I don't see anything about it.")
            phoenix.cornered("$dieRoll I got a ${value(1)}.")
            judge("A natural ${value(1)}?")
            phoenix.silly("No,\t a natural ${value(2)},\t with a modifier of ${value(-1)}.")
            judge.negative("$dieRoll\t Then your grapple fails.")
            maya.stand.objection("")
            judge.surprised("${Sound.SHOCK}$pause")
            phoenix("${Sound.SHOCK}$pause")
            edgeworth("${Sound.SHOCK}$bigPause")
            maya.determined("I looked it up online!\t Your grapple does succeed!")
            judge.surprised("On...\t line? $pause")
            maya.dull("...$bigPause")
            maya("It's not in your copy because it was introduced later as an errata! \"You succeed automatically if the target is $incapacitated.\"")
            judge.eyesClosed("Interesting. Then your grapple succeeds.")
        }
        phoenix.confident("I'll drag him towards the edge of the cliff.\t With my movement speed of ${value(50)} feet from being a wood elf and a monk,\t I should be able to reach it.\t How far down is it?")
        judge("It's ${value(80)} feet to the rocks below.")
        playMusic(Music.TRUTH) {
            phoenix.point("Then I'm jumping over and taking him with me!")
            judge.surprised("You tumble through the air and crash hard into the ground, both taking... ${diceRolls(8)} ${Sound.DAMAGE2} $shakeMedium ${value(31)} bludgeoning damage!")
            phoenix.headshake.objection("I'll use my reaction for $slowFall, to reduce the damage by ${value(45)}!")
            judge("Then you are fine,\t while the wizard takes the full damage.\t He's looking really bad.")
            phoenix.deskSlam.takeThat("$flurryOfBlows! With $advantage because he's $stunned! ${diceRolls(4)} That's a ${value(21)} and a ${value(25)} to hit.")
            judge.positive("Both hit.")
            phoenix.point("Pop pop. ${diceRolls(2)} ${Sound.DAMAGE2} ${value(6)} and ${value(8)} bludgeoning damage.")
            judge.surprised("(The combat only lasted one round?!)")
            judge.positive("How do you want to do this?")
        }
    }.run {
        print()
        writeTo("incapacitated.objection")
    }
}