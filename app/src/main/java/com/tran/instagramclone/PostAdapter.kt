package com.tran.instagramclone

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class PostAdapter(val context: Context, val posts: ArrayList<Post>): RecyclerView.Adapter<PostAdapter.viewHolder> (){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostAdapter.viewHolder {
        //Specify layout file to use for this item



        val view = LayoutInflater.from(context).inflate(R.layout.item_post,parent,false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: PostAdapter.viewHolder, position: Int) {
        val post = posts.get(position)
        holder.bind(post)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvUserName : TextView
        val ivImage : ImageView
        val tvDescription: TextView

        init {
            tvUserName = itemView.findViewById(R.id.tvUserName)
            ivImage = itemView.findViewById(R.id.ivImage)
            tvDescription = itemView.findViewById(R.id.tvDescription)
        }

        fun bind(post: Post){
            tvDescription.text = post.getDescription()
            tvUserName.text = post.getUser()?.username

            Glide.with(itemView.context).load(post.getImage()?.url).into(ivImage)

        }
    }

    fun clear() {
        posts.clear()
        notifyDataSetChanged()
    }

    // Add a list of items -- change to type used
    fun addAll(PostList: List<Post>) {
        posts.addAll(PostList)
        notifyDataSetChanged()
    }
}