package casadocodigo.impossible;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Impossible extends SurfaceView implements Runnable {
    boolean running = false;
    Thread renderThread = null;
    SurfaceHolder holder;
    Paint paint;

    public Impossible(Context context) {
        super(context);
        paint = new Paint();
        holder = getHolder();
    }

    private void drawPlayer(Canvas canvas) {
        paint.setColor(Color.GREEN);
        canvas.drawCircle(100, 100, 100, paint);
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

            // Draw the Player
            drawPlayer(canvas);

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
