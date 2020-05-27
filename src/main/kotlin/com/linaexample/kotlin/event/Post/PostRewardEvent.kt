package com.linaexample.kotlin.event.Post

class PostRewardEvent(post: Post) {
    private val post: Post = post
    val postId: Long?
        get() = post.id

    val memberId: Long?
        get() = post.memberId

}
