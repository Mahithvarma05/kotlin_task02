package SentimentScore

import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.File
import java.util.*
import kotlin.collections.HashMap

fun main()= runBlocking{
    val map = hashMapOf<String,Int>()
    map["excellent"]=3
    map["good"]=2
    map["better"]=1
    map["bad"]=-1
    map["worst"]=-2
    map["disappointed"]=-3

    val file1=File("C:\\Users\\THARUN\\testPredict.txt")
    val file2=File("C:\\Users\\THARUN\\testPredict2.txt")
    val file3=File("C:\\Users\\THARUN\\testPredict3.txt")

    val files= mutableListOf<File>()
    files.add(file1)
    files.add(file2)
    files.add(file3)


    val jobs = files.map { file ->
        launch {
            processFile(file,map)
        }
    }

    jobs.joinAll()

}
fun processFile(file: File,map:HashMap<String,Int>) {
    val words = file.readText().lowercase(Locale.getDefault()).split("\\W+".toRegex())
    val ob=words.filter { map.containsKey(it)}
    println(ob)
    var occurance= ob.groupingBy { it }.eachCount()

    occurance.filter { it.value > 1 }.forEach { (word, count) ->
        println("$word: $count")

    val score = ob.sumOf{
        map.getOrDefault(it, 0)
    }


    println(score)

    if (score>0){ file.renameTo(File(file.parent,"positive.txt")) }

    else if (score<0){ file.renameTo(File(file.parent,"negative.txt")) }

    else{ file.renameTo(File(file.parent,"neutral.txt")) }

}