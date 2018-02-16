package ru.pavlov.smlr.model

import org.springframework.data.repository.Repository
import org.springframework.stereotype.Component
import java.util.*
interface LinkRepository : Repository<Link, Long> {
    fun findOne(id: Long?): Optional<Link>
    fun save(link: Link): Link
    fun findAll(): List<Link>

}