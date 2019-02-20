package mobilityx.mobilutygroup.com.mobilityx;

import android.content.ContextWrapper;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class DevicePositionActivity extends AppCompatActivity {

    Camera camera;
    FrameLayout frameLayout;
    ShowCamera showCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_position);
        frameLayout = (FrameLayout) findViewById(R.id.framelayout);

        final Button button = findViewById(R.id.captureimagebutton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                captureImage(v);
            }
        });

        camera = Camera.open();
        showCamera = new ShowCamera(this, camera);

        frameLayout.addView(showCamera);
    }

    public void captureImage(View v) {

        if(camera!=null) {
            camera.takePicture(null, null, mPictureCallback);
        }

    }
    
    Camera.PictureCallback mPictureCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {

            File picture_file = getOutputMediaFile();

            if(picture_file == null) {
                return;
            } else {

                try {
                    FileOutputStream fos = new FileOutputStream(picture_file);
                    fos.write(data);
                    fos.close();
                    camera.startPreview();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    };


    private File getOutputMediaFile() {

       /* String state = Environment.getExternalStorageState();

        if(!state.equals(Environment.MEDIA_MOUNTED)) {
            return null;
        }
        else {
            File folder_gui = new File(Environment.getExternalStorageDirectory() + File.separator + "GUI");

            if(!folder_gui.exists()) {
                folder_gui.mkdirs();
            }

            File outputFile = new File(folder_gui, "abc.jpg");
            return outputFile;
        }*/

        ContextWrapper wrapper = new ContextWrapper(getApplicationContext());

        File file = wrapper.getDir("Images",MODE_PRIVATE);
        file = new File(file, "carImages" + (int)(Math.random() * 1000) + ".jpg");
        System.out.println("IMAGE FILE:" + file.getAbsolutePath());
        return file;

    }
    
    
}
