package com.example.miniproj;

import android.content.Context;
import android.content.Intent;
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

        // Set onClickListener for the CardView
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click event if needed
//                Toast.makeText(context, "CardView clicked", Toast.LENGTH_SHORT).show();
            }
        });

        viewHolder.btn_yt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String videoLink = recipe.getVideoLink();
                if (!videoLink.isEmpty()) {
                    // Open the YouTube video link in a browser or YouTube app
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(videoLink));
                    context.startActivity(intent);
                } else {
                    Toast.makeText(context, "No YouTube link available.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return convertView;
    }

    private static class ViewHolder {
        CardView cardView;
        ImageView imageView;
        TextView nameTextView, expandedIngredientTextView, expandedInstructionTextView;
        ImageView btn_yt;
    }
}