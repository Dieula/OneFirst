package oneclick.yonclick.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import oneclick.yonclick.Model.Plat;
import oneclick.yonclick.R;

public class PlatAdapter extends RecyclerView.Adapter<PlatAdapter.CustomViewHolder> {

    private Context mContext;
    private List<Plat> categories;


    public PlatAdapter( Context mContext,List<Plat> employees){
        this.categories = employees;
        this.mContext = mContext;
    }


    @Override
    public PlatAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_plat, parent, false);

        return new PlatAdapter.CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PlatAdapter.CustomViewHolder holder, int position) {
        Plat categorie = categories.get(position);
        holder.categoryName.setText(categorie.getNom_Plats());
        holder.tvProductPrice.setText(categorie.getPrix());
        String imgUrl = categorie.getImage();

        holder.image.setImageResource(0);

/*
       Glide.with(mContext)
                .load(imgUrl)
                .thumbnail(0.5f)
                .placeholder(R.drawable.valise)
                .into(holder.image);*/
    }



    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        public TextView categoryName,tvProductPrice;
        ImageView image;

        public CustomViewHolder(View view) {
            super(view);
            image = (ImageView) view.findViewById(R.id.ivProductImage);
            categoryName = (TextView) view.findViewById(R.id.tvProductName);
            tvProductPrice =(TextView) view.findViewById(R.id.tvProductPrice);
        }
    }
}
