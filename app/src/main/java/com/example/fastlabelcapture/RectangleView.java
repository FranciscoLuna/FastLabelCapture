package com.example.fastlabelcapture;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * This class create a drawable dynamic box by touching the screen.
 */
public class RectangleView extends View {

    Point pointInit = null;
    Point pointEnd = null;

    int left, top, right, bottom;
    boolean enoughDimension = false;

    static int DIMENSION_FREE = 0;
    static int DIMENSION_1_1 = 1;
    // static int DIMENSION_4_3 = 2;

    int dimension = DIMENSION_FREE;

    Paint paint;
    Canvas canvas;

    /**
     * Basic constructor
     * @param context
     */
    public RectangleView(Context context) {
        super(context);
        paint = new Paint();
        setFocusable(true);
        canvas = new Canvas();
    }

    /**
     * Constructor that allows to define a set of attributes
     * @param context
     * @param attrs
     */
    public RectangleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        setFocusable(true);
        canvas = new Canvas();
    }

    /**
     * Constructor that allows to define a set of attributes and the style
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public RectangleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        setFocusable(true);
        canvas = new Canvas();
    }

    /**
     * Method to determine if the box have a square shape or free dimension relation.
     * @param dimension
     */
    public void setDimension(int dimension){
        if(Math.abs(dimension) < 3){
            this.dimension = dimension;
        }
    }

    /**
     * This method take the data obtained from @onTouchEvent to determine the position and shape
     * of the square.
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {

        // Definición de los puntos
        if(pointInit == null)
            return;

        // Manage the creation of a square if it is the selected dimension
        if(dimension == DIMENSION_1_1){
            int height = Math.abs(pointEnd.y - pointInit.y);
            int width = Math.abs(pointEnd.x - pointInit.x);
            if(height >= width){
                pointEnd.y = pointInit.y < pointEnd.y ? pointInit.y+width :pointInit.y-width;
            }else{
                pointEnd.x = pointInit.x < pointEnd.x ? pointInit.x+height :pointInit.x-height;
            }
        }

        // To manage if user draw the box to up or left
        left = pointInit.x < pointEnd.x ? pointInit.x : pointEnd.x;
        top = pointInit.y < pointEnd.y ? pointInit.y : pointEnd.y;
        right = pointInit.x > pointEnd.x ? pointInit.x : pointEnd.x;
        bottom = pointInit.y > pointEnd.y ? pointInit.y : pointEnd.y;

        //Log.i("RECT", "(" + left + "," + top + "," + right + "," + bottom + ")");

        // The short rectangles are not drawn.
        if(right - left >= 30 && bottom - top >= 30) {

            // Creando el cuadrado
            paint.setAntiAlias(true);
            paint.setDither(true);
            paint.setStrokeJoin(Paint.Join.ROUND);
            paint.setStrokeWidth(5);

            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.parseColor("#AADB1255"));
            //paint.setStrokeWidth(2);


            canvas.drawRect(left, top, right, bottom, paint);

            enoughDimension = true;

        }else{
            enoughDimension = false;
        }

    }

    /**
     * This event handles the finger position in the screen when it's touched
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int eventAction = event.getAction();

        int X = (int) event.getX();
        int Y = (int) event.getY();

        switch (eventAction){
            // When start to touch
            case MotionEvent.ACTION_DOWN:

                //initialize rectangle.
                pointInit = new Point();
                pointInit.x = X;
                pointInit.y = Y;

                pointEnd = new Point();
                pointEnd.x = X + 30;
                pointEnd.y = Y + 30;

                break;
            case MotionEvent.ACTION_UP:
                break;
            // During the finger movement while the finger continues touching
            case MotionEvent.ACTION_MOVE:
                pointEnd.x = X;
                pointEnd.y = Y;
                // Log.i("POS", "(" + X + "," + Y + ")");
                break;
        }
        // redraw the canvas
        invalidate();
        return true;

    }

}
