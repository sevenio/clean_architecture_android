package com.example.mobile_ui.browse

import android.support.v7.widget.RecyclerView
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mobile_ui.R
import com.example.mobile_ui.model.Project
import javax.inject.Inject

class BrowseAdapter @Inject constructor() : RecyclerView.Adapter<BrowseAdapter.ViewHolder>() {

    var projects: List<Project> = arrayListOf()
    var projectListener: ProjectListener? = null

    override fun getItemCount(): Int {
        return projects.count()
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val project = projects[position]
        holder?.ownerNameText?.text = project.ownerName
        holder?.projectNameText?.text = project.fullName
        Glide.with(holder?.itemView?.context)
                .load(project.ownerAvatar)
                .apply(RequestOptions.circleCropTransform())
                .into(holder?.avatarImage)
        val startResource = if (project.isBookmarked) {
            R.drawable.ic_star_black_24dp
        } else {
            R.drawable.ic_star_border_black_24dp
        }
        holder?.bookmarkedImage?.setImageResource(startResource)
        holder?.itemView?.setOnClickListener {
            if (project.isBookmarked) {
                projectListener?.onBookmarkedProjectClicked(project.id)
            } else {
                projectListener?.onProjectClicked(project.id)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
                .from(parent?.context)
                .inflate(R.layout.item_project, parent, false)
        return ViewHolder(itemView)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var avatarImage: ImageView
        var projectNameText: TextView
        var bookmarkedImage: ImageView
        var ownerNameText: TextView

        init {
            avatarImage = view.findViewById(R.id.image_owner_avatar)
            bookmarkedImage = view.findViewById(R.id.image_bookmarked)
            projectNameText = view.findViewById(R.id.text_project_name)
            ownerNameText = view.findViewById(R.id.text_owner_name)


        }
    }

}