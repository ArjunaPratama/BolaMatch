package com.arjuna.matchball.Model.Club

import com.google.gson.annotations.SerializedName

data class ClubResponse(

	@field:SerializedName("teams")
	val teams: List<TeamsItem?>? = null
)