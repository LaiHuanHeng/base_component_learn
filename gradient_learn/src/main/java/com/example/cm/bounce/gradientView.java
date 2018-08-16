package com.example.cm.bounce;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class gradientView extends View{
    public gradientView(Context context) {
        super(context);
    }

    public gradientView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public gradientView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();

        int color1 = getResources().getColor(R.color.orange);
        int color2 = getResources().getColor(R.color.splashCenter);
        int color3 = getResources().getColor(R.color.colorPrimaryDark);
        int color4 = getResources().getColor(R.color.green_light);
        int color5 = getResources().getColor(R.color.green);

        Paint paint = new Paint();
        LinearGradient gradient = new LinearGradient(0,0,0,height,new int[]{color1,color2,color3,color4,color5},null, Shader.TileMode.MIRROR);
        paint.setShader(gradient);
        canvas.drawRect(0,0,width,height,paint);
    }
}
