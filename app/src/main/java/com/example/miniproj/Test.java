//package com.example.miniproj;
////
////import androidx.appcompat.app.AppCompatActivity;
////
////import android.os.Bundle;
////
////public class Test extends AppCompatActivity {
////
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_test);
////    }
////}
//
//import static android.content.ContentValues.TAG;
//
//import android.content.Context;
//import android.util.Log;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.GridView;
//import android.widget.ImageView;
//
//import androidx.annotation.NonNull;
//
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.QueryDocumentSnapshot;
//import com.google.firebase.firestore.QuerySnapshot;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Test extends BaseAdapter {
//
//    private Context context;
//    private List<String> imageUrls;
//
//    public Test(Test context, List<String> imageUrls) {
////        this.context = context;
//        this.imageUrls = imageUrls;
//    }
//
//    @Override
//    public int getCount() {
//        return imageUrls.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return imageUrls.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        ImageView imageView;
//
//        if (convertView == null) {
//            imageView = new ImageView(context);
//            imageView.setLayoutParams(new GridView.LayoutParams(200, 200));
//            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        } else {
//            imageView = (ImageView) convertView;
//        }
//
//        // Load image from Firestore and set it to the ImageView
//        String imageUrl = imageUrls.get(position);
//
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        db.collection("images")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            List<String> imageUrls = new ArrayList<>();
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                String imageUrl = document.getString("url");
//                                imageUrls.add(imageUrl);
//                            }
//
//                            // Create and set the adapter for the GridView
//                            Test adapter = new Test(Test.this, imageUrls);
////                            GridView gridView = findViewById(R.id.gridView);
////                            gridView.setAdapter(adapter);
//                        } else {
//                            Log.d(TAG, "Error getting documents: ", task.getException());
//                        }
//                    }
//                });
//        return imageView;
//    }
//}
