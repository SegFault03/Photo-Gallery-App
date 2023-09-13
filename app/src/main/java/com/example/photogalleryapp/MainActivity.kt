package com.example.photogalleryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.photogalleryapp.data.ArtInfo
import com.example.photogalleryapp.ui.theme.PhotoGalleryAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PhotoGalleryAppTheme {
                RootLayout(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun RootLayout(modifier: Modifier = Modifier)
{
    var imageIndex by remember {
        mutableStateOf(0)
    }

    val artInfoArray: Array<ArtInfo> = arrayOf(
        ArtInfo(
            artRes = R.drawable.pic_1,
            artName = stringResource(id = R.string.pic1),
            artistName = stringResource(id = R.string.artist_name_1)
        ),
        ArtInfo(
            artRes = R.drawable.pic_2,
            artName = stringResource(id = R.string.pic2),
            artistName = stringResource(id = R.string.artist_name_2)
        ),
        ArtInfo(
            artRes = R.drawable.pic_3,
            artName = stringResource(id = R.string.pic3),
            artistName = stringResource(id = R.string.artist_name_3)
        ),
        ArtInfo(
            artRes = R.drawable.pic_4,
            artName = stringResource(id = R.string.pic4),
            artistName = stringResource(id = R.string.artist_name_4)
        ),
        ArtInfo(
            artRes = R.drawable.pic_5,
            artName = stringResource(id = R.string.pic5),
            artistName = stringResource(id = R.string.artist_name_5)
        )
    )

    Column(
        modifier = modifier
            .wrapContentSize(align = Alignment.TopCenter)
            .padding(
                start = 12.dp,
                end = 12.dp,
                bottom = 8.dp,
                top = 28.dp
            )
            .verticalScroll(state = ScrollState(0))
    )
    {
        Text(
            text = stringResource(id = R.string.header),
            fontSize = 28.sp,
            fontFamily = FontFamily.Monospace,
            letterSpacing = 4.sp,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)
                .padding(bottom = 16.dp)
        )
        ImageLayout(
            artRes = artInfoArray[imageIndex].artRes,
            artContentDesRes = artInfoArray[imageIndex].artName,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)
        )
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 32.dp, bottom = 8.dp)
                .wrapContentSize(Alignment.Center),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            FloatingActionButton(
                onClick = {
                imageIndex = (imageIndex+1)%5
            },
                shape = CircleShape,
                modifier = Modifier.width(72.dp).testTag("nextBtn"),
                containerColor = Color(0xff007fff)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_keyboard_arrow_left_24),
                    contentDescription = stringResource(id = R.string.prev_btn_des),
                    tint = Color.White,
                    modifier = Modifier.size(44.dp)
                )
            }

            ArtistDescriptionLayout(
                imageNameRes = artInfoArray[imageIndex].artName,
                artistNameRes = artInfoArray[imageIndex].artistName
            )

            FloatingActionButton(onClick ={
                imageIndex = if(imageIndex==0) 4 else imageIndex-1
            },
                shape = CircleShape,
                modifier = Modifier.width(72.dp).testTag("prevBtn"),
                containerColor = Color(0xff007fff)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_keyboard_arrow_right_24),
                    contentDescription = stringResource(id = R.string.next_btn_des),
                    tint = Color.White,
                    modifier = Modifier.size(44.dp)
                )
            }
        }

    }
}

@Composable
fun ArtistDescriptionLayout(
    imageNameRes: String,
    artistNameRes: String,
    modifier: Modifier = Modifier
)
{
    Column(
        modifier = modifier
            .size(200.dp, 60.dp)
            .padding(horizontal = 8.dp)
            .wrapContentSize(Alignment.Center, false),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = imageNameRes,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 24.sp,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            modifier = Modifier
                .padding(bottom = 4.dp)
                .testTag("artNameTextNode")
        )
        Text(
            text = artistNameRes,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.SansSerif,
            fontStyle = FontStyle.Italic,
            color = Color.DarkGray,
            fontSize = 16.sp,
            modifier = Modifier.testTag("artistNameTextNode")
        )
    }
}

@Composable
fun ImageLayout(
    @DrawableRes artRes: Int,
    artContentDesRes: String,
    modifier: Modifier = Modifier
)
{
    Surface(
        modifier = modifier
            .shadow(24.dp),
        shape = RoundedCornerShape(20.dp)
    ){
        Image(
            painter = painterResource(id = artRes),
            contentDescription = artContentDesRes,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(height = 580.dp, width = 350.dp)
                .testTag("imageNode")
        )
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun DefaultPreview()
{
    PhotoGalleryAppTheme{
        RootLayout(modifier = Modifier.fillMaxSize())
    }
}