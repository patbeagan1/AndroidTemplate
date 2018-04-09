package com.example.sample.androidsample;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.sample.androidsample.retrofit.github.GitHubAPI;
import com.example.sample.androidsample.retrofit.github.GithubNetworkRequestTask;
import com.example.sample.androidsample.retrofit.github.models.RootObject;

import java.util.List;

import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();

    /**
     * The method is responsible for setting up the activity when it is created.
     * It is called in onCreate(), which is (unsurprisingly) the method called when the activity is created.
     * <p>
     * onCreate is one of many lifecycle events, which are detailed further down in this file.
     * Check LogCat for the order in which these events occur.
     */
    private void initializeView() {
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onFabButtonClicked();
                    }
                });
    }

    /**
     * This is set in the initializeView method.
     * It sends a network request to github which returns information about a single user.
     */
    private void onFabButtonClicked() {

        // Replace this hardcoded string with a text input, so we can ask for different
        // users without rebuilding.
        final String user = "patBeagan1";
        new GithubNetworkRequestTask(
                // A callback is something that you may not have seen before.
                // It allows you to give an "action" to another method.
                // Essentially, in this case it is saying:
                //  Do the work in GithubNetworkRequestTask, then perform this action when you are done.
                new GitHubAPI.ListReposCallBack() {
                    @Override
                    public void performAction(Response<List<RootObject>> listResponse) {
                        onNetworkRequestSucceeded(listResponse, user);
                    }
                }
        ).execute(user);
    }

    /**
     * This method is called when the network request has successfully succeeded.
     * It is set up in the onFabButtonClicked() method.
     * <p>
     * It prints out the words "hello world", followed by a list of the user's repositories.
     *
     * @param listResponse The response object which was returned by retrofit after making a network request.
     * @param user         The name of the user whose repositories we are attempting to retreive.
     */
    private void onNetworkRequestSucceeded(Response<List<RootObject>> listResponse, String user) {
        List<RootObject> body = listResponse.body();

        // Stringbuilders are used when we want to put a lot of little strings together.
        // They make sure that we don't run out of memory while creating the final string!
        StringBuilder stringBuilder = new StringBuilder();
        if (body != null) {
            for (RootObject rootObject : body) {
                stringBuilder.append(rootObject.getFullName());
                stringBuilder.append("\n");
            }
        }

        // Toasts are small grey popups which appear on the bottom of the screen.
        // They are useful for telling the user about quick updates, or when things finish loading.
        Toast.makeText(
                getBaseContext(),
                "Hello World!\n"
                        + "These are " + user + "'s repositories.\n"
                        + stringBuilder.toString(),
                Toast.LENGTH_LONG
        ).show();
        // Always remember to call "show()" on toasts! Otherwise it stays invisible.
    }

    //----------------------------------------------------------------------------------------------
    // The following methods modify the "Action bar", which is the top bar in the current activity.
    // The action bar is special because it allows you to keep certain actions always available,
    // such as 'search' or 'help', even if you are changing pages.
    //----------------------------------------------------------------------------------------------

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // This creates the "Nugget" menu which is in the right side of the action bar.
        // Inside the menu layout, there is a list of selectable items.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // When something in the "Nugget" menu is selected, this method is called.
        // From here, we can tell the current activity to do something else based on what the clicked item was.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //----------------------------------------------------------------------------------------------
    // The following are activity lifecycle methods.
    // You can learn more about them from the documentation.
    //  https://developer.android.com/guide/components/activities/activity-lifecycle.html
    //
    // If you open up the "Logcat" view, you should be able to see the order in which they are called,
    // when the activity is started.
    // Each of the most common ones has been overridden here, so that you'll have more insight into how they work.
    //----------------------------------------------------------------------------------------------

    /**
     * Called when the current activity is started, and the activity is created by AndroidOS.
     * It is the first lifecycle event, and so is usually used to set up the initial state of the activity.
     *
     * @param savedInstanceState The infomation that was saved the last time this activity was destroyed,
     *                           usually via rotation.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate() called with: savedInstanceState = [" + savedInstanceState + "]");
        initializeView();
    }

    /**
     * Called when the current activity is "visible" even if it is in the background
     */
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    /**
     * Called when the current activity stops being visible.
     * This does not include when it is in the background, in which case it is just visible "offscreen"
     * Activities call onPause() before this method.
     */
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    /**
     * This is called when the current activity is destroyed.
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    /**
     * This is called when the current activity is backgrounded.
     */
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    /**
     * This is called when the current activity is foregrounded.
     */
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }
}
