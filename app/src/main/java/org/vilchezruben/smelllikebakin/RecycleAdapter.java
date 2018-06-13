package org.vilchezruben.smelllikebakin;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public abstract class RecycleAdapter extends RecyclerView.Adapter{


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getLayoutId(),parent,false);
        return new ListViewHolder(view);
    }

    protected abstract int getLayoutId();


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ListViewHolder) holder).bindView(position);

    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return Recipes.names.length;
    }

    private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mTextView;
        private ImageView mImageView;
        private int mIndex;

        public ListViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.itemText);
            mImageView = itemView.findViewById(R.id.itemImage);
            itemView.setOnClickListener(this);
        }

        public void bindView(int position){
            mIndex = position ;
            mTextView.setText(Recipes.names[position]);
            mImageView.setImageResource(Recipes.resourceIds[position]);
        }

        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {
            onRecipeSelected(mIndex);
        }
    }

    protected abstract void onRecipeSelected(int index);
}
