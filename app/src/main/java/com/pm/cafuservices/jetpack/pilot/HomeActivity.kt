package com.pm.cafuservices.jetpack.pilot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.DrawerValue
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.pm.cafuservices.BottomAppBarCompose
import com.pm.cafuservices.jetpack.pilot.component.ContentHome
import com.pm.cafuservices.jetpack.pilot.component.DrawerContent
import com.pm.cafuservices.jetpack.pilot.component.TopBar
import com.pm.cafuservices.jetpack.ui.theme.CafuJetpackComposeTheme

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CafuJetpackComposeTheme {
                PilotDashboardSample()
            }
        }
    }
}


@Composable
fun PilotDashboardSample() {
    val scaffoldState = rememberScaffoldState(
        rememberDrawerState(initialValue = DrawerValue.Closed)
    )

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBar()
        },
        drawerContent = {
            DrawerContent()
        },
        content = {
            ContentHome()
        },
        bottomBar = {
            BottomAppBarCompose()
        }
    )
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    CafuJetpackComposeTheme {
        TopBar()
    }
}

@Preview(showBackground = true)
@Composable
fun DrawerContentPreview() {
    CafuJetpackComposeTheme {
        DrawerContent()
    }
}

@Preview(showBackground = true)
@Composable
fun ContentHomePreview() {
    CafuJetpackComposeTheme {
        ContentHome()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CafuJetpackComposeTheme {
        PilotDashboardSample()
    }
}