package com.mixture.common.utils

import android.view.View
import androidx.lifecycle.MutableLiveData

fun MutableLiveData<Trigger>.trigger() {
    this.value = Trigger()
}

fun View.setVisibility(clause: Boolean) {
    visibility = if (clause) View.VISIBLE else View.GONE
}