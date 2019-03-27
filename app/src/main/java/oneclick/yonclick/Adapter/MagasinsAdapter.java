package oneclick.yonclick.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import oneclick.yonclick.Model.Magasin;
import oneclick.yonclick.R;
import oneclick.yonclick.Detail.DetailsProduitActivity;
import oneclick.yonclick.Uils.ActivityUtils;
import oneclick.yonclick.dataa.preference.AppPreference;

import static oneclick.yonclick.InterfaceAPI.RestApi.BASE_URL_Image;

public class MagasinsAdapter extends RecyclerView.Adapter<MagasinsAdapter.CustomViewHolder> {

    private Context mContext;
    private List<Magasin> products;




    public MagasinsAdapter( Context mContext,List<Magasin> magasins){
        this.products = magasins;
        this.mContext = mContext;
    }



    @Override
    public MagasinsAdapter.CustomViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rectangle, parent, false);

        return new MagasinsAdapter.CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MagasinsAdapter.CustomViewHolder holder, final int position) {
         final Magasin produit = products.get(position);

        holder.categoryName.setText(produit.getName_busness());


        String imgUrl = BASE_URL_Image+produit.getImage_compagnie();


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ActivityUtils.getInstance().invokeProductDetailsMagasin(mContext,  products.get(position));

            }
        });


       /* holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent acheterDetails = new Intent(mContext,DetailsProduitActivity.class);
                acheterDetails.putExtra("id",produit.getId());
                mContext.startActivity(acheterDetails);

                Toast.makeText(mContext,"Good"+produit.getName_busness(), Toast.LENGTH_SHORT).show();
            }
        });*/



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
