package com.example.habitandhobby

import androidx.room.*

@Dao
interface dataDao {
    @Query("DELETE FROM Goal")
    fun deleteSmallGoals()

    @Query("DELETE FROM Goal WHERE rowid = (SELECT MAX(rowid) FROM Goal)")
    fun deleteLastGoal()

    @Query("SELECT * FROM GOAL WHERE habitName LIKE :habitName")
    fun getAllGoalsInCategory(habitName:String?):List<Goal>

    @Query("DELETE FROM GOAL WHERE habitName LIKE :habitName")
    fun deleteGoalsByHabitName(habitName:String?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrent(curr:CurrentActivity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSmallGoal(goal:Goal)

    @Query("SELECT * FROM CurrentActivity")
    fun getAllCurr():List<CurrentActivity>

    @Query("SELECT * FROM Goal")
    fun getAllSmallGoals():List<Goal>

    @Query("DELETE FROM CurrentActivity")
    fun deleteCurrent()

    @Query("DELETE FROM CurrentActivity WHERE rowid = (SELECT MAX(rowid) FROM CurrentActivity)")
    fun deleteLastCurrent()

}