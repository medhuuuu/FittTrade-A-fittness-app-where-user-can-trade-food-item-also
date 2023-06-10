package com.example.fittrade

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.fittrade.databinding.ActivityRegisterBinding
import com.example.fittrade.databinding.ActivitySellerAddItemBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class seller_add_item : AppCompatActivity() {
    private lateinit var binding: ActivitySellerAddItemBinding
    private lateinit var dbref : DatabaseReference
    private lateinit var sellerdbref : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        binding = ActivitySellerAddItemBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.addbutton.setOnClickListener {
            val item = binding.etAdditem1.text.toString()
            val itemprice = binding.etAdditem2.text.toString()
            val portion= binding.etAdditem3.text.toString()
            val companyname = binding.etAdditem4.text.toString()
            val expdate = binding.etAdditem5.text.toString()
            val userId = FirebaseAuth.getInstance().currentUser!!.uid

            val itemMap= hashMapOf(
                "itemName" to item,
                "price" to itemprice,
                "portion" to portion,
                "expdate" to expdate,
                "companyName" to companyname

//                "Itemno1Price" to itemno1price,
//                "Itemno2" to itemno2,
//                "Itemno2Price" to itemno2price,
//                "Itemno3" to itemno3,
//                "Itemno3Price" to itemno3price,
//                "Itemno4" to itemno4,
//                "Itemno4Price" to itemno4price,
//                "Itemno5" to itemno5,
//                "Itemno5Price" to itemno5price

            )
            //signin koro add koro shudu ei item??
            dbref = FirebaseDatabase.getInstance().getReference("Seller_item_list").child(userId).child(item)
            val key= dbref.key
            dbref.setValue(itemMap).addOnSuccessListener {

                startActivity(Intent(this, seller_productlist::class.java))
                Toast.makeText(this, "Added Done", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }

            sellerdbref = FirebaseDatabase.getInstance().getReference("Seller_product_list").child("seller").child(item)
            //val key1= dbref.key
            sellerdbref.setValue(itemMap).addOnSuccessListener {

                /*startActivity(Intent(this, seller_productlist::class.java))
                Toast.makeText(this, "Added Done", Toast.LENGTH_SHORT).show()*/
            }.addOnFailureListener {
                /*Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()*/
            }


        }

    }


}