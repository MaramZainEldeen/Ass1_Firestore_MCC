package com.example.lect_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {



    Button btn_show;
    Button btn_save ;
    EditText name ;
    EditText number ;
    EditText adress ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        btn_show = findViewById(R.id.btn_show);
        btn_save = findViewById(R.id.btn_save);
        name = findViewById(R.id.edt_name);
        number = findViewById(R.id.edt_number);
        adress = findViewById(R.id.edt_adress);


        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this , Recycle_view.class);
                startActivity(intent);
            }
        });


        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        String User_name = name.getText().toString().trim();
        String User_number = number.getText().toString().trim();
        String User_adress = adress.getText().toString().trim();

   // add data in fire base :
     Map<String,Object> user = new HashMap<>();
      user.put("name",User_name);
      user.put("number",User_number);
      user.put("adress",User_adress);

      db.collection("user").add(user).addOnSuccessListener(documentReference ->
       {
           Toast.makeText(MainActivity.this ,"Save is Success...",Toast.LENGTH_SHORT).show();
        Log.d("TTT" , "Add in Android is Done : "+ documentReference.getId());
        }).addOnFailureListener(e -> {
          Toast.makeText(MainActivity.this ,"Save is Failuer...",Toast.LENGTH_LONG).show();
        Log.d("TTT" , e.getMessage());
                });
            }
        });




    }

}