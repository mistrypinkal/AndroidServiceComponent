package com.pm.cafuservices.jetpack.pilot.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pm.cafuservices.R
import com.pm.cafuservices.jetpack.ui.theme.CafuJetpackComposeTheme


@Composable
fun ContentHome() {
    val pinter = painterResource(id = R.drawable.logo)
    Column(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.height(32.dp))
        Image(
            painter = pinter,
            contentDescription = "logo",
            modifier = Modifier
                .padding(32.dp)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun orderItem(
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

@Preview(showBackground = true)
@Composable
fun ContentHomePreview() {
    CafuJetpackComposeTheme {
        ContentHome()
    }
}
