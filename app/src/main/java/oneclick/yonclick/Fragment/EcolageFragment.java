package oneclick.yonclick.Fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;

import oneclick.yonclick.Adapter.EcolageAdapter;
import oneclick.yonclick.BaseUrl.ApiServiceGetRequest.ApiService;
import oneclick.yonclick.BaseUrl.ApiServiceGetRequest.RetroClient;
import oneclick.yonclick.Model.Model.Ecolage;
import oneclick.yonclick.Model.ModelList.EcolageList;
import oneclick.yonclick.R;
import oneclick.yonclick.Uils.AppUtility;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * A simple {@link Fragment} subclass.
 */
public class EcolageFragment extends Fragment {

     View view;

    private List<Ecolage> ecolages;
    private ProgressDialog pDialog;
    private RecyclerView recyclerView;
    private EcolageAdapter eAdapter;

    LinearLayout loadingView, noDataView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view = inflater.inflate(R.layout.fragment_ecolage, container, false);



         getSchoolData();

        AppUtility.noInternetWarning(view.findViewById(R.id.loadingView), getActivity());
        if (!AppUtility.isNetworkAvailable(getActivity())) {
            showEmptyView();
        }



        return view;
        }

    private void getSchoolData() {
        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading Data.. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        //Creating an object of our api interface
        ApiService api = RetroClient.getApiService();

        /**
         * Calling JSON
         */
        Call<EcolageList> call = api.getEcolageJSON();

        /**
         * Enqueue Callback will be call when get response...
         */

        call.enqueue(new Callback<EcolageList>() {
            @Override
            public void onResponse(Call<EcolageList> call, retrofit2.Response<EcolageList> response) {

                //Dismiss Dialog
                pDialog.dismiss();

                if (response.isSuccessful()) {
                    /**
                     * Got Successfully
                     */
                    ecolages = response.body().getEcoles();
                    recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
                    eAdapter = new EcolageAdapter(getActivity(),ecolages);
                    RecyclerView.LayoutManager eLayoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(eLayoutManager);

                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(eAdapter);

                    Toast.makeText(getActivity(), "Good", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<EcolageList> call, Throwable t) {
                pDialog.dismiss();
                Toast.makeText(getActivity(), "Baddd", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void showEmptyView() {
        if (loadingView != null) {
            loadingView.setVisibility(View.GONE);
        }
        if (noDataView != null) {
            noDataView.setVisibility(View.VISIBLE);
        }
    }

}
