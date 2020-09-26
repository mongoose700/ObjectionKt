package derekpeirce.objectionmaker

import kotlin.time.milliseconds
import kotlin.time.seconds

fun main() {
    makeTrial(defaultTextSpeed = 1) {
        
        val judge = Judge()
        val phoenix = Phoenix()
        val vonKarma = VonKarma()
        val maya = Maya()
        val polly = Polly()
        
        playMusic(Music.TRIAL) {
            judge("Now that you've defeated the archmage and his cohorts, what would you like to do?")
            phoenix("I'm going to search the archmage's body for any hints to who his allies were.")
            vonKarma("I'll follow and keep an eye out for any remaining threats.")
            maya("I'll stay back to cast alarm on the door, but you can take Polly with you! She'll help in your search!")
            polly("I'm helping! I'm helping!")
            Judge().positive("Very well. Phoenix, you may make an Investigation check, with advantage from the familiar's Help.")
            Judge()("Von Karma, with your passive Perception, you can tell that there are no more threats about you.")
            phoenix("Great! I rolled a 12, which comes out to a 24 with modifiers, so")
        }
        playMusic(Music.SUSPENSE) {
            vonKarma.snapFinger.holdIt("While Phoenix is busy investigating, I'll take out my dagger and stab him in the back!")
            phoenix.damage("Wait, what? Why would you attack me? And why now?")
            vonKarma.confident(
                "Simple. You and Maya took significant damage in the last fight," +
                        " and this is my perfect chance for me to finally get rid of both of you and claim the glory for myself."
            )
            maya.angry("No way! That's not fair! DM, you can't let him do this!")
            vonKarma.armsCrossed.objection("No, this isn't for him to decide. This is what my character would do. DM, allow this fight!")
            Judge().surprised("Well, he has a point. This fight will proceed. Von Karma, you have a surprise round.")
        }
        playMusic(Music.CROSS_EXAMINING) {
            phoenix.deskSlam.holdIt("Just because I'm investigating doesn't mean I've dropped my guard! I shouldn't be surprised!")
            Judge().eyesClosed("Ah, right. How to resolve this...")
            vonKarma.armsCrossed("Simple. My Sleight of Hand check needs to beat his passive Perception score. If it does, he's surprised.")
                .say("And, as he's busy investigating and his back is turned, he has disadvantage on his score for a -5 penalty.")
            Judge().positive("Very well. Make your Sleight of Hand check.")
            vonKarma.snapFinger(shakeSmall)
            phoenix.confident("Ha! Only a roll of 3! There's no way you'll beat my passive Perception of 21!")
            vonKarma.confident.objection("You forgot about my Reliable Talent feature! I treat this roll as a 10, and my total comes out to $shakeMedium 25!")
            phoenix.damage(flashMedium)
            Judge().surprised("A minimum roll of 25? Why did I even ask? Very well, you may take your surprise round.")
            vonKarma.snapFinger("Perfect! With my Assassinate feature, I get advantage, so that's ${pause(1.seconds)} a $shakeBig 24 to hit! And because you're surprised, it's an automatic critical hit!")
            phoenix.breakdown("Gah! I don't have enough hit points to survive this! I'm toast!")
            maya.determined("No, Phoenix, don't give up! There's no way he can defeat you in a single surprise round!")
        }
            phoenix.thinking("(Wait, surprise round... Of course!)")
        phoenix.deskSlam.objection("There's no such thing as a surprise round!")
        playMusic(Music.OBJECTION) {
            Judge().surprised("What? Of course there's a surprise round, you were surprised!")
            phoenix.read("No, not if we check the Combat Step by Step section on the first page of Chapter 9: Combat! ")
                .say("Step 1 is to determine surprise, which we have already done and Step 2 is to establish our positions.")
            phoenix.deskSlam("Before we reach Step 4 of taking the first turn, ${pause(100.milliseconds)} $shakeBig we all roll initiative!" +
                    "And if my roll is higher, I'm no longer surprised during the attack!")
            vonKarma.damage(flashMedium)
            vonKarma.armsCrossed("Fine, but you're wasting our time if you think you can beat my initiative roll!")
            Judge().surprised("Oh, of course! Everyone, roll initiative!")
        }
        playMusic(Music.SUSPENSE) {
            vonKarma.snapFinger("18!")
            maya.dull("Oh, no, I got a 3!")
            phoenix("Don't forget, you roll a separate initiative for Polly!")
            maya.determined("Right! Polly got a 21!")
            polly("Let's goooooo!")
            phoenix("I got a roll of 10.")
            vonKarma.snapFinger("Ha, that's only a 13 for you!")
            phoenix.confident.objection("You forget, as a Bard, I benefit from Jack of All Trades, which adds half of my proficiency bonus!")
            vonKarma.armsCrossed("Fine, but that's still only a 15.")
            phoenix.headshake("That, and my Peerless Skill that lets me use a Bardic Inspiration to add a d12!")
            phoenix.confident("And that's an additional $shakeMedium +5, for a total of 20! I beat your initiative, and I'm no longer surprised!")
            vonKarma.damage(flashMedium)
            vonKarma.cornered("Fine, so I lose the guaranteed critical hit, but I can still use Cunning Action: Aim!")
            vonKarma.armsCrossed("As long as I don't move, I get advantage on one attack roll, which ensures my sneak attack damage!")
        }
        playMusic(Music.OBJECTION) {
            phoenix.point.holdIt(
                "Now that I'm not surprised, I can use Cutting Words as my reaction, subtracting a d12 roll from your attack roll! $shakeMedium ${pause(
                    500.milliseconds
                )}"
            )
            phoenix.yell("That's a ${shakeBig} 9! Your attack roll is now 15, which is less than my Armor Class!")
            vonKarma.breakdown1("Fine, but you're still low on spells and hit points! You won't win this!")
            vonKarma.confident("You just used your two remaining Bardic Inspirations, you no longer have a chance!")
        }
            phoenix.thinking("(Great, now I can just hit him with hold person, and Maya and I will have enough time to take him out!)")
            phoenix.cornered("(Or, I would, if Maya hadn't cast freedom of movement on him in the last fight!)")
        playMusic(Music.SUSPENSE) {
            Judge()("Maya is also surprised, so after her we go to Phoenix at the top of the round!")
            maya("Wait, don't forget Polly! She got a 21!")
            vonKarma("And what is a parrot supposed to do in this fight?")
            phoenix.confident("She can use the Help action!")
            maya.determined("Yeah, she'll do that! Polly, Help!")
            polly("I'm helping! I'm helping!")
            vonKarma("Fine, as if that'll accomplish anything.")
        }
        playMusic(Music.OBJECTION) {

            phoenix.confident("Oh, it will. On my turn, I cast plane shift!")
            vonKarma.confident("Ha! You're fleeing! Fine by me, I can eliminate Maya in peace, and then I'll come after you!")
            phoenix.point.takeThat("I never said I was casting it on myself.")
            phoenix.yell("I'm using it to target Von Karma!")
            vonKarma.snapFinger("To do that, you'll need to hit me with an attack roll!")
            phoenix.nod("Yes, with advantage thanks to Polly! ${pause(1.seconds)} And that's a $shakeMedium 21 to hit!")
            vonKarma.damage("Urgh! But I still get to make a Charisma saving throw!")
            vonKarma.snapFinger("17! That should be enough!")
        }
            phoenix.point("Nope, the DC is 18!")
            vonKarma.damage("$flashBig ${pause(1.seconds)}")
        playMusic(Music.TRUTH) {
            vonKarma.breakdown1("Argh! Fine, I'll disappear for now, but I'll be back!")
            Judge().positive("Phoenix, which tuning fork did you use for this plane shift?")
            phoenix("The Elemental Plane of Fire.")
            vonKarma.armsCrossed("Very well. DM, I shall appear in the City of Brass! I'll use my efreeti contact there to")
            phoenix.read.objection("According to the PHB, the location in the new plane is determined randomly.")
            Judge().surprised("I see. I'll roll a d100...")
            Judge().negative("That's a 17. You appear in ${shakeBig}the Sea of Fire! ${pause(1.seconds)}You take 42 fire damage from the lava, and will continue to take 10d10 fire damage per round.")
            vonKarma.damage(flashBig)
            vonKarma.breakdown2("That's it, you're clearly all ganging up on me! I'm leaving, and not coming back!")
            Judge().negative("And good riddance!")
        }
    }.writeTo("example.json")
}