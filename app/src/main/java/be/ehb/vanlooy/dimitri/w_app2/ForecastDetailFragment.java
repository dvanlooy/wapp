package be.ehb.vanlooy.dimitri.w_app2;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A fragment representing a single Forecast detail screen.
 * This fragment is either contained in a {@link ForecastListActivity}
 * in two-pane mode (on tablets) or a {@link ForecastDetailActivity}
 * on handsets.
 */
public class ForecastDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_TITLE = "title";
    public static final String ARG_DETAIL = "detail";

    private String mTitle;
    private String mDetail;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ForecastDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_TITLE) && getArguments().containsKey(ARG_DETAIL)) {

            mTitle = getArguments().getString(ARG_TITLE);
            mDetail = getArguments().getString(ARG_DETAIL);

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                System.out.println("mTitle === "+mTitle);
                appBarLayout.setTitle(mTitle);
            }
        }else{
            mTitle = getActivity().getIntent().getStringExtra(ARG_TITLE);
            mDetail = getActivity().getIntent().getStringExtra(ARG_DETAIL);

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                System.out.println("mTitle === "+mTitle);
                appBarLayout.setTitle(mTitle);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.forecast_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mDetail != null) {
            ((TextView) rootView.findViewById(R.id.forecast_detail)).setText(mDetail);
        }else{
            ((TextView) rootView.findViewById(R.id.forecast_detail)).setText("DER IS IETS FOUT GELOPEN");
        }

        return rootView;
    }
}
