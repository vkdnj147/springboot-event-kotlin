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

    fun listPost(request: ServerRequest): ServerResponse {
        val posts: List<PostView> = repository.findAll().map(this::entityToView)
        return ok().contentType(APPLICATION_JSON).body(posts);
    }

    fun createPost(request: ServerRequest): ServerResponse {
        val post = request.body<Post>()
        postService.savePost(post)
//        repository.save(post)
        return ok().build()
    }

    fun getPost(request: ServerRequest): ServerResponse {
        val postId = request.pathVariable("id").toLong()
        return repository.findById(postId)?.let { ok().contentType(APPLICATION_JSON).body(it) }
                ?: ServerResponse.notFound().build()

    }

    fun entityToView(post: Post): PostView {
        val postView = PostView()
        postView.getView(post)
        return postView
    }
}