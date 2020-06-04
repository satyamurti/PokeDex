package com.mrspd.pokedex.adapters.models.modeltypes

import android.os.Parcel
import android.os.Parcelable

data class TypesResponse(
//    val damage_relations: DamageRelations,
//    val game_indices: List<GameIndice>,
//    val generation: GenerationX,
//    val id: Int,
//    val move_damage_class: MoveDamageClass,
//    val moves: List<Move>,
    val name: String?,
//    val names: List<Name>,
    val pokemon: List<Pokemon>?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.createTypedArrayList(Pokemon)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeTypedList(pokemon)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TypesResponse> {
        override fun createFromParcel(parcel: Parcel): TypesResponse {
            return TypesResponse(parcel)
        }

        override fun newArray(size: Int): Array<TypesResponse?> {
            return arrayOfNulls(size)
        }
    }
}