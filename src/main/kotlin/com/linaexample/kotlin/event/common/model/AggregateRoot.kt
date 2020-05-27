package com.linaexample.kotlin.event.common.model

import org.springframework.data.domain.AfterDomainEventPublication
import org.springframework.data.domain.DomainEvents
import java.util.*

open class AggregateRoot<A : AggregateRoot<A>?> : BaseEntity() {

    @Transient
    private val domainEvents: MutableList<Any?> = ArrayList()

    protected fun <T> registerEvent(event: T): T {
        domainEvents.add(event)
        return event
    }

    //도메인 이벤트가 게시 된 후 @AfterDomainEventsPublication으로 주석이 달린 메소드가 호출됩니다.
    //
    //이 방법의 목적은 일반적으로 모든 이벤트 목록을 지우는 것이므로 나중에 다시 게시되지 않습니다.
    @AfterDomainEventPublication
    protected fun clearDomainEvents() {
        domainEvents.clear()
    }

    //@DomainEvents로 주석이 달린 메소드는 엔티티가 올바른 저장소를 사용하여 저장 될 때마다 Spring Data에 의해 자동으로 호출됩니다.
    @DomainEvents
    protected fun domainEvents(): Collection<Any?> {
        return Collections.unmodifiableList(domainEvents)
    }

    protected fun andEventsFrom(aggregate: A): AggregateRoot<A> {
        domainEvents.addAll(aggregate!!.domainEvents())
        return this
    }

    protected fun andEvent(event: Any): AggregateRoot<A> {
        registerEvent(event)
        return this
    }
}
