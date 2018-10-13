package com.arjuna.matchball.Model.Match

import com.google.gson.annotations.SerializedName


data class MatchResponse(

	@field:SerializedName("events")
	val events: List<Events?>? = null
)