package com.example.bmta.view

class Constants() {
    companion object {
        @JvmField
        var SCREEN_WIDTH : Int = 100

        @JvmStatic
        fun setScreenWidth(value: Int) {
            SCREEN_WIDTH = value
        }

        @JvmField
        var SCREEN_HEIGHT : Int = 100

        @JvmStatic
        fun setScreenHeight(value: Int) {
            SCREEN_HEIGHT = value
        }
    }

}