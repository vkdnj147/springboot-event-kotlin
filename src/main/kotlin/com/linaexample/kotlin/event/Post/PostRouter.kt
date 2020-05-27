package com.linaexample.kotlin.event.Post

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.servlet.function.router

@Configuration
class PostRouter {

    @Bean
    fun postRoute(handler: PostHandler) = router {
        accept(APPLICATION_JSON).nest {
            GET("/post/{id}", handler::getPost)
            GET("/post", handler::listPost)
        }
        POST("/post", handler::createPost)
    }
}