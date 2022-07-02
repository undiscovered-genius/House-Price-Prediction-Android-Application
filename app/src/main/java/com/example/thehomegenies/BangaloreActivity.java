package com.example.thehomegenies;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class BangaloreActivity extends AppCompatActivity {

    private EditText sqft, bath, bhk, balcony;
    private AutoCompleteTextView area, ready;
    private Button predict;
    private TextView result;
    private Button clear;
    String url = "https://house-price-prediction-ban-app.herokuapp.com/predict";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bangalore);

        String[] location_name = new String[]{"1st Block Jayanagar", "1st Phase JP Nagar", "2nd Phase Judicial Layout", "2nd Stage Nagarbhavi", "5th Block Hbr Layout", "5th Phase JP Nagar", "6th Phase JP Nagar", "7th Phase JP Nagar", "8th Phase JP Nagar", "9th Phase JP Nagar", "AECS Layout", "Abbigere", "Akshaya Nagar", "Ambalipura", "Ambedkar Nagar", "Amruthahalli", "Anandapura", "Ananth Nagar", "Anekal", "Anjanapura", "Ardendale", "Arekere", "Attibele", "BEML Layout", "BTM 2nd Stage", "BTM Layout", "Babusapalaya", "Badavala Nagar", "Balagere", "Banashankari", "Banashankari Stage II", "Banashankari Stage III", "Banashankari Stage V", "Banashankari Stage VI", "Banaswadi", "Banjara Layout", "Bannerghatta", "Bannerghatta Road", "Basavangudi", "Basaveshwara Nagar", "Battarahalli", "Begur", "Begur Road", "Bellandur", "Benson Town", "Bharathi Nagar", "Bhoganhalli", "Billekahalli", "Binny Pete", "Bisuvanahalli", "Bommanahalli", "Bommasandra", "Bommasandra Industrial Area", "Bommenahalli", "Brookefield", "Budigere", "CV Raman Nagar", "Chamrajpet", "Chandapura", "Channasandra", "Chikka Tirupathi", "Chikkabanavar", "Chikkalasandra", "Choodasandra", "Cooke Town", "Cox Town", "Cunningham Road", "Dasanapura", "Dasarahalli", "Devanahalli", "Devarachikkanahalli", "Dodda Nekkundi", "Doddaballapur", "Doddakallasandra", "Doddathoguru", "Domlur", "Dommasandra", "EPIP Zone", "Electronic City", "Electronic City Phase II", "Electronics City Phase 1", "Frazer Town", "GM Palaya", "Garudachar Palya", "Giri Nagar", "Gollarapalya Hosahalli", "Gottigere", "Green Glen Layout", "Gubbalala", "Gunjur", "HAL 2nd Stage", "HBR Layout", "HRBR Layout", "HSR Layout", "Haralur Road", "Harlur", "Hebbal", "Hebbal Kempapura", "Hegde Nagar", "Hennur", "Hennur Road", "Hoodi", "Horamavu Agara", "Horamavu Banaswadi", "Hormavu", "Hosa Road", "Hosakerehalli", "Hoskote", "Hosur Road", "Hulimavu", "ISRO Layout", "ITPL", "Iblur Village", "Indira Nagar", "JP Nagar", "Jakkur", "Jalahalli", "Jalahalli East", "Jigani", "Judicial Layout", "KR Puram", "Kadubeesanahalli", "Kadugodi", "Kaggadasapura", "Kaggalipura", "Kaikondrahalli", "Kalena Agrahara", "Kalyan nagar", "Kambipura", "Kammanahalli", "Kammasandra", "Kanakapura", "Kanakpura Road", "Kannamangala", "Karuna Nagar", "Kasavanhalli", "Kasturi Nagar", "Kathriguppe", "Kaval Byrasandra", "Kenchenahalli", "Kengeri", "Kengeri Satellite Town", "Kereguddadahalli", "Kodichikkanahalli", "Kodigehaali", "Kodigehalli", "Kodihalli", "Kogilu", "Konanakunte", "Koramangala", "Kothannur", "Kothanur", "Kudlu", "Kudlu Gate", "Kumaraswami Layout", "Kundalahalli", "LB Shastri Nagar", "Laggere", "Lakshminarayana Pura", "Lingadheeranahalli", "Magadi Road", "Mahadevpura", "Mahalakshmi Layout", "Mallasandra", "Malleshpalya", "Malleshwaram", "Marathahalli", "Margondanahalli", "Marsur", "Mico Layout", "Munnekollal", "Murugeshpalya", "Mysore Road", "NGR Layout", "NRI Layout", "Nagarbhavi", "Nagasandra", "Nagavara", "Nagavarapalya", "Narayanapura", "Neeladri Nagar", "Nehru Nagar", "OMBR Layout", "Old Airport Road", "Old Madras Road", "Padmanabhanagar", "Pai Layout", "Panathur", "Parappana Agrahara", "Pattandur Agrahara", "Poorna Pragna Layout", "Prithvi Layout", "R.T. Nagar", "Rachenahalli", "Raja Rajeshwari Nagar", "Rajaji Nagar", "Rajiv Nagar", "Ramagondanahalli", "Ramamurthy Nagar", "Rayasandra", "Sahakara Nagar", "Sanjay nagar", "Sarakki Nagar", "Sarjapur", "Sarjapur  Road", "Sarjapura - Attibele Road", "Sector 2 HSR Layout", "Sector 7 HSR Layout", "Seegehalli", "Shampura", "Shivaji Nagar", "Singasandra", "Somasundara Palya", "Sompura", "Sonnenahalli", "Subramanyapura", "Sultan Palaya", "TC Palaya", "Talaghattapura", "Thanisandra", "Thigalarapalya", "Thubarahalli", "Tindlu", "Tumkur Road", "Ulsoor", "Uttarahalli", "Varthur", "Varthur Road", "Vasanthapura", "Vidyaranyapura", "Vijayanagar", "Vishveshwarya Layout", "Vishwapriya Layout", "Vittasandra", "Whitefield", "Yelachenahalli", "Yelahanka", "Yelahanka New Town", "Yelenahalli", "Yeshwanthpur", "other"};
        String[] area_type = new String[]{"Built-up  Area", "Carpet  Area", "Plot  Area"};
        String[] status = new String[]{"Ready To Move", "Not Ready"};
        ArrayAdapter<String> adapter4 =  new ArrayAdapter<>(this, R.layout.dropdown_item,location_name);
        ArrayAdapter<String> adapter5 =  new ArrayAdapter<>(this, R.layout.dropdown_item,area_type);
        ArrayAdapter<String> adapter6 =  new ArrayAdapter<>(this, R.layout.dropdown_item,status);

        AutoCompleteTextView location = findViewById(R.id.location2);
        area = findViewById(R.id.area2);
        sqft = findViewById(R.id.sqft2);
        bath = findViewById(R.id.bath2);
        bhk = findViewById(R.id.bhk2);
        balcony = findViewById(R.id.balcony2);
        ready = findViewById(R.id.status2);
        predict = findViewById(R.id.predict2);
        result = findViewById(R.id.result2);
        clear = findViewById(R.id.clear2);

        location.setAdapter(adapter4);
        area.setAdapter(adapter5);
        ready.setAdapter(adapter6);

        predict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (location.getText().toString().isEmpty() ){
                    location.setError("Select Any Location");
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
                            Toast.makeText(BangaloreActivity.this,err,Toast.LENGTH_SHORT).show();
                            Log.d("API ERROR : ", err);
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams(){
                            Map<String,String> params = new HashMap<>();
                            params.put("location",location.getText().toString());
                            params.put("area",area.getText().toString());
                            params.put("sqft",sqft.getText().toString());
                            params.put("bath",bath.getText().toString());
                            params.put("bhk",bhk.getText().toString());
                            params.put("balcony",balcony.getText().toString());
                            params.put("ready",ready.getText().toString());

                            return  params;
                        }
                    };

                    RequestQueue queue = Volley.newRequestQueue(BangaloreActivity.this);
                    queue.add(stringRequest);
                }
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                location.setText("");
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