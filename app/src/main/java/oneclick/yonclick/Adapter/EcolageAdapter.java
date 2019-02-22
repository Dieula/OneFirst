package oneclick.yonclick.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import oneclick.yonclick.Model.Categorie;
import oneclick.yonclick.Model.Ecolage;
import oneclick.yonclick.R;

public class EcolageAdapter extends RecyclerView.Adapter<EcolageAdapter.CustomViewHolder> {

    private Context mContext;
    private List<Ecolage> ecolages;


    public EcolageAdapter( Context mContext,List<Ecolage> ecolage){
        this.ecolages = ecolage;
        this.mContext = mContext;
    }


    @Override
    public EcolageAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ecolage, parent, false);

        return new EcolageAdapter.CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(EcolageAdapter.CustomViewHolder holder, int position) {
        Ecolage ecolage = ecolages.get(position);
        holder.ecolageName.setText(ecolage.getNomEcoles());

        String imgUrl = ecolage.getImage();

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
        return ecolages.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        public TextView ecolageName;
        ImageView image;

        public CustomViewHolder(View view) {
            super(view);
            image = (ImageView) view.findViewById(R.id.ivEcoleImage);
            ecolageName = (TextView) view.findViewById(R.id.tvEcoleName);

        }
    }
}
