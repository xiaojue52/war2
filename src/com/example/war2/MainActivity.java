package com.example.war2;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity {
	private EditText level;
	private EditText property;
	private Spinner star;
	private Button calculate;
	private EditText result;
	private Button clear;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		level = (EditText)this.findViewById(R.id.editText1);
		property = (EditText)this.findViewById(R.id.editText2);
		star = (Spinner)this.findViewById(R.id.spinner1);
		ArrayAdapter< String> adapter =new ArrayAdapter< String>( this,android.R.layout.simple_spinner_item);
		adapter.add("选择星星");
		adapter.add("2星");
		adapter.add("3星");
		adapter.add("4星");
		adapter.add("5星");
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		star.setAdapter(adapter);

		calculate = (Button)this.findViewById(R.id.button1);
		result = (EditText)this.findViewById(R.id.editText3);
		clear = (Button)this.findViewById(R.id.button2);
		calculate.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				float temp = calculater();
				if (temp==-1){
					return;
				}
				result.setText(String.valueOf(temp));
			}
			
		});
		clear.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				reset();
			}
			
		});
	}

	private float calculater(){
		float temp = 0;
		result.setText("");
		if ("".equals(level.getText().toString())){
			Toast.makeText(this, "请输入等级！", Toast.LENGTH_LONG).show();
			return -1;
		}
		if ("".equals(this.property.getText().toString())){
			Toast.makeText(this, "请输入主属性！", Toast.LENGTH_LONG).show();
			return -1;
		}
		if (this.star.getSelectedItemPosition()==0){
			Toast.makeText(this, "请选择星星！", Toast.LENGTH_LONG).show();
			return -1;
		}

		int lev = Integer.valueOf(level.getText().toString());
		int pro = Integer.valueOf(this.property.getText().toString());
		int sta = Integer.valueOf(this.star.getSelectedItemPosition());

		//Toast.makeText(this, ""+lev+"..."+pro+"..."+sta, Toast.LENGTH_LONG).show();
		temp = (float)(149+pro-lev/2.0);
		switch(sta){
		case 1:
			temp = (float) (temp - 3.5);
			break;
		case 2:
			temp = (float) (temp - 8.5);
			break;
		case 3:
			temp = (float) (temp - 15.5);
			break;
		case 4:
			temp = (float) (temp -24.5);
			break;
		default:
			break;
		
		}
		return temp;
	}
	
	private void reset(){
		level.setText("");
		property.setText("");
		star.setSelection(0);
		result.setText("");
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
