/**
 * Title: CST-338 Project 02: Activiti - Activiti Repository
 * @author Noah deFer
 * Date Created: 4/9/2025
 * Description: Repository class for Activiti database.
 */
package com.webcraftsolutions.project02.database;

import com.webcraftsolutions.project02.database.entities.Event;

import java.util.ArrayList;

public class ActivitiRepository {
    // CLASS FIELDS

    // Stores the repository instance.
    private static ActivitiRepository repository;

    // INSTANCE FIELDS

    // Stores the EventDAO instance.

    // Stores an array of Event instances.
    private ArrayList<Event> allEventLogs;

    private final EventDAO eventDAO;

    // CONSTRUCTORS

    // METHODS
}
