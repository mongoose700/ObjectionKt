package derekpeirce.objectionmaker

fun main() {
    makeTrial {
        val judge = Judge()
        val phoenix = Phoenix()
        val larry = Larry()
        val miles = Edgeworth(name = "Miles")

        playMusic(Music.TRIAL) {
            judge("At the end of the cave,\t you see darkness", args = ActionArgs(interrupted = true))
            phoenix.stand.objection("I have darkvision.")
            judge.negative("I'm aware,\t you just got the $gogglesOfNight earlier this session.")
            judge("But you can't see through this darkness.")
            miles("Then it's a good thing Larry has $devilsSight.")
        }
        playMusic(Music.SUSPENSE) {
            larry.thinkScratchHead("I actually swapped out that invocation for $eyesOfTheRuneKeeper when we reached level 4.")
            miles.armsCrossed("Disappointing.\t I do have $blindsight from my fighting style,\t I'll try to cautiously walk around in the darkness.")
            judge("Make a $perception check.")
            miles("$dieRoll\t I got a 12.")
            judge.eyesClosed("You find that it's a small room,\t but you don't notice anything in particular.")
            larry("Oh,\t I have the $light cantrip now!\t That's why I got rid of the invocation,\t it seemed redundant.")
            phoenix("You have $light?\t I thought your patron was Lilith,\t the...\t succubus?")
            larry.cry("Well,\t she ended up going to the Nine Hells and stopped responding to my telepathic messages.")
            larry.happy("But then I met Seraphina,\t and we hit it off.\t She offered me some of her powers.\t You should meet her,\t she's a real angel!")
            phoenix("(I really hope things go better for him this time...)", args = ActionArgs(doNotTalk = true))
            larry.think("So I don't have $devilsSight anymore.\t But I'll cast $light on a rock and throw it into the darkness.")
            judge.negative("The moment the light from the rock touches the darkness,\t it is dispelled.")
            larry.thumbsUp("The darkness is dispelled?")
            judge("${Sound.WHOOPS} No,\t the light.")
            phoenix.point("I'll try lighting a torch and sticking it in the darkness.")
            judge.negative("While the torch isn't extinguished,\t you can't see it while it's in the darkness.")
        }
        playMusic(Music.CROSS_EXAMINING) {
            phoenix.breakdown("I don't think I have anything for this.")
            judge("Larry,\t since you're able to learn $darkness as a warlock,\t after some reflection you remember how the $darkness spell works.")
            judge("Nonmagical light can't illuminate it,\t and any spells of 2nd level or lower that create light that overlaps with the darkness are dispelled.")
            phoenix("So we'd need a 3rd level spell to see in this darkness?\t But we're only level 4!")
            larry.cry("Why didn't we buy the $driftglobe?")
            judge(
                "(I was relying on Larry being able to see through it.\t I don't have a plan for this.)",
                args = ActionArgs(doNotTalk = true)
            )
            miles("I didn't want to have to use this out of combat...")
            miles.yell("I'll invoke my $channelDivinity: $sacredWeapon.")
            miles("My sword emits bright light in a 20 foot radius,\t and dim light 20 feet beyond that.")
            phoenix.point.holdIt("But that isn't a spell of 3rd level or higher,\t so it will just get dispelled!")
            miles.confidentSmirk.objection("That isn't the requirement.\t It only gets dispelled if it's a spell of 2nd level or lower.\t It's not a spell,\t so it can't be dispelled.")
            miles.point("And since $channelDivinity is explicitly described as magical,\t this light is magical,\t so it illuminates the darkness.")
        }
        playMusic(Music.TRUTH) {
            judge.positive("As you walk towards the darkness,\t it is illuminated.\t At the back of the room,\t there is writing in a language that you don't understand.")
            larry.happy.holdIt("I have $eyesOfTheRuneKeeper now!\t It lets me read any writing!")
            judge.positive("Yes,\t you alone can read it.\t It says \"bring the thunder.\"")
            phoenix.point("Sounds pretty straightforward,\t I'll cast $thunderwave at the wall.")
            judge.positive("The room rumbles,\t and part of the wall falls away,\t revealing a passage.")
            judge.stand("Sentient shadows begin to pour into the room.\t Miles,\t you have thirty seconds left with your $sacredWeapon.\t Everyone,\t roll initiative.")
        }
    }.writeTo("magicLight.objection")
}