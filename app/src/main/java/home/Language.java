package home;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.zeeloo.lefikre.R;

import Utilities.Utility;

/**
 * Created by Zeeloo on 8/3/2015.
 */
public class Language
{

    private LinearLayout detailsLinear = HomeActivity.detailsLinear;
    private ListView mListView = HomeActivity.mListView;
    private Activity activity = Utility.getActivity();
    private RelativeLayout lin_am,lin_en;
    public static SharedPreferences settings = HomeActivity.settings;
    private String lang="amh";
    SharedPreferences.Editor editor;

    Language()
    {
        setLanguageView();
    }

    public void setLanguageView()
    {
        detailsLinear.setVisibility(View.VISIBLE);
        mListView.setVisibility(View.GONE);

        detailsLinear.removeAllViews();

        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams
                (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        if(HomeActivity.x>70)
            p.setMargins(0,HomeActivity.x,0,0);

        else
            p.setMargins(0,70,0,0);

        detailsLinear.setLayoutParams(p);
        View view = activity.getLayoutInflater().inflate(R.layout.language, null);
        detailsLinear.addView(view);


        lin_am =  (RelativeLayout) view.findViewById(R.id.lin_am);
        lin_en =  (RelativeLayout) view.findViewById(R.id.lin_en);

        lin_am.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                am();
            }
        });

        lin_en.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                en();
            }
        });

        lang = settings.getString("lang", "not");

        if(lang.equals("amh"))
        {
            setWhite(lin_am);
            setGray(lin_en);

        }
        else
        {

            setWhite(lin_en);
            setGray(lin_am);

        }
    }

    public void en()
    {
        setWhite(lin_en);
        setGray(lin_am);

        editor = settings.edit();

        editor.putString("lang", "eng");
        editor.apply();
        editor.commit();
    }


    public void am()
    {
        setWhite(lin_am);
        setGray(lin_en);

        editor = settings.edit();

        editor.putString("lang", "am");
        editor.apply();
        editor.commit();

    }


    public void setWhite(RelativeLayout lb)
    {
        lb.setBackgroundColor(Color.parseColor("#FFFFFF"));
    }


    public void setGray(RelativeLayout lg)
    {
        lg.setBackgroundColor(Color.parseColor("#808080"));
    }

}
