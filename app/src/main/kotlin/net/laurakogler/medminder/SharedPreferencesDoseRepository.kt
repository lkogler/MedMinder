package net.laurakogler.medminder

import android.content.SharedPreferences

class SharedPreferencesDoseRepository(val sharedPreferences : SharedPreferences) : DoseRepository {

    override fun setDose(lastDoseTime: Long) {
        sharedPreferences.edit().putLong(MainActivity.LAST_DOSE_TIME, lastDoseTime).commit()
    }

    override fun getDose(): Long {
        return sharedPreferences.getLong(MainActivity.LAST_DOSE_TIME, 0)
    }
}
