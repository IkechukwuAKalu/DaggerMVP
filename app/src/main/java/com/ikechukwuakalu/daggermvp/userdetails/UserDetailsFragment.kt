package com.ikechukwuakalu.daggermvp.userdetails

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ikechukwuakalu.daggermvp.R
import com.ikechukwuakalu.daggermvp.data.models.User
import com.ikechukwuakalu.daggermvp.di.scopes.ActivityScoped
import com.ikechukwuakalu.daggermvp.utils.makeToast
import com.squareup.picasso.Picasso
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.user_details_frag.*
import javax.inject.Inject

@ActivityScoped
class UserDetailsFragment @Inject constructor(): DaggerFragment(), UserDetailsContract.View{

    @Inject
    lateinit var presenter: UserDetailsContract.Presenter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater?.inflate(R.layout.user_details_frag, container, false)

    override fun onResume() {
        super.onResume()
        presenter.attach(this)
        presenter.fetchUserDetails()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }

    override fun showLoading() {
        detailsProgressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        detailsProgressBar.visibility = View.GONE
    }

    override fun setTitle(title: String) {
        activity.title = title
    }

    override fun showUserDetails(user: User?) {
        detailsName.text = "Name: ${user?.name}"
        detailsLocation.text = "Location: ${user?.location}"
        detailsBio.text = "Bio: ${user?.bio}"
        detailsPublicRepos.text = "No of Public Repos: ${user?.public_repos}"
        with(detailsImage) {
            Picasso.with(context).load(user?.avatar_url).into(this)
        }
        openBrowserButton.visibility = View.VISIBLE
        openBrowserButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/${user?.login}"))
            startActivity(intent)
        }
    }

    override fun showErrorLoadingUser(message: String) {
        makeToast(context, message)
    }
}
