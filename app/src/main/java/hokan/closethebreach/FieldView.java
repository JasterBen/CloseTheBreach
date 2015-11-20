package hokan.closethebreach;

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

import hokan.closethebreach.R;

/**
 * Created by bmeunier on 18/11/15.
 */
public class FieldView extends View {

    private static final float LINE_DP_SIZE = 2;
    private static final float CASE_DP_SIZE = 25;

    protected Paint paint;
    protected int height;
    protected int width;
    protected float strokeWidth;

    protected float xDown = -1;
    protected float yDown = -1;

    protected Rect[] caseTab;
    protected int tabSize;
    protected int horizontalCaseNumber;
    protected int verticalCaseNumber;
    protected float caseWidth;
    protected float caseHeight;

    protected int[] playTab;

    protected boolean redTurn;

    protected GestureDetector detector;

    protected Context context;

    protected int prevSameColor;
    protected int whichWin;
    protected int cpt;


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
        strokeWidth = dpToPx(LINE_DP_SIZE);
        paint.setStrokeWidth(strokeWidth);
        detector = new GestureDetector(context, new GestureListener());
        redTurn = true;
        prevSameColor = 0;
        whichWin = R.string.nobody;
        cpt = 0;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;

        //verticalCaseNumber = (int) (width /dpToPx(CASE_DP_SIZE));
        //horizontalCaseNumber = (int) (height/dpToPx(CASE_DP_SIZE));
        verticalCaseNumber = 3;
        horizontalCaseNumber = 3;

        caseHeight = (height - ((horizontalCaseNumber + 1) * strokeWidth)) / horizontalCaseNumber;
        caseWidth = (width - ((verticalCaseNumber + 1) * strokeWidth)) / verticalCaseNumber;

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
        paint.setColor(Color.GRAY);
        float y = strokeWidth / 2;
        for (int i = 0; i <= horizontalCaseNumber; i++)
        {
            if (i % 3 == 0)
                paint.setColor(Color.BLACK);
            canvas.drawLine(0, y, width, y, paint);
            y += caseHeight + strokeWidth;
            if (paint.getColor() == Color.BLACK)
                paint.setColor(Color.GRAY);
        }
    }

    protected void drawVerticalLines(Canvas canvas)
    {
        paint.setColor(Color.GRAY);
        float x = strokeWidth / 2;
        for (int i = 0; i <= verticalCaseNumber; i++)
        {
            if (i % 3 == 0)
                paint.setColor(Color.BLACK);
            canvas.drawLine(x, 0, x, height, paint);
            x += caseWidth + strokeWidth;
            if (paint.getColor() == Color.BLACK)
                paint.setColor(Color.GRAY);
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

    protected float dpToPx(float dp)
    {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        boolean result = detector.onTouchEvent(event);
        int radius = 30;

        switch (event.getAction()) {
            case MotionEvent.ACTION_UP :
                if (!haveWin() && cpt != tabSize)
                {
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
                                    cpt++;
                                    postInvalidate();
                                }
                                else
                                    Toast.makeText(context, "Case déjà occupée", Toast.LENGTH_SHORT).show();

                                break;
                            }
                        }
                    }
                    if (haveWin() || cpt == tabSize)
                        Toast.makeText(context, context.getString(whichWin), Toast.LENGTH_SHORT).show();
                }

                break;
            default:
                break;
        }
        return result;
    }


    protected void createTabs()
    {
        tabSize = verticalCaseNumber * horizontalCaseNumber;
        caseTab = new Rect[tabSize];
        playTab = new int[tabSize];
        int div = Math.max(verticalCaseNumber, horizontalCaseNumber);
        for (int i = 0; i < tabSize; i++)
        {
            int left = (int) (((i % div) * caseWidth) + (((i % div) + 1) * strokeWidth));
            int top = (int) (((i / div) * caseHeight) + (((i / div) + 1) * strokeWidth));
            int right = (int) (((i % div) + 1) * (caseWidth + strokeWidth));
            int down = (int) (((i / div) + 1) * (caseHeight + strokeWidth));
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


    private boolean haveWin()
    {
        //test en diago
        if (playTab[0] + playTab[4] + playTab[8] == 3 ||
                playTab[0] + playTab[4] + playTab[8] == -3)
        {
            whichWin = playTab[0] == 1 ? R.string.red : R.string.blue;
            return true;
        }
        if (playTab[2] + playTab[4] + playTab[6] == 3 ||
                playTab[2] + playTab[4] + playTab[6] == -3)
        {
            whichWin = playTab[2] == 1 ? R.string.red : R.string.blue;
            return true;
        }


        //test en ligne
        for (int i = 0; i < tabSize; i += 3)
            if (playTab[i] + playTab[i + 1] + playTab[i + 2] == 3 ||
                    playTab[i] + playTab[i + 1] + playTab[i + 2] == -3)
            {
                whichWin = playTab[i] == 1 ? R.string.red : R.string.blue;
                return true;
            }


        //test en colonne
        for (int i = 0; i < horizontalCaseNumber; i++)
            if (playTab[i] + playTab[i + 3] + playTab[i + 6] == 3 ||
                    playTab[i] + playTab[i + 3] + playTab[i + 6] == -3)
            {
                whichWin = playTab[i] == 1 ? R.string.red : R.string.blue;
                return true;
            }

        return false;
    }
}
