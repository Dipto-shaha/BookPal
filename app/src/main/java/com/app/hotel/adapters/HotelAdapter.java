package com.app.hotel.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.app.hotel.R;
import com.app.hotel.activities.HotelDetailsActivity;
import com.app.hotel.viewModels.Hotel;
import com.squareup.picasso.Picasso;

import java.util.List;


public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.HotelViewHolder> {
    private final Context mContext;
    private final List<Hotel> hotels;

//    private OnItemClickListener mListener;

    public HotelAdapter(Context context, List<Hotel> uploads) {
        mContext = context;
        hotels = uploads;
    }

    @Override
    public HotelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.hotel, parent, false);
        return new HotelViewHolder(v);
    }

    @Override
    public void onBindViewHolder(HotelViewHolder holder, int position) {
        Hotel uploadCurrent = hotels.get(position);

        holder.hotelName.setText(uploadCurrent.getName());
        holder.hotelLocation.setText(uploadCurrent.getLocation());
        holder.hotelPrice.setText("BDT " + uploadCurrent.getPrice() + "/DAY");
        Picasso.get()
                .load(uploadCurrent.getImageUrl())
                .fit()
                .centerCrop()
                .into(holder.hotelImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, holder.hotelName.getText().toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, HotelDetailsActivity.class);
//                intent.putExtra("name")

                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return hotels.size();
    }

    public class HotelViewHolder extends RecyclerView.ViewHolder {
        public TextView hotelName, hotelLocation,hotelPrice;
        public ImageView hotelImage;

        public HotelViewHolder(View itemView) {
            super(itemView);

            hotelName = itemView.findViewById(R.id.hotelName);
            hotelPrice = itemView.findViewById(R.id.hotelPrice);
            hotelLocation = itemView.findViewById(R.id.hotelLocation);
            hotelImage = itemView.findViewById(R.id.hotelImage);
        }


    }


//    @Override
//    public void onClick(View v) {
//        if (mListener != null) {
//            int position = getAbsoluteAdapterPosition();
//            if (position != RecyclerView.NO_POSITION) {
//                mListener.onItemClick(position);
//            }
//        }
//    }
//
//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        menu.setHeaderTitle("Select Action");
//        MenuItem doWhatever = menu.add(Menu.NONE, 1, 1, "Do whatever");
//        MenuItem delete = menu.add(Menu.NONE, 2, 2, "Delete");
//
//        doWhatever.setOnMenuItemClickListener(this);
//        delete.setOnMenuItemClickListener(this);
//    }
//
//    @Override
//    public boolean onMenuItemClick(MenuItem item) {
//        if (mListener != null) {
//            int position = getAbsoluteAdapterPosition();
//            if (position != RecyclerView.NO_POSITION) {
//
//                switch (item.getItemId()) {
//                    case 1:
//                        mListener.onWhatEverClick(position);
//                        return true;
//                    case 2:
//                        mListener.onDeleteClick(position);
//                        return true;
//                }
//            }
//        }
//        return false;
//    }
}

//public interface OnItemClickListener {
//    void onItemClick(int position);
//
//    void onWhatEverClick(int position);
//
//    void onDeleteClick(int position);
//}
//
//    public void setOnItemClickListener(OnItemClickListener listener) {
//        mListener = listener;
//    }
//}
//}

