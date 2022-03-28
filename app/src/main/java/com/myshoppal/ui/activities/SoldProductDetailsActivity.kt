package com.myshoppal.ui.activities

import android.os.Bundle
import android.view.View
import com.myshoppal.R
import com.myshoppal.models.SoldProduct
import com.myshoppal.utils.Constants
import com.myshoppal.utils.GlideLoader
import kotlinx.android.synthetic.main.activity_sold_product_details.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * A detail screen for the sold product item.
 */
class SoldProductDetailsActivity : BaseActivity() {

    /**
     * This function is auto created by Android when the Activity Class is created.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        //This call the parent constructor
        super.onCreate(savedInstanceState)
        // This is used to align the xml view to this class
        setContentView(R.layout.activity_sold_product_details)

        var productDetails: SoldProduct = SoldProduct()

        if (intent.hasExtra(Constants.EXTRA_SOLD_PRODUCT_DETAILS)) {
            productDetails =
                intent.getParcelableExtra<SoldProduct>(Constants.EXTRA_SOLD_PRODUCT_DETAILS)!!
        }

        // TODO Step 2: Call the function to setup action bar.
        // START
        setupActionBar()
        // END

        // TODO Step 4: Call the function to populate the data in UI.
        // START
        setupUI(productDetails)
        // END
    }

    // TODO Step 1: Create a function to setup action bar.
    // START
    /**
     * A function for actionBar Setup.
     */
    private fun setupActionBar() {

        setSupportActionBar(toolbar_sold_product_details_activity)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_white_color_back_24dp)
        }

        toolbar_sold_product_details_activity.setNavigationOnClickListener { onBackPressed() }
    }
    // END

    // TODO Step 3: Create a function to setupUI.
    // START
    /**
     * A function to setup UI.
     *
     * @param productDetails Order details received through intent.
     */
    private fun setupUI(productDetails: SoldProduct) {

        tv_sold_product_details_id.text = productDetails.order_id

        // Date Format in which the date will be displayed in the UI.
        val dateFormat = "dd MMM yyyy HH:mm"
        // Create a DateFormatter object for displaying date in specified format.
        val formatter = SimpleDateFormat(dateFormat, Locale.getDefault())

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        val calendar: Calendar = Calendar.getInstance()
        calendar.timeInMillis = productDetails.order_date
        tv_sold_product_details_date.text = formatter.format(calendar.time)

        GlideLoader(this@SoldProductDetailsActivity).loadProductPicture(
            productDetails.image,
            iv_product_item_image
        )
        tv_product_item_name.text = productDetails.title
        tv_product_item_price.text ="$${productDetails.price}"
        tv_sold_product_quantity.text = productDetails.sold_quantity

        tv_sold_details_address_type.text = productDetails.address.type
        tv_sold_details_full_name.text = productDetails.address.name
        tv_sold_details_address.text =
            "${productDetails.address.address}, ${productDetails.address.zipCode}"
        tv_sold_details_additional_note.text = productDetails.address.additionalNote

        if (productDetails.address.otherDetails.isNotEmpty()) {
            tv_sold_details_other_details.visibility = View.VISIBLE
            tv_sold_details_other_details.text = productDetails.address.otherDetails
        } else {
            tv_sold_details_other_details.visibility = View.GONE
        }
        tv_sold_details_mobile_number.text = productDetails.address.mobileNumber

        tv_sold_product_sub_total.text = productDetails.sub_total_amount
        tv_sold_product_shipping_charge.text = productDetails.shipping_charge
        tv_sold_product_total_amount.text = productDetails.total_amount
    }
    // END
}
