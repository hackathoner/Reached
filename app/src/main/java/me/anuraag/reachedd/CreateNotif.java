package me.anuraag.reachedd;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CreateNotif extends Activity {

    private EditText name,address,time,arrival,departure,numbers,emails;
    private Button submit, cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_notif);
        ActionBar mybar = this.getActionBar();
        mybar.setTitle("Create a new notification");
        name = (EditText)findViewById(R.id.editText);
        address = (EditText)findViewById(R.id.editText2);
        time = (EditText)findViewById(R.id.editText5);
        arrival = (EditText)findViewById(R.id.editText3);
        departure = (EditText)findViewById(R.id.editText4);
        numbers = (EditText)findViewById(R.id.editText6);
        emails = (EditText)findViewById(R.id.editText7);

        submit = (Button)findViewById(R.id.submit);
        cancel = (Button)findViewById(R.id.cancel);

        final Intent back = new Intent(this,MyActivity.class);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.getText().clear();
                address.getText().clear();
                time.getText().clear();
                arrival.getText().clear();
                departure.getText().clear();
                numbers.getText().clear();
                emails.getText().clear();

                startActivity(back);

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!name.getText().toString().isEmpty() && !address.getText().toString().isEmpty() && !time.getText().toString().isEmpty() && !departure.getText().toString().isEmpty() && !arrival.getText().toString().isEmpty() && !numbers.getText().toString().isEmpty() && !emails.getText().toString().isEmpty()) {
                    createNewContactField();
                }
                else{
                    incorrectInput();
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.create_notif, menu);
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
    public void createNewContactField()
    {
        SharedPreferences sharedPref = getSharedPreferences("notifs.xml",MODE_PRIVATE);
        String old = sharedPref.getString("Notifs","");
        Log.i("Old Array", old);
        JSONObject notif = new JSONObject();

        String s = emails.getText().toString();
        int numEmailCom = 0;
        for( int i=0; i<s.length(); i++ ) {
            if( s.charAt(i) == ',' ) {
                numEmailCom++;
            }
        }
        Log.i("Email of Commas", numEmailCom + "");

        String f = numbers.getText().toString();
        int numPhoneCom = 0;
        for( int i=0; i<f.length(); i++ ) {
            if( f.charAt(i) == ',' ) {
                numPhoneCom++;
            }
        }
        Log.i("Number of Commas", numPhoneCom + "");


        try{
            JSONArray myold = new JSONArray(old);
            Log.i("Issue","worked");
            notif.put("Name",name.getText().toString());
            notif.put("Address",address.getText().toString());
            notif.put("Time",time.getText().toString());
            notif.put("Arrival",arrival.getText().toString());
            notif.put("Departure",departure.getText().toString());

            String emailss = emails.getText().toString().trim();
            emailss = emailss.replace(" ", "");
            String myn = numbers.getText().toString().trim();
            myn = myn.replace(" ", "");

            List<String> emailed = new ArrayList<String>(Arrays.asList(emailss.split(",")));
            List<String> numbed = new ArrayList<String>(Arrays.asList(myn.split(",")));

            JSONArray nums = new JSONArray(numbed);
            JSONArray ems = new JSONArray(emailed);

            notif.put("Emails",ems);
            notif.put("Numbers",nums);
            if(!old.equals("")) {
                myold.put(notif);
                String foo = myold.toString();
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("Notifs", foo);
                editor.apply();
            }else{
                JSONArray newAr = new JSONArray(notif.toString());
                String foo = notif.toString();
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("Notifs", newAr.toString());
                editor.apply();
            }
            Log.i("Notif",notif.toString());
        }catch(JSONException j) {
            Log.i("Issue","issue");
        }



    }
    private void incorrectInput()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Please make sure you properly fill out all the fields")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //do things
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }


}
