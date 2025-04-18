/**
 * Title: CST-338 Project 02: Activiti - User DAO
 * @author Noah deFer
 * Date Created: 4/15/2025
 * Description: Data access object for the user entitity.
 */
package com.webcraftsolutions.project02.database;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.webcraftsolutions.project02.database.entities.User;

import java.util.List;

@Dao
public interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User... user);

    @Delete
    void delete(User... user);

    @Query("SELECT * FROM " + ActivitiDatabase.USER_TABLE)
    LiveData<List<User>> getAllUsers();

    @Query("DELETE FROM " + ActivitiDatabase.USER_TABLE)
    void deleteAll();

    @Query("SELECT * FROM " + ActivitiDatabase.USER_TABLE + " WHERE username == :username")
    LiveData<User> getUserByUsername(String username);

    @Query("SELECT * FROM " + ActivitiDatabase.USER_TABLE + " WHERE username == :username")
    User getUserByUsernameSynchronous(String username);

    @Query("SELECT * FROM " + ActivitiDatabase.USER_TABLE + " WHERE id == :userId")
    LiveData<User> getUserByUserId(int userId);

    @Query("SELECT * FROM " + ActivitiDatabase.USER_TABLE + " WHERE id == :userId")
    User getUserByUserIdSynchronous(int userId);
}
