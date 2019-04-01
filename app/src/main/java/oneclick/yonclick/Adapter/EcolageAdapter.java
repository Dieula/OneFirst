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

import oneclick.yonclick.Model.Model.Ecolage;
import oneclick.yonclick.R;
import oneclick.yonclick.activity.activity.EcolageActivity;

import static oneclick.yonclick.BaseUrl.InterfaceAPIPost.RestApi.BASE_URL_Image;

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
        holder.ecolageName.setText(ecolage.getNom_etablissements());

        String imgUrl = BASE_URL_Image+ecolage.getImage();

        holder.image.setImageResource(0);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext,EcolageActivity.class));
            }
        });


       Glide.with(mContext)
                .load(imgUrl)
                .thumbnail(0.5f)
                .placeholder(R.drawable.valise)
                .into(holder.image);
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
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //   Intent intent=new Intent(mContext, PlatActivity.class);
                    mContext.startActivity(new Intent(mContext,EcolageActivity.class));
                    Toast.makeText(mContext, "Viewwww", Toast.LENGTH_SHORT).show();

                }
            });

        }
    }
}
