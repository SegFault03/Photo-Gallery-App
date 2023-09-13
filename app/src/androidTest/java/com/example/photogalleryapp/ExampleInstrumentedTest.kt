package com.example.photogalleryapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertContentDescriptionContains
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.photogalleryapp.ui.theme.PhotoGalleryAppTheme
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun artUpdateTest() {
        //Set up
        composeTestRule.setContent {
            PhotoGalleryAppTheme {
                RootLayout(modifier = Modifier.fillMaxSize())
            }
        }
        //Get node displaying image
        val imageLayout = composeTestRule.onNodeWithTag("imageNode")
        //Check if its set up correctly
        imageLayout.assertContentDescriptionContains("The Eiffel Tower")
        //Click next
        composeTestRule.onNodeWithTag("nextBtn").performClick()
        imageLayout.assertContentDescriptionContains("The Lotus Temple")
        //Click previous
        composeTestRule.onNodeWithTag("prevBtn").performClick()
        imageLayout.assertContentDescriptionContains("The Eiffel Tower")
    }

    @Test
    fun textDesTest(){
        //Set up
        composeTestRule.setContent {
            PhotoGalleryAppTheme {
                RootLayout(modifier = Modifier.fillMaxSize())
            }
        }
        //Get the two text nodes displaying artist and artist name
        val artNameNode = composeTestRule.onNodeWithTag("artNameTextNode")
        val artistNameNode = composeTestRule.onNodeWithTag("artistNameTextNode")
        //Check if they contain the default texts
        artNameNode.assertTextContains("The Eiffel Tower")
        artistNameNode.assertTextContains("ZaNuDa")
        //Click next
        composeTestRule.onNodeWithTag("nextBtn").performClick()
        artNameNode.assertTextContains("The Lotus Temple")
        artistNameNode.assertTextContains("jamiebramm")
        //Click previous
        composeTestRule.onNodeWithTag("prevBtn").performClick()
        artNameNode.assertTextContains("The Eiffel Tower")
        artistNameNode.assertTextContains("ZaNuDa")
    }
}