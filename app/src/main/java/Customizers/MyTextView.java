package Customizers;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class MyTextView extends TextView {

    public MyTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initialize();
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public MyTextView(Context context) {
        super(context);
        initialize();
    }

    private void initialize() {
        setTypeface(new TypeFaces().getTypefaceAmh(getContext()));
    }


}
