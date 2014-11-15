package com.example.myfirstandroidapp;

import java.util.Date;

import android.support.v7.app.ActionBarActivity;
import android.text.format.Time;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent; 
import android.app.Activity;
import android.widget.RadioButton;
 




public class MainActivity extends ActionBarActivity {
	private Button bt,btnClear,btnShow;
	private TextView tw1;
	//private RadioGroup rg;
	//private RadioButton rb;
	private DatePicker dp;
	
	private static final String[] m_countries={"o��","a��","b��","ab��"};
	private Spinner sp1;
	private ArrayAdapter<String> adapter;
	private static final String DEBUG_TAG="MyFirstAndroidApp";
	 
    protected void onCreate(Bundle savedInstanceState, OnItemSelectedListener OnItemSelectedListener) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //forceError();
       Log.i(DEBUG_TAG,"Inof about MyFirstAndroidApp"); 
       
       bt=(Button)findViewById(R.id.btnBack);
       tw1=(TextView)findViewById(R.id.edtRec);
       bt.setOnClickListener(new OnClickListener() {   
           @Override 
           public void onClick(View v) { 
               // TODO Auto-generated method stub 
        	   
        	   Toast.makeText(getApplicationContext(), "button clicked",Toast.LENGTH_SHORT).show();
               Intent it= new Intent(); 
               //���ݲ���
              // it.putExtra("name", "����");
               it.putExtra("name", tw1.getText().toString());
               it.setClass(MainActivity.this, ShowInfo.class); 
              // startActivity(it); //�����շ���ֵ
               startActivityForResult(it, 1); //���շ���ֵ  RESULT_OK 
              // MainActivity.this.finish();  //��ֹActivity
                
           } 
       }); 
      
       //radioGroup ѡ���¼�
       
//       rg=(RadioGroup)findViewById(R.id.rg1);
//       rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
		
//		@Override
//		public void onCheckedChanged(RadioGroup group, int checkedId) {
//			// TODO Auto-generated method stub
//			if (checkedId!=-1){
//				RadioButton rb=(RadioButton)findViewById(checkedId);
//				if (rb!=null){
//					tw1.setText("you choose"+rb.getText());
//				} else{
//					tw1.setText("choose 1");
//				}
//			}
			
			
//		}
//	});
    //clear�¼�   
//    btnClear=(Button)findViewById(R.id.btnClear); //����һ��Button
//    btnClear.setOnClickListener(new OnClickListener() {  //����Button�����¼�

//		@Override
//		public void onClick(View v) {
			// TODO Auto-generated method stub
//			RadioGroup rgall=(RadioGroup)findViewById(R.id.rg1);
//			if (rgall!=null){
//				rgall.clearCheck(); //�������ѡ��
//			}
//		}
    	
  //  });
      
   
   
    //����ѡ��
/*    dp=(DatePicker)findViewById(R.id.dp1);
    dp.init(dp.getYear(), dp.getMonth(), dp.getDayOfMonth(), new DatePicker.OnDateChangedListener() {
		
		@Override
		public void onDateChanged(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			// TODO Auto-generated method stub
	 
			//Date dt=new Date(dp.getYear()-1900,dp.getMonth(),dp.getDayOfMonth(),1,1);
			//tw1.setText(dt.toString());
			tw1.setText("��ѡ��������ǣ�"+year+"��"+(monthOfYear+1)+"��"+dayOfMonth+"�ա�");

		}
	});
    */
 
   btnShow=(Button)findViewById(R.id.btnShow);
   btnShow.setOnClickListener(new OnClickListener(){

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent it=new Intent();
		it.setClass(MainActivity.this, ShowSqlite.class);
		startActivity(it);
	}
	   
   });
    
   //spinner
   
/*  sp1=(Spinner)findViewById(R.id.sp1); 
  adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,m_countries);
  adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
  sp1.setAdapter(adapter);
  sp1.setOnItemSelectedListener(new Spinner.OnItemSelectedListener()
  {

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		 tw1.setText("����Ѫ���ǣ�"+m_countries[position]);
		 parent.setVisibility(view.VISIBLE);
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}

 
  });*/
       
  }// end

 
    //ǿ����ֹ���򣬵�������
    public void forceError(){
    	if(true){
    		throw new Error("Whools");
    	}
    }


	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}
	
	 /** 
     *  ���մ��з���ֵ��Activity���� 
     */  
    @Override  
    protected void onActivityResult(int requestCode, int resultCode, Intent data)  
    {  
        
        super.onActivityResult(requestCode, resultCode, data); 
        Log.i("Main", "requestCode:"+requestCode+"resultCode:"+resultCode);  
         //���� requestCode=1,resultCode=-1
        if(requestCode == 1){  
        	tw1.setText(data.getStringExtra("return"));  
        }  
      //  tw1.setText(data.getStringExtra("return"));  
        
    }  
}
