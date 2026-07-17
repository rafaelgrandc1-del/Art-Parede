package com.example.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "projects")
data class MoodboardProject(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val clientName: String,
    val description: String,
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(
    tableName = "moodboard_items",
    foreignKeys = [
        ForeignKey(
            entity = MoodboardProject::class,
            parentColumns = ["id"],
            childColumns = ["projectId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("projectId")]
)
data class MoodboardItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val projectId: Int,
    val effectKey: String,
    val customColorHex: String,
    val customScale: Float = 1.0f,
    val shineIntensity: Float = 0.5f,
    val notes: String = ""
)

@Dao
interface ArtParedeDao {
    @Query("SELECT * FROM projects ORDER BY createdAt DESC")
    fun getAllProjects(): Flow<List<MoodboardProject>>

    @Query("SELECT * FROM projects WHERE id = :id LIMIT 1")
    suspend fun getProjectById(id: Int): MoodboardProject?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProject(project: MoodboardProject): Long

    @Delete
    suspend fun deleteProject(project: MoodboardProject)

    @Query("SELECT * FROM moodboard_items WHERE projectId = :projectId ORDER BY id DESC")
    fun getItemsForProject(projectId: Int): Flow<List<MoodboardItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: MoodboardItem)

    @Delete
    suspend fun deleteItem(item: MoodboardItem)
}

@Database(entities = [MoodboardProject::class, MoodboardItem::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun artParedeDao(): ArtParedeDao
}
