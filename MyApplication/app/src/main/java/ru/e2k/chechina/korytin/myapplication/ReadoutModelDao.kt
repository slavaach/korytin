package ru.e2k.chechina.korytin.myapplication

import androidx.room.*

/*
 * Интерфейс доступа к базе данных (DAO - Data Access Object)
 */
@Dao
@TypeConverters(DateConverter::class)                   // Указываем, что при доступе к некоторым полям будет задействован наш конвертер DateConverter
interface ReadoutModelDao {

    @get:Query("select * from ReadoutTable")            // "get:" означает применение аннотации "Query" к геттеру (функцию геттера для переменной allReadoutItems вручную не пишем)
    val allReadoutItems: List<ReadoutTable>             // Обёртываем возвращаемое значение LiveData<...> чтобы отслеживать изменения в базе. При изменении данных будут рассылаться уведомления

    @Query("select * from ReadoutTable where id = :id")
    fun getReadoutById(id: Long): ReadoutTable

    @Query("select * from ReadoutTable where addr = :addr")
    fun getReadoutByAddr(addr: String): List<ReadoutTable>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addReadout(readoutModel: ReadoutTable)

    @Update
    fun updateReadout(readoutModel: ReadoutTable)

    @Delete
    fun deleteReadout(readoutModel: ReadoutTable)
}