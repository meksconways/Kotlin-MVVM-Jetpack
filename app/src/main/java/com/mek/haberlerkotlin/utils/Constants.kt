package com.mek.haberlerkotlin.utils


/** The base URL of the API */
const val BASE_URL: String = "https://api.hurriyet.com.tr/v1/"
/**
 * api keyimiz <3
 */
const val API_KEY: String = "5c7a70e17a1a4b3b9410bc3016e29036"

/**
 * haber pathleri
 */
const val DUNYA_PATH: String = "/dunya/"
const val GUNDEM_PATH: String = "/gundem/"
const val SPOR_PATH: String = "/spor/"
const val EKONOMI_PATH: String = "/ekonomi/"
const val AVRUPA_PATH: String = "/avrupa/"
const val MAGAZIN_PATH: String = "/magazin-haberleri/"
const val EGITIM_PATH: String = "/egitim/"

var TITLE: String = "Haberler"


var fromChildFragment: Boolean = false


class Helper {

    companion object {

        fun pathParse(path: String): String = path.split("/")[1]


        var month = HashMap<Int, String>()
        private fun createHashMap() {
            if (month.size == 0) {
                month[1] = "Ocak"
                month[2] = "Şubat"
                month[3] = "Mart"
                month[4] = "Nisan"
                month[5] = "Mayıs"
                month[6] = "Haziran"
                month[7] = "Temmuz"
                month[8] = "Ağuston"
                month[9] = "Eylül"
                month[10] = "Ekim"
                month[11] = "Kasım"
                month[12] = "Aralık"
            }
        }

        fun dateParse(date: String): String {

            createHashMap()
            val ay = date.substring(5, 7)
            val gun = date.substring(8, 10)
            val yil = date.substring(0, 4)
            val saat = date.substring(11, 16)
            return gun + " " + month[Integer.parseInt(ay)] + " " + yil + " " + saat

        }

    }

}