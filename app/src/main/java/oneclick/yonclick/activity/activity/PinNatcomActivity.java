package oneclick.yonclick.activity.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;


import oneclick.yonclick.R;

public class PinNatcomActivity extends BaseActivity{

    // initialize variables
    private Context mContext;
    private Activity mActivity;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        initVariables();
        initView();



    }
    private void initVariables() {
        mContext = getApplicationContext();
        mActivity = PinNatcomActivity.this;

    }
    private void initView() {
        setContentView(R.layout.activity_pin_natcom);


        initToolbar();
        enableBackButton();
        setToolbarTitle(getString(R.string.pin));
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                // Respond to the action bar's Up/Home button
                finish();
                return true;
        /*    case R.id.miShare:
                shareInfo();
                return true;*/
        }
        return super.onOptionsItemSelected(item);
    }
}
