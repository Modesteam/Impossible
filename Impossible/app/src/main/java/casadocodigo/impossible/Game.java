package casadocodigo.impossible;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class Game extends AppCompatActivity implements View.OnTouchListener {
    Impossible view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Set FullScreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Call Super onCreate
        super.onCreate(savedInstanceState);

        // Game's Logic
        view = new Impossible(this);

        view.setOnTouchListener(this);

        // Configure the View
        setContentView(view);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        view.moveDown(10);
        view.addScore(100);
        if( event.getX() < 100 && event.getY() > 290 && event.getY() < 310){
            view.init();
        }

        if(event.getX() < 100 && event.getY() > 490 && event.getY() < 510){
            System.exit(0);
        }

        view.moveDown(10);
        view.addScore(100);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        view.resume();
    }
}