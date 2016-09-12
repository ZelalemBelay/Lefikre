package home;

import android.app.ActionBar;
import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.zeeloo.lefikre.R;

import java.util.ArrayList;

import Adapters.DrawerListAdapters;
import Adapters.TopListAdapters;
import Customizers.MyTextView;
import Model.ListModel;
import Utilities.Utility;
import forActionBar.ActionBarTitleFader;
import forActionBar.ZoomingImages;

public class HomeActivity extends Activity
{
    private int mActionBarTitleColor;
    private int mActionBarHeight;
    private int mHeaderHeight;
    private int mMinHeaderTranslation;
    static ListView mListView;
    private ZoomingImages mHeaderPicture;
    private ImageView mHeaderLogo;
    private View mHeader;
    private View mPlaceHolderView;
    private AccelerateDecelerateInterpolator mSmoothInterpolator;
    private RectF mRect1 = new RectF();
    private RectF mRect2 = new RectF();

    private ActionBarTitleFader actionBarTitleFader;
    private SpannableString mSpannableString;

    private TypedValue mTypedValue = new TypedValue();

    ArrayList<ListModel> FAKES, drawer_list;

    DrawerLayout dl;
    static LinearLayout detailsLinear;

    public static SharedPreferences settings;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        settings  =  getSharedPreferences("settings",MODE_MULTI_PROCESS);

        mSmoothInterpolator = new AccelerateDecelerateInterpolator();
        mHeaderHeight = getResources().getDimensionPixelSize(R.dimen.header_height);
        mMinHeaderTranslation = -mHeaderHeight + getActionBarHeight();

        setContentView(R.layout.ac_bar_activity);
        Utility.activity = this;

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        ListView list = (ListView) findViewById(R.id.listView1);

        drawer_list = new ArrayList<ListModel>();

        drawer_list.add(new ListModel("መልዕክቶች ለሱ",R.drawable.lesu));
        drawer_list.add(new ListModel("መልዕክቶች ለሷ",R.drawable.lesua));
        drawer_list.add(new ListModel("አባባሎች",R.drawable.sayings));
        drawer_list.add(new ListModel("የራስዎን ማስገቢያ",R.drawable.mine));
        drawer_list.add(new ListModel("የኔ ምርጫዎች",R.drawable.favs));
        drawer_list.add(new ListModel("ቋንቋ መቀየሪያ",R.drawable.langs));
        drawer_list.add(new ListModel("መዝጊያ",R.drawable.close));

        list.setAdapter(new DrawerListAdapters(this, drawer_list));
        detailsLinear = (LinearLayout)findViewById(R.id.details_linear);

        dl = (DrawerLayout) findViewById(R.id.drawer_layout);

        mSmoothInterpolator = new AccelerateDecelerateInterpolator();
        mHeaderHeight = getResources().getDimensionPixelSize(R.dimen.header_height);
        mMinHeaderTranslation = -mHeaderHeight + getActionBarHeight();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dl.closeDrawer(Gravity.LEFT);

