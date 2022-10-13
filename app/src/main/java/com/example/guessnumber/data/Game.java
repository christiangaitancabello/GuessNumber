package com.example.guessnumber.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Game implements Parcelable {
    // Variable de tipo boolean, True si el usuario acierta el nToGuess, False en caso contrario.
    private Boolean win = false;
    // Variable de tipo Integer que guarda el número a adivinar por el usuario.
    private Integer nToGuess;
    // Variable de tipo Integer que guarda el número de intentos que lleva el usuario intentando adivinar el número.
    private Integer nTries;

    public Game() {
    }

    public Game(Boolean win, Integer nToGuess, Integer nTries) {
        this.win = win;
        this.nToGuess = nToGuess;
        this.nTries = nTries;
    }

    public Boolean getWin() {
        return win;
    }

    public void setWin(Boolean win) {
        this.win = win;
    }

    public Integer getnToGuess() {
        return nToGuess;
    }

    public void setnToGuess(Integer nToGuess) {
        this.nToGuess = nToGuess;
    }

    public Integer getnTries() {
        return nTries;
    }

    public void setnTries(Integer nTries) {
        this.nTries = nTries;
    }

    //region MÉTODOS INTERFAZ PARCELABLE

    protected Game(Parcel in) {
        byte tmpWin = in.readByte();
        win = tmpWin == 0 ? null : tmpWin == 1;
        if (in.readByte() == 0) {
            nToGuess = null;
        } else {
            nToGuess = in.readInt();
        }
        if (in.readByte() == 0) {
            nTries = null;
        } else {
            nTries = in.readInt();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (win == null ? 0 : win ? 1 : 2));
        if (nToGuess == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(nToGuess);
        }
        if (nTries == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(nTries);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Game> CREATOR = new Creator<Game>() {
        @Override
        public Game createFromParcel(Parcel in) {
            return new Game(in);
        }

        @Override
        public Game[] newArray(int size) {
            return new Game[size];
        }
    };

    //endregion
}
