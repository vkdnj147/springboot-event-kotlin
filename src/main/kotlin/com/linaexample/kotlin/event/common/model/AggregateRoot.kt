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

    @AfterDomainEventPublication
    protected fun clearDomainEvents() {
        domainEvents.clear()
    }

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
