package com.pm.cafuservices

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pm.cafuservices.components.analytics.Analytics
import com.pm.cafuservices.sample_event.ConfirmPhoneNumberLogEvent
import com.pm.cafuservices.jetpack.ui.theme.CafuJetpackComposeTheme

class MainActivity : ComponentActivity() {

    val app: BaseApplication
        get() {
            return application as BaseApplication
        }

    val analytics: Analytics
        get() {
            return app.analytics
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CafuJetpackComposeTheme {
                ScaffoldSample()
            }
        }

        analytics.track(
            ConfirmPhoneNumberLogEvent(
                0, "523516006", "+971"
            )
        )
    }
}

@Composable
fun ScaffoldSample() {
    val scaffoldState = rememberScaffoldState(
        rememberDrawerState(initialValue = DrawerValue.Closed)
    )

    Scaffold(
        scaffoldState = scaffoldState,

        drawerContent = {
            Text(text = "Drawer menu 1")
        },
        content = {
            ContentCustomerCompose()
        },
        bottomBar = {
            BottomAppBarCompose()
        }
    )
}

@Composable
fun ContentCustomerCompose() {
    val pinter = painterResource(id = R.drawable.logo)
    Column(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.height(32.dp))
        Image(
            painter = pinter,
            contentDescription = "logo",
            modifier = Modifier
                .padding(32.dp)
                .align(CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(32.dp))
        CarModelRow()
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "Order again",
            style = MaterialTheme.typography.h1,
            modifier = Modifier.padding(start = 16.dp)
        )
        CarModelSingleCard(
            drawable = R.drawable.img,
            makeModel = "Tesla Model S",
            plateDetail = "AB-12345 - Quebec",
            lastChargeDetail = "Last recharge: 6 days ago",
            modifier = Modifier.padding(8.dp)
        )
    }

}


@Composable
fun CarModel(
    @DrawableRes drawable: Int,
    makeModel: String,
    plateDetail: String,
    lastChargeDetail: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.width(240.dp)
    ) {

        Image(
            painter = painterResource(drawable),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
        Text(
            text = makeModel,
            style = MaterialTheme.typography.h2,
            modifier = Modifier
                .paddingFromBaseline(
                    top = 32.dp, bottom = 8.dp
                )

        )
        Text(
            text = plateDetail,
            style = MaterialTheme.typography.h4,
            modifier = Modifier
                .paddingFromBaseline(
                    top = 2.dp, bottom = 2.dp
                )

        )
        Text(
            text = plateDetail,
            style = MaterialTheme.typography.h5,
            modifier = Modifier
                .paddingFromBaseline(
                    top = 2.dp, bottom = 2.dp
                )

        )
    }

}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun carModelPreview() {
    CafuJetpackComposeTheme() {
        CarModel(
            drawable = R.drawable.img,
            makeModel = "Tesla Model S",
            plateDetail = "AB-12345 - Quebec",
            lastChargeDetail = "Last recharge: 6 days ago",
            modifier = Modifier.padding(8.dp)
        )
    }
}

private data class carModel(
    @DrawableRes val drawable: Int,
    val makeModel: String,
    val plateDetail: String,
    val lastChargeDetail: String
)

private val carModelData = listOf(
    carModel(
        drawable = R.drawable.img,
        makeModel = "Tesla Model S",
        plateDetail = "AB-12345 - Quebec",
        lastChargeDetail = "Last recharge: 16 days ago"
    ),
    carModel(
        drawable = R.drawable.img,
        makeModel = "Tesla Model E",
        plateDetail = "CD-1 - Quebec",
        lastChargeDetail = "Last recharge: 18 days ago"
    ),
    carModel(
        drawable = R.drawable.img,
        makeModel = "Tesla Model X",
        plateDetail = "EF-786 - Quebec",
        lastChargeDetail = "Last recharge: 2 days ago"
    ),


    )

@Composable
fun CarModelRow(
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(32.dp),
        contentPadding = PaddingValues(horizontal = 32.dp),
        modifier = modifier
    ) {
        items(carModelData) { item ->
            CarModel(
                drawable = item.drawable,
                makeModel = item.makeModel,
                plateDetail = item.plateDetail,
                lastChargeDetail = item.lastChargeDetail
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun CarModelRowPreview() {
    CafuJetpackComposeTheme() {
        CarModelRow()
    }
}

@Composable
fun CarModelSingleCard(
    @DrawableRes drawable: Int,
    makeModel: String,
    plateDetail: String,
    lastChargeDetail: String,
    modifier: Modifier = Modifier
) {
    Card(

        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 16.dp)
            .clickable { },
        elevation = 10.dp
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {

            Image(
                painter = painterResource(drawable),
                contentDescription = null,
                modifier = Modifier.width(124.dp),
                contentScale = ContentScale.FillWidth
            )

            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
            ) {


                Text(
                    text = makeModel,
                    style = MaterialTheme.typography.h2,
                    modifier = Modifier
                        .paddingFromBaseline(
                            bottom = 2.dp
                        )

                )
                Text(
                    text = plateDetail,
                    style = MaterialTheme.typography.h4,
                    modifier = Modifier
                        .paddingFromBaseline(
                            top = 2.dp, bottom = 2.dp
                        )

                )
                Text(
                    text = lastChargeDetail,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier
                        .paddingFromBaseline(
                            top = 2.dp, bottom = 2.dp
                        )

                )
            }
        }
    }

}


@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun CarModelRowSingleCardPreview() {
    CafuJetpackComposeTheme() {
        CarModelSingleCard(
            drawable = R.drawable.img,
            makeModel = "Tesla Model S",
            plateDetail = "AB-12345 - Quebec",
            lastChargeDetail = "Last recharge: 6 days ago",
            modifier = Modifier.padding(8.dp)
        )
    }
}


@Composable
fun BottomAppBarCompose() {
    val selectedIndex = remember { mutableStateOf(1) }
    BottomAppBar(
        backgroundColor = MaterialTheme.colors.primary,
        elevation = 10.dp
    ) {

        BottomNavigationItem(
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_home),
                    contentDescription = "Home"
                )
            },
            label = { Text(text = "Home", fontSize = 10.sp) },
            selected = (selectedIndex.value == 0),
            onClick = {
                selectedIndex.value = 0
            }
        )

        BottomNavigationItem(
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_wallet),
                    contentDescription = "Account"
                )
            },
            label = { Text(text = "Wallet", fontSize = 10.sp) },
            selected = (selectedIndex.value == 1),
            onClick = {
                selectedIndex.value = 1
            }
        )

        BottomNavigationItem(
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_recharge),
                    contentDescription = "Account"
                )
            },
            label = { Text(text = "Recharge", fontSize = 10.sp) },
            selected = (selectedIndex.value == 2),
            onClick = {
                selectedIndex.value = 2
            }
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_help),
                    contentDescription = "Account"
                )
            },
            label = { Text(text = "Help", fontSize = 10.sp) },
            selected = (selectedIndex.value == 3),
            onClick = {
                selectedIndex.value = 3
            }
        )

        BottomNavigationItem(
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_profile),
                    contentDescription = "Favorite"
                )
            },
            label = { Text(text = "Profile") },
            selected = (selectedIndex.value == 4),
            onClick = {
                selectedIndex.value = 4
            }
        )

    }
}


@Preview(showBackground = true)
@Composable
fun ScaffoldSamplePreview() {
    CafuJetpackComposeTheme {
        ScaffoldSample()
    }
}