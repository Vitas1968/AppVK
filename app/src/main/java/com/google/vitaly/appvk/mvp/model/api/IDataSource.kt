package com.google.vitaly.appvk.mvp.model.api

import com.google.vitaly.appvk.mvp.model.entity.Response
import com.google.vitaly.appvk.mvp.model.entity.wall.WallResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url


// ?user_id=587482032
// &fields=nickname,bdate,city,photo_50
// &access_token=77a76625cf14eb1423883dd8f60865bcd54deba2754ec775ce4b3955a17d3555d7326d21e1201503efd38
// &v=5.103

/*
 @GET("/method/friends.get?user_id={userId}&fields=nickname,bdate,city,photo_50"
            +"&access_token={accessToken}&v=5.103"
    )
    fun getFriends(@Path("user_id") user_id: String, @Path("access_token") access_token: String): Single<GithubUser>
 */
interface IDataSource {

    @GET("/method/friends.get?fields=nickname,bdate,city,photo_50&v=5.103")
    fun getFriends(@Query("user_id") userId: String, @Query("access_token") accessToken: String): Single<Response>

    @GET("/method/wall.get?count=50&v=5.103")
    fun getPostsWall(@Query("owner_id") userId: String, @Query("access_token") accessToken: String): Single<WallResponse>

    /*
    @GET("/users/{user}")
    fun getUser(@Path("user") username: String): Single<GithubUser>

    @GET
    fun getUserRepos(@Url url: String): Single<List<GithubRepository>>

    @GET
    fun getRepoForks(@Url url: String): Single<List<GithubRepository>>

     */
}