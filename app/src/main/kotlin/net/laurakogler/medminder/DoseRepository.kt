package net.laurakogler.medminder

interface DoseRepository {
    fun setDose(lastDoseTime: Long)
    fun getDose() : Long
}