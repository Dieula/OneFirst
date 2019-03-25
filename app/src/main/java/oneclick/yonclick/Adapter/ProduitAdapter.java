package oneclick.yonclick.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;
import oneclick.yonclick.Model.Product;
import oneclick.yonclick.R;
import oneclick.yonclick.Detail.DetailsProduitActivity;
import oneclick.yonclick.Uils.ActivityUtils;
import oneclick.yonclick.dataa.preference.AppPreference;

import static oneclick.yonclick.InterfaceAPI.RestApi.BASE_URL_Image;

public class ProduitAdapter extends RecyclerView.Adapter<ProduitAdapter.CustomViewHolder> {

    private Context mContext;
    private List<Product> products;




    public ProduitAdapter( Context mContext,List<Product> employees){
        this.products = employees;
        this.mContext = mContext;
    }


    @Override
    public ProduitAdapter.CustomViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rectangle, parent, false);


        return new ProduitAdapter.CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProduitAdapter.CustomViewHolder holder, final int position) {
        final Product produit = products.get(position);

        /*if (position <= 3) {
            holder.itemView.setVisibility(GONE);
        }
        else {
            holder.itemView.setVisibility(VISIBLE);

        }*/
        holder.categoryName.setText(produit.getName_product());

        holder.price.setText(produit.getPrix());


        String imgUrl = BASE_URL_Image+produit.getImage();


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ActivityUtils.getInstance().invokeProductDetailsGood(mContext,  products.get(position));

                /*Intent i = new Intent(mContext, DetailsProduitActivity.class);
                i.putExtra("prod", produit);

                mContext.startActivity(i);*/


            }
        });


       Glide.with(mContext)
                .load(imgUrl)
                .thumbnail(0.5f)
                .error(R.drawable.bot)
                .placeholder(R.drawable.nutv)
                .into(holder.image);

    }



    @Override
    public int getItemCount() {
        return products.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        public TextView categoryName,price;
        ImageView image;

        public CustomViewHolder(View view) {
            super(view);
            image = (ImageView) view.findViewById(R.id.ivProductImage);
            categoryName = (TextView) view.findViewById(R.id.tvProductName);
            price = (TextView) view.findViewById(R.id.price);


        }
    }

}
