package domo.openclassroom.com.premiereactivite;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by Remi on 28/01/2017.
 */

public class CalculAlcoolActivity extends Activity implements View.OnClickListener
{
    //Attributs
    protected EditText m_vueCentiLitreBu;
    protected EditText m_vuePourcentageAlcool;
    protected EditText m_vuePoids;
    protected float m_tauxAlcool;
    protected String m_commentaireAlcool;
    protected Button m_vueBoutonValider;
    protected RadioGroup m_vueSelectionSex;
    protected TextView m_vueCommentaireAlcool;

    //Méthodes

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //Selection de la vue
        setContentView(R.layout.activity_alcool);

        //récupération des vues
        m_vueCentiLitreBu = (EditText) findViewById(R.id.champClBu);
        m_vuePourcentageAlcool = (EditText) findViewById(R.id.champPourcentage);
        m_vuePoids = (EditText) findViewById(R.id.champPoids);
        m_vueSelectionSex = (RadioGroup) findViewById(R.id.selectionSex);
        m_vueBoutonValider = (Button) findViewById(R.id.buttonValider);
        m_vueCommentaireAlcool = (TextView) findViewById(R.id.commentaireAlcool);


        m_vueBoutonValider.setOnClickListener(this);

    }

    @Override
    public void onClick(View v)
    {
        boolean testFormulaire = true ;
        if (m_vueCentiLitreBu.getText().toString().isEmpty())
            testFormulaire = false;
        if (m_vuePourcentageAlcool.getText().toString().isEmpty())
            testFormulaire = false;
        if (m_vuePoids.getText().toString().isEmpty())
            testFormulaire = false;
        if(m_vueSelectionSex.getCheckedRadioButtonId() == -1)
            testFormulaire = false;

        if(testFormulaire)
        {
            // le formulaire est rempli, on peut faire les calcul

            // récupération des valeurs
            float centilitre = Float.valueOf(m_vueCentiLitreBu.getText().toString());
            float pourcentageAlcool = Float.valueOf(m_vuePourcentageAlcool.getText().toString());
            float poids = Float.valueOf(m_vuePoids.getText().toString());
            boolean masculin;
            int identifiantBox = m_vueSelectionSex.getCheckedRadioButtonId();
            if(identifiantBox == R.id.selectionHomme)
                masculin = true;
            else
                masculin = false;



            m_tauxAlcool = calculAlcool(centilitre, 100 / pourcentageAlcool, poids, masculin);
            m_commentaireAlcool = choixCoomentaire(m_tauxAlcool);
            m_vueCommentaireAlcool.setText(m_commentaireAlcool);

        }

    }

    public float calculAlcool(float centilitreBu, float pourcentageAlcool, float masse, boolean homme)
    {
        float coefficientSex;
        float tauxAlcool;
        if(homme)
            coefficientSex = (float) 0.7;
        else
            coefficientSex = (float) 0.6;

        tauxAlcool = (float) ((centilitreBu * pourcentageAlcool * 0.8) / (coefficientSex * masse));
        return  tauxAlcool;

    }

    public String choixCoomentaire(float resultatAlcool)
    {
        String commentaire;
        if(resultatAlcool < 0.20)
            //commentaire = "vous pouvez reprendre le volant";
            commentaire = (String)  getText(R.string.bonneRoute);
        else if(resultatAlcool < 0.50)
            //commentaire = "vous etes en dessous du seuil légal, mais attention vous pouvez resentir les effets de l'alcool" ;
            commentaire = (String) getText(R.string.prudence);
        else if(resultatAlcool < 0.80)
            //commentaire = "ne prenez pas le volant, vous etes au dessous du seuil légal" ;
            commentaire = (String) getText(R.string.infraction);
        else if(resultatAlcool < 2)
            //commentaire = "vous etes en état d'ivresse, c'est un Délit si vous prenez le volant avec votre état d'ivresse";
            commentaire = (String) getText(R.string.delit);
        else
            //commentaire = "vous tenez toujours debout? vous tenez bien l'alccol";
            commentaire = (String) getText(R.string.coma);

        return commentaire ;
    }
}
