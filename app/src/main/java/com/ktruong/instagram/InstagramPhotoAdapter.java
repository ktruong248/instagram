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

    // View lookup cache
    private static class ViewHolder {
        ImageView profileImage;
        TextView userNameText;
        TextView createdTimeView;
        ImageView photoImageView;
        TextView captionText;
        TextView captionUserName;
    }

    public InstagramPhotoAdapter(Context context, List<InstagramPhoto> photos) {
        super(context, 0, photos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_photo, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.profileImage = (ImageView) convertView.findViewById(R.id.profileImage);
            viewHolder.userNameText = (TextView) convertView.findViewById(R.id.userName);
            viewHolder.createdTimeView = (TextView) convertView.findViewById(R.id.createdTime);
            viewHolder.captionText = (TextView) convertView.findViewById(R.id.captionText);
            viewHolder.captionUserName = (TextView) convertView.findViewById(R.id.captionUserName);
            viewHolder.photoImageView = (ImageView) convertView.findViewById(R.id.photoImage);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        InstagramPhoto item = getItem(position);
//        ImageView profileImage = (ImageView) convertView.findViewById(R.id.profileImage);
        ImageView profileImage = viewHolder.profileImage;
        profileImage.setImageResource(0); //clear

        if (item.getProfileImageUrl() != null) {
            Transformation transformation = new RoundedTransformationBuilder()
                    .borderColor(Color.TRANSPARENT).scaleType(ImageView.ScaleType.FIT_CENTER)
                    .borderWidthDp(3)
//                .cornerRadius(500)
                    .cornerRadiusDp(30)
//                .oval(true)
                    .build();
            Picasso.with(getContext()).load(item.getProfileImageUrl()).transform(transformation).resize(150, 0).into(profileImage);
        } else {
            profileImage.setImageResource(R.mipmap.ic_launcher);
        }

//        TextView userNameText = (TextView) convertView.findViewById(R.id.userName);
        TextView userNameText = viewHolder.userNameText;
        userNameText.setText(item.getName());
//        userNameText.setTextColor(Color.BLUE);

//        ImageView photoImageView = (ImageView) convertView.findViewById(R.id.photoImage);
        ImageView photoImageView = viewHolder.photoImageView;
        photoImageView.setImageResource(0);
        if (item.getImageUrl() != null) {
            Picasso.with(getContext()).load(item.getImageUrl()).into(photoImageView);
        } else {
            photoImageView.setImageResource(R.mipmap.ic_launcher);
        }

//        TextView createdTimeView = (TextView) convertView.findViewById(R.id.createdTime);
        TextView createdTimeView = viewHolder.createdTimeView;
        createdTimeView.setText(item.getCreatedTime());
        createdTimeView.setFreezesText(true);

//        TextView captionText = (TextView) convertView.findViewById(R.id.captionText);
        TextView captionText = viewHolder.captionText;
        captionText.setText(item.getCaption());

        //

//        TextView captionUserName = (TextView) convertView.findViewById(R.id.captionUserName);
        TextView captionUserName = viewHolder.captionUserName;
        captionUserName.setText(item.getCaptionFromUserName());
//
//        ImageView likeImageView = (ImageView)convertView.findViewById(R.id.likeImage);
//        Picasso.with(getContext()).load("https://www.iconfinder.com/icons/299063/download/png/128").centerCrop().resize(80,80).into(likeImageView);
//
        return convertView;
    }
}
