package casadocodigo.impossible;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

public class Game extends AppCompatActivity {
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

        // Configure the View
        setContentView(view);
    }

    @Override
    protected void onResume() {
        super.onResume();
        view.resume();
    }
}