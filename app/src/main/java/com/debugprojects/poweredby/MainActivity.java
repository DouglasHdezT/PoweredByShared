package com.debugprojects.poweredby;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    //Declarando variables de control

    private TextView editText_email;
    private TextView editText_git;
    private TextView editText_facebook;
    private TextView editText_wa;
    private TextView editText_name;
    private TextView editText_career;

    private ImageView image_profile_photo;

    private Button button_share_info;
    private Button button_share_img;

    private String email_var;
    private String git_var;
    private String facebook_var;
    private String wa_var;
    private String name_var;
    private String career_var;

    private String msg_final;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getAllViews();

        button_share_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email_var = editText_email.getText().toString();
                git_var = editText_git.getText().toString();
                facebook_var = editText_facebook.getText().toString();
                wa_var = editText_wa.getText().toString();
                name_var = editText_name.getText().toString();
                career_var = editText_career.getText().toString();

                sendInfo(contentToString(email_var,git_var,facebook_var,wa_var, name_var, career_var));

            }
        });

        button_share_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareDrawable(R.drawable.icono_powered, "icono_powered");
            }
        });
    }

    private void getAllViews(){

        editText_email = findViewById(R.id.edit_text_email);
        editText_facebook = findViewById(R.id.edit_text_facebook);
        editText_git = findViewById(R.id.edit_text_github);
        editText_wa = findViewById(R.id.edit_text_wa);
        editText_career = findViewById(R.id.edit_text_carrera);
        editText_name = findViewById(R.id.edit_text_name);

        image_profile_photo = findViewById(R.id.image_profile_photo);

        button_share_info = findViewById(R.id.button_share_info);
        button_share_img = findViewById(R.id.button_share_image);

    }

    private String contentToString(String email, String git, String facebook, String wa, String name, String career){
        String msg = "About me...\nNombre: "+name+"\nCarrera: "+career+"\nCorreo electronico: "+
                email+"\nGithub: "+git+"\nFacebook: "+facebook+"\nWhatsapp: "+wa;

        return msg;
    }

    private void sendInfo(String msg){
        Intent sendIntent = new Intent();

        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, msg);
        sendIntent.setType("text/plain");

        startActivity(Intent.createChooser(sendIntent,"About me share..."));
    }

    private void sendImg(Uri image_uri){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, "Hello");
        intent.putExtra(Intent.EXTRA_STREAM, image_uri);
        intent.setType("image/png");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(intent);
    }

    public void shareDrawable(int resourceId, String fileName) {
        try {
            //Convertir el recurso en bitmap
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resourceId);

            //Guardar el bitmap en cache
            File outputFile = new File(getCacheDir(), fileName + ".png");
            FileOutputStream outPutStream = new FileOutputStream(outputFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outPutStream);
            outPutStream.flush();
            outPutStream.close();
            outputFile.setReadable(true, false);

            //share file
            Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(outputFile));
            shareIntent.setType("image/png");
            startActivity(shareIntent);
        }
        catch (Exception e) { Toast.makeText(this, "error", Toast.LENGTH_LONG).show();
        }
    }
}
