package com.example.ellis_galeriaarte

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ellis_galeriaarte.ui.theme.Ellis_GaleriaArteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ellis_GaleriaArteTheme {
                PantallaPrincipalGaleria()
            }
        }
    }
}

@Composable
fun PantallaPrincipalGaleria(){
    val artes = remember {mutableStateListOf(
        PiezaArte(
            R.drawable.arte1,
            "Abominación con reloj",
            "Ellis", //Los artes no son realmente míos, por si a caso xd
            "2016"
        ),
        PiezaArte(
            R.drawable.arte2,
            "Empatía",
            "Sancho",
            "2018"
        ),
        PiezaArte(
            R.drawable.arte3,
            "Venganza de los antiguos",
            "Ellis",
            "2017"
        )
    )}
    var indiceArteMostrado by remember { mutableIntStateOf(0) }

    var arte: PiezaArte

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.yeso))
            //.verticalScroll(rememberScrollState())
            .padding(horizontal = 12.dp, vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (artes.isEmpty()){ //Por si en algún momento decido seguir con la app y poder borrar artes...
            Text(
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth(),
                text = "No hay ninguna pieza de arte en la galeria.",
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic
            )
        } else{
            arte = artes[indiceArteMostrado]
            Spacer(modifier = Modifier.weight(1f))
            MostrarArte(arte)
            Spacer(modifier = Modifier.weight(1f))
            Navegacion(
                indice = indiceArteMostrado,
                tamanyoLista = artes.size,
                onAnterior = {
                    if (indiceArteMostrado > 0) {
                        indiceArteMostrado--
                    }
                },
                onSiguiente = {
                    if (indiceArteMostrado < artes.lastIndex) {
                        indiceArteMostrado++
                    }
                }
            )
        }
    }
}

@Composable
fun MostrarArte(
    arte: PiezaArte
){

    Surface(
        modifier = Modifier
            .padding(8.dp)
            .height(300.dp),
        shadowElevation = 8.dp,
        color = colorResource(R.color.hormiga)
    ) {
        Image(
            modifier = Modifier
                .padding(16.dp),
            painter = painterResource(arte.imagen),
            contentDescription = arte.titulo,
            alignment = Alignment.Center,
            contentScale = ContentScale.Fit
        )
    }
    Spacer(modifier = Modifier.height(20.dp))
    InformacionArte(arte.titulo, arte.artista, arte.anyo)
}

@Composable
fun InformacionArte(
    titulo: String,
    artista: String,
    anyo: String
){
    Column(
        modifier = Modifier
            .background(color = colorResource(R.color.pasto))
            .fillMaxWidth()
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .align(alignment = Alignment.Start),
            text = titulo,
            fontSize = 28.sp,
            textAlign = TextAlign.Start,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier
                .wrapContentWidth()
                .align(alignment = Alignment.Start)
        ) {
            Text(
                text = "$artista ",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Text(
                text = "($anyo)",
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun Navegacion(
    indice: Int,
    tamanyoLista: Int,
    onAnterior: () -> Unit,
    onSiguiente: () -> Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(
            onClick = onAnterior,
            enabled = indice > 0,
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.calabaza),
                contentColor = colorResource(R.color.yeso)
            )
    ) {
        Text("Anterior")
    }
        Button(
            onClick = onSiguiente,
            enabled = indice < tamanyoLista -1,
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.calabaza),
                contentColor = colorResource(R.color.yeso)
            )
        ) {
            Text("Siguiente")
        }
    }
}

class PiezaArte(
    val imagen: Int,
    val titulo: String,
    val artista: String,
    val anyo: String
){}