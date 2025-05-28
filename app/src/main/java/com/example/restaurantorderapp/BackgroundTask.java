package com.example.restaurantorderapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundTask extends AsyncTask<String, Void, String> {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    Context context;

    BackgroundTask(Context ctx){
        this.context = ctx;
    }
    @Override

    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        String flag = preferences.getString("flag","0");

        if(flag.equals("Register")) {
            Toast.makeText(context,s,Toast.LENGTH_LONG).show();
        }
        if(flag.equals("SignIn")){
            String test = "false";
            String name = "";
            //String email = "";
            String[] serverResponse = s.split("[,]");
            test = serverResponse[0];
            name = serverResponse[1];
            //email = serverResponse[2];

            if(test.equals("true")){
                editor.putString("name",name);
                editor.commit();
                //editor.putString("email",email);
                editor.commit();
                Intent intent = new Intent(context,LoginWelcome.class);
                context.startActivity(intent);
            }else{
                display("Login Failed...", "That username or password do not match our records :(.");
            }
        }else{
            display("Login Failed...","Something Wrong Happened.");
        }

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    public void display(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    @Override
    protected String doInBackground(String... params) {
        preferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        editor = preferences.edit();
        editor.putString("flag","0");
        editor.commit();

        String urlRegistration = "https://lamp.ms.wits.ac.za/home/s2676309/register.php";
        String urlLogin = "https://lamp.ms.wits.ac.za/home/s2676309/AuthenticateLogin.php";
        String activity = params[0];

        if(activity.equals("register")){
            String regName = params[1];
            //String regEmail = params[2];
            String regPassword = params[2];

            try {
                URL url = new URL(urlRegistration);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream,"UTF-8");
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                String myData = URLEncoder.encode("identifier_name","UTF-8")+"="+URLEncoder.encode(regName,"UTF-8")+"&"
                        //+URLEncoder.encode("identifier_email","UTF-8")+"="+URLEncoder.encode(regEmail,"UTF-8")+"&"
                        +URLEncoder.encode("identifier_password","UTF-8")+"="+URLEncoder.encode(regPassword,"UTF-8");
                bufferedWriter.write(myData);
                bufferedWriter.flush();
                bufferedWriter.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                inputStream.close();

                editor.putString("flag","Register");
                editor.commit();
                return "Successfully Registered " + regName;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if(activity.equals("SignIn")){
            //String loginEmail = params[1];
            String loginUsername = params[1];
            String loginPassword = params[2];
            try {
                URL url = new URL(urlLogin);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                //send the email and password to the database
                OutputStream outputStream = httpURLConnection.getOutputStream();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream,"UTF-8");
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                //String myData = URLEncoder.encode("identifier_loginEmail","UTF-8")+"="+URLEncoder.encode(loginEmail,"UTF-8")+"&"
                String myData = URLEncoder.encode("identifier_loginEmail","UTF-8")+"="+URLEncoder.encode(loginUsername,"UTF-8")+"&"
                        +URLEncoder.encode("identifier_loginPassword","UTF-8")+"="+URLEncoder.encode(loginPassword,"UTF-8");
                bufferedWriter.write(myData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                //get response from the database
                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String dataResponse = "";
                String inputLine = "";
                while((inputLine = bufferedReader.readLine()) != null){
                    dataResponse += inputLine;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                System.out.println(dataResponse);

                editor.putString("flag","SignIn");
                editor.commit();
                return  dataResponse;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}