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
    private int delete_x,delete_y,delete_width,delete_height; // 删除图标起点(x,y)、删除图标宽、高（px）

    private int  ic_left_clickResID,ic_left_unclickResID;    // 左侧图标 资源ID（点击 & 无点击）
    private Drawable  ic_left_click,ic_left_unclick; // 左侧图标（点击 & 未点击）
    private int left_x,left_y,left_width,left_height; // 左侧图标起点（x,y）、左侧图标宽、高（px）

    private int cursor; // 光标

    // 分割线变量
    private int lineColor_click,lineColor_unclick;// 点击时 & 未点击颜色
    private int color;
    private int linePosition;


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

    /**
     * 步骤1：初始化属性
     */

    private void init(Context context, AttributeSet attrs) {

        // 获取控件资源
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SuperEditText);

        /**
         * 初始化左侧图标（点击 & 未点击）
         */

        // a. 点击状态的左侧图标
         // 1. 获取资源ID
         ic_left_clickResID = typedArray.getResourceId(R.styleable.SuperEditText_ic_left_click, R.drawable.ic_left_click);
         // 2. 根据资源ID获取图标资源（转化成Drawable对象）
         ic_left_click =  getResources().getDrawable(ic_left_clickResID);
         // 3. 设置图标大小
         // 起点(x，y)、宽= left_width、高 = left_height
         left_x = typedArray.getInteger(R.styleable.SuperEditText_left_x, 0);
         left_y = typedArray.getInteger(R.styleable.SuperEditText_left_y, 0);
         left_width = typedArray.getInteger(R.styleable.SuperEditText_left_width, 60);
         left_height = typedArray.getInteger(R.styleable.SuperEditText_left_height, 60);

         ic_left_click.setBounds(left_x, left_y,left_width, left_height);
         // Drawable.setBounds(x,y,width,height) = 设置Drawable的初始位置、宽和高等信息
         // x = 组件在容器X轴上的起点、y = 组件在容器Y轴上的起点、width=组件的长度、height = 组件的高度

        // b. 未点击状态的左侧图标
         // 1. 获取资源ID
         ic_left_unclickResID = typedArray.getResourceId(R.styleable.SuperEditText_ic_left_unclick, R.drawable.ic_left_unclick);
         // 2. 根据资源ID获取图标资源（转化成Drawable对象）
         // 3. 设置图标大小（此处默认左侧图标点解 & 未点击状态的大小相同）
         ic_left_unclick =  getResources().getDrawable(ic_left_unclickResID);
         ic_left_unclick.setBounds(left_x, left_y,left_width, left_height);


        /**
         * 初始化删除图标
         */

         // 1. 获取资源ID
         ic_deleteResID = typedArray.getResourceId(R.styleable.SuperEditText_ic_delete,R.drawable.delete);
         // 2. 根据资源ID获取图标资源（转化成Drawable对象）
         ic_delete =  getResources().getDrawable(ic_deleteResID);
         // 3. 设置图标大小
         // 起点(x，y)、宽= left_width、高 = left_height
         delete_x = typedArray.getInteger(R.styleable.SuperEditText_delete_x, 0);
         delete_y = typedArray.getInteger(R.styleable.SuperEditText_delete_y, 0);
         delete_width = typedArray.getInteger(R.styleable.SuperEditText_delete_width, 60);
         delete_height = typedArray.getInteger(R.styleable.SuperEditText_delete_height, 60);
         ic_delete.setBounds(delete_x, delete_y, delete_width, delete_height);

        /**
         * 设置EditText左侧 & 右侧的图片（初始状态仅有左侧图片））
         */
        setCompoundDrawables( ic_left_unclick, null,
                null, null);

        // setCompoundDrawables(Drawable left, Drawable top, Drawable right, Drawable bottom)介绍
        // 作用：在EditText上、下、左、右设置图标（相当于android:drawableLeft=""  android:drawableRight=""）
        // 备注：传入的Drawable对象必须已经setBounds(x,y,width,height)，即必须设置过初始位置、宽和高等信息
        // x:组件在容器X轴上的起点 y:组件在容器Y轴上的起点 width:组件的长度 height:组件的高度
        // 若不想在某个地方显示，则设置为null

        // 另外一个相似的方法：setCompoundDrawablesWithIntrinsicBounds(Drawable left, Drawable top, Drawable right, Drawable bottom)
        // 作用：在EditText上、下、左、右设置图标
        // 与setCompoundDrawables的区别：setCompoundDrawablesWithIntrinsicBounds（）传入的Drawable的宽高=固有宽高（自动通过getIntrinsicWidth（）& getIntrinsicHeight（）获取）
        // 不需要设置setBounds(x,y,width,height)

        /**
         * 初始化光标（颜色 & 粗细）
         */
         // 原理：通过 反射机制 动态设置光标
         // 1. 获取资源ID
         cursor = typedArray.getResourceId(R.styleable.SuperEditText_cursor, R.drawable.cursor);
         try {

            // 2. 通过反射 获取光标属性
            Field f = TextView.class.getDeclaredField("mCursorDrawableRes");
            f.setAccessible(true);
            // 3. 传入资源ID
            f.set(this, cursor);

         } catch (Exception e) {
            e.printStackTrace();
         }

        /**
         * 初始化分割线（颜色、粗细、位置）
         */
         // 1. 设置画笔
         mPaint = new Paint();
         mPaint.setStrokeWidth(2.0f); // 分割线粗细

         // 2. 设置分割线颜色（使用十六进制代码，如#333、#8e8e8e）
         int lineColorClick_default = context.getResources().getColor(R.color.lineColor_click); // 默认 = 蓝色#1296db
         int lineColorunClick_default = context.getResources().getColor(R.color.lineColor_unclick); // 默认 = 灰色#9b9b9b
         lineColor_click = typedArray.getColor(R.styleable.SuperEditText_lineColor_click, lineColorClick_default);
         lineColor_unclick = typedArray.getColor(R.styleable.SuperEditText_lineColor_unclick, lineColorunClick_default);
         color = lineColor_unclick;

         mPaint.setColor(lineColor_unclick); // 分割线默认颜色 = 灰色
         setTextColor(color); // 字体默认颜色 = 灰色

         // 3. 分割线位置
         linePosition = typedArray.getInteger(R.styleable.SuperEditText_linePosition, 1);
         // 消除自带下划线
         setBackground(null);


    }

    /**
     * 复写EditText本身的方法：onTextChanged（）
     * 调用时刻：当输入框内容变化时
     */
    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        setDeleteIconVisible(hasFocus() && text.length() > 0,hasFocus());
        // hasFocus()返回是否获得EditTEXT的焦点，即是否选中
        // setDeleteIconVisible（） = 根据传入的是否选中 & 是否有输入来判断是否显示删除图标->>关注1
    }

    /**
     * 复写EditText本身的方法：onFocusChanged（）
     * 调用时刻：焦点发生变化时
     */
    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        setDeleteIconVisible(focused && length() > 0,focused);
        // focused = 是否获得焦点
        // 同样根据setDeleteIconVisible（）判断是否要显示删除图标->>关注1
    }


    /**
     * 作用：对删除图标区域设置为"点击 即 清空搜索框内容"
     * 原理：当手指抬起的位置在删除图标的区域，即视为点击了删除图标 = 清空搜索框内容
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        // 原理：当手指抬起的位置在删除图标的区域，即视为点击了删除图标 = 清空搜索框内容
        switch (event.getAction()) {
            // 判断动作 = 手指抬起时
            case MotionEvent.ACTION_UP:
                Drawable drawable =  ic_delete;

                if (drawable != null && event.getX() <= (getWidth() - getPaddingRight())
                        && event.getX() >= (getWidth() - getPaddingRight() - drawable.getBounds().width())) {

                    // 判断条件说明
                    // event.getX() ：抬起时的位置坐标
                    // getWidth()：控件的宽度
                    // getPaddingRight():删除图标图标右边缘至EditText控件右边缘的距离
                    // 即：getWidth() - getPaddingRight() = 删除图标的右边缘坐标 = X1
                        // getWidth() - getPaddingRight() - drawable.getBounds().width() = 删除图标左边缘的坐标 = X2
                    // 所以X1与X2之间的区域 = 删除图标的区域
                    // 当手指抬起的位置在删除图标的区域（X2=<event.getX() <=X1），即视为点击了删除图标 = 清空搜索框内容
                    setText("");

                }
                break;
        }
        return super.onTouchEvent(event);
    }

    /**
     * 关注1
     * 作用：判断是否显示删除图标 & 设置分割线颜色
     */
    private void setDeleteIconVisible(boolean deleteVisible,boolean leftVisible) {
        setCompoundDrawables(leftVisible ?  ic_left_click :  ic_left_unclick, null,
                deleteVisible ?  ic_delete: null, null);
        color = leftVisible ? lineColor_click : lineColor_unclick;
        setTextColor(color);
        invalidate();
    }

    /**
     * 作用：绘制分割线
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(color);
        setTextColor(color);
        // 绘制分割线
        // 需要考虑：当输入长度超过输入框时，所画的线需要跟随着延伸
        // 解决方案：线的长度 = 控件长度 + 延伸后的长度
        int x=this.getScrollX(); // 获取延伸后的长度
        int w=this.getMeasuredWidth(); // 获取控件长度

        // 传入参数时，线的长度 = 控件长度 + 延伸后的长度
                canvas.drawLine(0, this.getMeasuredHeight()- linePosition, w+x,
                        this.getMeasuredHeight() - linePosition, mPaint);

    }

}

