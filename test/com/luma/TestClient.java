
package com.luma;

import com.chroma.*;
import processing.core.*;

public class TestClient extends PApplet {

	public static void main(String[] args) {
		String[] a = { "Main" };
		PApplet.runSketch(a, new TestClient());
	}
	
	Luma testLuma;
	Chroma[] lumaClusters;
	Chroma[] lumaDomain;

	int startTime;
	int endTime;
	int totalTime;

	int lumaNumber = 5;
	int lumaQuality = 50;

	int lumaMinL = 30;
	int lumaMaxL = 75;

	int lumaMinC = 30;
	int lumaMaxC = 80;

	int lumaMinH = 40;
	int lumaMaxH = 200;
	
	@Override
	public void settings() {
		size(600, 600, FX2D);
		pixelDensity(2);
	}
	
	
	@Override
	public void setup() {

	    rectMode(CENTER);
	    smooth();
	    noStroke();
	    frameRate(30);


	    startTime = millis();
//	    testLuma = new Luma(lumaMinL, lumaMaxL, lumaMinC, lumaMaxC, lumaMinH, lumaMaxH);

//	    testLuma = new Luma(MunsellHue.GY, lumaMinL, lumaMaxL, lumaMinC, lumaMaxC);
	    testLuma = new Luma(MunsellHue.GY, lumaMinL, lumaMaxL, lumaMinC, lumaMaxC);

	    lumaClusters = testLuma.getClusters();
	    lumaDomain = testLuma.getDomain();

	    endTime = millis();


	    println("lumaClusters Length: " + lumaClusters.length);
	    println("lumaDomain Length: " + lumaDomain.length);
	    println("Processing Time(ms): " + (endTime-startTime));
	    println();

	}

	@Override
	public void draw() {
	    background(255);
	    plotLuma();
	    plotLumaCentroids();
	}

	void plotLuma() {
	    for (int i=0 ; i< lumaDomain.length; i++) {
	        // fill(lumaDomain[i].getColor());
	        stroke(lumaDomain[i].get());
	        strokeWeight(5);
	        point(map((float)lumaDomain[i].getLCH(Channel.H), 0, 360, 0, width), map((float)lumaDomain[i].getLCH(Channel.C), 0, 132, 0, height));
	    }
	}

	void plotLumaCentroids() {
	    for(int j = 0; j < lumaClusters.length; j++) {
	        fill(lumaClusters[j].get());
	        stroke(0);
	        strokeWeight(2);
	        rect(map((float)lumaClusters[j].getLCH(Channel.H), 0, 360, 0, width), map((float)lumaClusters[j].getLCH(Channel.C), 0, 132, 0, height), 20, 20);
	    }
	}
}


