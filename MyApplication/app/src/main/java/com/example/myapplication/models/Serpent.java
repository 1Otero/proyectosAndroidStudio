package com.example.myapplication.models;

import android.graphics.RectF;

import java.util.ArrayList;
import java.util.List;

public class Serpent {
    private List<RectF> segmentos;
    private float segmentoWidth;
    private float segmentoHeight;
    public Serpent(int numSegment, float initialX, float initialY, float segmentoWidth, float segmentoHeight){
          this.segmentos= new ArrayList<>();
          this.segmentoWidth= segmentoWidth;
          this.segmentoHeight= segmentoHeight;

          for(int i= 0; i < numSegment; i++){
            RectF segmento= new RectF(initialX, initialY, initialX + segmentoWidth, initialY + segmentoHeight);
            segmentos.add(segmento);
            initialX += segmentoWidth;
          }
    }
    public List<RectF> getSegmentos(){
        return segmentos;
    }
}
