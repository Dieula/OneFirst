package oneclick.yonclick.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import oneclick.yonclick.Detail.DetailsProduitActivity;
import oneclick.yonclick.Model.Product;
import oneclick.yonclick.R;
import oneclick.yonclick.Uils.ActivityUtils;
import oneclick.yonclick.Uils.ListTypeShow;
import oneclick.yonclick.dataa.constant.AppConstants;
import oneclick.yonclick.listener.OnItemClickListener;

import static oneclick.yonclick.InterfaceAPI.RestApi.BASE_URL_Image;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {

    private Context mContext;
    private List<Product> dataList;
    private ListTypeShow listTypeShow;

    Product productId;

    // Listener
    public static OnItemClickListener mListener;

   /* public ProductListAdapter(Context context, ArrayList<Product> dataList, ListTypeShow listTypeShow) {
        this.mContext = context;
        this.dataList = dataList;
        this.listTypeShow = listTypeShow;
    }*/


    public ProductListAdapter( Context context,List<Product> dataList, ListTypeShow listTypeShow) {
        this.mContext = context;
        this.dataList = dataList;
        this.listTypeShow = listTypeShow;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
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


                    mListener.onItemListener(view, getLayoutPosition());
                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (listTypeShow == ListTypeShow.GRID) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_grid, parent, false);
            return new ViewHolder(view, viewType);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_linear, parent, false);
            return new ViewHolder(view, viewType);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final Product product = dataList.get(position);

        String imgUrl = BASE_URL_Image+product.getImage();

        holder.tvProductName.setText(product.getName_product());
        holder.tvProductPrice.setText(product.getPrix());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ActivityUtils.getInstance().invokeProductDetailsGood(mContext,  dataList.get(position));
               /* Intent i = new Intent(mContext, DetailsProduitActivity.class);


                mContext.startActivity(i);*/

            }
        });

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

    public void setFilter(ArrayList<Product> productList) {
        dataList = new ArrayList<>();
        dataList.addAll(productList);
        notifyDataSetChanged();
    }
}
