package dangchph33497.fpoly.libmana.Hello;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

import dangchph33497.fpoly.libmana.LoginActivity;
import dangchph33497.fpoly.libmana.R;

public class SplashScreenActivity extends AppCompatActivity {
    private TextView view;
    private LottieAnimationView lottieAnimation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        lottieAnimation = findViewById(R.id.lottieAnimation);
        lottieAnimation.animate().setDuration(1500).setStartDelay(0);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },1500);
    }
}