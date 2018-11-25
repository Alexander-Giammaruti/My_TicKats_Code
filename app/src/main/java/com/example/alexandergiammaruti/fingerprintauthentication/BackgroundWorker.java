package com.example.alexandergiammaruti.fingerprintauthentication;


import android.accounts.Account;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Output;
import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.Buffer;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;

public class BackgroundWorker extends AsyncTask<String,Void,String> {

    AlertDialog alertDialog;
    private TextView textView;
    Context context;


    BackgroundWorker (Context ctx) {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {

        String type = params[0];
        String login_url = "https://tickats.live/login.php";
        String update_location_url = "https://tickats.live/updatelocation.php";


        if(type.equals("login")) {
            try {

                String user_name = params[1];
                String password = params[2];

                URL url = new URL(login_url);
                HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
                urlConnection.setDoOutput(true);
                urlConnection.setDoInput(true);

                OutputStream outputStream = urlConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = urlConnection.getInputStream();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                JSONObject responseJSON = new JSONObject(result);
                String loginMessage =responseJSON.getString("loginMessage");
                //Fid= responseJSON.getString("FWid");
                ((Globals) context.getApplicationContext()).setFieldWorkerID(responseJSON.getString("FWid"));

                /*
                FirstName=responseJSON.getString("First");
                LastName=responseJSON.getString("Last");
                HoursWorked=responseJSON.getString("Hours");
                //ProfilePic=responseJSON.getString("Photo");

                tab1.fid=Fid;
                tab1.fname=FirstName;
                tab1.lname=LastName;
                tab1.hrs=HoursWorked;
                */

                bufferedReader.close();
                inputStream.close();
                urlConnection.disconnect();

                return loginMessage;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }



        }else if (type.equals("update_location")){

            try {
                String longitude = params[1];
                String latitude = params[2];

                // this is for testing and needs to be improved for final implementation.


                URL theURL = new URL(update_location_url);
                URLConnection conn = theURL.openConnection();
                conn.setDoOutput(true);
                conn.setDoInput(true);
                OutputStream outputStream = conn.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("longitude", "UTF-8") + "=" + URLEncoder.encode(longitude, "UTF-8") + "&" +
                        URLEncoder.encode("latitude", "UTF-8") + "=" + URLEncoder.encode(latitude, "UTF-8") + "&" +
                        URLEncoder.encode("fwid", "UTF-8") + "=" + URLEncoder.encode(((Globals) context.getApplicationContext()).getFieldWorkerID(), "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                InputStream inputStream = conn.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                inputStream.close();

                return result;

            }catch(MalformedURLException e){e.printStackTrace();
            }catch(IOException e){e.printStackTrace();}
        }


        if(type.equals("update")){

        }

        if (type.equals("viewAllMyTickets")){

        }

        if (type.equals("view")){

        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Status");
    }

    @Override
    protected void onPostExecute(String result) {


        if(result.equals("Login Successful")){
            alertDialog.setMessage(result);
            alertDialog.show();
            //Intent intent=new Intent(context,Account.class);
            //context.startActivity(intent);
        }
        else{
            alertDialog.setMessage("Login Unsuccessful.Try again.");
            alertDialog.show();

        }

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

}
