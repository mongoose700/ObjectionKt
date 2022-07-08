package derekpeirce.objectionmaker

fun main() {
    makeTrial {
        val judge = Judge()
        val phoenix = Phoenix()
        val maya = Maya()
        val vonKarma = VonKarma()
        val pearl = Pearl()

        playMusic(Music.TRIAL) {
            judge("Von Karma,\t it's your turn.")
            vonKarma.stand("I'll run up to the thug and stab him with my longsword.\t ${diceRolls(2)} That's a ${value(19)} to hit for ${value(9)} slashing damage.")
            judge.surprised("I didn't say that it hit yet,\t but it did.\t It's now the thug's turn.\t He'll run")
            vonKarma.snapFinger.objection("I get my opportunity attack first.\t $dieRoll That's a ${value(23)} to hit with my net!\t The thug is now $restrained!")
        }
        playMusic(Music.SUSPENSE) {
            phoenix.point.holdIt("There are so many things wrong with that I don't know where to begin.")
            phoenix.read("First of all,\t you can't make a ranged attack for your opportunity attack,\t and nets are ranged weapons.")
            phoenix.point("But even if you could,\t you'd need to use $dexterity instead of $strength,\t and your $dexterity modifier is ${value(0)}.")
            phoenix.deskSlam("And even then you'd have $disadvantage from making a ranged attack while within five feet of a hostile creature.")
            vonKarma.confident.objection("All of those points are irrelevant.\t I didn't say I was throwing the net,\t I used it as an $improvisedWeapon.")
            phoenix.damage("But then it doesn't work,\t right?")
            vonKarma.stand.takeThat("The special property of the net says,\t \"A Large or smaller creature hit by a net is $restrained until it is freed.\"")
            vonKarma.confident("It doesn't say anything about requiring the net to be thrown.")
            maya.thinking("But isn't there a rule about not being able to attack with the net if you've already attacked with another weapon?\t You already attacked the thug with your longsword.")
            vonKarma.armsCrossed("No,\t the rule is simply that when you use your action, bonus action, or reaction to attack with the net,\t that's the only attack you get to make.")
            vonKarma.snapFinger("I'm only making one attack with my reaction.")
            judge.surprised("Then I suppose that the thug is now $restrained.\t That's a very powerful use of an opportunity attack,\t now the thug doesn't get to attack someone else.")
            judge("And he will need to use his action to try to escape,\t since he can't deal slashing damage...")
            vonKarma.confident("It will be even better in a few levels when I take the $tavernBrawler feat to get proficiency with $improvisedWeapons!")
        }
        playMusic(Music.TRUTH) {
            maya.determined.holdIt("Wait,\t I've looked this up online.\t There's a tweet from Jeremy Crawford:")
            maya.stand("\"If you use a weapon in a way that turns it into an improvised weapon—such as smacking someone with a bow—that weapon has none of its regular properties, unless the DM rules otherwise.\"")
            vonKarma.armsCrossed.objection("Those tweets are not official rulings!\t They have no bearing on how the rules are actually written and should be enforced.")
            judge.negative("While they aren't official,\t they can still provide guidance.\t The final decision as to whether such a ruling applies at this table is up to me,\t not you.")
            vonKarma.cornered("But there's no reason to ban it!\t It's not that powerful of a strategy,\t I don't get to add my proficiency bonus to the attack roll!")
            phoenix.point.takeThat("But you said so yourself,\t you can take $tavernBrawler to get that proficiency!")
            vonKarma.damage("Fine.\t DM,\t what is your ruling?")
            judge.eyesClosed("I'm inclined to rule that in this instance,\t the net will not restrain the thug.\t Roll for damage.")
            vonKarma.breakdown1("Bah.\t $dieRoll That's ${value(7)} damage of whatever type the net deals.")
        }
        pearl.sad("Mr. Nick?")
        phoenix("Pearls,\t what's wrong?")
        pearl.worried("Does this mean that my $sharpshooter $greatWeaponMaster build with a longbow doesn't work?")
        phoenix.damage("")
    }.writeTo("improvisedWeapon.objection")
}