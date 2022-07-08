package derekpeirce.objectionmaker

fun main() {
    makeTrial {
        val judge = Judge()
        val phoenix = Phoenix()
        val maya = Maya()
        val franziska = FranziskaVonKarma(name = "Franziska")
        val polly = Polly()

        playMusic(Music.SUSPENSE) {
            judge("The room begins to fill with acid.\t You expect that it will rise to your level in the next round.\t Phoenix,\t it's your turn.")
            phoenix.thinking("We need to pull the lever to open the door,\t but they're twenty feet apart from each other. I'm too far away to both pull the lever and get through the door.")
            maya("I can pull the lever on my turn.")
            phoenix("But by my next turn I'll start getting hurt by the acid. ", args = ActionArgs(mergeNext = true))
            phoenix.deskSlam("I'll move closer to the door and $ready the $dash action!")
            maya("Oh,\t I haven't used the $ready action before.\t I'll look it up!")
            franziska.confident.objection("When you take the $dash action, it just increases your movement for the current turn!\t But since it's not your turn,\t you can't actually move.")
            phoenix.damage("Oh no!\t I never realized that. ", args = ActionArgs(mergeNext = true))
            phoenix.breakdown("I hope I have enough hit points...")
            maya.determined.holdIt("The $ready action has a case for this:\t \"Then,\t you choose the action you will take in response to that trigger,\t or you choose to move up to your speed in response to it.\"")
            phoenix.confident("Aha!\t I'll do that instead.\t When the door opens,\t I'll move through the door.")
            judge.positive("Very well.\t Maya,\t you're up.")
            maya("I'll walk five feet and pull the lever.")
        }
        playMusic(Music.CORNERED) {
            judge("The door opens,\t revealing a slightly elevated area on which there is a man with a crossbow,\t aiming it towards the lever.")
            judge("He had $readied an attack against whoever opened the door,\t and", args = ActionArgs(interrupted = true))
            phoenix.point.holdIt("I also took the $ready action for when the door opened,\t so I should be able to run through it!")
            judge("Hm,\t you both took the $ready action for when the door opened.\t I don't know what to do.")
            franziska.confident.takeThat("According to Xanathar's Guide to Everything,\t the ordering of simultaneous events is decided by whoever's turn it is.")
            maya("That's me!\t I'll choose to let Phoenix react first.")
            judge.positive(
                "Very well.\t Phoenix,\t you can run through the doorway.\t The man at the door now has $disadvantage on his attack,\t getting ${
                    diceRolls(
                        2
                    )
                } a ${
                    value(
                        14
                    )
                }."
            )
            maya("That misses!\t I'll cast $haste on Franziska and run through the doorway as well.")
            franziska(
                "And now it's my turn!\t I'll run through the door and hit him with my whip. ",
                args = ActionArgs(mergeNext = true)
            )
            franziska.whipDesk("That's a ${value(19)} to hit.")
            judge.positive("It hits.")
            franziska.point("With $sneakAttack,\t that's ${diceRolls(4)} ${value(18)} slashing damage.")
        }
        playMusic(Music.TRIAL) {
            judge("And the mook is down.")
            franziska("Do I see any other foolish enemies?")
            judge("Not immediately without an active $perception check.")
            franziska("I'll make one with my bonus action.\t $dieRoll That's a ${value(15)}.")
            judge.negative("You don't notice any.")
            franziska("Then I'll $ready an attack with my whip for when any enemy fools get within five feet of an ally.")
            phoenix.point.objection("You already used your action to attack! You can't $ready your $haste action!")
            franziska.confident.objection("Only a fool would foolishly use their regular action first for something that could be done with their $haste action.\t I used the $haste action for the attack.")
            phoenix.thinking("I guess that makes sense.")
        }
        playMusic(Music.CORNERED) {
            judge("But after your turn,\t Crimus, the leader of the thieves guild pops out around the corner 50 feet away and fires at Franziska with his crossbow.\t That's an ${value(18)} to hit.")
            franziska("That\t", args = ActionArgs(interrupted = true))
            phoenix.deskSlam.holdIt("I'll use $cuttingWords", args = ActionArgs(interrupted = true))
            franziska.stand.objection("You already used your reaction, ", args = ActionArgs(mergeNext = true))
            franziska.cornered("you fool. $pause It hits.")
            judge.negative("He was hidden anyway.\t He gets his own $sneakAttack, and deals ${diceRolls(4)} $dieRoll ${value(23)} piercing damage.")
            franziska.damage("I don't have that many hit points.\t I'll halve it with $uncannyDodge.")
            maya.stand.holdIt("I thought you couldn't do that because you $readied an attack with your whip.")
            franziska.confident.objection("Since I $readied the attack,\t it was available to me as reaction,\t but I can still use it for something else instead.")
            judge("$dieRoll Crimus $hides behind the corner again.\t Phoenix,\t you're up.")
            phoenix.thinking("He's too far away for me to do much. ", args = ActionArgs(mergeNext = true))
            phoenix("I'll get 30 feet closer and $ready $viciousMockery for if he shows himself again.")
            judge("Maya,\t your turn.")
            maya.determined("I'll move 30 feet closer as well,\t and $ready $mindSpike! ", args = ActionArgs(mergeNext = true))
            maya("If it works,\t he won't be able to hide from me again.")
            phoenix.stand.holdIt("If you do that,\t you immediately spend the spell slot,\t and it's wasted if he doesn't pop out again.")
            maya.angry("Drat.\t Then I'll ready $fireBolt.")
            franziska.cornered.objection("If you $ready any spell,\t it consumes your foolish concentration!")
            maya.thinking("Was I concentrating on something?")
            franziska.damage("You're concentrating on $haste! If you foolishly drop it,\t I lose my next turn!")
            maya.dull("Oh,\t then I shouldn't do that. ", args = ActionArgs(mergeNext = true))
            maya("I'll summon Polly instead.")
            polly("Hello there.")
            maya("That's $dieRoll a ${value(9)} for initiative.")
            judge("Polly will go after Franziska,\t who's up now.\t What do you do?")
            franziska("I'll run around the corner and look for the foolish fool.")
            judge.positive("He's immediately visible when you round the corner.")
            franziska.confident("I'll use $insightfulFighting,\t $dieRoll that's a ${value(25)} on my $insight check.")
            judge.positive("$dieRoll He gets a good $deception check,\t but not that good.")
            franziska.whipDesk("I'll hit him with my whip, $dieRoll with a ${value(19)} to hit.")
            judge.positive("It hits.")
            franziska("${diceRolls(4)} ${value(16)} slashing damage.")
            judge("He halves it with $uncannyDodge.")
            franziska("Then I'll $ready an attack when Polly helps.")
            judge.positive("Polly's up next.")
            maya.determined("Polly, fly around the corner,\t next to Crimus,\t and take the $help action!")
            polly("Never fear!\t I am here!")
            franziska.whipDesk("Now I can attack with $advantage. ${diceRolls(2)}")
            franziska.yell("Natural 20! ${diceRolls(8)}")
            franziska("That's ${value(32)} slashing damage to the fool!")
            maya("Didn't you already apply $sneakAttack to your previous attack?")
            franziska.bow("$sneakAttack can apply once per turn,\t not once per round,\t and this is Polly's turn.")
            maya("Wow!\t I should cast $haste on you more often!")
            judge.surprised("How do you want to do this?")
        }
    }.writeTo("ready.objection")
}