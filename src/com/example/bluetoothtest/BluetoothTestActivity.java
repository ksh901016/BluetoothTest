package com.example.bluetoothtest;

import java.util.ArrayList;
import java.util.Set;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


public class BluetoothTestActivity extends Activity {
	
	private Button list;
	private BluetoothAdapter mBluetoothAdapter;
	private Set<BluetoothDevice>pairedDevices;
	private ListView lv;
	
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        list = (Button) findViewById(R.id.button4);
        lv = (ListView) findViewById(R.id.listView);
        mBluetoothAdapter= BluetoothAdapter.getDefaultAdapter();
        
    }
    
    public void on(View view){
    	if(!mBluetoothAdapter.isEnabled()){
    		Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
    		startActivityForResult(turnOn, 0);
    		Toast.makeText(getApplicationContext(),"블루투스가 켜집니다.", Toast.LENGTH_LONG).show();
    	}
    	else{
    		Toast.makeText(getApplicationContext(),"이미 켜져있습니다.",Toast.LENGTH_LONG).show();
    	}
    }
    
    public void list(View view){
    	pairedDevices = mBluetoothAdapter.getBondedDevices();
    	
    	ArrayList list = new ArrayList();
    	for(BluetoothDevice bt: pairedDevices)
    		list.add(bt.getName());
    	
    	Toast.makeText(getApplicationContext(), "페어링된 장치를 보여줍니다", Toast.LENGTH_LONG).show();
    	final ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, list);
    	lv.setAdapter(adapter);
    	
    }
    
    public void off(View view){
    	mBluetoothAdapter.disable();
    	Toast.makeText(getApplicationContext(), "블루투스가 꺼집니다.", Toast.LENGTH_LONG).show();
    }
    


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.bluetooth_test, menu);
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
