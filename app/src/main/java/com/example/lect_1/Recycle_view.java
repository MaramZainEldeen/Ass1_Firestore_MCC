package com.example.lect_1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firestore.v1.Value;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Recycle_view extends AppCompatActivity {

    ProgressDialog PD;
    RecyclerView rv;
    RecyclerViewAdapter adapter;
    ArrayList<User> item;
    FirebaseFirestore db ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);

        db = FirebaseFirestore.getInstance();

        PD = new ProgressDialog(Recycle_view.this);
        PD.setCancelable(false);
        PD.setMessage("Please Wait The Information.... :)");
        PD.show();
       // PD.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        rv = findViewById(R.id.rv_user);
        // جعل الريسايكل الفيو الغرض تبعها ثابت
        rv.setHasFixedSize(true);
        // تستخدم لكيفية العرض داخل الريسايكل فيو
        RecyclerView.LayoutManager  LM= new LinearLayoutManager(this);
        rv.setLayoutManager(LM);

        item = new ArrayList<User>();
        adapter = new RecyclerViewAdapter(Recycle_view.this,item);
        rv.setAdapter(adapter);

       EventChangeListener();



    }

    private void EventChangeListener() {
        db.collection("user").orderBy("name" , Query.Direction.ASCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override

            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                if(e != null){
                    if(PD.isShowing())
                        PD.dismiss();
                    Log.e("FireBase" , e.getMessage());
                    return;
                }

               for (DocumentChange dc : queryDocumentSnapshots.getDocumentChanges()){

                     if(dc.getType() == DocumentChange.Type.ADDED){
                   Log.d("test" , String.valueOf(dc.getDocument()));
                     item.add(dc.getDocument().toObject(User.class));

                     }
                   adapter.notifyDataSetChanged();
                   if(PD.isShowing())
                       PD.dismiss();
               }
            }
        });
    }
}