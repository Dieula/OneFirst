package oneclick.yonclick.Uils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import oneclick.yonclick.R;
import oneclick.yonclick.dataa.constant.AppConstants;


public class LargeImageViewActivity extends AppCompatActivity {

    private Context mContext;
    private TouchImageView touchImageView;

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
      Intent intent = getIntent();
 /*    String imageUrl = intent.getStringExtra(AppConstants.KEY_IMAGE_URL);

        Glide.with(mContext).
                load(imageUrl).
                placeholder(R.color.imgPlaceholder).
                into(touchImageView);*/
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
