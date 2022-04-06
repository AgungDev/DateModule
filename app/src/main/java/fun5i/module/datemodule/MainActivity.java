package fun5i.module.datemodule;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import fun5i.module.week.WidgetWeek;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvOut = findViewById(R.id.textView);
        WidgetWeek ww = findViewById(R.id.ww);
        ww.selectItem(new WidgetWeek.WeekOnChoice() {
            @Override
            public void selectCoice(int position, boolean[] curentData) {
                //MAKE FONT BOLD AND CHANGE COLOR
                Log.d(TAG, "selectCoice: "+position);
                if (curentData[position]){
                    ww.setFONT_BOLD(position, true);//bold fonts
                    ww.uxBackground(position, "#FFF4F4F4");
                    ww.setFONT_COLOR(position, "#D86D6D");
                }else{
                    ww.clearBackground(position);
                    ww.setFONT_COLOR(position, "#FFF4F4F4");
                    ww.setFONT_BOLD(position, false);
                }


                StringBuilder stringBuilder = new StringBuilder();
                String separator = ", ";
                boolean allFalse = true;
                for (int i =0; i< curentData.length; i++){
                    if (curentData[i]){
                        if (stringBuilder.length() >= 3){
                            stringBuilder.append(separator);
                        }
                        stringBuilder.append(ww.weekName2[i].toLowerCase());
                        allFalse = false;
                    }
                }
                if (allFalse){
                    tvOut.setTextSize(13f);
                    tvOut.setTypeface(Typeface.DEFAULT);
                    tvOut.setText("Null");
                }else{
                    tvOut.setTextSize(25f);
                    tvOut.setTypeface(Typeface.DEFAULT_BOLD);
                    tvOut.setText(stringBuilder.toString());
                }


            }
        });

    }
}