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

public class AbonnementAdapter extends RecyclerView.Adapter<AbonnementAdapter.CustomViewHolder> {

    private Context mContext;
    private List<Abonnement> abonnements;


    public AbonnementAdapter( Context mContext,List<Abonnement> abonnement){
        this.abonnements = abonnement;
        this.mContext = mContext;
    }



    @Override
    public AbonnementAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_abonnement, parent, false);

        return new AbonnementAdapter.CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AbonnementAdapter.CustomViewHolder holder, int position) {
        Abonnement abonnement = abonnements.get(position);
        holder.categoryName.setText(abonnement.getNom());

        String imgUrl = abonnement.getImage();

        Glide.with(mContext)
                .load(imgUrl)
                .thumbnail(0.5f)
                .placeholder(R.drawable.ic_all_products)
                .into(holder.image);
       /* String imgUrl = employee.getImage();

        holder.image.setImageResource(0);*/

    /*    Glide.with(mContext)
                .load(imgUrl)
                .thumbnail(0.5f)
                .placeholder(R.drawable.ic_all_products)
                .into(holder.image);*/
    }



    @Override
    public int getItemCount() {
        return abonnements.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        public TextView categoryName;
        ImageView image;

        public CustomViewHolder(View view) {
            super(view);
            image = (ImageView) view.findViewById(R.id.ivAbonnemnt);
            categoryName = (TextView) view.findViewById(R.id.tvAbonnement);

        }
    }

}
