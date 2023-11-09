package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.theme.greenColor

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("TRAINING", "onCreate")
        setContent {

            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    servicesUI(context = LocalContext.current)
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i("TRAINING","onStart")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("TRAINING","onRestart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("TRAINING","onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("TRAINING","onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("TRAINING","onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("TRAINING","onDestroy")
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
            text = "Hello $name!",
            modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}

@Composable
fun servicesUI(context: Context) {
    // on below line creating variable
    // for service status and button value.
    val serviceStatus = remember {
        mutableStateOf(false)
    }
    val buttonValue = remember {
        mutableStateOf("Start Service")
    }

    // on below line we are creating a column,
    Column(
        // on below line we are adding a modifier to it,
        modifier = Modifier
            .fillMaxSize()
            // on below line we are adding a padding.
            .padding(all = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        // on the below line we are adding a text for heading.
        Text(
            // on below line we are specifying text
            text = context.getString(R.string.services),
            // on below line we are specifying text color,
            // font size and font weight
            color = greenColor,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            if (serviceStatus.value) {
                // service already running
                // stop the service
                serviceStatus.value = !serviceStatus.value
                buttonValue.value = "Start Service"
                context.stopService(Intent(context, MyService::class.java))

            } else {
                // service not running start service.
                serviceStatus.value = !serviceStatus.value
                buttonValue.value = "Stop Service"

                // starting the service
                context.startService(Intent(context, MyService::class.java))
            }

        }) {
            // on below line creating a text for our button.
            Text(
                // on below line adding a text,
                // padding, color and font size.
                text = buttonValue.value,
                modifier = Modifier.padding(10.dp),
                color = Color.White,
                fontSize = 20.sp
            )
        }
    }
}