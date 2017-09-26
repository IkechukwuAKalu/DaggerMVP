package com.ikechukwuakalu.daggermvp.data.models

data class User(var id: Long, var login: String, var avatar_url: String,
                var url: String, var name: String = "", var location: String = "",
                var bio : String = "", var public_repos : String = "")