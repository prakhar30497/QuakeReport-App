package com.example.android.quakereport;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    public EarthquakeAdapter(Activity context, List<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    private static final String Location_Separator = " of " ;

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Earthquake currentEarthquake = getItem(position);

        String originalLocation = currentEarthquake.getLocation();
        String primaryLocation,locationOffset;

        if(originalLocation.contains(Location_Separator)){
            String[] parts = originalLocation.split(Location_Separator);
            locationOffset = parts[0]+Location_Separator;
            primaryLocation = parts[1];
        }
        else{
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }

        TextView magnitude = (TextView) listItemView.findViewById(R.id.tv_magnitude);
        String formattedMagnitude = formatMagnitude(currentEarthquake.getMagnitude());
        magnitude.setText(formattedMagnitude);

        GradientDrawable magnitudeCircle = (GradientDrawable) magnitude.getBackground();
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());
        magnitudeCircle.setColor(magnitudeColor);

        TextView locationOffsetView = (TextView) listItemView.findViewById(R.id.tv_locationOffset);
        locationOffsetView.setText(locationOffset);

        TextView location = (TextView) listItemView.findViewById(R.id.tv_location);
        location.setText(primaryLocation);

        Date dateObject = new Date(currentEarthquake.getDate());
        TextView date = (TextView) listItemView.findViewById(R.id.tv_date);
        String formattedDate = formatDate(dateObject);
        date.setText(formattedDate);

        TextView time = (TextView) listItemView.findViewById(R.id.tv_time);
        String formattedTime = formatTime(dateObject);
        time.setText(formattedTime);

        return listItemView;
    }

    private int getMagnitudeColor(Double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int)Math.floor(magnitude);

        switch (magnitudeFloor){
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

    private String formatMagnitude(Double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }
}
