package oneclick.yonclick.Uils;

import android.app.Activity;
import android.content.Intent;

import oneclick.yonclick.Detail.DetailsProduitActivity;
import oneclick.yonclick.activity.LargeImageViewActivity;
import oneclick.yonclick.activity.ProductListActivity;
import oneclick.yonclick.activity.SearchActivity;
import oneclick.yonclick.dataa.constant.AppConstants;

public class ActivityUtils {

    private static ActivityUtils sActivityUtils = null;

    public static ActivityUtils getInstance() {
        if (sActivityUtils == null) {
            sActivityUtils = new ActivityUtils();
        }
        return sActivityUtils;
    }

    public void invokeActivity(Activity activity, Class<?> tClass, boolean shouldFinish) {
        Intent intent = new Intent(activity, tClass);
        activity.startActivity(intent);
        if (shouldFinish) {
            activity.finish();
        }
    }

    public void invokeProducts(Activity activity, String pageTitle, int pageType, int categoryId) {
        Intent intent = new Intent(activity, ProductListActivity.class);
        intent.putExtra(AppConstants.PAGE_TITLE, pageTitle);
        intent.putExtra(AppConstants.PAGE_TYPE, pageType);
        intent.putExtra(AppConstants.CATEGORY_ID, categoryId);
        activity.startActivity(intent);
    }

    public void invokeProductDetails(Activity activity, int productId) {
        Intent intent = new Intent(activity, DetailsProduitActivity.class);
       // intent.putExtra(AppConstants.PRODUCT_ID, productId);
        activity.startActivity(intent);
    }
    public void invokeSearchActivity(Activity activity, String searchKey) {
        Intent intent = new Intent(activity, SearchActivity.class);
        intent.putExtra(AppConstants.SEARCH_KEY, searchKey);
        activity.startActivity(intent);
    }
    public void invokeImage(Activity activity, String imageUrl) {
        Intent intent = new Intent(activity, LargeImageViewActivity.class);
        intent.putExtra(AppConstants.KEY_IMAGE_URL, imageUrl);
        activity.startActivity(intent);
    }
/*
    public void invokeAddressActivity(Activity activity, ArrayList<LineItem> arrayList, boolean editOnly, boolean shouldFinish) {
        Intent intent = new Intent(activity, MyAddressActivity.class);
        intent.putParcelableArrayListExtra(AppConstants.KEY_LINE_ITEM_LIST, arrayList);
        intent.putExtra(AppConstants.KEY_EDIT_ONLY, editOnly);
        activity.startActivity(intent);
        if (shouldFinish) {
            activity.finish();
        }
    }

    public void invokeLoginAndOrder(Activity activity, ArrayList<LineItem> arrayList) {
        Intent intent = new Intent(activity, LoginActivity.class);
        intent.putParcelableArrayListExtra(AppConstants.KEY_LINE_ITEM_LIST, arrayList);
        intent.putExtra(AppConstants.KEY_LOGIN_ORDER, true);
        activity.startActivity(intent);
        activity.finish();
    }

    public void invokeWebPageActivity(Activity activity, String pageTitle, String url) {
        Intent intent = new Intent(activity, WebPageActivity.class);
        intent.putExtra(AppConstants.BUNDLE_KEY_TITLE, pageTitle);
        intent.putExtra(AppConstants.BUNDLE_KEY_URL, url);
        activity.startActivity(intent);
    }

    public void invokeNotifyContentActivity(Activity activity, String title, String message) {
        Intent intent = new Intent(activity, NotificationContentActivity.class);
        intent.putExtra(AppConstants.BUNDLE_KEY_TITLE, title);
        intent.putExtra(AppConstants.BUNDLE_KEY_MESSAGE, message);
        activity.startActivity(intent);
    }



    public void invokeOrder(Activity activity, String orderId) {
        Intent intent = new Intent(activity, OrderConfirmationPage.class);
        intent.putExtra(AppConstants.ORDER_ID, orderId);
        activity.startActivity(intent);
        activity.finish();
    }


    public void invokePlaceOrder(Activity activity, ArrayList<LineItem> arrayList) {
        Intent intentOrder = new Intent(activity, PlaceOrderActivity.class);
        intentOrder.putParcelableArrayListExtra(AppConstants.KEY_LINE_ITEM_LIST, arrayList);
        activity.startActivity(intentOrder);
        activity.finish();
    }
*/

}
