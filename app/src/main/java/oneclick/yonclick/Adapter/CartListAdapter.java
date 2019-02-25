package oneclick.yonclick.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import oneclick.yonclick.Model.CartItem;
import oneclick.yonclick.R;
import oneclick.yonclick.dataa.constant.AppConstants;
import oneclick.yonclick.listener.OnItemCheckedListener;
import oneclick.yonclick.listener.OnItemClickListener;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<CartItem> dataList;

    // Listener
    private static OnItemClickListener mListener;
    private static OnItemCheckedListener itemCheckedListener;

    public CartListAdapter(Context context, ArrayList<CartItem> dataList) {
        this.mContext = context;
        this.dataList = dataList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivProductImage;
        private TextView tvProductName, tvCount, tvProductPrice;
        private CheckBox chkCart;
        private ImageButton remove;

        public ViewHolder(final View itemView, int viewType) {
            super(itemView);

            ivProductImage = (ImageView) itemView.findViewById(R.id.ivProductImage);
            tvProductName = (TextView) itemView.findViewById(R.id.tvProductName);
            tvCount = (TextView) itemView.findViewById(R.id.tvCount);
            tvProductPrice = (TextView) itemView.findViewById(R.id.tvProductPrice);
            chkCart = (CheckBox) itemView.findViewById(R.id.chkCart);
            remove = (ImageButton) itemView.findViewById(R.id.remove);

            // listener
            chkCart.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        itemCheckedListener.onItemCheckListener(buttonView, getLayoutPosition(), true);
                    } else {
                        itemCheckedListener.onItemCheckListener(buttonView, getLayoutPosition(), false);
                    }
                }
            });
            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onItemListener(view, getLayoutPosition());
                }
            });
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

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart_list, parent, false);

        return new ViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final CartItem cartItem = dataList.get(position);

        holder.tvProductName.setText(cartItem.name);
        holder.tvProductPrice.setText(AppConstants.CURRENCY + String.valueOf(cartItem.price));
        holder.tvCount.setText(mContext.getResources().getString(R.string.quantity) + String.valueOf(cartItem.quantity));

        if (cartItem.isSelected == AppConstants.VALUE_SELECTED) {
            holder.chkCart.setChecked(true);
        } else {
            holder.chkCart.setChecked(false);
        }

        // set image
        if (!cartItem.images.isEmpty()) {
            Glide.with(mContext)
                    .load(cartItem.images)
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
        return AppConstants.VALUE_ZERO;
    }

    public void setItemClickListener(OnItemClickListener mListener) {
        this.mListener = mListener;
    }

    public void setItemCheckedListener(OnItemCheckedListener mCheckedListener) {
        this.itemCheckedListener = mCheckedListener;
    }

}