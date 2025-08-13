package com.example.comicapp

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.comicapp.ui.theme.ComicAppTheme

data class DylanDogNumero(
    val numero: Int,
    val titolo: String,
    val coverUrl: String? = null,
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

// 📚 ARCHIVIO COMPLETO COPERTINE DYLAN DOG
object DylanDogCovertureArchive {

    // 🎯 LINK REALI CONFERMATI
    private val linkReali = mapOf(
        // Sergio Bonelli Shop (Alta qualità)
        1 to "https://shop.sergiobonelli.it/resizer/1000/-1/true/1517583386868.jpg--l_alba_dei_morti_viventi___dylan_dog_01_cover.jpg?1517583387000",

        // Comics.org (Pattern: https://www.comics.org/issue/[ID]/cover/4/)
        // AGGIUNGI QUI GLI ID CHE TROVI NAVIGANDO SU COMICS.ORG
        // Esempio: 2 to "https://www.comics.org/issue/41986/cover/4/",
        // Esempio: 3 to "https://www.comics.org/issue/41987/cover/4/",
    )

    // 🔍 PATTERN COMICS.ORG - Aggiungi qui gli Issue ID che trovi
    private val comicsOrgIssueIds = mapOf(
        1 to 41985,  // Confermato dal search
        34 to 46522, // Confermato dal search
        66 to 51058, // Confermato dal search

        // 📝 AGGIUNGI QUI ALTRI ID CHE TROVI NAVIGANDO SU COMICS.ORG
        // Pattern di incremento: prova numeri consecutivi se non trovi l'ID esatto
        // Esempio: 2 to 41986, 3 to 41987, etc.
    )

    // 🎨 PLACEHOLDER TEMATICI (usati quando non abbiamo link reali)
    private val placeholderThemes = mapOf(
        1 to Pair("8B0000", "👻 ALBA MORTI 👻"),
        2 to Pair("4B0082", "🔪 JACK RIPPER 🔪"),
        3 to Pair("2F4F4F", "🌙 LUNA PIENA 🌙"),
        4 to Pair("800080", "👤 ANNA NEVER 👤"),
        5 to Pair("DC143C", "⚔️ KILLERS ⚔️"),
        10 to Pair("008B8B", "🪞 SPECCHIO 🪞"),
        15 to Pair("FF4500", "📺 CHANNEL 666 📺"),
        25 to Pair("9932CC", "✨ MORGANA ✨"),
        50 to Pair("FFD700", "⏰ EDGE TIME ⏰"),
        60 to Pair("228B22", "⚡ FRANKENSTEIN ⚡"),
        100 to Pair("FF4500", "🎯 STORIA DD 🎯"),
        200 to Pair("DC143C", "🎪 NUMERO 200 🎪"),
        300 to Pair("4B0082", "👨‍👩‍👧‍👦 FAMIGLIA 👨‍👩‍👧‍👦"),
        400 to Pair("8B0000", "💥 APOCALISSE 💥")
    )

    // 🔗 GENERA URL COPERTINA
    fun getCoverUrl(numero: Int): String {
        // 1. Prima priorità: Link reali confermati
        linkReali[numero]?.let { return it }

        // 2. Seconda priorità: Comics.org se abbiamo l'Issue ID
        comicsOrgIssueIds[numero]?.let { issueId ->
            return "https://www.comics.org/issue/$issueId/cover/4/"
        }

        // 3. Fallback: Placeholder tematico o generico
        val theme = placeholderThemes[numero]
        return if (theme != null) {
            val (colore, testo) = theme
            "https://via.placeholder.com/400x600/$colore/FFFFFF?text=DD%20$numero%0A%0A$testo"
        } else {
            getGenericPlaceholder(numero)
        }
    }

    private fun getGenericPlaceholder(numero: Int): String {
        val colori = listOf(
            "8B0000", "4B0082", "228B22", "FF4500", "2F4F4F",
            "DC143C", "191970", "800080", "008B8B", "B22222",
            "556B2F", "8B4513", "483D8B", "2E8B57", "A0522D"
        )
        val colore = colori[numero % colori.size]
        return "https://via.placeholder.com/400x600/$colore/FFFFFF?text=DD%20$numero%0A%0A📖%20FUMETTO%20📖"
    }
}

fun getListaDylanDog(): List<DylanDogNumero> {
    return listOf(
        DylanDogNumero(1, "Dawn of the Dead", DylanDogCovertureArchive.getCoverUrl(1)),
        DylanDogNumero(2, "Jack the Ripper", DylanDogCovertureArchive.getCoverUrl(2)),
        DylanDogNumero(3, "The Nights of the Full Moon", DylanDogCovertureArchive.getCoverUrl(3)),
        DylanDogNumero(4, "The Ghost of Anna Never", DylanDogCovertureArchive.getCoverUrl(4)),
        DylanDogNumero(5, "The Killers", DylanDogCovertureArchive.getCoverUrl(5)),
        DylanDogNumero(6, "The Beauty of the Devil", DylanDogCovertureArchive.getCoverUrl(6)),
        DylanDogNumero(7, "The Twilight Zone", DylanDogCovertureArchive.getCoverUrl(7)),
        DylanDogNumero(8, "The return of the monster", DylanDogCovertureArchive.getCoverUrl(8)),
        DylanDogNumero(9, "Alpha and Omega", DylanDogCovertureArchive.getCoverUrl(9)),
        DylanDogNumero(10, "Through the Looking Glass", DylanDogCovertureArchive.getCoverUrl(10)),
        DylanDogNumero(11, "Diabolo the Great", DylanDogCovertureArchive.getCoverUrl(11)),
        DylanDogNumero(12, "Killer!", DylanDogCovertureArchive.getCoverUrl(12)),
        DylanDogNumero(13, "They Live Among Us", DylanDogCovertureArchive.getCoverUrl(13)),
        DylanDogNumero(14, "Between Life and Death", DylanDogCovertureArchive.getCoverUrl(14)),
        DylanDogNumero(15, "Channel 666", DylanDogCovertureArchive.getCoverUrl(15)),
        DylanDogNumero(16, "The Castle of Fear", DylanDogCovertureArchive.getCoverUrl(16)),
        DylanDogNumero(17, "The Lady in Black", DylanDogCovertureArchive.getCoverUrl(17)),
        DylanDogNumero(18, "Cagliostro!", DylanDogCovertureArchive.getCoverUrl(18)),
        DylanDogNumero(19, "Memories from the invisible", DylanDogCovertureArchive.getCoverUrl(19)),
        DylanDogNumero(20, "From the Deep", DylanDogCovertureArchive.getCoverUrl(20)),
        DylanDogNumero(21, "Cursed Day", DylanDogCovertureArchive.getCoverUrl(21)),
        DylanDogNumero(22, "The Tunnel of Horror", DylanDogCovertureArchive.getCoverUrl(22)),
        DylanDogNumero(23, "The Mysterious Island", DylanDogCovertureArchive.getCoverUrl(23)),
        DylanDogNumero(24, "Pink Rabbits Kill", DylanDogCovertureArchive.getCoverUrl(24)),
        DylanDogNumero(25, "Morgana", DylanDogCovertureArchive.getCoverUrl(25)),
        DylanDogNumero(26, "After Midnight", DylanDogCovertureArchive.getCoverUrl(26)),
        DylanDogNumero(27, "I Saw You Die", DylanDogCovertureArchive.getCoverUrl(27)),
        DylanDogNumero(28, "Razor Blade", DylanDogCovertureArchive.getCoverUrl(28)),
        DylanDogNumero(29, "When the city sleeps", DylanDogCovertureArchive.getCoverUrl(29)),
        DylanDogNumero(30, "The Haunted House", DylanDogCovertureArchive.getCoverUrl(30)),
        DylanDogNumero(31, "Grand Guignol", DylanDogCovertureArchive.getCoverUrl(31)),
        DylanDogNumero(32, "Obsession", DylanDogCovertureArchive.getCoverUrl(32)),
        DylanDogNumero(33, "Jekyll!", DylanDogCovertureArchive.getCoverUrl(33)),
        DylanDogNumero(34, "The Darkness", DylanDogCovertureArchive.getCoverUrl(34)),
        DylanDogNumero(35, "The Cliff of Ghosts", DylanDogCovertureArchive.getCoverUrl(35)),
        DylanDogNumero(36, "A Midsummer Night's Nightmare", DylanDogCovertureArchive.getCoverUrl(36)),
        DylanDogNumero(37, "The Tiger's Dream", DylanDogCovertureArchive.getCoverUrl(37)),
        DylanDogNumero(38, "A voice from nowhere", DylanDogCovertureArchive.getCoverUrl(38)),
        DylanDogNumero(39, "The Lord of Silence", DylanDogCovertureArchive.getCoverUrl(39)),
        DylanDogNumero(40, "It Happened Tomorrow", DylanDogCovertureArchive.getCoverUrl(40)),
        DylanDogNumero(41, "Golconda!", DylanDogCovertureArchive.getCoverUrl(41)),
        DylanDogNumero(42, "The Hyena", DylanDogCovertureArchive.getCoverUrl(42)),
        DylanDogNumero(43, "Nobody's Story", DylanDogCovertureArchive.getCoverUrl(43)),
        DylanDogNumero(44, "Reflections of Death", DylanDogCovertureArchive.getCoverUrl(44)),
        DylanDogNumero(45, "Goblin", DylanDogCovertureArchive.getCoverUrl(45)),
        DylanDogNumero(46, "Hells", DylanDogCovertureArchive.getCoverUrl(46)),
        DylanDogNumero(47, "Written in Blood", DylanDogCovertureArchive.getCoverUrl(47)),
        DylanDogNumero(48, "Horror Paradise", DylanDogCovertureArchive.getCoverUrl(48)),
        DylanDogNumero(49, "The Mystery of the Thames", DylanDogCovertureArchive.getCoverUrl(49)),
        DylanDogNumero(50, "At the Edge of Time", DylanDogCovertureArchive.getCoverUrl(50)),
        DylanDogNumero(51, "Evil", DylanDogCovertureArchive.getCoverUrl(51)),
        DylanDogNumero(52, "The Red Mark", DylanDogCovertureArchive.getCoverUrl(52)),
        DylanDogNumero(53, "The Queen of Darkness", DylanDogCovertureArchive.getCoverUrl(53)),
        DylanDogNumero(54, "Delirium", DylanDogCovertureArchive.getCoverUrl(54)),
        DylanDogNumero(55, "The Mummy", DylanDogCovertureArchive.getCoverUrl(55)),
        DylanDogNumero(56, "Shadows", DylanDogCovertureArchive.getCoverUrl(56)),
        DylanDogNumero(57, "Return to Twilight", DylanDogCovertureArchive.getCoverUrl(57)),
        DylanDogNumero(58, "The Stone Hourglass", DylanDogCovertureArchive.getCoverUrl(58)),
        DylanDogNumero(59, "People who disappear", DylanDogCovertureArchive.getCoverUrl(59)),
        DylanDogNumero(60, "Frankenstein!", DylanDogCovertureArchive.getCoverUrl(60)),
        DylanDogNumero(61, "Terror from the infinite", DylanDogCovertureArchive.getCoverUrl(61)),
        DylanDogNumero(62, "The Vampires", DylanDogCovertureArchive.getCoverUrl(62)),
        DylanDogNumero(63, "Maelstrom!", DylanDogCovertureArchive.getCoverUrl(63)),
        DylanDogNumero(64, "The Secrets of Ramblyn", DylanDogCovertureArchive.getCoverUrl(64)),
        DylanDogNumero(65, "The Cave Beast", DylanDogCovertureArchive.getCoverUrl(65)),
        DylanDogNumero(66, "Match with Death", DylanDogCovertureArchive.getCoverUrl(66)),
        DylanDogNumero(67, "The Man Who Lived Twice", DylanDogCovertureArchive.getCoverUrl(67)),
        DylanDogNumero(68, "The Spectre in the Dark", DylanDogCovertureArchive.getCoverUrl(68)),
        DylanDogNumero(69, "Witch Hunt", DylanDogCovertureArchive.getCoverUrl(69)),
        DylanDogNumero(70, "The Forest of Assassins", DylanDogCovertureArchive.getCoverUrl(70)),
        DylanDogNumero(71, "The Mantis Crimes", DylanDogCovertureArchive.getCoverUrl(71)),
        DylanDogNumero(72, "The Last Full Moon", DylanDogCovertureArchive.getCoverUrl(72)),
        DylanDogNumero(73, "Armageddon!", DylanDogCovertureArchive.getCoverUrl(73)),
        DylanDogNumero(74, "The Long Goodbye", DylanDogCovertureArchive.getCoverUrl(74)),
        DylanDogNumero(75, "The Throat Cutter", DylanDogCovertureArchive.getCoverUrl(75)),
        DylanDogNumero(76, "Black Curse", DylanDogCovertureArchive.getCoverUrl(76)),
        DylanDogNumero(77, "The Last Man on Earth", DylanDogCovertureArchive.getCoverUrl(77)),
        DylanDogNumero(78, "The Killers from the Dark", DylanDogCovertureArchive.getCoverUrl(78)),
        DylanDogNumero(79, "The Evil Fairy", DylanDogCovertureArchive.getCoverUrl(79)),
        DylanDogNumero(80, "Killex's Brain", DylanDogCovertureArchive.getCoverUrl(80)),
        DylanDogNumero(81, "Johnny Freak", DylanDogCovertureArchive.getCoverUrl(81)),
        DylanDogNumero(82, "Far from the light", DylanDogCovertureArchive.getCoverUrl(82)),
        DylanDogNumero(83, "Doktor Terror", DylanDogCovertureArchive.getCoverUrl(83)),
        DylanDogNumero(84, "Zed", DylanDogCovertureArchive.getCoverUrl(84)),
        DylanDogNumero(85, "Ghosts", DylanDogCovertureArchive.getCoverUrl(85)),
        DylanDogNumero(86, "Story of a poor devil", DylanDogCovertureArchive.getCoverUrl(86)),
        DylanDogNumero(87, "Bloody Parties", DylanDogCovertureArchive.getCoverUrl(87)),
        DylanDogNumero(88, "Beyond Death", DylanDogCovertureArchive.getCoverUrl(88)),
        DylanDogNumero(89, "The Knights of Time", DylanDogCovertureArchive.getCoverUrl(89)),
        DylanDogNumero(90, "Titanic", DylanDogCovertureArchive.getCoverUrl(90)),
        DylanDogNumero(91, "Metamorphosis", DylanDogCovertureArchive.getCoverUrl(91)),
        DylanDogNumero(92, "The Mosaic of Horror", DylanDogCovertureArchive.getCoverUrl(92)),
        DylanDogNumero(93, "Presences...", DylanDogCovertureArchive.getCoverUrl(93)),
        DylanDogNumero(94, "The woman who kills the past", DylanDogCovertureArchive.getCoverUrl(94)),
        DylanDogNumero(95, "The Days of Nightmare", DylanDogCovertureArchive.getCoverUrl(95)),
        DylanDogNumero(96, "The Challenge", DylanDogCovertureArchive.getCoverUrl(96)),
        DylanDogNumero(97, "Behind the curtain", DylanDogCovertureArchive.getCoverUrl(97)),
        DylanDogNumero(98, "Satan's Gaze", DylanDogCovertureArchive.getCoverUrl(98)),
        DylanDogNumero(99, "Deadly Symphony", DylanDogCovertureArchive.getCoverUrl(99)),
        DylanDogNumero(100, "The story of Dylan Dog", DylanDogCovertureArchive.getCoverUrl(100)),

        // CONTINUA CON TUTTI GLI ALTRI... (per brevità mostro solo fino a 100)
        // Aggiungi tutti fino al 460 seguendo lo stesso pattern
        DylanDogNumero(200, "The number two hundred", DylanDogCovertureArchive.getCoverUrl(200)),
        DylanDogNumero(300, "Family Portrait", DylanDogCovertureArchive.getCoverUrl(300)),
        DylanDogNumero(400, "And now, the Apocalypse!", DylanDogCovertureArchive.getCoverUrl(400)),
        DylanDogNumero(460, "December 32", DylanDogCovertureArchive.getCoverUrl(460))
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDylanDog() {
    val context = LocalContext.current

    val numeriPosseduti = remember { SalvataggioFumetti.caricaNumeriPosseduti(context) }

    var listaDylanDog by remember {
        mutableStateOf(
            getListaDylanDog().map { dylanDog ->
                dylanDog.copy(posseduto = numeriPosseduti.contains(dylanDog.numero))
            }
        )
    }

    var testoRicerca by remember { mutableStateOf("") }
    var mostraRicerca by remember { mutableStateOf(false) }

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
                    text = "🕵️ Dylan Dog Collection",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "L'Indagatore dell'Incubo • 1986-2025",
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
                    text = "📊 Progresso Collezione",
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
                    Text("✅ Posseduti: $numeriPosseduti/$numeroTotale")
                    Text("📈 $percentualeCompleta%")
                }
                Text("❌ Mancanti: ${numeroTotale - numeriPosseduti}")
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
                Text("✅ Tutti", fontSize = 12.sp)
            }

            OutlinedButton(
                onClick = {
                    listaDylanDog = listaDylanDog.map { it.copy(posseduto = false) }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("❌ Reset", fontSize = 12.sp)
            }
        }

        LazyColumn(
            state = listState,
            verticalArrangement = Arrangement.spacedBy(8.dp)
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
            // Copertina con miglioramenti
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(dylanDog.coverUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = "Copertina Dylan Dog ${dylanDog.numero}",
                modifier = Modifier
                    .size(70.dp, 90.dp)
                    .clip(RoundedCornerShape(6.dp)),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(android.R.drawable.ic_menu_gallery),
                error = painterResource(android.R.drawable.ic_menu_gallery)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "#${dylanDog.numero}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = dylanDog.titolo,
                    fontSize = 15.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text(
                    text = if (dylanDog.posseduto) "✅" else "⭕",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )

                Checkbox(
                    checked = dylanDog.posseduto,
                    onCheckedChange = onCheckedChange
                )
            }
        }
    }
}