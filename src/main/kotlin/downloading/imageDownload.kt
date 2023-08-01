package downloading

import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File

fun downloadImage(url: String, destinationFile: File) {
    val client = OkHttpClient()
    val request = Request.Builder().url(url).build()

    client.newCall(request).execute().use { response ->
        if (!response.isSuccessful) {
            println("Failed to download image from $url: ${response.code}")
            return
        }

        response.body?.byteStream()?.use { inputStream ->
            destinationFile.outputStream().use { outputStream ->
                inputStream.copyTo(outputStream)
            }
        }

        println("Downloaded $url to ${destinationFile.absolutePath}")
    }
}

fun main() {
    val imageUrl = "https://images.pexels.com/photos/268533/pexels-photo-268533.jpeg?cs=srgb&dl=pexels-pixabay-268533.jpg&fm=jpg"
    val destinationFile = File("F:\\New folder\\downloaded_image.jpg")

    downloadImage(imageUrl, destinationFile)
}
