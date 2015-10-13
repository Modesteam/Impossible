package casadocodigo.impossible;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class Impossible extends SurfaceView implements Runnable {
    boolean running = false;
    Thread renderThread = null;
    SurfaceHolder holder;
    Paint paint;
    private int playerY = 300, playerX = 300, playerRadius = 50;
    private int enemyX, enemyY, enemyRadius = 50;
    private double distance;
    boolean gameover;

    public Impossible(Context context) {
        super(context);
        paint = new Paint();
        holder = getHolder();
    }

    private void drawPlayer(Canvas canvas) {
        paint.setColor(Color.GREEN);
        canvas.drawCircle(playerX, playerY, 50, paint);
    }

    private void drawEnemy(Canvas canvas) {
        paint.setColor(Color.GRAY);
        enemyRadius++;
        canvas.drawCircle(enemyX, enemyY, enemyRadius, paint);
    }

    public void moveDown(int pixels) {
        playerY += pixels;
    }
    private void checkCollision(Canvas canvas) {
        distance = Math.pow(playerY - enemyY, 2) + Math.pow(playerX - enemyX, 2);
        distance = Math.sqrt(distance);
        if (distance <= playerRadius + enemyRadius){
            gameover = true;
        }
    }
    @Override
    public void run() {
        while(running) {
            // Confirm if the screen is ready
            if(!holder.getSurface().isValid()) {
                continue;
            }

            // Block the canvas
            Canvas canvas = holder.lockCanvas();
            canvas.drawColor(Color.BLACK);

            // Draw the Player
            drawPlayer(canvas);
            drawEnemy(canvas);
            checkCollision(canvas);
            // Update and free the canvas
            holder.unlockCanvasAndPost(canvas);
            if(gameover){
                stopGame(canvas);
                break;
            }
        }
    }

    private void stopGame(Canvas canvas){
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.LTGRAY);
        paint.setTextSize(100);
        canvas.drawText("GAMA OVER!", 50, 150, paint);
    }

    public void resume() {
        running = true;
        renderThread = new Thread(this);
        renderThread.start();
    }
}
