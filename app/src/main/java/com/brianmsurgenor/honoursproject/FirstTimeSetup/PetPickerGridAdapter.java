package com.brianmsurgenor.honoursproject.FirstTimeSetup;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.brianmsurgenor.honoursproject.R;
import com.easyandroidanimations.library.Animation;
import com.easyandroidanimations.library.BounceAnimation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brian on 09/12/2015.
 */
public class PetPickerGridAdapter extends RecyclerView.Adapter<PetPickerGridAdapter.ViewHolder> {

    private List<Integer> mItems;
    private List<LinearLayout> backgroundList;
    private Context mContext;

    public PetPickerGridAdapter(ContentResolver contentResolver, Context context) {
        super();
        mItems = new ArrayList<>();
        backgroundList = new ArrayList<>();
        mContext = context;

        //Add items to mItems
        mItems.add(R.drawable.frog);
        mItems.add(R.drawable.puppy);
        mItems.add(R.drawable.cat);
        mItems.add(R.drawable.turtle);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.picker_adapter, viewGroup, false);
        final ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {

        final int id = mItems.get(i);
        viewHolder.imgThumbnail.setImageResource(id);
        backgroundList.add(viewHolder.petBackground);

        viewHolder.petHolder.setOnClickListener(new View.OnClickListener() {
            boolean selected = false;

            @Override
            public void onClick(View v) {

                if(!selected){
                    selected = true;
                    unselectAll();
                    viewHolder.petBackground.setBackgroundColor(Color.YELLOW);

                    new BounceAnimation(viewHolder.imgThumbnail)
                            .setNumOfBounces(3)
                            .setDuration(Animation.DURATION_SHORT)
                            .animate();

                    SetupUserActivity.petSelected(id);
                } else {
                    selected = false;
                    SetupUserActivity.petSelected(0);
                    viewHolder.petBackground.setBackgroundColor(mContext.getResources().
                            getColor(R.color.primaryBackground));
                }

            }
        });
    }

    private void unselectAll() {
        for (LinearLayout l: backgroundList) {
            l.setBackgroundColor(mContext.getResources().getColor(R.color.primaryBackground));
        }
    }


    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imgThumbnail;
        public CardView petHolder;
        public LinearLayout petBackground;

        public ViewHolder(View itemView) {
            super(itemView);
            petHolder = (CardView) itemView.findViewById(R.id.item_CardView);
            petBackground = (LinearLayout) itemView.findViewById(R.id.linearItem);
            imgThumbnail = (ImageView) itemView.findViewById(R.id.itemImage);
        }
    }
}
