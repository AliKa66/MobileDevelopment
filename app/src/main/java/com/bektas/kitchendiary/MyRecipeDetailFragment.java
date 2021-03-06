package com.bektas.kitchendiary;

import com.bektas.kitchendiary.model.Recipe;
import com.bektas.kitchendiary.util.GlideUtil;
import com.bektas.kitchendiary.util.MyRecipes;
import com.bektas.kitchendiary.util.TimeParser;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * A fragment representing a single Recipe detail screen.
 * This fragment is either contained in a {@link MyRecipeListActivity}
 * in two-pane mode (on tablets) or a {@link MyRecipeDetailActivity}
 * on handsets.
 */
public class MyRecipeDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String RECIPE_INDEX = "item_index";

    private Recipe mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MyRecipeDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(RECIPE_INDEX)) {
            mItem = MyRecipes.getByIndex(getArguments().getInt(RECIPE_INDEX));
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.my_recipe_detail, container, false);

        if (mItem != null) {
            ImageView image = rootView.findViewById(R.id.image);
            TextView txtName = rootView.findViewById(R.id.txtName);
            TextView txtPrepTime = rootView.findViewById(R.id.txtPreparationTime);
            TextView txtCookTime = rootView.findViewById(R.id.txtCookingTime);
            LinearLayout layoutIngredients = rootView.findViewById(R.id.linearLayout_ingredients);

            txtName.setText(mItem.getTitle());
            txtPrepTime.setText(TimeParser.fromTotalMinute(mItem.getPreparationTime()).getTimeToDisplay());
            txtCookTime.setText(TimeParser.fromTotalMinute(mItem.getCookingTime()).getTimeToDisplay());
            List<String> ingredients = mItem.getIngredients();
            for (int i = 0; i < ingredients.size(); i++) {
                TextView text = new TextView(getContext());
                text.setText(String.format("%d. %s", i+1, ingredients.get(i)));
                layoutIngredients.addView(text);
            }
            GlideUtil.showImage(mItem.getImageUrl(), rootView.getContext(), image);
        }

        return rootView;
    }

}
