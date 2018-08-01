package com.example.android.quakereport;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;


/**
 * Created by klaudia on 01/08/18.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    public static final String LOCATION_SEPARATOR = "of";

    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquakes) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.eqarthquake_list_item, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        final Earthquake currentEarthquake = getItem(position);

        TextView magnitudeTextView = (TextView) convertView.findViewById(R.id.magnitude_text_view);
        magnitudeTextView.setText(formatMagnitude(currentEarthquake.getmMagnitude()));
        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeTextView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getmMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);


        TextView offsetTextView = (TextView) convertView.findViewById(R.id.offset_text_view);
        TextView locationTextView = (TextView) convertView.findViewById(R.id.location_text_view);
        String[] splits = currentEarthquake.getmLocation().split(LOCATION_SEPARATOR);
        if(splits.length == 1){
            offsetTextView.setText(R.string.near_the);
        }
        else{
            offsetTextView.setText(splits[0] + "of");
        }
        locationTextView.setText(splits[splits.length-1]);

        /*
        *  if (originalLocation.contains(LOCATION_SEPARATOR)) {
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
         } else {
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
         }
        * */

        TextView dateTextView = (TextView) convertView.findViewById(R.id.date_text_view);
        dateTextView.setText(currentEarthquake.getDateString());

        TextView timeTextView = (TextView) convertView.findViewById(R.id.time_text_view);
        timeTextView.setText(currentEarthquake.getTimeString());

        return convertView;
    }

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

    private String formatMagnitude(double magnitude) {
        DecimalFormat formatter = new DecimalFormat("0.0");
        return formatter.format(magnitude);
    }

}
