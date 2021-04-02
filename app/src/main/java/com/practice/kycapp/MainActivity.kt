package com.practice.kycapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import java.util.*
import kotlin.system.exitProcess


class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		supportActionBar?.hide();
		setContentView(R.layout.activity_main)
		
		val noPan=findViewById<TextView>(R.id.tvNoPan);
		val dateDay=findViewById<EditText>(R.id.etDay);
		val dateMth=findViewById<EditText>(R.id.etMonth);
		val dateYr=findViewById<EditText>(R.id.etYear);
		val panField=findViewById<EditText>(R.id.etPan);
		noPan.setOnClickListener {
			
			moveTaskToBack(true);
			exitProcess(-1);
		}
		val subBtn=findViewById<Button>(R.id.btnSub);
		subBtn.setOnClickListener(){
			Toast.makeText(applicationContext, "Details submitted successfully", Toast.LENGTH_SHORT).show()
			
			Thread.sleep(1000);
			moveTaskToBack(true);
			exitProcess(-1);
		}
		subBtn.isEnabled=false;
		subBtn.isClickable=false;

//Pan Validating Pan======================================================================================
		
		val panReg=Regex(pattern="[A-Z]{5}[0-9]{4}[A-Z]{1}");
		panField.doAfterTextChanged {
			panField.error=if(panField.text.toString().matches(panReg))null else "Enter valid PAN"
			val num=if(panField.error!=null)R.drawable.et_error else R.drawable.et_touched_pan;
			panField.setBackgroundResource(num);
			if(panField.error!=null){
				subBtn.isEnabled=false;
				subBtn.isClickable=false;
			}
			else if(panField.text.toString().equals("")||dateDay.text.toString().equals("")||dateMth.text.toString().equals("")||dateYr.text.toString().equals("")){
				subBtn.isEnabled=false;
				subBtn.isClickable=false;
			}
			else{
				subBtn.isEnabled=true;
				subBtn.isClickable=true;
			}
			
		}
//Date Validating date=====================================================================================
		
		var days: IntArray= intArrayOf(31,28,31,30,31,30,31,31,30,31,30,31)
		
		var mth=1;
		var day=1;
		var yr=1;
		
		dateDay.doAfterTextChanged {
			day=if(dateDay.text.toString().equals(""))1 else Integer.parseInt(dateDay.text.toString());
			dateDay.error = if (mth in 12 downTo 1 && day in days[mth-1] downTo 1) null else "Enter Valid date"
			val num=if(dateDay.error!=null)R.drawable.et_error else R.drawable.et_touched_date;
			dateDay.setBackgroundResource(num);
			if(num==R.drawable.et_error||dateDay.text.toString().equals("")){
				subBtn.isEnabled=false;
				subBtn.isClickable=false;
			}
			else if(panField.text.toString().equals("")||dateDay.text.toString().equals("")||dateMth.text.toString().equals("")||dateYr.text.toString().equals("")){
				subBtn.isEnabled=false;
				subBtn.isClickable=false;
			}
			else{
				subBtn.isEnabled=true;
				subBtn.isClickable=true;
			}
		}
		dateMth.doAfterTextChanged {
			mth=if(dateMth.text.toString().equals(""))1 else Integer.parseInt(dateMth.text.toString());
			dateMth.error = if (mth in 12 downTo 1) null else "Enter Valid date"
			dateDay.error = if (mth in 12 downTo 1 && day in days[mth-1] downTo 1) null else "Enter Valid date"
			val num=if(dateMth.error!=null ||dateDay.error!=null )R.drawable.et_error else R.drawable.et_touched_date;
			dateDay.setBackgroundResource(num);
			dateMth.setBackgroundResource(num);
			if(num==R.drawable.et_error||dateMth.text.toString().equals("")){
				subBtn.isEnabled=false;
				subBtn.isClickable=false;
			}
			else if(panField.text.toString().equals("")||dateDay.text.toString().equals("")||dateMth.text.toString().equals("")||dateYr.text.toString().equals("")){
				subBtn.isEnabled=false;
				subBtn.isClickable=false;
			}
			else{
				subBtn.isEnabled=true;
				subBtn.isClickable=true;
			}
		}
		dateYr.doAfterTextChanged {
			yr =if(dateYr.text.toString().equals(""))1800 else Integer.parseInt(dateYr.text.toString());
			dateYr.error=if (yr in Calendar.getInstance().get(Calendar.YEAR) downTo 1800 && day in days[mth-1] downTo 1) null else "Enter Valid date"
			days[1]=if (yr%4==0)(if(yr%100==0)(if (yr%400==0)29 else 28)else 28) else 29;
			dateDay.error = if (mth in 12 downTo 1 && day in days[mth-1] downTo 1) null else "Enter Valid date"
			val num=if(dateDay.error!=null||dateMth.error!=null ||dateYr.error!=null )R.drawable.et_error else R.drawable.et_touched_date;
			dateDay.setBackgroundResource(num);
			dateMth.setBackgroundResource(num);
			dateYr.setBackgroundResource(num);
			if(num==R.drawable.et_error||dateYr.text.toString().equals("")){
				subBtn.isEnabled=false;
				subBtn.isClickable=false;
			}
			else if(panField.text.toString().equals("")||dateDay.text.toString().equals("")||dateMth.text.toString().equals("")||dateYr.text.toString().equals("")){
				subBtn.isEnabled=false;
				subBtn.isClickable=false;
			}
			else{
				subBtn.isEnabled=true;
				subBtn.isClickable=true;
			}
		}
	}
	
}