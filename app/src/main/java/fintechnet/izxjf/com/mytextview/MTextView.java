package fintechnet.izxjf.com.mytextview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import static android.R.attr.manageSpaceActivity;
import static android.R.attr.name;

/**
 * Created by as on 2017/5/17.
 */

public class MTextView extends View {
    //设置字体内容
    String darrenText ="";
    //设置字体颜色
    int darrenTextColor = Color.BLACK;
    //设置字体大小
    int darrenTextSize =15;

    private Paint mPaint;
    public MTextView(Context context) {
        this(context,null);
    }

    public MTextView(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public MTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);



        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MTextView);
        darrenText =typedArray.getString(R.styleable.MTextView_darrenText);
        darrenTextColor = typedArray.getColor(R.styleable.MTextView_darrenTextColor, Color.BLACK);
        darrenTextSize =typedArray.getDimensionPixelSize(R.styleable.MTextView_darrenTextSize, sp2px(darrenTextSize));
        typedArray.recycle();

        //定义画笔
        mPaint = new Paint();
        //房锯齿
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        //设置字体大小
        mPaint.setTextSize(sp2px(darrenTextSize));
        //设置字体颜色
        mPaint.setColor(darrenTextColor);

    }

    //进行绘制
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint.FontMetricsInt fontMetricsInt = mPaint.getFontMetricsInt();
        //获取中心线到基准线的高度
        int y =(fontMetricsInt.bottom -fontMetricsInt.top)/2-fontMetricsInt.bottom;
        //计算基准线高度
        int baseLine =getHeight()/2+y;

        //设置支持padding
        int x =getPaddingLeft();
        canvas.drawText(darrenText,x,baseLine,mPaint);
    }

    //进行测量
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width =MeasureSpec.getSize(widthMeasureSpec);
        int height =MeasureSpec.getSize(heightMeasureSpec);

        int widthMode =MeasureSpec.getMode(widthMeasureSpec);
        int heightMode =MeasureSpec.getMode(heightMeasureSpec);

        if(widthMode ==MeasureSpec.AT_MOST){
            Rect rect = new Rect();
            mPaint.getTextBounds(darrenText,0,darrenText.length(),rect);
            //获取宽度
            width =rect.width()+getPaddingRight()+getPaddingLeft();
        }
        if(heightMode ==MeasureSpec.AT_MOST){
            Rect rect = new Rect();
            mPaint.getTextBounds(darrenText,0,darrenText.length(),rect);
            //获取高度
            height =rect.height()+getPaddingBottom()+getPaddingTop();

        }
        setMeasuredDimension(width,height);
    }

    private int sp2px(int sp){
        return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,sp,getResources().getDisplayMetrics());
    }
}
