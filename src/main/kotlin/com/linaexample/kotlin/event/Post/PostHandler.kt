package com.linaexample.kotlin.event.Post

import com.linaexample.kotlin.event.Post.service.PostService
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.stereotype.Component
import org.springframework.web.servlet.function.ServerRequest
import org.springframework.web.servlet.function.ServerResponse
import org.springframework.web.servlet.function.ServerResponse.ok
import org.springframework.web.servlet.function.body

@Component
class PostHandler(private val repository: PostRepository, private val postService: PostService) {

    //핸들러 함수는 ServerRequest 인스턴스를 승인하고 Mono <ServerResponse> 인스턴스를 생성합니다.

    //.map 값을 변형해서 새로운 리스트를 생성
    fun listPost(request: ServerRequest): ServerResponse {
        val posts: List<PostView> = repository.findAll().map(this::entityToView)
        return ok().contentType(APPLICATION_JSON).body(posts);
    }

    fun createPost(request: ServerRequest): ServerResponse {
        //body에 있는 내용을 저장
        val post = request.body<Post>()
        postService.savePost(post)
//        repository.save(post)
        return ok().build()
    }

    fun getPost(request: ServerRequest): ServerResponse {
        val postId = request.pathVariable("id").toLong()
        //.let - 함수를 호출하는 객체
        //id가 일치하면 json 타입으로 호출
        return repository.findById(postId)?.let { ok().contentType(APPLICATION_JSON).body(it) }
                ?: ServerResponse.notFound().build()
    }

    fun entityToView(post: Post): PostView {
        val postView = PostView()
        postView.getView(post)
        return postView
    }
}