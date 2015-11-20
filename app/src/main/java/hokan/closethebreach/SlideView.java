package hokan.closethebreach;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by bmeunier on 20/11/15.
 */
public class SlideView extends View {

    protected Context context;
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

    public SlideView(Context context) {
        super(context);
        init(context);
    }

    public SlideView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SlideView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    protected void init(Context c)
    {
        this.context = c;

        detector = new GestureDetector(context, new GestureListener());

        isInLeftCircle = false;
        isInRightCircle = false;

        paint = new Paint();
        paint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;

        circleRadius = (5 / 2) * (height / 20);
        leftCircleCx = width / 8 + 2 * circleRadius;
        rightCircleCx = 7 * width / 8 - 2 * circleRadius;
        circleCy = height / 2;
        leftMin = leftCircleCx;
        rightMax = rightCircleCx;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawBar(canvas);
        updateBar(canvas);
        drawCircles(canvas);
    }

    protected void drawBar(Canvas canvas) {
        paint.setColor(Color.BLACK);
        float left = width / 8;
        float top = height / 2 - height / 20;
        float right = 7 * left;
        float bottom = height / 2 + height / 20;
        float rx = 50;
        float ry = 50;
        canvas.drawRoundRect(left, top, right, bottom, rx, ry, paint);
    }

    protected void drawCircles(Canvas canvas)
    {
        paint.setColor(Color.BLUE);

        canvas.drawCircle(leftCircleCx, circleCy, circleRadius, paint);
        canvas.drawCircle(rightCircleCx, circleCy, circleRadius, paint);
    }

    protected void updateBar(Canvas canvas)
    {
        paint.setColor(Color.RED);

        float left = leftCircleCx;
        float top = height / 2 - height / 20;
        float right = rightCircleCx;
        float bottom = height / 2 + height / 20;
        canvas.drawRect(left, top, right, bottom, paint);
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
                if (isInLeftCircle && event.getX() >= leftMin && event.getX() <= leftCircleRightMargin &&
                        event.getX() <= rightMax)
                    leftCircleCx = event.getX();
                else if (isInRightCircle && event.getX() >= leftMin && event.getX() <= rightMax &&
                        event.getX() >= rightCircleLeftMargin)
                    rightCircleCx = event.getX();

                postInvalidate();
                return true;
            default:
                break;
        }

        return result;
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
