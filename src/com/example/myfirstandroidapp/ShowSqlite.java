package com.example.myfirstandroidapp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.widget.ArrayAdapter;
import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.util.Log;
 

import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
 


public class ShowSqlite extends ActionBarActivity {
    private Button btnSBack,btnSearch;
    private EditText edtName,edtId;
    private ListView listview;
    private ArrayAdapter<String> aa ;
    String id[];
	String name[]; 
	int i = 0;
    SQLiteDatabase database; 
    List<Map<String, Object>> slist = new ArrayList<Map<String, Object>>();
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_sqlite);
		setTitle("查询明细");
		database = openDatabase(); 
		 
		//返回按钮
		btnSBack=(Button)findViewById(R.id.btnSBack);
		btnSBack.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();//关闭
			}
			
		});
		
		
		//查询按钮
		btnSearch=(Button)findViewById(R.id.btnSearch);
		edtName=(EditText)findViewById(R.id.edtName);
		edtId=(EditText)findViewById(R.id.edtId);
		listview = (ListView) findViewById(R.id.lvShow);
		btnSearch.setOnClickListener(new OnClickListener(){
        
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
		 
				//带参数的写法
			//	 String sql = "select * from username where name=?";      
			//        Cursor cursor = database.rawQuery(sql, new String[]   
			//        { edtName.getText().toString() });       
		    
		      Cursor cursor=database.rawQuery("SELECT * FROM username ", null);  
		     //   Cursor cursor = database.query("username", new String[]{"id", "name"}, "id=?", new String[]{"1"},  null, null, null);
		        String result = "未找到该单词."; 
		       // String id;
		        cursor.moveToFirst();

				int count = cursor.getCount();
				id = new String[count];
				name = new String[count];
				do {
					try {
						id[i] = cursor.getString(0);
						name[i] = cursor.getString(1);	
						i++;
					} catch (Exception e) {
						// TODO: handle exception
					}

				} while (cursor.moveToNext());
				
				//添加标题
				Map<String, Object> title = new HashMap<String, Object>();
                title.put("id","编号");
                title.put("name","姓名");
              
                slist.add(title);
				
				for (int i = 0; i < id.length; i++) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("id", id[i]);
					map.put("name", name[i]);
					slist.add(map);
				}
				edtName.setText(slist.get(1).toString());
				edtId.setText(slist.get(0).toString());
				//添加监视器
	            SimpleAdapter sa=new SimpleAdapter(ShowSqlite.this,slist,R.layout.listviewshow,new String[]{"id","name"},new int[]{R.id.t1,R.id.t2});
				listview.setOnItemClickListener(new OnItemClickListener(){

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						// TODO Auto-generated method stub
					
				/*		Cursor cursor = (Cursor) ((ListView) parent).getItemAtPosition(position);
						if (cursor != null && cursor.moveToPosition(position)) {
							Toast.makeText(ShowSqlite.this,cursor.getString(1),200).show();
						}  */
						
						TextView Name = (TextView) view.findViewById(R.id.t2); //获取item 的值
						Toast.makeText(ShowSqlite.this,Name.getText().toString(),200).show();
					}
		        	
		        });
		        listview.setAdapter(sa); 
	     
			}

		
			
		});
		
		
	}

 

	private SQLiteDatabase openDatabase() {   
        try {   
          
        	File dir=new File(getFilesDir(),"showinfo.db"); 
            Toast.makeText(getApplicationContext(), dir.toString(),Toast.LENGTH_SHORT).show();  
            //如果不存在就复制到相应的目录
            if (!(dir.exists())) {   
                // 获得封装dictionary.db文件的InputStream对象   
                InputStream is = getAssets().open("showinfo.db");
                FileOutputStream fos = new FileOutputStream(dir);   
                byte[] buffer = new byte[1024];   
                int count = 0;   
                // 开始复制showinfo.db文件   
                while ((count = is.read(buffer)) > 0) {   
                    fos.write(buffer, 0, count);   
                }  
  
                fos.close();   
                is.close();   
            }   
            // 打开数据库
            SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(   
                    dir, null);   
            return database;   
        } catch (Exception e) {   
        }   
        return null;   
    }   
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show_sqlite, menu);
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
