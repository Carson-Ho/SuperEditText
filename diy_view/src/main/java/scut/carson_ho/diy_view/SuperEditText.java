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
import android.widget.TextView;

import java.lang.reflect.Field;

/**
 * Created by Carson_Ho on 17/8/4.
 */

public class SuperEditText extends AppCompatEditText {

    /*
    * 定义属性变量
    * */
    private Paint mPaint; // 画笔


    private Drawable mClearDrawable_click;
//    private Drawable  ic_left_click;
    private int  ic_left_click1;    // 左侧图标 资源ID（点击）
    private Drawable  ic_left_click; // 左侧图标（点击）
    private Drawable  ic_left_unclick; // 左侧图标（未点击）
    private int cursor; // 光标

    private Drawable  ic_delete; // 删除图标
    private int  ic_deleteResID; // 删除图标(资源ID)

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

        // 获取控件资源
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SuperEditText);

        // 删除图标
        ic_deleteResID = typedArray.getResourceId(R.styleable.SuperEditText_ic_delete,R.drawable.delete);


        // 1. 根据资源ID获取图标资源（转化成Drawable对象）
        // 2. 再设置图标大小
        ic_delete =  getResources().getDrawable(ic_deleteResID);
        ic_delete.setBounds(0, 0, 50, 50);



//         ic_left_click = typedArray.getResources(R.styleable.SuperEditText_accountClick);
//         ic_left_click = getResources().getDrawable(R.drawable.account);
         ic_left_unclick = getResources().getDrawable(R.drawable.account_unclick);

         ic_left_click1 = typedArray.getResourceId(R.styleable.SuperEditText_accountClick, R.drawable.account);

        cursor = typedArray.getResourceId(R.styleable.SuperEditText_cursor, R.drawable.cursor);


        // 方块颜色（使用十六进制代码，如#333、#8e8e8e）
        int defaultColor1 = context.getResources().getColor(R.color.colorClick); // 默认颜色
        int defaultColor2 = context.getResources().getColor(R.color.colorUnClick); // 默认颜色
        colorClick = typedArray.getColor(R.styleable.SuperEditText_Colorclick, defaultColor1);
        colorUnClick = typedArray.getColor(R.styleable.SuperEditText_Colorunclick, defaultColor2);
        color = colorUnClick;


//       ic_delete.setBounds(0, 0,  ic_delete.getIntrinsicWidth()-50,  ic_delete.getIntrinsicHeight()-50);




         ic_left_click = getResources().getDrawable( ic_left_click1);
        // 从右侧开始算起，后两个参数 = 宽高(px)
         ic_left_click.setBounds(0, 0, 50, 50);
         ic_left_unclick.setBounds(0, 0, 50, 50);

        mPaint = new Paint();
        // mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(1.5f);
        mPaint.setColor(colorUnClick);
        setTextColor(color);

        setCompoundDrawables( ic_left_unclick, null,
               null, null);

        setBackground(null);


        try {
            Field f = TextView.class.getDeclaredField("mCursorDrawableRes");
            f.setAccessible(true);
//            f.set(this, R.drawable.cursor);
            f.set(this, cursor); // 要传入ID

        } catch (Exception e) {
            e.printStackTrace();
        }



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
                Drawable drawable =  ic_delete;
                if (drawable != null && event.getX() <= (getWidth() - getPaddingRight())
                        && event.getX() >= (getWidth() - getPaddingRight() - drawable.getBounds().width())) {

                    setText("");

                }
                break;
        }
        return super.onTouchEvent(event);
    }

    private void setClearIconVisible(boolean visible,boolean qian) {
        setCompoundDrawables(qian ?  ic_left_click :  ic_left_unclick, null,
                visible ?  ic_delete: null, null);
        color = qian ? colorClick : colorUnClick;
        setTextColor(color);




        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(color);
        setTextColor(color);

        int x=this.getScrollX();
        int w=this.getMeasuredWidth();
        canvas.drawLine(0, this.getHeight()-1, w+x,
                this.getHeight() -1, mPaint);

    }
}

