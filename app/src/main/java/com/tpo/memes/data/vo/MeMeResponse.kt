package com.tpo.memes.data.vo

import java.io.Serializable

data class MeMeResponse(
    var success: Boolean,
    var data: MeMeList
) : Serializable
{
    data class MeMeList(
        var memes: List<MemeData>
    ) : Serializable
}


