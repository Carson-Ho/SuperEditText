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


    private int  ic_deleteResID; // 删除图标 资源ID
    private Drawable  ic_delete; // 删除图标

    private int  ic_left_clickResID;    // 左侧图标 资源ID（点击）
    private Drawable  ic_left_click; // 左侧图标（点击）

    private int  ic_left_unclickResID;    // 左侧图标 资源ID（未点击）
    private Drawable  ic_left_unclick; // 左侧图标（未点击）

    private  int left_x,left_y; // 左侧图标起点
    private int left_width; // 左侧图标宽（px）
    private int left_height; // 左侧图标高（px）

    private  int delete_x,delete_y; // 左侧图标起点
    private int delete_width; // 左侧图标宽（px）
    private int delete_height; // 左侧图标高（px）

    private int cursor; // 光标

    

    private int lineColor_click;
    private int lineColor_unclick;
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


        // 删除图标大小
        // 起点(x，y)
        delete_x = typedArray.getInteger(R.styleable.SuperEditText_delete_x, 0);
        delete_y = typedArray.getInteger(R.styleable.SuperEditText_delete_y, 0);
        delete_width = typedArray.getInteger(R.styleable.SuperEditText_delete_width, 30);
        delete_height = typedArray.getInteger(R.styleable.SuperEditText_delete_height, 30);

        // 删除图标
        ic_deleteResID = typedArray.getResourceId(R.styleable.SuperEditText_ic_delete,R.drawable.delete);
        // 初始化操作
        // 1. 根据资源ID获取图标资源（转化成Drawable对象）
        // 2. 再设置图标大小(从右侧开始算起，后两个参数 = 宽高(px))
        ic_delete =  getResources().getDrawable(ic_deleteResID);
        ic_delete.setBounds(delete_x, delete_y, delete_width, delete_height);

        // 左侧图标大小
        // 起点(x，y)
        left_x = typedArray.getInteger(R.styleable.SuperEditText_left_x, 0);
        left_y = typedArray.getInteger(R.styleable.SuperEditText_left_y, 0);
        left_width = typedArray.getInteger(R.styleable.SuperEditText_left_width, 30);
        left_height = typedArray.getInteger(R.styleable.SuperEditText_left_height, 30);
        
        ic_left_clickResID = typedArray.getResourceId(R.styleable.SuperEditText_ic_left_click, R.drawable.ic_left_click);
        // 初始化操作
        // 1. 根据资源ID获取图标资源（转化成Drawable对象）
        // 2. 再设置图标大小
        ic_left_click =  getResources().getDrawable(ic_left_clickResID);
        ic_left_click.setBounds(left_x, left_y,left_width, left_height);

        ic_left_unclickResID = typedArray.getResourceId(R.styleable.SuperEditText_ic_left_unclick, R.drawable.ic_left_unclick);
        // 初始化操作
        // 1. 根据资源ID获取图标资源（转化成Drawable对象）
        // 2. 再设置图标大小
        ic_left_unclick =  getResources().getDrawable(ic_left_unclickResID);
        ic_left_unclick.setBounds(left_x, left_y,left_width, left_height);


        cursor = typedArray.getResourceId(R.styleable.SuperEditText_cursor, R.drawable.cursor);


        // 分割线相关
        mPaint = new Paint();
        mPaint.setStrokeWidth(1.5f); // 分割线粗细

        // 分割线颜色（使用十六进制代码，如#333、#8e8e8e）
        int lineColorClick_default = context.getResources().getColor(R.color.lineColor_click); // 默认 = 蓝色#1296db
        int lineColorunClick_default = context.getResources().getColor(R.color.lineColor_unclick); // 默认 = 灰色#9b9b9b
        lineColor_click = typedArray.getColor(R.styleable.SuperEditText_lineColor_click, lineColorClick_default);
        lineColor_unclick = typedArray.getColor(R.styleable.SuperEditText_lineColor_unclick, lineColorunClick_default);
        color = lineColor_unclick;

        mPaint.setColor(lineColor_unclick); // 分割线默认颜色 = 灰色
        setTextColor(color); // 字体默认颜色 = 灰色

        // 设置图片（初始状态仅有左侧图片）
        setCompoundDrawables( ic_left_unclick, null,
               null, null);

        // 消除自带下划线
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
        color = qian ? lineColor_click : lineColor_unclick;
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

