package ru.e2k.chechina.korytin.myapplication

import android.content.Context
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
find(this , findViewById<EditText>(R.id.addr) as EditText)

    }

    fun find(cont: Context, editText: EditText) = runBlocking { //(1)
        val zhkhDatabase = ZhkhDatabase.getDatabase(cont)  // Создать или получить базу данных

        val r = async(Dispatchers.Default) {                           // CommonPool Асинхронно считаем данные из базы. Получаем запись с идентификатором ноль.
            zhkhDatabase?.readoutDAO()?.getReadoutById(0)     // Считаем нулевую запись. Результат последнего выражения потока возвращается в переменную r
        }
        launch (Dispatchers.Main ) {            //  Contacts.Intents.UI  Запустить и забыть. Поток в потоке интерфейса UI. Ждём данные и выводим их в интерфейс.
            var rec = r.await()                               // Подождём результата.
            editText.setText(rec?.addr)                       // Выведем в интерфейс.
        }


        /*  val job = launch(Dispatchers.Default) { //(2)
              val result = "Ghj,f" //(3)
              println("$result")
          }
          println("The result: ")
          job.join() //(4)*/
    }
}
