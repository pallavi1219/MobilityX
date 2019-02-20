package mobilityx.mobilutygroup.com.mobilityx;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;

import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.io.File;

public class MediaPlaybackActivity extends AppCompatActivity {

    Camera camera;
    FrameLayout frameLayout;
    ShowCamera showCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final int REQUEST_VIDEO_CAPTURE = 1;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_playback2);

        frameLayout = (FrameLayout) findViewById(R.id.framelayout);

            camera = Camera.open();
            showCamera = new ShowCamera(this, camera);

            frameLayout.addView(showCamera);

        final Button button = findViewById(R.id.recordbutton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
                }
            }
        });

    }
}
