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

import oneclick.yonclick.Model.Categorie;
import oneclick.yonclick.Model.Product;
import oneclick.yonclick.R;

public class ProduitAdapter extends RecyclerView.Adapter<ProduitAdapter.CustomViewHolder> {

    private Context mContext;
    private List<Product> employees;


    public ProduitAdapter(List<Product> employees)
    {
        this.employees = employees;
        this.mContext = mContext;
    }



    @Override
    public ProduitAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rectangle, parent, false);

        return new ProduitAdapter.CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProduitAdapter.CustomViewHolder holder, int position) {
        Product employee = employees.get(position);
        holder.categoryName.setText(employee.getNom_Produits());
        holder.price.setText(employee.getPrix());
       String imgUrl = employee.getImage();

       // holder.image.setImageResource(0);

      /* Glide.with(mContext)
                .load(imgUrl)
                .thumbnail(0.5f)
                .placeholder(R.drawable.iconlogo)
                .into(holder.image);*/
    }



    @Override
    public int getItemCount() {
        return employees.size();
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
