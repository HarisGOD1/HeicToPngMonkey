package ru.thegod.services

import jakarta.inject.Singleton
import java.io.File
import javax.imageio.ImageIO

@Singleton
class GeminiCode {

    /**
     * Converts a HEIC image file to a PNG image file.
     *
     * @param sourcePath The path to the source HEIC file.
     * @param destinationPath The path where the destination PNG file will be saved.
     * @return True if conversion was successful, false otherwise.
     */
    fun convertToPng(sourcePath: String, destinationPath: String): Boolean {
        val sourceFile = File(sourcePath)
        val destinationFile = File(destinationPath)

        // Ensure the source file exists
        if (!sourceFile.exists()) {
            println("Error: Source file not found at '$sourcePath'")
            return false
        }

        // Ensure the output format is supported (it should be, PNG is standard)
        val writerFormatNames = ImageIO.getWriterFormatNames().map { it.lowercase() }
        if ("png" !in writerFormatNames) {
            println("Error: No ImageIO writer found for PNG format. This is highly unusual.")
            return false
        }

        println("Attempting to read HEIC file: ${sourceFile.absolutePath}")

        try {
            // 1. Read the HEIC image.
            // ImageIO will automatically find the NightMonkeys plugin to handle the HEIC format
            // if the dependencies and native libraries are set up correctly.
            val image = ImageIO.read(sourceFile)

            if (image == null) {
                println("Error: ImageIO.read returned null. This likely means no suitable ImageReader was found.")
                println("Please check:")
                println("  - Is 'libheif' installed and accessible in your system's PATH?")
                println("  - Is the NightMonkeys dependency correctly added to your build file?")
                println("  - Is the JVM running with '--enable-native-access=ALL-UNNAMED'?")
                return false
            }

            println("Successfully read image data. Width: ${image.width}, Height: ${image.height}")

            // 2. Write the image data to a new file in PNG format.
            println("Writing PNG file to: ${destinationFile.absolutePath}")
            val success = ImageIO.write(image, "png", destinationFile)

            if (success) {
                println("Conversion successful!")
            } else {
                println("Error: ImageIO.write failed to save the PNG file.")
            }

            return success

        } catch (e: Exception) {
            println("An exception occurred during conversion: ${e.message}")
            e.printStackTrace()
            return false
        }
    }
}