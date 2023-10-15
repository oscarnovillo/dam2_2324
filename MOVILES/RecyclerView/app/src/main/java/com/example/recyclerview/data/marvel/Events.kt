package com.example.recyclerview.data.marvel

data class Events(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemX>,
    val returned: Int
)