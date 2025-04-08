/**
 * Title: Project 02: Activiti - Main Menu Activity
 * Author(s): Noah deFer
 * Date Created: 4/8/2025
 * Description: The main menu for the Activiti application.
 *      Contains buttons that lead to the other sections of the Activiti application.
 */

package com.webcraftsolutions.project02;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // CLASS FIELDS

    // INSTANCE FIELDS

    // METHODS

    /**
     * Called when this activity is created.
     * Sets an OnClickListener for each button.
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // TODO Set OnClickListener for logoutButton

        // TODO Set OnClickListener for mainMenuEventButton

        // TODO Set OnClickListener for mainMenuExerciseButton

        // TODO Set OnClickListener for mainMenuWellnessButton

        // TODO Set OnClickListener for mainMenuTravelButton
    }

    // STATIC METHODS

    /**
     * Creates a toast and displays it to the screen.
     * @param message The message to be displayed.
     */
    public static void toastMaker(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    // GETTERS & SETTERS
}