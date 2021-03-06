package oneclick.yonclick.Uils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import oneclick.yonclick.activity.Detail.DetailsProduitActivity;
import oneclick.yonclick.activity.Detail.PlatDetailsActivity;
import oneclick.yonclick.Model.Model.Abonnement;
import oneclick.yonclick.Model.Model.GetCategoryWithProduit;
import oneclick.yonclick.Model.Model.GetMarqueWithProduit;
import oneclick.yonclick.Model.Model.Magasin;
import oneclick.yonclick.Model.Model.Plat;
import oneclick.yonclick.Model.Model.Product;
import oneclick.yonclick.Model.Model.Restaurant;
import oneclick.yonclick.activity.activity.AbonnementFormActivity;
import oneclick.yonclick.activity.activity.LargeImageViewActivity;
import oneclick.yonclick.activity.activity.PlatActivity;
import oneclick.yonclick.activity.activity.ProductListActivity;
import oneclick.yonclick.activity.activity.SearchActivity;
import oneclick.yonclick.dataa.constant.AppConstants;

public class ActivityUtils {

    private Context mContext;

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

        Intent i = new Intent(activity, DetailsProduitActivity.class);
        i.putExtra(AppConstants.PRODUCT_ID, productId);
        activity.startActivity(i);

    }

    public void invokeProductDetailsGood(Context mContext, Product productId) {

        Intent i = new Intent(mContext, DetailsProduitActivity.class);
        i.putExtra(AppConstants.produit, productId);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(i);

    }

    public void invokeProductDetailsAbonnement(Context mContext, Abonnement abonnementId) {

        Intent i = new Intent(mContext, AbonnementFormActivity.class);
        i.putExtra(AppConstants.abonnement, abonnementId);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(i);



    }
    public void invokeProductDetailsfood(Context mContext, Restaurant restaurantId) {

        Intent i = new Intent(mContext, PlatActivity.class);
        i.putExtra(AppConstants.restaurant, restaurantId);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(i);

    }


    public void invokeProductDetailsImage(Context mContext) {

        Intent intent = new Intent(mContext, LargeImageViewActivity.class);
        intent.putExtra(AppConstants.KEY_IMAGE_URL,"id");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);


    }



    public void invokeProductDetailsCategorie(Context mContext, GetCategoryWithProduit categorieId) {

        Intent i = new Intent(mContext, DetailsProduitActivity.class);
        i.putExtra(AppConstants.categorie, categorieId);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(i);

    }

    public void invokeProductDetailsBrand(Context mContext, GetMarqueWithProduit brandId) {

        Intent i = new Intent(mContext, DetailsProduitActivity.class);
        i.putExtra(AppConstants.brand, brandId);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(i);

    }

    public void invokeProductDetailsMagasin(Context mContext, Magasin magasinId) {

        Intent i = new Intent(mContext, DetailsProduitActivity.class);
        i.putExtra(AppConstants.magasin, magasinId);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(i);

    }

    public void invokeProductDetailsPlat(Context mContext, Plat platId) {

        Intent i = new Intent(mContext, PlatDetailsActivity.class);
        i.putExtra(AppConstants.plat, platId);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(i);

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

}
