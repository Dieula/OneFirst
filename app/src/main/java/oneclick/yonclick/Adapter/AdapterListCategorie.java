package oneclick.yonclick.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;


import oneclick.yonclick.Model.Model.GetCategoryWithProduit;
import oneclick.yonclick.R;
import oneclick.yonclick.Uils.ActivityUtils;
import oneclick.yonclick.Uils.ListTypeShow;
import oneclick.yonclick.listener.OnItemClickListener;

import static oneclick.yonclick.BaseUrl.InterfaceAPIPost.RestApi.BASE_URL_Image;

public class AdapterListCategorie extends RecyclerView.Adapter<AdapterListCategorie.ViewHolder> {

    private Context mContext;
    private List<GetCategoryWithProduit> dataList;
    private ListTypeShow listTypeShow;

    // Listener
    public static OnItemClickListener mListener;



    public AdapterListCategorie(Context context, List<GetCategoryWithProduit> dataList, ListTypeShow listTypeShow)
    {
        this.mContext = context;
        this.dataList = dataList;
        this.listTypeShow = listTypeShow;
    }




    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView ivProductImage;
        private TextView tvProductName, tvProductPrice, tvOrderCount, tvRattingCount;
        private RatingBar ratingBar;

        public ViewHolder(final View itemView, int viewType) {
            super(itemView);

            ivProductImage = (ImageView) itemView.findViewById(R.id.ivProductImage);
            tvProductName = (TextView) itemView.findViewById(R.id.tvProductName);
            tvProductPrice = (TextView) itemView.findViewById(R.id.tvProductPrice);
            tvOrderCount = (TextView) itemView.findViewById(R.id.tvOrderCount);


            // listener
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                  //  Intent intent = new Intent(mC)

                 //   mListener.onItemListener(view, getLayoutPosition());
                }
            });
        }
    }

    @Override
    public AdapterListCategorie.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {

        if (listTypeShow == ListTypeShow.GRID) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_grid, parent, false);
            return new AdapterListCategorie.ViewHolder(view, viewType);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_linear, parent, false);
            return new AdapterListCategorie.ViewHolder(view, viewType);
        }
    }

    @Override
    public void onBindViewHolder(AdapterListCategorie.ViewHolder holder, final int position) {

        final GetCategoryWithProduit product = dataList.get(position);

        String imgUrl = BASE_URL_Image+product.getImage();


        holder.tvProductName.setText(product.getNameProduct());
        holder.tvProductPrice.setText(product.getPrix());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ActivityUtils.getInstance().invokeProductDetailsCategorie(mContext,  dataList.get(position));

            }
        });

        holder.tvProductName.setText(product.getNameProduct());

        if (!product.getImage().isEmpty()) {
            Glide.with(mContext)
                    .load(imgUrl)
                    .placeholder(R.color.imgPlaceholder)
                    .centerCrop()
                    .into(holder.ivProductImage);
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    public void setItemClickListener(OnItemClickListener mListener) {
        if (mListener != null) {
            this.mListener = (OnItemClickListener) mListener;
        }
    }

    public void setFilter(ArrayList<GetCategoryWithProduit> productList) {
        dataList = new ArrayList<>();
        dataList.addAll(productList);
        notifyDataSetChanged();
    }
}
