package com.pawmot.rx

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.reactive.config.EnableWebFlux

@SpringBootApplication
@EnableWebFlux
open class App

fun main(args: Array<String>) {
    SpringApplication.run(App::class.java, *args)
}