package com.observerapp.mobile.securesancharapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.observerapp.mobile.securesancharapp.allTime.API_URL;

public class welcome extends AppCompatActivity {

    EditText e1,e2,e3,e4,e5;
    Button b1,b2;
    ProgressBar pB1;
    String OTP,UID,EMAIL,NAME;
    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        e1 = (EditText)findViewById(R.id.e1);
        e2 = (EditText)findViewById(R.id.e2);

        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);

        pB1= (ProgressBar) findViewById(R.id.pB1);
        tv1= (TextView) findViewById(R.id.tv1);

        e2.setVisibility(View.GONE);
        pB1.setVisibility(View.GONE);
        b2.setVisibility(View.GONE);
        tv1.setVisibility(View.GONE);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendOTP();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyOTP();
            }
        });

        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });
    }

    public void sendOTP(){
        pB1.setVisibility(View.VISIBLE);

        //next();

        final String s1 = e1.getText().toString();

        String param = s1;
        b1.setEnabled(false);

        try{

            // Instantiate the RequestQueue.
            RequestQueue queue = Volley.newRequestQueue(this);
            //final String url = API_URL+"/register/"+param;
            final String url = API_URL+"/hello.txt";

            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.
//                            res.setText("Response is: "+ response);
                            pB1.setVisibility(View.GONE);
                            Toast.makeText(welcome.this, response, Toast.LENGTH_LONG).show();
                            try {
                                JSONObject res = new JSONObject(response);

                                OTP = res.getString("otp");
                                UID = res.getString("userid");
                                EMAIL=res.getString("email");
                                NAME=res.getString("name");


                                if(true){
                                    next(); // NOT WORKING WHY ?????

                                    String userIdVal = res.get("userId").toString();
                                    //Toast.makeText(welcome.this, "Welcome "+res.get("username")+"("+userIdVal+")", Toast.LENGTH_SHORT).show();
                                    Toast.makeText(welcome.this, response, Toast.LENGTH_LONG).show();
/*
                                    putSharedPref(username,s1);
                                    putSharedPref(userId,userIdVal);
*/
                                }else{
                                    Toast.makeText(welcome.this, "Error in sending request", Toast.LENGTH_SHORT).show();
                                    b1.setEnabled(true);
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
//                    res.setText("That didn't work!");
                    pB1.setVisibility(View.GONE);
                    b1.setEnabled(true);
                    Toast.makeText(welcome.this, error.toString()+" \n Some problem occured, Try Later...!!!", Toast.LENGTH_LONG).show();
                    b1.setEnabled(true);
                }
            }){
                @Override
                protected Map<String, String> getParams()
                {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("email",s1);
                    //params.put("domain", "http://itsalif.info");

                    return params;
                }
            };

            // Add the request to the RequestQueue.
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            queue.add(stringRequest);


        }catch (Exception e){
            Toast.makeText(this, "BAAL BAAL BACHA", Toast.LENGTH_SHORT).show();
        }

    }

    public void verifyOTP(){
        pB1.setVisibility(View.VISIBLE);

        String s2 = e2.getText().toString();

        if(s2.equals(OTP)){
            Toast.makeText(this, "Your are successfully logged in...!!!", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(welcome.this,registerProfile.class);
            startActivity(i);
            finish();
        }else{
            Toast.makeText(this, "Invalid OTP try again...!!!", Toast.LENGTH_SHORT).show();
        }

        pB1.setVisibility(View.GONE);
    }

    public void back(){
        b1.setEnabled(true);
        e1.setVisibility(View.VISIBLE);
        e2.setVisibility(View.GONE);
        b1.setVisibility(View.VISIBLE);
        b2.setVisibility(View.GONE);
        tv1.setVisibility(View.GONE);
    }

    public void next(){
        Toast.makeText(this, "INSIDE NEXT", Toast.LENGTH_SHORT).show();
        e1.setVisibility(View.GONE);
        e2.setVisibility(View.VISIBLE);
        b1.setVisibility(View.GONE);
        b2.setVisibility(View.VISIBLE);
        tv1.setVisibility(View.VISIBLE);
    }
}
