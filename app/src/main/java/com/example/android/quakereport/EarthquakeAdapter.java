package com.example.android.quakereport;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
        final Earthquake currentEqarthquake = getItem(position);

        TextView nameTextView = (TextView) convertView.findViewById(R.id.magnitude_text_view);
        nameTextView.setText(formatMagnitude(currentEqarthquake.getmMagnitude()));


        TextView offsetTextView = (TextView) convertView.findViewById(R.id.offset_text_view);
        TextView locationTextView = (TextView) convertView.findViewById(R.id.location_text_view);
        String[] splits = currentEqarthquake.getmLocation().split(LOCATION_SEPARATOR);
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
        dateTextView.setText(currentEqarthquake.getDateString());

        TextView timeTextView = (TextView) convertView.findViewById(R.id.time_text_view);
        timeTextView.setText(currentEqarthquake.getTimeString());


        return convertView;
    }

    private String formatMagnitude(double magnitude) {
        DecimalFormat formatter = new DecimalFormat("0.0");
        return formatter.format(magnitude);
    }

}
