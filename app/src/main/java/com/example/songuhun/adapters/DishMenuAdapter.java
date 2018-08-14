package com.example.songuhun.adapters;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.songuhun.myapplication.R;
import com.example.songuhun.objects.Dish;
import com.example.songuhun.objects.DishCategory;

import java.util.ArrayList;

/**
 * Created by user on 2/27/16.
 */
public class DishMenuAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<DishCategory> dishCategoryList;
    private ArrayList<DishCategory> originalList;
    private View.OnClickListener clickListener;

    public DishMenuAdapter(Context context
            , ArrayList<DishCategory> originalList) {
        this(context,
             originalList,
             new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext()
                            , ((TextView)v).getText()
                            , Toast.LENGTH_SHORT).show();
                }
            });
    }

    public DishMenuAdapter(Context context,
                           ArrayList<DishCategory> originalList,
                           View.OnClickListener clickListener) {
        this.context = context;
        this.dishCategoryList = new ArrayList<>();
        this.dishCategoryList.addAll(originalList);
        this.originalList = new ArrayList<>();
        this.originalList.addAll(originalList);
        this.clickListener = clickListener;
    }

    @Override
    public int getGroupCount() {
        return dishCategoryList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return dishCategoryList.get(groupPosition).getChildList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return dishCategoryList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return dishCategoryList.get(groupPosition).getChildList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        DishCategory dishCategory = (DishCategory) getGroup(groupPosition);

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.dishes_expandable_list_parent_row, null);
        }

        TextView heading = convertView.findViewById(R.id.parent_text);

        heading.setText(dishCategory.getName().trim());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        Dish dish = (Dish) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.dishes_expandable_list_child_row, null);
        }

        ImageView childIcon = convertView.findViewById(R.id.child_icon);
        childIcon.setImageResource(dish.getIcon());

        final DishView childText =  convertView.findViewById(R.id.child_text);
        childText.setDish(dish);

        childText.setText(dish.getName().trim());
        childText.setOnClickListener(clickListener);

        return convertView;
    }

    public static class DishView extends android.support.v7.widget.AppCompatTextView{

        public DishView(Context context) {
            super(context);
            dish = null;
        }

        public DishView(Context context, AttributeSet attrs) {
            super(context, attrs);
            dish = null;
        }

        public DishView(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            dish = null;
        }

        private Dish dish;

        public void setDish(Dish dish) {
            this.dish = dish;
        }

        public Dish getDish() {
            return dish;
        }
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void filterData(String query) {
        query = query.toLowerCase();
        dishCategoryList.clear();

        if (query.isEmpty()) {
            dishCategoryList.addAll(originalList);
        }
        else {
            for (DishCategory dishCategory : originalList) {
                ArrayList<Dish> childList = dishCategory.getChildList();
                ArrayList<Dish> newList = new ArrayList<>();

                for (Dish dish : childList) {
                    if (dish.getName().toLowerCase().contains(query)) {
                        newList.add(dish);
                    }
                }
                if (newList.size() > 0) {
                    DishCategory nDishCategory = new DishCategory(dishCategory.getName(), newList);
                    dishCategoryList.add(nDishCategory);
                }
            }
        }

        notifyDataSetChanged();
    }
}
