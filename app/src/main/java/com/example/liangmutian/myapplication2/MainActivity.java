package com.example.liangmutian.myapplication2;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;


public class MainActivity extends AppCompatActivity {


    LowStorageDialog lowStorageDialog;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
    }


    public void Onclick(View v) {

        lowStorageDialog = new LowStorageDialog.Builder(this)
                .setContent(R.string.low_storage_warning_message)
                .initBtn()
                .setPositiveBtn(R.string.allow, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        lowStorageDialog.dismiss();

                    }
                })
                .setNegativeBtn(R.string.manual_clean, new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        try {
                            Intent intent = new Intent();
                            intent.setComponent(new ComponentName("com.android.settings", "com.android.settings.SubSettings"));
                            intent.putExtra(":settings:show_fragment", "com.android.settings.SpaceManagementSettings");
                            startActivity(intent);
                        } catch (Exception e) {
                            Log.e("settings", "start settings error: "+e);
                        }

                    }
                }).build();
        lowStorageDialog.show();



    }
}
