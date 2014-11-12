package me.anuraag.reachedd;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;


public class MyActivity extends Activity {


    private EditText create;
    private Intent createActivity;
    private ListView items;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        ActionBar mybar = this.getActionBar();
        mybar.setTitle("Notifications");
        create = (EditText)findViewById(R.id.editText);
        createActivity = new Intent(this,CreateNotif.class);

        SharedPreferences sharedPref = this.getSharedPreferences("loc.xml",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.apply();


        String[] myStringArray = new String[12];
        myStringArray[0] = "cool";
        myStringArray[1] = "cool1";
        myStringArray[2] = "cool2";
        myStringArray[3] = "cool3";
        myStringArray[4] = "cool4";
        myStringArray[5] = "cool5";
        myStringArray[6] = "cool";
        myStringArray[7] = "cool1";
        myStringArray[8] = "cool2";
        myStringArray[9] = "cool3";
        myStringArray[10] = "cool4";
        myStringArray[11] = "cool5";
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, myStringArray);
        items = (ListView)findViewById(R.id.listview);
        items.setAdapter(adapter);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(createActivity);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
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
