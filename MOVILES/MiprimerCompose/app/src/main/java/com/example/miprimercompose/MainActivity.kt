package com.example.miprimercompose

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BatteryUnknown
import androidx.compose.material.icons.rounded.Bathtub
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.UiMode
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.fistcompose.domain.Data
import com.example.miprimercompose.ui.theme.MiPrimerComposeTheme
import dagger.Component
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Pantalla()
        }
    }
}

@Composable
fun Pantalla(
    viewModel: MainViewModel = hiltViewModel(),
    )
{
    ContenidoPantalla(viewModel)
}


@Composable
fun ContenidoPantalla(
    viewModel: MainViewModel? = null,

){
    MiPrimerComposeTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Blue),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .width(200.dp)
                    .height(200.dp)
                    .padding(16.dp)
                    .background(MaterialTheme.colorScheme.primary),


                )

            {
                Greeting(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Blue)
                        .padding(16.dp)
                        .background(Color.Yellow),
                    name = "Juan ",
                )
                Spacer(modifier = Modifier.height(8.dp))
               
                Greeting(

                    name = "Jllll ",
                )
                Spacer(modifier = Modifier.height(8.dp))
                var texto by remember { mutableStateOf("") }
                var textoViewModel = viewModel?.text?.collectAsState()

                if (textoViewModel?.value == "sal")
                    CajaTextoMia(texto = textoViewModel.value,
                            onClick =   { it -> viewModel?.changeText(it) })

                TextField(value = textoViewModel?.value ?: "", onValueChange = {
                    Log.i("MIERDECILLA", it)
                   viewModel?.changeText(it)
                })

                val context = LocalContext.current

                AsyncImage(
                    // cargar imagen con ImageRequest
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://placebear.com/200/300")

                        .build(),

                    contentDescription = "Translated description of what the image contains",
                    modifier = Modifier
                        .size(200.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .clickable {
                            Toast
                                .makeText(context, "Hola", Toast.LENGTH_SHORT)
                                .show()
                        },
                    contentScale = ContentScale.Crop,

                    )

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Gray)
                ) {

                    items(listOf<Data>(Data(1,"jj"),Data(2,"2"))) { data ->
                        Card(modifier = Modifier
                            .clickable {

                            }
                            .padding(8.dp)

                        ) {

                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(MaterialTheme.colorScheme.secondary)
                                    .padding(5.dp)
                            ) {
                                Text(text = "${data.id} ${data.nombre}")
                            }
                        }
                    }


                }



            }
        }
    }
}

@Composable
fun CajaTextoMia(
    texto : String,
    onClick : (String) -> Unit
    
)
{
    Row() {
        Icon(
            imageVector = Icons.Rounded.Bathtub,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.secondary,
        )
        Text(
            modifier = Modifier.height(100.dp)
                .clickable {  onClick("kk") },
            text = texto)
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "lolo $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true,
    )
@Composable
fun GreetingPreview() {
    ContenidoPantalla()
}
