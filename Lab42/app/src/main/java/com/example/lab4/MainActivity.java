package com.example.lab4;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button buttonMaps;
    ImageView avatarImageView;
    EditText name;
    EditText teamLocation;
    ActivityResultLauncher<Intent> launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        buttonMaps = findViewById(R.id.map_button);
        buttonMaps.setOnClickListener(this::onClickMapButton);
        avatarImageView = findViewById(R.id.avatarImageView);
        avatarImageView.setOnClickListener(this::onClickViewImage);

        // Register for activity result
        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Intent data = result.getData();
                        if (result.getResultCode() == RESULT_OK && data != null) {
                            String drawableName = data.getStringExtra("drawableName");
                            int resID = getResources().getIdentifier(drawableName, "drawable", getPackageName());
                            avatarImageView.setImageResource(resID);
                        }
                    }
                });

        name = findViewById(R.id.EditTextName);
        teamLocation = findViewById(R.id.EditTextLocation);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void onOpenInGoogleMaps(String location) {
        // Create a Uri from an intent string
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(location));
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    public void onClickMapButton(View view) {
        String teamAddressString = teamLocation.getText().toString();
        onOpenInGoogleMaps(teamAddressString);
    }

    public void onClickViewImage(View view) {
        // Go to image profile
        Intent intent = new Intent(getApplicationContext(), Images.class);
        launcher.launch(intent);
    }
}
