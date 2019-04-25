package ru.e2k.chechina.korytin.myapplication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.util.*

@Entity
class ReadoutTable(
        @ColumnInfo var addr: String?,  // String? - Поля могут иметь значения NULL
        @ColumnInfo var hvs: String?,
        @ColumnInfo var gvs: String?,
        @ColumnInfo var t1: String?,
        @ColumnInfo var t2: String?,
        @ColumnInfo var t3: String?,
        @ColumnInfo var comment: String?,
        @field:TypeConverters(DateConverter::class)   // Добавим дополнительные преобразователи типов (а именно, написанный нами класс DateConverter),
        @ColumnInfo var curDate: Date?                // которые может использовать поле даты. "field:" - применение аннотации TypeConverters к полю.
){

    @PrimaryKey(autoGenerate = true) var id: Long = 0 // Long - без вопросительного знака, значит поле NOT NULL
}

/*
 * Класс конвертеров Long в Date и обратно для TypeConverters() в Room, чтобы автоматически сохранять в базе типы, на которые она не рассчитана
 * Та или иная функция выбирается автоматически исходя из входного типа данных
 */
class DateConverter {
    @TypeConverter  // Указать, что метод является конвертером
    fun toDate(timestamp: Long?) = timestamp?.let { Date(it) }
    @TypeConverter  // Указать, что метод является конвертером
    fun toTimestamp(date: Date?) = date?.time
}