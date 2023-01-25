package com.example.pytorchapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView results1;
    private TextView results2;
    private TextView results3;
    private TextView results4;
    private TextView results5;
    private Button button1;
    private Button button;
    private ModelClass MClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        results1 = findViewById(R.id.results1);
        results2 = findViewById(R.id.results2);
        results3 = findViewById(R.id.results3);
        results4 = findViewById(R.id.results4);
        results5 = findViewById(R.id.results5);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClicked();
            }
        });

        MClass = new ModelClass(this);

        Toast.makeText(this, "Prep Done!", Toast.LENGTH_LONG).show();
    }

    private void buttonClicked() {
        try {
            Bitmap birdMap = BitmapFactory.decodeStream(getAssets().open("cardinal.jpg"));
            Bitmap butterflyMap = BitmapFactory.decodeStream(getAssets().open("648985.jpg"));

            int[] birdArray = MClass.runModel(birdMap, "NABirdsModel", 5);
            int[] biosphereArray = MClass.runModel(butterflyMap, "BiosphereModel", 5);
            int[] butterflyArray = MClass.runModel(butterflyMap, "ButterflyModel", 5);
            int[] metaArray = MClass.runModel(birdMap, "MetaModel", 5);

            results1.setText(String.format(Locale.US, "Bird: %d", birdArray[0]));
            results2.setText(String.format(Locale.US, "Biosphere: %d", biosphereArray[0]));
            results3.setText(String.format(Locale.US, "Butterfly: %d", butterflyArray[0]));
            results4.setText(String.format(Locale.US, "Meta: %d", metaArray[0]));
            results5.setText("DONE!");

            Toast.makeText(this, "Done!", Toast.LENGTH_LONG).show();
        }
        catch (IOException e) {
            System.out.println("issue");
        }
    }


}
