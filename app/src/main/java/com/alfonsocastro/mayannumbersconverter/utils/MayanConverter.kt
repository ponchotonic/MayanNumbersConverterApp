package com.alfonsocastro.mayannumbersconverter.utils

import java.lang.IndexOutOfBoundsException
import kotlin.math.pow

class MayanConverter {

    companion object {

        @Suppress("SpellCheckingInspection")
        private const val BASE_20_CHARACTERS = "0123456789ABCDEFGHIJK"

        fun convertToBase20(decimalToConvert: Int): String {
            var mayanString = ""
            var decimal = decimalToConvert

            do {

                mayanString = decimalToBase20Character(decimal % 20).toString() + mayanString
                decimal /= 20
            } while (decimal > 0)

            return mayanString
        }

        fun convertToDecimal(base20: String): Int {
            var decimal = 0.0
            val base20Reversed = base20.reversed()
            for ( i in 0..base20Reversed.lastIndex) {
                val digit = base20ToDecimalCharacter(base20Reversed[i])
                decimal += (digit * 20.0.pow(i))
            }
            return decimal.toInt()
        }

        private fun base20ToDecimalCharacter(base20: Char): Int {
            val decimal = BASE_20_CHARACTERS.indexOf(base20)
            return if (decimal >= 0){
                decimal
            } else{
                throw IndexOutOfBoundsException("Base20 string: $base20. Decimal: $decimal")
            }
        }

        private fun decimalToBase20Character(decimal: Int): Char? {
            val base20Character: Char
            if (decimal>20 || decimal<0) {
                return null
            } else {
                base20Character = BASE_20_CHARACTERS[decimal]
            }
            /* var base20Character: Char = decimal.toChar()
            if (decimal > 9) {
                base20Character = base20Characters[decimal]
            } */
            return base20Character
        }


    }
}