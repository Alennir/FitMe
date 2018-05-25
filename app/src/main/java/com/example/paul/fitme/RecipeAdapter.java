package com.example.paul.fitme;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Paul on 25/05/2018.
 */

public class RecipeAdapter extends  RecyclerView.Adapter<RecipeAdapter.RecipeHolder> {

        private List<RecipeEntity> recipeArray;

        public RecipeAdapter(List<RecipeEntity> recipeArray){
            this.recipeArray = recipeArray;
        }

        @Override
        public RecipeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_support, parent, false);
            RecipeHolder holder = new RecipeHolder(view);
            return holder;
        }

    @Override
        public void onBindViewHolder(RecipeHolder holder, int position) {
        String name = recipeArray.get(position).getName();
        String url = recipeArray.get(position).getUrl();
        String img =recipeArray.get(position).getImage();

        holder.changeAll(name,url,img);

    }

    @Override
    public int getItemCount() {
        return recipeArray.size();
    }

    public void setNewRecette(List<RecipeEntity> array){
            this.recipeArray = array;
            notifyDataSetChanged();
        }

        class RecipeHolder extends RecyclerView.ViewHolder {
            private TextView tv_name, tv_url;
            private ImageView iv_image;

            public RecipeHolder(View itemView) {
                super(itemView);
                this.tv_name = itemView.findViewById(R.id.rv_name);
                this.tv_url = itemView.findViewById(R.id.rv_url);
                this.iv_image = itemView.findViewById(R.id.rv_image);
            }

            private void changeAll(String name, String url, String img){
                String link = "Voir la recette <a href="+url+"> ici </a>.";
                tv_name.setText(name);
                Glide.with(itemView).load(img).into(iv_image);
                tv_url.setMovementMethod(LinkMovementMethod.getInstance());
                tv_url.setText(Html.fromHtml(link));
            }
        }

}
