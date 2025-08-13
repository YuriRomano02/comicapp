package com.example.comicapp

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.comicapp.ui.theme.ComicAppTheme


data class DylanDogNumero(
    val numero: Int,
    val titolo: String,
    var posseduto: Boolean = false
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComicAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppDylanDog()
                }
            }
        }
    }
}


object SalvataggioFumetti {
    private const val PREFS_NAME = "dylan_dog_data"
    private const val KEY_NUMERI_POSSEDUTI = "numeri_posseduti"

    fun salvaNumeriPosseduti(context: Context, numeriPosseduti: Set<Int>) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putStringSet(KEY_NUMERI_POSSEDUTI, numeriPosseduti.map { it.toString() }.toSet())
        editor.apply()
    }

    fun caricaNumeriPosseduti(context: Context): Set<Int> {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val savedSet = prefs.getStringSet(KEY_NUMERI_POSSEDUTI, emptySet()) ?: emptySet()
        return savedSet.mapNotNull { it.toIntOrNull() }.toSet()
    }
}


fun getListaDylanDog(): List<DylanDogNumero> {
    return listOf(
        DylanDogNumero(1, "Dawn of the Dead"),
        DylanDogNumero(2, "Jack the Ripper"),
        DylanDogNumero(3, "The Nights of the Full Moon"),
        DylanDogNumero(4, "The Ghost of Anna Never"),
        DylanDogNumero(5, "The Killers"),
        DylanDogNumero(6, "The Beauty of the Devil"),
        DylanDogNumero(7, "The Twilight Zone"),
        DylanDogNumero(8, "The return of the monster"),
        DylanDogNumero(9, "Alpha and Omega"),
        DylanDogNumero(10, "Through the Looking Glass"),
        DylanDogNumero(11, "Diabolo the Great"),
        DylanDogNumero(12, "Killer!"),
        DylanDogNumero(13, "They Live Among Us"),
        DylanDogNumero(14, "Between Life and Death"),
        DylanDogNumero(15, "Channel 666"),
        DylanDogNumero(16, "The Castle of Fear"),
        DylanDogNumero(17, "The Lady in Black"),
        DylanDogNumero(18, "Cagliostro!"),
        DylanDogNumero(19, "Memories from the invisible"),
        DylanDogNumero(20, "From the Deep"),
        DylanDogNumero(21, "Cursed Day"),
        DylanDogNumero(22, "The Tunnel of Horror"),
        DylanDogNumero(23, "The Mysterious Island"),
        DylanDogNumero(24, "Pink Rabbits Kill"),
        DylanDogNumero(25, "Morgana"),
        DylanDogNumero(26, "After Midnight"),
        DylanDogNumero(27, "I Saw You Die"),
        DylanDogNumero(28, "Razor Blade"),
        DylanDogNumero(29, "When the city sleeps"),
        DylanDogNumero(30, "The Haunted House"),
        DylanDogNumero(31, "Grand Guignol"),
        DylanDogNumero(32, "Obsession"),
        DylanDogNumero(33, "Jekyll!"),
        DylanDogNumero(34, "The Darkness"),
        DylanDogNumero(35, "The Cliff of Ghosts"),
        DylanDogNumero(36, "A Midsummer Night's Nightmare"),
        DylanDogNumero(37, "The Tiger's Dream"),
        DylanDogNumero(38, "A voice from nowhere"),
        DylanDogNumero(39, "The Lord of Silence"),
        DylanDogNumero(40, "It Happened Tomorrow"),
        DylanDogNumero(41, "Golconda!"),
        DylanDogNumero(42, "The Hyena"),
        DylanDogNumero(43, "Nobody's Story"),
        DylanDogNumero(44, "Reflections of Death"),
        DylanDogNumero(45, "Goblin"),
        DylanDogNumero(46, "Hells"),
        DylanDogNumero(47, "Written in Blood"),
        DylanDogNumero(48, "Horror Paradise"),
        DylanDogNumero(49, "The Mystery of the Thames"),
        DylanDogNumero(50, "At the Edge of Time"),
        DylanDogNumero(51, "Evil"),
        DylanDogNumero(52, "The Red Mark"),
        DylanDogNumero(53, "The Queen of Darkness"),
        DylanDogNumero(54, "Delirium"),
        DylanDogNumero(55, "The Mummy"),
        DylanDogNumero(56, "Shadows"),
        DylanDogNumero(57, "Return to Twilight"),
        DylanDogNumero(58, "The Stone Hourglass"),
        DylanDogNumero(59, "People who disappear"),
        DylanDogNumero(60, "Frankenstein!"),
        DylanDogNumero(61, "Terror from the infinite"),
        DylanDogNumero(62, "The Vampires"),
        DylanDogNumero(63, "Maelstrom!"),
        DylanDogNumero(64, "The Secrets of Ramblyn"),
        DylanDogNumero(65, "The Cave Beast"),
        DylanDogNumero(66, "Match with Death"),
        DylanDogNumero(67, "The Man Who Lived Twice"),
        DylanDogNumero(68, "The Spectre in the Dark"),
        DylanDogNumero(69, "Witch Hunt"),
        DylanDogNumero(70, "The Forest of Assassins"),
        DylanDogNumero(71, "The Mantis Crimes"),
        DylanDogNumero(72, "The Last Full Moon"),
        DylanDogNumero(73, "Armageddon!"),
        DylanDogNumero(74, "The Long Goodbye"),
        DylanDogNumero(75, "The Throat Cutter"),
        DylanDogNumero(76, "Black Curse"),
        DylanDogNumero(77, "The Last Man on Earth"),
        DylanDogNumero(78, "The Killers from the Dark"),
        DylanDogNumero(79, "The Evil Fairy"),
        DylanDogNumero(80, "Killex's Brain"),
        DylanDogNumero(81, "Johnny Freak"),
        DylanDogNumero(82, "Far from the light"),
        DylanDogNumero(83, "Doktor Terror"),
        DylanDogNumero(84, "Zed"),
        DylanDogNumero(85, "Ghosts"),
        DylanDogNumero(86, "Story of a poor devil"),
        DylanDogNumero(87, "Bloody Parties"),
        DylanDogNumero(88, "Beyond Death"),
        DylanDogNumero(89, "The Knights of Time"),
        DylanDogNumero(90, "Titanic"),
        DylanDogNumero(91, "Metamorphosis"),
        DylanDogNumero(92, "The Mosaic of Horror"),
        DylanDogNumero(93, "Presences..."),
        DylanDogNumero(94, "The woman who kills the past"),
        DylanDogNumero(95, "The Days of Nightmare"),
        DylanDogNumero(96, "The Challenge"),
        DylanDogNumero(97, "Behind the curtain"),
        DylanDogNumero(98, "Satan's Gaze"),
        DylanDogNumero(99, "Deadly Symphony"),
        DylanDogNumero(100, "The story of Dylan Dog"),
        DylanDogNumero(101, "The Door to Hell"),
        DylanDogNumero(102, "Brothers from another time"),
        DylanDogNumero(103, "Demons"),
        DylanDogNumero(104, "Endless Night"),
        DylanDogNumero(105, "The Horrible Invasion"),
        DylanDogNumero(106, "The revolt of the machines"),
        DylanDogNumero(107, "The land of colored shadows"),
        DylanDogNumero(108, "The Guardian of Memory"),
        DylanDogNumero(109, "The Flight of the Ostrich"),
        DylanDogNumero(110, "Aracne"),
        DylanDogNumero(111, "The Prophecy"),
        DylanDogNumero(112, "Close Encounters"),
        DylanDogNumero(113, "The Dark Half"),
        DylanDogNumero(114, "The Paper Prison"),
        DylanDogNumero(115, "The Beast's Den"),
        DylanDogNumero(116, "The Governess"),
        DylanDogNumero(117, "The fifth season"),
        DylanDogNumero(118, "The Game of Destiny"),
        DylanDogNumero(119, "The Cat's Eye"),
        DylanDogNumero(120, "Abyss"),
        DylanDogNumero(121, "Till Death Do Us Part"),
        DylanDogNumero(122, "The Border"),
        DylanDogNumero(123, "Phoenix"),
        DylanDogNumero(124, "The Witch's Peak"),
        DylanDogNumero(125, "Three for zero"),
        DylanDogNumero(126, "The Red Death"),
        DylanDogNumero(127, "Johnny's Heart"),
        DylanDogNumero(128, "The Call of the Wild"),
        DylanDogNumero(129, "The Return of Killex"),
        DylanDogNumero(130, "The Necromancer"),
        DylanDogNumero(131, "When the Stars Fall"),
        DylanDogNumero(132, "The Man Who Sells Time"),
        DylanDogNumero(133, "Ananga!"),
        DylanDogNumero(134, "The Jaguar's Cry"),
        DylanDogNumero(135, "Scanner"),
        DylanDogNumero(136, "Someone Up There Is Calling Us"),
        DylanDogNumero(137, "The Lost City"),
        DylanDogNumero(138, "Bad Thoughts"),
        DylanDogNumero(139, "Hook the implacable"),
        DylanDogNumero(140, "Towards a distant world"),
        DylanDogNumero(141, "The Exterminating Angel"),
        DylanDogNumero(142, "Black Soul"),
        DylanDogNumero(143, "Apocalypse"),
        DylanDogNumero(144, "Beautiful to die for"),
        DylanDogNumero(145, "The Infernal Dog"),
        DylanDogNumero(146, "Ghost Hotel"),
        DylanDogNumero(147, "Stardust"),
        DylanDogNumero(148, "Abysses of Madness"),
        DylanDogNumero(149, "The Alien"),
        DylanDogNumero(150, "The Viper's Kiss"),
        DylanDogNumero(151, "The Lake in the Sky"),
        DylanDogNumero(152, "Death at home"),
        DylanDogNumero(153, "The Road to Nowhere"),
        DylanDogNumero(154, "The Beat of Time"),
        DylanDogNumero(155, "The new lineage"),
        DylanDogNumero(156, "The Giant"),
        DylanDogNumero(157, "The Sleep of Reason"),
        DylanDogNumero(158, "Born to Kill"),
        DylanDogNumero(159, "Extrasensory Perceptions"),
        DylanDogNumero(160, "The Druid"),
        DylanDogNumero(161, "The Dark Lady's Smile"),
        DylanDogNumero(162, "The Captive God"),
        DylanDogNumero(163, "The Perfect World"),
        DylanDogNumero(164, "The Screaming Woman"),
        DylanDogNumero(165, "Isle of Dogs"),
        DylanDogNumero(166, "Surviving Eden"),
        DylanDogNumero(167, "Medusa"),
        DylanDogNumero(168, "The River of Oblivion"),
        DylanDogNumero(169, "The Mirror of the Soul"),
        DylanDogNumero(170, "The Little Death"),
        DylanDogNumero(171, "Diabolical Possession"),
        DylanDogNumero(172, "Memories from the Underground"),
        DylanDogNumero(173, "For a Fistful of Pounds"),
        DylanDogNumero(174, "A stroke of bad luck"),
        DylanDogNumero(175, "The Seed of Madness"),
        DylanDogNumero(176, "The \"project\""),
        DylanDogNumero(177, "The Disciple"),
        DylanDogNumero(178, "Letters from Hell"),
        DylanDogNumero(179, "The Third Side of the Coin"),
        DylanDogNumero(180, "Hunting Nights"),
        DylanDogNumero(181, "The Mark of the Vampire"),
        DylanDogNumero(182, "Safari"),
        DylanDogNumero(183, "Requiem for a Monster"),
        DylanDogNumero(184, "The Mysteries of Venice"),
        DylanDogNumero(185, "Phobia"),
        DylanDogNumero(186, "The Black Man"),
        DylanDogNumero(187, "Lost Loves"),
        DylanDogNumero(188, "The Bangor Labyrinth"),
        DylanDogNumero(189, "The Price of Death"),
        DylanDogNumero(190, "Mordecai's Secret"),
        DylanDogNumero(191, "Charade"),
        DylanDogNumero(192, "Sunspots"),
        DylanDogNumero(193, "The eternal illusion"),
        DylanDogNumero(194, "The Witch of Brentford"),
        DylanDogNumero(195, "A strange client"),
        DylanDogNumero(196, "Who Killed Santa Claus?"),
        DylanDogNumero(197, "The Four Elements"),
        DylanDogNumero(198, "The Law of the Jungle"),
        DylanDogNumero(199, "Homo homini lupus"),
        DylanDogNumero(200, "The number two hundred"),
        DylanDogNumero(201, "Daisy & Queen"),
        DylanDogNumero(202, "The Seventh Circle"),
        DylanDogNumero(203, "The Milford Family"),
        DylanDogNumero(204, "Resurrection"),
        DylanDogNumero(205, "The Schoolmate"),
        DylanDogNumero(206, "Fog"),
        DylanDogNumero(207, "The Temple of the Second Life"),
        DylanDogNumero(208, "An Unknown World"),
        DylanDogNumero(209, "The Beast"),
        DylanDogNumero(210, "The Pied Piper"),
        DylanDogNumero(211, "The House of Ghosts"),
        DylanDogNumero(212, "Necropolis"),
        DylanDogNumero(213, "The Witch Slayer"),
        DylanDogNumero(214, "Manila"),
        DylanDogNumero(215, "The Well of Deception"),
        DylanDogNumero(216, "The cursed grimoire"),
        DylanDogNumero(217, "The Big Sleep"),
        DylanDogNumero(218, "The Painted Nightmare"),
        DylanDogNumero(219, "The Tenth Victim"),
        DylanDogNumero(220, "Unfair Competition"),
        DylanDogNumero(221, "The Devil's Touch"),
        DylanDogNumero(222, "The Wisdom of the Dead"),
        DylanDogNumero(223, "The Two Lives of Dream"),
        DylanDogNumero(224, "On the Edge of Memories"),
        DylanDogNumero(225, "Insomnia"),
        DylanDogNumero(226, "24 hours to not die"),
        DylanDogNumero(227, "Killer Instinct"),
        DylanDogNumero(228, "Beyond that door"),
        DylanDogNumero(229, "Heaven Can Wait"),
        DylanDogNumero(230, "The mysterious tenant"),
        DylanDogNumero(231, "Nightmare Tour"),
        DylanDogNumero(232, "A Ghost in Scotland Yard"),
        DylanDogNumero(233, "The Unwelcome Guest"),
        DylanDogNumero(234, "The Last Arcanum"),
        DylanDogNumero(235, "Sonata macabre"),
        DylanDogNumero(236, "Designated Victims"),
        DylanDogNumero(237, "In the shadow of the volcano"),
        DylanDogNumero(238, "The Heirs of the Twilight"),
        DylanDogNumero(239, "The Big Bastard"),
        DylanDogNumero(240, "Uchronìa"),
        DylanDogNumero(241, "Xabaras!"),
        DylanDogNumero(242, "In the name of the father"),
        DylanDogNumero(243, "The Assassin is Among Us"),
        DylanDogNumero(244, "Marty"),
        DylanDogNumero(245, "The Freaks' Cemetery"),
        DylanDogNumero(246, "The Inn at the End of the World"),
        DylanDogNumero(247, "All Sally's loves"),
        DylanDogNumero(248, "Steel Soul"),
        DylanDogNumero(249, "Buried Memories"),
        DylanDogNumero(250, "Elevator to Hell"),
        DylanDogNumero(251, "The lighthouse keeper"),
        DylanDogNumero(252, "Poltergeist!"),
        DylanDogNumero(253, "Sullivan's Monsters"),
        DylanDogNumero(254, "Lives at stake"),
        DylanDogNumero(255, "Room number 63"),
        DylanDogNumero(256, "The ferocious Takurr"),
        DylanDogNumero(257, "The Keeper"),
        DylanDogNumero(258, "The Fury of Upyr"),
        DylanDogNumero(259, "From a distant galaxy"),
        DylanDogNumero(260, "Casper's Sentence"),
        DylanDogNumero(261, "Greetings from Moonlight"),
        DylanDogNumero(262, "The Arsonist"),
        DylanDogNumero(263, "Watership Down"),
        DylanDogNumero(264, "Liam the Liar"),
        DylanDogNumero(265, "Reincarnations"),
        DylanDogNumero(266, "The Dragon's Claws"),
        DylanDogNumero(267, "Things from the Other World"),
        DylanDogNumero(268, "Module A38"),
        DylanDogNumero(269, "The Professionals"),
        DylanDogNumero(270, "The King of the Flies"),
        DylanDogNumero(271, "The Little Devil"),
        DylanDogNumero(272, "The Graham Massacre"),
        DylanDogNumero(273, "Buried Alive!"),
        DylanDogNumero(274, "Escape from the past"),
        DylanDogNumero(275, "The Thirteenth Man"),
        DylanDogNumero(276, "A stranger on the road"),
        DylanDogNumero(277, "The Day of the Werewolf"),
        DylanDogNumero(278, "Descent into the Abyss"),
        DylanDogNumero(279, "The Garden of Illusions"),
        DylanDogNumero(280, "Mater Morbi"),
        DylanDogNumero(281, "The path of life"),
        DylanDogNumero(282, "Dangerous Liaisons"),
        DylanDogNumero(283, "The Persecutor"),
        DylanDogNumero(284, "In the sign of pain"),
        DylanDogNumero(285, "The Brain Thief"),
        DylanDogNumero(286, "Re-education program"),
        DylanDogNumero(287, "The New Barbarians"),
        DylanDogNumero(288, "Forced Labor"),
        DylanDogNumero(289, "The Way of Enigmas"),
        DylanDogNumero(290, "The Dark Heir"),
        DylanDogNumero(291, "Without trickery or deception"),
        DylanDogNumero(292, "Captive Souls"),
        DylanDogNumero(293, "The Last Immortals"),
        DylanDogNumero(294, "It's Raining Frogs"),
        DylanDogNumero(295, "Between husband and wife..."),
        DylanDogNumero(296, "The Second Chance"),
        DylanDogNumero(297, "The Spell"),
        DylanDogNumero(298, "In the Killer's Head"),
        DylanDogNumero(299, "A loyal clientele"),
        DylanDogNumero(300, "Family Portrait"),
        DylanDogNumero(301, "The Embalmer"),
        DylanDogNumero(302, "The perfect crime"),
        DylanDogNumero(303, "The Bone Eater"),
        DylanDogNumero(304, "Terror at high altitude"),
        DylanDogNumero(305, "The Museum of Crime"),
        DylanDogNumero(306, "The trunk of wonders"),
        DylanDogNumero(307, "The Killer Next Door"),
        DylanDogNumero(308, "The Mother Goddess"),
        DylanDogNumero(309, "The Autopsy"),
        DylanDogNumero(310, "I, the monster"),
        DylanDogNumero(311, "The Crow's Judgment"),
        DylanDogNumero(312, "Alien Epidemic"),
        DylanDogNumero(313, "The Collapse"),
        DylanDogNumero(314, "Signs of the End"),
        DylanDogNumero(315, "The legion of skeletons"),
        DylanDogNumero(316, "Blacky"),
        DylanDogNumero(317, "The impostor"),
        DylanDogNumero(318, "Urban Legends"),
        DylanDogNumero(319, "The Returners"),
        DylanDogNumero(320, "The Fugitive"),
        DylanDogNumero(321, "Young Vampires"),
        DylanDogNumero(322, "The Banshee's Cry"),
        DylanDogNumero(323, "The Eye of Balor"),
        DylanDogNumero(324, "Hate Never Dies"),
        DylanDogNumero(325, "A new life"),
        DylanDogNumero(326, "On the skin"),
        DylanDogNumero(327, "The Sleepwalkers"),
        DylanDogNumero(328, "Trash Island"),
        DylanDogNumero(329, "...and leaves a beautiful corpse"),
        DylanDogNumero(330, "The magnificent creature"),
        DylanDogNumero(331, "Death is not enough"),
        DylanDogNumero(332, "Destined for the Earth"),
        DylanDogNumero(333, "The Autumn Wanderers"),
        DylanDogNumero(334, "Hell's Pay"),
        DylanDogNumero(335, "The ordeal"),
        DylanDogNumero(336, "Burn, Witch... Burn!"),
        DylanDogNumero(337, "Deep Space"),
        DylanDogNumero(338, "Never Again, Inspector Bloch"),
        DylanDogNumero(339, "Anarchy in the UK"),
        DylanDogNumero(340, "Welcome to Wickedford"),
        DylanDogNumero(341, "In the service of chaos"),
        DylanDogNumero(342, "The Heart of Men"),
        DylanDogNumero(343, "In the smoke of battle"),
        DylanDogNumero(344, "The taste of water"),
        DylanDogNumero(345, "The Guardian Spirits"),
        DylanDogNumero(346, "...and to ashes you will return"),
        DylanDogNumero(347, "The Abandoned"),
        DylanDogNumero(348, "The Wrong Hand"),
        DylanDogNumero(349, "The dead woman does not forget"),
        DylanDogNumero(350, "Tears of Stone"),
        DylanDogNumero(351, "At the bottom of evil"),
        DylanDogNumero(352, "The Calligraphy of Pain"),
        DylanDogNumero(353, "The Inquisitor General"),
        DylanDogNumero(354, "Poverty and Cruelty"),
        DylanDogNumero(355, "The Man of Your Dreams"),
        DylanDogNumero(356, "The Human Machine"),
        DylanDogNumero(357, "Forbidden to minors"),
        DylanDogNumero(358, "The Price of Meat"),
        DylanDogNumero(359, "On the bottom"),
        DylanDogNumero(360, "Remington House"),
        DylanDogNumero(361, "Mater Dolorosa"),
        DylanDogNumero(362, "After a long silence"),
        DylanDogNumero(363, "Lost Things"),
        DylanDogNumero(364, "The Wild Years"),
        DylanDogNumero(365, "Chronodrama"),
        DylanDogNumero(366, "Family Day"),
        DylanDogNumero(367, "The Last Night's Lullaby"),
        DylanDogNumero(368, "The Angel's Pass"),
        DylanDogNumero(369, "Graphic Horror Novel"),
        DylanDogNumero(370, "The Terror"),
        DylanDogNumero(371, "Dampyr is coming"),
        DylanDogNumero(372, "Black and White"),
        DylanDogNumero(373, "The Flame"),
        DylanDogNumero(374, "The End of Darkness"),
        DylanDogNumero(375, "In the mystery"),
        DylanDogNumero(376, "Graphic Horror Novel: the sequel!"),
        DylanDogNumero(377, "Non-human"),
        DylanDogNumero(378, "Sleeping, maybe dreaming"),
        DylanDogNumero(379, "The Tango of Lost Souls"),
        DylanDogNumero(380, "Nobody is innocent"),
        DylanDogNumero(381, "Trypophobia"),
        DylanDogNumero(382, "The Butcher and the Rose"),
        DylanDogNumero(383, "Deep Black"),
        DylanDogNumero(384, "The machine that didn't want to die"),
        DylanDogNumero(385, "You'll Lose Your Head"),
        DylanDogNumero(386, "Hyppolita"),
        DylanDogNumero(387, "Let Chaos Reign!"),
        DylanDogNumero(388, "Exercise number 6"),
        DylanDogNumero(389, "The Survivor"),
        DylanDogNumero(390, "The Fall of the Gods"),
        DylanDogNumero(391, "The Blood of the Earth"),
        DylanDogNumero(392, "The primordium"),
        DylanDogNumero(393, "The World Falls Apart"),
        DylanDogNumero(394, "Eternal Seasons"),
        DylanDogNumero(395, "Of Time and Other Illusions"),
        DylanDogNumero(396, "His Name Was War"),
        DylanDogNumero(397, "Morbo M"),
        DylanDogNumero(398, "Who dies is seen again"),
        DylanDogNumero(399, "Today we're married"),
        DylanDogNumero(400, "And now, the Apocalypse!"),
        DylanDogNumero(401, "The Black Dawn"),
        DylanDogNumero(402, "The Red Sunset"),
        DylanDogNumero(403, "The Blade, the Moon and the Ogre"),
        DylanDogNumero(404, "Anna Forever"),
        DylanDogNumero(405, "The Killer"),
        DylanDogNumero(406, "The Last Laugh"),
        DylanDogNumero(407, "The Entity"),
        DylanDogNumero(408, "Peering into the Abyss"),
        DylanDogNumero(409, "Back to the Dark"),
        DylanDogNumero(410, "The Eternal Night"),
        DylanDogNumero(411, "The Third Day"),
        DylanDogNumero(412, "A Bad Year"),
        DylanDogNumero(413, "The Masters of Nothingness"),
        DylanDogNumero(414, "Innocent Games"),
        DylanDogNumero(415, "Revenge in Mask"),
        DylanDogNumero(416, "The Prisoner"),
        DylanDogNumero(417, "The Hour of Judgement"),
        DylanDogNumero(418, "Sally"),
        DylanDogNumero(419, "Albachiara"),
        DylanDogNumero(420, "Jenny"),
        DylanDogNumero(421, "The Variable"),
        DylanDogNumero(422, "The Blue Moment"),
        DylanDogNumero(423, "In the warrior's room"),
        DylanDogNumero(424, "Candiweb"),
        DylanDogNumero(425, "The Predators"),
        DylanDogNumero(426, "Death at stake"),
        DylanDogNumero(427, "Life and its opposite"),
        DylanDogNumero(428, "Where Dreams Go to Die"),
        DylanDogNumero(429, "The Beast of the Moor"),
        DylanDogNumero(430, "Northern Abysses"),
        DylanDogNumero(431, "Nothing is forever"),
        DylanDogNumero(432, "I will protect you"),
        DylanDogNumero(433, "Riding the Lightning"),
        DylanDogNumero(434, "The Infernauts"),
        DylanDogNumero(435, "Two Minutes to Midnight"),
        DylanDogNumero(436, "Not with a bang..."),
        DylanDogNumero(437, "...But with a moan"),
        DylanDogNumero(438, "The Nameless City"),
        DylanDogNumero(439, "The Silent Invasion"),
        DylanDogNumero(440, "And Then There Were None"),
        DylanDogNumero(441, "The Guilty Conspiracy"),
        DylanDogNumero(442, "Fragments"),
        DylanDogNumero(443, "The Indifferent"),
        DylanDogNumero(444, "Death in sixteen ninths"),
        DylanDogNumero(445, "Xenon!"),
        DylanDogNumero(446, "The Other Side of the Mirror"),
        DylanDogNumero(447, "Hazel the Dead"),
        DylanDogNumero(448, "Anatomy of the Soul"),
        DylanDogNumero(449, "The measure of the world"),
        DylanDogNumero(450, "Shock"),
        DylanDogNumero(451, "Bad Land"),
        DylanDogNumero(452, "A Quiet Scary Friday"),
        DylanDogNumero(453, "Blood Rain"),
        DylanDogNumero(454, "Sting to Hell"),
        DylanDogNumero(455, "Escape from Golconda"),
        DylanDogNumero(456, "He Who Devours Shadows"),
        DylanDogNumero(457, "The Thin Black Line"),
        DylanDogNumero(458, "Seven Lives"),
        DylanDogNumero(459, "Hikikomori"),
        DylanDogNumero(460, "December 32")
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDylanDog() {
    val context = LocalContext.current

    // Carica i dati salvati all'avvio
    val numeriPosseduti = remember { SalvataggioFumetti.caricaNumeriPosseduti(context) }

    // Crea la lista di Dylan Dog con i dati salvati
    var listaDylanDog by remember {
        mutableStateOf(
            getListaDylanDog().map { dylanDog ->
                dylanDog.copy(posseduto = numeriPosseduti.contains(dylanDog.numero))
            }
        )
    }

    // Stato per la ricerca
    var testoRicerca by remember { mutableStateOf("") }
    var mostraRicerca by remember { mutableStateOf(false) }

    // Filtro per la ricerca (numero O titolo)
    val numeroFiltrati = remember(listaDylanDog, testoRicerca) {
        if (testoRicerca.isBlank()) {
            listaDylanDog
        } else {
            listaDylanDog.filter {
                it.numero.toString().contains(testoRicerca, ignoreCase = true) ||
                        it.titolo.contains(testoRicerca, ignoreCase = true)
            }
        }
    }

    val listState = rememberLazyListState()
    val numeroTotale = listaDylanDog.size


    LaunchedEffect(listaDylanDog) {
        val posseduti = listaDylanDog.filter { it.posseduto }.map { it.numero }.toSet()
        SalvataggioFumetti.salvaNumeriPosseduti(context, posseduti)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "🕵️ Dylan Dog",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "L'Indagatore dell'Incubo 💾",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            IconButton(onClick = { mostraRicerca = !mostraRicerca }) {
                Icon(Icons.Default.Search, contentDescription = "Cerca")
            }
        }


        if (mostraRicerca) {
            OutlinedTextField(
                value = testoRicerca,
                onValueChange = { testoRicerca = it },
                label = { Text("Cerca numero o titolo...") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                singleLine = true
            )
        }


        val numeriPosseduti = listaDylanDog.count { it.posseduto }
        val percentualeCompleta = (numeriPosseduti * 100) / numeroTotale

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "🔍 Progresso Collezione",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(8.dp))


                LinearProgressIndicator(
                    progress = numeriPosseduti.toFloat() / numeroTotale,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Posseduti: $numeriPosseduti/$numeroTotale")
                    Text("$percentualeCompleta%")
                }
                Text("Mancanti: ${numeroTotale - numeriPosseduti}")
            }
        }


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = {
                    listaDylanDog = listaDylanDog.map { it.copy(posseduto = true) }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Segna Tutti", fontSize = 12.sp)
            }

            OutlinedButton(
                onClick = {
                    listaDylanDog = listaDylanDog.map { it.copy(posseduto = false) }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Deseleziona Tutti", fontSize = 12.sp)
            }
        }


        LazyColumn(
            state = listState,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(numeroFiltrati) { dylanDog ->
                DylanDogItem(
                    dylanDog = dylanDog,
                    onCheckedChange = { isChecked ->
                        listaDylanDog = listaDylanDog.map {
                            if (it.numero == dylanDog.numero) {
                                it.copy(posseduto = isChecked)
                            } else {
                                it
                            }
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun DylanDogItem(
    dylanDog: DylanDogNumero,
    onCheckedChange: (Boolean) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = if (dylanDog.posseduto)
                MaterialTheme.colorScheme.primaryContainer
            else
                MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = dylanDog.posseduto,
                onCheckedChange = onCheckedChange
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Dylan Dog #${dylanDog.numero}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = dylanDog.titolo,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Text(
                text = if (dylanDog.posseduto) "✅" else "❌",
                fontSize = 18.sp,
                textAlign = TextAlign.End
            )
        }
    }
}