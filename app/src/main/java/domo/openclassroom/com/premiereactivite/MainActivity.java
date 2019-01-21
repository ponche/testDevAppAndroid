package domo.openclassroom.com.premiereactivite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



/*
Activty Menu, redige vers les Activity choisir
 */

public class MainActivity extends Activity
{
    //Attributs
    protected Intent m_intentAlcool;
    protected Intent m_intentImc;
    protected Intent m_intentCamera;
    protected Intent m_intentLoop;

    protected Button m_boutonAlcool;
    protected Button m_boutonImc;
    protected Button m_boutonCamera;

    //Methode
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);


        m_intentImc = new Intent(MainActivity.this, CalculImc.class);
        m_intentAlcool = new Intent(MainActivity.this, CalculAlcoolActivity.class);
        m_intentCamera = new Intent(MainActivity.this, CameraActivity.class);

        setContentView(R.layout.activity_main);

        m_boutonAlcool = (Button) findViewById(R.id.boutonAlcool);
        m_boutonImc = (Button) findViewById(R.id.boutonImc);
        m_boutonCamera = (Button) findViewById(R.id.boutonCamera);



        m_boutonAlcool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(m_intentAlcool);
            }
        });

        m_boutonImc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(m_intentImc);
            }
        });

        m_boutonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(m_intentCamera);
            }
        });

    }

}