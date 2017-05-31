package com.thunder.learnkotlin

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.RadioButton
import android.widget.TextView
import kotlinx.android.synthetic.main.row.view.*

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private val data: MutableList<String> = mutableListOf()
    private var isSelectionModeOn: Boolean = false

    init {
        (1..20).mapTo(data) { "Item $it" }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.ViewHolder? {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (isSelectionModeOn) {
//            holder.viewRadioButton.visibility = View.VISIBLE
            val anim: AlphaAnimation = AlphaAnimation(0.0f, 1.0f)
            anim.duration = 300

            anim.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationRepeat(animation: Animation?) { }

                override fun onAnimationEnd(animation: Animation?) {
                    holder.viewRadioButton.visibility = View.VISIBLE
                }

                override fun onAnimationStart(animation: Animation?) { }

            })

            holder.viewRadioButton.startAnimation(anim)

        } else {
            if (holder.viewRadioButton.visibility != View.INVISIBLE) {
                val anim: AlphaAnimation = AlphaAnimation(1.0f, 0.0f)

                anim.duration = 300

                anim.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationRepeat(animation: Animation?) { }

                    override fun onAnimationEnd(animation: Animation?) {
                        holder.viewRadioButton.visibility = View.INVISIBLE
                    }

                    override fun onAnimationStart(animation: Animation?) { }

                })

                holder.viewRadioButton.startAnimation(anim)
            }
        }

        holder.viewTextView.text = data[position]
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun triggerSelectionMode() {
        isSelectionModeOn = !isSelectionModeOn
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val viewTextView: TextView = itemView.text
        val viewRadioButton: RadioButton = itemView.radio
    }
}
