package com.example.comicapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
// Importa qui i tuoi font personalizzati se ne aggiungi
// Esempio: import androidx.compose.ui.text.font.Font
// Esempio: import com.example.comicapp.R

// val DylanDogFontFamily = FontFamily(
//    Font(R.font.nome_tuo_font_regular),
//    Font(R.font.nome_tuo_font_bold, FontWeight.Bold)
// )

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Serif, // Esempio con un font Serif generico
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily.Serif, // Esempio
        fontWeight = FontWeight.Bold, // Titoli in grassetto
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp,
        color = DylanRed // Titoli in rosso, per esempio
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Monospace, // Esempio per dettagli, come il numero dell'albo
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    // Puoi definire altri stili (displayLarge, headlineSmall, etc.)
    // per adattarli allo stile Dylan Dog.
)
