package com.example.animalgame

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.compose.material3.TextField
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle

import androidx.core.content.ContextCompat

import androidx.navigation.NavController
import androidx.navigation.NavHostController

var name = "Guest"
var totalCoins = 500
val avatarOptions = mutableListOf(
    R.drawable.tiger, R.drawable.parrot, R.drawable.monkey, R.drawable.elephant
)
var backgroundTheme = R.drawable.safari_light_brown
var ownedItems = mutableSetOf<String>()

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            AnimalGameNavHost(navController)
        }
    }
}

@Composable
fun AnimalGameGreetingScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Background Image
        Image(
            painter = painterResource(id = backgroundTheme),
            contentDescription = "Theme Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Foreground Column with content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Color(
                        ContextCompat.getColor(
                            LocalContext.current,
                            R.color.safari_light_brown
                        )
                    ).copy(alpha = 0.5f)
                ) // Optional semi-transparent overlay
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
        // Store Button in the top left
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                onClick = { navController.navigate("store") },
                modifier = Modifier
            ) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Go to Store",
                    tint = Color.Black,
                    modifier = Modifier.size(32.dp)
                )
            }
            Spacer(modifier = Modifier.width(1.dp)) // Placeholder to balance the row
        }
        // Rest of the existing code remains the same
        // Game Logo
        Image(
            painter = painterResource(id = R.drawable.animalgamelogo),
            contentDescription = "Animal Game Logo",
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
                .background(Color.LightGray)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Greeting Text
        Text(
            text = "Welcome $name, to the Animal Game!",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth() // Ensures text is centered properly
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Test your knowledge of the animal kingdom!",
            fontSize = 16.sp,
            color = Color.DarkGray
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Start Button
        Button(
            onClick = { navController.navigate("game_screen") },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(android.graphics.Color.parseColor("#228B22")))
        ) {
            Text(text = "Play Game", fontSize = 18.sp, color = Color.White)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { navController.navigate("name_submission") },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(android.graphics.Color.parseColor("#228B22")))
        ) {
            Text(text = "Submit Animal Name", fontSize = 18.sp, color = Color.White)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { navController.navigate("view_dictionary") },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(android.graphics.Color.parseColor("#228B22")))
        ) {
            Text(text = "View Animal Dictionary", fontSize = 18.sp, color = Color.White)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { navController.navigate("how_to_play") },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(android.graphics.Color.parseColor("#228B22")))
        ) {
            Text(text = "How To Play", fontSize = 18.sp, color = Color.White)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { backgroundTheme = R.drawable.safari_light_brown; navController.navigate("login") },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(android.graphics.Color.parseColor("#228B22")))
        ) {
            Text(text = "Logout", fontSize = 18.sp, color = Color.White)
        }
    }
}
    }

@Composable
fun nameSubmission(navController: NavController) {
    // State to hold the text entered by the user
    var animalName by remember { mutableStateOf("") }
    var lastMessages by remember { mutableStateOf(listOf<Pair<String, Boolean>>()) }
    val animalDictionary = AnimalDictionary()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = backgroundTheme),
            contentDescription = "Theme Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        // Box for Back Button
        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 12.dp)
        ) {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .padding(top = 16.dp)
                    .size(60.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }
        }

        // Box for Main Content (Column)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 64.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Color(
                            ContextCompat.getColor(
                                LocalContext.current,
                                R.color.safari_light_brown
                            )
                        ).copy(alpha = 0.5f)
                    )
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                // Title Text
                Text(
                    text = "Animal Name Submission",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                // Description Text
                Text(
                    text = "If you are aware of an animal that was not accepted but should be, please submit the animal name for review.",
                    fontSize = 16.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Animal Name TextField
                TextField(
                    value = animalName,
                    onValueChange = { newText -> animalName = newText.trimStart() }, // Prevent leading spaces
                    label = { Text("Animal Name") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    singleLine = true
                )

                // Submit Button (Enabled Only If Text Is Entered)
                Button(
                    onClick = {
                        val letter = animalName.firstOrNull()?.uppercase() ?: ""

                        val animalList = when (letter) {
                            "A" -> animalDictionary.A
                            "B" -> animalDictionary.B
                            "C" -> animalDictionary.C
                            "D" -> animalDictionary.D
                            "E" -> animalDictionary.E
                            "F" -> animalDictionary.F
                            "G" -> animalDictionary.G
                            "H" -> animalDictionary.H
                            "I" -> animalDictionary.I
                            "J" -> animalDictionary.J
                            "K" -> animalDictionary.K
                            "L" -> animalDictionary.L
                            "M" -> animalDictionary.M
                            "N" -> animalDictionary.N
                            "O" -> animalDictionary.O
                            "P" -> animalDictionary.P
                            "Q" -> animalDictionary.Q
                            "R" -> animalDictionary.R
                            "S" -> animalDictionary.S
                            "T" -> animalDictionary.T
                            "U" -> animalDictionary.U
                            "V" -> animalDictionary.V
                            "W" -> animalDictionary.W
                            "X" -> animalDictionary.X
                            "Y" -> animalDictionary.Y
                            "Z" -> animalDictionary.Z
                            else -> null
                        }

                        if (animalList != null && isValidGuess(animalName, animalList)) {
                            val message = "Animal already exists in the Dictionary."
                            lastMessages = listOf(message to true) + lastMessages
                        } else {
                            val message = "Animal name has been submitted."
                            lastMessages = listOf(message to false) + lastMessages

                        }
                    },
                    enabled = animalName.isNotBlank(), // Button is only clickable if text is entered
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(top = 16.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.forest_green))
                ) {
                    Text(
                        text = "Submit",
                        fontSize = 18.sp,
                        color = Color.White
                    )
                }

                // Display message if there is one
                lastMessages.forEach { (messageText, isAlreadyIn) ->
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = messageText,
                        fontSize = 18.sp,
                        color = if (isAlreadyIn) Color.Yellow else Color.White,
                    )
                }

                //STOPPINGPOINT
            }
        }
    }
}



@Composable
fun howToPlay(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()

    ) {
        Image(
            painter = painterResource(id = backgroundTheme),
            contentDescription = "Theme Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        // Back Button Box (non-scrollable, always on top)
        Box(
            modifier = Modifier
                .fillMaxWidth() // Fill the width for the back button
                .padding(1.dp) // Padding for positioning the icon at the top-left
                .align(Alignment.TopStart) // Position it at the top-left
        ) {
            IconButton(
                onClick = { navController.popBackStack() },  // Back to the previous screen
                modifier = Modifier
                    .padding(top = 16.dp) // Padding for positioning the icon at the top-left
                    .align(Alignment.TopStart) // Align the back button to the top-left corner
                    .size(60.dp) // Set a fixed size for the icon button
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",

                )
            }
        }

        // Content Box (scrollable, below the back button)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Color(
                        ContextCompat.getColor(
                            LocalContext.current,
                            R.color.safari_light_brown
                        )
                    ).copy(alpha = 0.5f)
                )
                .padding(top = 64.dp) // Ensure content starts after the back button
                .verticalScroll(rememberScrollState()) // Make the column scrollable
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 8.dp) // Adjust padding
            ) {
                // Title text (rules header)
                Text(
                    text = "🐾 Animal Game Rules 🐾",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Center,         // Centers the text content
                    modifier = Modifier
                        .fillMaxWidth()                   // Ensures the text takes full width for centering
                        .padding(bottom = 20.dp)
                )

                // Rules text
                Text(
                    text = """
                    1. Players take turns guessing animal names that start with the same letter.

                    2. Each player must guess a unique animal. Repeating an animal eliminates the player.

                    3. If a guess is incorrect or not in the list, the player is eliminated.

                    4. The last player standing wins!

                    5. Have fun and think creatively!

                    """.trimIndent(),
                    fontSize = 22.sp,
                    color = Color.Black
                )

                // Spacer between the rules and the image
                Spacer(modifier = Modifier.height(16.dp))

                // Image with rounded corners and centered alignment
                Image(
                    painter = painterResource(id = R.drawable.dog_and_monkey_chess),
                    contentDescription = "Dog and Monkey playing chess",
                    modifier = Modifier
                        .size(340.dp)
                        .align(Alignment.CenterHorizontally) // Ensures the image is centered horizontally
                        .clip(RoundedCornerShape(16.dp)) // Rounded corners for the image
                        .border(4.dp, Color.Black)
                )
            }
        }
    }
}





@Composable
fun AnimalGameNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "login") {
        composable("greeting") { AnimalGameGreetingScreen(navController) }
        composable("game_screen") { GameScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("register") { RegisterScreen(navController) }
        composable("store") { StoreScreen(navController) }
        composable("purchase_coins") {
            PurchaseCoinsScreen(navController)
        }
        composable(
            "game_play/{letter}/{numPlayers}",
            arguments = listOf(
                navArgument("letter") { type = NavType.StringType },
                navArgument("numPlayers") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val letter = backStackEntry.arguments?.getString("letter")?.firstOrNull() ?: 'A'
            val numPlayers = backStackEntry.arguments?.getInt("numPlayers") ?: 2
            GamePlay(navController, letter, numPlayers)
        }
        composable(
            "winner_screen/{winnerName}",
            arguments = listOf(navArgument("winnerName") { type = NavType.StringType })
        ) { backStackEntry ->
            val winnerName = backStackEntry.arguments?.getString("winnerName") ?: "Unknown"
            WinnerScreen(navController, winnerName)
        }
        composable("name_submission") { nameSubmission(navController) }
        composable("view_dictionary") { viewDictionary(navController) }
        composable("animal_list/{letter}",arguments = listOf(
            navArgument("letter") { type = NavType.StringType }
        )
        ) { backStackEntry ->
            val letter = backStackEntry.arguments?.getString("letter")?.firstOrNull() ?: 'A'
            animalList(navController, letter)
        }
        composable("how_to_play") { howToPlay(navController) }
    }
}


@Composable
fun viewDictionary(navController: NavController) {
    val alphabet = ('A'..'Z').toList()

    Box(modifier = Modifier.fillMaxSize()) {
        // Background for everything
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(ContextCompat.getColor(LocalContext.current, R.color.safari_light_brown)))
        )

        // Red background for the top section
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(121.dp) // Height to cover everything above the divider
                .background(Color(0xFF83343B))
        )

        // Back Button
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .padding(top = 16.dp)
                .align(Alignment.TopStart)
                .size(48.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.Black
            )
        }

        // Title
        Text(
            text = "Animal Game Dictionary",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 60.dp)
        )

        // Black horizontal divider
        Divider(
            color = Color.Black,
            thickness = 1.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 120.dp)
        )

        // LazyColumn for alphabet buttons
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 120.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
            items(alphabet.chunked(1)) { row ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    row.forEach { letter ->
                        Button(
                            onClick = { navController.navigate("animal_list/${letter}") },
                            modifier = Modifier
                                .weight(1f)
                                .height(50.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(android.graphics.Color.parseColor("#228B22")))
                        ) {
                            Text(
                                text = letter.toString(),
                                fontSize = 18.sp,
                                color = Color.White
                            )
                        }
                    }
                }
                Image(
                    painter = getImage(row[0]),
                    contentDescription = "Image description",
                    modifier = Modifier.padding(16.dp).border(4.dp, Color.Black),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(16.dp))
                // Black horizontal divider
                Divider(
                    color = Color.Black,
                    thickness = 1.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}


@Composable
fun getImage(letter: Char): Painter {
    val context = LocalContext.current
    val resourceName = "${letter.lowercase()}_animal_image" // Generates "a_animal_image", "b_animal_image", etc.
    val resourceId = context.resources.getIdentifier(resourceName, "drawable", context.packageName)

    return painterResource(
        id = if (resourceId != 0) resourceId else R.drawable.defaulticon // Use default if not found
    )
}

@Composable
fun animalList(navController: NavController, letter: Char) {
    var animalDictionary = AnimalDictionary()
    val animalList = when (letter.uppercase()) {
        "A" -> animalDictionary.A
        "B" -> animalDictionary.B
        "C" -> animalDictionary.C
        "D" -> animalDictionary.D
        "E" -> animalDictionary.E
        "F" -> animalDictionary.F
        "G" -> animalDictionary.G
        "H" -> animalDictionary.H
        "I" -> animalDictionary.I
        "J" -> animalDictionary.J
        "K" -> animalDictionary.K
        "L" -> animalDictionary.L
        "M" -> animalDictionary.M
        "N" -> animalDictionary.N
        "O" -> animalDictionary.O
        "P" -> animalDictionary.P
        "Q" -> animalDictionary.Q
        "R" -> animalDictionary.R
        "S" -> animalDictionary.S
        "T" -> animalDictionary.T
        "U" -> animalDictionary.U
        "V" -> animalDictionary.V
        "W" -> animalDictionary.W
        "X" -> animalDictionary.X
        "Y" -> animalDictionary.Y
        "Z" -> animalDictionary.Z
        else -> throw IllegalArgumentException("Letter '$letter' is not valid in the dictionary.")
    }

    // Box to allow layering the back button and title over the content
    Box(modifier = Modifier.fillMaxSize()) {
        // Background for everything
        Box(
            modifier = Modifier
                
            .fillMaxSize()
                .background(
                Color(
                    ContextCompat.getColor(
                        LocalContext.current,
                        R.color.safari_light_brown
                    )
                )
            )
            .alpha(5f)
        )

        // Red background for the top section
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(121.dp) // Height to cover everything above the divider
                .background(Color(0xFF83343B))
        )
        Image(
            painter = painterResource(id = backgroundTheme),
            contentDescription = "Theme Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        // Back Button icon at the top-left
        IconButton(
            onClick = { navController.popBackStack() },  // Back to the previous screen
            modifier = Modifier
                .padding(top = 16.dp) // Padding for positioning the icon at the top-left
                .align(Alignment.TopStart) // Align the back button to the top-left corner
                .size(48.dp) // Set a fixed size for the icon button
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.Black
            )
        }

        // Title Text at the top center
        Text(
            text = "Letter $letter Dictionary",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.TopCenter)  // Position it at the top center
                .padding(top = 60.dp) // Add space from the top to avoid overlap with back button
        )

        // Black horizontal line
        Divider(
            color = Color.Black,
            thickness = 1.dp,
            modifier = Modifier
                .fillMaxWidth() // Makes the line span the full width
                .padding(top = 120.dp) // Ensure the line is placed below the title
        )

        // LazyColumn for the alphabet buttons, ensuring space for the title
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 120.dp, start = 16.dp, end = 16.dp, bottom = 16.dp), // Adjust top padding to avoid overlap with title and back button
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp) // Add spacing between rows of buttons
        ) {
            item {
                Image(
                    painter = getImage(letter),
                    contentDescription = "Image description",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.padding(top = 30.dp).fillMaxWidth().border(4.dp, Color.Black)
                )
            }
            item {
                Spacer(modifier = Modifier.height(16.dp)) // Space before the first animal
            }
            items(animalList.toList().sorted()) { animal ->
                Text(text = animal, fontSize = 20.sp, fontWeight = FontWeight.Bold , style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(8.dp))
            }
        }
    }
}

