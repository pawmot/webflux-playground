package com.pawmot.rx
import io.reactivex.Single
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SampleCtrl {
    @GetMapping("hello")
    fun hello(): Single<String> {
        return Single.just("Hello World!")
    }
}