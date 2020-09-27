package derekpeirce.objectionmaker

import java.awt.Color
import kotlin.time.milliseconds
import kotlin.time.seconds

fun keyword(s: String) = s
fun spell(s: String) = color(s, Color.GREEN)

val help = keyword("Help")
val surprise = keyword("surprise")
val surprised = keyword("surprised")
val surpriseRound = keyword("surprise round")
val passivePerception = keyword("passive Perception")
val sleightOfHand = keyword("Sleight of Hand")
val advantage = keyword("advantage")
val disadvantage = keyword("disadvantage")
val investigation = keyword("Investigation")
val reliableTalent = keyword("Reliable Talent")
val assassinate = keyword("Assassinate")
val hitPoints = keyword("hit points")
val criticalHit = keyword("critical hit")
val initiative = keyword("initiative")
val sneakAttack = keyword("Sneak Attack")
val jackOfAllTrades = keyword("Jack of All Trades")
val cuttingWords = keyword("Cutting Words")
val peerlessSkill = keyword("Peerless Skill")
val proficiencyBonus = keyword("proficiency bonus")
val abilityCheck = keyword("ability check")
val bardicInspiration = keyword("Bardic Inspiration")
val cunningActionAim = keyword("Cunning Action: Aim")

val alarm = spell("alarm")
val holdPerson = spell("hold person")
val freedomOfMovement = spell("freedom of movement")
val planeShift = spell("plane shift")

val smallPause = pause(100.milliseconds)
val pause = pause(500.milliseconds)
val bigPause = pause(1.seconds)

