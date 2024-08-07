package com.fpoly.huyndph30375_assignment_kot104.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.hq.quanhqph33420_assignment.R
import com.fpoly.huyndph30375_assignment_kot104.bottom_nav.Screens
import com.fpoly.huyndph30375_assignment_kot104.database.MyDatabase
import com.fpoly.huyndph30375_assignment_kot104.database.entities.Favorites
import com.fpoly.huyndph30375_assignment_kot104.database.factory.FavoriteFactory
import com.fpoly.huyndph30375_assignment_kot104.database.factory.SaveUserFactory
import com.fpoly.huyndph30375_assignment_kot104.database.repository.FavoriteRepository
import com.fpoly.huyndph30375_assignment_kot104.database.repository.SaveUserRepository
import com.fpoly.huyndph30375_assignment_kot104.database.viewModel.FavoriteViewModel
import com.fpoly.huyndph30375_assignment_kot104.database.viewModel.SaveUserViewModel
import com.fpoly.huyndph30375_assignment_kot104.font.GoogleFont

@Composable
fun FavoriteScreen(navController: NavController) {
    FavoriteScreenComponent(navController = navController)
}

@Composable
private fun FavoriteScreenComponent(modifier: Modifier = Modifier, navController: NavController) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val favoriteRepository =
        FavoriteRepository(MyDatabase.getDatabase(context, scope).favoriteDao())
    val favoriteViewModel: FavoriteViewModel =
        viewModel(factory = FavoriteFactory(favoriteRepository))
    val saveUserRepository =
        SaveUserRepository(MyDatabase.getDatabase(context, scope).saveUserDao())
    val saveUserViewModel: SaveUserViewModel =
        viewModel(factory = SaveUserFactory(saveUserRepository))
    val saveUser by saveUserViewModel.getUser.observeAsState(null)
    val email = when {
        saveUser != null -> saveUser!!.email
        else -> "null"
    }
    val listItem by favoriteViewModel.getAllFavorite(email)
        .observeAsState(emptyList())

    Column(
        modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Outlined.Search, contentDescription = null)
            }
            Column(
                modifier.fillMaxWidth(0.5f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Favorites",
                    fontFamily = GoogleFont.MerriweatherFont,
                    fontWeight = FontWeight(700),
                    fontSize = 16.sp,
                    color = Color(0xFF303030)
                )
            }
            IconButton(onClick = {
                navController.navigate(Screens.cart)
            }) {
                Icon(Icons.Outlined.ShoppingCart, contentDescription = null)
            }
        }
        ListItem(list = listItem)
    }
}

@Composable
fun ItemList(item: Favorites, modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Card(modifier = Modifier.size(100.dp)) {
                AsyncImage(
                    model = item.imgProduct,
                    contentDescription = "${item.id}",
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier.width(20.dp))
            Column {
                Text(
                    text = item.nameProduct,
                    fontFamily = GoogleFont.NunitoSansFont,
                    fontWeight = FontWeight(600),
                    fontSize = 14.sp,
                    color = Color(0xFF999999)
                )
                Spacer(modifier.height(5.dp))
                Text(
                    text = "$ ${item.priceProduct}",
                    fontFamily = GoogleFont.NunitoSansFont,
                    fontWeight = FontWeight(700),
                    fontSize = 16.sp,
                    color = Color(0xFF242424)
                )
            }
        }
        Column(
            modifier.fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                Surface(
                    shape = RoundedCornerShape(50.dp),
                    border = BorderStroke(1.dp, Color.Black),
                    modifier = Modifier
                        .size(20.dp)
                        .fillMaxWidth(),
                ) {
                    IconButton(
                        onClick = {
                        },
                        modifier = Modifier.background(Color.White)
                    ) {
                        Icon(
                            Icons.Default.Clear,
                            contentDescription = "",
                            modifier = Modifier.size(15.dp)
                        )
                    }
                }
                Spacer(modifier.width(10.dp))
            }
            Spacer(modifier.height(25.dp))
            Column {
                Row {
                    Card(shape = RoundedCornerShape(10.dp)) {
                        Row(
                            modifier = Modifier
                                .size(30.dp)
                                .background(Color(0x60606066)),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Image(
                                painterResource(id = R.drawable.bag_), contentDescription = "bag",
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun ListItem(list: List<Favorites>) {
    LazyColumn {
        items(list) { item ->
            ItemList(item = item)
        }
    }
}