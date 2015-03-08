/*

Author      : Piyoros Vephula
Date        : 15/2/2015
Course      : CS211D
Homework    : 3
File Name   : TempConvertActivity.java
Description : This application converts Fahrenheit to Celsius or Celsius to Fahrenheit by clicking radio button to select the unit then type the number on degrees and enter.

*/

package com.ccsf.pvephula.tempconverter;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;


public class TempConvertActivity extends Activity
{

    EditText et;
    TextView tv;
    int converter = 1;

//*****************************convertTemp()******************************
    public void convertTemp(int c, String temp)
    {
        Log.d("temp",temp);
        Log.d("c",""+c);
        int theTemp = Integer.parseInt(temp);
        Log.d("theTemp",""+theTemp);
        int result;
        switch(c)
        {
            case  1:
                result = (theTemp - 32) * 5/9;
                tv.setText(""+result+"°");
                break;
            case 2:
                result = (theTemp * 9/5) + 32;
                tv.setText(""+result+"°");
                break;
        }

    }

//*******************************onCreate()*******************************
    @Override
    protected void onCreate(Bundle b)
    {
        super.onCreate(b);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_temp_convert);
        et = (EditText)findViewById(R.id.temp_input);
        tv = (TextView)findViewById(R.id.temp_result);
        et.setOnKeyListener(new View.OnKeyListener()
        {
            public boolean onKey(View v,int keyCode,KeyEvent ke)
            {
                if((ke.getAction() == KeyEvent.ACTION_DOWN)&&(keyCode == KeyEvent.KEYCODE_ENTER))
                {
                    String theTemp = et.getText().toString();
                    Log.d("Temp",theTemp);
                    convertTemp(converter,theTemp);
                    return (true);
                }
                return (false);
            }
        });
    }

//*************************onRadioButtonClicked()*************************
    public void onRadioButtonClicked(View v)
    {
        boolean checked = ((RadioButton)v).isChecked();
        switch(v.getId())
        {
            case R.id.f_to_c:
            if(checked)
            {
                converter = 1;
            }
            break;
            case R.id.c_to_f:
            if(checked)
            {
                converter = 2;
            }
            break;
        }
    }

//*************************onCreateOptionsMenu()**************************
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_temp_convert, menu);
        return true;
    }

//************************onOptionsItemSelected()*************************
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
