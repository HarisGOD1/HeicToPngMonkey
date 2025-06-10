package ru.thegod.services

import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Test

@MicronautTest
class HeifMonkeyReaderTest {

    @Inject
    lateinit var heifMonkeyReader: HeifMonkeyReader

    @Test
    fun `file converted`(){


        heifMonkeyReader.convertHeicToJpg("/home/thegod/JAVA/PROJECTS/HeicToPngMonkey/src/main/resources/images/heic/IMG_8498.HEIC",
            "/home/thegod/JAVA/PROJECTS/HeicToPngMonkey/src/main/resources/images/png")
    }
}