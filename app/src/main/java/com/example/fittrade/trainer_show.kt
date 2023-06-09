package com.example.fittrade

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fittrade.databinding.ActivityDoctorShowBinding
import com.example.fittrade.databinding.ActivityTrainerShowBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import latestmsgclass

class trainer_show : AppCompatActivity() {
    lateinit var binding: ActivityTrainerShowBinding
    lateinit var toggle: ActionBarDrawerToggle
    private lateinit var recyclerView: RecyclerView
    private lateinit var latestArrayList: ArrayList<latestmsgclass>
    private lateinit var dbref : DatabaseReference
    private lateinit var db : FirebaseAuth
    private lateinit var latestAdapter: latestmessageAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityTrainerShowBinding.inflate(layoutInflater)
        setContentView(binding.root)
        veryfyisuserloggedin()


        binding.apply {
            toggle = ActionBarDrawerToggle(
                this@trainer_show,
                drawyerLayout,
                R.string.open,
                R.string.close
            )

            drawyerLayout.addDrawerListener(toggle)
            toggle.syncState()
            val senderuid = FirebaseAuth.getInstance().currentUser?.uid
            supportActionBar?.title ="Message Notification"
            dbref = FirebaseDatabase.getInstance().getReference()

            recyclerView= findViewById(R.id.recycle_latest_msg_trainer)
            recyclerView.addItemDecoration(DividerItemDecoration(this@trainer_show, DividerItemDecoration.VERTICAL))
            latestArrayList= arrayListOf()
            /* docarrayList = arrayListOf()
             userarrayList= arrayListOf()
     */
            recyclerView.layoutManager = LinearLayoutManager(this@trainer_show)

            val latesthashmap= HashMap<String,latestmsgclass>()
            fun refreshRecyleviewmsg(){
                latestArrayList.clear()
                latesthashmap.values.forEach{
                    latestArrayList.add(it)
                    recyclerView.adapter= latestmessageAdapter(this@trainer_show, latestArrayList)

                }

            }


            dbref.child("latestchat").child(senderuid!!)
                .addChildEventListener(object: ChildEventListener {
                    override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                        val chatMessage = snapshot.getValue(latestmsgclass::class.java)
                        latesthashmap[snapshot.key!!]= chatMessage!!
                        refreshRecyleviewmsg()
                    }

                    override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                        val chatMessage = snapshot.getValue(latestmsgclass::class.java)
                        latesthashmap[snapshot.key!!]= chatMessage!!
                        refreshRecyleviewmsg()
                    }

                    override fun onChildRemoved(snapshot: DataSnapshot) {
                        TODO("Not yet implemented")
                    }

                    override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                        TODO("Not yet implemented")
                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }

                })




            navView4.setNavigationItemSelectedListener {
                when(it.itemId){
                    R.id.provider_profile->{
                        startActivity(Intent(this@trainer_show, trainer_profile::class.java))
                    }
                    R.id.logout_provider->{
                       FirebaseAuth.getInstance().signOut()
                        val intent= Intent(this@trainer_show,MainActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }

                    R.id.new_msg ->{
                        startActivity(Intent(this@trainer_show, New_message::class.java))
                    }
                    R.id.trainer_msg ->{
                        startActivity(Intent(this@trainer_show, Trainer_log::class.java))
                    }

                    R.id.seller_msg ->{
                        startActivity(Intent(this@trainer_show, Seller_log::class.java))
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