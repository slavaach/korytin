package ru.e2k.chechina.korytin.myapplication


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

/*
 * Описание базы данных.
 */
@Database(entities = [ReadoutTable::class], version = 1 , exportSchema = false)  // Перечисляем в entities, какие классы будут использоваться для создания таблиц.
abstract class ZhkhDatabase : RoomDatabase() {

    abstract fun readoutDAO(): ReadoutModelDao           // Описываем абстрактные методы для получения объектов интерфейса BorrowModelDao, которые вам понадобятся

    // Сопутствующий объект для получения базы данных (фактически синглтон). Можно не использовать.
    companion object {

        private var INSTANCE: ZhkhDatabase? = null

        fun getDatabase(context: Context): ZhkhDatabase? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder<ZhkhDatabase>(context, ZhkhDatabase::class.java, "readout_db")
                        .build()
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }


    }
}