package com.udacity.shoestore.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.R

class ShoeListViewModel : ViewModel() {
    private var _shoes = MutableLiveData<MutableList<Shoe>>(mutableListOf())
    val getShoes: LiveData<MutableList<Shoe>>
        get() = _shoes
    private var _listUpdated = MutableLiveData<Boolean>()
    val listUpdated: LiveData<Boolean>
        get() = _listUpdated

    init {
        _listUpdated.value = false
        //creating 3 shoes
        var sho1: Shoe =
            Shoe("Shoe1", 36.5, "company1", "for women", listOf(R.drawable.sneakerwomen.toString()))
        var sho2: Shoe =
            Shoe("Shoe2", 40.0, "company2", "for men", listOf(R.drawable.shoered.toString()))
        var sho3: Shoe =
            Shoe("Shoe3", 16.5, "company3", "for kids", listOf(R.drawable.shoekids.toString()))
        addShoe(sho1, false)
        addShoe(sho2, false)
        addShoe(sho3, false)
        _listUpdated.value = true
    }

    fun addShoe(shoe: Shoe, update: Boolean) {
        val list = _shoes.value
        list?.let {
            it.add(shoe)
            _shoes.value = list!!
            if (update)
                _listUpdated.value = true
        }

    }

    fun lisUpdateComplete() {
        _listUpdated.value = false
    }
}