package com.linaexample.kotlin.event.reward


import com.linaexample.kotlin.event.common.model.BaseEntity
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "rewards")
class Reward(postId: Long?, memberId: Long?) : BaseEntity() {

    var postId: Long? = postId
    var memberId: Long? = memberId


}
