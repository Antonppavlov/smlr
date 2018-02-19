package ru.pavlov.smlr.model

import javax.persistence.*

@Entity
@Table(name = "links")
class Link(

        var text: String = "",

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "links_sequance")
        @SequenceGenerator(name = "links_sequance", sequenceName = "links_seq")
        var id: Long = 0

)