package com.linaexample.kotlin.event

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EventApplication

fun main(args: Array<String>) {
    runApplication<EventApplication>(*args)
}
