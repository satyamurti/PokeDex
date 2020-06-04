package com.mrspd.pokedex.models.modelspokedex


import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.mrspd.pokedex.adapters.models.modelspokedex.Ability
import com.mrspd.pokedex.adapters.models.modelspokedex.Sprites
import com.mrspd.pokedex.adapters.models.modelspokedex.Stat
import com.mrspd.pokedex.adapters.models.modelspokedex.Type


@Entity(
    tableName = "pokemon"
)
data class Pokeresponse(
    @PrimaryKey
    val pokemonkey: Int? = null,
    @SerializedName("abilities")
    val abilities: List<Ability>?,
    val id: Int,
    val name: String?,
    @SerializedName("sprites")
    val sprites: Sprites?,
    @SerializedName("stats")
    val stats: List<Stat>?,
    @SerializedName("types")
    val types: List<Type>?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.createTypedArrayList(Ability),
        parcel.readInt(),
        parcel.readString(),
        parcel.readParcelable(Sprites::class.java.classLoader),
        parcel.createTypedArrayList(Stat),
        parcel.createTypedArrayList(Type)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(abilities)
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeTypedList(stats)
        parcel.writeParcelable(sprites, flags)
        parcel.writeTypedList(types)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Pokeresponse> {
        override fun createFromParcel(parcel: Parcel): Pokeresponse {
            return Pokeresponse(parcel)
        }

        override fun newArray(size: Int): Array<Pokeresponse?> {
            return arrayOfNulls(size)
        }
    }
}
