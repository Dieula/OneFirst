package oneclick.yonclick.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import oneclick.yonclick.Model.Ecolage;
import oneclick.yonclick.Model.Employe;
import oneclick.yonclick.Model.Product;
import oneclick.yonclick.Model.Restaurant;
import oneclick.yonclick.R;
import oneclick.yonclick.activity.PlatActivity;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.CustomViewHolder> {
    private List<Restaurant> restaurants;
    private Context mContext;

    public RestaurantAdapter(Context mContext, List<Restaurant> restaurant){
        this.restaurants = restaurant;
        this.mContext = mContext;
    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_reataurant, parent, false);

        return new CustomViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(RestaurantAdapter.CustomViewHolder holder, int position) {
        Restaurant restaurant = restaurants.get(position);
        holder.restaurantName.setText(restaurant.getName_categorie());

        String imgUrl = restaurant.getImage();

        holder.image.setImageResource(0);

       Glide.with(mContext)
                .load(imgUrl)
                .thumbnail(0.5f)
                .placeholder(R.drawable.valise)
                .into(holder.image);
    }


    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        public TextView restaurantName;
        ImageView image;

        public CustomViewHolder(View view) {
            super(view);
            image = (ImageView) view.findViewById(R.id.ivProductResto);
            restaurantName = (TextView) view.findViewById(R.id.tvName);
            
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                 //   Intent intent=new Intent(mContext, PlatActivity.class);
                    mContext.startActivity(new Intent(mContext,PlatActivity.class));
                    Toast.makeText(mContext, "Viewwww", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}
