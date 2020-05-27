package com.linaexample.kotlin.event.Post


import com.linaexample.kotlin.event.common.model.BaseView

class PostView : BaseView() {

    var title = ""
    var content = ""
    var memberId: Long? = null

    fun getView(post: Post) {
        this.title = post.title
        this.content = post.content
        this.memberId = post.memberId
    }


}
