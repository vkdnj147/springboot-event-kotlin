package com.linaexample.kotlin.event.Post

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.servlet.function.router

@Configuration
class PostRouter {

//WebFlux에서 라우터 기능은 RequestPredicate (즉, 요청을 관리해야하는 사람) 및 HandlerFunction
// (즉, 요청을 구체화하는 방법)에 의해 결정되는 기능입니다.
//
//핸들러 함수는 ServerRequest 인스턴스를 승인하고 Mono <ServerResponse> 인스턴스를 생성합니다.
    @Bean
    fun postRoute(handler: PostHandler) = router {
        accept(APPLICATION_JSON).nest {
            GET("/post/{id}", handler::getPost)
            GET("/post", handler::listPost)
        }
        POST("/post", handler::createPost)
    }
}