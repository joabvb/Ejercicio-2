package com.mexiti.costogasolina

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppUI()
        }
    }
}

@Composable
fun AppUI() {
    var precioPorLitro by remember { mutableStateOf("") }
    var cantidadLitros by remember { mutableStateOf("") }
    var propina by remember { mutableStateOf("") }
    var incluirPropina by remember { mutableStateOf(false) }

    val total = (precioPorLitro.toDoubleOrNull() ?: 0.0) * (cantidadLitros.toDoubleOrNull() ?: 0.0) +
            if (incluirPropina) (propina.toDoubleOrNull() ?: 0.0) else 0.0

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(painter = painterResource(id = R.drawable.gasolinera2), contentDescription = "")
            Text(
                text = "Calcular monto de gasolina",
                fontSize = 24.sp,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(painter = painterResource(id = R.drawable.precio), contentDescription = "Precio por litro", modifier = Modifier.size(40.dp))
                Spacer(modifier = Modifier.width(8.dp))
                TextField(
                    value = precioPorLitro,
                    onValueChange = { precioPorLitro = it },
                    label = { Text("Precio por litro") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(painter = painterResource(id = R.drawable.gasolina), contentDescription = "Cantidad de litros", modifier = Modifier.size(40.dp))
                Spacer(modifier = Modifier.width(8.dp))
                TextField(
                    value = cantidadLitros,
                    onValueChange = { cantidadLitros = it },
                    label = { Text("Cantidad de litros") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Switch(
                    checked = incluirPropina,
                    onCheckedChange = { incluirPropina = it }
                )
                Text("Incluir propina", modifier = Modifier.padding(start = 8.dp))
            }
            if (incluirPropina) {
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(painter = painterResource(id = R.drawable.propina), contentDescription = "Propina", modifier = Modifier.size(40.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    TextField(
                        value = propina,
                        onValueChange = { propina = it },
                        label = { Text("Propina") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done)
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Total: ${NumberFormat.getCurrencyInstance().format(total)}",
                fontSize = 20.sp,
                color = Color.Black,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}
