package com.codecleanms.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class CalcActivity extends Activity {

    protected EditText txtResultado;
    protected Queue<String> collection;
    protected float result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);

        this.txtResultado = (EditText) findViewById(R.id.txt_resultado);
        collection = new LinkedList<>();
        this.result = 0;

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
        String resultado = this.txtResultado.getText().toString();

        if(findViewById(R.id.btn_one) == view){
            this.txtResultado.setText(String.format("%s1", resultado));
        }
        else if(findViewById(R.id.btn_two) == view){
            this.txtResultado.setText(String.format("%s2", resultado));
        }
        else if(findViewById(R.id.btn_three) == view){
            this.txtResultado.setText(String.format("%s3", resultado));
        }
        else if(findViewById(R.id.btn_four) == view){
            this.txtResultado.setText(String.format("%s4", resultado));
        }
        else if(findViewById(R.id.btn_five) == view){
            this.txtResultado.setText(String.format("%s5", resultado));
        }
        else if(findViewById(R.id.btn_six) == view){
            this.txtResultado.setText(String.format("%s6", resultado));
        }
        else if(findViewById(R.id.btn_seven) == view){
            this.txtResultado.setText(String.format("%s7", resultado));
        }
        else if(findViewById(R.id.btn_eigth) == view){
            this.txtResultado.setText(String.format("%s8", resultado));
        }
        else if(findViewById(R.id.btn_nine) == view){
            this.txtResultado.setText(String.format("%s9", resultado));
        }
        else if(findViewById(R.id.btn_zero) == view){
            this.txtResultado.setText(String.format("%s0", resultado));
        }
        else if(findViewById(R.id.btn_plus) == view){
            collection.add(resultado);
            collection.add("+");
            this.txtResultado.setText("");
        }
        else if(findViewById(R.id.btn_minus) == view){
            collection.add(resultado);
            collection.add("-");
            this.txtResultado.setText("");
        }
        else if(findViewById(R.id.btn_times) == view){
            collection.add(resultado);
            collection.add("*");
            this.txtResultado.setText("");
        }
        else if(findViewById(R.id.btn_divide) == view){
            collection.add(resultado);
            collection.add("/");
            this.txtResultado.setText("");
        }
    }

    public void clearScreen(View view) {
        this.txtResultado.setText("");
    }

    public void calculationResult(View view){
        Iterator<String> iterator= collection.iterator();
        String resultado = this.txtResultado.getText().toString();
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
        DecimalFormat df = new DecimalFormat("0.00");
        this.txtResultado.setText(df.format(this.result));
    }
}
