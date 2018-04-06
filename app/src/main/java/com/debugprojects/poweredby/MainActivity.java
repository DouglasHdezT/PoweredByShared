package com.debugprojects.poweredby;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //Declarando variables de control

    private TextView editText_email;
    private TextView editText_git;
    private TextView editText_facebook;
    private TextView editText_wa;

    private Button button_share;

    private String email_var;
    private String git_var;
    private String facebook_var;
    private String wa_var;

    private String msg_final;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getAllViews();

        button_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email_var = editText_email.getText().toString();
                git_var = editText_git.getText().toString();
                facebook_var = editText_facebook.getText().toString();
                wa_var = editText_wa.getText().toString();

                sendInfo(contentToString(email_var,git_var,facebook_var,wa_var));

            }
        });
    }

    private void getAllViews(){

        editText_email = findViewById(R.id.edit_text_email);
        editText_facebook = findViewById(R.id.edit_text_facebook);
        editText_git = findViewById(R.id.edit_text_github);
        editText_wa = findViewById(R.id.edit_text_wa);

        button_share = findViewById(R.id.button_share);

    }

    private String contentToString(String email, String git, String facebook, String wa){
        String msg = "About me... \nCorreo electronico: "+email+"\nGithub: "+git+
                "\nFacebook: "+facebook+"\nWhatsapp: "+wa;

        return msg;
    }

    private void sendInfo(String msg){
        Intent sendIntent = new Intent();

        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, msg);
        sendIntent.setType("plain/text");

        startActivity(Intent.createChooser(sendIntent,"About me share..."));
    }
}
