package com.example.alexandergiammaruti.fingerprintauthentication;


import android.accounts.Account;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Output;
import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONArray;
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
        String update_hours_url = "https://tickats.live/updatehours.php";
        String TicketStart_URL = "https://tickats.live/DisplayDataTickets.php";
        String HeavyEquipment_URL = "https://tickats.live/DisplayHeavyEquipment.php";
        String LightEquipment_URL = "https://tickats.live/DisplayLightEquipment.php";
        String WorkerInfo_URL = "https://tickats.live/DisplayWorkerInfo.php";
        String HeaderInfo_URL = "https://tickats.live/DisplayDataWorksiteLocation.php";


        if (type.equals("login")) {
            try {

                String user_name = params[1];
                String password = params[2];

                URL url = new URL(login_url);
                HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
                urlConnection.setDoOutput(true);
                urlConnection.setDoInput(true);

                OutputStream outputStream = urlConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(user_name, "UTF-8") + "&"
                        + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = urlConnection.getInputStream();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                JSONObject responseJSON = new JSONObject(result);
                String loginMessage = responseJSON.getString("loginMessage");
                //Fid= responseJSON.getString("FWid");
                ((Globals) context.getApplicationContext()).setFieldWorkerID(responseJSON.getString("FWid"));
                ((Globals) context.getApplicationContext()).setFirstName(responseJSON.getString("First"));
                ((Globals) context.getApplicationContext()).setLastName(responseJSON.getString("Last"));
                ((Globals) context.getApplicationContext()).setHoursWorked(responseJSON.getString("Hours"));


                tab1.fid = ((Globals) context.getApplicationContext()).getFieldWorkerID();
                tab1.fname = ((Globals) context.getApplicationContext()).getFirstName();
                tab1.lname = ((Globals) context.getApplicationContext()).getLastName();
                tab1.hrs = ((Globals) context.getApplicationContext()).getHoursWorked();


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


        } else if (type.equals("update_location")) {

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
                        URLEncoder.encode("fwid", "UTF-8") + "=" + URLEncoder.encode(((Globals) FingerprintAuthenticationActivity.context.getApplicationContext()).getFieldWorkerID(), "UTF-8");
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

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        if (type.equals("update_hours")) {
            try {
                String hours = params[1];
                String fwid = params[2];

                // this is for testing and needs to be improved for final implementation.


                URL theURL = new URL(update_hours_url);
                URLConnection conn = theURL.openConnection();
                conn.setDoOutput(true);
                conn.setDoInput(true);
                OutputStream outputStream = conn.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("hours", "UTF-8") + "=" + URLEncoder.encode(hours, "UTF-8") + "&" +
                        URLEncoder.encode("fwid", "UTF-8") + "=" + URLEncoder.encode(fwid, "UTF-8");
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

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (type.equals("viewAllMyTickets")) {

        }

        if (type.equals("view")) {

        } else if (type.equals("TicketStart")) {
            try {
                String FWid = params[1];
                URL url = new URL(TicketStart_URL);
                URLConnection conn = url.openConnection();
                conn.setDoOutput(true);
                conn.setDoInput(true);
                OutputStream outputStream = conn.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("FWid", "UTF-8") + "=" + URLEncoder.encode(FWid, "UTF-8");
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

                JSONObject JO = new JSONObject(result);
                JSONArray jTickets = JO.getJSONArray("Tickets");// Array name from php file

                for (int i = 0; i < jTickets.length(); i++) {
                    JSONObject t = jTickets.getJSONObject(i);
                    // Pulls data from the URL and puts it into a string for later insertion
                    String Tid = t.getString("TicketID");
                    String wName = t.getString("WorksiteName");
                    String Prior = t.getString("Priority");
                    // Adds values from the JSON array into the relative layout
                    tab2.mTicketID.add(Tid);
                    tab2.mWorksite.add(wName);
                    tab2.mPriority.add(Prior);
                }

                bufferedReader.close();
                inputStream.close();
                outputStream.close();
                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (type.equals("HeavyEquip")) {
            try {
                String TID = params[1];
                //String TID = params[2];
                URL url = new URL(HeavyEquipment_URL);
                URLConnection conn = url.openConnection();
                conn.setDoOutput(true);
                conn.setDoInput(true);
                OutputStream outputStream = conn.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("TID", "UTF-8") + "=" + URLEncoder.encode(TID, "UTF-8");
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
                JSONObject Job = new JSONObject(result);
                JSONArray HEdata = Job.getJSONArray("Heavy Equipment");
                for (int i = 0; i < HEdata.length(); i++) {
                    JSONObject HE = HEdata.getJSONObject(i);
                    String HEid = HE.getString("Heavy Equipment ID");
                    String Model = HE.getString("Equipment Description");
                    String status = HE.getString("Equipment Status");
                    TicketDetails.mArr1.add(HEid);
                    TicketDetails.mArr2.add(Model);
                    TicketDetails.mArr3.add(status);
                }
                bufferedReader.close();
                inputStream.close();
                outputStream.close();

                return result;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (type.equals("LightEquip")) {
            try {
                String TID = params[1];
                URL url = new URL(LightEquipment_URL);
                URLConnection conn = url.openConnection();
                conn.setDoOutput(true);
                conn.setDoInput(true);
                OutputStream outputStream = conn.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("TID", "UTF-8") + "=" + URLEncoder.encode(TID, "UTF-8");
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

                JSONObject Job = new JSONObject(result);
                JSONArray LEdata = Job.getJSONArray("Light Equipment");
                for (int i = 0; i < LEdata.length(); i++) {
                    JSONObject LE = LEdata.getJSONObject(i);
                    String LEid = LE.getString("Light Equipment ID");
                    String Model = LE.getString("Equipment Description");
                    String status = LE.getString("Equipment Status");
                    TicketDetails.mArr4.add(LEid);
                    TicketDetails.mArr5.add(Model);
                    TicketDetails.mArr6.add(status);
                }
                bufferedReader.close();
                inputStream.close();
                outputStream.close();

                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else if (type.equals("Workers")){
            try{
                String TID = params[1];
                URL url = new URL(WorkerInfo_URL);
                URLConnection conn = url.openConnection();
                conn.setDoOutput(true);
                conn.setDoInput(true);
                OutputStream outputStream = conn.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("TID", "UTF-8") + "=" + URLEncoder.encode(TID, "UTF-8");
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
                JSONObject Job = new JSONObject(result);
                JSONArray Wdata = Job.getJSONArray("Worker");
                for(int i =0; i<Wdata.length(); i++) {
                    JSONObject HE = Wdata.getJSONObject(i);
                    String Fname = HE.getString("First Name");
                    String Lname = HE.getString("Last Name");
                    String Pnumber = HE.getString("Phone Number");
                    TicketDetails.mArr7.add(Fname);
                    TicketDetails.mArr8.add(Lname);
                    TicketDetails.mArr9.add(Pnumber);
                }
                bufferedReader.close();
                inputStream.close();
                outputStream.close();

                return result;
            }
            catch (MalformedURLException e) {
                e.printStackTrace(); }
            catch (IOException e) {
                e.printStackTrace(); }
            catch (JSONException e) {
                e.printStackTrace(); }
        }else if (type.equals("JobsiteHeader")){
            try{
                String TID = params[1];
                URL url = new URL(HeaderInfo_URL);
                URLConnection conn = url.openConnection();
                conn.setDoOutput(true);
                conn.setDoInput(true);
                OutputStream outputStream = conn.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("TID", "UTF-8") + "=" + URLEncoder.encode(TID, "UTF-8");
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

                JSONObject Job = new JSONObject(result);
                String message=Job.getString("Message");
                TicketDetails.JobAddress=Job.getString("Jobsite");
                TicketDetails.DateAdded=Job.getString("Date");
                TicketDetails.Prior=Job.getString("Priority");

                inputStream.close();
                outputStream.close();

                bufferedReader.close();
                return result;
            }
            catch (MalformedURLException e) {
                e.printStackTrace(); }
            catch (IOException e) {
                e.printStackTrace(); }
            catch (JSONException e) {
                e.printStackTrace(); }
        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        if(context != null) {
            alertDialog = new AlertDialog.Builder(context).create();
            alertDialog.setTitle("Login Status");
        }
    }

    @Override
    protected void onPostExecute(String result) {

        if(result!=null) {
            if (result.equals("Login Successful")) {
                alertDialog.setMessage(result);
                alertDialog.show();

                //start the next screen
                Intent accountIntent = new Intent(context, com.example.alexandergiammaruti.fingerprintauthentication.Account.class);
                context.startActivity(accountIntent);
            } else if (result.equals("Login Unsuccessful")) {
                alertDialog.setMessage("Login Unsuccessful.Try again.");
                alertDialog.show();

            }
        }

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

}
