package ru.thegod.services

import com.github.gotson.nightmonkeys.heif.imageio.plugins.HeifImageReaderSpi
import jakarta.inject.Singleton
import java.io.File
import javax.imageio.ImageIO

@Singleton
class HeifMonkeyReader {
    fun convertHeicToJpg(pathToFile: String,pathToOut: String){
        val heicFile = File(pathToFile)
        val outPng = File(pathToOut+"/out.png")
        try{
            for(e in ImageIO.getReaderFormatNames())
                println(e)

            val heicInputStream = ImageIO.createImageInputStream(heicFile)
            val imageReader = HeifImageReaderSpi().createReaderInstance()
            imageReader.setInput(heicInputStream)

            val bufferedHeic = imageReader.read(0)
            val pngOutputStream = ImageIO.createImageOutputStream(outPng)

            ImageIO.write(bufferedHeic,"png",outPng)
            pngOutputStream.flush()



        }
        catch (e: Exception){
            error("Exception:\n$e")
        }

    }
}