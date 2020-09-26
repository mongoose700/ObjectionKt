package derekpeirce.objectionmaker

fun main() {
    makeTrial {
        Judge().stand.say("Now that you've defeated the dragon and his cohorts, what would you like to do?")
        Phoenix().stand.say("I'm going to search the room for any hidden hoard")
        VonKarma().stand.say("I'll follow and keep an eye out for any remaining threats.")
        Maya().stand.say("I'll stay back, but you can take Polly with you! She'll help in your search!")
        Judge().positive.say("Very well. Phoenix, you may make an Investigation check, with advantage from the familiar's Help.")
        Judge().stand.say("Von Karma, with your passive Perception, you can tell that there are no more threats about you.")
        Phoenix().stand.say("Great! I rolled a 12, which comes out to a 26 with modifiers, so")
        VonKarma().snapFinger.holdIt("While Phoenix is busy investigating, I'll take out my dagger and stab him in the back!")

    }.writeTo("example.json")
}