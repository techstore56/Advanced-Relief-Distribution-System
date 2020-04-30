package com.fgtit.record.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fgtit.record.R;
import com.fgtit.record.model.EnrollItem;

import java.util.List;


public class EnrollHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private List<EnrollItem> enrollItems;

    public ImageView userImage;
    public TextView userName;

    public EnrollHolder(View convertView, List<EnrollItem> enrollItems) {
        super(convertView);
        this.enrollItems = enrollItems;

        itemView.setOnClickListener(this);

        userImage = (ImageView) convertView
                .findViewById(R.id.item_enroll_image);

        userName = (TextView) convertView
                .findViewById(R.id.item_enroll_name);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            default:
                break;
        }
    }


}
