package com.linaexample.kotlin.event.Post.service

import com.linaexample.kotlin.event.Post.Post
import com.linaexample.kotlin.event.Post.PostRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostService(private val postRepository: PostRepository) {

    @Transactional
    fun savePost(post: Post) {
        post.rewarded()
        postRepository.save(post)
    }

}
