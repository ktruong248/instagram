package com.ktruong.instagram;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

/**
 * Created by ktruong on 2/18/15.
 */
public class InstagramPhotoAdapter extends ArrayAdapter<InstagramPhoto> {

    public InstagramPhotoAdapter(Context context, List<InstagramPhoto> photos) {
        super(context,0, photos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_photo, parent, false);
        }

        InstagramPhoto item = getItem(position);
        ImageView profileImage = (ImageView)convertView.findViewById(R.id.profileImage);
        profileImage.setImageResource(0); //clear

        Transformation transformation = new RoundedTransformationBuilder()
                .borderColor(Color.TRANSPARENT).scaleType(ImageView.ScaleType.FIT_CENTER)
                .borderWidthDp(3)
//                .cornerRadius(500)
                .cornerRadiusDp(30)
//                .oval(true)
                .build();
        Picasso.with(getContext()).load(item.getProfileImageUrl()).transform(transformation).resize(150,0).into(profileImage);

        TextView userNameText = (TextView) convertView.findViewById(R.id.userName);
        userNameText.setText(item.getName());
        userNameText.setTextColor(Color.BLUE);

        ImageView photoImageView = (ImageView) convertView.findViewById(R.id.photoImage);
        photoImageView.setImageResource(0);
        Picasso.with(getContext()).load(item.getImageUrl()).into(photoImageView);

        TextView createdTimeView = (TextView) convertView.findViewById(R.id.createdTime);
        createdTimeView.setText(item.getCreatedTime());
        createdTimeView.setFreezesText(true);

//        TextView commentText = (TextView)convertView.findViewById(R.id.commentText);
//        commentText.setText(item.getComment());

        // findByviewId also slow so there are technique to optimized
//        tvTitle.setText(item.getTitle());
//        tvScore.setText(item.creditScoreLabel());

        return convertView;
    }
}
