

/**


        DEPRECATED (lol)


package com.example.leo.projetandroid.Display;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.leo.projetandroid.R;

public class SpriteDisplay extends SurfaceView implements Runnable {

    //last know position of the sprite
    float lastPosX = 500;
    float lastPosY = 500;

    // thread for the sprite
    Thread gameThread = null;

    //Used for paint and canvas
    SurfaceHolder ourHolder;

    // A boolean which we will set and unset
    // when the game is running- or not.
    volatile boolean playing;

    // A Canvas and a Paint object
    Canvas canvas;
    Paint paint;

    // This variable tracks the game frame rate
    long fps;

    // This is used to help calculate the fps
    private long timeThisFrame;

    // Declare an object of type Bitmap
    Bitmap spriteBitmap;

    //whether the sprite is moving or not
    boolean isMoving = false;

    //the number of pixels the sprite walk each second
    float walkSpeedPerSecond = 300;

    // position of the sprite on the screen
    float spritePosX = 500;
    float spritePosY = 600;

    // Width and Height of the sprite
    private int spriteWidth = 150;
    private int spriteHeight = 300;

    // How many frames are there on the sprite sheet?
    private int frameCount = 1;

    // start at the first frame
    private int currentFrame = 0;

    // What time was it when we last changed frames
    private long lastFrameChangeTime = 0;

    // How long should each frame last
    private int frameLengthInMilliseconds = 100;

    // A rectangle to define an area of the
    // sprite sheet that represents 1 frame
    private Rect frameToDraw = new Rect(
            0,
            0,
            spriteWidth,
            spriteHeight);

    // A rect that defines an area of the screen
    // on which to draw
    RectF whereToDraw = new RectF(
            spritePosX,
            spritePosY,
            spritePosX + spriteWidth,
            spritePosY + spriteHeight);



    public SpriteDisplay(Context context) {
        // The next line of code asks the
        // SurfaceView class to set up our object.
        // How kind.
        super(context);

        // Initialize ourHolder and paint objects
        ourHolder = getHolder();
        paint = new Paint();

        // Load Bob from his .png file
        spriteBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.character);

        // Scale the bitmap to the correct size
        // We need to do this because Android automatically
        // scales bitmaps based on screen density
        spriteBitmap = Bitmap.createScaledBitmap(spriteBitmap,
                spriteWidth * frameCount,
                spriteHeight,
                true);

        // Set our boolean to true - game on!
        playing = true;

    }

    @Override
    public void run() {
        while (playing) {

            // Capture the current time in milliseconds in startFrameTime
            long startFrameTime = System.currentTimeMillis();

            // Update the frame
            update();

            // Draw the frame
            onDraw();

            // Calculate the fps this frame
            // We can then use the result to
            // time animations and more.
            timeThisFrame = System.currentTimeMillis() - startFrameTime;
            if (timeThisFrame >= 1) {
                fps = 1000 / timeThisFrame;
            }
        }
    }

    public void update() {

    }

    public void getCurrentFrame() {

        long time = System.currentTimeMillis();
        if (isMoving) {// Only animate if bob is moving
            if (time > lastFrameChangeTime + frameLengthInMilliseconds) {
                lastFrameChangeTime = time;
                currentFrame++;
                if (currentFrame >= frameCount) {

                    currentFrame = 0;
                }
            }
        }
        //update the left and right values of the source of
        //the next frame on the spritesheet
        frameToDraw.left = currentFrame * spriteWidth;
        frameToDraw.right = frameToDraw.left + spriteWidth;
    }


    public void onDraw() {

        // Make sure our drawing surface is valid or we crash
        if (ourHolder.getSurface().isValid()) {
            // Lock the canvas ready to draw
            canvas = ourHolder.lockCanvas();
            //canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

            //canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);


            // Draw the background color
            //canvas.drawColor(Color.TRANSPARENT);
            // Choose the brush color for drawing
            paint.setColor(Color.argb(255, 200, 129, 0));

            // Make the text a bit bigger
            paint.setTextSize(100);
           // paint.clearShadowLayer();
           // paint.setAlpha(180);

            //spriteBitmap.setHasAlpha(true);

            // Draw bob at bobXPosition, 200 pixels
            //canvas.drawBitmap(spriteBitmap, spritePosX, 200, paint);

            whereToDraw.set((int) spritePosX,
                    (int) spritePosY,
                    (int) spritePosX + spriteWidth,
                    (int) spritePosY + spriteHeight);

            getCurrentFrame();

            //canvas.drawColor(Color.TRANSPARENT);
            //this.setZOrderOnTop(true);

            canvas.drawText("coucou", 150, 150, paint);
            //this.setBackgroundColor(Color.TRANSPARENT);
            //this.setZOrderOnTop(false); //necessary
            //getHolder().setFormat(PixelFormat.TRANSPARENT);
            //getHolder().addCallback(this);

            //canvas.drawColor(Color.argb(100, 26, 128, 182));


            canvas.drawBitmap(spriteBitmap, 500, 500, paint);

            canvas.drawBitmap(spriteBitmap, frameToDraw, whereToDraw, paint);
            //canvas.drawARGB(255, 225, 225, 255);

            // Draw everything to the screen

            ourHolder.unlockCanvasAndPost(canvas);
        }

    }

    // If SimpleGameEngine Activity is paused/stopped
    // shutdown our thread.
    public void pause() {
        playing = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            Log.e("Error:", "joining thread");
        }

    }

    // If SimpleGameEngine Activity is started theb
    // start our thread.
    public void resume() {
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        canvas.drawColor(Color.TRANSPARENT);
    }



}






/**


    private Context context;

    private int viewWidth;
    private int viewHeight;
    private SurfaceHolder surfaceHolder;

    private Bitmap character;

    RectF whereToDraw = new RectF(
            500,
            500,
            550,
            550);



    public SpriteDisplay (Context context){
        super(context, null);
    }

    public SpriteDisplay(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        surfaceHolder = getHolder();
        paint = new Paint();

        this.setBackgroundColor(Color.TRANSPARENT);
        this.setZOrderOnTop(true);
        //getHolder().setFormat(PixelFormat.TRANSPARENT);

        this.character = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.character);

        this.running = true;
        run();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        viewWidth = w;
        viewHeight = h;

        this.setBackgroundColor(Color.TRANSPARENT);
        this.setZOrderOnTop(true);
        //getHolder().setFormat(PixelFormat.TRANSPARENT);
        //bitmap = BitmapFactory.decodeFile("character.png");
    }

    @Override
    public void run() {
        Canvas canvas;

        while(running){

            //if the drawing surface is valid
            if (surfaceHolder.getSurface().isValid()) {

                //lock the canvas for reasons (?)
                canvas = surfaceHolder.lockCanvas();

                this.setBackgroundColor(Color.TRANSPARENT);
                paint.setColor(Color.argb(255,  0, 129, 0));


                this.setZOrderOnTop(true);

                canvas.drawBitmap(character,
                        null,
                        whereToDraw, paint);

                canvas.save();
                canvas.drawBitmap(character, 500, 500, paint  );
                surfaceHolder.unlockCanvasAndPost(canvas);

            }
        }

    }

    private void update(){

    }

    public void getCurrentFrame(){

    }

    private void drawSprites(){


    }

    // If SimpleGameEngine Activity is paused/stopped
    // shutdown our thread.
    public void pause() {

    }

    // If SimpleGameEngine Activity is started theb
    // start our thread.
    public void resume() {

    }

}

 **/