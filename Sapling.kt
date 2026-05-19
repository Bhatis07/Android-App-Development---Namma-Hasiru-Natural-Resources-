package com.internshipproject

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saplings")
data class Sapling(
    var name: String,
    var location: String,
    var species: String,
    var status: String,
    var lastUpdate: String,
    var imagePath: String? = null
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
