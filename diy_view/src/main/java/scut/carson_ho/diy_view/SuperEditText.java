package scut.carson_ho.diy_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Carson_Ho on 17/8/4.
 */

public class SuperEditText extends AppCompatEditText {
    private static final int DRAWABLE_LEFT = 0;
    private static final int DRAWABLE_TOP = 1;
    private static final int DRAWABLE_RIGHT = 2;
    private static final int DRAWABLE_BOTTOM = 3;



    private Paint mPaint;

    private Drawable mClearDrawable_click;
    private Drawable logo_click;
    private Drawable logo_unclick;

    private Drawable mClearDrawable_unclick;

    private int colorClick;
    private int colorUnClick;
    private int color;

    public SuperEditText(Context context) {
        super(context);

    }

    public SuperEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public SuperEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

//        setBackgroundColor(getResources().getColor(R.color.colorAccent1));
        // 控件资源名称
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SuperEditText);

        mClearDrawable_unclick = getResources().getDrawable(R.drawable.delete);
        logo_click = getResources().getDrawable(R.drawable.account);
        logo_unclick = getResources().getDrawable(R.drawable.account_unclick);

        // 方块颜色（使用十六进制代码，如#333、#8e8e8e）
        int defaultColor1 = context.getResources().getColor(R.color.colorClick); // 默认颜色
        int defaultColor2 = context.getResources().getColor(R.color.colorUnClick); // 默认颜色
        colorClick = typedArray.getColor(R.styleable.SuperEditText_Colorclick, defaultColor1);
        colorUnClick = typedArray.getColor(R.styleable.SuperEditText_Colorunclick, defaultColor2);
        color = colorUnClick;

//      mClearDrawable_unclick.setBounds(0, 0, mClearDrawable_unclick.getIntrinsicWidth()-50, mClearDrawable_unclick.getIntrinsicHeight()-50);
        mClearDrawable_unclick.setBounds(0, 0, 80, 80);

        // 从右侧开始算起，后两个参数 = 宽高
        logo_click.setBounds(0, 0, 80, 80);
        logo_unclick.setBounds(0, 0, 80, 80);

        mPaint = new Paint();
        // mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(1.0f);
        mPaint.setColor(colorUnClick);

        setCompoundDrawables(logo_unclick, null,
               null, null);



    }


    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        setClearIconVisible(hasFocus() && text.length() > 0,hasFocus());
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        setClearIconVisible(focused && length() > 0,focused);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                Drawable drawable = mClearDrawable_unclick;
                if (drawable != null && event.getX() <= (getWidth() - getPaddingRight())
                        && event.getX() >= (getWidth() - getPaddingRight() - drawable.getBounds().width())) {

                    setText("");

                }
                break;
        }
        return super.onTouchEvent(event);
    }

    private void setClearIconVisible(boolean visible,boolean qian) {
        setCompoundDrawables(qian ? logo_click : logo_unclick, null,
                visible ? mClearDrawable_unclick : null, null);
        color = qian ? colorClick : colorUnClick;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(color);

        int x=this.getScrollX();
        int w=this.getMeasuredWidth();
        canvas.drawLine(0, this.getHeight() - 1, w+x,
                this.getHeight() - 1, mPaint);

    }
}

