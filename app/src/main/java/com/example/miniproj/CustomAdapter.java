//package com.example.miniproj;
//
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.net.Uri;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.cardview.widget.CardView;
//
//import com.bumptech.glide.Glide;
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;
//
//import org.checkerframework.checker.nullness.qual.NonNull;
//
//import java.util.List;
//
//public class CustomAdapter extends BaseAdapter {
//
//    private Context context;
//    private List<Recipe> recipeList;
//
//    StorageReference storageRef = FirebaseStorage.getInstance().getReference();
////    StorageReference imageRef = storageRef.child("images/" + expandedImageView);
//
//
//    public CustomAdapter(Context context, List<Recipe> recipeList) {
//        this.context = context;
//        this.recipeList = recipeList;
//    }
//
//    @Override
//    public int getCount() {
//        return recipeList.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return recipeList.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewHolder viewHolder;
//
//        if (convertView==null) {
//            convertView = LayoutInflater.from(context).inflate(R.layout.expanded_layout, parent, false);
//
//            viewHolder = new ViewHolder();
//            viewHolder.cardView = convertView.findViewById(R.id.cardView);
//            viewHolder.imageView = convertView.findViewById(R.id.expandedImageView);
//            viewHolder.nameTextView = convertView.findViewById(R.id.expandedNameTextView);
//
//            convertView.setTag(viewHolder);
//        } else {
//            convertView = LayoutInflater.from(context).inflate(R.layout.expanded_layout, parent, false);
//            viewHolder = (ViewHolder) convertView.getTag();
//
//            viewHolder = new ViewHolder();
//            viewHolder.cardView = convertView.findViewById(R.id.cardView);
//            viewHolder.imageView = convertView.findViewById(R.id.expandedImageView);
//            viewHolder.nameTextView = convertView.findViewById(R.id.expandedNameTextView);
//
//        }
//
//        // Get the recipe for this position
//        Recipe recipe = recipeList.get(position);
//
//        // Set the image
////        Glide.with(viewHolder.imageView .getContext().getApplicationContext()).load(imageRef).into(viewHolder.imageView );
//        //StorageReference imageRef = null;
//        //Glide.with(this).load(imageRef).into(imageView);
//        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
//        Uri imageUri = Uri.parse(recipe.getImage());
//        StorageReference photoReference= storageReference.child("images/"+imageUri);
//
//        long ONE_MEGABYTE = 1024 * 1024;
//        ViewHolder finalViewHolder = viewHolder;
//        ViewHolder finalViewHolder2 = finalViewHolder;
//        photoReference.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
//            @Override
//            public void onSuccess(byte[] bytes) {
//                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
//                finalViewHolder2.imageView.setImageBitmap(bmp);
//
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception exception) {
//                Toast.makeText(context.getApplicationContext(), "No Such file or Path found!!", Toast.LENGTH_LONG).show();
//            }
//        });
//
//        // Set the name
//        viewHolder.nameTextView.setText(recipe.getName());
//
//        // Set onClickListener for the CardView
//        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Toggle the expanded state
//                recipe.setExpanded(!recipe.isExpanded());
//
//                // Notify the adapter that the data has changed
//                notifyDataSetChanged();
//            }
//        });
//
//
//        // Handle the expanded state
//        if (recipe.isExpanded()) {
//            View expandedView = LayoutInflater.from(context).inflate(R.layout.expanded_layout, parent, false);
//
////            ImageView expandedImageView = expandedView.findViewById(R.id.expandedImageView);
//
//            TextView expandedNameTextView = expandedView.findViewById(R.id.expandedNameTextView);
//            TextView expandedDescriptionTextView = expandedView.findViewById(R.id.expandedDescriptionTextView);
//            TextView expandedIngredientsTextView = expandedView.findViewById(R.id.expandedIngredientsTextView);
//            TextView expandedInstructionsTextView = expandedView.findViewById(R.id.expandedInstructionsTextView);
//
//            // Set the expanded view data
//
////            Glide.with(this).load(imageRef).into(imageView);
//
////            StorageReference storageRef = FirebaseStorage.getInstance().getReference();
////            StorageReference imageRef = storageRef.child("images/" + expandedImageView);
//
//            storageReference = FirebaseStorage.getInstance().getReference();
//            imageUri = Uri.parse(recipe.getImage());
//            photoReference= storageReference.child("images/"+imageUri);
//
//            ONE_MEGABYTE = 1024 * 1024;
//            finalViewHolder = viewHolder;
//            ViewHolder finalViewHolder1 = finalViewHolder;
//            photoReference.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
//                @Override
//                public void onSuccess(byte[] bytes) {
//                    Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
//                    finalViewHolder1.imageView.setImageBitmap(bmp);
//
//                }
//            }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception exception) {
//                    Toast.makeText(context.getApplicationContext(), "No Such file or Path found!!", Toast.LENGTH_LONG).show();
//                }
//            });
//
////            Glide.with(expandedImageView.getContext().getApplicationContext()).load(imageRef).into(expandedImageView);
//            expandedNameTextView.setText(recipe.getName());
//            expandedDescriptionTextView.setText(recipe.getDescription());
//            expandedIngredientsTextView.setText(recipe.getIngredients());
//            expandedInstructionsTextView.setText(recipe.getInstructions());
//
//            // Replace the convertView with the expanded view
//            return expandedView;
//        }
//        return convertView;
//
//    }
//
//    private static class ViewHolder {
//        CardView cardView;
//        ImageView imageView;
//        TextView nameTextView;
//    }
//}


package com.example.miniproj;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.List;

public class CustomAdapter extends BaseAdapter {

    private Context context;
    private List<Recipe> recipeList;

    StorageReference storageRef = FirebaseStorage.getInstance().getReference();

    public CustomAdapter(Context context, List<Recipe> recipeList) {
        this.context = context;
        this.recipeList = recipeList;
    }

    @Override
    public int getCount() {
        return recipeList.size();
    }

    @Override
    public Object getItem(int position) {
        return recipeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.expanded_layout, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.cardView = convertView.findViewById(R.id.cardView);
            viewHolder.imageView = convertView.findViewById(R.id.expandedImageView);
            viewHolder.nameTextView = convertView.findViewById(R.id.expandedNameTextView);
            viewHolder.expandedIngredientTextView=convertView.findViewById(R.id.expandedIngredientsTextView);
            viewHolder.expandedInstructionTextView=convertView.findViewById(R.id.expandedInstructionsTextView);
            viewHolder.btn_yt=convertView.findViewById(R.id.btn_yt);

            convertView.setTag(viewHolder);
        } else {
            convertView = LayoutInflater.from(context).inflate(R.layout.expanded_layout, parent, false);
            viewHolder = (ViewHolder) convertView.getTag();

            viewHolder = new ViewHolder();
            viewHolder.cardView = convertView.findViewById(R.id.cardView);
            viewHolder.imageView = convertView.findViewById(R.id.expandedImageView);
            viewHolder.nameTextView = convertView.findViewById(R.id.expandedNameTextView);
            viewHolder.expandedIngredientTextView=convertView.findViewById(R.id.expandedIngredientsTextView);
            viewHolder.expandedInstructionTextView=convertView.findViewById(R.id.expandedInstructionsTextView);
            viewHolder.btn_yt=convertView.findViewById(R.id.btn_yt);
        }

        // Get the recipe for this position
        Recipe recipe = recipeList.get(position);



        // Set the image
//        Glide.with(viewHolder.imageView .getContext().getApplicationContext()).load(imageRef).into(viewHolder.imageView );
        //StorageReference imageRef = null;
        //Glide.with(this).load(imageRef).into(imageView);
        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
        Uri imageUri = Uri.parse(recipe.getImage());
        StorageReference photoReference= storageReference.child("images/"+imageUri);

        long ONE_MEGABYTE = 1024 * 1024;
        ViewHolder finalViewHolder = viewHolder;
        ViewHolder finalViewHolder2 = finalViewHolder;
        photoReference.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                finalViewHolder2.imageView.setImageBitmap(bmp);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
//                Toast.makeText(context.getApplicationContext(), "No Such file or Path found!!", Toast.LENGTH_LONG).show();
            }
        });

        // Set the name
        viewHolder.nameTextView.setText(recipe.getName());
        viewHolder.expandedInstructionTextView.setText(recipe.getInstructions());
        viewHolder.expandedIngredientTextView.setText(recipe.getIngredients());
//        viewHolder.btn_yt.setImageURI(recipe.getYoutube());

        // Set onClickListener for the CardView
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click event if needed
//                Toast.makeText(context, "CardView clicked", Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }

    private static class ViewHolder {
        CardView cardView;
        ImageView imageView;
        TextView nameTextView,expandedIngredientTextView, expandedInstructionTextView;
        ImageView btn_yt;
    }
}
