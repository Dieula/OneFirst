package oneclick.yonclick.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import oneclick.yonclick.Model.Historic;
import oneclick.yonclick.R;

public class HistoricArrayAdapter extends ArrayAdapter<Historic> {
    public HistoricArrayAdapter(Context context, ArrayList<Historic> historic) {
        super(context, android.R.layout.simple_list_item_1, historic);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // get the data item for position
        Historic historic = getItem(position);

        // check the existing view being reused
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_historic, parent, false);
        }

        TextView tvProductName = convertView.findViewById(R.id.tvProductName);
        TextView tvDate =  convertView.findViewById(R.id.tvDate);
        TextView tvOrder =  convertView.findViewById(R.id.tvOrder);
        TextView tvProductPrice =  convertView.findViewById(R.id.tvProductPrice);
/*
        tvProductName.setText(historic.getNom_client());
        tvService.setText(historic.getTit_liste());
        tvDate.setText(historic.getCreated());*/

        return convertView;
    }
}
