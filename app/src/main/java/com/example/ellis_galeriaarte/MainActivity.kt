package com.example.ellis_galeriaarte

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    val artes = remember {mutableStateListOf<PiezaArte>()}
    var indiceArteMostrado by remember { mutableIntStateOf(0) }
    var arte by remember { mutableStateOf(artes[indiceArteMostrado]) }

    artes.add(PiezaArte(
        R.drawable.arte1,
        "Abominación con reloj",
        "Ellis",
        "2016"
    ))
    artes.add(PiezaArte(
        R.drawable.arte1,
        "Empatía",
        "Sancho",
        "2018"
    ))
    artes.add(PiezaArte(
        R.drawable.arte1,
        "Venganza de los antiguos",
        "Ellis",
        "2017"
    ))

    //Que la columna sirva para recorrer la lista
    Column(
        modifier = Modifier
            .background(color = colorResource(R.color.yeso))
            .padding(top = 36.dp, start = 12.dp, end = 12.dp, bottom = 12.dp)
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
    ) {
        MostrarArte(arte)
        Spacer(modifier = Modifier.height(12.dp))
        indiceArteMostrado = navegacion(indiceArteMostrado, artes.size)

        //Si no hay arte, mostrar un mensaje en medio
        if (artes.isEmpty()){
            Text(
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth(),
                text = "No hay ninguna pieza de arte en la galeria.",
                textAlign = TextAlign.Center,
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic
            )
        }
    }
}

@Composable
fun MostrarArte(
    arte: PiezaArte
){
    Card(
        modifier = Modifier
            .padding(8.dp)
            .background(color = colorResource(R.color.hormiga))
    ) {
        Image(
            painter = painterResource(arte.imagen),
            contentDescription = arte.titulo
        )
    }
    Spacer(modifier = Modifier.height(20.dp))
    InformacionArte(arte.titulo, arte.artista, arte.anyo)
}

//Texto informativo
@Composable
fun InformacionArte(
    titulo: String,
    artista: String,
    anyo: String
){
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .background(color = colorResource(R.color.pasto))
    ) {
        Text(
            text = titulo,
            fontSize = 28.sp,
            textAlign = TextAlign.Left
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
fun navegacion(
    indice: Int,
    tamanyoLista: Int
): Int{
    var hayMasArte by remember {mutableStateOf(true)}
    var hayMenosArte by remember {mutableStateOf(false)}
    var nuevoIndice = indice

    Row() {
        if (indice == 0){
            Button(
                onClick = {
                    hayMenosArte = false
                }
            ) {
                Text("Anterior")
            }
        } else{
            Button(
                onClick = {
                    nuevoIndice--
                    if (!hayMasArte){
                        hayMasArte = true
                    }
                }
            ) {
                Text("Anterior")
            }
        }

        if (indice == tamanyoLista - 1){
            Button(
                onClick = {
                    hayMasArte = false
                }
            ) {
                Text("Siguiente")
            }
        } else{
            Button(
                onClick = {
                    nuevoIndice++
                    if (!hayMenosArte){
                        hayMenosArte = true
                    }

                }
            ) {
                Text("Siguiente")
            }
        }
    }
    return nuevoIndice
}

//Piezas de arte a las que asginar la imagen y la info
class PiezaArte(
    val imagen: Int,
    val titulo: String,
    val artista: String,
    val anyo: String
){}