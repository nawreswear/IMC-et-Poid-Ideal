package com.imcetpoidideal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private Button btn_ajt,btn_eff,btn_vid;
    private EditText edpod,edtaille,edage;
    private ListView list;
    protected ArrayAdapter<Personne> adaptpersonn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private void init(){
        btn_ajt=findViewById(R.id.btn_ajt);
        btn_eff=findViewById(R.id.btn_eff);
        btn_vid=findViewById(R.id.btn_vid);
        edage=findViewById(R.id.edage);
        edpod=findViewById(R.id.edpoids);
        edtaille=findViewById(R.id.edtaille);
        list=findViewById(R.id.list);
        adaptpersonn=new ArrayAdapter<Personne>(this, android.R.layout.simple_list_item_1);
       //liee listvew avec array adpter
        list.setAdapter(adaptpersonn);
        ecouteurs();
    }

    private void ecouteurs() {
        btn_ajt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ajouter();
            }
        });
        btn_vid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vider();
            }
        });
        btn_eff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Effacer();
            }
        });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int positon, long id) {
            afficher(positon);
            }
        });
    }

    private void afficher(int positon) {
        AlertDialog.Builder b =new AlertDialog.Builder(this);
        b.setTitle("Homme/Femme");
        b.setMessage("Vous etre ?");
        b.setPositiveButton("Homme", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        afficherMessage(positon,true);
                    }
                });
                b.setNegativeButton("Femme", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        afficherMessage(positon, false);
                    }
                });
                AlertDialog alertDialog=b.create();
                alertDialog.show();
    }
    protected void afficherMessage(int position,boolean estHomme){
        Personne p=adaptpersonn.getItem(position);
        DecimalFormat df=new DecimalFormat("0.00");
        String message="IMC"+df.format(p.imc())+"\n";
        message="Interpretaion";
        if(p.imc()<18.5)
        {  message+="Megrateur\n";}
        else if (p.imc()<30)
        { message+="Corpulence normale\n";}
        else{ message+="Surpods\n";}
         String type;
        if(estHomme)
        { type="H";}
        else
        {type="F";}
        message+="Pod Ideal("+type+"):"+p.getPoidIdeal(estHomme);
        Toast t=Toast.makeText(this,message,Toast.LENGTH_LONG);
        t.show();
    }
    private void Effacer() {
        edtaille.setText("");
        edpod.setText("");
        edage.setText("");
    }

    private void Vider() {
        adaptpersonn.clear();
    }

    private void ajouter() {
        if(!edage.getText().toString().isEmpty()&&
        !edpod.getText().toString().isEmpty()&&
        !edtaille.getText().toString().isEmpty()){
            int age= Integer.parseInt(edage.getText().toString());
        if(age>18) {
            float taille = Float.parseFloat(edtaille.getText().toString());
            float poid = Float.parseFloat(edpod.getText().toString());
            Personne p = new Personne(taille, poid, age);
            adaptpersonn.add(p);
            Effacer();
        }else{
            Toast t=Toast.makeText(this,"ce calcul n'est interpréatable que pour adulte",Toast.LENGTH_LONG);
            t.show();
        }}else {
                Toast t=Toast.makeText(this,"verifier les valeurs SVP! ",Toast.LENGTH_LONG);
                t.show();
            }
    }
}