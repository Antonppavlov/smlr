package ru.pavlov.smlr.model

import javax.persistence.*

@Entity
@Table(name = "links")
class Link(
        var text: String = " ",
        @Id @GeneratedValue(strategy = GenerationType.AUTO) var id: Long = 0

)