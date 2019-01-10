package com.moez.rushdrive;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class RushDrive extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background,myCar,carLeft,carRight,carCenter,gameOver;
	int carX,carY;
	int carLeftX,carLeftY,carRightX,carRightY,carCenterX,carCenterY;
	int velocityR,velocityL,velocityC,velocityM;
	int distance;
	int nbr=4,score;
	int gameState=0;
	Random ran;
	int x=0,y=0;
	ShapeRenderer shapeRanderer;
	BitmapFont font;
	Rectangle carRect,carLRect,carRRect,carCRect;
	Music music;
	@Override
	public void create () {
		batch = new SpriteBatch();
		ran = new Random();
		shapeRanderer = new ShapeRenderer();
		carRect = new Rectangle();
		carLRect = new Rectangle();
		carRRect = new Rectangle();
		carCRect = new Rectangle();
		background = new Texture("roads.png");
		gameOver= new Texture("gameover.png");

		myCar = new Texture("car.png");
		carLeft = new Texture("carBlue.png");
		carRight = new Texture("car2.png");
		carCenter = new Texture("carBlue.png");
		score=0;
		font = new BitmapFont();
		font.setColor(Color.WHITE);
		font.getData().setScale(5);


		gameState=0;

		carX=-75+Gdx.graphics.getWidth()/2;
		carY=310;

		carLeftX=-300+Gdx.graphics.getWidth()/2;
		carLeftY = Gdx.graphics.getHeight()+ran.nextInt(300);

		carRightX= 150+Gdx.graphics.getWidth()/2;
		carRightY = Gdx.graphics.getHeight()+ran.nextInt(300);

		carCenterX= -75+Gdx.graphics.getWidth()/2;
		carCenterY = Gdx.graphics.getHeight()+ran.nextInt(300);

		velocityR = ran.nextInt(10);
		velocityL = ran.nextInt(10);
		velocityC = ran.nextInt(10);
		velocityM = 200;
		distance = Gdx.graphics.getWidth()/2;

		int s = ran.nextInt(5);
		if(s==1)
		{
			music = Gdx.audio.newMusic(Gdx.files.internal("music2.mp3"));
		}
		else if(s==2)
		{
			music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		}
		else if(s==3)
		{
			music = Gdx.audio.newMusic(Gdx.files.internal("music1.mp3"));
		}
		else if(s==0)
		{
			music = Gdx.audio.newMusic(Gdx.files.internal("music3.mp3"));
		}
		else if(s==4)
		{
			music = Gdx.audio.newMusic(Gdx.files.internal("klay.mp3"));
		}


		music.play();

		

	}

	@Override
	public void render () {

		if(Gdx.input.justTouched()){
			x = Gdx.input.getX();
			y = Gdx.input.getY();
			if(gameState==0)
			{
				score =0;
				carX=-75+Gdx.graphics.getWidth()/2;
				carY=310;

				carLeftX=-300+Gdx.graphics.getWidth()/2;
				carLeftY = Gdx.graphics.getHeight()+ran.nextInt(300);

				carRightX= 150+Gdx.graphics.getWidth()/2;
				carRightY = Gdx.graphics.getHeight()+ran.nextInt(300);

				carCenterX= -75+Gdx.graphics.getWidth()/2;
				carCenterY = Gdx.graphics.getHeight()+ran.nextInt(300);

				velocityR = ran.nextInt(10);
				velocityL = ran.nextInt(10);
				velocityC = ran.nextInt(10);
				velocityM = 200;

			}
			gameState=1;


			//if(x>(Gdx.graphics.getWidth()+200)/2 && carX+280<Gdx.graphics.getWidth())
			//{
				//carX+=280;
				//x = Gdx.input.getX();
				//y = Gdx.input.getY();
				//carX =x;
		//	}
			//if (x<(Gdx.graphics.getWidth()-200)/2 && carX-280>0)
			//{
				//carX-=280;

				//carX =x;
		//	}

		//	if(y>(Gdx.graphics.getHeight()-200)/2 && carY-velocityM>0)
			//{

					//carY-=velocityM;
				//	carY=y;


		//	}
			//if(y<(Gdx.graphics.getHeight()+200)/2 && carY+velocityM<Gdx.graphics.getHeight()-300){

					//carY+=velocityM;

		//	}




			/*if(y>Gdx.graphics.getHeight()/2)
			{
				carY+=310;
			}
			else if (y<Gdx.graphics.getHeight()/2)
			{
				carY-=310;
			}*/

		}

		if(carLeftY<-310 && gameState==1)
		{
			carLeftY=Gdx.graphics.getHeight()+ran.nextInt(500);
			do {
				velocityL=ran.nextInt(10);
			}while(velocityL==0);

		}

		if(carCenterY<-310 && gameState==1)
		{
			carCenterY=Gdx.graphics.getHeight()+ran.nextInt(500);
			do {
				velocityL=ran.nextInt(10);
			}while(velocityC==0);

		}

		if(carRightY<-310 && gameState==1)
		{
			carRightY=Gdx.graphics.getHeight()+ran.nextInt(500);
			do {
				velocityR=ran.nextInt(10);
			}while(velocityR==0);

		}

		if(gameState==1)
		{
			carLeftY-=velocityL;
			carCenterY-=velocityC;
			carRightY-=velocityR;
		}


		batch.begin();
		batch.draw(background,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		batch.draw(myCar,Gdx.input.getX()-75,Gdx.graphics.getHeight()-Gdx.input.getY(),150,300);
		batch.draw(carLeft,carLeftX,carLeftY,150,300);
		batch.draw(carRight,carRightX,carRightY,150,300);
		batch.draw(carCenter,carCenterX,carCenterY,150,300);

		font.draw(batch,String.valueOf(score),100,100);



		//shapeRanderer.begin(ShapeRenderer.ShapeType.Filled);
		//shapeRanderer.setColor(Color.RED);

		carRect.set(Gdx.input.getX()-75,Gdx.graphics.getHeight()-Gdx.input.getY(),150,300);
		//shapeRanderer.rect(carRect.x,carRect.y,150,300);

		carRRect.set(carRightX,carRightY,150,300);
		//shapeRanderer.rect(carRRect.x,carRRect.y,150,300);

		carLRect.set(carLeftX,carLeftY,150,300);
		//shapeRanderer.rect(carLRect.x,carLRect.y,150,300);

		carCRect.set(carCenterX,carCenterY,150,300);
		//shapeRanderer.rect(carCRect.x,carCRect.y,150,300);

		//shapeRanderer.end();

		if(Intersector.overlaps(carRect,carRRect) || Intersector.overlaps(carRect,carLRect) || Intersector.overlaps(carRect,carCRect))
		{
			//Gdx.app.log("collision ===== ","yesss !");
			gameState=0;
			batch.draw(gameOver,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());


		}
		else
		{
			if(carRightY<-310 || carCenterY<-310 || carLeftY<-310)
			{
				score ++;
				Gdx.app.log("score ===== ",score+"");

			}
		}


		batch.end();
	}
	

}
