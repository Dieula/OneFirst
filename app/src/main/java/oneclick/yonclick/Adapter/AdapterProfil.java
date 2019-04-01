package oneclick.yonclick.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import oneclick.yonclick.Model.Model.Profil;
import oneclick.yonclick.R;

public class AdapterProfil  extends ArrayAdapter<Profil> {
    public AdapterProfil(Context context, ArrayList<Profil> historic) {
        super(context, android.R.layout.simple_list_item_1, historic);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // get the data item for position
        Profil historic = getItem(position);

        // check the existing view being reused
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_profil, parent, false);
        }



        // find the image view
        ImageView ivImage1 = (ImageView) convertView.findViewById(R.id.ivNameImage);
        // clear out image from convertView
        // ivImage1.setImageResource(0);

        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvName);

        tvTitle.setText(historic.getName());


        ivImage1.setImageResource(historic.getImage());
        //Picasso.with(getContext()).load(equipe.getImage()).into(ivImage1);
        return convertView;

    }
}