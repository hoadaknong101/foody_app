package hcmute.edu.vn.phamdinhquochoa.foodyapp.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import hcmute.edu.vn.phamdinhquochoa.foodyapp.R;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.beans.Category;

public class CategoryCard extends LinearLayout {

    private ImageView image;
    private TextView tvName;
    private Category category;

    public CategoryCard(Context context) {
        super(context);
        initControl(context);
    }

    public CategoryCard(Context context, Category category){
        super(context);
        this.category = category;
        initControl(context);
    }

    private void initControl(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.category_card, this);

        image = findViewById(R.id.imageCategory);
        tvName = findViewById(R.id.tvCategoryName);

        // Set name for category cart
        tvName.setText(category.getName());
    }
}
