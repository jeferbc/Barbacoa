package jeffersonbernalcardona.barbacoa;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {
    private static final long SPLASH_DELAY=5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);    //Set an orientation
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE); //Remove app bar(Bar in the up of the app with the name of app)
        setContentView(R.layout.activity_splash);

        TimerTask Task = new TimerTask() {
            @Override
            public void run() {
                Intent i = new Intent().setClass(SplashActivity.this,LoginActivity.class);
                startActivity(i);
                finish();

            }
        };
        Timer timer=new Timer();
        timer.schedule(Task,SPLASH_DELAY);
    }
}