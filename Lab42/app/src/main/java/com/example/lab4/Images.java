package com.example.lab4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Images extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_images);

        ImageView imageView2 = findViewById(R.id.imageView2);
        ImageView imageView3 = findViewById(R.id.imageView3);
        ImageView imageView4 = findViewById(R.id.imageView4);
        ImageView imageView5 = findViewById(R.id.imageView5);
        ImageView imageView6 = findViewById(R.id.imageView6);
        ImageView imageView7 = findViewById(R.id.imageView7);

        View.OnClickListener setTeamIconListener = this::setTeamIcon;
        imageView7.setOnClickListener(setTeamIconListener);
        imageView2.setOnClickListener(setTeamIconListener);
        imageView3.setOnClickListener(setTeamIconListener);
        imageView4.setOnClickListener(setTeamIconListener);
        imageView5.setOnClickListener(setTeamIconListener);
        imageView6.setOnClickListener(setTeamIconListener);
    }

    protected void setTeamIcon(View view) {


        ImageView avatarImage = (ImageView) findViewById(R.id.avatarImageView);

        String drawableName = "ic_logo_00";


        if (view.getId() == R.id.imageView2) {
            drawableName = "ic_logo_01";
        } else if (view.getId() == R.id.imageView3) {
            drawableName = "ic_logo_02";
        } else if (view.getId() == R.id.imageView4) {
            drawableName = "ic_logo_03";
        } else if (view.getId() == R.id.imageView5) {
            drawableName = "ic_logo_04";
        } else if (view.getId() == R.id.imageView6) {
            drawableName = "ic_logo_05";
        } else if (view.getId() == R.id.imageView7) {
            drawableName = "ic_logo_06";
        }

        Intent returnIntent = new Intent();
        returnIntent.putExtra("drawableName", drawableName);
        setResult(RESULT_OK, returnIntent);
        finish();




    }









}
