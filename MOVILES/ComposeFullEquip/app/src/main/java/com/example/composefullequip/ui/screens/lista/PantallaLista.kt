package com.example.composefullequip.ui.screens.lista


import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.composefullequip.domain.modelo.Persona
import java.util.UUID



@Composable
fun PantallaLista(
    viewModel: PantallaListaViewModel = hiltViewModel(),
    onViewDetalle: (UUID) -> Unit,
    bottomNavigationBar : @Composable () -> Unit = {}


    ) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.handleEvent(PantallaListaEvent.GetPersonas)
    }

    PantallaListaInterna(
        state = state.value,
        onViewDetalle = onViewDetalle,
        bottomNavigationBar = bottomNavigationBar,
    )





}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PantallaListaInterna(
    state: PantallaListaState,
    onViewDetalle: (UUID) -> Unit,
    bottomNavigationBar : @Composable () -> Unit = {},

) {

    val snackbarHostState = remember { SnackbarHostState() }



    Scaffold (
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = bottomNavigationBar,
        floatingActionButton = {
            Button(onClick = { /*TODO*/ }) {
                Text("+")
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { innerPadding ->
        LaunchedEffect(state.error) {
            state.error?.let {
                snackbarHostState.showSnackbar(
                    message = state.error.toString(),
                    duration = SnackbarDuration.Short,
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color.Gray)
        ) {

            items(items = state.personas, key = { persona -> persona.id }) {
                    persona ->
                PersonaItem(persona = persona,
                    onViewDetalle = onViewDetalle,
                    modifier = Modifier.animateItemPlacement(
                        animationSpec = tween(1000)
                    ))
            }
        }

    }


}


@Composable
fun PersonaItem(persona: Persona,

                onViewDetalle: (UUID) -> Unit,
                modifier: Modifier = Modifier){

    Card(modifier = modifier
        .fillMaxWidth()
        .padding(8.dp)
        .clickable { onViewDetalle(persona.id) } ) {
        Row( modifier = Modifier.padding(8.dp)){
            Text(
                modifier = Modifier.weight(weight = 0.4F),
                text = persona.nombre
            )
            Text(
                modifier = Modifier.weight(0.4F),
                text = persona.apellido)
            Text(
                text = persona.edad.toString(),
                modifier = Modifier.weight(0.2F)
            )
        }
    }

}


@Preview
@Composable
fun previewPersonaItem() {
    PersonaItem(
        persona = Persona("nombre", "apellido", 10),
        onViewDetalle = {},
    )
}
