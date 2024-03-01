package com.mk.api.di.assessment.response

import com.google.gson.annotations.SerializedName

data class AllAnimalsResponse(
    val animals: List<AnimalsResponse?>,
    val pagination: AnimalsPaginationResponse?
)

data class AnimalsResponse(
    val id: Int?,
    @SerializedName("organization_id")
    val orgId: String?,
    val url: String?,
    val type: String?,
    val species: String?,
    val breeds: AnimalBreedResponse?,
    val colors: AnimalColorResponse?,
    val age: String?,
    val gender: String?,
    val size: String?,
    val coat: String?,
    val name: String?,
    val description: String?,
    val photos: List<AnimalPhotosResponse?>,
    val videos: List<AnimalVideosResponse?>,
    val status: String?,
    val attributes: AnimalAttributesResponse?,
    val environment: AnimalEnvironmentResponse?,
    val tags: List<String?>,
    val contact: AnimalContactResponse?,
    @SerializedName("published_at")
    val published: String?,
)

data class AnimalAttributesResponse(
    @SerializedName("spayed_neutered")
    val spayedNeutered: Boolean?,
    @SerializedName("house_trained")
    val houseTrained: Boolean?,
    val declawed: Boolean?,
    @SerializedName("special_needs")
    val specialNeeds: Boolean?,
    @SerializedName("shots_current")
    val shotsUpToDate: Boolean?,
)

data class AnimalEnvironmentResponse(
    val children: Boolean?,
    val dogs: Boolean?,
    val cats: Boolean?,
)

data class AnimalBreedResponse(
    val primary: String?,
    val secondary: String?,
    val mixed: String?,
    val unknown: String?,
)

data class AnimalColorResponse(
    val primary: String?,
    val secondary: String?,
    val tertiary: String?,
)

data class AnimalPhotosResponse(
    val small: String?,
    val medium: String?,
    val large: String?,
    val full: String?
)

data class AnimalVideosResponse(
    val embed: String?
)

data class AnimalsPaginationResponse(
    @SerializedName("count_per_page")
    val countPerPage: Int?,
    @SerializedName("total_count")
    val totalCount: Int?,
    @SerializedName("current_page")
    val currentPage: Int?,
    @SerializedName("total_pages")
    val totalPages: Int?,
)

data class AnimalContactResponse(
    val email: String?,
    val phone: String?,
    val address: AnimalAddressResponse?,
)

data class AnimalAddressResponse(
    val address1: String?,
    val address2: String?,
    val city: String?,
    val state: String?,
    val postcode: String?,
    val country: String?,
)

data class AllAnimalsModel (
    val animals: List<AnimalsModel>,
    val pagination: AnimalsPaginationModel
)

data class AnimalsModel(
    val id: Int,
    @SerializedName("organization_id")
    val orgId: String,
    val url: String,
    val type: String,
    val species: String,
    val breeds: AnimalBreedModel,
    val colors: AnimalColorModel,
    val age: String,
    val gender: String,
    val size: String,
    val coat: String?,
    val name: String,
    val description: String?,
    val status: String,
    val attributes: AnimalAttributesModel,
    val environment: AnimalEnvironmentModel,
)

data class AnimalBreedModel(
    val primary: String?,
    val secondary: String?,
    val mixed: String?,
    val unknown: String?,
)

data class AnimalColorModel(
    val primary: String?,
    val secondary: String?,
    val tertiary: String?,
)

data class AnimalsPaginationModel(
    val countPerPage: Int,
    val totalCount: Int,
    val currentPage: Int,
    val totalPages: Int,
)

data class AnimalPhotosModel(
    val small: String,
    val medium: String,
    val large: String,
    val full: String
)

data class AnimalVideosModel(
    val embed: String
)

data class AnimalAttributesModel(
    val spayedNeutered: Boolean,
    val houseTrained: Boolean,
    val declawed: Boolean,
    val specialNeeds: Boolean,
    val shotsUpToDate: Boolean,
)

data class AnimalEnvironmentModel(
    val children: Boolean,
    val dogs: Boolean,
    val cats: Boolean,
)

data class AnimalContactModel(
    val email: String,
    val phone: String,
    val address: AnimalAddressResponse,
)

data class AnimalAddressModel(
    val address1: String?,
    val address2: String?,
    val city: String,
    val state: String,
    val postcode: String,
    val country: String,
)