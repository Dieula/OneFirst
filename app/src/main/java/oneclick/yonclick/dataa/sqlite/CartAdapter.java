package oneclick.yonclick.dataa.sqlite;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import oneclick.yonclick.Model.CartList;
import oneclick.yonclick.R;
import xyz.hanks.library.bang.SmallBangView;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    public SmallBangView mSmallBang;
    String mClickedProductID;
    static List<CartList> dbList;
    DbManager helper;
    private Context mContext;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView cart_item_image, cart_item_delete;
        public TextView cart_item_title, cart_item_price;

        public ViewHolder(View itemView) {
            super(itemView);
            cart_item_title = (TextView) itemView.findViewById(R.id.cart_item_title);
            cart_item_price = (TextView) itemView.findViewById(R.id.cart_item_price);
            cart_item_image = (ImageView) itemView.findViewById(R.id.cart_item_image);
            cart_item_delete = (ImageView) itemView.findViewById(R.id.cart_item_delete);
        }
    }

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mContext;
    }

    // Pass in the contact array into the constructor
    public CartAdapter(Context context, List<CartList> dbList) {
        this.dbList = new ArrayList<CartList>();
        this.mContext = context;
        mContext = context;
        this.dbList = dbList;
    }

    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View cartView = inflater.inflate(R.layout.cart_item, parent, false);
        CartAdapter.ViewHolder viewHolder = new CartAdapter.ViewHolder(cartView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final CartAdapter.ViewHolder viewHolder, int position) {
        mSmallBang = new SmallBangView((Activity) mContext);
        // Get the data model based on position
        final CartList data = dbList.get(position);
        TextView ListTitle = viewHolder.cart_item_title;
        ListTitle.setText(data.getProductName());

        TextView ListPrice = viewHolder.cart_item_price;
        ListPrice.setText(data.getProductPrice() + " Taka");

        final ImageView ListDelete = viewHolder.cart_item_delete;
        ListDelete.setImageResource(R.drawable.delete);

        ImageView ListImage = viewHolder.cart_item_image;
        //ListImage.setImageResource(R.drawable.demo_shirt);
        Glide.with(getContext())
                .load(data.getImageUrl()).into(ListImage);

    }

    private void toast(String text) {
        Toast.makeText(getContext(),text, Toast.LENGTH_SHORT).show();
    }

    public void DeleteFromCartList(){
        dbList= new ArrayList<CartList>();
        helper = DbManager.getInstance(getContext());
        helper.deleteARow(mClickedProductID);
    }

    @Override
    public int getItemCount() {
        return dbList.size();
    }


}
