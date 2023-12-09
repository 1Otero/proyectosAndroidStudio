package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;
import com.example.myapplication.models.Serpent;

public class SerpentView extends View {

    private Paint paint;
    private SerpentView serpentView;
    private Serpent serpent;

    public SerpentView(Context context) {
        super(context);
        this.paint= new Paint();
        serpent= new Serpent();
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        canvas.drawColor(Color.BLACK);
        paint.setColor(Color.GREEN);
        for(RectF segmento: serpent.getSegmentos()){

        }


    }
}
