package com.example.storageexample;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.FileSystem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        writeFileExternalStorage();
    }

    private void writeFileExternalStorage() {
        //Text of the Document
        String NEW_LINE =  System.getProperty("line.separator") ;
        String textToWrite = "This string write in txt file.";

        //Checking the availability state of the External Storage.
        String state = Environment.getExternalStorageState();
        if (!Environment.MEDIA_MOUNTED.equals(state)) {

            //If it isn't mounted - we can't write into it.
            return;
        }

        //Create a new file that points to the root directory, with the given name:

        //File file = new File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "test.txt"); // Storage/self/primary/Android/data/com.example.storageexample/files
        File file = new File(getExternalFilesDir("Belge/Txt/"), "test.txt");
        // File file = new File(Environment.getExternalStoragePublicDirectory("Resim/"), "test.txt");
        // File file = getExternalCacheDir();
        Log.d("@@@", file.getAbsolutePath());


        //This point and below is responsible for the write operation
        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            //second argument of FileOutputStream constructor indicates whether
            //to append or create new file if one exists
            outputStream = new FileOutputStream(file, true);

            outputStream.write((NEW_LINE + textToWrite).getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}