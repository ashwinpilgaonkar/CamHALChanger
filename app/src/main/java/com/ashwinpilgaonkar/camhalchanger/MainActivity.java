package com.ashwinpilgaonkar.camhalchanger;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import eu.chainfire.libsuperuser.Shell;

public class MainActivity extends AppCompatActivity{

    static TextView Status;
    static Button SultanHAL;
    static Button StockHAL;
    static Button Camera;
    static Button Backup;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //INITIALIZE UI ELEMENTS
        Status = (TextView) findViewById(R.id.Status_TextView);
        SultanHAL = (Button) findViewById(R.id.SultanHAL_Button);
        StockHAL = (Button) findViewById(R.id.StockHAL_Button);
        Camera = (Button) findViewById(R.id.CameraLauncher_Button);
        Backup = (Button) findViewById(R.id.BackupStock_Button);

        //CHECK FOR ROOT ACCESS
        CheckRoot checkRoot = new CheckRoot();
        checkRoot.execute();

        //SET ACTION BAR ICON
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_launcher);

        SultanHAL.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View V) {

                        //BACKS UP STOCK FILES TO .STOCK
                        String[] commands = {"mv system/lib/hw/camera.msm8974.so system/lib/hw/camera.msm8974.so.stock",

                                "mv system/lib/libmmcamera_interface.so system/lib/libmmcamera_interface.so.stock",
                                "mv system/lib/libmmjpeg_interface.so system/lib/libmmjpeg_interface.so.stock",
                                "mv system/lib/libqomx_core.so system/lib/libqomx_core.so.stock",

                                "mv system/vendor/lib/libchromatix_s5k3m2_hfr_60fps.so system/vendor/lib/libchromatix_s5k3m2_hfr_60fps.so.stock",
                                "mv system/vendor/lib/libchromatix_s5k3m2_hfr_90fps.so system/vendor/lib/libchromatix_s5k3m2_hfr_90fps.so.stock",
                                "mv system/vendor/lib/libchromatix_s5k3m2_hfr_120fps.so system/vendor/lib/libchromatix_s5k3m2_hfr_120fps.so.stock",
                                "mv system/vendor/lib/libmmjpeg.so system/vendor/lib/libmmjpeg.so.stock",


                                //RESTORES SULTAN HAL FILES
                                "mv system/lib/hw/camera.msm8974.so.sultan system/lib/hw/camera.msm8974.so",

                                "mv system/lib/libmmcamera_interface.so.sultan system/lib/libmmcamera_interface.so",
                                "mv system/lib/libmmjpeg_interface.so.sultan system/lib/libmmjpeg_interface.so",
                                "mv system/lib/libqomx_core.so.sultan system/lib/libqomx_core.so",

                                "mv system/vendor/lib/libchromatix_s5k3m2_hfr_60fps.so.sultan system/vendor/lib/libchromatix_s5k3m2_hfr_60fps.so",
                                "mv system/vendor/lib/libchromatix_s5k3m2_hfr_90fps.so.sultan system/vendor/lib/libchromatix_s5k3m2_hfr_90fps.so",
                                "mv system/vendor/lib/libchromatix_s5k3m2_hfr_120fps.so.sultan system/vendor/lib/libchromatix_s5k3m2_hfr_120fps.so",
                                "mv system/vendor/lib/libmmjpeg.so.sultan system/vendor/lib/libmmjpeg.so"};

                        MoveFilesTask task = new MoveFilesTask(getApplicationContext());
                        task.execute(commands);
                    }
                }
        );

        StockHAL.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View V) {

                        //BACKUP SULTAN HAL FILES TO .SULTAN
                        String[] commands = {"mv system/lib/hw/camera.msm8974.so system/lib/hw/camera.msm8974.so.sultan",

                                "mv system/lib/libmmcamera_interface.so system/lib/libmmcamera_interface.so.sultan",
                                "mv system/lib/libmmjpeg_interface.so system/lib/libmmjpeg_interface.so.sultan",
                                "mv system/lib/libqomx_core.so system/lib/libqomx_core.so.sultan",

                                "mv system/vendor/lib/libchromatix_s5k3m2_hfr_60fps.so system/vendor/lib/libchromatix_s5k3m2_hfr_60fps.so.sultan",
                                "mv system/vendor/lib/libchromatix_s5k3m2_hfr_90fps.so system/vendor/lib/libchromatix_s5k3m2_hfr_90fps.so.sultan",
                                "mv system/vendor/lib/libchromatix_s5k3m2_hfr_120fps.so system/vendor/lib/libchromatix_s5k3m2_hfr_120fps.so.sultan",
                                "mv system/vendor/lib/libmmjpeg.so system/vendor/lib/libmmjpeg.so.sultan",


                                //RESTORE STOCK FILES
                                "mv system/lib/hw/camera.msm8974.so.stock system/lib/hw/camera.msm8974.so",

                                "mv system/lib/libmmcamera_interface.so.stock system/lib/libmmcamera_interface.so",
                                "mv system/lib/libmmjpeg_interface.so.stock system/lib/libmmjpeg_interface.so",
                                "mv system/lib/libqomx_core.so.stock system/lib/libqomx_core.so",

                                "mv system/vendor/lib/libchromatix_s5k3m2_hfr_60fps.so.stock system/vendor/lib/libchromatix_s5k3m2_hfr_60fps.so",
                                "mv system/vendor/lib/libchromatix_s5k3m2_hfr_90fps.so.stock system/vendor/lib/libchromatix_s5k3m2_hfr_90fps.so",
                                "mv system/vendor/lib/libchromatix_s5k3m2_hfr_120fps.so.stock system/vendor/lib/libchromatix_s5k3m2_hfr_120fps.so",
                                "mv system/vendor/lib/libmmjpeg.so.stock system/vendor/lib/libmmjpeg.so"};

                        MoveFilesTask task = new MoveFilesTask(getApplicationContext());
                        task.execute(commands);
                    }

                }
        );

        Camera.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View V) {

                         Intent intent = new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
                        startActivity(intent);
                    }
                }
        );

        Backup.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View V) {

                        String[] commands = {"mv system/lib/hw/camera.msm8974.so system/lib/hw/camera.msm8974.so.stock",

                                "mv system/lib/libmmcamera_interface.so system/lib/libmmcamera_interface.so.stock",
                                "mv system/lib/libmmjpeg_interface.so system/lib/libmmjpeg_interface.so.stock",
                                "mv system/lib/libqomx_core.so system/lib/libqomx_core.so.stock",

                                "mv system/vendor/lib/libchromatix_s5k3m2_hfr_60fps.so system/vendor/lib/libchromatix_s5k3m2_hfr_60fps.so.stock",
                                "mv system/vendor/lib/libchromatix_s5k3m2_hfr_90fps.so system/vendor/lib/libchromatix_s5k3m2_hfr_90fps.so.stock",
                                "mv system/vendor/lib/libchromatix_s5k3m2_hfr_120fps.so system/vendor/lib/libchromatix_s5k3m2_hfr_120fps.so.stock",
                                "mv system/vendor/lib/libmmjpeg.so system/vendor/lib/libmmjpeg.so.stock"};

                        MoveFilesTask task = new MoveFilesTask(getApplicationContext());
                          task.execute(commands);
                    }
                }
        );
    }

    //CHECKS WHICH HAL IS CURRENTLY ENABLED
    public void getStatus() {
        File file = new File("system/lib/hw/camera.msm8974.so.sultan");
        File file2 = new File("system/lib/hw/camera.msm8974.so.stock");

        //CHECKS IF CURRENTLY ENABLED HAL IS STOCK
        if(file.exists()) {
            Status.setText("Stock HAL");
            StockHAL.setEnabled(false);
            SultanHAL.setEnabled(true);
            Backup.setEnabled(false);
        }

        //CHECKS IF CURRENTLY ENABLED HAL IS SULTAN
        else if(file2.exists()) {
            Status.setText("Sultan's HAL");
            SultanHAL.setEnabled(false);
            StockHAL.setEnabled(true);
            Backup.setEnabled(false);
        }

        //BOTH FILES ARE ABSENT- HAL NOT FLASHED
        else if(!file.exists() && !file2.exists()){
            Status.setText("Not installed");
            SultanHAL.setEnabled(false);
            StockHAL.setEnabled(false);
            Backup.setEnabled(true);
        }

        //IF BOTH FILES EXIST, IT MEANS SOMETHING IS WRONG
        else {
            Status.setText("Unknown Error");
            SultanHAL.setEnabled(false);
            StockHAL.setEnabled(false);
            Backup.setEnabled(false);
        }
    }

    //ASYNC TASK TO PERFORM FILE MOVE OPERATIONS
    private class MoveFilesTask extends AsyncTask<String, Void, Void> {

        Context context;

        //getApplicationContext RETURNS NULL INSIDE ASYNCTASK SO CONTEXT HAS BEEN PASSED TO CONSTRUCTOR FROM MAINACTIVITY
        public MoveFilesTask(Context con) {
            pd = new ProgressDialog(MainActivity.this);
            context = con;
        }

        @Override
        protected void onPreExecute() {

            //SHOW SPINNER PROGRESS DIALOG DURING FILE MOVE OPERATION
            pd.setTitle("Please Wait");
            pd.setMessage("Working...");
            pd.setCancelable(false);
            pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);

            //ADD 100ms DELAY BETWEEN BUTTON PRESS AND DIALOG BOX SHOWING UP
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            pd.show();
        }

        protected Void doInBackground(String... commands) {

            for (int i=0; i<commands.length; i++)
                Shell.SU.run(commands[i]);

            //RESTART CAMERA SERVICE TO APPLY SETTINGS
            Shell.SU.run("pkill media");

            return null;
        }

        protected void onPostExecute(Void result) {
            if (pd.isShowing()) {
                pd.dismiss();
            }

            //REFRESH TEXT AND BUTTONS AFTER HAL IS CHANGED
            MainActivity mainActivity = new MainActivity();
            mainActivity.getStatus();


            //ADD 150ms DELAY BEFORE SHOWING TOAST
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Toast toast = Toast.makeText(context, "Action performed successfully", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    //ASYNC TASK TO CHECK FOR ROOT
    private class CheckRoot extends AsyncTask<Void, Void, Void> {
        boolean root;

        @Override
        protected Void doInBackground(Void... params) {

            if (!Shell.SU.available())
                root = false;

            else {
                root = true;
                Shell.SU.run("mount -o rw,remount /system"); //ENABLE WRITING TO SYSTEM
            }


            return null;
        }

        protected void onPostExecute(Void result) {

            if (!root) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(false);

                builder.setTitle("Error");
                builder.setMessage("Root access unavailable.");

                builder.setNeutralButton("Exit",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                System.exit(0);
                            }
                        });
                builder.show();
            }

            else {
                MainActivity mainActivity = new MainActivity();
                mainActivity.getStatus();
            }
        }
    }
}
