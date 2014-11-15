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
		
		//新页面接收数据  第二总方法也可以
       // Bundle bundle = this.getIntent().getExtras();
        //接收name值
       // String name = bundle.getString("name");
		
         Log.i("获取到的name值为",name);
       
		 tv1 = (TextView)findViewById( R.id.edtRec );
		 tv1.setText("传过来的值为:"+name);
		 
		 btnBack=(Button)findViewById(R.id.btnBack);
		 btnBack.setOnClickListener(new OnClickListener()  
	        {  
	              
	            @Override  
	            public void onClick(View v)  
	            {  
	                Intent intent=new Intent();  
	                intent.putExtra("return", tv1.getText().toString()); //传递参数到return
	                setResult(RESULT_OK, intent);  
	                finish();  //关闭
	                  
	            }  
	        }); 
		 
		 //创建数据库
		 btnC=(Button)findViewById(R.id.btnC);
		 btnC.setOnClickListener(new OnClickListener()
		 {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// SQLiteDatabase mdatabase; //此方法无法创建
				// mdatabase=openOrCreateDatabase("mySqliteDatabase.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
				
				DatabaseHelper database_helper=new DatabaseHelper(ShowInfo.this,"mySqliteDatabase.db",1);
				SQLiteDatabase db=database_helper.getReadableDatabase();
				 Toast.makeText(getApplicationContext(), "数据库创建成功",Toast.LENGTH_SHORT).show();
				
			}
			 
		 });
		 //插入数据
		 btnI=(Button)findViewById(R.id.btnI);
		 btnI.setOnClickListener(new OnClickListener()
		 {

			@Override
			public void onClick(View v) {
				 
				ContentValues values = new ContentValues();
	            values.put("id", 1);//注意值的类型要匹配
	            values.put("name", "tornado");
	            DatabaseHelper database_helper = new DatabaseHelper(ShowInfo.this, "mySqliteDatabase.db");
	            SQLiteDatabase db = database_helper.getWritableDatabase();//这里是获得可写的数据库
	            db.insert("user1", null, values);
				
			}
			 
		 }); 
		 
		 //查询数据
		 btnQ=(Button)findViewById(R.id.btnQ);
		 btnQ.setOnClickListener(new OnClickListener()
		 {

			@Override
			public void onClick(View v) {
				DatabaseHelper database_helper = new DatabaseHelper(ShowInfo.this, "mySqliteDatabase.db");
	            SQLiteDatabase db = database_helper.getWritableDatabase();
	            //查询的语法，参数1为表名；参数2为表中的列名；参数3为要查询的列名；参数时为对应列的值；该函数返回的是一个游标
	            Cursor cursor = db.query("user1", new String[]{"id", "name"}, "id=?", new String[]{"1"},  null, null, null);
	            //遍历每一个记录
	            while(cursor.moveToNext()) {
	                String strname = cursor.getString(cursor.getColumnIndex("name"));//返回列名为name的值
	                tv1.setText("name:"+strname);
	                System.out.println("query---->" + strname);
	            }
	            
			}
			 
		 }); 
		 
		 //更新数据
		 btnU=(Button)findViewById(R.id.btnU);
		 edtName=(TextView)findViewById(R.id.edtName);
		 btnU.setOnClickListener(new OnClickListener()
		 {

			@Override
			public void onClick(View v) {
				String getName=edtName.getText().toString();
				if (getName==null){
					Toast.makeText(getApplicationContext(), "输入为空",Toast.LENGTH_SHORT).show();
					return;
				}
				DatabaseHelper database_helper = new DatabaseHelper(ShowInfo.this, "mySqliteDatabase.db");
	            SQLiteDatabase db = database_helper.getWritableDatabase();
	            ContentValues values = new ContentValues();
	            values.put("name", getName);
	           
	            //参数1为表名，参数2为更新后的值，参数3表示满足条件的列名称，参数4为该列名下的值
	            db.update("user1", values, "id=?", new String[]{"1"}); //id 为 1 的更新
	           
	            
			}
			 
		 }); 	
		 //删除数据
		 btnD=(Button)findViewById(R.id.btnD);
		 edtName=(TextView)findViewById(R.id.edtName); 
		 btnD.setOnClickListener(new OnClickListener()
		 {

			@Override
			public void onClick(View v) {
				 
				DatabaseHelper database_helper = new DatabaseHelper(ShowInfo.this, "mySqliteDatabase.db");
	            SQLiteDatabase db = database_helper.getWritableDatabase();
	            //直接删除名为tornado对应的那条记录
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
