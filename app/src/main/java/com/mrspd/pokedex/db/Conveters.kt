package com.mrspd.pokedex.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mrspd.pokedex.adapters.models.modelspokedex.Ability
import com.mrspd.pokedex.adapters.models.modelspokedex.Sprites
import com.mrspd.pokedex.adapters.models.modelspokedex.Stat
import com.mrspd.pokedex.adapters.models.modelspokedex.Type


class Conveters {

    @TypeConverter
    fun getAbility(ability: List<Ability>): String {

        val gson = Gson()
        val type: java.lang.reflect.Type? = object :
            TypeToken<List<Ability?>?>() {}.type
        return gson.toJson(ability, type)
    }

    @TypeConverter
    fun setAbility(ability: String): List<Ability> {
        val gson = Gson()
        val type: java.lang.reflect.Type? = object :
            TypeToken<List<Ability?>?>() {}.type
        return gson.fromJson(ability, type)
    }

    @TypeConverter
    fun getSprites(sprites: Sprites): String {
        val gson = Gson()
        val type: java.lang.reflect.Type? = object :
            TypeToken<Sprites?>() {}.type
        return gson.toJson(sprites, type)
    }

    @TypeConverter
    fun setSprites(sprites: String): Sprites {
        val gson = Gson()
        val type: java.lang.reflect.Type? = object :
            TypeToken<Sprites?>() {}.type
        return gson.fromJson(sprites, type)
    }

    @TypeConverter
    fun getStat(stat: List<Stat>): String {
        val gson = Gson()
        val type: java.lang.reflect.Type? = object :
            TypeToken<List<Stat?>?>() {}.type
        return gson.toJson(stat, type)
    }

    @TypeConverter
    fun setStat(name: String): List<Stat> {
        val gson = Gson()
        val type: java.lang.reflect.Type? = object :
            TypeToken<List<Stat?>?>() {}.type
        return gson.fromJson(name, type)
    }

    @TypeConverter
    fun getType(stat: List<Type>): String {
        val gson = Gson()
        val type: java.lang.reflect.Type? = object :
            TypeToken<List<Type?>?>() {}.type
        return gson.toJson(stat, type)
    }

    @TypeConverter
    fun setType(name: String): List<Type> {
        val gson = Gson()
        val type: java.lang.reflect.Type? = object :
            TypeToken<List<Type?>?>() {}.type
        return gson.fromJson(name, type)
    }

    ///////////////////////////////////////////////////////////////////////////
    //  @TypeConverter
    //    fun getType(type: Type): String {
    //        return type.type?.name.toString()
    //    }
    //
    //    @TypeConverter
    //    fun setType(name: String): TypeX {
    //        return TypeX(name, name)
    //    }
    ///////////////////////////////////////////////////////////////////////////


}