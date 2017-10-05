package com.ikechukwuakalu.daggermvp.users

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import android.widget.EditText
import android.widget.LinearLayout
import com.ikechukwuakalu.daggermvp.R
import com.ikechukwuakalu.daggermvp.data.models.User
import com.ikechukwuakalu.daggermvp.di.scopes.ActivityScoped
import com.ikechukwuakalu.daggermvp.userdetails.UsersDetailsActivity
import com.ikechukwuakalu.daggermvp.utils.makeToast
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.users_list_frag.*
import javax.inject.Inject

@ActivityScoped
class UsersFragment @Inject constructor() : DaggerFragment(), UsersContract.View {

    @Inject
    lateinit var presenter: UsersContract.Presenter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater?.inflate(R.layout.users_list_frag, container, false)

    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
        usersRV.layoutManager = LinearLayoutManager(context)
        presenter.attach(this)
        presenter.loadUsers(null)
    }

    override fun onPause() {
        super.onPause()
        presenter.detach()
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun showUsers(users: List<User>) {
        usersRV.visibility = View.VISIBLE
        usersRV.adapter = UsersAdapter(users, View.OnClickListener {
            presenter.openUserDetails(it.tag.toString())
        })
    }

    override fun showUserDetails(login: String) {
        val intent = Intent(context, UsersDetailsActivity::class.java)
        intent.putExtra(UsersDetailsActivity.LOGIN_KEY, login)
        startActivity(intent)
    }

    override fun hideUsers() {
        usersRV.visibility = View.GONE
    }

    override fun showUsersNotAvailable() {
        makeToast(context, "Users not available")
    }

    override fun showErrorLoadingUsers(message: String?) {
        makeToast(context, message ?: "Error loading Users")
    }

    override fun setTitle(title: String) {
        activity.title = title
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.users_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.changeCityAction) {
            changeCityDialog()
        }
        return true
    }

    private fun changeCityDialog() {
        val alertDialog = AlertDialog.Builder(context)
        alertDialog.setTitle("Enter a new city")
        val cityBox = EditText(context)
        cityBox.hint = "city"
        val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        )
        cityBox.layoutParams = layoutParams
        cityBox.requestFocus()
        alertDialog.setView(cityBox)
        alertDialog.setPositiveButton("Change") { p0, p1 ->
            val city : String? = cityBox.text.trim().toString()
            if (city != "") {
                presenter.loadUsers(city)
            }
            p0.dismiss()
        }
        alertDialog.setNegativeButton("Cancel") { p0, p1 ->
            p0.dismiss()
        }
        alertDialog.show()
    }
}