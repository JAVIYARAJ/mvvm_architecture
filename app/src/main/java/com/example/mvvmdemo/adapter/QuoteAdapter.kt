package com.example.mvvmdemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmdemo.R
import com.example.mvvmdemo.model.AirLinesListItem
import com.example.mvvmdemo.model.QuoteList

class QuoteAdapter(
    private var list: ArrayList<com.example.mvvmdemo.model.Result>,
    private var context: Context
) : RecyclerView.Adapter<QuoteAdapter.MyViewHolder>() {
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var quote = view.findViewById<TextView>(R.id.quote);
        var author = view.findViewById<TextView>(R.id.author);
        var date = view.findViewById<TextView>(R.id.date);

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view =
            LayoutInflater.from(context).inflate(R.layout.custom_quote_layout, parent, false);
        return MyViewHolder(view);
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var item = list[position];
        holder.quote.text = item.content;
        holder.author.text = item.author;
        holder.date.text=item.dateAdded ?: "26/04/2023";

    }

    override fun getItemCount(): Int {
        return list.size;
    }
}