package com.shenzhen.jimeng.jm;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class JMWorkProgress extends View {
    private int mWidth;
    private int mHeight;
    private int useWidth, minWidth, centreWidth;
    private RectF mBigOval, mSecondOval, mThirdlyOval, mMinOval;
    private Paint mBigPaint, mSecondPaint, mThirdlyPaint, mMinPaint;
    private int mBigColor, mSecondColor, mThirdlyColor, mMinColor;
    private float mStart;
    private String mText;
    private int mTextStyle, mTextSize, mOvalStyle,mOvalStrokeWidth;
    private boolean textIsShow = true;
    private boolean animationIsShow=true;
    private static final float SWEEP_INC = 6;


    public JMWorkProgress(Context context) {
        super(context);
        initCustomAttrs(context, null);
        init();
    }

    public JMWorkProgress(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initCustomAttrs(context, attrs);
        init();
    }

    public JMWorkProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initCustomAttrs(context, attrs);
        init();
    }

    /**
     * 获取自定义属性
     */
    private void initCustomAttrs(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.JMWorkProgress);
        mBigColor = ta.getColor(R.styleable.JMWorkProgress_oval_big_color, Color.BLACK);
        mSecondColor = ta.getColor(R.styleable.JMWorkProgress_oval_second_color, Color.BLUE);
        mThirdlyColor = ta.getColor(R.styleable.JMWorkProgress_oval_thirdly_color, Color.YELLOW);
        mMinColor = ta.getColor(R.styleable.JMWorkProgress_oval_min_color, Color.RED);
        mText = ta.getString(R.styleable.JMWorkProgress_text);
        mTextStyle = ta.getInteger(R.styleable.JMWorkProgress_textStyle, 0);
        mTextSize = ta.getInteger(R.styleable.JMWorkProgress_textSize, 28);
        mOvalStyle = ta.getInteger(R.styleable.JMWorkProgress_oval_min_color, 0);
        mOvalStrokeWidth = ta.getInteger(R.styleable.JMWorkProgress_ovalStrokeWidth, 8);
        ta.recycle();
    }
    private void init(){
        initPaint();
    }
    private void initPaint() {
        //初始化
        mBigPaint = new Paint();
        mBigPaint.setAntiAlias(true);
        mBigPaint.setColor(mBigColor);
        mSecondPaint = new Paint();
        mSecondPaint.setAntiAlias(true);
        mSecondPaint.setColor(mSecondColor);
        mThirdlyPaint = new Paint();
        mThirdlyPaint.setAntiAlias(true);
        mThirdlyPaint.setColor(mThirdlyColor);
        mMinPaint = new Paint();
        mMinPaint.setAntiAlias(true);
        mMinPaint.setColor(mMinColor);
        switch (mOvalStyle){
            case 0:
                mBigPaint.setStyle(Paint.Style.STROKE);
                mSecondPaint.setStyle(Paint.Style.STROKE);
                mThirdlyPaint.setStyle(Paint.Style.STROKE);
                mMinPaint.setStyle(Paint.Style.STROKE);
                break;
            case 1:
                mBigPaint.setStyle(Paint.Style.FILL);
                mSecondPaint.setStyle(Paint.Style.FILL);
                mThirdlyPaint.setStyle(Paint.Style.FILL);
                mMinPaint.setStyle(Paint.Style.FILL);
                break;
            case 2:
                mBigPaint.setStyle(Paint.Style.FILL_AND_STROKE);
                mSecondPaint.setStyle(Paint.Style.FILL_AND_STROKE);
                mThirdlyPaint.setStyle(Paint.Style.FILL_AND_STROKE);
                mMinPaint.setStyle(Paint.Style.FILL_AND_STROKE);
                break;
            default:
                mBigPaint.setStyle(Paint.Style.STROKE);
                mSecondPaint.setStyle(Paint.Style.STROKE);
                mThirdlyPaint.setStyle(Paint.Style.STROKE);
                mMinPaint.setStyle(Paint.Style.STROKE);
                break;
        }

        mBigPaint.setStrokeWidth(mOvalStrokeWidth);
        mSecondPaint.setStrokeWidth(mOvalStrokeWidth);
        mThirdlyPaint.setStrokeWidth(mOvalStrokeWidth);
        mMinPaint.setStrokeWidth(mOvalStrokeWidth);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        useWidth = mWidth;
        if (mWidth > mHeight) {
            useWidth = mHeight;
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setCentreWidth();
        mBigOval = new RectF(centreWidth - 4 * minWidth, centreWidth - 4 * minWidth, centreWidth + 4 * minWidth, centreWidth + 4 * minWidth);
        mSecondOval = new RectF(centreWidth - 3 * minWidth, centreWidth - 3 * minWidth, centreWidth + 3 * minWidth, centreWidth + 3 * minWidth);
        mThirdlyOval = new RectF(centreWidth - 2 * minWidth, centreWidth - 2 * minWidth, centreWidth + 2 * minWidth, centreWidth + 2 * minWidth);
        mMinOval = new RectF(centreWidth - 1 * minWidth, centreWidth - 1 * minWidth, centreWidth + 1 * minWidth, centreWidth + 1 * minWidth);
        canvas.drawArc(mBigOval, mStart, 40, false, mBigPaint);
        canvas.drawArc(mSecondOval, 0 - mStart, 60, false, mSecondPaint);
        canvas.drawArc(mThirdlyOval, 90 - mStart, 80, false, mThirdlyPaint);
        canvas.drawArc(mMinOval, 270 + mStart, 100, false, mMinPaint);
        //改变值后停止绘制
        drawHintText(canvas, mText, textIsShow);
        animationLogic(animationIsShow);
    }
    private void animationLogic(boolean animationIsShow){
        if (animationIsShow){
            mStart -= SWEEP_INC;
            invalidate();
        }
    }
    //定义中心
    private void setCentreWidth() {
        //定义最小的标识
        minWidth = useWidth / 10;
        //定义中心标识
        centreWidth = minWidth * 5;
    }

    private void drawHintText(Canvas canvas, String string, boolean textIsShow) {
        if (textIsShow) {
            if (string!=null){
                int textLength = string.length();
                drawHintTextAboutLength(canvas, string, textLength);
            }
            textIsShow = false;
        }
    }

    private void drawHintTextAboutLength(Canvas canvas, String string, int textLength) {
        Paint textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(mTextSize);
        switch (mTextStyle) {
            case 0:
                textPaint.setStyle(Paint.Style.STROKE);
                break;
            case 1:
                textPaint.setStyle(Paint.Style.FILL);
                break;
            case 2:
                textPaint.setStyle(Paint.Style.FILL_AND_STROKE);
                break;
            default:
                textPaint.setStyle(Paint.Style.STROKE);
                break;
        }

        textPaint.setTextAlign(Paint.Align.CENTER);
        //获取文字度量信息
        Paint.FontMetrics fm = textPaint.getFontMetrics();
        float textHeight = fm.descent - fm.ascent;

        //绘制文字的矩形框范围
        RectF rectF1 = new RectF(minWidth, centreWidth - textHeight / 2, minWidth * 2, centreWidth + textHeight / 2);
        RectF rectF2 = new RectF(minWidth * 2, centreWidth - textHeight / 2, minWidth * 3, centreWidth + textHeight / 2);
        RectF rectF3 = new RectF(minWidth * 3, centreWidth - textHeight / 2, minWidth * 4, centreWidth + textHeight / 2);
        RectF rectF4 = new RectF(minWidth * 4, centreWidth - textHeight / 2, minWidth * 6, centreWidth + textHeight / 2);
        RectF rectF5 = new RectF(minWidth * 6, centreWidth - textHeight / 2, minWidth * 7, centreWidth + textHeight / 2);
        RectF rectF6 = new RectF(minWidth * 7, centreWidth - textHeight / 2, minWidth * 8, centreWidth + textHeight / 2);
        RectF rectF7 = new RectF(minWidth * 8, centreWidth - textHeight / 2, minWidth * 9, centreWidth + textHeight / 2);
        switch (textLength) {
            case 1:
                textPaint.setColor(mMinColor);
                canvas.drawText(string.substring(0, 1), rectF4.left + rectF4.width() / 2, rectF4.bottom - fm.descent, textPaint);
                break;
            case 2:
                textPaint.setColor(mThirdlyColor);
                canvas.drawText(string.substring(0, 1), rectF3.left + rectF3.width() / 2, rectF3.bottom - fm.descent, textPaint);
                textPaint.setColor(mThirdlyColor);
                canvas.drawText(string.substring(1, 2), rectF5.left + rectF5.width() / 2, rectF5.bottom - fm.descent, textPaint);
                ;
                break;
            case 3:
                textPaint.setColor(mSecondColor);
                canvas.drawText(string.substring(0, 1), rectF2.left + rectF2.width() / 2, rectF2.bottom - fm.descent, textPaint);
                textPaint.setColor(mMinColor);
                canvas.drawText(string.substring(1, 2), rectF4.left + rectF4.width() / 2, rectF4.bottom - fm.descent, textPaint);
                textPaint.setColor(mSecondColor);
                canvas.drawText(string.substring(2, 3), rectF6.left + rectF6.width() / 2, rectF6.bottom - fm.descent, textPaint);
                break;
            case 4:
                textPaint.setColor(mBigColor);
                canvas.drawText(string.substring(0, 1), rectF1.left + rectF1.width() / 2, rectF1.bottom - fm.descent, textPaint);
                textPaint.setColor(mThirdlyColor);
                canvas.drawText(string.substring(1, 2), rectF3.left + rectF3.width() / 2, rectF3.bottom - fm.descent, textPaint);
                textPaint.setColor(mThirdlyColor);
                canvas.drawText(string.substring(2, 3), rectF5.left + rectF5.width() / 2, rectF5.bottom - fm.descent, textPaint);
                textPaint.setColor(mBigColor);
                canvas.drawText(string.substring(3, 4), rectF7.left + rectF7.width() / 2, rectF7.bottom - fm.descent, textPaint);
                break;
            case 5:
                textPaint.setColor(mBigColor);
                canvas.drawText(string.substring(0, 1), rectF1.left + rectF1.width() / 2, rectF1.bottom - fm.descent, textPaint);
                textPaint.setColor(mThirdlyColor);
                canvas.drawText(string.substring(1, 2), rectF2.left + rectF2.width() / 2, rectF2.bottom - fm.descent, textPaint);
                textPaint.setColor(mMinColor);
                canvas.drawText(string.substring(2, 3), rectF4.left + rectF4.width() / 2, rectF4.bottom - fm.descent, textPaint);
                textPaint.setColor(mThirdlyColor);
                canvas.drawText(string.substring(3, 4), rectF6.left + rectF6.width() / 2, rectF6.bottom - fm.descent, textPaint);
                textPaint.setColor(mBigColor);
                canvas.drawText(string.substring(4, 5), rectF7.left + rectF7.width() / 2, rectF7.bottom - fm.descent, textPaint);
                break;
            case 6:
                textPaint.setColor(mBigColor);
                canvas.drawText(string.substring(0, 1), rectF1.left + rectF1.width() / 2, rectF1.bottom - fm.descent, textPaint);
                textPaint.setColor(mThirdlyColor);
                canvas.drawText(string.substring(1, 2), rectF2.left + rectF2.width() / 2, rectF2.bottom - fm.descent, textPaint);
                textPaint.setColor(mThirdlyColor);
                canvas.drawText(string.substring(2, 3), rectF3.left + rectF3.width() / 2, rectF3.bottom - fm.descent, textPaint);
                textPaint.setColor(mThirdlyColor);
                canvas.drawText(string.substring(3, 4), rectF5.left + rectF5.width() / 2, rectF5.bottom - fm.descent, textPaint);
                textPaint.setColor(mThirdlyColor);
                canvas.drawText(string.substring(4, 5), rectF6.left + rectF6.width() / 2, rectF6.bottom - fm.descent, textPaint);
                textPaint.setColor(mBigColor);
                canvas.drawText(string.substring(5, 6), rectF7.left + rectF7.width() / 2, rectF7.bottom - fm.descent, textPaint);
                break;
            case 7:
                textPaint.setColor(mBigColor);
                canvas.drawText(string.substring(0, 1), rectF1.left + rectF1.width() / 2, rectF1.bottom - fm.descent, textPaint);
                textPaint.setColor(mThirdlyColor);
                canvas.drawText(string.substring(1, 2), rectF2.left + rectF2.width() / 2, rectF2.bottom - fm.descent, textPaint);
                textPaint.setColor(mThirdlyColor);
                canvas.drawText(string.substring(2, 3), rectF3.left + rectF3.width() / 2, rectF3.bottom - fm.descent, textPaint);
                textPaint.setColor(mMinColor);
                canvas.drawText(string.substring(3, 4), rectF4.left + rectF4.width() / 2, rectF4.bottom - fm.descent, textPaint);
                textPaint.setColor(mThirdlyColor);
                canvas.drawText(string.substring(4, 5), rectF5.left + rectF5.width() / 2, rectF5.bottom - fm.descent, textPaint);
                textPaint.setColor(mThirdlyColor);
                canvas.drawText(string.substring(5, 6), rectF6.left + rectF6.width() / 2, rectF6.bottom - fm.descent, textPaint);
                textPaint.setColor(mBigColor);
                canvas.drawText(string.substring(6, 7), rectF7.left + rectF7.width() / 2, rectF7.bottom - fm.descent, textPaint);
                break;
            default:
                break;
        }

    }

    public int getmWidth() {
        return mWidth;
    }

    public void setmWidth(int mWidth) {
        this.mWidth = mWidth;
    }

    public int getmHeight() {
        return mHeight;
    }

    public void setmHeight(int mHeight) {
        this.mHeight = mHeight;
    }

    public int getUseWidth() {
        return useWidth;
    }

    public void setUseWidth(int useWidth) {
        this.useWidth = useWidth;
    }

    public int getMinWidth() {
        return minWidth;
    }

    public void setMinWidth(int minWidth) {
        this.minWidth = minWidth;
    }

    public int getCentreWidth() {
        return centreWidth;
    }

    public void setCentreWidth(int centreWidth) {
        this.centreWidth = centreWidth;
    }

    public RectF getmBigOval() {
        return mBigOval;
    }

    public void setmBigOval(RectF mBigOval) {
        this.mBigOval = mBigOval;
    }

    public RectF getmSecondOval() {
        return mSecondOval;
    }

    public void setmSecondOval(RectF mSecondOval) {
        this.mSecondOval = mSecondOval;
    }

    public RectF getmThirdlyOval() {
        return mThirdlyOval;
    }

    public void setmThirdlyOval(RectF mThirdlyOval) {
        this.mThirdlyOval = mThirdlyOval;
    }

    public RectF getmMinOval() {
        return mMinOval;
    }

    public void setmMinOval(RectF mMinOval) {
        this.mMinOval = mMinOval;
    }

    public Paint getmBigPaint() {
        return mBigPaint;
    }

    public void setmBigPaint(Paint mBigPaint) {
        this.mBigPaint = mBigPaint;
    }

    public Paint getmSecondPaint() {
        return mSecondPaint;
    }

    public void setmSecondPaint(Paint mSecondPaint) {
        this.mSecondPaint = mSecondPaint;
    }

    public Paint getmThirdlyPaint() {
        return mThirdlyPaint;
    }

    public void setmThirdlyPaint(Paint mThirdlyPaint) {
        this.mThirdlyPaint = mThirdlyPaint;
    }

    public Paint getmMinPaint() {
        return mMinPaint;
    }

    public void setmMinPaint(Paint mMinPaint) {
        this.mMinPaint = mMinPaint;
    }

    public int getmBigColor() {
        return mBigColor;
    }

    public void setmBigColor(int mBigColor) {
        this.mBigColor = mBigColor;
    }

    public int getmSecondColor() {
        return mSecondColor;
    }

    public void setmSecondColor(int mSecondColor) {
        this.mSecondColor = mSecondColor;
    }

    public int getmThirdlyColor() {
        return mThirdlyColor;
    }

    public void setmThirdlyColor(int mThirdlyColor) {
        this.mThirdlyColor = mThirdlyColor;
    }

    public int getmMinColor() {
        return mMinColor;
    }

    public void setmMinColor(int mMinColor) {
        this.mMinColor = mMinColor;
    }

    public float getmStart() {
        return mStart;
    }

    public void setmStart(float mStart) {
        this.mStart = mStart;
    }

    public String getmText() {
        return mText;
    }

    public void setmText(String mText) {
        this.mText = mText;
    }

    public int getmTextStyle() {
        return mTextStyle;
    }

    public void setmTextStyle(int mTextStyle) {
        this.mTextStyle = mTextStyle;
    }

    public int getmTextSize() {
        return mTextSize;
    }

    public void setmTextSize(int mTextSize) {
        this.mTextSize = mTextSize;
    }

    public int getmOvalStyle() {
        return mOvalStyle;
    }

    public void setmOvalStyle(int mOvalStyle) {
        this.mOvalStyle = mOvalStyle;
    }

    public int getmOvalStrokeWidth() {
        return mOvalStrokeWidth;
    }

    public void setmOvalStrokeWidth(int mOvalStrokeWidth) {
        this.mOvalStrokeWidth = mOvalStrokeWidth;
    }

    public boolean isTextIsShow() {
        return textIsShow;
    }

    public void setTextIsShow(boolean textIsShow) {
        this.textIsShow = textIsShow;
    }

    public boolean getAnimationIsShow() {
        return animationIsShow;
    }

    public void setAnimationIsShow(boolean animationIsShow) {
        this.animationIsShow = animationIsShow;
        if (animationIsShow){
            invalidate();
        }

    }

}
