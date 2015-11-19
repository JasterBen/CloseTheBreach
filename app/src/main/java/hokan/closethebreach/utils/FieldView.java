package hokan.closethebreach.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by bmeunier on 18/11/15.
 */
public class FieldView extends View {

    private static final float LINE_DP_SIZE = 5;
    private static final int HORIZONTAL_CASE_NUMBER = 3;
    private static final int VERTICAL_CASE_NUMBER = 3;

    protected Paint paint;
    protected int height;
    protected int width;
    protected float strokeWidth;

    protected float xDown = -1;
    protected float yDown = -1;

    protected Rect[] caseTab;
    protected int tabSize;
    protected float caseWidth;
    protected float caseHeight;

    protected int[] playTab;

    protected boolean redTurn;

    protected GestureDetector detector;

    protected Context context;




    public FieldView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public FieldView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    public FieldView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    protected void init()
    {
        paint = new Paint();
        paint.setAntiAlias(true);
        strokeWidth = dpToPx();
        paint.setStrokeWidth(strokeWidth);
        detector = new GestureDetector(context, new GestureListener());
        redTurn = true;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;

        caseHeight = (height - ((HORIZONTAL_CASE_NUMBER - 1) * strokeWidth)) / HORIZONTAL_CASE_NUMBER;
        caseWidth = (width - ((VERTICAL_CASE_NUMBER - 1) * strokeWidth)) / VERTICAL_CASE_NUMBER;

        createTabs();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawHorizontalLines(canvas);
        drawVerticalLines(canvas);
        drawCircle(canvas);
    }

    protected void drawHorizontalLines(Canvas canvas)
    {
        paint.setColor(Color.BLACK);
        float y = caseHeight + strokeWidth / 2;
        for (int i = 1; i < HORIZONTAL_CASE_NUMBER; i++)
        {
            canvas.drawLine(0, y, width, y, paint);
            y += caseHeight + strokeWidth;
        }
    }

    protected void drawVerticalLines(Canvas canvas)
    {
        paint.setColor(Color.BLACK);
        float x = caseWidth + strokeWidth / 2;
        for (int i = 1; i < VERTICAL_CASE_NUMBER; i++)
        {
            canvas.drawLine(x, 0, x, height, paint);
            x += caseWidth + strokeWidth;
        }
    }

    protected void drawCircle(Canvas canvas)
    {
        for (int i = 0; i < tabSize; i++)
        {
            if (playTab[i] != 0)
            {
                paint.setColor(playTab[i] == 1 ? Color.RED : Color.BLUE);
                float radius = Math.min(caseHeight, caseWidth) / 4;
                Rect rect = caseTab[i];
                float centerX = (rect.right + rect.left) / 2;
                float centerY = (rect.top + rect.bottom) / 2;
                canvas.drawCircle(centerX, centerY, radius, paint);
            }
        }
    }

    protected float dpToPx()
    {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, LINE_DP_SIZE, getResources().getDisplayMetrics());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        boolean result = detector.onTouchEvent(event);
        int radius = 65;

        switch (event.getAction()) {
            case MotionEvent.ACTION_UP :
                float x = event.getX();
                float y = event.getY();
                if (xDown != -1 && yDown != -1 &&
                        Math.pow((x - xDown), 2) <= Math.pow(radius, 2) &&
                        Math.pow(y - yDown, 2) <= Math.pow(radius, 2))
                {
                    for (int i = 0; i < tabSize; i++) {
                        Rect rect = caseTab[i];
                        if (x >= rect.left && x <= rect.right &&
                                y >= rect.top && y <= rect.bottom)
                        {
                            if (playTab[i] == 0)
                            {
                                playTab[i] = redTurn ? 1 : -1;
                                redTurn = !redTurn;
                                postInvalidate();
                            }
                            else
                                Toast.makeText(context, "Case déjà occupée", Toast.LENGTH_SHORT).show();

                            break;
                        }
                    }
                }
                break;
            default:
                break;
        }

        return result;
    }


    protected void createTabs()
    {
        tabSize = VERTICAL_CASE_NUMBER * HORIZONTAL_CASE_NUMBER;
        caseTab = new Rect[tabSize];
        playTab = new int[tabSize];
        for (int i = 0; i < tabSize; i++)
        {
            int left = (int) ((i % 3) * (caseWidth + strokeWidth));
            int top = (int) ((i / 3) * (caseHeight + strokeWidth));
            int right = (int) ((((i % 3) + 1) * caseWidth) + ((i % 3) * strokeWidth));
            int down = (int) ((((i / 3) + 1) * caseHeight) + ((i % 3) * strokeWidth));
            caseTab[i] = new Rect(left, top, right, down);
            playTab[i] = 0;
        }


    }


    public class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDown(MotionEvent e) {
            xDown = e.getX();
            yDown = e.getY();
            return true;
        }
    }
}
