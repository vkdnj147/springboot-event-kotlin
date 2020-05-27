package com.linaexample.kotlin.event.Post


import org.springframework.data.repository.CrudRepository

interface PostRepository : CrudRepository<Post, Long> {

}
