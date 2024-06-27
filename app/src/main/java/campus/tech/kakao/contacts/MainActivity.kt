package campus.tech.kakao.contacts

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var noContactsText: TextView
    private lateinit var contactsRecyclerView: RecyclerView
    private lateinit var addContactButton: FloatingActionButton
    private val contactsList = mutableListOf<Contact>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        noContactsText = findViewById(R.id.noContactsText)
        contactsRecyclerView = findViewById(R.id.contactsRecyclerView)
        addContactButton = findViewById(R.id.addContactButton)

        contactsRecyclerView.layoutManager = LinearLayoutManager(this)
        contactsRecyclerView.adapter = ContactsAdapter(contactsList) { contact ->
            val intent = Intent(this, ContactDetailActivity::class.java)
            intent.putExtra("contact", contact)
            startActivity(intent)
        }

        addContactButton.setOnClickListener {
            val intent = Intent(this, AddContactActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_ADD_CONTACT)
        }

        updateUI()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_ADD_CONTACT && resultCode == RESULT_OK) {
            data?.getParcelableExtra<Contact>("contact")?.let { contact ->
                contactsList.add(contact)
                updateUI()
            }
        }
    }

    private fun updateUI() {
        if (contactsList.isEmpty()) {
            noContactsText.visibility = TextView.VISIBLE
            contactsRecyclerView.visibility = RecyclerView.GONE
        } else {
            noContactsText.visibility = TextView.GONE
            contactsRecyclerView.visibility = RecyclerView.VISIBLE
            contactsRecyclerView.adapter?.notifyDataSetChanged()
        }
    }

    companion object {
        const val REQUEST_CODE_ADD_CONTACT = 1
    }
}

