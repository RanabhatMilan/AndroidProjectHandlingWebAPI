package com.example.db.blogposts;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Url;

public class BloggerAPI {
    public static final String key = "AIzaSyBPAHH6HHfn62aAwRaNM4u1qSGO9vqORsw";
    public static final String url = "https://www.googleapis.com/blogger/v3/blogs/5368603297511044146/posts/";
    public static PostService postService = null;
    public static PostService getService(){
        if (postService == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            postService = retrofit.create(PostService.class);
        }
        return postService;
    }
    public interface PostService {
        @GET("?key="+key)
        Call<BlogPostList> getPostList();
    }
}
