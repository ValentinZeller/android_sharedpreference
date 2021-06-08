package com.example.persistancedonnee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    EditText nom;
    EditText prenom;
    EditText email;
    EditText phone;
    TextView compteur;
    Button saveButton;

    //Affichage des données sauvegardées
    TextView savedNom;
    TextView savedPrenom;
    TextView savedMail;
    TextView savedPhone;

    int valeurCompteur = 0;

    SharedPreferences myPref;
    public static final String PREFERENCES = "myPrefs"; //Nom du fichier xml des shared preferences

    //Clés
    public static final String SHARED_NOM = "SharedNom";
    public static final String SHARED_PRENOM = "SharedPrenom";
    public static final String SHARED_MAIL = "SharedMail";
    public static final String SHARED_PHONE = "SharedPhone";
    public static final String SHARED_COMPTEUR = "SharedCompteur";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_PersistanceDonnee);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nom = (EditText)findViewById(R.id.editNom);
        prenom = (EditText)findViewById(R.id.editPrenom);
        email = (EditText)findViewById(R.id.editEmail);
        phone = (EditText)findViewById(R.id.editTelephone);
        saveButton = (Button)findViewById(R.id.sauvegarder);
        compteur = (TextView)findViewById(R.id.compteur);
        savedNom = (TextView)findViewById(R.id.savedNom);
        savedPrenom = (TextView)findViewById(R.id.savedPrenom);
        savedMail = (TextView)findViewById(R.id.savedMail);
        savedPhone = (TextView)findViewById(R.id.savedPhone);

        //Shared preferences
        myPref = getSharedPreferences(PREFERENCES,MODE_PRIVATE);
        SharedPreferences.Editor myEditor = myPref.edit();

        //Gestion du compteur d'utilisation
        valeurCompteur = (myPref.getInt(SHARED_COMPTEUR,0));
        if (valeurCompteur == 0) {
            valeurCompteur++;
            myEditor.putInt(SHARED_COMPTEUR,valeurCompteur);
            myEditor.apply();
        } else {
            valeurCompteur++;
            myEditor.putInt(SHARED_COMPTEUR,valeurCompteur);
            myEditor.commit();
        }

        //Affichage des données persistantes
        if (!myPref.getString(SHARED_NOM,"").equals("")){
            savedNom.setText(myPref.getString(SHARED_NOM,""));
        }
        if (!myPref.getString(SHARED_PRENOM,"").equals("")){
            savedPrenom.setText(myPref.getString(SHARED_PRENOM,""));
        }
        if (!myPref.getString(SHARED_MAIL,"").equals("")){
            savedMail.setText(myPref.getString(SHARED_MAIL,""));
        }
        if (!myPref.getString(SHARED_PHONE,"").equals("")){
            savedPhone.setText(myPref.getString(SHARED_PHONE,""));
        }

        compteur.setText(String.valueOf(valeurCompteur));

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Mise à jour des données persistantes

                SharedPreferences.Editor myEditor = myPref.edit();

                myEditor.putString(SHARED_NOM,nom.getText().toString());
                myEditor.putString(SHARED_PRENOM,prenom.getText().toString());
                myEditor.putString(SHARED_MAIL,email.getText().toString());
                myEditor.putString(SHARED_PHONE,phone.getText().toString());
                myEditor.apply();

                Toast.makeText(MainActivity.this, "Sauvegarde "+myPref.getString(SHARED_NOM,"vide"), Toast.LENGTH_SHORT).show();
            }
        });
    }
}