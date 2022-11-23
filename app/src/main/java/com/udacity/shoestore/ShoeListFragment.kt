package com.udacity.shoestore

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ItemdetaillayoutBinding
import com.udacity.shoestore.models.ShoeListViewModel
import timber.log.Timber

class ShoeListFragment : Fragment() {
    private lateinit var binding: FragmentShoeListBinding
    private lateinit var myViewModel: ShoeListViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)
        myViewModel = ViewModelProvider(requireActivity()).get(ShoeListViewModel::class.java)
        binding.shoeViewModel = myViewModel
        binding.lifecycleOwner = this
        myViewModel.listUpdated.observe(
            viewLifecycleOwner,
            Observer { updated -> if (updated) fillShoesList() })
        binding.btnAddShoe.setOnClickListener { v: View ->
            findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToAddShoeFragment())
        }
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Add menu items here
                menuInflater.inflate(R.menu.logoutmenu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                if (menuItem.itemId.equals(R.id.loginFragment))
                    findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment())
                else
                    Timber.d("menu item id not empleminted " + menuItem.itemId)
                return NavigationUI.onNavDestinationSelected(menuItem, view!!.findNavController())

            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
        return binding.root
    }

    fun fillShoesList() {
        myViewModel.lisUpdateComplete()
        binding.lstCountTxt.text = myViewModel.getShoes.value?.count().toString() + " Shoe(s)"
        val parentList = binding.lstLayOut
        val iterate = myViewModel.getShoes.value?.listIterator()
        if (iterate != null) {
            while (iterate.hasNext()) {
                val curShoe = iterate.next()
                val holder: ItemdetaillayoutBinding = DataBindingUtil.inflate(
                    layoutInflater,
                    R.layout.itemdetaillayout,
                    parentList,
                    false
                )
                holder.shoemod = curShoe
                if (curShoe.images != null && curShoe.images.count() > 0)
                    holder.shoeImage.setImageResource(curShoe.images[0].toInt())
                else holder.shoeImage.setImageResource(R.drawable.noimage)
                holder.shoeSize.text = curShoe.size.toString()
                parentList.addView(holder.root)
            }
        }

    }
}