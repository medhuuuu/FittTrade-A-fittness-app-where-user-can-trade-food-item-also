package com.example.fittrade

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fittrade.databinding.ActivityDoctorShowBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import latestmsgclass

class doctor_show : AppCompatActivity() {

    lateinit var binding: ActivityDoctorShowBinding
    lateinit var toggle: ActionBarDrawerToggle
    private lateinit var recyclerView: RecyclerView
    private lateinit var latestArrayList: ArrayList<latestmsgclass>
    private lateinit var dbref : DatabaseReference
    private lateinit var db : FirebaseAuth
    private lateinit var latestmessageAdapter: latestmessageAdapter

    var receiverRoom : String?=null
    var senderRoom : String?=null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityDoctorShowBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val recieveruid = intent.getStringExtra("id")
        print(recieveruid)
        val senderuid = FirebaseAuth.getInstance().currentUser?.uid
        senderRoom = recieveruid + senderuid
        receiverRoom = senderuid + recieveruid



        dbref = FirebaseDatabase.getInstance().getReference()


        recyclerView= findViewById(R.id.recycle_latest_msg)
        latestArrayList= arrayListOf()

        recyclerView.layoutManager = LinearLayoutManager(this)



        veryfyisuserloggedin()
        binding.apply {
            toggle = ActionBarDrawerToggle(
                this@doctor_show,
                drawyerLayout,
                R.string.open,
                R.string.close
            )
            drawyerLayout.addDrawerListener(toggle)
            toggle.syncState()


            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            dbref.child("latestchat")
                .addValueEventListener(object : ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                       latestArrayList.clear()
                        for(postSnapshot in snapshot.children){
                            val message =postSnapshot.getValue(latestmsgclass::class.java)
                            latestArrayList.add(message!!)


                        }

                        recyclerView.adapter= latestmessageAdapter(this@doctor_show, latestArrayList)
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Toast.makeText(this@doctor_show, error.toString(), Toast.LENGTH_SHORT).show()
                    }

                })

            /*dbref.child("latestmsg").addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    latestArrayList.clear()

                    for (postSnapshot in snapshot.children) {
                        val user = postSnapshot.getValue(latestmsgclass::class.java)
                        latestArrayList.add(user!!)


                    }
                    recyclerView.adapter = latestmessageAdapter(this@doctor_show, latestArrayList)
                    latestmessageAdapter.notifyDataSetChanged()

                }


                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@doctor_show, error.toString(), Toast.LENGTH_SHORT).show()
                }
            })*/
            navView3.setNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.provider_profile -> {
                        startActivity(Intent(this@doctor_show, doc_profile::class.java))
                        /*Toast.makeText(this@Choose_prog, "User Profile", Toast.LENGTH_SHORT).show()*/

                    }

                    R.id.logout_provider -> {
                        FirebaseAuth.getInstance().signOut()
                        val intent = Intent(this@doctor_show, MainActivity::class.java)
                        intent.flags= Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)

                    }
                    R.id.new_msg -> {
                        startActivity(Intent(this@doctor_show, New_message::class.java))
                    }
                    R.id.trainer_msg ->{
                        startActivity(Intent(this@doctor_show, Trainer_log::class.java))
                    }
                    R.id.seller_msg ->{
                        startActivity(Intent(this@doctor_show, Seller_log::class.java))
                    }
                }
                true

            }

        }
    }


    private fun veryfyisuserloggedin() {
        val uid= FirebaseAuth.getInstance().uid
        if(uid==null){
            val intent= Intent(this, MainActivity::class.java)
            intent.flags= Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            true
        }
        return super.onOptionsItemSelected(item)
    }

}




