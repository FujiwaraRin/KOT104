package com.fpoly.huyndph30375_assignment_kot104.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.fpoly.huyndph30375_assignment_kot104.database.dao.CartDao
import com.fpoly.huyndph30375_assignment_kot104.database.dao.FavoriteDao
import com.fpoly.huyndph30375_assignment_kot104.database.dao.ProductDao
import com.fpoly.huyndph30375_assignment_kot104.database.dao.SaveUserDao
import com.fpoly.huyndph30375_assignment_kot104.database.dao.UserDao
import com.fpoly.huyndph30375_assignment_kot104.database.entities.Carts
import com.fpoly.huyndph30375_assignment_kot104.database.entities.Favorites
import com.fpoly.huyndph30375_assignment_kot104.database.entities.Products
import com.fpoly.huyndph30375_assignment_kot104.database.entities.SaveUsers
import com.fpoly.huyndph30375_assignment_kot104.database.entities.Users
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(
    entities = [
        Users::class,
        Products::class,
        Carts::class,
        SaveUsers::class,
        Favorites::class
    ],
    version = 1,
    exportSchema = false
)
abstract class MyDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun productDao(): ProductDao
    abstract fun cartDao(): CartDao
    abstract fun saveUserDao(): SaveUserDao
    abstract fun favoriteDao(): FavoriteDao

    companion object {
        @Volatile
        private var INSTANCE: MyDatabase? = null
        fun getDatabase(context: Context, scope: CoroutineScope): MyDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    MyDatabase::class.java,
                    "item_database"
                ).addCallback(ProductDatabaseCallback(scope))
                    .build()
                    .also { INSTANCE = it }
            }
        }

        class ProductDatabaseCallback(private val scope: CoroutineScope) : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    scope.launch {
                        populateDatabase(productDao = database.productDao())
                    }
                }
            }

            suspend fun populateDatabase(productDao: ProductDao) {
                val products1 = Products(
                    id = 1,
                    "Black Simple Lamp",
                    "https://cityfurnish.com/blog/wp-content/uploads/2023/04/stylish-scandinavian-living-room-with-design-mint-sofa-furnitures-mock-up-poster-map-plants-eleg-min.jpg",
                    500,
                    2
                )
                val products2 = Products(
                    id = 2,
                    "Minimal Stand",
                    "https://www.crownasia.com.ph/wp-content/uploads/2023/08/What-Kind-of-Furniture-Would-Suit-Your-Cavite-House-and-Lot.jpg",
                    1061,
                    5
                )
                val products3 = Products(
                    id = 3,
                    "Coffee Chair",
                    "https://housing.com/news/wp-content/uploads/2023/04/Best-furniture-brands-in-India-f.jpg",
                    362,
                    4
                )
                val products4 = Products(
                    id = 4,
                    "Black Simple Lamp",
                    "https://foyr.com/learn/wp-content/uploads/2021/11/top-furniture-brands.png",
                    1453,
                    1
                )
                val products5 = Products(
                    id = 5,
                    "Minimal Stand",
                    "https://c8.alamy.com/comp/MMEB4A/white-cozy-furniture-in-spacious-modern-living-room-with-wooden-floor-MMEB4A.jpg",
                    1235,
                    2
                )
                val products6 = Products(
                    id = 6,
                    "Coffee Chair",
                    "https://www.vinterior.co/blog/wp-content/uploads/2023/03/ARTILLERYHOUSE_01.JPG-1.jpg",
                    2448,
                    3
                )
                productDao.addProduct(products1)
                productDao.addProduct(products2)
                productDao.addProduct(products3)
                productDao.addProduct(products4)
                productDao.addProduct(products5)
                productDao.addProduct(products6)
            }
        }
    }
}