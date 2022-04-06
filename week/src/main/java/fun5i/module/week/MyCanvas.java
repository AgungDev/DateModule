package fun5i.module.week;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Typeface;

import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

public class MyCanvas {

    private static final int SIZE_DEFAULT_ICON = 120;

    private Context con;

    public MyCanvas(Context c){
        this.con = c;
    }

    public Bitmap circlerShape(int size){
        Bitmap b = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        Canvas cv = new Canvas(b);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, size, size);

        paint.setAntiAlias(true);
        cv.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        // canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        cv.drawCircle(size / 2, size / 2,
                size / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        cv.drawBitmap(b, rect, rect, paint);
        return b;
    }

    public Bitmap iconDateTOP(String text, int color, int font, int styleTypeFace, int size){
        Bitmap bm = Bitmap.createBitmap(SIZE_DEFAULT_ICON, SIZE_DEFAULT_ICON, Bitmap.Config.ARGB_8888);
        Canvas cv = new Canvas(bm);

        Typeface tf = ResourcesCompat.getFont(con, font);

        Rect rect = new Rect();
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        paint.setTypeface(Typeface.create(tf, styleTypeFace));
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setColor(ContextCompat.getColor(con, color));
        paint.setTextSize(size);
        // paintText.setShadowLayer(10f, 10f, 10f, Color.BLACK);

        paint.getTextBounds(text, 0, 0, rect);

        float x = (cv.getWidth() / 2) - (rect.width() / 2) ;
        float y =  cv.getHeight() - (rect.height() / 3);
        cv.drawText(text, x, y, paint);
        return bm;
    }
}