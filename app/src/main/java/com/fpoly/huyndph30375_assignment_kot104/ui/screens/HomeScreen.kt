package com.fpoly.huyndph30375_assignment_kot104.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.hq.quanhqph33420_assignment.R
import com.fpoly.huyndph30375_assignment_kot104.database.MyDatabase
import com.fpoly.huyndph30375_assignment_kot104.database.entities.Products
import com.fpoly.huyndph30375_assignment_kot104.database.factory.ProductFactory
import com.fpoly.huyndph30375_assignment_kot104.database.repository.ProductRepository
import com.fpoly.huyndph30375_assignment_kot104.database.viewModel.ProductViewModel
import com.fpoly.huyndph30375_assignment_kot104.font.GoogleFont
import com.fpoly.huyndph30375_assignment_kot104.model.FilterIcon
import com.fpoly.huyndph30375_assignment_kot104.bottom_nav.Screens

@Composable
fun HomeScreen(navController: NavController) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val productRepository =
        ProductRepository(MyDatabase.getDatabase(context = context, scope = scope).productDao())
    val viewModel: ProductViewModel = viewModel(factory = ProductFactory(productRepository))
    val listProduct by viewModel.getAllProduct.observeAsState(emptyList())
    ComponentHomeScreen(
        navController = navController,
        listProduct = listProduct
    )
}

@Composable
private fun ItemProduct(
    productModel: Products,
    navController: NavController,
) {
    Column(modifier = Modifier.clickable {
        navController.navigate("itemProduct/${productModel.id}")
    }) {
        Box(modifier = Modifier.height(200.dp)) {
            AsyncImage(
                model = productModel.imgProduct,
                contentDescription = "${productModel.id}",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
            ) {
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
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = productModel.nameProduct,
            fontFamily = GoogleFont.NunitoSansFont,
            fontSize = 14.sp,
            color = Color(0xFF606060),
            fontWeight = FontWeight(400)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "$ ${productModel.priceProduct}",
            fontFamily = GoogleFont.NunitoSansFont,
            fontSize = 14.sp,
            color = Color(0xFF303030),
            fontWeight = FontWeight(700)
        )
    }
}

@Composable
private fun ListProduct(navController: NavController, listProducts: List<Products>) {
    val state = rememberLazyStaggeredGridState()

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(20.dp),
        state = state,
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalItemSpacing = 20.dp,
        content = {
            items(listProducts) { item ->
                ItemProduct(productModel = item, navController = navController)
            }
        }
    )
}

@Composable
private fun FilterComponent(modifier: Modifier = Modifier) {
    val items = listOf(
        FilterIcon("Popular", R.drawable.star),
        FilterIcon("Chair", R.drawable.chair),
        FilterIcon("Table", R.drawable.table),
        FilterIcon("Armchair", R.drawable.armchair),
        FilterIcon("Bed", R.drawable.bed)
    )
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 0.dp, 0.dp, 0.dp)
    ) {
        itemsIndexed(items) { index, item ->
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Card(modifier.size(45.dp)) {
                    Column(
                        modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painterResource(id = item.image),
                            contentDescription = "$index _",
                            modifier.size(20.dp)
                        )

                    }
                }
                Text(
                    text = item.name,
                    fontSize = 14.sp,
                    fontWeight = FontWeight(600),
                    fontFamily = GoogleFont.NunitoSansFont
                )
            }
            Spacer(modifier.width(25.dp))
        }
    }
}
@Composable
private fun ComponentHomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    listProduct: List<Products>
) {
    Surface(modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Column {
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
                        text = "Make home",
                        fontFamily = GoogleFont.GelasioFont,
                        fontWeight = FontWeight(400),
                        fontSize = 18.sp,
                        color = Color(0xFF909090)
                    )
                    Text(
                        text = "BEAUTIFUL", fontSize = 18.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight(700),
                        fontFamily = GoogleFont.GelasioFont
                    )
                }
                IconButton(onClick = { navController.navigate(Screens.cart) }) {
                    Icon(Icons.Outlined.ShoppingCart, contentDescription = null)
                }
            }
            Spacer(modifier.height(15.dp))
            FilterComponent()
            ListProduct(navController = navController, listProducts = listProduct)
        }
    }
}