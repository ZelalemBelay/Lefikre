package home;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.zeeloo.lefikre.R;

import Utilities.Utility;

/**
 * Created by Zeeloo on 8/4/2015.
 */
class SubmitView
{
    private LinearLayout detailsLinear = HomeActivity.detailsLinear;
    private ListView mListView = HomeActivity.mListView;
    private Activity activity = Utility.getActivity();

    public SubmitView()
    {
        detailsLinear.removeAllViews();

        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams
                (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        if(HomeActivity.x>70)
            p.setMargins(0,HomeActivity.x,0,0);

        else
            p.setMargins(0,70,0,0);

        detailsLinear.setLayoutParams(p);
        
        detailsLinear.setVisibility(View.VISIBLE);
        mListView.setVisibility(View.GONE);

        View v = activity.getLayoutInflater().inflate(R.layout.submit,null);
        detailsLinear.addView(v);
    }

}
