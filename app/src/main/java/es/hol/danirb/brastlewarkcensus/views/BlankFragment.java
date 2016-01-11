package es.hol.danirb.brastlewarkcensus.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import es.hol.danirb.brastlewarkcensus.R;
import es.hol.danirb.brastlewarkcensus.models.Gnome;


public class BlankFragment extends Fragment {
    // parameter gnomelist
    private static final String ARG_GNOMELIST = "gnomelist";

    // TODO: Rename and change types of parameters
    private String mGnomeListParam;


    public BlankFragment() {
        // Required empty public constructor
    }


    public static BlankFragment newInstance(Gnome param1) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_GNOMELIST, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mGnomeListParam = getArguments().getString(ARG_GNOMELIST);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_blank, container, false);

        Button btn_go_list = (Button) v.findViewById(R.id.btn_go_list);

        return v;
    }

    public class BtnListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

        }
    }
}



