package oneclick.yonclick.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import oneclick.yonclick.Model.Abonnement;
import oneclick.yonclick.Model.Categorie;
import oneclick.yonclick.R;

public class CategorieAdapter extends RecyclerView.Adapter<CategorieAdapter.CustomViewHolder> {

    private Context mContext;
    private List<Categorie> categories;


    public CategorieAdapter( Context mContext,List<Categorie> employees){
        this.categories = employees;
        this.mContext = mContext;
    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category, parent, false);

        return new CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        Categorie categorie = categories.get(position);
        holder.categoryName.setText(categorie.getNombrand());

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
        public TextView categoryName;
        ImageView image;

        public CustomViewHolder(View view) {
            super(view);
            image = (ImageView) view.findViewById(R.id.ivProductImage);
            categoryName = (TextView) view.findViewById(R.id.tvProductName);

        }
    }

}
