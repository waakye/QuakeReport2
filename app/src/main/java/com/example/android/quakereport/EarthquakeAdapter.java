package com.example.android.quakereport;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by lesterlie on 4/16/17.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private static final String LOG_TAG = EarthquakeAdapter.class.getName();
    private static final String LOCATION_SEPARATOR = " of ";

    public EarthquakeAdapter(Activity context, List<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise, inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.earthquake_list_item, parent, false);
        }

        // Get the {@link Earthquake} object located at this position in the list
        Earthquake currentEarthquake = getItem(position);

        // Find the TextView in the earthquake_list_iteme_list_item.xml layout with the ID quake_magnitude
        TextView magnitudeTextView = (TextView)listItemView.findViewById(R.id.quake_magnitude);
        String formattedMagnitude = formatMagnitude(currentEarthquake.getMagnitude());
        magnitudeTextView.setText(formattedMagnitude);

        // Set the proper background color on the magnitude circle
        // Fetch the background from the TextView, which is a GradientDrawable
        GradientDrawable magnitudeCircle = (GradientDrawable)magnitudeTextView.getBackground();
        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());
        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        // Get the original location string from the Earthquake object
        // which can be in the format of "5 km N of Cairo, Egypt" or "Pacific-Antartic Ridge"
        String originalLocation = currentEarthquake.getLocation();

        // If the original location string (i.e. "5km N of Cairo, Egypt" contains a primary location
        // (Cairo, Egypt) and a location offset (5km N of that city) then store the primary location
        // separately from the location offset in 2 Strings, so they can be displayed in 2 TextViews
        String primaryLocation;
        String locationOffset;

        // Check whether the originalLocation string contains the " of " text
        if (originalLocation.contains(LOCATION_SEPARATOR)) {
            // Split the string into different parts (as an array of Strings) based on the " of "
            // text.  We expect an array of 2 strings, where the first String will be "5km N" and
            // the second String will be Cairo, Egypt
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            // Location offset should be "5km N" + " of " --> "5km N of "
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            // Primary location should be "Cairo, Egypt"
            primaryLocation = parts[1];
        } else {
            // Otherwise, there is no " of " text in the originalLocation string
            // Hence, set the default location offset to say "Near the"
            locationOffset = getContext().getString(R.string.near_the);
            // The primary location will be the full location string "Pacific-Antartic Ridge"
            primaryLocation = originalLocation;
        }


        // Find the TextView with the ID location
        TextView primaryLocationTextView = (TextView)listItemView.findViewById(R.id.primary_location);
        // Get the location from the current Earthquake object and set this text on the location
        // TextView
        primaryLocationTextView.setText(primaryLocation);

        // Find the TextView with the view ID location offset
        TextView locationOffsetView = (TextView)listItemView.findViewById(R.id.location_offset);
        // Display the location offset of the current earthquake in that TextView
        locationOffsetView.setText(locationOffset);

        // Create a new Date object from the time in milliseconds of the earthquake
        Date dateObject = new Date(currentEarthquake.getTimeInMilliseconds());

        // Find the TextView in the earthquake_list_iteme_list_item.xml layout with the ID quake_date
        TextView dateTextView = (TextView)listItemView.findViewById(R.id.quake_date);
        // Format the date string (i.e. "Mar 3 1984")
        String formattedDate = formatDate(dateObject);
        // Get the date from the current Earthquake object and set this text on the date TextView
        dateTextView.setText(formattedDate);

        // Find the TextView with the view ID time
        TextView timeView = (TextView)listItemView.findViewById(R.id.time);
        // Format the time string (i.e. "4:30 PM"
        String formattedTime = formatTime(dateObject);
        // Display the time of the current earthquake in that TextView
        timeView.setText(formattedTime);

        // Return the whole list item layout (containing 3 TextViews) so that it can be shown in
        // the ListView
        return listItemView;
    }

    /**
     * Return the color for the magnitude circle based on the intensity of the earthquake.
     *
     * @param magnitude of the earthquake
     */
    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }

        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }

    /**
     * Return the formatted magnitude string showing 1 decimal place (i.e. "3.2")
     * from a decimal magnitude value.
     */
    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }
}
