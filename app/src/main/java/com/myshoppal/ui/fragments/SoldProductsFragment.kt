package com.myshoppal.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.myshoppal.R
import com.myshoppal.firestore.FirestoreClass
import com.myshoppal.models.SoldProduct
import com.myshoppal.ui.adapters.SoldProductsListAdapter
import kotlinx.android.synthetic.main.fragment_sold_products.*

/**
 * Sold Products Listing Fragment.
 */
class SoldProductsFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sold_products, container, false)
    }

    override fun onResume() {
        super.onResume()

        getSoldProductsList()
    }

    private fun getSoldProductsList() {
        // Show the progress dialog.
        showProgressDialog(resources.getString(R.string.please_wait))

        // Call the function of Firestore class.
        FirestoreClass().getSoldProductsList(this@SoldProductsFragment)
    }

    /**
     * A function to get the list of sold products.
     */
    fun successSoldProductsList(soldProductsList: ArrayList<SoldProduct>) {

        // Hide Progress dialog.
        hideProgressDialog()

        if (soldProductsList.size > 0) {
            rv_sold_product_items.visibility = View.VISIBLE
            tv_no_sold_products_found.visibility = View.GONE

            rv_sold_product_items.layoutManager = LinearLayoutManager(activity)
            rv_sold_product_items.setHasFixedSize(true)

            val soldProductsListAdapter =
                SoldProductsListAdapter(requireActivity(), soldProductsList)
            rv_sold_product_items.adapter = soldProductsListAdapter
        } else {
            rv_sold_product_items.visibility = View.GONE
            tv_no_sold_products_found.visibility = View.VISIBLE
        }
    }
}