package oneclick.yonclick.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;
import oneclick.yonclick.Model.Product;
import oneclick.yonclick.R;
import oneclick.yonclick.activity.DetailsProduitActivity;
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
         /*
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent acheterDetails = new Intent(mContext,DetailsProduitActivity.class);
                acheterDetails.putExtra("id",viewType);
                mContext.startActivity(acheterDetails);
                Product P = products.get(viewType);
                Toast.makeText(mContext, P.getName_product(), Toast.LENGTH_SHORT).show();
            }
        });*/


        return new ProduitAdapter.CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProduitAdapter.CustomViewHolder holder, final int position) {
        final Product produit = products.get(position);
        holder.categoryName.setText(produit.getName_product());

        holder.price.setText(produit.getPrix());

     // String imgUrl = produit.getImage();

         String imgUrl = BASE_URL_Image+produit.getImage();
         holder.image.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent acheterDetails = new Intent(mContext,DetailsProduitActivity.class);
                 acheterDetails.putExtra("id",produit.getId());
                 mContext.startActivity(acheterDetails);

                 Toast.makeText(mContext,"Good"+produit.getName_product(), Toast.LENGTH_SHORT).show();
             }
         });



       // holder.image.setImageResource(0);
      /*

     if(Integer.valueOf(produit.getImage())==1)
        {
            Glide.with(mContext)
                    .load(imgUrl)
                    .thumbnail(0.5f)
                    .placeholder(R.drawable.soulier)
                    .into(holder.image);

        }
        else if(Integer.valueOf(produit.getImage())==1)
        {
            Glide.with(mContext)
                    .load(imgUrl)
                    .thumbnail(0.5f)
                    .placeholder(R.drawable.souli)
                    .into(holder.image);
        }
       else if(Integer.valueOf(produit.getImage())==2)
       {
           Glide.with(mContext)
                   .load(imgUrl)
                   .thumbnail(0.5f)
                   .placeholder(R.drawable.valise)
                   .into(holder.image);
       }

       else if(Integer.valueOf(produit.getImage())==3)
       {
           Glide.with(mContext)
                   .load(imgUrl)
                   .thumbnail(0.5f)
                   .placeholder(R.drawable.soulier)
                   .into(holder.image);
       }


        String imageUri = ""+produit.getImage();
*/

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
