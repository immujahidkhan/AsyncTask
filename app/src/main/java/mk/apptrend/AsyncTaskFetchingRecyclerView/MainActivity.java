package mk.apptrend.AsyncTaskFetchingRecyclerView;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends Activity {
    private static String urlString;
    ArrayList<ModelClass> list = new ArrayList<>();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.hasFixedSize();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        //urlString = "http://rem.witorbit.net/index.php?display=signup&ajax=1&array=1&fk_state_id=7";
        urlString = "https://volleyandroid.000webhostapp.com/androidindex.php";
        //new ProcessJSON().execute(urlString);
        new CitiesListJSON().execute(urlString);
    }

    private class CitiesListJSON extends AsyncTask<String,Void,String> {
        ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);

        @Override
        protected String doInBackground(String... strings) {
            String stream = null;
            String urlString = strings[0];
            HTTPDataHandler hh = new HTTPDataHandler();
            stream = hh.GetHTTPData(urlString);
            return stream;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setMessage("Loading");
            progressDialog.show();
            progressDialog.setIndeterminate(false);
            progressDialog.setCanceledOnTouchOutside(false);
        }

        @Override
        protected void onPostExecute(String s) {
            //super.onPostExecute(s);
            if (s != null) {
                progressDialog.dismiss();
                try {
                    JSONObject obj = new JSONObject(s);
                    JSONArray array = obj.getJSONArray("records");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject weather_object_0 = array.getJSONObject(i);
                        String id = weather_object_0.getString("Id");
                        String name = weather_object_0.getString("Name");
                        list.add(new ModelClass(id,name));
                    }
                    recyclerView.setAdapter(new AdapterClass(MainActivity.this,list));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
