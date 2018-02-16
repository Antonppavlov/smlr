package ru.pavlov.smlr.service

interface KeyMapperService {

    interface Get {
        data class Link(val link: String) : Get
        data class LinkNotFound(var key: String) : Get
    }

    fun getLink(key: String): Get

    fun add(link: String): String

}