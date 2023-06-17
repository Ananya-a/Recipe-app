package com.example.miniproj;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import androidx.cardview.widget.CardView;

import java.util.List;

public class CustomAdapter extends BaseAdapter {

    private Context context;
    private List<Recipe> recipeList;

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

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Get the recipe for this position
        Recipe recipe = recipeList.get(position);

        // Set the image
        Uri imageUri = Uri.parse(recipe.getImage());
        viewHolder.imageView.setImageURI(imageUri);

        // Set the name
        viewHolder.nameTextView.setText(recipe.getName());

        // Set onClickListener for the CardView
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle the expanded state
                recipe.setExpanded(!recipe.isExpanded());

                // Notify the adapter that the data has changed
                notifyDataSetChanged();
            }
        });

        // Handle the expanded state
        if (recipe.isExpanded()) {
            View expandedView = LayoutInflater.from(context).inflate(R.layout.expanded_layout, parent, false);
            ImageView expandedImageView = expandedView.findViewById(R.id.expandedImageView);
            TextView expandedNameTextView = expandedView.findViewById(R.id.expandedNameTextView);
            TextView expandedDescriptionTextView = expandedView.findViewById(R.id.expandedDescriptionTextView);
            TextView expandedIngredientsTextView = expandedView.findViewById(R.id.expandedIngredientsTextView);

            // Set the expanded view data
            StorageReference storageRef = FirebaseStorage.getInstance().getReference();
            StorageReference imageRef = storageRef.child("images/" + expandedImageView);
//            Glide.with(this).load(imageRef).into(imageView);
            expandedNameTextView.setText(recipe.getName());
            expandedDescriptionTextView.setText(recipe.getDescription());
            expandedIngredientsTextView.setText(recipe.getIngredients());

            // Replace the convertView with the expanded view
            return expandedView;
        }

        return convertView;
    }

    private static class ViewHolder {
        CardView cardView;
        ImageView imageView;
        TextView nameTextView;
    }
}