package com.shoaib.materialcard;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.service.autofill.ImageTransformation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SportsAdapter extends RecyclerView.Adapter<SportsAdapter.SportsViewHolder> {

    private Context context;
    private ArrayList<Sport> sportArray;


    public SportsAdapter(Context context, ArrayList<Sport> sportArray) {
        this.context = context;
        this.sportArray = sportArray;

    }

    @NonNull
    @Override
    public SportsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false);

        return new SportsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SportsViewHolder holder, int position) {
        Sport currentSport = sportArray.get(position);
        holder.bindTo(currentSport);
        holder.cardClicked(currentSport);



    }



    @Override
    public int getItemCount() {
        return sportArray.size();
    }

    public class SportsViewHolder extends RecyclerView.ViewHolder {
        private ImageView sportsImage;
        private TextView titleText;
        private TextView infoText;
        private CardView itemCard;
        public SportsViewHolder(@NonNull View itemView) {
            super(itemView);
            sportsImage = itemView.findViewById(R.id.sportsImage);
            titleText = itemView.findViewById(R.id.title);
            infoText = itemView.findViewById(R.id.subTitle);
            itemCard = itemView.findViewById(R.id.item_card);

        }

        public void bindTo(Sport currentSport){

            titleText.setText(currentSport.getTitle());
            Glide.with(context).load(currentSport.getimageResource()).into(sportsImage);
            infoText.setText(currentSport.getInfo());


        }

        public void cardClicked(final  Sport  currentSport){
            itemCard.setOnClickListener(new View.OnClickListener() {
                    @Override
                public void onClick(View v) {
                        Intent intent = new Intent(context, DetailActivity.class);
                        intent.putExtra("Image", currentSport.getimageResource());
                        intent.putExtra("Name", currentSport.getTitle());
                        intent.putExtra("detailText", context.getString(R.string.detail_text));

                        context.startActivity(intent);
                    }
            });


        }
    }
}
