package com.pawmot.rx

import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import org.springframework.core.io.buffer.DataBuffer
import org.springframework.core.io.buffer.DataBufferFactory
import org.springframework.stereotype.Component
import org.springframework.web.reactive.socket.WebSocketHandler
import org.springframework.web.reactive.socket.WebSocketMessage
import org.springframework.web.reactive.socket.WebSocketSession
import reactor.core.publisher.Mono
import java.util.concurrent.TimeUnit

@Component
open class UptimeHandler(private val dbf: DataBufferFactory) : WebSocketHandler {

    val uptime = Observable.interval(1, TimeUnit.SECONDS).toFlowable(BackpressureStrategy.LATEST).publish()

    init {
        uptime.connect()
    }

    override fun handle(session: WebSocketSession): Mono<Void> {
        println("Got a connection. Id: ${session.id}")
        return session.send(uptime.map { t -> WebSocketMessage(WebSocketMessage.Type.TEXT, serialize(t)) })
    }

    private fun serialize(i: Long): DataBuffer {
        val str = i.toString() + " seconds"
        val bytes = str.toByteArray()
        return dbf.wrap(bytes)
    }
}
