package com.udacity.shoestore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentAddShoeBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.ShoeListViewModel
import timber.log.Timber

class AddShoeFragment : Fragment() {
    private lateinit var myModelView: ShoeListViewModel
    private lateinit var binding: FragmentAddShoeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_shoe, container, false)
        binding.btnCancel.setOnClickListener { v: View ->
            findNavController().navigate(AddShoeFragmentDirections.actionAddShoeFragmentToShoeListFragment())

        }
        binding.btnSave.setOnClickListener { v: View ->
            addShoe()
            findNavController().navigate(AddShoeFragmentDirections.actionAddShoeFragmentToShoeListFragment())
        }
        myModelView = ViewModelProvider(requireActivity()).get(ShoeListViewModel::class.java)
        return binding.root
    }

    private fun addShoe() {
        var nam: String = binding.txtAddShoeName.text.toString()
        var size: Double = binding.txtAddShoeSize.text.toString().toDouble()
        var comp: String = binding.txtAddCompanyName.text.toString()
        var desc: String = binding.txtAddShoedescription.text.toString()
        var imgSrc: String = R.drawable.noimage.toString()
        val shoe = Shoe(nam, size, comp, desc, listOf(imgSrc))
        myModelView.addShoe(shoe, true)
        Timber.d("new shoe added")
    }
}