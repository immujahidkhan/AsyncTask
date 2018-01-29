package mk.apptrend.AsyncTaskFetching;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends Activity {
    private static String urlString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView tv =  findViewById(R.id.tv);
        tv.setText("");
        urlString = "http://rem.witorbit.net/index.php?display=signup&ajax=1&array=1&fk_state_id=7";
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
                TextView tv = findViewById(R.id.tv);
                try {
                    JSONObject obj = new JSONObject(s);
                    JSONArray array = obj.getJSONArray("cities_list");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject weather_object_0 = array.getJSONObject(i);
                        String weather_0_id = weather_object_0.getString("id");
                        String weather_0_main = weather_object_0.getString("name");
                        tv.setText(tv.getText() + "id..." + weather_0_id + "\n");
                        tv.setText(tv.getText() + "name..." + weather_0_main + "\n");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
