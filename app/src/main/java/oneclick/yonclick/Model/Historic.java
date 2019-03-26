package oneclick.yonclick.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class Historic implements Serializable {








    public Historic(JSONObject jsonObject) throws JSONException
    {
        //this.id_demande = jsonObject.getString("id_demande");


    }

    public static ArrayList<Historic> fromJSONArray (JSONArray array)
    {
        ArrayList<Historic> results = new ArrayList<>();
        for (int x= 0; x < array.length(); x++)
        {
            try
            {
                results.add(new Historic(array.getJSONObject(x)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return results;
    }

}