fun main() {

    makeTrial(defaultTextSpeed = null) {
        
        val judge = Judge()
        val phoenix = Phoenix()
        val vonKarma = VonKarma()
        val maya = Maya()
        val polly = Polly()
        
        playMusic(Music.TRIAL) {
            judge("Now that you've defeated the archmage and his cohorts,\t what would you like to do?")
            phoenix("I'm going to search the archmage's body for any hints to who his allies are.")
            vonKarma("I'll follow and keep an eye out for any remaining threats.")
            maya("I'll stay back to cast $alarm on the door,\t but you can take Polly with you!\t\t $pause She'll $help in your search!")
            polly("I'm helping!\t I'm helping!")
            judge.positive("Very well.\t\t Phoenix, you may make an $investigation check,\t with advantage from the familiar's $help.")
            judge("Von Karma,\t with your passive Perception,\t you can tell that there are no more threats about you.")
            phoenix.stand("Great!$pause I rolled a 13,\t which comes out to a 25 with modifiers,\t but my passive", interrupted = true)
        }
        playMusic(Music.SUSPENSE) {
            vonKarma.snapFinger.holdIt("While Phoenix is busy investigating,\t\t\t I'll take out my Dagger of Venom and stab him in the back!")
            phoenix.damage("Wait,\t what?\t\t Why would you attack me?\t\t And why now?")
            vonKarma.confident(
                "Simple.\t\t You and Maya took significant damage in the last fight,\t" +
                        " and this is my perfect chance for me to finally get rid of both of you and claim the glory for myself.",
                animated = false
            )
            maya.angry("No way!\t That's not fair!\t DM,\t you can't let him do this!")
            vonKarma.armsCrossed.objection("No,\t this isn't for him to decide.\t\t This is what my character would do.\t\t DM, allow this fight!")
            judge.surprised("Well,\t he has a point.\t\t This fight will proceed.\t\t Von Karma,\t you have a $surpriseRound.")
        }
        playMusic(Music.CROSS_EXAMINING) {
            phoenix.deskSlam.holdIt("Just because I'm investigating doesn't mean I've dropped my guard!\t\t I shouldn't be surprised!")
            judge.eyesClosed("Ah,\t hmmm.\t\t How to resolve this...")
            vonKarma.armsCrossed("Simple.\t My $sleightOfHand check needs to beat his $passivePerception score.\t\t If it does, he's $surprised.")
                .invoke("And,\t as he's busy investigating and his back is turned,\t he has $disadvantage on his score for a -5 penalty.")
            judge.positive("Very well.\t\t Make your $sleightOfHand check.")
            vonKarma.snapFinger(shakeSmall)
            phoenix.confident("Ha!\t\t Only a roll of 3!\t\t There's no way you'll beat my $passivePerception of 21!")
            vonKarma.confident.objection("You forgot about my $reliableTalent feature!\t\t I treat this roll as a 10,\t and my total comes out to $shakeMedium 25!")
            phoenix.damage(flashMedium)
            judge.surprised("A minimum roll of 25?\t\t Why did I even ask?\t\t\t\t Very well,\t you may take your $surpriseRound.")
            vonKarma.snapFinger("Perfect!\t\t With my $assassinate feature,\t I get $advantage,\t so that's $bigPause a $shakeBig 24 to hit!\t\t And because you're $surprised,\t it's an automatic $criticalHit!")
            phoenix.breakdown("Gah!\t\t I don't have enough $hitPoints to survive this!\t\t I'm toast!")
            maya.determined("No,\t Phoenix,\t don't give up!\t\t There's no way he can defeat you in a single $surpriseRound!")
        }
        phoenix.thinking("(Wait,\t\t surprise round...$bigPause Of course!)")
        playMusic(Music.OBJECTION) {
            phoenix.deskSlam.objection("There's no such thing as a $surpriseRound!")
            judge.surprised("What?\t\t Of course there's a $surpriseRound,\t you were $surprised!")
            phoenix.read("No,\t not if we check the Combat Step by Step section on the first page of Chapter 9: Combat!\t\t ")
                .invoke("Step 1 is to determine $surprise,\t which we have already done,\t and Step 2 is to establish our positions.")
            phoenix.deskSlam("Before we reach Step 4 of taking the first turn,\t Step 3,\t ${pause(100.milliseconds)} $shakeBig we all roll $initiative!" +
                    "\t\t\t And if my roll is higher,\t I'm no longer $surprised during the attack!")
            vonKarma.damage(flashMedium)
            vonKarma.armsCrossed("Fine,\t but you're wasting our time if you think you can beat my $initiative roll!")
            judge.surprised("Oh,\t of course!\t\t Everyone,\t roll $initiative!")
        }
        playMusic(Music.SUSPENSE) {
            vonKarma.snapFinger("8,\t plus my Dexterity and Alert bonus,\t so 18!")
            maya.dull("Oh, no,\t I got a 3!")
            phoenix("Don't forget,\t you roll a separate $initiative for Polly!")
            maya.determined("Right!\t\t Polly got a 21!")
            polly("Let's goooooo!")
            phoenix("I got a roll of 10.")
            vonKarma.snapFinger("Ha,\t that's only a 13 for you!")
            phoenix.confident.objection("You forget,\t I benefit from $jackOfAllTrades,\t which adds half of my $proficiencyBonus to any $abilityCheck that doesn't already include it!\t\t And $initiative rolls are ${abilityCheck}s!")
            vonKarma.armsCrossed("Fine,\t but that's still only a 15.")
            phoenix.headshake("That,\t and my $peerlessSkill that lets me use a $bardicInspiration to add a d12!")
            phoenix.confident("And that's an additional $shakeMedium +5,\t for a total of$shakeMedium 20!\t\t\t I beat your $initiative,\t and I'm no longer $surprised!")
            vonKarma.damage(flashMedium)
            vonKarma.cornered("Fine,\t so I lose the $criticalHit,\t but I can still use $cunningActionAim!")
            vonKarma.armsCrossed("As long as I don't move,\t I get $advantage on one attack roll,\t which ensures my $sneakAttack damage!")
        }
        playMusic(Music.OBJECTION) {
            phoenix.point.holdIt(
                "Now that I'm not surprised,\t I can use $cuttingWords as my reaction,\t subtracting a d12 roll from your attack roll! $shakeMedium ${pause(
                    500.milliseconds
                )}"
            )
            vonKarma.confident.objection("The Player's Handbook is clear on this,\t you can't use Cutting Words after knowing that an attack roll hits!")
            phoenix.read.objection("It says that I can't use it after the DM determines whether attack roll succeeds or fails,\t and he hasn't ruled on it yet!")
            judge.positive("That is correct,\t the attack may still be modified.")
            vonKarma.damage(flashSmall)
            phoenix.point.objection(bigPause)
        }
        judge.surprised("Wait,\t\t why are you objecting?")
        phoenix.silly("Oh,\t\t sorry,\t\t that's my Cutting Word. \"Objection.\"")
        playMusic(Music.OBJECTION) {
            phoenix.yell.objection("That's a $shakeBig 9!\t\t\t Your attack roll is now 15,\t which is less than my Armor Class!")
            vonKarma.breakdown1("Fine,\t but you're still low on spells and hit points!\t\t You won't win this!")
        }
