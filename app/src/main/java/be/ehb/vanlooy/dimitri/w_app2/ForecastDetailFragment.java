package be.ehb.vanlooy.dimitri.w_app2;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
    public static final String TITLE = "title";
    public static final String DETAILTEXT = "text";
    public static final String DETAILTEMP = "temp";
    public static final String DETAILMINTEMP = "min";
    public static final String DETAILMAXTEMP = "max";
    public static final String DETAILICON = "icon";

    private String mTitle;
    private String mTemp, mMinTemp, mMaxTemp;
    private int mIconId, mDescriptionId;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ForecastDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(DETAILICON) && getArguments().containsKey(DETAILTEXT)) {

            mTitle = getArguments().getString(TITLE);
            mIconId = getArguments().getInt(DETAILICON);
            mDescriptionId = getArguments().getInt(DETAILTEXT);
            mTemp = getArguments().getString(DETAILTEMP);
            mMinTemp = getArguments().getString(DETAILMINTEMP);
            mMaxTemp = getArguments().getString(DETAILMAXTEMP);


            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mTitle);
            }
        }else{

            mTitle = getArguments().getString(TITLE);
            mIconId = getActivity().getIntent().getIntExtra(DETAILICON,0);
            mDescriptionId = getActivity().getIntent().getIntExtra(DETAILTEXT,0);
            mTemp = getActivity().getIntent().getStringExtra(DETAILTEMP);
            mMinTemp = getActivity().getIntent().getStringExtra(DETAILMINTEMP);
            mMaxTemp = getActivity().getIntent().getStringExtra(DETAILMAXTEMP);

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mTitle);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.forecast_detail, container, false);

        if (mDescriptionId != 0) {
            ((TextView) rootView.findViewById(R.id.detailText)).setText(getResources().getString(mDescriptionId));
        }
        if (mIconId != 0) {
            ((ImageView) rootView.findViewById(R.id.detailIcon)).setImageResource(mIconId);
        }
        if (mTemp != null) {
            ((TextView) rootView.findViewById(R.id.detailTemp)).setText(mTemp);
        }
        if (mMinTemp != null) {
            ((TextView) rootView.findViewById(R.id.detailMinTemp)).setText(mMinTemp);
        }
        if (mMaxTemp != null) {
            ((TextView) rootView.findViewById(R.id.detailMaxTemp)).setText(mMaxTemp);
        }

        return rootView;
    }
}
