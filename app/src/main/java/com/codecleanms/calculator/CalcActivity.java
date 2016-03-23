package com.codecleanms.calculator;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class CalcActivity extends Activity {

    protected EditText txtResultado;
    protected Queue<String> collection;
    protected float result;
    protected Context context;
    protected TextView txtHistorico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);

        this.txtResultado = (EditText) findViewById(R.id.txt_resultado);
        this.txtHistorico = (TextView) findViewById(R.id.txt_historico);
        this.collection = new LinkedList<>();
        this.result = 0;

        context = getApplicationContext();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calc, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void calcula(View view) {
        Button btnOperacao = (Button) view;
        String resultado = this.txtResultado.getText().toString();
        String operador = btnOperacao.getText().toString();
        String historico = this.txtHistorico.getText().toString();

        this.txtResultado.setText(String.format("%s" + operador, resultado));
        this.txtHistorico.setText(String.format("%s" + operador, historico));

        if("+".equals(operador) || "-".equals(operador) || "*".equals(operador) || "/".equals(operador)){
            collection.add(resultado);
            collection.add(operador);
            this.txtResultado.setText("");
            this.txtHistorico.setText(String.format("%s" + operador, historico));
        }
    }

    public void clearScreen(View view) {
        this.txtResultado.setText("");
        this.txtHistorico.setText("");
        this.collection.clear();
    }

    public void calculationResult(View view){

        String resultado = this.txtResultado.getText().toString();
        if("".equals(resultado)){
            Toast toast = Toast.makeText(context, "Digite um número!", Toast.LENGTH_SHORT);
            toast.show();
        }else{
            Iterator<String> iterator= collection.iterator();
            collection.add(resultado);

            while (iterator.hasNext()){
                String element = collection.remove();
                switch (element){
                    case "+":
                        this.result = this.result + Float.parseFloat(collection.remove());
                        break;
                    case "-":
                        this.result = this.result - Float.parseFloat(collection.remove());
                        break;
                    case "/":
                        this.result = this.result / Float.parseFloat(collection.remove());
                        break;
                    case "*":
                        this.result = this.result * Float.parseFloat(collection.remove());
                        break;
                    default:
                        this.result = Float.parseFloat(element);
                        break;
                }
            }
            this.txtResultado.setText(String.valueOf(this.result));
        }
    }
}
