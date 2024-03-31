package com.example.myshelf.objects;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Grocery implements Parcelable {

    public Grocery(@NonNull String groceryName) {
        this.groceryName = groceryName;
    }

    @PrimaryKey(autoGenerate = true)
    private int groceryId;

    @NonNull
    @ColumnInfo(name="grocery_name")
    private String groceryName;

    // Constructor used for Parcelable
    protected Grocery(Parcel in) {
        groceryId = in.readInt();
        groceryName = in.readString();
    }

    public static final Creator<Grocery> CREATOR = new Creator<Grocery>() {
        @Override
        public Grocery createFromParcel(Parcel in) {
            return new Grocery(in);
        }

        @Override
        public Grocery[] newArray(int size) {
            return new Grocery[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(groceryId);
        parcel.writeString(groceryName);
    }

}
