package oneclick.yonclick.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import oneclick.yonclick.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EcolageFragment extends Fragment {

View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view = inflater.inflate(R.layout.fragment_ecolage, container, false);





         return view;



    }

}
