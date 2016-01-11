package es.hol.danirb.brastlewarkcensus.network;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;

import es.hol.danirb.brastlewarkcensus.models.Brastlewark;
import es.hol.danirb.brastlewarkcensus.models.Gnome;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by dani on 9/01/16.
 */
public class GetCesus extends AsyncTask<Void, Context, String> {
    private Context context;


    private static String urlString = "http://alt17android.hol.es/androidtest.json";

    private static final String TAG_VILLAGE = "Brastlewark";
    private static final String TAG_ID = "id";
    private static final String TAG_NAME = "name";
    private static final String TAG_PICTURE = "thumbnail";
    private static final String TAG_AGE = "age";
    private static final String TAG_WEIGHT = "weight";
    private static final String TAG_HEIGHT = "height";
    private static final String TAG_HAIR_COLOR = "hair_color";
    private static final String TAG_PROFESSIONS = "professions";
    private static final String TAG_FRIENDS = "friends";
    private List<Gnome> gnomeList = new ArrayList<>();
    private ProgressDialog pDialog;
    // contacts JSONArray
    JSONArray gnomes = null;

    public GetCesus(Context context) {
        this.context = context;
        this.pDialog = new ProgressDialog(context);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // Showing progress dialog



    }

    @Override
    protected String doInBackground(Void... params) {

            Service client = ServiceGenerator.createService(Service.class);
            Call<Brastlewark> call = client.repoVillage();
            call.enqueue(new Callback<Brastlewark>() {
                String res;

                @Override
                public void onResponse(Response<Brastlewark> response, Retrofit retrofit) {
                    Log.d("JSON", response.body().toString());
                    if (response.isSuccess()) {
                         setGnomeList(response.body().getGnomeList());
                        Log.d("JSON", response.body().toString());
                 //       Instantiate a JSON object from the request response
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response.toString());
                            ObjectOutput out = new ObjectOutputStream(new FileOutputStream(new File(Environment.getExternalStorageDirectory().getPath(),"")+"cacheFile.srl"));
                            out.writeObject( jsonObject );
                            out.close();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                        res=response.body().toString();
                    }
                }
                @Override
                public void onFailure(Throwable t) {
                    res="";
                }
            });
        try {
            call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
//
//                String res = "";
//        URL url;
//        HttpURLConnection urlConnection = null;
//        try {
//
//
//
//
//
//            url = new URL(urlString);
//            urlConnection = (HttpURLConnection) url.openConnection();
//          //  InputStreamReader isw = new InputStreamReader(urlConnection.getInputStream());
//            BufferedReader in = new BufferedReader(new InputStreamReader(
//                   urlConnection.getInputStream()));
//            String inputLine;
//            while ((inputLine = in.readLine()) != null)
//                {
//                    res +=inputLine;
//                    System.out.println(inputLine);
//            }
//            in.close();
//
//            BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(), "UTF-8"));
//            String json = reader.readLine();
//
//// Instantiate a JSON object from the request response
//            JSONObject jsonObject = new JSONObject(json);
//
//// Save the JSONOvject
//            ObjectOutput out = new ObjectOutputStream(new FileOutputStream(new File(Environment.getExternalStorageDirectory().getPath(),"")+"cacheFile.srl"));
//            out.writeObject( jsonObject );
//            out.close();
////            int data;
////            while ((data = isw.read()) != -1) {
////                char current = (char) data;
////                System.out.print(current);
////                 res+=current;
////            }
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                urlConnection.disconnect();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
        String res = null;
        return res;
    }

    @Override
    protected void onPostExecute(String aVoid) {
        pDialog.dismiss();

//        try {
//            JSONObject jsonObj = new JSONObject(aVoid);
//
//            // Getting JSON Array node
//            gnomes = jsonObj.getJSONArray(TAG_VILLAGE);
//
//            for (int i = 0; i < gnomes.length(); i++) {
//                JSONObject c = gnomes.getJSONObject(i);
//                Gnome g = new Gnome();
//                g.setId(c.getInt(TAG_ID));
//                g.setName(c.getString(TAG_NAME));
//                g.setPicture(c.getString(TAG_PICTURE));
//                g.setAge(c.getInt(TAG_AGE));
//                g.setHair_color(c.getString(TAG_HAIR_COLOR));
//                g.setHeight(c.getDouble(TAG_HEIGHT));
//                g.setWeight(c.getDouble(TAG_WEIGHT));
//
//                JSONArray professionsJSON = c.getJSONArray(TAG_PROFESSIONS);
//                List<String> professionsList = new ArrayList<>();
//                for (int j = 0; j < professionsJSON.length(); j++) {
//                    professionsList.add(professionsJSON.getString(j));
//                }
//                g.setProfessions(professionsList);
//
//                JSONArray friendsJSON = c.getJSONArray(TAG_FRIENDS);
//                List<String> friendsList = new ArrayList<>();
//                for (int k = 0; k < friendsJSON.length(); k++) {
//                    friendsList.add(friendsJSON.getString(k));
//                }
//                g.setFriends(friendsList);
//                Log.d("GNOME: ", g.getName() + " " + g.getId());
//                gnomeList.add(g);
//            }
//        } catch (Exception e) {
//            e.getMessage();
//        }
    }

    public List<Gnome> getGnomeList() {
        return gnomeList;
    }

    public void setGnomeList(List<Gnome> gnomeList) {
        this.gnomeList = gnomeList;
    }
}
