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

import java.text.NumberFormat;
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
        TextView likeCountText;
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
            viewHolder.likeCountText = (TextView) convertView.findViewById(R.id.likeCounts);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        InstagramPhoto item = getItem(position);
        ImageView profileImage = viewHolder.profileImage;
        profileImage.setImageResource(0); //clear

        if (item.getProfileImageUrl() != null) {
            Transformation transformation = new RoundedTransformationBuilder()
                    .borderColor(Color.TRANSPARENT).scaleType(ImageView.ScaleType.FIT_CENTER)
                    .borderWidthDp(3)
                    .cornerRadiusDp(30)
                    .build();
            Picasso.with(getContext()).load(item.getProfileImageUrl()).transform(transformation).into(profileImage);
        } else {
            profileImage.setImageResource(R.mipmap.ic_launcher);
        }

        TextView userNameText = viewHolder.userNameText;
        userNameText.setText(item.getName());

        ImageView photoImageView = viewHolder.photoImageView;
        photoImageView.setImageResource(0);
        if (item.getImageUrl() != null) {
            Picasso.with(getContext()).load(item.getImageUrl()).into(photoImageView);
        } else {
            photoImageView.setImageResource(R.mipmap.ic_launcher);
        }

        TextView likeCountText = viewHolder.likeCountText;
        String format = NumberFormat.getInstance().format(item.getLikeCounts());
        likeCountText.setText(format + " likes");

        TextView createdTimeView = viewHolder.createdTimeView;
        createdTimeView.setText(item.getCreatedTime());
        createdTimeView.setFreezesText(true);

        TextView captionText = viewHolder.captionText;
        captionText.setText(item.getCaption());

        TextView captionUserName = viewHolder.captionUserName;
        captionUserName.setText(item.getCaptionFromUserName());

        return convertView;
    }
}
