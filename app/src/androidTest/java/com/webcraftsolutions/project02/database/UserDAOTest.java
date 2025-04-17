/**
 * Title: CST-338 Project 02: Activiti - UserDAO Test
 * @author Noah deFer
 * Date Created: 4/17/2025
 * Description: Java class for implementation tests for UserDAO.java.
 */
package com.webcraftsolutions.project02.database;

import static org.junit.Assert.*;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.webcraftsolutions.project02.database.entities.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class UserDAOTest {

    // CONSTANT FIELDS

    // User Username/Password String
    private final String TEST_USER = "TEST_USER";

    // FIELDS

    // Database Object
    private ActivitiDatabase db;

    // User Data Access Object
    private UserDAO userDAO;

    // User Object
    private User user;

    /**
     * Runs before each test. Sets up database and user variables.
     * @throws Exception Exception
     */
    @Before
    public void setUp() throws Exception {
        // Get Database
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, ActivitiDatabase.class).build();
        // Get UserDAO
        userDAO = db.userDAO();

        // Get User
        user = new User(TEST_USER, TEST_USER);
        user.setId(1);
    }

    /**
     * Runs after each test. Closes database and invalidates variables.
     * @throws Exception Exception
     */
    @After
    public void tearDown() throws Exception {
        // Close Database
        db.close();

        // Invalidate Variables
        userDAO = null;
        user = null;
    }

    /**
     * Implementation Test for the User Table's Insert method.
     */
    @Test
    public void insert() {
        // Check that user is NOT already in database.
        assertNull(userDAO.getUserByUserIdSynchronous(user.getId()));

        // Insert user.
        userDAO.insert(user);

        // Check that user IS in database.
        User dbUser = userDAO.getUserByUserIdSynchronous(user.getId());
        assertNotNull(dbUser);
        assertEquals(dbUser, user);
    }

    /**
     * Implementation Test for the User Table's Delete method.
     */
    @Test
    public void delete() {
        // Put User in Database
        userDAO.insert(user);

        // Check that User is in Database
        assertNotNull(userDAO.getUserByUserIdSynchronous(user.getId()));
        assertEquals(user, userDAO.getUserByUserIdSynchronous(user.getId()));

        // Delete User from Database
        userDAO.delete(user);

        // Check that User is NOT in Database.
        assertNull(userDAO.getUserByUserIdSynchronous(user.getId()));
    }

    /**
     * Implementation Test for the User Table's getUserByUsername method.
     */
    @Test
    public void getUserByUsernameSynchronous() {
        // Put User in Database
        userDAO.insert(user);

        // Get User from Database
        User dbUser = userDAO.getUserByUsernameSynchronous(user.getUsername());
        assertNotNull(dbUser);
        assertEquals(user, dbUser);
    }

    /**
     * Implementation Test for the User Table's getUserByUserId method.
     */
    @Test
    public void getUserByUserIdSynchronous() {
        // Put User in Database
        userDAO.insert(user);

        // Get User from Database
        User dbUser = userDAO.getUserByUserIdSynchronous(user.getId());
        assertNotNull(dbUser);
        assertEquals(user, dbUser);
    }

    /**
     * Implementation Test for the User Table's Insert method updating pre-existing users.
     */
    @Test
    public void update() {
        // Put User in Database
        userDAO.insert(user);
        User userOld = userDAO.getUserByUserIdSynchronous(user.getId());

        // Update User Entry in Database
        user.setAdmin(true);
        user.setUsername(TEST_USER+TEST_USER);
        userDAO.insert(user);

        // Check old and new versions of users
        User userNew = userDAO.getUserByUserIdSynchronous(user.getId());
        assertNotEquals(userOld, userNew);
    }
}