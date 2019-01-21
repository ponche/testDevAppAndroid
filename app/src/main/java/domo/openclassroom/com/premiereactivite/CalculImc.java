package domo.openclassroom.com.premiereactivite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Remi on 28/01/2017.
 *
 * Activity qui calcul IMC et renvoie un commentaire en fonction de indice
 */




public class CalculImc extends Activity implements View.OnClickListener {
    //Attributs
    protected EditText vuePoids = null;
    protected EditText vueTaille = null;
    protected Button vueValider = null;
    protected TextView vueCommentaire = null;
    protected float resultatIMC = 0;
    protected String commentairePoids = "" ;

    // public PendingIntent intentTest = new PendingIntent();


    // Méthodes
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_imc);

        Button boutonValider = (Button) findViewById(R.id.valider);
        boutonValider.setOnClickListener(this);

        // on renseigne les liens vers les vue
        vuePoids = (EditText) findViewById(R.id.champPourcentage);
        vueTaille = (EditText) findViewById(R.id.champTaille);
        vueValider = (Button) findViewById(R.id.valider);
        vueCommentaire = (TextView) findViewById(R.id.commantaireIMC);

        // Zone de test
        /*Uri sms = Uri.parse("sms:0667973869?body=Salut");
        Uri telephone = Uri.parse("tel:0667973869");
        Intent secondeActivite = new Intent(Intent.ACTION_DIAL, telephone);
        startActivity(secondeActivite);*/

        // Zone  de test prochaine activity
        //Intent intentAlcool = new Intent(MainActivity.this, CalculAlcoolActivity.class);
        //startActivity(intentAlcool);
    }

    @Override
    public void onClick(View view) {
        String stringPoids =  vuePoids.getText().toString();
        String stringTaille = vueTaille.getText().toString();

        if(stringPoids.isEmpty() || stringTaille.isEmpty())
        {
            vueCommentaire.setText("Vous devez remplir les champ!!!!!!");

        }
        if(!stringPoids.isEmpty() && !stringTaille.isEmpty()) {
            float lePoids = Float.valueOf(stringPoids);
            float laTaille = Float.valueOf(stringTaille);

            resultatIMC = calculIMC(lePoids, laTaille);

            commentairePoids = choixCommentaire(resultatIMC);

            vueCommentaire.setText(commentairePoids);

            /* ligne en dessous :
            Test d'envoie SMS via un Intent et la méthode direct sans confirmation d'envoi
             */

            /*Intent sms = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:0667973869"));
            sms.putExtra("sms_body", commentairePoids);
            startActivity(sms);*/



            //SmsManager.getDefault().sendTextMessage("0667973869", null, commentairePoids, null, null);

            //Intent intentAlcool = new Intent(MainActivity.this, CalculAlcoolActivity.class);
            //startActivity(intentAlcool);



        }



    }

    public float calculIMC(float poids, float taille) {

        float resultat = poids / (taille * taille) ;

        return resultat ;
    }

    public String choixCommentaire(float resulatIMC)
    {

        /* les string doivent etre stocker dans values/string et non les hardcoder
        permet de faire une traduction des string en fonction de la langue local
         */
        String conseil;
        if (resulatIMC < 18)
        {
            conseil = "Vous etes minces, votre IMC est de " + String.valueOf(resulatIMC);
        }
        else if(resultatIMC < 25)
        {
            conseil ="Vous etes dans la normal, votre IMC est de " + String.valueOf(resulatIMC);
        }
        else if(resulatIMC < 29)
        {
            conseil = "vous etes en légér surpoids, faites attention a votre ligne. votre imc est de " + String.valueOf(resulatIMC);
        }
        else if(resulatIMC < 40)
        {
            conseil = "vous etes obese, votre imc est de " + String.valueOf(resulatIMC);
        }
        else
        {
            conseil = "vous avez une obeséité morbide. votre imc est de " + String.valueOf(resulatIMC) ;

        }

        return conseil ;
    }

    @Override

    protected void onPause()
    {
        // Supprime les champ quand Activity et supprimer ou mis en arrière plan
        super.onPause();
        vueCommentaire.setText("");
        vueTaille.setText("");
        vuePoids.setText("");
    }
}