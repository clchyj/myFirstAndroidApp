package com.example.myfirstandroidapp;

import android.R.string;
import android.support.v7.app.ActionBarActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;
import android.view.View;  
import android.view.View.OnClickListener;  
import android.widget.Button;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ShowInfo extends ActionBarActivity {
	
	private TextView tv1,edtName; 
	private Button btnBack,btnC,btnQ,btnD,btnI,btnU;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_info);
		
		Intent intent=this.getIntent();  
		String name=intent.getStringExtra("name");
		
		//��ҳ���������  �ڶ��ܷ���Ҳ����
       // Bundle bundle = this.getIntent().getExtras();
        //����nameֵ
       // String name = bundle.getString("name");
		
         Log.i("��ȡ����nameֵΪ",name);
       
		 tv1 = (TextView)findViewById( R.id.edtRec );
		 tv1.setText("��������ֵΪ:"+name);
		 
		 btnBack=(Button)findViewById(R.id.btnBack);
		 btnBack.setOnClickListener(new OnClickListener()  
	        {  
	              
	            @Override  
	            public void onClick(View v)  
	            {  
	                Intent intent=new Intent();  
	                intent.putExtra("return", tv1.getText().toString()); //���ݲ�����return
	                setResult(RESULT_OK, intent);  
	                finish();  //�ر�
	                  
	            }  
	        }); 
		 
		 //�������ݿ�
		 btnC=(Button)findViewById(R.id.btnC);
		 btnC.setOnClickListener(new OnClickListener()
		 {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// SQLiteDatabase mdatabase; //�˷����޷�����
				// mdatabase=openOrCreateDatabase("mySqliteDatabase.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
				
				DatabaseHelper database_helper=new DatabaseHelper(ShowInfo.this,"mySqliteDatabase.db",1);
				SQLiteDatabase db=database_helper.getReadableDatabase();
				 Toast.makeText(getApplicationContext(), "���ݿⴴ���ɹ�",Toast.LENGTH_SHORT).show();
				
			}
			 
		 });
		 //��������
		 btnI=(Button)findViewById(R.id.btnI);
		 btnI.setOnClickListener(new OnClickListener()
		 {

			@Override
			public void onClick(View v) {
				 
				ContentValues values = new ContentValues();
	            values.put("id", 1);//ע��ֵ������Ҫƥ��
	            values.put("name", "tornado");
	            DatabaseHelper database_helper = new DatabaseHelper(ShowInfo.this, "mySqliteDatabase.db");
	            SQLiteDatabase db = database_helper.getWritableDatabase();//�����ǻ�ÿ�д�����ݿ�
	            db.insert("user1", null, values);
				
			}
			 
		 }); 
		 
		 //��ѯ����
		 btnQ=(Button)findViewById(R.id.btnQ);
		 btnQ.setOnClickListener(new OnClickListener()
		 {

			@Override
			public void onClick(View v) {
				DatabaseHelper database_helper = new DatabaseHelper(ShowInfo.this, "mySqliteDatabase.db");
	            SQLiteDatabase db = database_helper.getWritableDatabase();
	            //��ѯ���﷨������1Ϊ����������2Ϊ���е�����������3ΪҪ��ѯ������������ʱΪ��Ӧ�е�ֵ���ú������ص���һ���α�
	            Cursor cursor = db.query("user1", new String[]{"id", "name"}, "id=?", new String[]{"1"},  null, null, null);
	            //����ÿһ����¼
	            while(cursor.moveToNext()) {
	                String strname = cursor.getString(cursor.getColumnIndex("name"));//��������Ϊname��ֵ
	                tv1.setText("name:"+strname);
	                System.out.println("query---->" + strname);
	            }
	            
			}
			 
		 }); 
		 
		 //��������
		 btnU=(Button)findViewById(R.id.btnU);
		 edtName=(TextView)findViewById(R.id.edtName);
		 btnU.setOnClickListener(new OnClickListener()
		 {

			@Override
			public void onClick(View v) {
				String getName=edtName.getText().toString();
				if (getName==null){
					Toast.makeText(getApplicationContext(), "����Ϊ��",Toast.LENGTH_SHORT).show();
					return;
				}
				DatabaseHelper database_helper = new DatabaseHelper(ShowInfo.this, "mySqliteDatabase.db");
	            SQLiteDatabase db = database_helper.getWritableDatabase();
	            ContentValues values = new ContentValues();
	            values.put("name", getName);
	           
	            //����1Ϊ����������2Ϊ���º��ֵ������3��ʾ���������������ƣ�����4Ϊ�������µ�ֵ
	            db.update("user1", values, "id=?", new String[]{"1"}); //id Ϊ 1 �ĸ���
	           
	            
			}
			 
		 }); 	
		 //ɾ������
		 btnD=(Button)findViewById(R.id.btnD);
		 edtName=(TextView)findViewById(R.id.edtName); 
		 btnD.setOnClickListener(new OnClickListener()
		 {

			@Override
			public void onClick(View v) {
				 
				DatabaseHelper database_helper = new DatabaseHelper(ShowInfo.this, "mySqliteDatabase.db");
	            SQLiteDatabase db = database_helper.getWritableDatabase();
	            //ֱ��ɾ����Ϊtornado��Ӧ��������¼
	            db.delete("user1", "name=?" ,new String[]{edtName.getText().toString()});
	           
	            
			}
			 
		 });  
		 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show_info, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
