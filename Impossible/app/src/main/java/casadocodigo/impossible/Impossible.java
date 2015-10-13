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
    private int playerY = 300;
    private float enemyRadius;

    public Impossible(Context context) {
        super(context);
        paint = new Paint();
        holder = getHolder();
    }

    private void drawPlayer(Canvas canvas) {
        paint.setColor(Color.GREEN);
        canvas.drawCircle(100, playerY, 100, paint);
    }

    private void drawEnemy(Canvas canvas) {
        paint.setColor(Color.GRAY);
        enemyRadius++;
        canvas.drawCircle(100, 100, enemyRadius, paint);
    }

    public void moveDown(int pixels) {
        playerY += pixels;
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

            // Update and free the canvas
            holder.unlockCanvasAndPost(canvas);
        }
    }

    public void resume() {
        running = true;
        renderThread = new Thread(this);
        renderThread.start();
    }
}
