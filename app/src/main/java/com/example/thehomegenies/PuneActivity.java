package com.example.thehomegenies;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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

public class PuneActivity extends AppCompatActivity {

    private EditText sqft, bath, bhk, balcony;
    private AutoCompleteTextView area, ready;
    private Button predict;
    private TextView result;
    private Button clear;
    String url = "https://house-price-prediction-pun-app.herokuapp.com/predict";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pune);

        String[] location = new String[]{"Alandi Road", "Ambegaon Budruk", "Anandnagar", "Aundh", "Aundh Road", "Balaji Nagar", "aner", "Baner road", "Bhandarkar Road", "Bhavani Peth", "Bibvewadi", "Bopodi", "Budhwar Peth", "Bund Garden Road", "Camp", "Chandan Nagar", "Dapodi", "Deccan Gymkhana", "Dehu Road", "Dhankawadi", "Dhayari Phata", "Dhole Patil Road", "Erandwane", "Fatima Nagar", "Fergusson College Road", "Ganesh Peth", "Ganeshkhind", "Ghorpade Peth", "Ghorpadi", "Gokhale Nagar", "Gultekdi", "Guruwar peth", "Hadapsar", "Hadapsar Industrial Estate", "Hingne Khurd", "Jangali Maharaj Road", "Kalyani Nagar", "Karve Nagar", "Karve Road", "Kasba Peth", "Katraj", "Khadaki", "Khadki", "Kharadi", "Kondhwa", "Kondhwa Budruk", "Kondhwa Khurd", "Koregaon Park", "Kothrud", "Law College Road", "Laxmi Road", "Lulla Nagar", "Mahatma Gandhi Road", "Mangalwar peth", "Manik Bagh", "Market yard", "Model colony", "Mukund Nagar", "Mundhawa", "Nagar Road", "Nana Peth", "Narayan Peth", "Narayangaon", "Navi Peth", "Padmavati", "Parvati Darshan", "Pashan", "Paud Road", "Pirangut", "Prabhat Road", "Pune Railway Station", "Rasta Peth", "Raviwar Peth", "Sadashiv Peth", "Sahakar Nagar", "Salunke Vihar", "Sasson Road", "Satara Road", "Senapati Bapat Road", "Shaniwar Peth", "Shivaji Nagar", "Shukrawar Peth", "Sinhagad Road", "Somwar Peth", "Swargate", "Tilak Road", "Uruli Devachi", "Vadgaon Budruk", "Viman Nagar", "Vishrant Wadi", "Wadgaon Sheri", "Wagholi", "Wakadewadi", "Wanowrie", "Warje", "Yerawada"};
        String[] area_type = new String[]{"Built-up  Area", "Carpet  Area", "Plot  Area"};
        String[] status = new String[]{"Ready To Move", "Not Ready"};
        ArrayAdapter<String> adapter1 =  new ArrayAdapter<>(this, R.layout.dropdown_item,location);
        ArrayAdapter<String> adapter2 =  new ArrayAdapter<>(this, R.layout.dropdown_item,area_type);
        ArrayAdapter<String> adapter3 =  new ArrayAdapter<>(this, R.layout.dropdown_item,status);

        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.location);
        area = findViewById(R.id.area);
        sqft = findViewById(R.id.sqft);
        bath = findViewById(R.id.bath);
        bhk = findViewById(R.id.bhk);
        balcony = findViewById(R.id.balcony);
        ready = findViewById(R.id.status);
        predict = findViewById(R.id.predict);
        result = findViewById(R.id.result);
        clear = findViewById(R.id.clear);

        autoCompleteTextView.setAdapter(adapter1);
        area.setAdapter(adapter2);
        ready.setAdapter(adapter3);


//        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(PuneActivity.this,autoCompleteTextView.getText().toString(),Toast.LENGTH_SHORT).show();
//            }
//        });


        predict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (autoCompleteTextView.getText().toString().isEmpty() ){
                    autoCompleteTextView.setError("Select Any Location");
                }else if (area.getText().toString().isEmpty()){
                    area.setError("Select type of Area");
                }else if (sqft.getText().toString().isEmpty() || sqft.getText().toString().equals("0") || Integer.parseInt(sqft.getText().toString()) < 500){
                    sqft.setError("Cannot be Empty / 0 / <500");
                }else if (bath.getText().toString().isEmpty() || bath.getText().toString().equals("0") || (Integer.parseInt(bath.getText().toString()) > 10) || (Integer.parseInt(bath.getText().toString()) > (Integer.parseInt(bhk.getText().toString())))) {
                    bath.setError("Cannot be Empty / 0 / >10 / > no. of rooms");
                }else if (bhk.getText().toString().isEmpty() || bhk.getText().toString().equals("0") || Integer.parseInt(bhk.getText().toString()) > 10){
                    bhk.setError("Cannot be Empty / 0 / >10");
                }else if (balcony.getText().toString().isEmpty() || balcony.getText().toString().equals("0") || (Integer.parseInt(balcony.getText().toString()) > 10) || (Integer.parseInt(balcony.getText().toString()) > (Integer.parseInt(bhk.getText().toString())))){
                    balcony.setError("Cannot be Empty / 0 / >10 / > no. of rooms");
                }else if (ready.getText().toString().isEmpty()){
                    ready.setError("Select something!");
                }else{
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONObject jsonObject = new JSONObject(response);
                                        String data = jsonObject.getString("price");
                                        clear.setVisibility(View.VISIBLE);

                                        Log.d("Predict Price :", "onResponse: "+data);
                                        result.setTextColor(Color.parseColor("#5bdeac"));
                                        result.setText("Rs. "+data);



                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            String err = (error.getMessage()==null)?"Failed! Please Try Again":error.getMessage();
                            Toast.makeText(PuneActivity.this,err,Toast.LENGTH_SHORT).show();
                            Log.d("API ERROR : ", err);
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams(){
                            Map<String,String> params = new HashMap<>();
//                            params.put("location",location.getText().toString());
                            params.put("location",autoCompleteTextView.getText().toString());
                            params.put("area",area.getText().toString());
                            params.put("sqft",sqft.getText().toString());
                            params.put("bath",bath.getText().toString());
                            params.put("bhk",bhk.getText().toString());
                            params.put("balcony",balcony.getText().toString());
                            params.put("ready",ready.getText().toString());

                            return  params;
                        }
                    };

                    RequestQueue queue = Volley.newRequestQueue(PuneActivity.this);
                    queue.add(stringRequest);
                }
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                autoCompleteTextView.setText("");
                area.setText("");
                sqft.setText("");
                bath.setText("");
                bhk.setText("");
                balcony.setText("");
                ready.setText("");
                result.setText("");
                clear.setVisibility(View.GONE);
            }
        });

    }
}