//            phoenix.thinking("(Great, now I can just hit him with hold person, and Maya and I will have enough time to take him out!)")
//            phoenix.cornered("(Or, I would, if Maya hadn't cast freedom of movement on him in the last fight!)")
        playMusic(Music.SUSPENSE) {
            vonKarma.confident("You just used your two remaining Bardic Inspirations,\t you no longer have a chance! $pause You're just delaying the inevitable!", animated = false)
            judge("Von Karma stabs with his dagger,\t but Mr. Wright avoids it with a distracting objection.\t\t Maya is also surprised,\t so after her we go to Phoenix at the top of the round!")
            maya("Wait,\t don't forget Polly!\t\t She got a 21!")
            vonKarma("And what is a parrot supposed to do in this fight?")
            phoenix.confident("She can use the Help action!")
            maya.determined("Yeah,\t she'll do that!\t\t Polly, Help!")
            polly("I'm helping!\t I'm helping!")
            vonKarma("Fine,\t as if that'll accomplish anything.\t\t You've wasted enough time as it is.")
            phoenix.confident("Oh,\t it will.\t\t On my turn,\t I cast $planeShift!")
            vonKarma.confident("Ha!\t\t\t You're fleeing!\t\t Fine by me,\t I can eliminate Maya in peace,\t and then I'll come after you!")
        }
        playMusic(Music.OBJECTION) {
            phoenix.point.takeThat("I never said I was casting it on myself.")
            phoenix.yell("I'm using it to target von Karma!")
            vonKarma.damage("What?")
            vonKarma.snapFinger("To do that,\t you'll need to hit me with an attack roll!")
            phoenix.nod("Yes,\t with advantage thanks to Polly! ${pause(1.seconds)} And that's a $shakeMedium 27 to hit!")
            vonKarma.damage("Urgh!\t\t But I still get to make a Charisma saving throw!")
            vonKarma.confident("And I have advantage thanks to my Mage Slayer feat,\t so", animated = false, interrupted = true)
            maya.determined.objection("You rolled an 11.")
            vonKarma.cornered("Wh-what?\t\t I haven't rolled my d20 yet!")
            maya.determined("Right,\t but I still had one more Portent left,\t and I can see you!\t\t I replace your die roll with an 11.")
            vonKarma.cornered("That's... $pause only a 15 total!")
            phoenix.point("And the DC is 18!")
        }
        vonKarma.damage("$flashBig ${pause(1.seconds)}")
        playMusic(Music.TRUTH) {
            vonKarma.breakdown1("Argh!\t\t Fine,\t I'll disappear for now,\t but I'll be back!")
            judge.positive("You connect with your attack,\t and von Karma vanishes in an instant. $pause Phoenix,\t which tuning fork did you use for this $planeShift?")
            phoenix("The Elemental Plane of Fire.")
            vonKarma.armsCrossed("Very well.\t\t DM,\t I shall appear in the City of Brass!\t\t I'll use my efreeti contact there to", interrupted = true)
            phoenix.read.objection("According to the Player's Handbook,\t the location in the new plane is determined randomly.")
            judge.surprised("I see.\t\t I'll take a map of the Plane of Fire and roll a d100...")
            judge.negative("That's a 17. $bigPause You appear in ${shakeBig}the Sea of Fire! ${pause(1.seconds)}You take 42 fire damage from the lava,\t and will continue to take 10d10 fire damage per round.")
            vonKarma.damage(flashBig)
            vonKarma.breakdown2("Gah!\t\t\t That's it,\t you're clearly all ganging up on me!\t\t I'm leaving,\t and not coming back!")
            judge.negative("And good riddance!")
        }
        judge.surprised("Um,$pause do either of you know anyone else who can join?")
    }.run {
        print()
        writeTo("example.json")
    }
}