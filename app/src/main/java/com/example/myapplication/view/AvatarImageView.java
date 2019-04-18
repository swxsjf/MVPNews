package com.example.myapplication.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * Created by Android Studio.
 * User: 身为行
 * Date: 2019/4/16
 * Time: 16:55
 * Describe: ${as}
 */
public class AvatarImageView extends AppCompatImageView {
   private Paint mPaint;
    private Bitmap bitmap;
    private Matrix matrix;
    private BitmapShader shader;

    public AvatarImageView(Context context) {
        this(context,null);
    }

    public AvatarImageView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public AvatarImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        resetAvatar();

    }

    public void resetAvatar() {
        BitmapDrawable drawable = (BitmapDrawable) this.getDrawable();
        bitmap = drawable.getBitmap();
        shader = new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        mPaint = new Paint();
         mPaint.setShader(shader);
        matrix = new Matrix();
         this.invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // super.onDraw(canvas);

        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();
        int measuredWidth = this.getMeasuredWidth();
        int measuredHeight = this.getMeasuredHeight();

        float scaleX = measuredWidth * 1.0f / bitmapWidth;
        float scaleY = measuredHeight * 1.0f / bitmapHeight;
        float max = Math.min(scaleX, scaleY);

        matrix.setScale(max,max);
        shader.setLocalMatrix(matrix);
//        Bitmap bitmap = Bitmap.createBitmap(this.bitmap, 0, 0, bitmapWidth, bitmapHeight, matrix, true);
//        shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
         mPaint.setShader(shader);
        int radius = Math.min(measuredWidth, measuredHeight)/2;
        canvas.drawCircle(measuredWidth/2,measuredHeight/2,radius,mPaint);

    }
}