@Composable
fun GameScreen(navController: NavController) {
    var selectedLetter by remember { mutableStateOf<Char?>(null) }
    var selectedPlayers by remember { mutableStateOf<Int?>(null) }
    var isGameStarted by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = backgroundTheme),
            contentDescription = "Theme Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        // Back Button
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.Black
            )
        }

        Column(
            modifier = Modifier.fillMaxSize()
                .background(
                    Color(
                        ContextCompat.getColor(
                            LocalContext.current,
                            R.color.safari_light_brown
                        )
                    ).copy(alpha = 0.5f)
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center // Correct placement
        ) {
            // Prompt for letter selection
            Text(
                text = "Choose a letter:",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))
            // Letter Selection (A-Z) in a grid
            LazyVerticalGrid(
                columns = GridCells.Fixed(6), // You can adjust the number of columns here
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(8.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(('A'..'Z').toList()) { letter ->
                    Button(
                        onClick = { selectedLetter = letter },
                        modifier = Modifier
                            .padding(4.dp)
                            .background(
                                if (selectedLetter == letter) colorResource(id = R.color.forest_green) else Color.Gray
                            ),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                    ) {
                        Text(
                            text = letter.toString(),
                            color = if (selectedLetter == letter) Color.White else Color.Black,
                            fontSize = 18.sp
                        )
                    }
                }
            }

            Text(
                text = "Choose number of players:",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            Spacer(modifier = Modifier.height(16.dp))

            // Number of Players Selection (1-4)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                (2..4).forEach { playerCount ->
                    Button(
                        onClick = { selectedPlayers = playerCount },
                        modifier = Modifier
                            .padding(4.dp)
                            .background(
                                if (selectedPlayers == playerCount) colorResource(id = R.color.forest_green) else Color.Gray
                            ),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                    ) {
                        Text(
                            text = playerCount.toString(),
                            color = if (selectedPlayers == playerCount) Color.White else Color.Black,
                            fontSize = 18.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Start Game Button
            Button(
                onClick = {
                    navController.navigate("game_play/${selectedLetter}/${selectedPlayers}")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(android.graphics.Color.parseColor("#228B22"))),
                enabled = selectedLetter != null && selectedPlayers != null
            ) {
                Text(text = "Start Game", fontSize = 18.sp, color = Color.White)
            }


        }
    }
}

@Composable
fun GamePlay(navController: NavController, letter: Char, numPlayers: Int) {
    val animalDictionary = AnimalDictionary()

    // Dynamically get the correct animal list based on the letter chosen
    val animalList = when (letter.uppercase()) {
        "A" -> animalDictionary.A
        "B" -> animalDictionary.B
        "C" -> animalDictionary.C
        "D" -> animalDictionary.D
        "E" -> animalDictionary.E
        "F" -> animalDictionary.F
        "G" -> animalDictionary.G
        "H" -> animalDictionary.H
        "I" -> animalDictionary.I
        "J" -> animalDictionary.J
        "K" -> animalDictionary.K
        "L" -> animalDictionary.L
        "M" -> animalDictionary.M
        "N" -> animalDictionary.N
        "O" -> animalDictionary.O
        "P" -> animalDictionary.P
        "Q" -> animalDictionary.Q
        "R" -> animalDictionary.R
        "S" -> animalDictionary.S
        "T" -> animalDictionary.T
        "U" -> animalDictionary.U
        "V" -> animalDictionary.V
        "W" -> animalDictionary.W
        "X" -> animalDictionary.X
        "Y" -> animalDictionary.Y
        "Z" -> animalDictionary.Z
        else -> throw IllegalArgumentException("Letter '$letter' is not valid in the dictionary.")
    }

    var playerNames by remember { mutableStateOf(List(numPlayers) { "" }) }
    var selectedAvatars by remember { mutableStateOf(List(numPlayers) { R.drawable.defaulticon }) }
    var nameEntryComplete by remember { mutableStateOf(false) }
    var currentPlayerIndex by remember { mutableStateOf(0) }
    var guessedAnimals by remember { mutableStateOf(mutableSetOf<String>()) }
    var eliminatedPlayers by remember { mutableStateOf(mutableSetOf<Int>()) }
    var guess by remember { mutableStateOf("") }
    var feedbackMessage by remember { mutableStateOf<String?>(null) }
    var lastGuesses by remember { mutableStateOf(listOf<Pair<String, Boolean>>()) }

    Box(
    ) {
        // Background Image
        Image(
            painter = painterResource(id = backgroundTheme),
            contentDescription = "Theme Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Color(
                        ContextCompat.getColor(
                            LocalContext.current,
                            R.color.safari_light_brown
                        )
                    ).copy(alpha = 0.5f)
                )
            .then(if (nameEntryComplete) Modifier.border(4.dp, Color(0xFF83343B)) else Modifier)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (!nameEntryComplete) {

                // Player Name Entry Screen
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Enter Player Names & Choose Avatar",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth() // Ensures text is centered properly
                )

                Spacer(modifier = Modifier.height(16.dp))

                playerNames.forEachIndexed { index, _ ->
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        TextField(
                            value = playerNames[index],
                            onValueChange = { newName ->
                                playerNames =
                                    playerNames.toMutableList().also { it[index] = newName }
                            },
                            label = { Text("Player ${index + 1} Name") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        // Avatar Selection
                        Text("Select Avatar:")
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            avatarOptions.forEach { avatarResId ->
                                Box(
                                    modifier = Modifier
                                        .size(64.dp)
                                        .clip(CircleShape)
                                        .background(
                                            if (selectedAvatars[index] == avatarResId) Color.DarkGray else Color.Transparent
                                        )
                                        .clickable {
                                            selectedAvatars = selectedAvatars.toMutableList().also {
                                                it[index] = avatarResId
                                            }
                                        },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Image(
                                        painter = painterResource(id = avatarResId),
                                        contentDescription = "Avatar",
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .clip(CircleShape)
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }

                Button(
                    onClick = { nameEntryComplete = true },
                    enabled = playerNames.all { it.isNotBlank() } && selectedAvatars.none { it == R.drawable.defaulticon },
                    modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(android.graphics.Color.parseColor("#228B22")))
                ) {
                    Text(text = "Start Game", fontSize = 18.sp, color = Color.White)
                }
            } else {
                // Game Start Screen after names and avatars are selected
                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Animals that start with '$letter'",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(16.dp))



                Spacer(modifier = Modifier.height(16.dp))

                // Guess Input Field
                androidx.compose.material3.TextField(
                    value = guess,
                    onValueChange = { guess = it },
                    label = { Text("Enter an animal") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Submit Guess Button
                Button(
                    onClick = {
                        val formattedGuess = guess.trim().replaceFirstChar { it.uppercase() }
                        var isCorrect = isValidGuess(formattedGuess, animalList)

                        if (isCorrect && formattedGuess !in guessedAnimals) {
                            guessedAnimals.add(formattedGuess)
                            feedbackMessage =
                                "${playerNames[currentPlayerIndex].replaceFirstChar { it.uppercase() }} guessed correctly!"
                        } else {
                            eliminatedPlayers.add(currentPlayerIndex)
                            feedbackMessage =
                           
                                "Incorrect Guess, ${playerNames[currentPlayerIndex].replaceFirstChar { it.uppercase() }} has been eliminated!"
                            // update isCorrect variable based on if the Animal is in the list but has already been guessed.
                            isCorrect = false
                        }


                        // Update lastGuesses list with (guess, isCorrect)
                        lastGuesses = listOf(formattedGuess to isCorrect) + lastGuesses

                        if (eliminatedPlayers.size == numPlayers - 1) {
                            val winnerIndex =
                                (0 until numPlayers).first { it !in eliminatedPlayers }
                            navController.navigate("winner_screen/${playerNames[winnerIndex]}")
                        } else {
                            currentPlayerIndex =
                           
                                getNextPlayer(currentPlayerIndex, numPlayers, eliminatedPlayers)
                        }

                        guess = "" // Reset guess input
                    },
                    enabled = guess.isNotBlank(),
                    modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(android.graphics.Color.parseColor("#228B22")))
                ) {
                    Text(text = "Submit Guess", fontSize = 18.sp, color = Color.White)
                }

                Spacer(modifier = Modifier.height(16.dp))

                Spacer(modifier = Modifier.height(24.dp))

                // Game Turn System
                Box(
                    modifier = Modifier
                        .padding(1.dp)  // Adds space around the box
                        .clip(RoundedCornerShape(12.dp))  // Rounds the edges
                        .background(Color(0xFF83343B))  // Terracotta red color
                        .padding(horizontal = 2.dp, vertical = 8.dp)  // Inner padding
                        .height(100.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = buildAnnotatedString {
                            // Display the "Current Turn: " text normally
                            append("Current Turn: ")
                            // Display the player name in italics
                            withStyle(style = SpanStyle(fontStyle = FontStyle.Italic)) {
                                append(playerNames[currentPlayerIndex].replaceFirstChar { it.uppercaseChar() })
                            }
                        },
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.align(Alignment.Center),
                    textAlign = TextAlign.Center
                    )
                }


                Spacer(modifier = Modifier.height(16.dp))


                // Display Player Avatars and Names
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    playerNames.forEachIndexed { index, name ->
                        val isEliminated = eliminatedPlayers.contains(index)
                        val backgroundColor = when {
                            index == currentPlayerIndex -> Color.Green
                            isEliminated -> Color.Red
                            else -> Color.Transparent
                        }
                        Box(
                            modifier = Modifier
                                .padding(8.dp)
                                .clip(CircleShape)
                                .background(backgroundColor)
                                .clickable { /* handle avatar click if needed */ },
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = selectedAvatars[index]),
                                contentDescription = "Avatar",
                                modifier = Modifier
                                    .size(75.dp)
                                    .clip(CircleShape)
                            )
                        }
                    }
                }


                // Display Feedback Message
                feedbackMessage?.let {
                    Text(
                        text = it,
                        fontSize = 18.sp,
                        color = if (it.contains("eliminated")) Color.Red else Color.Green,
                        fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Previous Guesses:",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(8.dp))

                lastGuesses.forEach { (guessText, isCorrect) ->
                    Text(
                        text = guessText,
                        fontSize = 18.sp,
                        color = if (isCorrect) Color.Green else Color.Red
                    )
                }

            }
        }
    }
}

// Helper function to check both singular and plural guesses
fun isValidGuess(guess: String, animalList: Set<String>): Boolean {
    val formattedAnimalList = animalList.map { it.lowercase() }.toSet()  // Normalize animalList
    val singularGuess = guess.trim().lowercase()
    val pluralGuess = singularGuess + "s"

    return singularGuess in formattedAnimalList || pluralGuess in formattedAnimalList
}

// Function to get the next active player
fun getNextPlayer(currentIndex: Int, totalPlayers: Int, eliminatedPlayers: Set<Int>): Int {
    var nextIndex = (currentIndex + 1) % totalPlayers
    while (nextIndex in eliminatedPlayers) {
        nextIndex = (nextIndex + 1) % totalPlayers
    }
    return nextIndex
}




// Winner Screen
@Composable
fun WinnerScreen(navController: NavController, winnerName: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Background Image
        Image(
            painter = painterResource(id = backgroundTheme),
            contentDescription = "Theme Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Color(
                    ContextCompat.getColor(
                        LocalContext.current,
                        R.color.safari_light_brown
                    )
                ).copy(alpha = 0.5f)
            )
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.animalgamevictory), // Replace with your image resource
            contentDescription = "Image description",
            modifier = Modifier.padding(16.dp).border(4.dp, Color.Black),
            contentScale = ContentScale.Crop
        )
        Text(
            text = "Congratulations, ${winnerName.replaceFirstChar { it.uppercase() }}! You won the Animal Game!",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth() // Ensures text is centered properly
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate("greeting") }, // Restart Game
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(android.graphics.Color.parseColor("#228B22")))
        ) {
            Text(text = "Play Again", fontSize = 18.sp, color = Color.White)
        }
    }
}
}


