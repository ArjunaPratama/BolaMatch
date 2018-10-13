package com.arjuna.matchball.Model.Club

import com.google.gson.annotations.SerializedName


data class TeamsItem(

	@SerializedName("idTeam") val idTeam: String,
	@SerializedName("strTeamBadge") val teamBadge: String,
	@SerializedName("strTeam") val strTeam: String
)