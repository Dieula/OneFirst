package oneclick.yonclick.activity.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bumptech.glide.Glide;

import oneclick.yonclick.Model.Model.Product;
import oneclick.yonclick.R;
import oneclick.yonclick.View.TouchImageView;
import oneclick.yonclick.dataa.constant.AppConstants;

public class LargeImageViewActivity extends AppCompatActivity {

    private Context mContext;
    private TouchImageView touchImageView;
    Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        initFunctionality();
    }

    private void initView() {
        setContentView(R.layout.activity_large_image);

        mContext = LargeImageViewActivity.this;
        touchImageView = (TouchImageView) findViewById(R.id.largeImageView);
    }

    private void initFunctionality() {

      /* if(getIntent().getStringArrayExtra(AppConstants.KEY_IMAGE_URL) != null){
            product = (Product) getIntent().getSerializableExtra("large_image_url");
        }else{
            System.out.println("PROD : NO DETAILS");
        }
       // System.out.println("PROD INFO : "+product.getName_product());
      */

        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra(AppConstants.KEY_IMAGE_URL);

        Glide.with(mContext).
                load(imageUrl).
                placeholder(R.color.imgPlaceholder).
                into(touchImageView);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
