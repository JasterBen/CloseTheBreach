package hokan.closethebreach.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;

import java.util.zip.Inflater;

import hokan.closethebreach.R;

/**
 * Created by bmeunier on 18/11/15.
 */
public class FieldView extends View {

    private static final int DP_SIZE = 100;

    protected Paint paint;
    protected int height;
    protected int width;


    public FieldView(Context context) {
        super(context);
        init();
    }

    public FieldView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public FieldView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    protected void init()
    {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        float strokeWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DP_SIZE,
                getResources().getDisplayMetrics());
        paint.setStrokeWidth(strokeWidth);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //canvas.drawLine((width - 2 * DP_SIZE) / 3, 0, (width - 2 * DP_SIZE) / 3, height, paint);
        //canvas.drawLine(2 * ((width - 2 * DP_SIZE) / 3) + DP_SIZE, 0, 2 * ((width - 2 * DP_SIZE) / 3) + DP_SIZE, height, paint);
        //canvas.drawLine(0, (height - 2 * DP_SIZE) / 3, width, (height - 2 * DP_SIZE) / 3, paint);
        //canvas.drawLine(0, 2 * ((height - 2 * DP_SIZE) / 3) + DP_SIZE, width, 2 * ((height - 2 * DP_SIZE) / 3) + DP_SIZE, paint);

    }
}
