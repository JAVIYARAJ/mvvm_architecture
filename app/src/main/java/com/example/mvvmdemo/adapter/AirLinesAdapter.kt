package com.example.mvvmdemo.adapter

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmdemo.R
import de.hdodenhof.circleimageview.CircleImageView

class AirLinesAdapter(
    private var list: ArrayList<com.example.mvvmdemo.model.AirLinesListItem>,
    private var context: Context
) : RecyclerView.Adapter<AirLinesAdapter.MyViewHolder>() {
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var countryName = view.findViewById<TextView>(R.id.countryName);
        var airLineName = view.findViewById<TextView>(R.id.airLineName);
        var airLineSlogan = view.findViewById<TextView>(R.id.airLineSlogan);
        var airLineLogo = view.findViewById<ImageView>(R.id.airLineImage);
        var airLineDate = view.findViewById<TextView>(R.id.airLineDate);
        var airLineHeadQuater = view.findViewById<TextView>(R.id.airLineHeadQuater);

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view =
            LayoutInflater.from(context).inflate(R.layout.custom_airline_layout, parent, false);
        return MyViewHolder(view);
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var item = list[position];
        holder.airLineName.text = item.name;
        holder.airLineSlogan.text = item.slogan;
        holder.countryName.text = item.country;
        holder.airLineDate.text = item.established;
        holder.airLineHeadQuater.text = item.head_quaters;

        Glide.with(context)
            .load(Uri.parse("https://upload.wikimedia.org/wikipedia/en/thumb/9/9b/Qatar_Airways_Logo.svg/300px-Qatar_Airways_Logo.svg.png")).into(holder.airLineLogo);

    }

    override fun getItemCount(): Int {
        return list.size;
    }
}