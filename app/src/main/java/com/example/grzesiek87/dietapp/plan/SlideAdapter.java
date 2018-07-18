package com.example.grzesiek87.dietapp.plan;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.grzesiek87.dietapp.*;
import com.example.grzesiek87.dietapp.activities.AddToDishActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter dla ViewPagera, wewnatrz jego jest RecyclerView
 */
public class SlideAdapter extends PagerAdapter {
    private Context context;
    private LayoutInflater inflater;
    private TextView pora, poraDesc,prot,fats,carb;
    public TextView kcal;
    private Button  addDishButton;
    private int kalorie = 0;

    public String[] lst_dzien = {
            "Śniadanie",
            "Drugie Śniadanie",
            "Obiad",
            "Podwieczorek",
            "Kolacja"};
    public String[] lst_desc = {"Sniadanie", "Drugie", "Obiad", "Podwieczorek", "Kolacja"};
    public List<List<Product>> produkty = new ArrayList<>();
    public List<List<String>> strings = new ArrayList<>();

    public List<Product> jeden,dwa,trzy,cztery,piec = new ArrayList<>();

    public void setKcal(TextView kcal) {
        this.kcal = kcal;
    }

    public TextView getKcal() {
        return kcal;
    }

    public SlideAdapter(Context context) {
        this.context = context;
        produkty.add(jeden);
        produkty.add(dwa);
        produkty.add(trzy);
        produkty.add(cztery);
        produkty.add(piec);

}

    public void setProdukty(int index,List<Product> produkty) {
        this.produkty.set(index,produkty);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (lst_dzien != null) {
            return lst_dzien.length;
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == (LinearLayout) object);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout) object);

    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.slide, container, false);

        //RecyclerView
        RecyclerView mRecyclerView;
        SlideRecyclerViewAdapter mAdapter;

        RecyclerView.LayoutManager mLayoutManager;

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new SlideRecyclerViewAdapter(context);
        mAdapter.setmProducts((ArrayList<Product>) produkty.get(position));

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));

        LinearLayout layoutslide = view.findViewById(R.id.slideLinearLayout);

        addDishButton = view.findViewById(R.id.addDishButton);
        addDishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), AddToDishActivity.class);
                intent.putExtra("EXTRA_SESSION_ID",Integer.toString(position+1));
                context.startActivity(intent);
            }
        });

        pora = view.findViewById(R.id.pora);
        poraDesc = view.findViewById(R.id.poraDesc);
        pora.setText(lst_dzien[position]);
        kcal = view.findViewById(R.id.textViewKcal);
        carb = view.findViewById(R.id.textViewCarb);
        fats = view.findViewById(R.id.textViewFats);
        prot = view.findViewById(R.id.textViewProt);

        if(produkty != null)
            if (produkty.get(position) != null)
                if(produkty.size() > 0)
                    if(produkty.get(position).size() > 0)
                        if(produkty.get(position).get(0) != null) {
                            double kalorie=0,wegle=0,tluszcze=0,bialka=0;
                            for(int i = 0 ; i < produkty.get(position).size() ; i++) {
                                kalorie += produkty.get(position).get(i).getCalories();
                                wegle += produkty.get(position).get(i).getCarbohydrates();
                                tluszcze += produkty.get(position).get(i).getFats();
                                bialka += produkty.get(position).get(i).getProteins();
                            }
                                kcal.setText(Double.toString(kalorie));
                                carb.setText(Double.toString(wegle));
                                fats.setText(Double.toString(tluszcze));
                                prot.setText(Double.toString(bialka));
                        }
        container.addView(view);
        return view;

    }

}
