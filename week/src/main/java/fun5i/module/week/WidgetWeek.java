package fun5i.module.week;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.Shape;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class WidgetWeek extends RelativeLayout {

    private static final String TAG = "WidgetWeek";

    public interface WeekOnChoice{
        void selectCoice(int position, boolean[] curentData);
    }
    private WeekOnChoice interf1;
    public void selectItem(WeekOnChoice w){
        interf1 = w;
        if (!SELECT2){
            for (int as=0; as< WEEK_LENGTH; as++){
                changeFunction2(as);
            }
        }
        SELECT2 = true;
    }

    private boolean SELECT2= false;
    private void updateInterface(int pos, boolean[] curentData){
        interf1.selectCoice(pos, curentData);

    }

    private static final int WEEK_LENGTH = 7;
    private OtherFunction fun;
    public String[] weekName={
            "S",
            "M",
            "T",
            "W",
            "T",
            "F",
            "S"
    };

    public String[] weekName2={
            "SON",
            "MON",
            "TUE",
            "WED",
            "THU",
            "FRI",
            "SAT"
    };

    private boolean[] onCLickTv={
            false,
            false,
            false,
            false,
            false,
            false,
            false
    };

    private LinearLayout linearLayout;
    private TextView sun, mon,tue, wed, thu, fri, sat;
    private TextView[] textViews={
            sun,
            mon,
            tue,
            wed,
            thu,
            fri,
            sat
    };
    private LinearLayout.LayoutParams parmLin;
    private RelativeLayout.LayoutParams parmRel;
    private int FONT_WIDTH = 70;
    private int FONT_MARGIN = (int) FONT_WIDTH/18;
    private int FONT_PADDING = (int) FONT_WIDTH/2-(FONT_WIDTH/9);
    private String FONT_COLOR = "#000000";
    private String FONT_BACKGROUND_COLOR = "#2196F3";
    private boolean FONT_WEIGHT = false;

    public WidgetWeek(Context context) {
        super(context);
        fun = new OtherFunction();
    }

    public WidgetWeek(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        fun = new OtherFunction();

        linearLayout = new LinearLayout(getContext());
        linearLayout.setId(generateViewId());
        parmRel = new RelativeLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT
        );
        parmLin = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT
        );
        setLayoutParams(parmRel);
        parmRel.addRule(RelativeLayout.CENTER_HORIZONTAL);
        linearLayout.setLayoutParams(parmRel);
        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        addView(linearLayout);

        setBackgroundColor(Color.TRANSPARENT);

        //init component
        for (int i=0; i< WEEK_LENGTH; i++){
            textViews[i] = newText(weekName[i]);
        }

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.widget_week);
        int count = typedArray.getIndexCount();

        for (int i=0; i<count; i++){
            int attrib = typedArray.getIndex(i);
            if (attrib == R.styleable.widget_week_size) {
                int newSize = typedArray.getDimensionPixelSize(attrib, 10);
                //init component
                for (int a=0; a< WEEK_LENGTH; a++){
                     seTVSize(textViews[a], (int) fun.pxToDP(newSize));
                }
            }else if (attrib == R.styleable.widget_week_activeBG) {
                int input = typedArray.getInt(attrib,0);
                //init component
                for (int a=0; a< WEEK_LENGTH; a++){
                    if (a == input){
                        onCLickTv[a] = true;
                        uxBackground(a, FONT_BACKGROUND_COLOR);
                    }
                }
            }else if (attrib == R.styleable.widget_week_padding) {
                //relative
            }else if (attrib == R.styleable.widget_week_margin) {
                int newSize = typedArray.getDimensionPixelSize(attrib, 0);
                //init component
                for (int a=0; a< WEEK_LENGTH; a++){
                    setMarginText(textViews[a], (int) fun.pxToDP(newSize));
                }
            }else if (attrib == R.styleable.widget_week_backgroundColorS) {
                setFONT_BACKGROUND_COLOR(Integer.toHexString(typedArray.getColor(attrib, -1)));
            }else if (attrib == R.styleable.widget_week_fontColor) {
                String input = Integer.toHexString(typedArray.getColor(attrib, 0));
                FONT_COLOR = input; //setup
                //init component
                for (int a=0; a< WEEK_LENGTH; a++){
                    setFONT_COLOR(
                            a,
                            "#"+input
                    );
                }
            }else if (attrib == R.styleable.widget_week_bold) {
                boolean input = typedArray.getBoolean(attrib, false);

                for (int a=0; a< WEEK_LENGTH; a++){
                    setFONT_BOLD(a, input);
                }
            }
        }
        typedArray.recycle();
        for (int as=0; as< WEEK_LENGTH; as++){
            changeFunction(as);
        }


        for (int i=0; i< WEEK_LENGTH; i++){
            linearLayout.addView(textViews[i]);
        }
    }

    private boolean CHANGE_FUNCTION = true;

    private void changeFunction(final int position){
        textViews[position].setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onCLickTv[position]){
                    onCLickTv[position] = false;
                    clearBackground(position);
                }else{
                    onCLickTv[position] = true;
                    uxBackground(position, FONT_BACKGROUND_COLOR);
                }
            }
        });
    }

    private void changeFunction2(final int position){
        textViews[position].setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (onCLickTv[position]){
                    onCLickTv[position] = false;
                }else{
                    onCLickTv[position] = true;
                }
                updateInterface(position,onCLickTv);
            }
        });
    }

    public void clearBackground(int position){
        textViews[position].setBackgroundResource(0);
    }

    public void setFONT_BACKGROUND_COLOR(String color) {
        this.FONT_BACKGROUND_COLOR = "#"+color;
    }

    public void uxBackground(int position, String color){
        String ss = Integer.toHexString(Color.parseColor(color));
        int[] a = fun.stringToARGB(ss);
        setBackgroundText(textViews[position], a[0],a[1],a[2],a[3]);
    }

    public void setFONT_BOLD(int position, boolean active){
        if (active){
            textViews[position].setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        }else{
            textViews[position].setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        }

    }

    public void setFONT_COLOR(int position, String c){
        String ss = Integer.toHexString(Color.parseColor(c));
        int[] color = fun.stringToARGB(ss);
        textViews[position].setTextColor(Color.argb(color[0],color[1],color[2],color[3]));
    }


    private void setMarginText(TextView textView, int size){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                FONT_WIDTH,
                FONT_WIDTH
        );
        textView.setLayoutParams(params);
        FONT_MARGIN = size;
        params.setMargins(size,size,size,size);
    };

    public void setBackgroundText(TextView textView, int a, int r, int g, int b){
        //padding

        FONT_PADDING = (int) FONT_WIDTH/2-(FONT_WIDTH/4)+(FONT_WIDTH/20);
        ShapeDrawable smallerCircle = new ShapeDrawable(new OvalShape());
        smallerCircle.setPadding(FONT_PADDING,FONT_PADDING,FONT_PADDING,FONT_PADDING);
        smallerCircle.getPaint().setARGB(a , r, g, b );
        Drawable[] drawables = {smallerCircle};
        LayerDrawable layerDrawable = new LayerDrawable(drawables);
        textView.setBackgroundDrawable(layerDrawable);
    }

    private TextView seTVSize(TextView textView, int width){
        FONT_WIDTH = width;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                width,
                width
        );
        textView.setLayoutParams(params);
        int fontsze = (int) width/8;
        textView.setTextSize(  fontsze );

        //margin
        FONT_MARGIN = (int) FONT_WIDTH/18;
        params.setMargins(FONT_MARGIN,FONT_MARGIN,FONT_MARGIN,FONT_MARGIN);

        //padding and color
        setBackgroundText(textView,
                0, //transparancy 100%
                0,
                0,
                0
        );

        return textView;
    }

    private TextView newText(String txt){
        TextView textView = new TextView(getContext());
        textView.setId(generateViewId());
        textView.setText(txt);
        textView.setGravity(Gravity.CENTER_HORIZONTAL);

        textView = seTVSize(textView, 50);
        //setFONT_WIEGHT(textView, true);

        return textView;
    }

    private void initRootLayout(){
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                getViewTreeObserver().removeOnGlobalLayoutListener(this);
                Log.d(TAG, "initRootLayout: "+getWidth());
                //LENIER_WIDTH = getWidth();
            }
        });

    }

    public void setFONT_PADDING(int fontSize){
        FONT_PADDING = (int) fontSize/2-(fontSize/7);
    }


}
