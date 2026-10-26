package com.example.ellis_galeriaarte

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

// Imagina, ellis, que estas viendo a los actores o escenas de foundryVTT

@Composable
fun PantallaPrincipalGaleria(){
    val artes = remember {mutableStateListOf<PiezaArte>()}

    //Que la columna sirva para recorrer la lista
    Column(
        modifier = Modifier
            .background(color = colorResource(R.color.yeso))
            .padding(top = 28.dp)
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
    ) {
        for (piezaArte in artes){
            TarjetaArtePequena(piezaArte)
        }

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

//Tarjetitas que son botones, la imagen es recortada y tiene un pequeño texto descriptivo
//De toodo el ancho de la pantalla
@Composable
fun TarjetaArtePequena(piezaArte: PiezaArte){
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
    ) {
        BotonArteSeleccionable(piezaArte.imagen)
        Spacer(Modifier.height(4.dp))
        InformacionArte(piezaArte.informacion)
        Spacer(Modifier.height(4.dp))
    }
}

//La parte de la imagen, al hacer click debería lanzar una tarjeta popup del arte completo con la info
@Composable
fun BotonArteSeleccionable(
    @DrawableRes imagen: Int
){
    var lanzarTarjetaDesplegada by remember { mutableStateOf(false) }

    Button(
        onClick = {lanzarTarjetaDesplegada = true},
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
    ) {
        Image(
            modifier = Modifier,
            painter = painterResource(imagen),
            contentDescription = ""
        )
    }

    if (lanzarTarjetaDesplegada){
        TarjetaArteDesplegable(imagen)
    }
}

//Texto informativo
@Composable
fun InformacionArte(
    informacion: String
){
    Text(
        text = informacion
    )
}

//Tarjeta popup que se lanza
@Composable
fun TarjetaArteDesplegable(
    @DrawableRes imagen: Int
){}

//Piezas de arte a las que asginar la imagen y la info
class PiezaArte(
    val imagen: Int,
    val informacion: String
){}