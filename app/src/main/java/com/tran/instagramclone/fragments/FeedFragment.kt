package com.tran.instagramclone.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery
import com.tran.instagramclone.MainActivity
import com.tran.instagramclone.Post
import com.tran.instagramclone.PostAdapter
import com.tran.instagramclone.R


class FeedFragment : Fragment() {

    lateinit var postsRecyclerView: RecyclerView

    lateinit var  adapter: PostAdapter

//    var allPosts: MutableList<Post> = mutableListOf()


    lateinit var swipeContainer: SwipeRefreshLayout
    var allPosts: ArrayList<Post> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //This is where we set up our views and click listeners

        postsRecyclerView = view.findViewById(R.id.postRecyclerView)

        swipeContainer=view.findViewById(R.id.swipeContainer)
        swipeContainer.setOnRefreshListener {
            queryPosts()
        }
            // Your code to refresh the list here.
            // Make sure you call swipeContainer.setRefreshing(false)
            // once the network request has completed successfully.


        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light);


        //Steps to populate REcycler View
        //1. Create layout for each row in list
        //2. Create data source for each row(This is the Post class)
        //3. Create adapter that will bridge data and row layout (PostAdapter)
        //4. Set adapter on Recycler View
        adapter = PostAdapter(requireContext(), allPosts)
        postsRecyclerView.adapter = adapter
        //5. Set layout manager on RecyclerView
        postsRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        queryPosts()
    }

    fun queryPosts(){

        //Specify which class to query
        val query: ParseQuery<Post> = ParseQuery.getQuery(Post::class.java)

        //Find all Post objects
        query.include(Post.KEY_USER)
        query.addDescendingOrder("createdAt")
//        query.setLimit(4)
        query.findInBackground(object: FindCallback<Post> {
            override fun done(posts: MutableList<Post>?, e: ParseException?) {
                if (e!= null){
                    Log.e(TAG,"Error fetching posts")
                } else {
                    if (posts != null) {
                        for (post in posts) {
                            Log.i(TAG,"Post: " + post.getDescription() + ", username: " +
                                    post.getUser()?.username)

                        }
                        adapter.clear()
                        adapter.addAll(posts)
                        adapter.notifyDataSetChanged()
                    }
                    swipeContainer.isRefreshing = false
                }

            }


        })
    }

    companion object {
        const val TAG = "FeedFragment"
    }
}