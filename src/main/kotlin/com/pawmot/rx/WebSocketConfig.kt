package com.pawmot.rx

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.buffer.DataBufferFactory
import org.springframework.core.io.buffer.DefaultDataBufferFactory
import org.springframework.web.reactive.HandlerMapping
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping
import org.springframework.web.reactive.socket.WebSocketHandler
import org.springframework.web.reactive.socket.server.WebSocketService
import org.springframework.web.reactive.socket.server.support.HandshakeWebSocketService
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter
import org.springframework.web.reactive.socket.server.upgrade.ReactorNettyRequestUpgradeStrategy

@Configuration
open class WebSocketConfig {

    @Bean
    open fun handlerAdapter(): WebSocketHandlerAdapter = WebSocketHandlerAdapter()

    @Bean
    open fun mappings(uh: UptimeHandler): HandlerMapping {
        val map: Map<String, WebSocketHandler> =
                mapOf(Pair("/uptime", uh))

        val mapping = SimpleUrlHandlerMapping()
        mapping.urlMap = map
        return mapping
    }

    @Bean
    open fun webSocketService(): WebSocketService = HandshakeWebSocketService(ReactorNettyRequestUpgradeStrategy())

    @Bean
    open fun dataBufferFactory(): DataBufferFactory = DefaultDataBufferFactory()
}