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



data class DylanDogNumero(
    val numero: Int,
    val titolo: String,
    var posseduto: Boolean = false
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {  // Cambiato da ComicAppTheme a MaterialTheme
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
        DylanDogNumero(1, "L'alba dei morti viventi"),
        DylanDogNumero(2, "Jack lo squartatore"),
        DylanDogNumero(3, "Le notti della luna piena"),
        DylanDogNumero(4, "Il fantasma di Anna Never"),
        DylanDogNumero(5, "Gli uccisori"),
        DylanDogNumero(6, "La bellezza del demonio"),
        DylanDogNumero(7, "La zona del crepuscolo"),
        DylanDogNumero(8, "Il ritorno del mostro"),
        DylanDogNumero(9, "Alfa e Omega"),
        DylanDogNumero(10, "Attraverso lo specchio"),
        DylanDogNumero(11, "Diabolo il grande"),
        DylanDogNumero(12, "Killer!"),
        DylanDogNumero(13, "Vivono tra noi"),
        DylanDogNumero(14, "Fra la vita e la morte"),
        DylanDogNumero(15, "Canale 666"),
        DylanDogNumero(16, "Il castello della paura"),
        DylanDogNumero(17, "La Dama in nero"),
        DylanDogNumero(18, "Cagliostro!"),
        DylanDogNumero(19, "Memorie dall'invisibile"),
        DylanDogNumero(20, "Dal profondo"),
        DylanDogNumero(21, "Giorno maledetto"),
        DylanDogNumero(22, "Il tunnel dell'orrore"),
        DylanDogNumero(23, "L'isola misteriosa"),
        DylanDogNumero(24, "I conigli rosa uccidono"),
        DylanDogNumero(25, "Morgana"),
        DylanDogNumero(26, "Dopo mezzanotte"),
        DylanDogNumero(27, "Ti ho visto morire"),
        DylanDogNumero(28, "Lama di rasoio"),
        DylanDogNumero(29, "Quando la città dorme"),
        DylanDogNumero(30, "La casa infestata"),
        DylanDogNumero(31, "Grand Guignol"),
        DylanDogNumero(32, "Ossessione"),
        DylanDogNumero(33, "Jekyll!"),
        DylanDogNumero(34, "Il buio"),
        DylanDogNumero(35, "La scogliera degli spettri"),
        DylanDogNumero(36, "Incubo di una notte di mezza estate"),
        DylanDogNumero(37, "Il sogno della tigre"),
        DylanDogNumero(38, "Una voce dal nulla"),
        DylanDogNumero(39, "Il Signore del Silenzio"),
        DylanDogNumero(40, "Accadde domani"),
        DylanDogNumero(41, "Golconda!"),
        DylanDogNumero(42, "La iena"),
        DylanDogNumero(43, "Storia di Nessuno"),
        DylanDogNumero(44, "Riflessi di morte"),
        DylanDogNumero(45, "Goblin"),
        DylanDogNumero(46, "Inferni"),
        DylanDogNumero(47, "Scritto con il sangue"),
        DylanDogNumero(48, "Horror Paradise"),
        DylanDogNumero(49, "Il mistero del Tamigi"),
        DylanDogNumero(50, "Ai confini del tempo"),
        DylanDogNumero(51, "Il Male"),
        DylanDogNumero(52, "Il Marchio Rosso"),
        DylanDogNumero(53, "La Regina delle Tenebre"),
        DylanDogNumero(54, "Delirium"),
        DylanDogNumero(55, "La mummia"),
        DylanDogNumero(56, "Ombre"),
        DylanDogNumero(57, "Ritorno al Crepuscolo"),
        DylanDogNumero(58, "La clessidra di pietra"),
        DylanDogNumero(59, "Gente che scompare"),
        DylanDogNumero(60, "Frankenstein!"),
        DylanDogNumero(61, "Terrore dall'infinito"),
        DylanDogNumero(62, "I vampiri"),
        DylanDogNumero(63, "Maelstrom!"),
        DylanDogNumero(64, "I segreti di Ramblyn"),
        DylanDogNumero(65, "La belva delle caverne"),
        DylanDogNumero(66, "Partita con la morte"),
        DylanDogNumero(67, "L'uomo che visse due volte"),
        DylanDogNumero(68, "Lo spettro nel buio"),
        DylanDogNumero(69, "Caccia alle streghe"),
        DylanDogNumero(70, "Il bosco degli assassini"),
        DylanDogNumero(71, "I delitti della Mantide"),
        DylanDogNumero(72, "L'ultimo plenilunio"),
        DylanDogNumero(73, "Armageddon!"),
        DylanDogNumero(74, "Il lungo addio"),
        DylanDogNumero(75, "Il tagliagole"),
        DylanDogNumero(76, "Maledizione nera"),
        DylanDogNumero(77, "L'ultimo uomo sulla Terra"),
        DylanDogNumero(78, "I killer venuti dal buio"),
        DylanDogNumero(79, "La Fata del Male"),
        DylanDogNumero(80, "Il cervello di Killex"),
        DylanDogNumero(81, "Johnny Freak"),
        DylanDogNumero(82, "Lontano dalla luce"),
        DylanDogNumero(83, "Doktor Terror"),
        DylanDogNumero(84, "Zed"),
        DylanDogNumero(85, "Fantasmi"),
        DylanDogNumero(86, "Storia di un povero diavolo"),
        DylanDogNumero(87, "Feste di sangue"),
        DylanDogNumero(88, "Oltre la morte"),
        DylanDogNumero(89, "I cavalieri del tempo"),
        DylanDogNumero(90, "Titanic"),
        DylanDogNumero(91, "Metamorfosi"),
        DylanDogNumero(92, "Il mosaico dell'orrore"),
        DylanDogNumero(93, "Presenze..."),
        DylanDogNumero(94, "La donna che uccide il passato"),
        DylanDogNumero(95, "I giorni dell'incubo"),
        DylanDogNumero(96, "La sfida"),
        DylanDogNumero(97, "Dietro il sipario"),
        DylanDogNumero(98, "Lo sguardo di Satana"),
        DylanDogNumero(99, "Sinfonia mortale"),
        DylanDogNumero(100, "La storia di Dylan Dog"),
        DylanDogNumero(101, "La porta dell'Inferno"),
        DylanDogNumero(102, "Fratelli di un altro tempo"),
        DylanDogNumero(103, "I demoni"),
        DylanDogNumero(104, "Notte senza fine"),
        DylanDogNumero(105, "L'orrenda invasione"),
        DylanDogNumero(106, "La rivolta delle macchine"),
        DylanDogNumero(107, "Il paese delle ombre colorate"),
        DylanDogNumero(108, "Il guardiano della memoria"),
        DylanDogNumero(109, "Il volo dello struzzo"),
        DylanDogNumero(110, "Aracne"),
        DylanDogNumero(111, "La profezia"),
        DylanDogNumero(112, "Incontri ravvicinati"),
        DylanDogNumero(113, "La metà oscura"),
        DylanDogNumero(114, "La prigione di carta"),
        DylanDogNumero(115, "L'antro della belva"),
        DylanDogNumero(116, "La governante"),
        DylanDogNumero(117, "La quinta stagione"),
        DylanDogNumero(118, "Il gioco del destino"),
        DylanDogNumero(119, "L'occhio del gatto"),
        DylanDogNumero(120, "Abyss"),
        DylanDogNumero(121, "Finché morte non vi separi"),
        DylanDogNumero(122, "Il confine"),
        DylanDogNumero(123, "Phoenix"),
        DylanDogNumero(124, "Il Picco della Strega"),
        DylanDogNumero(125, "Tre per zero"),
        DylanDogNumero(126, "La Morte Rossa"),
        DylanDogNumero(127, "Il cuore di Johnny"),
        DylanDogNumero(128, "Il richiamo della foresta"),
        DylanDogNumero(129, "Il ritorno di Killex"),
        DylanDogNumero(130, "Il negromante"),
        DylanDogNumero(131, "Quando cadono le stelle"),
        DylanDogNumero(132, "L'uomo che vende il tempo"),
        DylanDogNumero(133, "Ananga!"),
        DylanDogNumero(134, "L'urlo del giaguaro"),
        DylanDogNumero(135, "Scanner"),
        DylanDogNumero(136, "Lassù qualcuno ci chiama"),
        DylanDogNumero(137, "La città perduta"),
        DylanDogNumero(138, "Cattivi pensieri"),
        DylanDogNumero(139, "Hook l'implacabile"),
        DylanDogNumero(140, "Verso un mondo lontano"),
        DylanDogNumero(141, "L'Angelo Sterminatore"),
        DylanDogNumero(142, "Anima nera"),
        DylanDogNumero(143, "Apocalisse"),
        DylanDogNumero(144, "Belli da morire"),
        DylanDogNumero(145, "Il cane infernale"),
        DylanDogNumero(146, "Ghost Hotel"),
        DylanDogNumero(147, "Polvere di stelle"),
        DylanDogNumero(148, "Abissi di follia"),
        DylanDogNumero(149, "L'alieno"),
        DylanDogNumero(150, "Il bacio della vipera"),
        DylanDogNumero(151, "Il lago nel cielo"),
        DylanDogNumero(152, "Morte a domicilio"),
        DylanDogNumero(153, "La strada verso il nulla"),
        DylanDogNumero(154, "Il battito del tempo"),
        DylanDogNumero(155, "La nuova stirpe"),
        DylanDogNumero(156, "Il gigante"),
        DylanDogNumero(157, "Il sonno della ragione"),
        DylanDogNumero(158, "Nato per uccidere"),
        DylanDogNumero(159, "Percezioni extrasensoriali"),
        DylanDogNumero(160, "Il Druido"),
        DylanDogNumero(161, "Il sorriso dell'Oscura Signora"),
        DylanDogNumero(162, "Il dio prigioniero"),
        DylanDogNumero(163, "Il mondo perfetto"),
        DylanDogNumero(164, "La Donna Urlante"),
        DylanDogNumero(165, "L'isola dei cani"),
        DylanDogNumero(166, "Sopravvivere all'Eden"),
        DylanDogNumero(167, "Medusa"),
        DylanDogNumero(168, "Il fiume dell'oblio"),
        DylanDogNumero(169, "Lo specchio dell'anima"),
        DylanDogNumero(170, "La Piccola Morte"),
        DylanDogNumero(171, "Possessione diabolica"),
        DylanDogNumero(172, "Memorie dal sottosuolo"),
        DylanDogNumero(173, "Per un pugno di sterline"),
        DylanDogNumero(174, "Un colpo di sfortuna"),
        DylanDogNumero(175, "Il seme della follia"),
        DylanDogNumero(176, "Il \"progetto\""),
        DylanDogNumero(177, "Il discepolo"),
        DylanDogNumero(178, "Lettere dall'Inferno"),
        DylanDogNumero(179, "La terza faccia della medaglia"),
        DylanDogNumero(180, "Notti di caccia"),
        DylanDogNumero(181, "Il marchio del vampiro"),
        DylanDogNumero(182, "Safarà"),
        DylanDogNumero(183, "Requiem per un mostro"),
        DylanDogNumero(184, "I misteri di Venezia"),
        DylanDogNumero(185, "Phobia"),
        DylanDogNumero(186, "L'Uomo Nero"),
        DylanDogNumero(187, "Amori perduti"),
        DylanDogNumero(188, "Il Labirinto di Bangor"),
        DylanDogNumero(189, "Il prezzo della morte"),
        DylanDogNumero(190, "Il segreto di Mordecai"),
        DylanDogNumero(191, "Sciarada"),
        DylanDogNumero(192, "Macchie solari"),
        DylanDogNumero(193, "L'eterna illusione"),
        DylanDogNumero(194, "La strega di Brentford"),
        DylanDogNumero(195, "Uno strano cliente"),
        DylanDogNumero(196, "Chi ha ucciso Babbo Natale?"),
        DylanDogNumero(197, "I quattro elementi"),
        DylanDogNumero(198, "La legge della giungla"),
        DylanDogNumero(199, "Homo homini lupus"),
        DylanDogNumero(200, "Il numero duecento"),
        DylanDogNumero(201, "Daisy & Queen"),
        DylanDogNumero(202, "Il settimo girone"),
        DylanDogNumero(203, "La famiglia Milford"),
        DylanDogNumero(204, "Resurrezione"),
        DylanDogNumero(205, "Il compagno di scuola"),
        DylanDogNumero(206, "Nebbia"),
        DylanDogNumero(207, "Il Tempio della Seconda Vita"),
        DylanDogNumero(208, "Un mondo sconosciuto"),
        DylanDogNumero(209, "La Bestia"),
        DylanDogNumero(210, "Il Pifferaio Magico"),
        DylanDogNumero(211, "La casa dei fantasmi"),
        DylanDogNumero(212, "Necropolis"),
        DylanDogNumero(213, "L'uccisore di streghe"),
        DylanDogNumero(214, "Manila"),
        DylanDogNumero(215, "Il pozzo degli inganni"),
        DylanDogNumero(216, "Il grimorio maledetto"),
        DylanDogNumero(217, "Il grande sonno"),
        DylanDogNumero(218, "L'incubo dipinto"),
        DylanDogNumero(219, "La decima vittima"),
        DylanDogNumero(220, "Concorrenza sleale"),
        DylanDogNumero(221, "Il tocco del diavolo"),
        DylanDogNumero(222, "La saggezza dei morti"),
        DylanDogNumero(223, "Le due vite di Dream"),
        DylanDogNumero(224, "Sul filo dei ricordi"),
        DylanDogNumero(225, "Insonnia"),
        DylanDogNumero(226, "24 ore per non morire"),
        DylanDogNumero(227, "Istinto omicida"),
        DylanDogNumero(228, "Oltre quella porta"),
        DylanDogNumero(229, "Il cielo può attendere"),
        DylanDogNumero(230, "L'inquilino misterioso"),
        DylanDogNumero(231, "Nightmare Tour"),
        DylanDogNumero(232, "Un fantasma a Scotland Yard"),
        DylanDogNumero(233, "L'ospite sgradito"),
        DylanDogNumero(234, "L'ultimo arcano"),
        DylanDogNumero(235, "Sonata macabra"),
        DylanDogNumero(236, "Vittime designate"),
        DylanDogNumero(237, "All'ombra del vulcano"),
        DylanDogNumero(238, "Gli eredi del Crepuscolo"),
        DylanDogNumero(239, "Il gran bastardo"),
        DylanDogNumero(240, "Ucronìa"),
        DylanDogNumero(241, "Xabaras!"),
        DylanDogNumero(242, "In nome del padre"),
        DylanDogNumero(243, "L'assassino è tra noi"),
        DylanDogNumero(244, "Marty"),
        DylanDogNumero(245, "Il cimitero dei freaks"),
        DylanDogNumero(246, "La locanda alla fine del mondo"),
        DylanDogNumero(247, "Tutti gli amori di Sally"),
        DylanDogNumero(248, "Anima d'acciaio"),
        DylanDogNumero(249, "I ricordi sepolti"),
        DylanDogNumero(250, "Ascensore per l'Inferno"),
        DylanDogNumero(251, "Il guardiano del faro"),
        DylanDogNumero(252, "Poltergeist!"),
        DylanDogNumero(253, "I mostri di Sullivan"),
        DylanDogNumero(254, "Vite in gioco"),
        DylanDogNumero(255, "La stanza numero 63"),
        DylanDogNumero(256, "Il feroce Takurr"),
        DylanDogNumero(257, "Il custode"),
        DylanDogNumero(258, "La furia dell'Upyr"),
        DylanDogNumero(259, "Da una lontana galassia"),
        DylanDogNumero(260, "La condanna di Casper"),
        DylanDogNumero(261, "Saluti da Moonlight"),
        DylanDogNumero(262, "L'incendiario"),
        DylanDogNumero(263, "La collina dei conigli"),
        DylanDogNumero(264, "Liam il bugiardo"),
        DylanDogNumero(265, "Reincarnazioni"),
        DylanDogNumero(266, "Gli artigli del Drago"),
        DylanDogNumero(267, "Cose dell'altro mondo"),
        DylanDogNumero(268, "Il modulo A38"),
        DylanDogNumero(269, "I professionisti"),
        DylanDogNumero(270, "Il re delle mosche"),
        DylanDogNumero(271, "Il piccolo diavolo"),
        DylanDogNumero(272, "La strage dei Graham"),
        DylanDogNumero(273, "Seppelliti vivi!"),
        DylanDogNumero(274, "Fuga dal passato"),
        DylanDogNumero(275, "Il tredicesimo uomo"),
        DylanDogNumero(276, "Uno sconosciuto sulla strada"),
        DylanDogNumero(277, "Il giorno del licantropo"),
        DylanDogNumero(278, "Discesa nell'abisso"),
        DylanDogNumero(279, "Il giardino delle illusioni"),
        DylanDogNumero(280, "Mater Morbi"),
        DylanDogNumero(281, "Il cammino della vita"),
        DylanDogNumero(282, "Relazioni pericolose"),
        DylanDogNumero(283, "Il persecutore"),
        DylanDogNumero(284, "Nel segno del dolore"),
        DylanDogNumero(285, "Il ladro di cervelli"),
        DylanDogNumero(286, "Programma di rieducazione"),
        DylanDogNumero(287, "I nuovi barbari"),
        DylanDogNumero(288, "Lavori forzati"),
        DylanDogNumero(289, "La via degli enigmi"),
        DylanDogNumero(290, "L'erede oscuro"),
        DylanDogNumero(291, "Senza trucco né inganno"),
        DylanDogNumero(292, "Anime prigioniere"),
        DylanDogNumero(293, "Gli ultimi immortali"),
        DylanDogNumero(294, "Piovono rane"),
        DylanDogNumero(295, "Tra moglie e marito..."),
        DylanDogNumero(296, "La seconda occasione"),
        DylanDogNumero(297, "Il sortilegio"),
        DylanDogNumero(298, "Nella testa del killer"),
        DylanDogNumero(299, "Un'affezionata clientela"),
        DylanDogNumero(300, "Ritratto di famiglia"),
        DylanDogNumero(301, "L'imbalsamatore"),
        DylanDogNumero(302, "Il delitto perfetto"),
        DylanDogNumero(303, "Il divoratore di ossa"),
        DylanDogNumero(304, "Terrore ad alta quota"),
        DylanDogNumero(305, "Il museo del crimine"),
        DylanDogNumero(306, "Il baule delle meraviglie"),
        DylanDogNumero(307, "L'assassino della porta accanto"),
        DylanDogNumero(308, "La Dea Madre"),
        DylanDogNumero(309, "L'autopsia"),
        DylanDogNumero(310, "Io, il mostro"),
        DylanDogNumero(311, "Il giudizio del corvo"),
        DylanDogNumero(312, "Epidemia aliena"),
        DylanDogNumero(313, "Il crollo"),
        DylanDogNumero(314, "I segni della fine"),
        DylanDogNumero(315, "La legione degli scheletri"),
        DylanDogNumero(316, "Blacky"),
        DylanDogNumero(317, "L'impostore"),
        DylanDogNumero(318, "Leggende urbane"),
        DylanDogNumero(319, "I ritornanti"),
        DylanDogNumero(320, "La fuggitiva"),
        DylanDogNumero(321, "Giovani vampiri"),
        DylanDogNumero(322, "Il pianto della Banshee"),
        DylanDogNumero(323, "L'occhio di Balor"),
        DylanDogNumero(324, "L'odio non muore mai"),
        DylanDogNumero(325, "Una nuova vita"),
        DylanDogNumero(326, "Sulla pelle"),
        DylanDogNumero(327, "I sonnambuli"),
        DylanDogNumero(328, "Trash Island"),
        DylanDogNumero(329, "...e lascia un bel cadavere"),
        DylanDogNumero(330, "La magnifica creatura"),
        DylanDogNumero(331, "La morte non basta"),
        DylanDogNumero(332, "Destinato alla terra"),
        DylanDogNumero(333, "I raminghi dell'autunno"),
        DylanDogNumero(334, "La paga dell'inferno"),
        DylanDogNumero(335, "Il calvario"),
        DylanDogNumero(336, "Brucia, strega... Brucia!"),
        DylanDogNumero(337, "Spazio profondo"),
        DylanDogNumero(338, "Mai più, ispettore Bloch"),
        DylanDogNumero(339, "Anarchia nel Regno Unito"),
        DylanDogNumero(340, "Benvenuti a Wickedford"),
        DylanDogNumero(341, "Al servizio del caos"),
        DylanDogNumero(342, "Il cuore degli uomini"),
        DylanDogNumero(343, "Nel fumo della battaglia"),
        DylanDogNumero(344, "Il sapore dell'acqua"),
        DylanDogNumero(345, "Gli spiriti custodi"),
        DylanDogNumero(346, "...e cenere tornerai"),
        DylanDogNumero(347, "Gli abbandonati"),
        DylanDogNumero(348, "La mano sbagliata"),
        DylanDogNumero(349, "La morta non dimentica"),
        DylanDogNumero(350, "Lacrime di pietra"),
        DylanDogNumero(351, "In fondo al male"),
        DylanDogNumero(352, "La calligrafia del dolore"),
        DylanDogNumero(353, "Il generale inquisitore"),
        DylanDogNumero(354, "Miseria e crudeltà"),
        DylanDogNumero(355, "L'uomo dei tuoi sogni"),
        DylanDogNumero(356, "La macchina umana"),
        DylanDogNumero(357, "Vietato ai minori"),
        DylanDogNumero(358, "Il prezzo della carne"),
        DylanDogNumero(359, "Sul fondo"),
        DylanDogNumero(360, "Remington House"),
        DylanDogNumero(361, "Mater dolorosa"),
        DylanDogNumero(362, "Dopo un lungo silenzio"),
        DylanDogNumero(363, "Cose perdute"),
        DylanDogNumero(364, "Gli anni selvaggi"),
        DylanDogNumero(365, "Cronodramma"),
        DylanDogNumero(366, "Il giorno della famiglia"),
        DylanDogNumero(367, "La ninna nanna dell'ultima notte"),
        DylanDogNumero(368, "Il passo dell'angelo"),
        DylanDogNumero(369, "Graphic Horror Novel"),
        DylanDogNumero(370, "Il terrore"),
        DylanDogNumero(371, "Arriva il Dampyr"),
        DylanDogNumero(372, "Il bianco e il nero"),
        DylanDogNumero(373, "La fiamma"),
        DylanDogNumero(374, "La fine dell'oscurità"),
        DylanDogNumero(375, "Nel mistero"),
        DylanDogNumero(376, "Graphic Horror Novel: il sequel!"),
        DylanDogNumero(377, "Non umano"),
        DylanDogNumero(378, "Dormire, forse sognare"),
        DylanDogNumero(379, "Il tango delle anime perse"),
        DylanDogNumero(380, "Nessuno è innocente"),
        DylanDogNumero(381, "Tripofobia"),
        DylanDogNumero(382, "Il macellaio e la rosa"),
        DylanDogNumero(383, "Profondo nero"),
        DylanDogNumero(384, "La macchina che non voleva morire"),
        DylanDogNumero(385, "Perderai la testa"),
        DylanDogNumero(386, "Hyppolita"),
        DylanDogNumero(387, "Che regni il Caos!"),
        DylanDogNumero(388, "Esercizio numero 6"),
        DylanDogNumero(389, "La sopravvissuta"),
        DylanDogNumero(390, "La caduta degli dei"),
        DylanDogNumero(391, "Il sangue della terra"),
        DylanDogNumero(392, "Il primordio"),
        DylanDogNumero(393, "Casca il mondo"),
        DylanDogNumero(394, "Eterne Stagioni"),
        DylanDogNumero(395, "Del tempo e di altre illusioni"),
        DylanDogNumero(396, "Il suo nome era guerra"),
        DylanDogNumero(397, "Morbo M"),
        DylanDogNumero(398, "Chi muore si rivede"),
        DylanDogNumero(399, "Oggi sposi"),
        DylanDogNumero(400, "E ora, l'Apocalisse!"),
        DylanDogNumero(401, "L'alba Nera"),
        DylanDogNumero(402, "Il Tramonto Rosso"),
        DylanDogNumero(403, "La lama, la Luna e l'orco"),
        DylanDogNumero(404, "Anna per sempre"),
        DylanDogNumero(405, "L'uccisore"),
        DylanDogNumero(406, "L'ultima risata"),
        DylanDogNumero(407, "L'entità"),
        DylanDogNumero(408, "Scrutando nell'abisso"),
        DylanDogNumero(409, "Ritorno al buio"),
        DylanDogNumero(410, "La notte eterna"),
        DylanDogNumero(411, "Il terzo giorno"),
        DylanDogNumero(412, "Una pessima annata"),
        DylanDogNumero(413, "I padroni del nulla"),
        DylanDogNumero(414, "Giochi innocenti"),
        DylanDogNumero(415, "Vendetta in maschera"),
        DylanDogNumero(416, "Il detenuto"),
        DylanDogNumero(417, "L'ora del giudizio"),
        DylanDogNumero(418, "Sally"),
        DylanDogNumero(419, "Albachiara"),
        DylanDogNumero(420, "Jenny"),
        DylanDogNumero(421, "La variabile"),
        DylanDogNumero(422, "Il momento blu"),
        DylanDogNumero(423, "Nella stanza del guerriero"),
        DylanDogNumero(424, "Candiweb"),
        DylanDogNumero(425, "I predatori"),
        DylanDogNumero(426, "La morte in palio"),
        DylanDogNumero(427, "La vita e il suo contrario"),
        DylanDogNumero(428, "Dove i sogni vanno a morire"),
        DylanDogNumero(429, "La bestia della brughiera"),
        DylanDogNumero(430, "Abissi boreali"),
        DylanDogNumero(431, "Nulla è per sempre"),
        DylanDogNumero(432, "Io ti proteggerò"),
        DylanDogNumero(433, "Cavalcando il fulmine"),
        DylanDogNumero(434, "Gli Infernauti"),
        DylanDogNumero(435, "Due minuti a mezzanotte"),
        DylanDogNumero(436, "Non con fragore..."),
        DylanDogNumero(437, "...Ma con un lamento"),
        DylanDogNumero(438, "La città senza nome"),
        DylanDogNumero(439, "L'invasione silenziosa"),
        DylanDogNumero(440, "E poi non rimase nessuno"),
        DylanDogNumero(441, "La congiura dei colpevoli"),
        DylanDogNumero(442, "Frammenti"),
        DylanDogNumero(443, "Gli Indifferenti"),
        DylanDogNumero(444, "Morte in sedici noni"),
        DylanDogNumero(445, "Xenon!"),
        DylanDogNumero(446, "L'altro lato dello specchio"),
        DylanDogNumero(447, "Hazel la morta"),
        DylanDogNumero(448, "Anatomia dell'anima"),
        DylanDogNumero(449, "La misura del mondo"),
        DylanDogNumero(450, "Shock"),
        DylanDogNumero(451, "Terra funesta"),
        DylanDogNumero(452, "Un tranquillo venerdì di paura"),
        DylanDogNumero(453, "Pioggia di sangue"),
        DylanDogNumero(454, "Stangata agli inferni"),
        DylanDogNumero(455, "Fuga da Golconda"),
        DylanDogNumero(456, "Colui che divora le ombre"),
        DylanDogNumero(457, "La Sottile linea Nera"),
        DylanDogNumero(458, "Sette Vite"),
        DylanDogNumero(459, "Hikikomori"),
        DylanDogNumero(460, "32 Dicembre")
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