                switch (position) {
                    case 3: {
                        new SubmitView();
                        break;
                    }


                    case 5: {
                        new Language();
                        break;
                    }

                    default: {
                        mListView.setVisibility(View.VISIBLE);
                        detailsLinear.setVisibility(View.GONE);
                        break;
                    }

                }
            }
        });


        mListView = (ListView) findViewById(R.id.listview);
        mHeader = findViewById(R.id.header);
        mHeaderPicture = (ZoomingImages) findViewById(R.id.header_picture);
        mHeaderPicture.setResourceIds(R.drawable.picture0, R.drawable.picture1);
        mHeaderLogo = (ImageView) findViewById(R.id.header_logo);

        mActionBarTitleColor = getResources().getColor(R.color.actionbar_title_color);

        mSpannableString = new SpannableString("የፍቅር መልዕክቶች");
        actionBarTitleFader = new ActionBarTitleFader(mActionBarTitleColor);

        setupActionBar();
        setupListView();
    }

    ListModel clicked;

    public void back(View v)
    {
        mListView.setVisibility(View.VISIBLE);
        detailsLinear.setVisibility(View.GONE);
    }

    public static int x = 70;

    private void setupListView()
    {
        FAKES = new ArrayList<ListModel>();
        int aa[] = new int[]{R.drawable.ic1,R.drawable.ic2,R.drawable.ic3,R.drawable.ic4,R.drawable.ic5};
        String bb[] = new String[]{"ለፍቅር የተጻፉ መልዕክቶች ያሉበት ቦታ ነወ","እየመረጥን ይጠቅማሉ የምንለውን መልሰን ለናንተ"," እናቀርባለን አስተማሪ ነው ይጠቅማል የምትሉትን ላኩልን"," መልሰን ለናንተ እናቀርባለን እናመሰግናለን ","ሀላችሁን ውድድድድድድድ እናረጋችሀለን"};

       int ii = 0;
        for (int i = 0; i < 1000; i++)
        {
            if(ii>4)
                ii=0;
            FAKES.add(new ListModel(bb[ii], aa[ii++], true,i+""));
        }

        mPlaceHolderView = getLayoutInflater().inflate(R.layout.view_header_placeholder, mListView, false);
        mListView.addHeaderView(mPlaceHolderView);
        mListView.setAdapter(new TopListAdapters(this, FAKES));
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                detailsLinear.setVisibility(View.VISIBLE);
                mListView.setVisibility(View.GONE);
                clicked = FAKES.get(position);
                setTextForDetail();

            }
        });


        mListView.setOnScrollListener(new AbsListView.OnScrollListener()
        {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount)
            {
                int scrollY = getScrollY();
                mHeader.setTranslationY(Math.max(-scrollY, mMinHeaderTranslation));
                float ratio = clamp(mHeader.getTranslationY() / mMinHeaderTranslation, 0.0f, 1.0f);
                interpolate(mHeaderLogo, getActionBarIconView(), mSmoothInterpolator.getInterpolation(ratio));
                TITLE_SPAN(clamp(5.0F * ratio - 4.0F, 0.0F, 1.0F));

                x = mHeader.getHeight()- scrollY;
            }
        });
    }


    private void setTextForDetail()
    {
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams
                (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        if(x>70)
        p.setMargins(0,x,0,0);

        else
            p.setMargins(0,70,0,0);

        detailsLinear.setLayoutParams(p);

        MyTextView text = (MyTextView)findViewById(R.id.detail_text);
        text.setText(clicked.getTitle());
    }

    private void TITLE_SPAN(float alpha)
    {
        actionBarTitleFader.setAlpha(alpha);
        mSpannableString.setSpan(actionBarTitleFader, 0, mSpannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        getActionBar().setTitle(mSpannableString);
    }


    public static float clamp(float value, float min, float max) {
        return Math.max(min,Math.min(value, max));
    }


    private void interpolate(View view1, View view2, float interpolation)
    {
        getOnScreenRect(mRect1, view1);
        getOnScreenRect(mRect2, view2);

        float scaleX = 1.0F + interpolation * (mRect2.width() / mRect1.width() - 1.0F);
        float scaleY = 1.0F + interpolation * (mRect2.height() / mRect1.height() - 1.0F);
        float translationX = 0.5F * (interpolation * (mRect2.left + mRect2.right - mRect1.left - mRect1.right));
        float translationY = 0.5F * (interpolation * (mRect2.top + mRect2.bottom - mRect1.top - mRect1.bottom));

        view1.setTranslationX(translationX);
        view1.setTranslationY(translationY - mHeader.getTranslationY());
        view1.setScaleX(scaleX);
        view1.setScaleY(scaleY);
    }

    private RectF getOnScreenRect(RectF rect, View view)
    {
        rect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        return rect;
    }

    public int getScrollY() {
        View c = mListView.getChildAt(0);
        if (c == null) {
            return 0;
        }

        int firstVisiblePosition = mListView.getFirstVisiblePosition();
        int top = c.getTop();

        int headerHeight = 0;
        if (firstVisiblePosition >= 1) {
            headerHeight = mPlaceHolderView.getHeight();
        }

        return -top + firstVisiblePosition * c.getHeight() + headerHeight;
    }

    private void setupActionBar()
    {
        ActionBar actionBar = getActionBar();
        actionBar.setIcon(R.drawable.ic_transparent);

    }

    private ImageView getActionBarIconView() {
        return (ImageView) findViewById(android.R.id.home);
    }

    public int getActionBarHeight() {
        if (mActionBarHeight != 0) {
            return mActionBarHeight;
        }
        getTheme().resolveAttribute(android.R.attr.actionBarSize, mTypedValue, true);
        mActionBarHeight = TypedValue.complexToDimensionPixelSize(mTypedValue.data, getResources().getDisplayMetrics());
        return mActionBarHeight;
    }
}
