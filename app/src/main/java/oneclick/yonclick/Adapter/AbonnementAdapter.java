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

import oneclick.yonclick.Model.Abonnement;
import oneclick.yonclick.R;
import oneclick.yonclick.Uils.ActivityUtils;
import oneclick.yonclick.activity.AbonnementFormActivity;

import static oneclick.yonclick.InterfaceAPI.RestApi.BASE_URL_Image;

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
    public void onBindViewHolder(AbonnementAdapter.CustomViewHolder holder, final int position) {
        Abonnement abonnement = abonnements.get(position);
        holder.categoryName.setText(abonnement.getName_subscription());

        String imgUrl = BASE_URL_Image+abonnement.getImage();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.getInstance().invokeProductDetailsAbonnement(mContext,  abonnements.get(position));

            }
        });

        Glide.with(mContext)
                .load(imgUrl)
                .thumbnail(0.5f)
                .placeholder(R.drawable.iconlogo)
                .into(holder.image);
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
     /*       view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    //   Intent intent=new Intent(mContext, PlatActivity.class);
                    mContext.startActivity(new Intent(mContext,AbonnementFormActivity.class));
                    Toast.makeText(mContext, "Viewwww", Toast.LENGTH_SHORT).show();
                }
            });
*/
        }
    }

}
