package com.linaexample.kotlin.event.Post


import com.linaexample.kotlin.event.common.model.AggregateRoot
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Table


@Entity
@Table(name = "posts")
class Post : AggregateRoot<Post>() {

    var title = ""
    var content = ""
    var memberId: Long? = null

    @Enumerated(EnumType.STRING)
    private var rewardStatus = RewardStatus.INIT

    fun rewarded() {
        this.rewardStatus = RewardStatus.DONE
        registerEvent(PostRewardEvent(this))
    }

    enum class RewardStatus {
        INIT, DOING, DONE
    }


}
