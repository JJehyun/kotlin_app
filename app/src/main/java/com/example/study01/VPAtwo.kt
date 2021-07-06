package com.example.study01

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class VPAtwo(private val data:Array<String>) : RecyclerView.Adapter<VPAtwo.PagerViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        return PagerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_pagerbm, parent, false))
    }                                             //view pager2를 한장씩 구현할 layout 이름 (res > layout > view_pager)

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.bind(data[position])
    }

    inner class PagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val textView:TextView = itemView.findViewById(R.id.textView)
        //view_pager에서 side마다 변경할 text view id값
        fun bind(data:String){
            textView.text = data
        }
    }
}