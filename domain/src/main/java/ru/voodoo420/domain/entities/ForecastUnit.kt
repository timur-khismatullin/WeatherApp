package ru.voodoo420.domain.entities

import android.os.Parcel
import android.os.Parcelable

data class ForecastUnit(
    val dateTime: String?,
    val temperature: Float,
    val icon: String?,
    val description: String?,
    val humidity: Int,
    val feelsLike: Float,
    val min: Float,
    val max: Float,
    val wind: Float,
    val pressure: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readFloat(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readFloat(),
        parcel.readFloat(),
        parcel.readFloat(),
        parcel.readFloat(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(dateTime)
        parcel.writeFloat(temperature)
        parcel.writeString(icon)
        parcel.writeString(description)
        parcel.writeInt(humidity)
        parcel.writeFloat(feelsLike)
        parcel.writeFloat(min)
        parcel.writeFloat(max)
        parcel.writeFloat(wind)
        parcel.writeInt(pressure)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ForecastUnit> {
        override fun createFromParcel(parcel: Parcel): ForecastUnit {
            return ForecastUnit(parcel)
        }

        override fun newArray(size: Int): Array<ForecastUnit?> {
            return arrayOfNulls(size)
        }
    }
}