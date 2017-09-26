package com.ikechukwuakalu.daggermvp.userdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ikechukwuakalu.daggermvp.R
import com.ikechukwuakalu.daggermvp.data.models.User
import com.ikechukwuakalu.daggermvp.di.scopes.ActivityScoped
import com.ikechukwuakalu.daggermvp.makeToast
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
        detailsProgress.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        detailsProgress.visibility = View.GONE
    }

    override fun setTitle(title: String) {
        setTitle(title)
    }

    override fun showUserDetails(user: User?) {
        detailsName.text = user?.name
        detailsLocation.text = user?.location
        detailsBio.text = user?.bio
        detailsPublicRepos.text = user?.public_repos
        with(detailsImage) {
            Picasso.with(context).load(user?.avatar_url).into(this)
        }
    }

    override fun showErrorLoadingUser(message: String) {
        makeToast(context, message)
    }
}
