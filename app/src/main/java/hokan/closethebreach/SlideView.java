package hokan.closethebreach;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.lang.ref.WeakReference;

import hokan.closethebreach.utils.OnCursorValueChangeListener;

/**
 * Created by bmeunier on 20/11/15.
 */
public class SlideView extends View {

    protected int barColor;
    protected int circleColor;
    protected int barBetweenCircleColor;
    protected int markerColor;

    protected Paint paint;

    protected int width;
    protected int height;

    protected float circleRadius;
    protected float leftCircleCx;
    protected float rightCircleCx;
    protected float circleCy;

    protected boolean isInLeftCircle;
    protected boolean isInRightCircle;

    protected float leftMin;
    protected float rightMax;

    protected float leftCircleRightMargin;
    protected float rightCircleLeftMargin;

    protected GestureDetector detector;

    protected int markerNumber;
    protected float[] markerTab;
    protected float markerDist;

    protected int leftCircleActualPos;
    protected int rightCircleActualPos;

    protected WeakReference<OnCursorValueChangeListener> cursorInterface = new WeakReference<OnCursorValueChangeListener>(null);

    public SlideView(Context context) {
        super(context);
        init(context);
    }

    public SlideView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getAttrs(context, attrs);
        init(context);
    }

    public SlideView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getAttrs(context, attrs);
        init(context);
    }

    protected void init(Context c)
    {
        detector = new GestureDetector(c, new GestureListener());

        isInLeftCircle = false;
        isInRightCircle = false;

        paint = new Paint();
        paint.setAntiAlias(true);
    }

    protected void getAttrs(Context context, AttributeSet attrs) {

        TypedArray typedArray =
                context.getTheme().obtainStyledAttributes(attrs, R.styleable.SlideView, 0, 0);

        try {
            markerNumber = typedArray.getInt(R.styleable.SlideView_marker_number, 5);
            leftCircleActualPos = typedArray.getInt(R.styleable.SlideView_left_circle, 0);
            rightCircleActualPos = typedArray.getInt(R.styleable.SlideView_right_circle, markerNumber - 1);
            barColor = typedArray.getColor(R.styleable.SlideView_bar_color, Color.GRAY);
            circleColor = typedArray.getColor(R.styleable.SlideView_circle_color, Color.CYAN);
            barBetweenCircleColor = typedArray.getColor(R.styleable.SlideView_bar_between_circle_color, Color.CYAN);
            markerColor = typedArray.getColor(R.styleable.SlideView_marker_color, Color.BLACK);

        } catch (Exception e) {
        }
        finally {
            typedArray.recycle();
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;

        circleRadius = (5 / 2) * (height / 15);
        leftCircleCx = width / 8;
        rightCircleCx = 7 * leftCircleCx;
        circleCy = height / 2;
        leftMin = leftCircleCx;
        rightMax = rightCircleCx;

        markerDist = (rightCircleCx - leftCircleCx) / (markerNumber - 1);
        markerTab = new float[markerNumber];
        for (int i = 0; i < markerNumber; i++)
            markerTab[i] = leftCircleCx + (i * markerDist);

        leftCircleCx = markerTab[leftCircleActualPos];
        rightCircleCx = markerTab[rightCircleActualPos];
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawBar(canvas);
        drawMarker(canvas);
        updateBar(canvas);
        drawCircles(canvas);
    }

    protected void drawBar(Canvas canvas) {
        paint.setColor(barColor);

        float left = width / 8;
        float top = height / 2 - height / 20;
        float right = 7 * left;
        float bottom = height / 2 + height / 20;
        float rx = 50;
        float ry = 50;
        RectF rect = new RectF(left, top, right, bottom);
        canvas.drawRoundRect(rect, rx, ry, paint);
    }

    protected void drawCircles(Canvas canvas)
    {
        paint.setColor(circleColor);

        canvas.drawCircle(leftCircleCx, circleCy, circleRadius, paint);
        canvas.drawCircle(rightCircleCx, circleCy, circleRadius, paint);
    }

    protected void updateBar(Canvas canvas)
    {
        paint.setColor(barBetweenCircleColor);

        float left = leftCircleCx;
        float top = height / 2 - height / 20;
        float right = rightCircleCx;
        float bottom = height / 2 + height / 20;
        canvas.drawRect(left, top, right, bottom, paint);
    }

    protected void drawMarker(Canvas canvas)
    {
        paint.setColor(markerColor);

        float top = circleCy + 3 * circleRadius / 2;
        float bottom = circleCy + 2 * circleRadius;
        for (int i = 0; i < markerNumber; i++)
            canvas.drawLine(markerTab[i], top, markerTab[i], bottom, paint);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        boolean result = detector.onTouchEvent(event);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN :
                isInLeftCircle =
                        (Math.pow (event.getX(), 2) <= Math.pow(leftCircleCx + circleRadius, 2) &&
                                Math.pow(event.getX(), 2) >= Math.pow(leftCircleCx - circleRadius, 2) &&
                                Math.pow(event.getY(), 2) <= Math.pow(circleCy + circleRadius, 2) &&
                                Math.pow(event.getY(), 2) >= Math.pow(circleCy - circleRadius, 2));
                isInRightCircle =
                        (Math.pow (event.getX(), 2) <= Math.pow(rightCircleCx + circleRadius, 2) &&
                                Math.pow(event.getX(), 2) >= Math.pow(rightCircleCx - circleRadius, 2) &&
                                Math.pow(event.getY(), 2) <= Math.pow(circleCy + circleRadius, 2) &&
                                Math.pow(event.getY(), 2) >= Math.pow(circleCy - circleRadius, 2));
                leftCircleRightMargin = rightCircleCx - 2 * circleRadius;
                rightCircleLeftMargin = leftCircleCx + 2 * circleRadius;
                break;
            case MotionEvent.ACTION_MOVE :
                if (isInLeftCircle &&
                        event.getX() >= leftMin &&
                        event.getX() <= leftCircleRightMargin &&
                        event.getX() <= rightMax)
                {
                    leftCircleCx = event.getX();
                }
                else if (isInRightCircle &&
                        event.getX() >= leftMin &&
                        event.getX() <= rightMax &&
                        event.getX() >= rightCircleLeftMargin)
                {
                    rightCircleCx = event.getX();
                }

                postInvalidate();
                return true;
            case MotionEvent.ACTION_UP :
                OnCursorValueChangeListener cursorValueChangeListener = cursorInterface.get();
                if (isInLeftCircle) {
                    leftCircleCx = getClosest(event.getX());
                    if (cursorValueChangeListener != null)
                        cursorValueChangeListener.onCursorValueChangeListener(this, true, leftCircleActualPos);
                }
                else if (isInRightCircle) {
                    rightCircleCx = getClosest(event.getX());
                    if (cursorValueChangeListener != null)
                        cursorValueChangeListener.onCursorValueChangeListener(this, false, rightCircleActualPos);
                }

                postInvalidate();
                return true;
            default:
                break;
        }

        return result;
    }

    protected static float computeDist(float currentX, float markerX)
    {
        return (float) (Math.pow((currentX - markerX), 2));
    }

    protected float getClosest(float currentX)
    {
        float minDist = computeDist(currentX, markerTab[0]);
        float markerX = markerTab[0];
        int pos = 0;

        for (int i = 1; i < markerNumber; i++)
        {
            minDist = Math.min(minDist, computeDist(currentX, markerTab[i]));
            if (minDist == computeDist(currentX, markerTab[i])) {
                markerX = markerTab[i];
                pos = i;
            }
        }

        if (markerX <= leftCircleCx && isInRightCircle)
            markerX = leftCircleCx + markerDist;
        else if (markerX >= rightCircleCx && isInLeftCircle)
            markerX = rightCircleCx - markerDist;

        if (isInLeftCircle)
            leftCircleActualPos = pos;
        else if (isInRightCircle)
            rightCircleActualPos = pos;


        return markerX;
    }

    public int getLeftCircleActualPos() {
        return leftCircleActualPos;
    }

    public int getRightCircleActualPos() {
        return rightCircleActualPos;
    }

    public void setCursorValueChangeListener(OnCursorValueChangeListener cursorInterface)
    {
        this.cursorInterface = new WeakReference<OnCursorValueChangeListener>(cursorInterface);
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {

        protected static final int SWIPE_THRESHOLD = 100;
        protected static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            boolean result = false;

            try {
                float distanceX = e2.getX() - e1.getX();
                float distanceY = e2.getY() - e1.getX();

                if (Math.pow(distanceY, 2) <= Math.pow(circleRadius, 2))
                {
                    boolean isInCircle = isInLeftCircle || isInRightCircle;

                    if (isInCircle && Math.abs(distanceX) > SWIPE_THRESHOLD &&
                            Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD)
                    {
                        result = true;
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return result;
        }
    }
}