@Composable
fun LoginScreen(navController: NavController) {
    var user by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val passwordVisible by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Color(
                    ContextCompat.getColor(
                        LocalContext.current,
                        R.color.safari_light_brown
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // App Logo
            Image(
                painter = painterResource(id = R.drawable.animalgamelogo),
                contentDescription = "Animal Game Logo",
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Title
            Text(
                text = "Log in to Animal Game",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(24.dp))

            // User Field
            OutlinedTextField(
                value = user,
                onValueChange = { user = it },
                label = { Text("User") },
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = "User") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Password Field
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Password") },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            if (errorMessage != null) {
                Text(
                    text = errorMessage!!,
                    color = Color.Red,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(8.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Login Button
            Button(
                onClick = {
                    // Basic validation
                    if (user.isBlank() || password.isBlank()) {
                        errorMessage = "Please fill in all fields"
                    } else {
                        name = user
                        navController.navigate("greeting") {}
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.forest_green))
            ) {
                Text(text = "Login", fontSize = 18.sp, color = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Register Link
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Don't have an account? ",
                    color = Color.DarkGray
                )
                TextButton(onClick = { navController.navigate("register") }) {
                    Text(
                        text = "Register",
                        fontSize = 18.sp,
                        color = colorResource(id = R.color.forest_green),
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // Guest Mode
            TextButton(
                onClick = { name = "Guest"; navController.navigate("greeting") },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(
                    text = "Continue as Guest",
                    fontSize = 18.sp,
                    color = Color.DarkGray
                )
            }
        }
    }
}

@Composable
fun RegisterScreen(navController: NavController) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    val passwordVisible by remember { mutableStateOf(false) }
    val confirmPasswordVisible by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Color(
                    ContextCompat.getColor(
                        LocalContext.current,
                        R.color.safari_light_brown
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Image(
                painter = painterResource(id = R.drawable.animalgamelogo),
                contentDescription = "Animal Game Logo",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray)
                    .padding(8.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Title
            Text(
                text = "Create an Account",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Username Field
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username") },
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Username") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Email Field
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                leadingIcon = { Icon(Icons.Default.Email, contentDescription = "Email") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Password Field
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Password") },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Confirm Password Field
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Confirm Password") },
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Confirm Password") },
                visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                )
            )

            Spacer(modifier = Modifier.height(8.dp))
            if (errorMessage != null) {
                Text(
                    text = errorMessage!!,
                    color = Color.Red,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(8.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Register Button
            Button(
                onClick = {
                    when {
                        username.isBlank() || email.isBlank() || password.isBlank() || confirmPassword.isBlank() -> {
                            errorMessage = "Please fill in all fields"
                        }
                        password != confirmPassword -> {
                            errorMessage = "Passwords do not match"
                        }
                        else -> {
                            name = username
                            navController.navigate("greeting") {
                            }
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.forest_green))
            ) {
                Text(text = "Register", fontSize = 18.sp, color = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Login Link
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Already have an account? ",
                    color = Color.DarkGray
                )
                TextButton(onClick = { navController.navigate("login") }) {
                    Text(
                        text = "Login",
                        color = colorResource(id = R.color.forest_green),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

data class StoreItem(
    val id: String,
    val name: String,
    val price: Int,
    val imageResId: Int,
    val type: ItemType
)

enum class ItemType {
    THEME, AVATAR
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StoreScreen(navController: NavController) {
    // Simulated user's current coins
    var userCoins by remember { mutableStateOf(totalCoins) }
    var currentThemeBackground by remember { mutableStateOf<Int?>(backgroundTheme) }
    // Predefined store items
    val storeItems = listOf(
        // Themes
        StoreItem("safari_theme", "Safari Theme", 200, R.drawable.safari_theme, ItemType.THEME),
        StoreItem("ocean_theme", "Ocean Theme", 250, R.drawable.ocean_theme, ItemType.THEME),
        StoreItem("forest_theme", "Forest Theme", 200, R.drawable.forest_theme, ItemType.THEME),

        // Avatar Skins
        StoreItem("lion_avatar", "Lion Avatar", 150, R.drawable.lion_avatar, ItemType.AVATAR),
        StoreItem("bear_avatar", "Bear Avatar", 150, R.drawable.bear_avatar, ItemType.AVATAR),
        StoreItem("giraffe_avatar", "Giraffe Avatar", 175, R.drawable.giraffe_avatar, ItemType.AVATAR),
        StoreItem("penguin_avatar", "Penguin Avatar", 175, R.drawable.penguin_avatar, ItemType.AVATAR)
    )
    var showThemePopup by remember { mutableStateOf<StoreItem?>(null) }
    // Track purchased items
    var purchasedItems = ownedItems
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Store",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "Coins: $userCoins",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                        IconButton(
                            onClick = { navController.navigate("purchase_coins") }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Buy Coins",
                                tint = Color(0xFFFFD700) // Gold color for the coin icon
                            )
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        showThemePopup?.let { theme ->
            AlertDialog(
                onDismissRequest = { showThemePopup = null },
                title = { Text("Change Theme") },
                text = { Text("Do you want to set the ${theme.name} as your background?") },
                confirmButton = {
                    TextButton(
                        onClick = {
                            currentThemeBackground = theme.imageResId
                            backgroundTheme = theme.imageResId
                            showThemePopup = null
                        }
                    ) {
                        Text("Yes")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = { showThemePopup = null }
                    ) {
                        Text("No")
                    }
                }
            )
        }

        // Background Image
        currentThemeBackground?.let { imageResId ->
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = "Theme Background",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            // Buy Coins Button
            Button(
                onClick = { navController.navigate("purchase_coins") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4CAF50) // Green color for purchase button
                )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "Purchase",
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        "Buy More Coins",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
            }

            // Rest of the store content in a LazyColumn
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                // Themes Section
                item {
                    Box(
                        modifier = Modifier
                            .background(Color.LightGray, shape = RoundedCornerShape(4.dp))
                            .padding(4.dp)
                    ) {
                        Text(
                            text = "Themes",
                            modifier = Modifier.padding(16.dp),
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    }
                }
                item {
                    StoreItemGrid(
                        items = storeItems.filter { it.type == ItemType.THEME },
                        userCoins = userCoins,
                        purchasedItems = purchasedItems,
                        onPurchase = { item ->
                            if (userCoins >= item.price) {
                                userCoins -= item.price
                                totalCoins = userCoins
                                purchasedItems.add(item.id)
                                ownedItems.add(item.id)
                                showThemePopup = item
                            }
                        }
                    )
                }

                // Avatar Skins Section
                item {
                    Box(
                        modifier = Modifier
                            .background(Color.LightGray, shape = RoundedCornerShape(4.dp))
                            .padding(4.dp)
                    ) {
                        Text(
                            text = "Avatars",
                            modifier = Modifier.padding(16.dp),
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    }
                }
                item {
                    StoreItemGrid(
                        items = storeItems.filter { it.type == ItemType.AVATAR },
                        userCoins = userCoins,
                        purchasedItems = purchasedItems,
                        onPurchase = { item ->
                            if (userCoins >= item.price) {
                                userCoins -= item.price
                                totalCoins = userCoins
                                purchasedItems.add(item.id)
                                ownedItems.add(item.id)
                                avatarOptions.add(item.imageResId)
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun StoreItemGrid(
    items: List<StoreItem>,
    userCoins: Int,
    purchasedItems: Set<String>,
    onPurchase: (StoreItem) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        items.chunked(2).forEach { rowItems ->
            Row(modifier = Modifier.fillMaxWidth()) {
                rowItems.forEach { item ->
                    StoreItemCard(
                        item = item,
                        userCoins = userCoins,
                        purchasedItems = purchasedItems,
                        onPurchase = onPurchase,
                        modifier = Modifier.weight(1f)
                    )
                }
                if (rowItems.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
fun StoreItemCard(
    item: StoreItem,
    userCoins: Int,
    purchasedItems: Set<String>,
    onPurchase: (StoreItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = item.imageResId),
                contentDescription = item.name,
                modifier = Modifier
                    .size(100.dp)
                    .padding(bottom = 8.dp)
            )
            Text(
                text = item.name,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "${item.price} Coins",
                color = Color.Gray
            )

            if (item.id in purchasedItems) {
                Button(
                    onClick = { /* Already purchased */ },
                    enabled = false,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Green,
                        contentColor = Color.LightGray
                    )
                ) {
                    Text("Owned")
                }
            } else {
                Button(
                    onClick = { onPurchase(item) },
                    enabled = userCoins >= item.price,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF228B22),
                        contentColor = Color.LightGray
                    )
                ) {
                    Text("Buy")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PurchaseCoinsScreen(navController: NavController) {
    val coinPackages = listOf(
        CoinPackage(100, "$0.99", "small_coins"),
        CoinPackage(500, "$4.99", "medium_coins"),
        CoinPackage(1000, "$9.99", "large_coins"),
        CoinPackage(2500, "$19.99", "xlarge_coins")
    )

    var showPurchaseDialog by remember { mutableStateOf(false) }
    var selectedPackage by remember { mutableStateOf<CoinPackage?>(null) }
    var userCoins by remember { mutableStateOf(totalCoins) }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Purchase Coins",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    Text(
                        "Current Coins: $userCoins",
                        modifier = Modifier.padding(end = 16.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
            )
        }
    ) { paddingValues ->
        if (showPurchaseDialog) {
            selectedPackage?.let { pkg ->
                AlertDialog(
                    onDismissRequest = { showPurchaseDialog = false },
                    title = { Text("Confirm Purchase") },
                    text = {
                        Text("Do you want to purchase ${pkg.amount} coins for ${pkg.price}?")
                    },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                userCoins += pkg.amount
                                totalCoins += pkg.amount
                                showPurchaseDialog = false
                                val message = "Successfully purchased ${pkg.amount} coins!"
                                context.makeToast(message)
                            }
                        ) {
                            Text("Purchase")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { showPurchaseDialog = false }) {
                            Text("Cancel")
                        }
                    }
                )
            }
        }

        Image(
            painter = painterResource(id = backgroundTheme),
            contentDescription = "Theme Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF5F5F5)
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        "Buy Coins",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        "Purchase coins to unlock premium items in the store!",
                        fontSize = 14.sp
                    )
                }
            }

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(coinPackages) { pkg ->
                    CoinPackageItem(
                        coinPackage = pkg,
                        onClick = {
                            selectedPackage = pkg
                            showPurchaseDialog = true
                        }
                    )
                }
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFFFF3E0)
                )
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Special Offer",
                        tint = Color(0xFFFF9800),
                        modifier = Modifier.size(40.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(
                            "Limited Time Offer!",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = Color(0xFFE65100)
                        )
                        Text(
                            "Get 5000 coins for only $29.99 (50% off!)",
                            fontSize = 14.sp
                        )
                    }
                }
                Button(
                    onClick = {
                        selectedPackage = CoinPackage(5000, "$29.99", "special_offer")
                        showPurchaseDialog = true
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF9800)
                    )
                ) {
                    Text("Get Special Offer")
                }
            }
        }
    }
}

fun Context.makeToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

@Composable
fun CoinPackageItem(coinPackage: CoinPackage, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(Color(0xFFFFD700), shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = coinPackage.amount.toString(),
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Text(
                        text = "${coinPackage.amount} Coins",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Text(
                        text = coinPackage.price,
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                }
            }

            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4CAF50)
                )
            ) {
                Text("Buy")
            }
        }
    }
}

data class CoinPackage(
    val amount: Int,
    val price: String,
    val id: String
)


class AnimalDictionary {
    var A = setOf(
        "Aardvark",
        "Aardwolf",
        "Abyssinian",
        "Abyssinian Guinea Pig",
        "Acadian Flycatcher",
        "Achrioptera Manga",
        "Ackie Monitor",
        "Addax",
        "Adélie Penguin",
        "Admiral Butterfly",
        "Aesculapian Snake",
        "Affenpinscher",
        "Afghan Hound",
        "African Bullfrog",
        "African Bush Elephant",
        "African Civet",
        "African Clawed Frog",
        "African Elephant",
        "African Fish Eagle",
        "African Forest Elephant",
        "African Golden Cat",
        "African Grey Parrot",
        "African Jacana",
        "African Palm Civet",
        "African Penguin",
        "African Sugarcane Borer",
        "African Tree Toad",
        "African Wild Dog",
        "Agama Lizard",
        "Agkistrodon Contortrix",
        "Agouti",
        "Aidi",
        "Ainu",
        "Airedale Terrier",
        "Airedoodle",
        "Akbash",
        "Akita",
        "Akita Shepherd",
        "Alabai",
        "Alaskan Husky",
        "Alaskan Klee Kai",
        "Alaskan Malamute",
        "Alaskan Pollock",
        "Alaskan Shepherd",
        "Albacore Tuna",
        "Albatross",
        "Albertonectes",
        "Albino Corn Snake",
        "Aldabra Giant Tortoise",
        "Alligator Gar",
        "Allosaurus",
        "Alpaca",
        "Alpine Dachsbracke",
        "Alpine Goat",
        "Alusky",
        "Amano Shrimp",
        "Amargasaurus",
        "Amazon Parrot",
        "Amazon River Dolphin",
        "Amazon Tree Boa",
        "Amazonian Royal Flycatcher",
        "Amberjack",
        "Ambrosia Beetle",
        "American Alligator",
        "Alligator",
        "American Alsatian",
        "Alsatian",
        "American Bulldog",
        "American Bully",
        "American Cocker Spaniel",
        "American Cockroach",
        "American Coonhound",
        "American Dog Tick",
        "American Eel",
        "American Eskimo Dog",
        "American Foxhound",
        "American Hairless Terrier",
        "American Leopard Hound",
        "American Paddlefish",
        "American Pit Bull Terrier",
        "American Pugabull",
        "American Pygmy Goat",
        "American Robin",
        "American Staffordshire Terrier",
        "American Toad",
        "American Water Spaniel",
        "American Wirehair",
        "Amethystine Python",
        "Amphicoelias Fragillimus",
        "Amur Leopard",
        "Anaconda",
        "Anatolian Shepherd Dog",
        "Anchovies",
        "Andrewsarchus",
        "Angelfish",
        "Angelshark",
        "Angled Sunbeam Caterpillar",
        "Anglerfish",
        "Angora Ferret",
        "Angora Goat",
        "Anhinga",
        "Anna’s Hummingbird",
        "Anole Lizard",
        "Anomalocaris",
        "Ant",
        "Antarctic Scale Worm",
        "Anteater",
        "Antelope",
        "Anteosaurus",
        "Antiguan Racer Snake",
        "Aoudad Sheep",
        "Ape",
        "Apennine Wolf",
        "Appenzeller Dog",
        "Apple Head Chihuahua",
        "Apple Moth",
        "Arabian Cobra",
        "Arabian Wolf",
        "Arafura File Snake",
        "Arambourgiania",
        "Arapaima",
        "Archaeoindris",
        "Archaeopteryx",
        "Archaeotherium",
        "Archelon Turtle",
        "Archerfish",
        "Arctic Char",
        "Arctic Fox",
        "Arctic Hare",
        "Arctic Wolf",
        "Arctodus",
        "Arctotherium",
        "Argentavis Magnificens",
        "Argentine Black and White Tegu",
        "Argentine Horned Frog",
        "Argentinosaurus",
        "Arizona Bark Scorpion",
        "Arizona Black Rattlesnake",
        "Arizona Blonde Tarantula",
        "Arizona Coral Snake",
        "Armadillo",
        "Armadillo Lizard",
        "Armenian Gampr",
        "Armored Catfish",
        "Armyworm",
        "Arsinoitherium",
        "Arthropleura",
        "Aruba Rattlesnake",
        "Ashy Mining Bee",
        "Asian Arowana",
        " Arowana",
        "Asian Carp",
        "Asian Cockroach",
        "Asian Elephant",
        "Asian Giant Hornet",
        "Asian Lady Beetle",
        "Asian Longhorn Beetle",
        "Asian Palm Civet",
        "Asian Vine Snake",
        "Asian Water Monitor",
        "Asiatic Black Bear",
        "Asp",
        "Assassin Bug",
        "Assassin Snail",
        "Atlantic Cod",
        "Atlantic Salmon",
        "Atlantic Sturgeon",
        "Atlas Beetle",
        "Atlas Moth",
        "Aurochs",
        "Aussiedoodle",
        "Aussiedor",
        "Aussiepom",
        "Australian Bulldog",
        "Australian Cattle Dog",
        "Australian Cockroach",
        "Australian Firehawk",
        "Australian Flathead Perch",
        "Australian Gecko",
        "Australian Kelpie Dog",
        "Australian Labradoodle",
        "Australian Mist",
        "Australian Retriever",
        "Australian Shepherd",
        "Australian Shepherd Mix",
        "Australian Terrier",
        "Australopithecus",
        "Australorp Chicken",
        "Avocet",
        "Axanthic Ball Python",
        "Axolotl",
        "Ayam Cemani",
        "Aye-aye",
        "Azawakh"
    )
    var B = setOf(
        "Babirusa",
        "Baboon",
        "Bactrian Camel",
        "Badger",
        "Bagle – Basset Hound Mix",
        "Bagworm Moth",
        "Bagworm Moth Caterpillar",
        "Baird’s Rat Snake",
        "Bald Eagle",
        "Baleen Whale",
        "Balinese",
        "Balkan Lynx",
        "Ball Python",
        "Bamboo Rat",
        "Bamboo Shark",
        "Bamboo Worms",
        "Banana Ball Python",
        "Banana Cinnamon Ball Python",
        "Banana Spider",
        "Banded Krait",
        "Banded Palm Civet",
        "Banded Water Snake",
        "Bandicoot",
        "Banjo Catfish",
        "Barb",
        "Barbet",
        "Barbut’s Cuckoo Bumblebee",
        "Barinasuchus",
        "Bark Beetle",
        "Bark Scorpion",
        "Barn Owl",
        "Barn Spider",
        "Barn Swallow",
        "Barnacle",
        "Barnevelder",
        "Barosaurus",
        "Barracuda",
        "Barramundi Fish",
        "Barred Owl",
        "Barreleye Fish",
        "Barylambda",
        "Basenji Dog",
        "Basenji Mix",
        "Basilisk Lizard",
        "Basilosaurus",
        "Basking Shark",
        "Bass",
        "Bassador",
        "Basset Fauve de Bretagne",
        "Basset Hound",
        "Bassetoodle",
        "Bat",
        "Bat-Eared Fox",
        "Batfish",
        "Bavarian Mountain Hound",
        "Baya",
        "Bea-Tzu",
        "Beabull",
        "Beagador",
        "Beagle",
        "Beagle Mix",
        "Beagle Shepherd",
        "Beaglier",
        "Beago",
        "Bear",
        "Bearded Collie",
        "Bearded Dragon",
        "Bearded Fireworm",
        "Bearded Vulture",
        "Beaski",
        "Beauceron",
        "Beauty rat snake",
        "Beaver",
        "Bed Bugs",
        "Bedlington Terrier",
        "Bee",
        "Bee-Eater",
        "Beefalo",
        "Beetle",
        "Beewolf wasp",
        "Belgian Canary",
        "Belgian Laekenois",
        "Belgian Malinois",
        "Belgian Malinois Mix",
        "Belgian Sheepdog",
        "Belgian Shepherd",
        "Belgian Tervuren",
        "Belted Kingfisher",
        "Beluga Sturgeon",
        "Beluga Sturgeon",
        "Bengal Tiger",
        "Bergamasco",
        "Berger Blanc Suisse",
        "Berger Picard",
        "Bernedoodle",
        "Bernese Mountain Dog",
        "Bernese Mountain Dog Mix",
        "Bernese Shepherd",
        "Betta Fish",
        "Bhutan Takin",
        "Bichir",
        "Bichon Frise",
        "Bichpoo",
        "Biewer Terrier",
        "Bigfin Reef Squid",
        "Bighorn Sheep",
        "Bilby",
        "Binturong",
        "Bird",
        "Bird Of Paradise",
        "Bird Snake",
        "Birman",
        "Biscuit Beetle",
        "Bismarck Ringed Python",
        "Bison",
        "Black And Tan Coonhound",
        "Black and White Warbler",
        "Black Aphids",
        "Black Bass",
        "Black Crappie",
        "Black Dragon Lizard",
        "Black German Shepherd",
        "Black Mamba",
        "Black Marlin",
        "Black Mouth Cur",
        "Black Pastel Ball Python",
        "Black Rat Snake",
        "Black Rhinoceros",
        "Black Russian Terrier",
        "Black Sea Bass",
        "Black Swallowtail Caterpillar",
        "Black Tarantula",
        "Black Throat Monitor",
        "Black Wasp",
        "Black Widow Spider",
        "Black Witch Moth",
        "Black-Bellied Whistling Duck",
        "Black-Capped Chickadee",
        "Black-Footed Ferret",
        "Black-headed python",
        "Black-Tailed Rattlesnake",
        "Blackburnian Warbler",
        "Blackfin Tuna",
        "Blacknose Shark",
        "Blackpoll Warbler",
        "Blacktip Reef Shark",
        "Blacktip Shark",
        "Bladefin Basslet",
        "Blanket Octopus",
        "Blind Snake",
        "Blister Beetle",
        "Blister Beetle",
        "Blobfish",
        "Blood Python",
        "Bloodhound",
        "Blowfly",
        "Blue Andalusian",
        "Blue Belly Lizard",
        "Blue Catfish",
        "Blue Death Feigning Beetle",
        "Blue Dragon Sea Slug",
        "Blue Eyed Pleco",
        "Blue German Shepherd",
        "Blue Gray Gnatcatcher",
        "Blue grosbeak",
        "Blue Iguana",
        "Blue Jay",
        "Blue Lacy Dog",
        "Blue Nose Pit Bull",
        "Blue Picardy Spaniel",
        "Blue Racer",
        "Blue Shark",
        "Blue Tanager (Blue-Grey Tanager)",
        "Blue Tang",
        "Blue Tit",
        "Blue Whale",
        "Blue-Ringed Octopus",
        "Bluefin Tuna",
        "Bluefish",
        "Bluegill",
        "Bluetick Coonhound",
        "Boas",
        "Bobcat",
        "Bobolink",
        "Boelen’s python",
        "Boer Goat",
        "Boerboel",
        "Boggle",
        "Boglen Terrier",
        "Boiga",
        "Bolivian Anaconda",
        "Bolognese Dog",
        "Bombardier Beetle",
        "Bombay",
        "Bonefish",
        "Bongo",
        "Bonito Fish",
        "Bonnethead Shark",
        "Bonobo",
        "Booby",
        "Boomslang",
        "Booted Bantam",
        "Borador",
        "Border Collie",
        "Border Collie Mix",
        "Border Terrier",
        "Bordoodle",
        "Borkie",
        "Bornean Orangutan",
        "Borneo Elephant",
        "Boskimo",
        "Boston Terrier",
        "Bottlenose Dolphin",
        "Bouvier Des Flandres",
        "Bowfin",
        "Bowhead Whale",
        "Box Jellyfish",
        "Box Tree Moth",
        "Box Turtle",
        "Box-Headed Blood Bee",
        "Boxachi",
        "Boxador",
        "Boxer Dog",
        "Boxer Mix",
        "Boxerdoodle",
        "Boxfish",
        "Boxsky",
        "Boxweiler",
        "Bracco Italiano",
        "Brachiosaurus"
    )
    var C = setOf(
        "Cabbage Moth",
        "Cactus Moth",
        "Cactus Mouse",
        "Cactus Wren",
        "Caecilian",
        "Caiman",
        "Caiman Lizard",
        "Cairn Terrier",
        "California Condor",
        "California Kingsnake",
        "California Tarantula",
        "Camel",
        "Camel Cricket",
        "Camel Spider",
        "Campine Chicken",
        "Canaan Dog",
        "Canada Lynx",
        "Canada Warbler",
        "Canadian Eskimo Dog",
        "Canadian Horse",
        "Cane Corso",
        "Cane Rat",
        "Cane Spider",
        "Cantil",
        "Canvasback",
        "Cape Lion",
        "Capuchin",
        "Capybara",
        "Caracal",
        "Cardinal",
        "Caribbean Reef Shark",
        "Caribou",
        "Carolina Dog",
        "Carolina Parakeet",
        "Carp",
        "Carpenter Ant",
        "Carpet Beetle",
        "Carpet Python",
        "Carpet Viper",
        "Carrion Beetle",
        "Cascabel",
        "Cashmere Goat",
        "Cassowary",
        "Cat",
        "Cat Snake",
        "Cat-Eyed Snake",
        "Cat-Faced Spider",
        "Catahoula Bulldog",
        "Catahoula Leopard",
        "Catalan Sheepdog",
        "Caterpillar",
        "Catfish",
        "Caucasian Mountain Dog",
        "Caucasian Shepherd",
        "Cava Tzu",
        "Cavador",
        "Cavalier King Charles Spaniel",
        "Cavapoo",
        "Cave Bear",
        "Cave Lion",
        "Cecropia Moth",
        "Cedar Waxwing",
        "Centipede",
        "Central Ranges Taipan",
        "Cephalaspis",
        "Ceratopsian",
        "Ceratosaurus",
        "Cervalces latifrons",
        "Cesky Fousek",
        "Cesky Terrier",
        "Chain Pickerel",
        "Chameleon",
        "Chamois",
        "Chartreux",
        "Cheagle",
        "Checkered Garter Snake",
        "Cheetah",
        "Chesapeake Bay Retriever",
        "Chestnut-Sided Warbler",
        "Chi Chi",
        "Chickadee",
        "Chicken",
        "Chicken Snake",
        "Chigger",
        "Chihuahua",
        "Chihuahua Mix",
        "Children’s python",
        "Chilean Recluse Spider",
        "Chilean Rose Tarantula",
        "Chilesaurus",
        "Chimaera",
        "Chimpanzee",
        "Chinchilla",
        "Chinese Alligator",
        "Chinese Cobra",
        "Chinese Crested Dog",
        "Chinese Geese",
        "Chinese Paddlefish",
        "Chinese Shar-Pei",
        "Chinese Water Deer",
        "Chinook",
        "Chinook Salmon",
        "Chinstrap Penguin",
        "Chipit",
        "Chipmunk",
        "Chipoo",
        "Chipping Sparrow",
        "Chiton",
        "Chiweenie",
        "Chorkie",
        "Chow Chow",
        "Chow Pom",
        "Chow Shepherd",
        "Christmas Beetle",
        "Christmas Island Red Crab",
        "Chromodoris Willani",
        "Chusky",
        "Cicada",
        "Cichlid",
        "Cinereous Vulture",
        "Cinnamon Ball Python",
        "Cinnamon Bear",
        "Cinnamon Ferret",
        "Clark’s Grebe",
        "Clearnose Skate",
        "Click Beetle",
        "Clock Spider",
        "Clothes Moth",
        "Clouded Leopard",
        "Clownfish",
        "Clumber Spaniel",
        "Coachwhip Snake",
        "Coastal Carpet Python",
        "Coastal Taipan",
        "Coati",
        "Cobalt Blue Tarantula",
        "Cobia Fish",
        "Cobra",
        "Cochin Chicken",
        "Cockalier",
        "Cockapoo",
        "Cockatiel",
        "Cockatoo",
        "Cocker Spaniel",
        "Cockle",
        "Cockroach",
        "Coconut Crab",
        "Codfish",
        "Codling Moth",
        "Coelacanth",
        "Collared Peccary",
        "Collett’s Snake",
        "Collie",
        "Collie Mix",
        "Colossal Squid",
        "Comb Jellyfish",
        "Comb-crested Jacana",
        "Comet Moth",
        "Comfort Retriever",
        "Common Buzzard",
        "Common Carp",
        "Common European Adder",
        "Common Frog",
        "Common Furniture Beetle",
        "Common Goldeneye",
        "Common Grackle",
        "Common Green Magpie",
        "Common House Spider",
        "Common Loon",
        "Common Raven",
        "Common Spotted Cuscus",
        "Common Toad",
        "Common Yellowthroat",
        "Compsognathus",
        "Cone Snail",
        "Conger Eel",
        "Congo Snake",
        "Conure",
        "Cookiecutter Shark",
        "Cooper’s Hawk",
        "Copperhead",
        "Coral",
        "Coral Snake",
        "Corella",
        "Corgidor",
        "Corgipoo",
        "Corkie",
        "Corman Shepherd",
        "Cormorant",
        "Corn Earworm",
        "Corn Rex Cat",
        "Corn Snake",
        "Cory Catfish",
        "Coryphodon",
        "Cosmic Caterpillar",
        "Costa’s Hummingbird",
        "Coton de Tulear",
        "Cotton-top Tamarin",
        "Cottonmouth",
        "Coues Deer",
        "Cougar",
        "Cow",
        "Cow Reticulated Python",
        "Coyote",
        "Crab",
        "Crab Spider",
        "Crab-Eating Fox",
        "Crab-Eating Macaque",
        "Crabeater Seal",
        "Crane",
        "Crappie Fish",
        "Crayfish",
        "Crested Gecko",
        "Crested Penguin",
        "Cricket",
        "Croatian Sheepdog",
        "Crocodile",
        "Crocodile Monitor",
        "Crocodile Shark",
        "Crocodylomorph",
        "Cross Fox",
        "Cross River Gorilla",
        "Crow",
        "Crucian Carp",
        "Cryolophosaurus",
        "Cuban Boa",
        "Cuban Cockroach",
        "Cubera Snapper",
        "Cuckoo",
        "Cucumber Beetle",
        "Curly Coated Retriever",
        "Curly Hair Tarantula",
        "Cuttlefish",
        "Czechoslovakian Wolfdog"
    )
    var D = setOf(
        "Dachsador",
        "Dachshund",
        "Dachshund Mix",
        "Daeodon",
        "Dalmadoodle",
        "Dalmador",
        "Dalmatian",
        "Damselfish",
        "Dandie Dinmont Terrier",
        "Daniff",
        "Danios",
        "Danish Swedish Farmdog",
        "Dapple Dachshund",
        "Dark-Eyed Junco",
        "Dark-Eyed Junco",
        "Darkling Beetle",
        "Darwin’s fox",
        "Darwin’s Frog",
        "Daug",
        "De Brazza’s Monkey",
        "De Kay’s Brown Snake",
        "Death Adder",
        "Death’s Head Cockroach",
        "Deathwatch Beetle",
        "Decorator Crab",
        "Deer",
        "Deer Head Chihuahua",
        "Deer Mouse",
        "Deer Tick",
        "Deinocheirus",
        "Deinosuchus",
        "Desert Ghost Ball Python",
        "Desert Kingsnake",
        "Desert Locust",
        "Desert Rain Frog",
        "Desert Tortoise",
        "Desert Wolf",
        "Desmostylus",
        "Deutsche Bracke",
        "Devil’s Coach Horse Beetle",
        "Devon Rex",
        "Dhole",
        "Diamond Python",
        "Diamondback Moth",
        "Dickcissel",
        "Dickinsonia",
        "Dik-Dik",
        "Dilophosaurus",
        "Dimetrodon",
        "Diminutive Woodrat",
        "Dingo",
        "Dinocrocuta",
        "Dinofelis",
        "Dinopithecus",
        "Dinosaur Shrimp",
        "Dinosaurs",
        "Diplodocus",
        "Diprotodon",
        "Dire Wolf",
        "Disco Clam",
        "Discus",
        "Diving Bell Spider",
        "Diving Duck",
        "Doberman Pinscher",
        "Dobsonfly",
        "Dodo",
        "Doedicurus",
        "Dog",
        "Dog Tick",
        "Dogo Argentino",
        "Dogue De Bordeaux",
        "Dolphin",
        "Donkey",
        "Dorgi",
        "Dorkie",
        "Dorking Chicken",
        "Dormouse",
        "Double Doodle",
        "Douc",
        "Downy Woodpecker",
        "Doxiepoo",
        "Doxle",
        "Draco Volans Lizard",
        "Dragon Eel",
        "Dragon Snake",
        "Dragonfish",
        "Dragonfly",
        "Dreadnoughtus",
        "Drever",
        "Dried Fruit Moth",
        "Drum Fish",
        "Dubia Cockroach",
        "Duck",
        "Dugong",
        "Dumeril’s Boa",
        "Dung Beetle",
        "Dungeness Crab",
        "Dunker",
        "Dunkleosteus",
        "Dunnock",
        "Dusky Dolphin",
        "Dusky Shark",
        "Dutch Rabbit",
        "Dutch Shepherd",
        "Dwarf Boa",
        "Dwarf Crocodile",
        "Dwarf Gourami",
        "Dwarf Hamster"
    )
    var E = setOf(
        "Eagle",
        "Eagle Ray",
        "Eared Grebe",
        "Earless Monitor Lizard",
        "Earthworm",
        "Earwig",
        "East Siberian Laika",
        "Eastern Barred Bandicoot",
        "Eastern Bluebird",
        "Eastern Box Turtle",
        "Eastern Brown Snake",
        "Eastern Chipmunk",
        "Eastern Coral Snake",
        "Eastern Cottontail",
        "Eastern Diamondback Rattlesnake",
        "Eastern Dobsonfly",
        "Eastern Fence Lizard",
        "Eastern Glass Lizard",
        "Eastern Gorilla",
        "Eastern Gray Squirrel",
        "Eastern Green Mamba",
        "Eastern Hognose Snake",
        "Eastern Indigo Snake",
        "Eastern Kingbird",
        "Eastern Lowland Gorilla",
        "Eastern Meadowlark",
        "Eastern Phoebe",
        "Eastern Racer",
        "Eastern Rat Snake",
        "Eastern Tiger Snake",
        "Eastern Turkey",
        "Eastern Woodrat",
        "Echidna",
        "Eclectus Parrot",
        "Edible Frog",
        "Eel",
        "Eel catfish",
        "Eelpout",
        "Egret",
        "Egyptian Cobra",
        "Egyptian Goose",
        "Egyptian Mau",
        "Egyptian Tortoise",
        "Egyptian Vulture",
        "Eider",
        "Eland",
        "Elasmosaurus",
        "Elasmotherium",
        "Electric Catfish",
        "Electric Eel",
        "Elegant Tern",
        "Elephant",
        "Elephant Beetle",
        "Elephant Bird",
        "Elephant Fish",
        "Elephant Seal",
        "Elephant Shrew",
        "Elf Owl",
        "Elk",
        "Ember Tetra",
        "Embolotherium",
        "Emerald Toucanet",
        "Emerald Tree Boa",
        "Emerald Tree Monitor",
        "Emperor Angelfish",
        "Emperor Goose",
        "Emperor Penguin",
        "Emperor Tamarin",
        "Emu",
        "Enchi Ball Python",
        "English Angora Rabbit",
        "English Bulldog",
        "English Cocker Spaniel",
        "English Cream Golden Retriever",
        "English Crested Guinea Pig",
        "English Foxhound",
        "English Longhorn Cattle",
        "English Pointer",
        "English Setter",
        "English Shepherd",
        "English Springer Spaniel",
        "English Toy Terrier",
        "Entlebucher Mountain Dog",
        "Epagneul Pont Audemer",
        "Epicyon haydeni",
        "Epidexipteryx",
        "Equatorial Spitting Cobra",
        "Equus giganteus",
        "Ermine",
        "Eryops",
        "Escolar",
        "Eskimo Dog",
        "Eskipoo",
        "Estrela Mountain Dog",
        "Euoplocephalus",
        "Eurasian Beaver",
        "Eurasian Bullfinch",
        "Eurasian Collared Dove",
        "Eurasian Eagle-owl",
        "Eurasian Jay",
        "Eurasian Lynx",
        "Eurasian Nuthatch",
        "Eurasian Wolf",
        "Eurasier",
        "European Bee-Eater",
        "European Corn Borer",
        "European Goldfinch",
        "European Polecat",
        "European Robin",
        "European Starling",
        "European Wildcat",
        "Eurypterus",
        "Evening Bat",
        "Evening Grosbeak",
        "Executioner Wasp",
        "Eyelash Viper"
    )
    var F = setOf(
        "Fairy-Wren",
        "Falcon",
        "Fallow deer",
        "False Cobra",
        "False coral snake",
        "False Killer Whale",
        "False Water Cobra",
        "False Widow Spider",
        "Fancy Mouse",
        "Fangtooth",
        "Feather Star",
        "Feist",
        "Fennec Fox",
        "Fer-de-lance Snake",
        "Ferret",
        "Ferruginous Hawk",
        "Fiddler Crab",
        "Field Cuckoo Bumblebee",
        "Field Spaniel",
        "Fierce Snake",
        "Figeater Beetle",
        "Fila Brasileiro",
        "Fin Whale",
        "Finch",
        "Finnish Lapphund",
        "Finnish Spitz",
        "Fire Ball Python",
        "Fire Eel",
        "Fire Salamander",
        "Fire-Bellied Toad",
        "Firefly",
        "Firefly Ball Python",
        "Fish",
        "Fisher",
        "Fishing Cat",
        "Flamingo",
        "Flat-Coated Retriever",
        "Flathead Catfish",
        "Flea",
        "Flea Beetle",
        "Fleckvieh Cattle",
        "Florida Gar",
        "Florida Mouse",
        "Florida Panther",
        "Florida Woods Cockroach",
        "Flounder",
        "Flounder Fish",
        "Flour Beetle",
        "Flowerhorn Fish",
        "Fluke Fish",
        "Fly",
        "Flycatcher",
        "Flying Fish",
        "Flying Lemur",
        "Flying Snake",
        "Flying Squirrel",
        "Football Fish",
        "Forest Cobra",
        "Forest Cuckoo Bumblebee",
        "Formosan Mountain Dog",
        "Fossa",
        "Fox",
        "Fox Snakes",
        "Fox Squirrel",
        "Fox Terrier",
        "Freeway Ball Python",
        "French Bulldog",
        "French Bulldog Mix",
        "French Lop",
        "Frenchton",
        "Frengle",
        "Freshwater Crocodile",
        "Freshwater Drum",
        "Freshwater Eel",
        "Freshwater Jellyfish",
        "Freshwater Sunfish",
        "Frigatebird",
        "Frilled Lizard",
        "Frilled Shark",
        "Fritillary Butterfly",
        "Frizzle Chicken",
        "Frog",
        "Frogfish",
        "Frug",
        "Fruit Bat",
        "Fruit Fly",
        "Fulvous Whistling Duck",
        "Fur Seal",
        "Furrow Bee"
    )
    var G = setOf(
        "Gaboon Viper",
        "Gadwall",
        "Galapagos Penguin",
        "Galapagos Shark",
        "Galapagos Tortoise",
        "Gar",
        "Garden Eel",
        "Garden Spider",
        "Gargoyle Gecko",
        "Garter Snake",
        "Gastornis",
        "Gazelle",
        "Gecko",
        "Genet",
        "Gentoo Penguin",
        "Geoffroys Tamarin",
        "Gerberian Shepsky",
        "Gerbil",
        "German Cockroach",
        "German Longhaired Pointer",
        "German Pinscher",
        "German Shepherd Guide",
        "German Shepherd Mix",
        "German Sheppit",
        "German Sheprador",
        "German Shorthaired Pointer",
        "German Spitz",
        "German Wirehaired Pointer",
        "Gharial",
        "Ghost Catfish",
        "Ghost Crab",
        "Giant African Land Snail",
        "Giant Armadillo",
        "Giant Beaver",
        "Giant Clam",
        "Giant Desert Centipede",
        "Giant Golden Mole",
        "Giant House Spider",
        "Giant Isopod",
        "Giant Leopard Moth",
        "Giant Panda Bear",
        "Giant Salamander",
        "Giant Schnauzer",
        "Giant Schnoodle",
        "Giant Siphonophore",
        "Giant Trevally",
        "Giant Weta",
        "Giant Wood Moth",
        "Gibbon",
        "Gigantopithecus",
        "Gila Monster",
        "Giraffe",
        "Glass Frog",
        "Glass Lizard",
        "Glechon",
        "Glen Of Imaal Terrier",
        "Glowworm",
        "Gnat",
        "Goat",
        "Goberian",
        "Goblin Shark",
        "Goby Fish",
        "Goldador",
        "Goldcrest",
        "Golden Dox",
        "Golden Eagle",
        "Golden Irish",
        "Golden Jackal",
        "Golden Lancehead",
        "Golden Lion Tamarin",
        "Golden Masked Owl",
        "Golden Mole",
        "Golden Newfie",
        "Golden Oriole",
        "Golden Pyrenees",
        "Golden Retriever",
        "Golden Retriever Mix",
        "Golden Saint",
        "Golden Shepherd",
        "Golden Shiner",
        "Golden Tortoise Beetle",
        "Golden Trout",
        "Golden-Crowned Flying Fox",
        "Golden-Crowned Kinglet",
        "Goldendoodle",
        "Goldfish",
        "Goliath Beetle",
        "Goliath Frog",
        "Goliath Grouper",
        "Goliath Tigerfish",
        "Gollie",
        "Gomphotherium",
        "Goonch Catfish",
        "Goose",
        "Gooty Sapphire Tarantula",
        "Gopher",
        "Gopher Tortoise",
        "Goral",
        "Gordon Setter",
        "Gorgosaurus",
        "Gorilla",
        "Goshawk",
        "Gouldian Finch",
        "Gourami",
        "Grapevine Beetle",
        "Grass Carp",
        "Grass Snake",
        "Grass Spider",
        "Grasshopper",
        "Grasshopper Mouse",
        "Gray Catbird",
        "Gray Fox",
        "Gray Tree Frog",
        "Great Blue Heron",
        "Great Crested Flycatcher",
        "Great Dane",
        "Great Dane Mix",
        "Great Danoodle",
        "Great Egret",
        "Great Hammerhead Shark",
        "Great Kiskadee",
        "Great Plains Rat Snake",
        "Great Potoo Bird",
        "Great Pyrenees",
        "Great Pyrenees Mix",
        "Great White Shark",
        "Greater Swiss Mountain Dog",
        "Grebe",
        "Green Anaconda",
        "Green Anole",
        "Green Aphids",
        "Green Bee-Eater",
        "Green Bottle Blue Tarantula",
        "Green Frog",
        "Green Heron",
        "Green June Beetle",
        "Green Mamba",
        "Green Rat Snake",
        "Green Snake",
        "Green Sunfish",
        "Green Tree Frog",
        "Green Tree Python",
        "Greenland Dog",
        "Greenland Shark",
        "Grey Heron",
        "Grey Mouse Lemur",
        "Grey Reef Shark",
        "Grey Seal",
        "Greyhound",
        "Griffon Vulture",
        "Griffonshire",
        "Grizzly Bear",
        "Groenendael",
        "Ground Snake",
        "Ground Squirrel",
        "Groundhog",
        "Groundhog Tick",
        "Grouper",
        "Grouse",
        "Grunion",
        "Guadalupe Bass",
        "Guinea Fowl",
        "Guinea Pig",
        "Gulper Catfish",
        "Gulper Eel",
        "Guppy",
        "Gypsy Cuckoo Bumblebee",
        "Gypsy Moth"
    )
    var H = setOf(
        "Haast’s Eagle",
        "Habu Snake",
        "Haddock",
        "Hagfish",
        "Haikouichthys",
        "Hainosaurus",
        "Hairy Frogfish",
        "Hairy Woodpecker",
        "Hairy-footed Flower Bee",
        "Halibut",
        "Hallucigenia",
        "Hamburg Chicken",
        "Hammerhead Shark",
        "Hammerhead Worm",
        "Hammond’s flycatcher",
        "Hamster",
        "Harbor Porpoise",
        "Harbor Seal",
        "Hardhead Catfish",
        "Hare",
        "Harlequin Coral Snake",
        "Harlequin Rabbit",
        "Harp Seal",
        "Harpy Eagle",
        "Harrier",
        "Harris’s Hawk",
        "Hartebeest",
        "Hatzegopteryx",
        "Havamalt",
        "Havanese",
        "Havapoo",
        "Havashire",
        "Havashu",
        "Hawaiian Crow",
        "Hawk",
        "Hawk Moth Caterpillar",
        "Hedgehog",
        "Helicoprion",
        "Hellbender",
        "Hepatic Tanager",
        "Hercules Beetle",
        "Hercules Moth",
        "Hermit Crab",
        "Heron",
        "Herrerasaurus",
        "Herring",
        "Herring Gull",
        "Highland Cattle",
        "Himalayan",
        "Hippopotamus",
        "Hoary Bat",
        "Hobo Spider",
        "Hognose snake",
        "Hokkaido",
        "Holy Cross Frog",
        "Honduran White Bat",
        "Honey Badger",
        "Honey Bee",
        "Honey Buzzard",
        "Hooded Oriole",
        "Hooded Seal",
        "Hook-Nosed Sea Snake",
        "Hoopoe",
        "Horgi",
        "Horn Shark",
        "Hornbill",
        "Horned Adder",
        "Horned Beetle",
        "Horned Grebe",
        "Horned Lizard",
        "Horned Viper",
        "Hornet",
        "Horse",
        "Horse Mackerel",
        "Horsefly",
        "Horseshoe Crab",
        "Houdan Chicken",
        "House Finch",
        "House Sparrow",
        "House wren",
        "Housefly",
        "Hovasaurus",
        "Hovawart",
        "Howler Monkey",
        "Human",
        "Humboldt Penguin",
        "Humboldt Squid",
        "Hummingbird",
        "Hummingbird Hawk-Moth",
        "Humpback Whale",
        "Huntaway",
        "Huntsman Spider",
        "Huskador",
        "Huskita",
        "Husky",
        "Husky Jack",
        "Huskydoodle",
        "Hyacinth Macaw",
        "Hyaenodon",
        "Hyena"
    )
    var I = setOf(
        "Ibex",
        "Ibis",
        "Ibizan Hound",
        "Icadyptes",
        "Icelandic Sheepdog",
        "Ichthyosaurus",
        "Ichthyostega",
        "Iguana",
        "Iguanodon",
        "IMG Boa Constrictor",
        "Immortal Jellyfish",
        "Impala",
        "Imperial Moth",
        "Inchworm",
        "Indian Cobra",
        "Indian Elephant",
        "Indian Giant Squirrel",
        "Indian Palm Squirrel",
        "Indian python",
        "Indian Rhinoceros",
        "Indian Star Tortoise",
        "Indianmeal Moth",
        "Indigo Snake",
        "Indochinese Tiger",
        "Indri",
        "Inland Taipan",
        "Insect",
        "Insects",
        "Io Moth",
        "Irish Doodle",
        "Irish Elk",
        "Irish Setter",
        "Irish Terrier",
        "Irish Water Spaniel",
        "Irish WolfHound",
        "Italian Greyhound",
        "Ivory-billed woodpecker",
        "Ivy Bee"
    )
    var J = setOf(
        "Jabiru",
        "Jacana",
        "Jack Crevalle",
        "Jack Russells",
        "Jack-Chi",
        "Jackabee",
        "Jackal",
        "Jackdaw",
        "Jackrabbit",
        "Jackson’s Chameleon",
        "Jagdterrier",
        "Jaguar",
        "Jaguarundi Cat",
        "Jamaican Boa",
        "Jamaican Iguana",
        "Japanese Bantam Chicken",
        "Japanese Beetle",
        "Japanese Chin",
        "Japanese Macaque",
        "Japanese rat snake",
        "Japanese Spitz",
        "Japanese Squirrel",
        "Japanese Terrier",
        "Javan Leopard",
        "Javan Rhinoceros",
        "Javanese",
        "Jellyfish",
        "Jerboa",
        "Jewel Beetle",
        "John Dory",
        "Jonah Crab",
        "Joro Spider",
        "Josephoartigasia monesi",
        "Jumping Spider",
        "Jungle Carpet Python",
        "Junglefowl"
    )
    var K = setOf(
        "Kagu",
        "Kai Ken",
        "Kakapo",
        "Kaluga Sturgeon",
        "Kamehameha Butterfly",
        "Kangal Shepherd Dog",
        "Kangaroo",
        "Kangaroo Mouse",
        "Kangaroo Rat",
        "Katydid",
        "Kaua’i ‘Ō‘ō",
        "Keagle",
        "Keel-Billed Toucan",
        "Keelback",
        "Keeshond",
        "Kelp Greenling",
        "Kentucky Warbler",
        "Kenyan Sand Boa",
        "Kermode Bear",
        "Kerry Blue Terrier",
        "Kestrel",
        "Keta Salmon",
        "Key Deer",
        "Keyhole Cichlid",
        "Khao Manee",
        "Khapra Beetle",
        "Kiang",
        "Kiko Goat",
        "Killdeer",
        "Killer Clown Ball Python",
        "Killer Whale",
        "Killifish",
        "Kinabalu Giant Red Leech",
        "Kinder Goat",
        "King Cobra",
        "King Crab",
        "King Eider",
        "King Mackerel",
        "King Penguin",
        "King Rat Snake",
        "King Salmon",
        "King Shepherd",
        "King Snake",
        "King Vulture",
        "Kingfisher",
        "Kingklip",
        "Kinkajou",
        "Kirtland’s Snake",
        "Kishu",
        "Kissing Bugs",
        "Kissing Gourami",
        "Kit Fox",
        "Kitefin Shark",
        "Kiwi",
        "Klipspringer",
        "Knifefish",
        "Knight Anole",
        "Koala",
        "Kodiak Bear",
        "Kodkod",
        "Koi Fish",
        "Kokanee Salmon",
        "Komodo Dragon",
        "Komondor",
        "Kooikerhondje",
        "Koolie",
        "Korean Jindo",
        "Kori Bustard",
        "Kouprey",
        "Kowari",
        "Krait",
        "Krill",
        "Kudu",
        "Kudzu Bug",
        "Kuvasz"
    )
    var L = setOf(
        "Labahoula",
        "Labmaraner",
        "Labout’s Fairy Wrasse",
        "Labrabull",
        "Labradane",
        "Labradoodle",
        "Labrador Retriever",
        "Labraheeler",
        "Labrottie",
        "Lace Bug",
        "Lace Monitor",
        "Ladybug",
        "Ladyfish",
        "Lagotto Romagnolo",
        "Lake Sturgeon",
        "Lake Trout",
        "Lakeland Terrier",
        "LaMancha Goat",
        "Lamprey",
        "Lancashire Heeler",
        "Lancetfish",
        "Landseer Newfoundland",
        "Lappet-faced Vulture",
        "Lapponian Herder",
        "Larder Beetle",
        "Large Munsterlander",
        "Largemouth Bass",
        "Laughing Kookaburra",
        "Lavender Albino Ball Python",
        "Lawnmower Blenny",
        "Lazarus Lizard",
        "Leaf-Tailed Gecko",
        "Leafcutter Ant",
        "Leafcutter Bee",
        "Least Flycatcher",
        "Leatherback Sea Turtle",
        "Leech",
        "Leedsichthys",
        "Leghorn Chicken",
        "Leichhardt’s Grasshopper",
        "Lemming",
        "Lemon Blast Ball Python",
        "Lemon Cuckoo Bumblebee",
        "Lemon Shark",
        "Lemur",
        "Leonberger",
        "Leopard",
        "Leopard Cat",
        "Leopard Frog",
        "Leopard Gecko",
        "Leopard Lizard",
        "Leopard Seal",
        "Leopard Shark",
        "Leopard Tortoise",
        "Leptocephalus",
        "Lesser Jacana",
        "Lesser Scaup",
        "Lhasa Apso",
        "Lhasapoo",
        "Liger",
        "Limpet",
        "Lineback Cattle",
        "Linnet",
        "Lion",
        "Lion’s Mane Jellyfish",
        "Lionfish",
        "Liopleurodon",
        "Lipstick Albino Boa",
        "Little Brown Bat",
        "Little Penguin",
        "Livyatan",
        "Lizard",
        "Lizardfish",
        "Llama",
        "Loach",
        "Lobster",
        "Locust",
        "Loggerhead Shrike",
        "Lone Star Tick",
        "Long-Eared Owl",
        "Long-Haired Rottweiler",
        "Long-Tailed Tit",
        "Long-Winged Kite Spider",
        "Longfin Mako Shark",
        "Longnose Gar",
        "Lorikeet",
        "Loris",
        "Lowchen",
        "Lumpfish",
        "Luna Moth",
        "Luna Moth Caterpillar",
        "Lungfish",
        "Lurcher",
        "Lykoi Cat",
        "Lynx",
        "Lyrebird",
        "Lystrosaurus"
    )
    var M = setOf(
        "Macaque",
        "Macaroni Penguin",
        "Macaw",
        "MacGillivray’s Warbler",
        "Machaeroides",
        "Mackenzie Valley Wolf",
        "Macrauchenia",
        "Madagascar Hissing Cockroach",
        "Madagascar Jacana",
        "Madagascar Tree Boa",
        "Madora Moth",
        "Magellanic Penguin",
        "Maggot",
        "Magnolia Warbler",
        "Magpie",
        "Magyarosaurus",
        "Mahi Mahi",
        "Maiasaura",
        "Maine Coon",
        "Mal Shi",
        "Malayan Civet",
        "Malayan Krait",
        "Malayan Tiger",
        "Malchi",
        "Mallard",
        "Malteagle",
        "Maltese",
        "Maltese Mix",
        "Maltese Shih Tzu",
        "Maltipom",
        "Maltipoo",
        "Mamba",
        "Mamushi Snake",
        "Man of War Jellyfish",
        "Manatee",
        "Manchester Terrier",
        "Mandarin Rat Snake",
        "Mandrill",
        "Maned Wolf",
        "Mangrove Snake",
        "Mangrove Snapper",
        "Manta Ray",
        "Mantella Frog",
        "Marabou Stork",
        "Marans Chicken",
        "Marble Fox",
        "Maremma Sheepdog",
        "Margay",
        "Marine Iguana",
        "Marine Toad",
        "Markhor",
        "Marmoset",
        "Marmot",
        "Marsh Frog",
        "Marsican Brown Bear",
        "Masiakasaurus",
        "Masked Angelfish",
        "Masked Palm Civet",
        "Mason Bee",
        "Massasauga",
        "Mastador",
        "Mastiff",
        "Mastiff Mix",
        "Mauzer",
        "May Beetle",
        "Mayan Cichlid",
        "Meagle",
        "Mealworm Beetle",
        "Mealybug",
        "Meerkat",
        "Megalania",
        "Megalochelys",
        "Megalodon",
        "Megamouth Shark",
        "Meganeura",
        "Megatherium",
        "Meiolania",
        "Mekong Giant Catfish",
        "Merganser",
        "Mexican Alligator Lizard",
        "Mexican Black Kingsnake",
        "Mexican Eagle",
        "Mexican Fireleg Tarantula",
        "Mexican Free-Tailed Bat",
        "Mexican Mole Lizard",
        "Microraptor",
        "Midget Faded Rattlesnake",
        "Miki",
        "Milk Snake",
        "Milkfish",
        "Milkweed aphids",
        "Millipede",
        "Mini Labradoodle",
        "Mini Lop",
        "Miniature Bull Terrier",
        "Miniature Husky",
        "Miniature Pinscher",
        "Mink",
        "Minke Whale",
        "Mississippi Kite",
        "Moccasin Snake",
        "Mockingbird",
        "Modern Game Chicken",
        "Mojarra",
        "Mojave Ball Python",
        "Mojave Rattlesnake",
        "Mola mola",
        "Mole",
        "Mole Crab",
        "Mole Cricket",
        "Mole Snake",
        "Mollusk",
        "Molly",
        "Monarch Butterfly",
        "Mongoose",
        "Mongrel",
        "Monitor Lizard",
        "Monkey",
        "Monkfish",
        "Monocled Cobra",
        "Monte Iberia Eleuth",
        "Moon Jellyfish",
        "Moonglow Boa",
        "Moorhen",
        "Moose",
        "Moray Eel",
        "Morkie",
        "Morpho Butterfly",
        "Mosasaurus",
        "Moscow Watchdog",
        "Mosquito",
        "Moth",
        "Mountain Beaver",
        "Mountain Bluebird",
        "Mountain Cur",
        "Mountain Feist",
        "Mountain Gorilla",
        "Mountain Lion",
        "Mourning Dove",
        "Mourning Gecko",
        "Mourning Warbler",
        "Mouse",
        "Mouse Spider",
        "Mouse-Deer",
        "Mozambique Spitting Cobra",
        "Mud Snake",
        "Mudi",
        "Mudpuppy",
        "Mudskipper",
        "Mule",
        "Mule Deer",
        "Mulga Snake",
        "Mullet Fish",
        "Muntjac",
        "Muscovy Duck",
        "Musk Deer",
        "Muskellunge",
        "Muskox",
        "Muskrat",
        "Mussurana Snake",
        "Muttaburrasaurus",
        "Myna Bird"
    )
    var N = setOf(
        "Nabarlek",
        "Naegleria",
        "Naked Mole Rat",
        "Narwhal",
        "Natterjack",
        "Nautilus",
        "Neanderthal",
        "Neapolitan Mastiff",
        "Nebelung",
        "Needlefish",
        "Nelore Cattle",
        "Nematode",
        "Neon Tetra",
        "Neptune Grouper",
        "Netherland Dwarf Rabbit",
        "New Hampshire Red Chicken",
        "Newfoundland",
        "Newfypoo",
        "Newt",
        "Nguni Cattle",
        "Nicobar pigeon",
        "Nigerian Goat",
        "Night Adder",
        "Night Heron",
        "Night Snake",
        "Nightingale",
        "Nightjar",
        "Nile Crocodile",
        "Nile Monitor",
        "Nile Perch",
        "Nilgai",
        "No See Ums",
        "Norfolk Terrier",
        "Norrbottenspets",
        "North American Black Bear",
        "Northern Alligator Lizard",
        "Northern Bobwhite",
        "Northern Cardinal",
        "Northern Flicker",
        "Northern Fur Seal",
        "Northern Harrier",
        "Northern Inuit Dog",
        "Northern Jacana",
        "Northern Parula",
        "Northern Pintail",
        "Northern Potoo",
        "Northern Screamer",
        "Northern Water Snake",
        "Norway Rat",
        "Norwegian Buhund",
        "Norwegian Elkhound",
        "Norwegian Forest",
        "Norwegian Lundehund",
        "Norwich Terrier",
        "Nose-Horned Viper",
        "Nova Scotia Duck Tolling Retriever",
        "Nubian Goat",
        "Nudibranch",
        "Numbat",
        "Nuralagus",
        "Nurse Shark",
        "Nut Weevil",
        "Nuthatch",
        "Nutria",
        "Nyala"
    )
    var O = setOf(
        "Oak Toad",
        "Oarfish",
        "Ocean Perch",
        "Ocean Pout",
        "Ocean Whitefish",
        "Oceanic Whitetip Shark",
        "Ocellated Turkey",
        "Ocelot",
        "Octopus",
        "Oenpelli python",
        "Oilfish",
        "Okapi",
        "Old English Sheepdog",
        "Old House Borer",
        "Oleander Hawk Moth",
        "Olingo",
        "Olive Baboon",
        "Olive python",
        "Olive Sea Snake",
        "Olm",
        "Olympic Marmot",
        "Onagadori Chicken",
        "Onager",
        "Opabinia",
        "Opah",
        "Opaleye",
        "Opossum",
        "Oranda Goldfish",
        "Orange Baboon Tarantula",
        "Orange Dream Ball Python",
        "Orange Roughy",
        "Orange Spider",
        "Orange Tanager",
        "Orange-Crowned Warbler",
        "Orangutan",
        "Orb Weaver",
        "Orchard Oriole",
        "Orchid Dottyback",
        "Oregon Spotted Frog",
        "Ori-Pei",
        "Oribi",
        "Oriental Cockroach",
        "Oriental Dwarf Kingfisher",
        "Orinoco Crocodile",
        "Ornate Bichir",
        "Ornate Black-Tailed Rattlesnake",
        "Ornate Box Turtle",
        "Ornithocheirus",
        "Ornithomimus",
        "Ortolan Bunting",
        "Oscar Fish",
        "Osprey",
        "Ostracod",
        "Ostrich",
        "Otter",
        "Otterhound",
        "Ovenbird",
        "Oviraptor",
        "Owl",
        "Owl Butterfly",
        "Owlfly",
        "Ox",
        "Oxpecker",
        "Oyster",
        "Oyster Toadfish",
        "Ozark Bass"
    )
    var P = setOf(
        "Pachycephalosaurus",
        "Pacific Coast Tick",
        "Pacific Sleeper Shark",
        "Pacific Spaghetti Eel",
        "Paddlefish",
        "Pademelon",
        "Painted Bunting",
        "Painted Turtle",
        "Palaeoloxodon namadicus",
        "Palaeophis",
        "Paleoparadoxia",
        "Palm Rat",
        "Palo Verde Beetle",
        "Panda Pied Ball Python",
        "Pangolin",
        "Panther",
        "Papillon",
        "Papillon Mix",
        "Paradise Flying Snake",
        "Parakeet",
        "Parasaurolophus",
        "Parrot",
        "Parrot Snake",
        "Parrotfish",
        "Parrotlet",
        "Parson Russell Terrier",
        "Parti Schnauzer",
        "Partridge",
        "Patagonian Cavy",
        "Patagonian Mara",
        "Patagotitan",
        "Patas Monkey",
        "Patterdale Terrier",
        "Pea Puffer",
        "Peacock",
        "Peacock Bass",
        "Peacock Butterfly",
        "Peacock Spider",
        "Peagle",
        "Peekapoo",
        "Pekingese",
        "Pelagornis",
        "Pelican",
        "Pelycosaurs",
        "Pembroke Welsh Corgi",
        "Penguin",
        "Pennsylvania Wood Cockroach",
        "Peppered Moth",
        "Peppermint Angelfish",
        "Perch Fish",
        "Père David’s Deer",
        "Peregrine Falcon",
        "Peringuey’s Adder",
        "Perro De Presa Canario",
        "Persian",
        "Peruvian Guinea Pig",
        "Peruvian Inca Orchid",
        "Pesquet’s Parrot",
        "Petit Basset Griffon Vendéen",
        "Petite Goldendoodle",
        "Pharaoh Hound",
        "Pheasant",
        "Pheasant-tailed Jacana",
        "Philippine Cobra",
        "Phoenix Chicken",
        "Phorusrhacos",
        "Phytosaurs",
        "Picardy Spaniel",
        "Pictus Catfish",
        "Piebald Dachshund",
        "Pied Ball Python",
        "Pied Tamarin",
        "Pied-Billed Grebe",
        "Pig",
        "Pig-Nosed Turtle",
        "Pigeon",
        "Pika",
        "Pike Fish",
        "Pileated Woodpecker",
        "Pinacate Beetle",
        "Pine Beetle",
        "Pine Marten",
        "Pine Siskin",
        "Pine Snake",
        "Pinfish",
        "Pink Bollworm",
        "Pink Fairy Armadillo",
        "Pink Salmon",
        "Pink Toed Tarantula",
        "Pink-Necked Green Pigeon",
        "Pipe Snake",
        "Pipefish",
        "Piranha",
        "Pit Bull",
        "Pit Viper",
        "Pitador",
        "Pitsky",
        "Plains Hognose Snake",
        "Platinum Arowana",
        "Platybelodon",
        "Platypus",
        "Plesiosaur",
        "Pliosaur",
        "Plott Hound Mix",
        "Plott Hounds",
        "Plymouth Rock Chicken",
        "Pocket Beagle",
        "Pocket Pitbull",
        "Podenco Canario",
        "Pointer",
        "Pointer Mix",
        "Poison Dart Frog",
        "Polacanthus",
        "Polar Bear",
        "Polecat",
        "Polish Chicken",
        "Polish Lowland Sheepdog",
        "Polish Tatra Sheepdog",
        "Polka Dot Stingray",
        "Pollock Fish",
        "Polyphemus Moth",
        "Pomapoo",
        "Pomchi",
        "Pomeagle",
        "Pomeranian",
        "Pomeranian Mix",
        "Pompano Fish",
        "Pomsky",
        "Pond Skater",
        "Poochon",
        "Poodle",
        "Poogle",
        "Pool Frog",
        "Porbeagle Shark",
        "Porcupine",
        "Porcupinefish",
        "Portuguese Podengo",
        "Possum",
        "Potato Beetle",
        "Potoo",
        "Potoroo",
        "Powderpost Beetle",
        "Prairie Chicken",
        "Prairie Dog",
        "Prairie Rattlesnake",
        "Prawn",
        "Praying Mantis",
        "Proboscis Monkey",
        "Procoptodon",
        "Pronghorn",
        "Psittacosaurus",
        "Pteranodon",
        "Pterodactyl",
        "Pudelpointer",
        "Puertasaurus",
        "Puff Adder",
        "Pufferfish",
        "Puffin",
        "Pug",
        "Pug Mix",
        "Pugapoo",
        "Puggle",
        "Pugshire",
        "Puli",
        "Puma",
        "Pumi",
        "Pumpkin Patch Tarantula",
        "Purple Emperor Butterfly",
        "Purple Finch",
        "Purple Gallinule",
        "Purple Tarantula",
        "Purussaurus",
        "Puss Caterpillar",
        "Puss Moth",
        "Pygmy Hippopotamus",
        "Pygmy Marmoset",
        "Pygmy python",
        "Pygmy Rattlesnake",
        "Pygmy Shark",
        "Pygora Goat",
        "Pyjama Shark",
        "Pyrador",
        "Pyredoodle",
        "Pyrenean Mastiff",
        "Pyrenean Shepherd",
        "Pyrosome",
        "Python"
    )
    var Q = setOf(
        "Quagga",
        "Quahog Clam",
        "Quail",
        "Queen Snake",
        "Quetzal",
        "Quetzalcoatlus northropi",
        "Quokka",
        "Quoll"
    )
    var R = setOf(
        "Rabbit",
        "Raccoon",
        "Raccoon Dog",
        "Racer Snake",
        "Radiated Tortoise",
        "Ragamuffin",
        "Ragdoll",
        "Raggle",
        "Rainbow Boa",
        "Rainbow Grasshopper",
        "Rainbow Kribs",
        "Rainbow Shark",
        "Rat",
        "Rat Snakes",
        "Rat Terrier",
        "Rattlesnake",
        "Red Ackie Monitor",
        "Red Aphids",
        "Red Deer",
        "Red Diamondback Rattlesnake",
        "Red Drum Fish",
        "Red Finch",
        "Red Fox",
        "Red Kite",
        "Red Knee Tarantula",
        "Red Nose Pit Bull",
        "Red Panda",
        "Red Paper Wasp",
        "Red Racer Snake",
        "Red Spitting Cobra",
        "Red Squirrel",
        "Red Star Chicken",
        "Red Tail Boa",
        "Red Wolf",
        "Red-Bellied Black Snake",
        "Red-Bellied Woodpecker",
        "Red-Billed Quelea Bird",
        "Red-Eared Slider",
        "Red-Eyed Tree Frog",
        "Red-Footed Tortoise",
        "Red-handed Tamarin",
        "Red-Headed Vulture",
        "Red-Lipped Batfish",
        "Red-Shouldered Hawk",
        "Red-Tailed Cuckoo Bumblebee",
        "Red-winged blackbird",
        "Redback Spider",
        "Redbone Coonhound",
        "Redcap Chicken",
        "Redear Sunfish",
        "Redhump Eartheater",
        "Redstart",
        "Redtail Catfish",
        "Reef Shark",
        "Regal Jumping Spider",
        "Reindeer",
        "Repenomamus",
        "Reticulated python",
        "Rex Rabbit",
        "Rhamphosuchus",
        "Rhea",
        "Rhesus Macaque",
        "Rhino Beetle",
        "Rhino Viper",
        "Rhinoceros",
        "Rhode Island Red Chicken",
        "Rhodesian Ridgeback",
        "Rhombic Egg-Eater Snake",
        "Ribbon Eel",
        "Ribbon Snake",
        "Rim Rock Crowned Snake",
        "Ring-billed Gull",
        "Ringed Kingfisher",
        "Rinkhals Snake",
        "River Otter",
        "River Turtle",
        "Roadrunner",
        "Robber Flies",
        "Robin",
        "Rock Bass",
        "Rock Crab",
        "Rock Hyrax",
        "Rock Python",
        "Rockfish",
        "Rockhopper Penguin",
        "Rodents",
        "Roe Deer",
        "Roosevelt Elk",
        "Rooster",
        "Root Aphids",
        "Rose-Breasted Grosbeak",
        "Roseate Spoonbill",
        "Rosy Boa",
        "Rotterman",
        "Rottle",
        "Rottsky",
        "Rottweiler",
        "Rottweiler Mix",
        "Rough Earth Snake",
        "Rough Green Snake",
        "Rough-Legged Hawk",
        "Rove Beetle",
        "Royal Penguin",
        "Rubber Boa",
        "Ruby-Crowned Kinglet",
        "Ruby-Throated Hummingbird",
        "Ruddy Duck",
        "Ruddy Turnstone",
        "Rufous Hummingbird",
        "Russel’s Viper",
        "Russell Terrier",
        "Russian Bear Dog",
        "Russian Blue",
        "Russian Tortoise"
    )
    var S = setOf(
        "Saanen Goat",
        "Saarloos Wolfdog",
        "Saber-Toothed Tiger",
        "Sable",
        "Sable Black German Shepherd",
        "Sable Ferret",
        "Sable German Shepherd",
        "Saddleback Caterpillar",
        "Saiga",
        "Sailfish",
        "Saint Berdoodle",
        "Saint Bernard",
        "Saint Shepherd",
        "Salamander",
        "Salmon",
        "Salmon Shark",
        "Saluki",
        "Sambar",
        "Samoyed",
        "San Francisco Garter Snake",
        "Sand Cat",
        "Sand Crab",
        "Sand Dollar",
        "Sand Lizard",
        "Sand Tiger Shark",
        "Sand Viper",
        "Sandhill Crane",
        "Sandpiper",
        "Sandworm",
        "Saola",
        "Sapsali",
        "Sarcosuchus",
        "Sardines",
        "Sarkastodon",
        "Sarplaninac",
        "Sarus Crane",
        "Satanic Leaf-Tailed Gecko",
        "Saturniidae Moth",
        "Sauropoda",
        "Savanna Goat",
        "Savannah Monitor",
        "Savannah Sparrow",
        "Savu Python",
        "Saw-scaled Viper",
        "Sawfish",
        "Scale-Crested Pygmy Tyrant",
        "Scaleless Ball Python",
        "Scallops",
        "Scarab Beetle",
        "Scarlet Kingsnake",
        "Scarlet Macaw",
        "Scarlet Tanager",
        "Schapendoes",
        "Schipperke",
        "Schneagle",
        "Schnoodle",
        "Scimitar-horned Oryx",
        "Scissor-tailed Flycatcher",
        "Scorpion",
        "Scorpion Fish",
        "Scotch Collie",
        "Scottish Deerhound",
        "Scottish Fold Cat",
        "Scottish Terrier",
        "Scrotum Frog",
        "Sculpin",
        "Scutosaurus",
        "Sea Anemone",
        "Sea Bass",
        "Sea Dragon",
        "Sea Eagle",
        "Sea Lion",
        "Sea Otter",
        "Sea Roach",
        "Sea Slug",
        "Sea Snake",
        "Sea Spider",
        "Sea Squirt",
        "Sea Trout",
        "Sea Turtle",
        "Sea Urchin",
        "Seagull",
        "Seahorse",
        "Seal",
        "Sealyham Terrier",
        "Sedge Warbler",
        "Sehuencas Water Frog",
        "Sei Whale",
        "Senegal Parrot",
        "Senepol Cattle",
        "Sequined Spider",
        "Serval",
        "Seymouria",
        "Shantungosaurus",
        "Shark",
        "Sharp-Shinned Hawk",
        "Sharp-Tailed Snake",
        "Shastasaurus",
        "Sheep",
        "Sheepadoodle",
        "Sheepshead Fish",
        "Shepadoodle",
        "Shepkita",
        "Shepweiler",
        "Shetland Sheepdog",
        "Shiba Inu",
        "Shiba Inu Mix",
        "Shichi",
        "Shih Poo",
        "Shih Tzu",
        "Shih Tzu Mix",
        "Shikoku",
        "Shiloh Shepherd",
        "Shiranian",
        "Shoebill Stork",
        "Shollie",
        "Short-Eared Owl",
        "Short-Faced Bear",
        "Shortfin Mako Shark",
        "Shrew",
        "Shrimp",
        "Siamese",
        "Siberian",
        "Siberian Husky",
        "Siberian Ibex",
        "Siberian Retriever",
        "Siberian Tiger",
        "Siberpoo",
        "Sichuan Takin",
        "Sidewinder",
        "Sika Deer",
        "Silken Windhound",
        "Silkie Chicken",
        "Silky Shark",
        "Silky Terrier",
        "Silver Dollar",
        "Silver Labrador",
        "Simbakubwa",
        "Sinosauropteryx",
        "Sivatherium",
        "Sixgill shark",
        "Skate Fish",
        "Skeleton Tarantula",
        "Skink Lizard",
        "Skipjack Tuna",
        "Skua",
        "Skunk",
        "Skye Terrier",
        "Sleeper Shark",
        "Sloth",
        "Slovak Cuvac",
        "Slow Worm",
        "Slug",
        "Smallmouth Bass",
        "Smilosuchus",
        "Smokybrown Cockroach",
        "Smooth Earth Snake",
        "Smooth Fox Terrier",
        "Smooth Green Snake",
        "Smooth Hammerhead Shark",
        "Smooth Snake",
        "Snail",
        "Snailfish",
        "Snake",
        "Snapping Turtle",
        "Snook Fish",
        "Snorkie",
        "Snouted Cobra",
        "Snow Bunting",
        "Snow Crab",
        "Snow Goose",
        "Snow Leopard",
        "Snowberry Clearwing Moth",
        "Snowflake Eel",
        "Snowshoe",
        "Snowshoe Hare",
        "Snowy Owl",
        "Sockeye Salmon",
        "Soldier Beetle",
        "Somali",
        "Song Sparrow",
        "Song Thrush",
        "South China Tiger",
        "Southeastern Blueberry Bee",
        "Southern Black Racer",
        "Southern Flannel Moth",
        "Southern Hognose Snake",
        "Southern House Spider",
        "Southern Pacific Rattlesnake",
        "Spadefoot Toad",
        "Spalax",
        "Spanador",
        "Spanish Goat",
        "Spanish Mackerel",
        "Spanish Mastiff",
        "Spanish Water Dog",
        "Sparrow",
        "Sparrowhawk",
        "Speckled Kingsnake",
        "Speckled Trout",
        "Spectacled Bear",
        "Sperm Whale",
        "Sphynx",
        "Spider",
        "Spider Ball Python",
        "Spider Beetle",
        "Spider Monkey",
        "Spider Wasp",
        "Spider-Tailed Horned Viper",
        "Spinner Shark",
        "Spinone Italiano",
        "Spinosaurus",
        "Spiny bush viper",
        "Spiny Dogfish",
        "Spiny Hill Turtle",
        "Spitting Cobra",
        "Spix’s Macaw",
        "Sponge",
        "Spongy Moth",
        "Spotted Bass",
        "Spotted Gar",
        "Spotted Garden Eel",
        "Spotted Lanternfly",
        "Spotted python",
        "Spotted Skunk",
        "Springador",
        "Springbok",
        "Springerdoodle",
        "Squash Bee",
        "Squash Beetle",
        "Squid",
        "Squirrel",
        "Squirrel Monkey",
        "Squirrelfish",
        "Sri Lankan Elephant",
        "Stabyhoun",
        "Staffordshire Bull Terrier",
        "Stag Beetle",
        "Standard Schnauzer",
        "Star-nosed mole",
        "Starfish",
        "Stargazer Fish",
        "Steelhead Salmon",
        "Steller’s Sea Cow",
        "Stick Insect",
        "Stiletto Snake",
        "Stingray",
        "Stoat",
        "Stone Crab",
        "Stonechat",
        "Stonefish",
        "Stoplight Loosejaw",
        "Stork",
        "Strawberry Hermit Crab",
        "Striped Bass",
        "Striped Hyena",
        "Striped Rocket Frog",
        "Stromatolite",
        "Stupendemys",
        "Sturgeon",
        "Styracosaurus",
        "Suchomimus",
        "Suckerfish",
        "Sugar Glider",
        "Sulcata Tortoise",
        "Sultan Chicken",
        "Sumatran Elephant",
        "Sumatran Orangutan",
        "Sumatran Rhinoceros",
        "Sumatran Tiger",
        "Summer Tanager",
        "Sun Bear",
        "Sunbeam Snake",
        "Sunset Ball Python",
        "Super Pastel Ball Python",
        "Supersaurus",
        "Superworm",
        "Surgeonfish",
        "Sussex Chicken",
        "Swai Fish",
        "Swainson’s Hawk",
        "Swallow",
        "Swallowtail Butterfly",
        "Swallowtail Caterpillar",
        "Swan",
        "Swedish Elkhound",
        "Swedish Lapphund",
        "Swedish Vallhund",
        "Swordfish",
        "Syrian Hamster"
    )
    var T = setOf(
        "Taco Terrier",
        "Tailless Whip Scorpion",
        "Taimen Fish",
        "Taipan",
        "Takin",
        "Tamarin",
        "Tamaskan",
        "Tang",
        "Tangerine Leopard Gecko",
        "Tapanuli Orangutan",
        "Tapir",
        "Tarantula",
        "Tarantula Hawk",
        "Tarbosaurus",
        "Tarpon",
        "Tarsier",
        "Tasmanian Devil",
        "Tasmanian Tiger",
        "Tasmanian Tiger Snake",
        "Tawny Frogmouth",
        "Tawny Mining Bee",
        "Tawny Owl",
        "Teacup Chihuahua",
        "Teacup Maltese",
        "Teacup Miniature Horse",
        "Teacup Poodle",
        "Teddy Bear Hamster",
        "Teddy Guinea Pig",
        "Teddy Roosevelt Terrier",
        "Telescope Fish",
        "Ten-Lined June Beetle",
        "Tennessee Walking Horse",
        "Tenrec",
        "Tent Caterpillar",
        "Tentacled Snake",
        "Tenterfield Terrier",
        "Termite",
        "Terrier",
        "Terror Bird",
        "Tetra",
        "Texas Blind Snake",
        "Texas Brown Tarantula",
        "Texas Coral Snake",
        "Texas Garter Snake",
        "Texas Heeler",
        "Texas Indigo Snake",
        "Texas Night Snake",
        "Texas Rat Snake",
        "Texas Spiny Lizard",
        "Thai Ridgeback",
        "Thalassomedon",
        "Thanatosdrakon",
        "Therizinosaurus",
        "Theropod",
        "Thornback Ray",
        "Thorny Devil",
        "Thresher Shark",
        "Thrush",
        "Thylacoleo",
        "Thylacoleo carnifex",
        "Thylacosmilus",
        "Tibetan Fox",
        "Tibetan Mastiff",
        "Tibetan Spaniel",
        "Tibetan Terrier",
        "Tick",
        "Tiffany",
        "Tiger",
        "Tiger Beetle",
        "Tiger Moth",
        "Tiger Muskellunge",
        "Tiger Rattlesnake",
        "Tiger Salamander",
        "Tiger Shark",
        "Tiger snake",
        "Tiger Swallowtail",
        "Tiger Swallowtail Caterpillar",
        "Tiger Trout",
        "Tiktaalik",
        "Timber Rattlesnake",
        "Timor python",
        "Tire Track Eel",
        "Titan Beetle",
        "Titanoboa",
        "Titanosaur",
        "Toadfish",
        "Tokay Gecko",
        "Tomato Hornworm",
        "Torkie",
        "Tornjak",
        "Tortoise",
        "Tosa",
        "Toucan",
        "Towhee",
        "Toxodon",
        "Toy Fox Terrier",
        "Toy Poodle",
        "Transylvanian Hound",
        "Trapdoor spider",
        "Tree Cricket",
        "Tree Frog",
        "Tree Kangaroo",
        "Tree Snake",
        "Tree swallow",
        "Tree Viper",
        "Treecreeper",
        "Treehopper",
        "Treeing Tennessee Brindle",
        "Treeing Walker Coonhound",
        "Triggerfish",
        "Troodon",
        "Tropicbird",
        "Trout",
        "Tsetse Fly",
        "Tuatara",
        "Tufted Coquette",
        "Tufted Titmouse",
        "Tully Monster",
        "Tuna",
        "Tundra Swan",
        "Turaco",
        "Turkey",
        "Turkey Vulture",
        "Turkish Angora",
        "Turnspit",
        "Turtle Frog",
        "Turtles",
        "Tusoteuthis",
        "Tussock Moth Caterpillar",
        "Twig Snake",
        "Tylosaurus",
        "Tyrannosaurus Rex"
    )
    var U = setOf(
        "Uakari",
        "Uaru Cichlid",
        "Uguisu",
        "Uinta Ground Squirrel",
        "Uintatherium",
        "Ulysses Butterfly",
        "Umbrellabird",
        "Unau",
        "Underwing Moth",
        "Upland Sandpiper",
        "Ural owl",
        "Urechis unicinctus",
        "Urial",
        "Uromastyx",
        "Urutu Snake",
        "Utonagan"
    )
    var V = setOf(
        "Valley Bulldog",
        "Vampire Bat",
        "Vampire Crab",
        "Vampire Squid",
        "Vaquita",
        "Veery",
        "Vegavis",
        "Velociraptor",
        "Venus Flytrap",
        "Vermilion Flycatcher",
        "Vervet Monkey",
        "Vestal Cuckoo Bumblebee",
        "Vicuña",
        "Vine Snake",
        "Vinegaroon",
        "Viper",
        "Viper Boa",
        "Viper Shark",
        "Viperfish",
        "Virgin Islands Dwarf Gecko",
        "Vizsla",
        "Volcano Snail",
        "Vole",
        "Volpino Italiano",
        "Vulture"
    )
    var W = setOf(
        "Wahoo Fish",
        "Waimanu",
        "Walking Catfish",
        "Wallaby",
        "Walleye Fish",
        "Walrus",
        "Wandering Albatross",
        "Warbler",
        "Warthog",
        "Wasp",
        "Water Beetle",
        "Water Buffalo",
        "Water Bug",
        "Water Dragon",
        "Water Vole",
        "Waterbuck",
        "Wattled Jacana",
        "Wax Moth",
        "Weasel",
        "Weaver Bird",
        "Weimaraner",
        "Weimardoodle",
        "Wels Catfish",
        "Welsh Black Cattle",
        "Welsh Corgi",
        "Welsh Springer Spaniel",
        "Welsh Terrier",
        "West Highland Terrier",
        "West Siberian Laika",
        "Western Blacklegged Tick",
        "Western Blind Snake",
        "Western Diamondback Rattlesnake",
        "Western Gorilla",
        "Western Green Mamba",
        "Western Hognose Snake",
        "Western Kingbird",
        "Western Lowland Gorilla",
        "Western Rat Snake",
        "Western Rattlesnake",
        "Western Tanager",
        "Westiepoo",
        "Whale Shark",
        "Wheaten Terrier",
        "Whimbrel",
        "Whinchat",
        "Whippet",
        "Whiptail Lizard",
        "White Butterfly",
        "White Catfish",
        "White Crappie",
        "White Ferret",
        "White German Shepherd",
        "White Marlin",
        "White Rhinoceros",
        "White Shark",
        "White Sturgeon",
        "White Tiger",
        "White-Crowned Sparrow",
        "White-Eyed Vireo",
        "White-Faced Capuchin",
        "White-tail deer",
        "White-Tailed Eagle",
        "Whitetail Deer",
        "Whiting",
        "Whoodle",
        "Whooping Crane",
        "Wild Boar",
        "Wildebeest",
        "Willow Flycatcher",
        "Willow Warbler",
        "Winter Moth",
        "Wire Fox Terrier",
        "Wirehaired Pointing Griffon",
        "Wirehaired Vizsla",
        "Wiwaxia",
        "Wolf",
        "Wolf Eel",
        "Wolf Snake",
        "Wolf Spider",
        "Wolffish",
        "Wolverine",
        "Woma Python",
        "Wombat",
        "Wood Bison",
        "Wood Duck",
        "Wood Frog",
        "Wood Tick",
        "Wood Turtle",
        "Woodlouse",
        "Woodlouse Spider",
        "Woodpecker",
        "Woodrat",
        "Wool Carder Bee",
        "Woolly Aphids",
        "Woolly Bear Caterpillar",
        "Woolly Mammoth",
        "Woolly Monkey",
        "Woolly Rhinoceros",
        "Worm",
        "Worm Snake",
        "Wrasse",
        "Writing Spider",
        "Wrought Iron Butterflyfish",
        "Wryneck",
        "Wyandotte Chicken",
        "Wyoming Toad"
    )
    var X = setOf(
        "X-Ray Tetra",
        "Xeme",
        "Xenacanthus",
        "Xenoceratops",
        "Xenoposeidon",
        "Xenotarsosaurus",
        "Xerus",
        "Xiaosaurus",
        "Xiaotingia",
        "Xingu River Ray",
        "Xiphactinus",
        "Xoloitzcuintli"
    )
    var Y = setOf(
        "Yabby",
        "Yak",
        "Yakutian Laika",
        "Yarara",
        "Yellow Aphids",
        "Yellow Bass",
        "Yellow Bellied Sapsucker",
        "Yellow Belly Ball Python",
        "Yellow Cobra",
        "Yellow Crazy Ant",
        "Yellow Perch",
        "Yellow Sac Spider",
        "Yellow Spotted Lizard",
        "Yellow Tanager",
        "Yellow Tang",
        "Yellow-Bellied Sea Snake",
        "Yellow-Eyed Penguin",
        "Yellow-faced Bee",
        "Yellowhammer",
        "Yellowish Cuckoo Bumblebee",
        "Yellowjacket",
        "Yellowtail Snapper",
        "Yellowthroat",
        "Yeti Crab",
        "Yokohama Chicken",
        "Yoranian",
        "Yorkie Bichon",
        "Yorkiepoo",
        "Yorkshire Terrier"
    )
    var Z = setOf(
        "Zebra",
        "Zebra Finch",
        "Zebra Mussels",
        "Zebra Pleco",
        "Zebra Shark",
        "Zebra Snake",
        "Zebra Spitting Cobra",
        "Zebra Tarantula",
        "Zebrafish",
        "Zebu",
        "Zokor",
        "Zonkey",
        "Zorse",
        "Zuchon"
    )
}





