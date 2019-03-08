package oneclick.yonclick.dataa.constant;

public class AppConstants {

    // Integer constants
    public static final int VALUE_ZERO = 0;
    public static final int INDEX_ZERO = 0;
    public static final String EMPTY_STRING = "";
    public static final String COMMA = ",";
    public static final int VALUE_SELECTED = 1;
    public static final int VALUE_NOT_SELECTED = 0;
    public static final int PASSWORD_MIN_CHAR_LIMIT = 6;
    public static final String CURRENCY = "US $";
    public static final String ORDER_STATUS_PENDING = "pending";
    public static final String ORDER_STATUS_COMPLETED = "completed";

    // ISO 8601 date time format
    public static final String DATE_FORMAT_ISO8601 = "yyyy-MM-dd'T'HH:mm:ss";

    // Number of days for pick recent products before
    public static final int NUMBER_OF_DAYS_BEFORE = 30; // @Todo: need to do it value 7

    // search page key
    public static final int INITIAL_PAGE_NUMBER = 1;
    public static final int DEFAULT_PER_PAGE = 10;
    public static final int MAX_PER_PAGE = 100;
    public static final int MAX_POPULAR = 20;
    public static final String SEARCH_KEY = "searchKey";
    public static final String KEY_TITLE = "title";
    public static final String KEY_DATE = "date";
    public static final String KEY_ASC = "asc";
    public static final String KEY_DESC = "desc";

    // authentication
    public static final String DEFAULT_COUNTRY_ALPHA = "BD";
    public static final String DEFAULT_PASSWORD = "1";

    // categories ID
    public static final int CATEGORY_TEC_PRODUCTS_ID = 21;

    // String constants
    public static final String PRODUCT_ID = "id";
    public static final String CATEGORY_ID = "categoryId";
    public static final String PAGE_TITLE = "pageTitle";
    public static final String PAGE_TYPE = "pageType";
    public static final String ORDER_ID = "orderId";

    public static final int
            TYPE_FEATURED = 1,
            TYPE_RECENT = 2,
            TYPE_POPULAR = 3,
            TYPE_CATEGORY = 4;

    public static final int NO_CATEGORY = -1;

    // Large image view
    public static final String KEY_IMAGE_URL = "large_image_url";
    public static final String KEY_PERMISSION = "perm";

}
