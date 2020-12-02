package com.example.thebreakingbadapp.data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Character(
    @SerializedName("char_id") val charId: Int,
    @SerializedName("name") val name: String,
    @SerializedName("birthday") val birthday: String,
    @SerializedName("occupation") val occupation: List<String>,
    @SerializedName("img") val img: String,
    @SerializedName("status") val status: String,
    @SerializedName("nickname") val nickname: String,
    @SerializedName("appearance") val appearance: List<String>,
    @SerializedName("portrayed") val portrayed: String,
    @SerializedName("category") val category: String,
    @SerializedName("better_call_saul_appearance") val betterCallSaulAppearance: List<String>
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.createStringArrayList()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.createStringArrayList()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.createStringArrayList()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(charId)
        parcel.writeString(name)
        parcel.writeString(birthday)
        parcel.writeStringList(occupation)
        parcel.writeString(img)
        parcel.writeString(status)
        parcel.writeString(nickname)
        parcel.writeStringList(appearance)
        parcel.writeString(portrayed)
        parcel.writeString(category)
        parcel.writeStringList(betterCallSaulAppearance)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Character> {
        override fun createFromParcel(parcel: Parcel): Character {
            return Character(parcel)
        }

        override fun newArray(size: Int): Array<Character?> {
            return arrayOfNulls(size)
        }
    }
}