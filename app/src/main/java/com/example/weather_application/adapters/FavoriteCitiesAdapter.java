package com.example.weather_application.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather_application.R;
import com.example.weather_application.helper.DBHelper;
import com.example.weather_application.ui.activity.MainActivity;

import java.util.List;

public class FavoriteCitiesAdapter extends RecyclerView.Adapter<FavoriteCitiesAdapter.CityViewHolder> {

    private List<String> cities;
    private Context mContext;

    public FavoriteCitiesAdapter(List<String> cities, Context context) {
        this.cities = cities;
        this.mContext = context;
    }

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite_city, parent, false);
        return new CityViewHolder(view,mContext, this);
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder holder, int position) {
        String city = cities.get(position);
        holder.bind(city);
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    static class CityViewHolder extends RecyclerView.ViewHolder {
        private TextView cityNameTextView;
        private Context context;
        private Button btnRemoveCity;
        private DBHelper dbHelper;
        private FavoriteCitiesAdapter adapter;

        public CityViewHolder(@NonNull View itemView, Context context, FavoriteCitiesAdapter adapter) {
            super(itemView);
            cityNameTextView = itemView.findViewById(R.id.text_city_name);
            this.context = context;
            btnRemoveCity = itemView.findViewById(R.id.btn_remove_city);
            this.adapter = adapter;
        }

        public void bind(String cityName) {
            cityNameTextView.setText(cityName);

            btnRemoveCity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        dbHelper = new DBHelper(context);
                        dbHelper.removeFavoriteCity(cityName);
                        adapter.cities.remove(position);
                        adapter.notifyItemRemoved(position);
                    }
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, cityName, Toast.LENGTH_SHORT).show();
                    if (context instanceof MainActivity) {
                        ((MainActivity) context).requestWeather(cityName, true);
                    }
                }
            });
        }
    }
}
