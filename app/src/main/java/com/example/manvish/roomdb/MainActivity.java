package com.example.manvish.roomdb;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.manvish.roomdb.database.AppDatabase;
import com.example.manvish.roomdb.utils.DatabaseInitializer;

import java.io.File;
import java.io.FileInputStream;
import java.nio.channels.FileChannel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createDB();
        DatabaseInitializer.populateAsync(AppDatabase.getAppDatabase(this));
    }

    private void createDB() {

        try {
            File data = Environment.getExternalStorageDirectory();

            Log.d("Gajanand", "createDB: "+data.getAbsolutePath());
            String currentDBPath=getDatabasePath("user.db").getAbsolutePath();
            File file=getDatabasePath("user.db");

            Log.d("Gajanand", "createDB: file"+file.getAbsolutePath());
            File currentDB = new File( currentDBPath);



            Log.d("Gajanand", "createDB: "+currentDB.getAbsolutePath()+" "+currentDBPath);

                if (!currentDB.exists()) {
                    currentDB.mkdir();
                    FileChannel src = new FileInputStream(currentDB).getChannel();
                    src.close();
                }

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("Gajanand", "createDB: "+e.toString());
        }
    }

    @Override
    protected void onDestroy() {
        AppDatabase.destroyInstance();
        super.onDestroy();
    }


}
