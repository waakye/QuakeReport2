package com.example.android.quakereport;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by lesterlie on 4/16/17.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private static final String LOG_TAG = EarthquakeAdapter.class.getName();

    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise, inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        // Get the {@link Earthquake} object located at this position in the list
        Earthquake currentEarthquake = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID quake_magnitude
        TextView magnitudeTextView = (TextView)listItemView.findViewById(R.id.quake_magnitude);
        // Get the magnitude from the current Earthquake object and set this text on the
        // magnitude TextView
        magnitudeTextView.setText(currentEarthquake.getEarthquakeMagnitude());

        // Find the TextView in the list_item.xml layout with the ID quake_location
        TextView locationTextView = (TextView)listItemView.findViewById(R.id.quake_location);
        // Get the location from the current Earthquake object and set this text on the location
        // TextView
        locationTextView.setText(currentEarthquake.getEarthquakeLocation());

        // Find the TextView in the list_item.xml layout with the ID quake_date
        TextView dateTextView = (TextView)listItemView.findViewById(R.id.quake_date);
        // Get the date from the current Earthquake object and set this text on the date TextView
        dateTextView.setText(currentEarthquake.getmEarthquakeDate());

        // Return the whole list item layout (containing 3 TextViews) so that it can be shown in
        // the ListView
        return listItemView;
    }
}
