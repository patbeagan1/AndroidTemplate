package com.example.sample.androidsample;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Fragments are like "pages" which can be loaded into activities.
 * They are more flexible than activities, and allow for different layouts in landscape mode.
 *
 * Fragments will become relevant later on, once we've covered activities.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }
}
